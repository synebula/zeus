package com.synebula.zeus.app.controller

import com.synebula.gaea.app.UnionTypedApp
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQueryTyped
import com.synebula.zeus.domain.service.cmd.UserCmd
import com.synebula.zeus.domain.service.contr.rbac.IUserService
import com.synebula.zeus.query.view.UserView
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
)