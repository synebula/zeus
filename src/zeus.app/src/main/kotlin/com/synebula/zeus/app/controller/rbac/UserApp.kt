package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.controller.DomainApplication
import com.synebula.gaea.data.message.HttpMessage
import com.synebula.gaea.data.message.Status
import com.synebula.gaea.data.serialization.json.IJsonSerializer
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.service.cmd.rbac.UserCmd
import com.synebula.zeus.domain.service.contr.rbac.IUserService
import com.synebula.zeus.query.contr.IUserQuery
import com.synebula.zeus.query.view.UserView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserApp(
    service: IUserService,
    query: IUserQuery,
    logger: ILogger
) : DomainApplication<UserCmd, UserView, String>(
    "用户信息", service, query, UserView::class.java, logger
) {

    @Autowired
    lateinit var serializer: IJsonSerializer

    override fun add(command: UserCmd): HttpMessage {
        return this.safeExecute("查询重复用户信息出错, 用户信息: ${serializer.serialize(command)}") {
            val list = this.query.list(mapOf(Pair("name", command.name)), UserView::class.java)
            if (list.isEmpty())
                it.from(super.add(command))
            else {
                it.status = Status.Failure
                it.message = "系统中已存在该用户"
            }
        }
    }

    /**
     * 激活用户
     *
     * @param key 用户ID
     */
    @GetMapping("/{key}/active")
    fun active(@PathVariable key: String, token: String): String {
        this.safeExecute("激活用户出现异常") {
            (this.service as IUserService).active(key, token)
        }
        return "<html><body><div style='text-align: center; padding: 100px;'><h2>激活成功！</h2></div></body></html>"
    }

    @GetMapping("/{name}/forgot")
    fun forgot(@PathVariable name: String): HttpMessage {
        return this.safeExecute("遗忘用户密码出现异常") {
            val users = this.query.list(mapOf(Pair("name", name)), UserView::class.java)
            if (users.isNotEmpty()) {
                it.load((this.service as IUserService).forgotPassword(users[0].id))

            } else {
                it.status = Status.Failure
                it.message = "找不到该用户信息"
            }
        }
    }

    @PutMapping("/{key}/password/reset")
    fun resetPassword(@PathVariable key: String, password: String, token: String?): HttpMessage {
        return this.safeExecute("重置用户密码出现异常") {
            it.load((this.service as IUserService).resetPassword(key, password, token))
        }
    }

    @PutMapping("/{key}/password")
    fun changePassword(@PathVariable key: String, password: String, newPassword: String): HttpMessage {
        return this.safeExecute("修改用户密码出现异常") {
            it.load((this.service as IUserService).changePassword(key, password, newPassword))
        }
    }
}