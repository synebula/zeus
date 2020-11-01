package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.Application
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.service.cmd.rbac.resource.PageCmd
import com.synebula.zeus.domain.service.contr.rbac.resource.IPageService
import com.synebula.zeus.query.contr.resouce.IPageQuery
import com.synebula.zeus.query.view.resource.PageView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pages")
class PageApp(
    service: IPageService,
    query: IPageQuery,
    logger: ILogger
) : Application<PageCmd, PageView, String>(
    "页面信息", PageView::class.java,
    service, query, logger
)