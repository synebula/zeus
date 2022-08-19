package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.Application
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.service.cmd.rbac.GroupCmd
import com.synebula.zeus.domain.service.contr.rbac.IGroupService
import com.synebula.zeus.query.contr.IGroupQuery
import com.synebula.zeus.query.view.GroupView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/groups")
class GroupApp(
    service: IGroupService,
    query: IGroupQuery,
    logger: ILogger
) : Application<GroupCmd, GroupView, String>(
    "分组信息", service, query, logger
)