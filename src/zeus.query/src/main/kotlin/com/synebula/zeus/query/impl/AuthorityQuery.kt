package com.synebula.zeus.query.impl

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.env.AuthorityType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.IAuthorityQuery
import com.synebula.zeus.query.view.AuthorityView
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class AuthorityQuery(template: MongoTemplate) : MongoQuery(template),
    IAuthorityQuery {
    var clazz = AuthorityView::class.java
    var collection = this.collection(this.clazz)

    override fun authorized(resourceType: ResourceType, role: String): List<AuthorityView> {
        return this.template.find(
                Query.query(
                        Criteria.where("type").`is`(resourceType)
                                .and("role").`is`(role)
                ), this.clazz, this.collection)
    }

    override fun authorize(resourceType: ResourceType, resource: String, role: String): AuthorityType {
        val authority = this.template.findOne(
                Query.query(
                        Criteria.where("type").`is`(resourceType)
                                .and("resource").`is`(resource)
                                .and("role").`is`(role)
                ), this.clazz, this.collection)
        return authority?.authority ?: AuthorityType.Default
    }
}