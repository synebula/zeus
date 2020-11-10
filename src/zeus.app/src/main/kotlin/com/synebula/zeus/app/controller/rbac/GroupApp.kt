package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.Application
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQuery
import com.synebula.zeus.domain.service.cmd.rbac.GroupCmd
import com.synebula.zeus.domain.service.contr.rbac.IGroupService
import com.synebula.zeus.query.view.GroupView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/groups")
class GroupApp(
    service: IGroupService,
    query: IQuery,
    logger: ILogger
) : Application<GroupCmd, GroupView, String>(
    "分组信息", GroupView::class.java,
    service, query, logger
)