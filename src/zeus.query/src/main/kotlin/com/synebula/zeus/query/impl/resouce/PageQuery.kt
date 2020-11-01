package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.resouce.IPageQuery
import com.synebula.zeus.query.contr.resouce.IPermissionQuery
import com.synebula.zeus.query.contr.resouce.ISystemQuery
import com.synebula.zeus.query.view.resource.PageView
import org.springframework.data.mongodb.core.MongoTemplate

class PageQuery(template: MongoTemplate, var permissionQuery: IPermissionQuery, var systemQuery: ISystemQuery) :
    MongoQuery(template), IPageQuery {
    private val clazz = PageView::class.java

    override fun withPermission(role: String): List<PageView> {
        return this.withPermission(role, null)
    }

    override fun withPermission(role: String, system: String?): List<PageView> {
        if (system != null) {
            val permission = this.systemQuery.authentication(system, role)
            if (permission == PermissionType.Deny)
                return listOf()
        }
        val params = mutableMapOf<String, Any>()
        if (system != null) params["system"] = system
        val pages = this.list(params, this.clazz)
        val permissions = this.permissionQuery.resourcePermissions(ResourceType.Page, role)
        return pages.filter { i ->
            val permission = permissions.find { p -> i.id == p.resource }
            permission == null || permission.authorization == PermissionType.Allow
        }
    }

    override fun authentication(resource: String, role: String): PermissionType {
        return this.permissionQuery.authentication(ResourceType.Page, resource, role)
    }
}