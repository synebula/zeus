package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.resouce.ISystemQuery
import com.synebula.zeus.query.view.resource.SystemView
import org.springframework.data.mongodb.core.MongoTemplate

class SystemQuery(template: MongoTemplate, var permissionQuery: PermissionQuery) : MongoQuery(template), ISystemQuery {
    private val clazz = SystemView::class.java

    override fun withPermission(role: String): List<SystemView> {
        val systems = this.list(mapOf(), this.clazz)
        val permissions = this.permissionQuery.resourcePermissions(ResourceType.System, role)
        return systems.filter { i -> permissions.find { p -> i.id == p.resource }?.authority == PermissionType.Allow }
    }

    override fun authentication(resource: String, role: String): PermissionType? {
        return this.permissionQuery.authentication(ResourceType.System, resource, role)
    }
}