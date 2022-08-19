package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.Application
import com.synebula.gaea.app.struct.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.spring.aop.annotation.Method
import com.synebula.gaea.spring.aop.annotation.Module
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
@Module("页面")
class PageApp(
    service: IPageService,
    logger: ILogger,
    var pageQuery: IPageQuery
) : Application<PageCmd, PageView, String>(
    "页面信息", service, pageQuery, logger
) {

    @Method("获取角色系统下有权页面")
    @GetMapping("/in-system/{system}/authorized/{role}")
    fun authorized(@PathVariable system: String, @PathVariable role: String): HttpMessage {
        val msg = HttpMessage()
        msg.data = this.pageQuery.authorized(role, system)
        return msg
    }

    @Method("获取角色全部有权页面")
    @GetMapping("/authorized/{role}")
    fun authorized(@PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.pageQuery.authorized(role)
        }
    }

    @Method("验证角色页面权限")
    @GetMapping("/{page}/authorize/{role}")
    fun authorize(@PathVariable page: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.pageQuery.authorize(page, role)
        }
    }

    @Method("验证角色URL权限")
    @GetMapping("/authorize/{role}")
    fun uriAuthorize(@PathVariable role: String, uri: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.pageQuery.uriAuthorize(uri, role)
        }
    }


}