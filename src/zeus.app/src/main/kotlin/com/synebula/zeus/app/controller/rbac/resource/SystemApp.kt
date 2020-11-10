package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.Application
import com.synebula.gaea.app.component.HttpMessage
import com.synebula.gaea.log.ILogger
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
) : Application<SystemCmd, SystemView, String>(
    "系统信息", SystemView::class.java,
    service, systemQuery, logger
) {
    @GetMapping("/permission/{role}")
    fun withPermission(@PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.systemQuery.withPermission(role)
        }
    }

    @GetMapping("/{system}/authentication/{role}")
    fun authentication(@PathVariable system: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.systemQuery.authentication(system, role)
        }
    }
}