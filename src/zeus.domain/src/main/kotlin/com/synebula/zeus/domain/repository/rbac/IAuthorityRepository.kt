package com.synebula.zeus.domain.repository.rbac

import com.synebula.gaea.domain.repository.IRepository
import com.synebula.zeus.domain.model.rbac.Authority
import com.synebula.zeus.env.ResourceType

interface IAuthorityRepository : IRepository<Authority, String> {
    fun removeByResourceRole(type: ResourceType, resource: List<String>, role: String)
}