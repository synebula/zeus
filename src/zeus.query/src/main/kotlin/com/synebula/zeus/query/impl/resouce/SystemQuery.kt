package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.data.permission.AuthorityType
import com.synebula.gaea.mongodb.query.MongodbQuery
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.IAuthorityQuery
import com.synebula.zeus.query.contr.resouce.ISystemQuery
import com.synebula.zeus.query.view.resource.SystemView
import org.springframework.data.mongodb.core.MongoTemplate

class SystemQuery(template: MongoTemplate, var authorityQuery: IAuthorityQuery) :
    MongodbQuery<SystemView, String>(SystemView::class.java, template), ISystemQuery {

    override fun authorized(role: String): List<SystemView> {
        val systems = this.list(mapOf())
        val authorities = this.authorityQuery.authorized(ResourceType.System, role)
        return systems.filter { i -> authorities.find { p -> i.id == p.resource }?.authority == AuthorityType.Allow }
    }

    override fun authorize(resource: String, role: String): AuthorityType {
        return this.authorityQuery.authorize(ResourceType.System, resource, role)
    }
}