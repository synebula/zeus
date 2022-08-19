package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongodb.query.MongodbQuery
import com.synebula.zeus.env.AuthorityType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.IAuthorityQuery
import com.synebula.zeus.query.contr.resouce.IPageQuery
import com.synebula.zeus.query.contr.resouce.ISystemQuery
import com.synebula.zeus.query.view.resource.PageView
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class PageQuery(template: MongoTemplate, var authorityQuery: IAuthorityQuery, var systemQuery: ISystemQuery) :
    MongodbQuery<PageView, String>(PageView::class.java, template), IPageQuery {

    override fun authorized(role: String): List<PageView> {
        return this.authorized(role, null)
    }

    override fun authorized(role: String, system: String?): List<PageView> {
        if (system != null) {
            val authority = this.systemQuery.authorize(system, role)
            if (authority == AuthorityType.Deny)
                return listOf()
        }
        val params = mutableMapOf<String, Any>()
        if (system != null) params["system"] = system
        val pages = this.list(params)
        val authorities = this.authorityQuery.authorized(ResourceType.Page, role)
        return pages.filter { i ->
            val authority = authorities.find { p -> i.id == p.resource }
            authority != null && authority.authority == AuthorityType.Allow
        }
    }

    override fun authorize(resource: String, role: String): AuthorityType {
        return this.authorityQuery.authorize(ResourceType.Page, resource, role)
    }

    override fun uriAuthorize(path: String, role: String): AuthorityType? {
        val page = this.template.findOne(
            Query.query(Criteria.where("uri").`is`(path)),
            this.clazz, this.collection(this.clazz)
        ) ?: return null
        return this.authorize(page.id!!, role)
    }
}
