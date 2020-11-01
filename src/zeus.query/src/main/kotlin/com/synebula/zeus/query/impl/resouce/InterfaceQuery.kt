package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.resouce.IInterfaceQuery
import com.synebula.zeus.query.view.resource.InterfaceView
import org.springframework.data.mongodb.core.MongoTemplate

class InterfaceQuery(template: MongoTemplate, var permissionQuery: PermissionQuery) : MongoQuery(template), IInterfaceQuery {

    private val clazz = InterfaceView::class.java

    override fun withPermission(role: String): List<InterfaceView> {
        val interfaces = this.list(mapOf(), this.clazz)
        val permissions = this.permissionQuery.resourcePermissions(ResourceType.Interface, role)
        return interfaces.filter { i -> permissions.find { p -> i.id == p.resource }?.authorization == PermissionType.Allow }
    }

    override fun authentication(resource: String, role: String): PermissionType {
        return this.permissionQuery.authentication(ResourceType.Interface, resource, role)
    }
}