package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.Application
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.service.cmd.rbac.resource.InterfaceCmd
import com.synebula.zeus.domain.service.contr.rbac.resource.IInterfaceService
import com.synebula.zeus.query.impl.resouce.InterfaceQuery
import com.synebula.zeus.query.view.resource.InterfaceView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/interfaces")
class InterfaceApp(
    service: IInterfaceService,
    query: InterfaceQuery,
    logger: ILogger
) : Application<InterfaceCmd, InterfaceView, String>(
    "接口信息", InterfaceView::class.java,
    service, query, logger
)