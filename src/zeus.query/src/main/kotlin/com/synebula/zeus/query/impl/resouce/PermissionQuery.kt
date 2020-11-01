package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.contr.resouce.IPermissionQuery
import com.synebula.zeus.query.view.resource.PermissionView
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class PermissionQuery(template: MongoTemplate) : MongoQuery(template), IPermissionQuery {
    var clazz = PermissionView::class.java
    var collection = this.collection(this.clazz)

    override fun resourcePermissions(resourceType: ResourceType, role: String): List<PermissionView> {
        return this.template.find(
                Query.query(
                        Criteria.where("resourceType").`is`(resourceType)
                                .and("role").`is`(role)
                ), this.clazz, this.collection)
    }

    override fun authentication(resourceType: ResourceType, resource: String, role: String): PermissionType {
        val permission = this.template.findOne(
                Query.query(
                        Criteria.where("resourceType").`is`(resourceType)
                                .and("resource").`is`(resource)
                                .and("role").`is`(role)
                ), this.clazz, this.collection)
        return permission?.authorization ?: PermissionType.Allow
    }
}