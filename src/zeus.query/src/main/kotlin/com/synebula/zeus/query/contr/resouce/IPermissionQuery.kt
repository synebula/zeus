package com.synebula.zeus.query.contr.resouce

import com.synebula.gaea.query.IQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.view.resource.PermissionView

interface IPermissionQuery : IQuery {

    fun resourcePermissions(resourceType: ResourceType, role: String): List<PermissionView>

    fun authentication(resourceType: ResourceType, resource: String, role: String): PermissionType?
}