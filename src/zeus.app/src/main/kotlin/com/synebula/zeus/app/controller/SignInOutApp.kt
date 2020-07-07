package com.synebula.zeus.app.controller

import com.synebula.gaea.app.IApplication
import com.synebula.gaea.app.component.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.query.contr.IUserQuery
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign")
class SignInOutApp(var query: IUserQuery, override var logger: ILogger?) : IApplication {
    override var name: String = "用户登录管理"

    @PostMapping("/in")
    fun signIn(name: String, password: String): HttpMessage {
        return this.safeExecute("用户登录出现异常") {
            it.load(this.query.signIn(name, password))
        }
    }

    @PostMapping("/out")
    fun signOut(user: String): HttpMessage {
        return HttpMessage(user)
    }
}