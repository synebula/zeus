package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.controller.DomainApplication
import com.synebula.gaea.db.query.IQuery
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.service.cmd.rbac.RoleCmd
import com.synebula.zeus.domain.service.contr.rbac.IRoleService
import com.synebula.zeus.query.view.RoleView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/roles")
class RoleApp(
    service: IRoleService,
    query: IQuery,
    logger: ILogger
) : DomainApplication<RoleCmd, RoleView, String>(
    "用户信息", service, query, RoleView::class.java, logger
)