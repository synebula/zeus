package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.Application
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.service.cmd.rbac.resource.PermissionCmd
import com.synebula.zeus.domain.service.contr.rbac.resource.IPermissionService
import com.synebula.zeus.query.contr.resouce.IPermissionQuery
import com.synebula.zeus.query.view.resource.PermissionView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/permissions")
class PermissionApp(
    service: IPermissionService,
    query: IPermissionQuery,
    logger: ILogger
) : Application<PermissionCmd, PermissionView, String>(
    "权限信息", PermissionView::class.java,
    service, query, logger
)