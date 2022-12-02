package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.data.permission.AuthorityType
import com.synebula.gaea.mongodb.query.MongodbQuery
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.IAuthorityQuery
import com.synebula.zeus.query.contr.resouce.IInterfaceQuery
import com.synebula.zeus.query.contr.resouce.ISystemQuery
import com.synebula.zeus.query.view.resource.InterfaceView
import org.springframework.data.mongodb.core.MongoTemplate

class InterfaceQuery(
    template: MongoTemplate,
    var authorityQuery: IAuthorityQuery,
    var systemQuery: ISystemQuery
) : MongodbQuery<InterfaceView, String>(InterfaceView::class.java, template), IInterfaceQuery {

    override fun authorized(role: String): List<InterfaceView> {
        return this.authorized(role, null)

    }

    override fun authorized(role: String, system: String?): List<InterfaceView> {
        if (system != null) {
            val authority = this.systemQuery.authorize(system, role)
            if (authority == AuthorityType.Deny)
                return listOf()
        }
        val params = mutableMapOf<String, String>()
        if (system != null) params["system"] = system
        val interfaces = this.list(params)
        val authorities = this.authorityQuery.authorized(ResourceType.Interface, role)
        return interfaces.filter { i ->
            val authority = authorities.find { p -> i.id == p.resource }
            authority == null || authority.authority == AuthorityType.Allow
        }
    }

    override fun authorize(resource: String, role: String): AuthorityType {
        return this.authorityQuery.authorize(ResourceType.Interface, resource, role)
    }
}