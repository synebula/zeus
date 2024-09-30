package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.controller.DomainApplication
import com.synebula.gaea.data.message.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.spring.aop.annotation.Method
import com.synebula.zeus.domain.service.cmd.rbac.resource.SystemCmd
import com.synebula.zeus.domain.service.contr.rbac.resource.ISystemService
import com.synebula.zeus.query.contr.resouce.ISystemQuery
import com.synebula.zeus.query.view.resource.SystemView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/systems")
class SystemApp(
    service: ISystemService,
    logger: ILogger,
    var systemQuery: ISystemQuery
) : DomainApplication<SystemCmd, SystemView, String>(
    "系统信息", service, systemQuery, SystemView::class.java, logger
) {
    @Method("获取角色有权系统")
    @GetMapping("/authorized/{role}")
    fun authorized(@PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.systemQuery.authorized(role)
        }
    }

    @Method("验证角色系统权限")
    @GetMapping("/{system}/authorize/{role}")
    fun authorize(@PathVariable system: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.systemQuery.authorize(system, role)
        }
    }
}