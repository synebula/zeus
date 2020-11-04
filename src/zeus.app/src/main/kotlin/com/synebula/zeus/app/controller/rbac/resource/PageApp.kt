package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.Application
import com.synebula.gaea.app.component.HttpMessage
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
class PageApp(
    service: IPageService,
    logger: ILogger,
    var pageQuery: IPageQuery
) : Application<PageCmd, PageView, String>(
    "页面信息", PageView::class.java,
    service, pageQuery, logger
) {

    @GetMapping("/in-system/{system}/permission/{role}")
    fun withSystemPermission(@PathVariable system: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.pageQuery.withPermission(role, system)
        }
    }

    @GetMapping("/permission/{role}")
    fun withPermission(@PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.pageQuery.withPermission(role)
        }
    }

    @GetMapping("/{page}/authentication/{role}")
    fun authentication(@PathVariable page: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.pageQuery.authentication(page, role)
        }
    }

    @GetMapping("/authentication/{role}")
    fun pathAuthentication(@PathVariable role: String, uri: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.pageQuery.uriAuthentication(uri, role)
        }
    }


}