package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.controller.DomainApplication
import com.synebula.gaea.db.query.IQuery
import com.synebula.gaea.log.ILogger
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
) : DomainApplication<GroupCmd, GroupView, String>(
    "用户组信息", service, query, GroupView::class.java, logger
)