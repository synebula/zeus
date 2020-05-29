package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.data.message.Message
import com.synebula.gaea.data.message.Status
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.ICommand
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.extension.*
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.User
import com.synebula.zeus.domain.service.contr.component.IUserNotifier
import com.synebula.zeus.domain.service.contr.rbac.IUserService
import java.util.*

class UserService(
    repository: IRepository,
    converter: IObjectConverter,
    logger: ILogger,
    var userNotifier: IUserNotifier
) :
    Service<User, String>(User::class.java, repository, converter, logger), IUserService {
    override fun add(command: ICommand): Message<String> {
        val user = this.convert(command)
        user.password = user.password.toMd5()
        user.token = UUID.randomUUID().toString()
        user.alive = false
        this.repository.add(user, this.clazz)
        userNotifier.added(user.id!!, user.name, user.token!!)
        return Message(user.id!!)
    }

    /**
     * 激活用户
     *
     * @param key 用户ID
     */
    override fun active(key: String, token: String): Message<Any> {
        val user = this.repository.get(key, this.clazz)
        return if (user.alive) {
            Message("用户${user.name}无需重复激活")
        } else {
            if (token == user.token) {
                user.alive = true
                user.token = null
                this.repository.update(user, this.clazz)
                Message(Status.Success, "用户${user.name}激活成功")
            } else {
                logger.warn(this, "用户${user.name}激活失败, {key: ${key}, token: ${token}")
                Message(Status.Failure, "用户${user.name}激活失败, 请从系统发送的邮件链接激活用户")
            }
        }
    }

    override fun changePassword(key: String, password: String, newPassword: String): Message<Any> {
        val user = this.repository.get(key, this.clazz)
        return if (user.password == password.toMd5()) {
            user.password = newPassword.toMd5()
            user.token = null
            this.repository.update(user, this.clazz)
            Message()
        } else {
            logger.warn(this, "用户修改${user.name}密码失败, 旧密码验证不通过")
            Message(Status.Failure, "用户修改密码失败, 旧密码验证不通过")
        }
    }


    override fun resetPassword(key: String, password: String, token: String?): Message<Any> {
        val user = this.repository.get(key, this.clazz)
        return if (token == user.token) {
            user.password = password.toMd5()
            user.token = null
            this.repository.update(user, this.clazz)
            Message()
        } else {
            logger.warn(this, "用户重置${user.name}密码失败, 系统密码修改令牌:${user.token}, {key: ${key} , token: ${token}")
            Message(Status.Failure, "用户重置密码失败, 如需重置密码请从系统发送的邮件链接中重置")
        }
    }

    override fun forgotPassword(key: String): Message<String> {
        val user = this.repository.get(key, this.clazz)
        return if (user.alive) {
            user.token = UUID.randomUUID().toString()
            this.repository.update(user, this.clazz)
            userNotifier.forgot(user.id!!, user.name, user.token!!)
            Message()
        } else
            Message(Status.Failure, "用户还未激活, 请先激活用户")
    }
}