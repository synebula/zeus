package com.synebula.zeus.query.contr.resouce

import com.synebula.gaea.query.IQuery
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.query.view.resource.PageView

interface IPageQuery : IQuery {

    fun withPermission(role: String): List<PageView>

    fun authentication(resource: String, role: String): PermissionType
}