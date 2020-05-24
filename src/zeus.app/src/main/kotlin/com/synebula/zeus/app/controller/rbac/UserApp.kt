package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.UnionApp
import com.synebula.gaea.app.component.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQuery
import com.synebula.zeus.app.component.IUserAdded
import com.synebula.zeus.domain.service.cmd.rbac.UserCmd
import com.synebula.zeus.domain.service.contr.rbac.IUserService
import com.synebula.zeus.query.view.UserView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserApp(
    service: IUserService,
    query: IQuery,
    logger: ILogger
) : UnionApp<UserCmd, UserView, String>(
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
    fun active(@PathVariable key: String): String {
        this.safeExecute("激活用户出现异常") {
            (this.service as IUserService).active(key)
        }
        return "<html><body><div style='text-align: center; padding: 100px;'><h2>激活成功！</h2></div></body></html>"
    }
}