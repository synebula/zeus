package com.synebula.zeus.query.contr.resouce

import com.synebula.gaea.query.IQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.query.view.resource.SystemView

interface ISystemQuery : IQuery {

    fun withPermission(role: String): List<SystemView>

    fun authentication(resource: String, role: String): PermissionType?
}