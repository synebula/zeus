package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.serialization.IObjectMapper
import com.synebula.gaea.domain.service.Service
import com.synebula.zeus.domain.model.rbac.Authority
import com.synebula.zeus.domain.repository.rbac.IAuthorityRepository
import com.synebula.zeus.domain.service.cmd.rbac.AuthorityBatchAddCmd
import com.synebula.zeus.domain.service.contr.rbac.IAuthorityService
import com.synebula.zeus.env.ResourceType

class AuthorityService(
    private var authorityRepository: IAuthorityRepository,
    mapper: IObjectMapper
) : Service<Authority, String>(Authority::class.java, authorityRepository, mapper),
    IAuthorityService {

    override fun add(cmd: AuthorityBatchAddCmd) {
        val authorities = cmd.resource.map { Authority(cmd.role, it, cmd.type, cmd.authority) }
        this.repository.add(authorities)
    }

    override fun removeByResourceRole(type: ResourceType, resource: List<String>, role: String) {
        this.authorityRepository.removeByResourceRole(type, resource, role)
    }
}