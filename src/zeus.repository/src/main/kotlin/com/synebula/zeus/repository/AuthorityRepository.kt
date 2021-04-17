package com.synebula.zeus.repository

import com.synebula.gaea.mongo.repository.MongoRepository
import com.synebula.zeus.domain.model.rbac.Authority
import com.synebula.zeus.domain.repository.IAuthorityRepository
import com.synebula.zeus.env.ResourceType
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class AuthorityRepository(var template: MongoTemplate) : MongoRepository(template), IAuthorityRepository {
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