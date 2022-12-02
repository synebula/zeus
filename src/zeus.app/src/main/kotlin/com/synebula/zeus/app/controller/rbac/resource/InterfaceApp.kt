package com.synebula.zeus.app.controller.rbac.resource

import com.synebula.gaea.app.controller.Application
import com.synebula.gaea.data.message.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.spring.aop.annotation.Method
import com.synebula.zeus.domain.service.cmd.rbac.resource.InterfaceCmd
import com.synebula.zeus.domain.service.contr.rbac.resource.IInterfaceService
import com.synebula.zeus.query.contr.resouce.IInterfaceQuery
import com.synebula.zeus.query.view.resource.InterfaceView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/interfaces")
class InterfaceApp(
    service: IInterfaceService,
    logger: ILogger,
    var interfaceQuery: IInterfaceQuery
) : Application<InterfaceCmd, InterfaceView, String>(
    "接口信息", service, interfaceQuery, logger
) {

    @Method("获取角色系统下有权接口")
    @GetMapping("/in-system/{system}/authorized/{role}")
    fun authorized(@PathVariable system: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.interfaceQuery.authorized(role, system)
        }
    }

    @Method("获取角色全部有权接口")
    @GetMapping("/authorized/{role}")
    fun authorized(@PathVariable role: String): HttpMessage {
        return this.safeExecute("获取有权资源列表失败") { msg ->
            msg.data = this.interfaceQuery.authorized(role)
        }
    }

    @Method("验证角色接口权限")
    @GetMapping("/{api}/authorize/{role}")
    fun authorize(@PathVariable api: String, @PathVariable role: String): HttpMessage {
        return this.safeExecute("获取权限信息失败") { msg ->
            msg.data = this.interfaceQuery.authorize(api, role)
        }
    }
}