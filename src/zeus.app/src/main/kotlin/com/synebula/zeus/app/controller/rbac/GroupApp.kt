package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.UnionApp
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQuery
import com.synebula.zeus.domain.service.cmd.rbac.GroupCmd
import com.synebula.zeus.domain.service.cmd.rbac.RoleCmd
import com.synebula.zeus.domain.service.contr.rbac.IGroupService
import com.synebula.zeus.domain.service.contr.rbac.IRoleService
import com.synebula.zeus.query.view.GroupView
import com.synebula.zeus.query.view.RoleView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/groups")
class GroupApp(
    service: IGroupService,
    query: IQuery,
    logger: ILogger
) : UnionApp<GroupCmd, GroupView, String>(
    "分组信息", GroupView::class.java,
    service, query, logger
)