package com.synebula.zeus.domain.service.contr.rbac

import com.synebula.gaea.domain.service.IService
import com.synebula.zeus.domain.service.cmd.rbac.AuthorityBatchAddCmd
import com.synebula.zeus.env.ResourceType

interface IAuthorityService : IService<String> {

    fun add(cmd: AuthorityBatchAddCmd)

    fun removeByResourceRole(type: ResourceType, resource: List<String>, role: String)

}