package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.message.DataMessage
import com.synebula.gaea.data.message.Status
import com.synebula.gaea.data.serialization.IObjectMapper
import com.synebula.gaea.domain.service.ICommand
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.ext.toMd5
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.User
import com.synebula.zeus.domain.repository.rbac.IUserRepository
import com.synebula.zeus.domain.service.component.IUserNotifier
import com.synebula.zeus.domain.service.contr.rbac.IUserService
import java.util.*

class UserService(
    repository: IUserRepository,
    mapper: IObjectMapper,
    var userNotifier: IUserNotifier?,
    var logger: ILogger
) : Service<User, String>(User::class.java, repository, mapper), IUserService {

//    init {
//        groupService.addBeforeRemoveListener(this.clazz.name) { id ->
//            val msg = Message()
//            if (this.repository.count(mapOf(Pair("group", id)), this.clazz) > 0) {
//                msg.status = Status.Failure
//                msg.message = "组下还有用户"
//            }
//            msg
//        }
//        roleService.addBeforeRemoveListener(this.clazz.name) { id ->
//            val msg = Message()
//            if (this.repository.count(mapOf(Pair("role", id)), this.clazz) > 0) {
//                msg.status = Status.Failure
//                msg.message = "角色下还有用户"
//            }
//            msg
//        }
//    }

    override fun add(command: ICommand): DataMessage<String> {
        val user = this.map(command)
        user.password = user.password.toMd5()
        user.token = UUID.randomUUID().toString()
        user.alive = false
        this.repository.add(user)
        userNotifier?.added(user.id!!, user.name, user.token!!)
        return DataMessage(user.id!!)
    }

    /**
     * 激活用户
     *
     * @param key 用户ID
     */
    override fun active(key: String, token: String): DataMessage<Any> {
        val user = this.repository.get(key)!!
        return if (user.alive) {
            DataMessage("用户${user.name}无需重复激活")
        } else {
            if (token == user.token) {
                user.alive = true
                user.token = null
                this.repository.update(user)
                DataMessage(Status.Success, "用户${user.name}激活成功")
            } else {
                logger.warn(this, "用户${user.name}激活失败, {key: ${key}, token: ${token}")
                DataMessage(Status.Failure, "用户${user.name}激活失败, 请从系统发送的邮件链接激活用户")
            }
        }
    }

    override fun changePassword(key: String, password: String, newPassword: String): DataMessage<Any> {
        val user = this.repository.get(key)!!
        return if (user.password == password.toMd5()) {
            user.password = newPassword.toMd5()
            user.token = null
            this.repository.update(user)
            DataMessage()
        } else {
            logger.warn(this, "用户修改${user.name}密码失败, 旧密码验证不通过")
            DataMessage(Status.Failure, "用户修改密码失败, 旧密码验证不通过")
        }
    }


    override fun resetPassword(key: String, password: String, token: String?): DataMessage<Any> {
        val user = this.repository.get(key)!!
        return if (token == user.token) {
            user.password = password.toMd5()
            user.token = null
            this.repository.update(user)
            DataMessage()
        } else {
            logger.warn(this, "用户重置${user.name}密码失败, 系统密码修改令牌:${user.token}, {key: ${key} , token: ${token}")
            DataMessage(Status.Failure, "用户重置密码失败, 如需重置密码请从系统发送的邮件链接中重置")
        }
    }

    override fun forgotPassword(key: String): DataMessage<String> {
        val user = this.repository.get(key)!!
        return if (user.alive) {
            user.token = UUID.randomUUID().toString()
            this.repository.update(user)
            userNotifier?.forgot(user.id!!, user.name, user.token!!)
            DataMessage()
        } else
            DataMessage(Status.Failure, "用户还未激活, 请先激活用户")
    }
}