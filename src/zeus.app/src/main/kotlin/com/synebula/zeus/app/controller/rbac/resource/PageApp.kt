package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.Application
import com.synebula.gaea.app.component.aop.annotation.ModuleName
import com.synebula.gaea.app.struct.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.service.cmd.rbac.resource.PageCmd
import com.synebula.zeus.domain.service.contr.rbac.resource.IPageService
import com.synebula.zeus.query.contr.resouce.IPageQuery
import com.synebula.zeus.query.view.resource.PageView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pages")
@ModuleName("页面")
class PageApp(
    service: IPageService,
    logger: ILogger,
    var pageQuery: IPageQuery
) : Application<PageCmd, PageView, String>(
    "页面信息", PageView::class.java,
    service, pageQuery, logger
) {

    @GetMapping("/in-system/{system}/authorized/{role}")
    fun authorized(@PathVariable system: String, @PathVariable role: String): HttpMessage {
        val msg = HttpMessage()
        msg.data = this.pageQuery.authorized(role, system)
        return msg
    }

    @GetMapping("/authorized/{role}")
    fun authorized(@PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.pageQuery.authorized(role)
        }
    }

    @GetMapping("/{page}/authorize/{role}")
    fun authorize(@PathVariable page: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.pageQuery.authorize(page, role)
        }
    }

    @GetMapping("/authorize/{role}")
    fun uriAuthorize(@PathVariable role: String, uri: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.pageQuery.uriAuthorize(uri, role)
        }
    }


}