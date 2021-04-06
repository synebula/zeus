package com.synebula.zeus.app.controller

import com.synebula.gaea.app.IApplication
import com.synebula.gaea.app.component.security.TokenManager
import com.synebula.gaea.app.struct.HttpMessage
import com.synebula.gaea.data.message.Status
import com.synebula.gaea.data.serialization.json.IJsonSerializer
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQuery
import com.synebula.zeus.domain.service.cmd.rbac.UserCmd
import com.synebula.zeus.domain.service.contr.rbac.IUserService
import com.synebula.zeus.query.contr.IUserQuery
import com.synebula.zeus.query.view.UserView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign")
class SignInOutApp(override var logger: ILogger?) : IApplication {
    @Autowired
    lateinit var query: IQuery

    @Autowired
    lateinit var userQuery: IUserQuery

    @Autowired
    lateinit var userService: IUserService

    @Autowired
    lateinit var tokenHelper: TokenManager

    @Autowired
    lateinit var serializer: IJsonSerializer

    override var name: String = "用户登录管理"

    @PostMapping("/in")
    fun signIn(name: String, password: String, remember: Boolean?): HttpMessage {
        return this.safeExecute("用户登录出现异常") {
            val message = this.userQuery.signIn(name, password)
            if (message.data != null) {
                val user = message.data
                user!!.remember = remember ?: false
                val token = tokenHelper.sign(message.data!!)
                it.data = token
            } else {
                it.load(message)
            }
        }
    }

    @PostMapping("/out")
    fun signOut(user: String): HttpMessage {
        return HttpMessage(user)
    }

    @PostMapping("/up")
    fun signUp(@RequestBody command: UserCmd): HttpMessage {
        return this.safeExecute("用户注册出错, 用户信息: ${serializer.serialize(command)}") {
            val list = this.query.list(mapOf(Pair("name", command.name)), UserView::class.java)
            if (list.count() == 0) {
                val message = userService.add(command)
                it.data = message.data
            } else {
                it.status = Status.Failure
                it.message = "系统中已存在该用户"
            }
        }
    }
}