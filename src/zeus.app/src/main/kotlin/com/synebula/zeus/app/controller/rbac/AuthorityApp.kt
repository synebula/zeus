package com.synebula.zeus.app.controller.rbac

import com.synebula.gaea.app.controller.Application
import com.synebula.gaea.data.message.HttpMessage
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.spring.aop.annotation.Method
import com.synebula.zeus.domain.service.cmd.rbac.AuthorityBatchAddCmd
import com.synebula.zeus.domain.service.cmd.rbac.AuthorityCmd
import com.synebula.zeus.domain.service.contr.rbac.IAuthorityService
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.IAuthorityQuery
import com.synebula.zeus.query.view.AuthorityView
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/authorities")
class AuthorityApp(
    query: IAuthorityQuery,
    logger: ILogger,
    private var authorityService: IAuthorityService
) : Application<AuthorityCmd, AuthorityView, String>(
    "权限信息", authorityService, query, logger
) {
    @Method("批量添加权限信息")
    @PostMapping("/batch")
    fun add(@RequestBody cmd: AuthorityBatchAddCmd): HttpMessage {
        this.authorityService.add(cmd)
        return this.httpMessageFactory.create()
    }

    @Method("根据资源和角色删除权限")
    @DeleteMapping("/{type}/role/{role}")
    fun removeByResourceRole(
        @PathVariable type: ResourceType,
        @PathVariable role: String,
        @RequestBody resource: List<String>
    ): HttpMessage {
        this.authorityService.removeByResourceRole(type, resource, role)
        return this.httpMessageFactory.create()
    }
}