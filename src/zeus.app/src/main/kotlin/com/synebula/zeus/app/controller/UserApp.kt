package com.synebula.zeus.app.controller

import com.synebula.gaea.app.UnionTypedApp
import com.synebula.gaea.app.component.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQueryTyped
import com.synebula.zeus.app.component.IUserAdded
import com.synebula.zeus.domain.service.cmd.rbac.UserCmd
import com.synebula.zeus.domain.service.contr.rbac.IUserService
import com.synebula.zeus.query.view.UserView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserApp(
        service: IUserService,
        query: IQueryTyped,
        logger: ILogger
) : UnionTypedApp<UserCmd, UserView, String>(
        "用户信息", UserView::class.java,
        service, query, logger
) {
    @Autowired
    lateinit var userAdded: IUserAdded

    override fun add(command: UserCmd): HttpMessage {
        val msg = super.add(command)
        userAdded.added(msg.data.toString(), command.name)
        return msg
    }

    /**
     * 激活用户
     *
     * @param key 用户ID
     */
    @GetMapping("/{key}/active")
    fun active(key: String) {
        this.safeExecute("激活用户出现异常") {
            (this.service as IUserService).active(key)
        }
    }
}