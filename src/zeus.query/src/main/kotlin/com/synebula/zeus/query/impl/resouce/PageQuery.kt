package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.resouce.IPageQuery
import com.synebula.zeus.query.view.resource.PageView
import org.springframework.data.mongodb.core.MongoTemplate

class PageQuery(template: MongoTemplate, var permissionQuery: PermissionQuery) : MongoQuery(template), IPageQuery {
    private val clazz = PageView::class.java

    override fun withPermission(role: String): List<PageView> {
        val pages = this.list(mapOf(), this.clazz)
        val permissions = this.permissionQuery.resourcePermissions(ResourceType.Page, role)
        return pages.filter { i -> permissions.find { p -> i.id == p.resource }?.authorization == PermissionType.Allow }
    }

    override fun authentication(resource: String, role: String): PermissionType {
        return this.permissionQuery.authentication(ResourceType.Page, resource, role)
    }
}