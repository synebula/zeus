package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.Application
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQuery
import com.synebula.zeus.domain.service.cmd.rbac.RoleCmd
import com.synebula.zeus.domain.service.contr.rbac.IRoleService
import com.synebula.zeus.query.view.RoleView
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/roles")
class RoleApp(
    service: IRoleService,
    query: IQuery,
    logger: ILogger
) : Application<RoleCmd, RoleView, String>(
    "用户信息", RoleView::class.java,
    service, query, logger
)