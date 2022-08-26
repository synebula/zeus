package com.synebula.zeus.repository

import com.synebula.gaea.mongodb.repository.MongodbRepository
import com.synebula.zeus.domain.model.rbac.Authority
import com.synebula.zeus.domain.repository.rbac.IAuthorityRepository
import com.synebula.zeus.env.ResourceType
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class AuthorityRepository(var template: MongoTemplate) :
    MongodbRepository<Authority, String>(Authority::class.java, template), IAuthorityRepository {
    override fun removeByResourceRole(type: ResourceType, resource: List<String>, role: String) {
        this.template.remove(
            Query.query(
                Criteria.where("type").`is`(type)
                    .and("resource").`in`(resource)
                    .and("role").`is`(role)
            ),
            Authority::class.java
        )
    }
}