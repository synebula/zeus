package com.synebula.zeus.domain.repository

import com.synebula.gaea.domain.repository.IRepository
import com.synebula.zeus.env.ResourceType

interface IAuthorityRepository : IRepository {
    fun removeByResourceRole(type: ResourceType, resource: List<String>, role: String)
}