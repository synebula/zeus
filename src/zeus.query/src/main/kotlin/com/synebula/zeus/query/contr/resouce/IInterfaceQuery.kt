package com.synebula.zeus.query.contr.resouce

import com.synebula.gaea.data.permission.AuthorityType
import com.synebula.gaea.query.IQuery
import com.synebula.zeus.query.view.resource.InterfaceView

interface IInterfaceQuery : IQuery<InterfaceView, String>  {

    fun authorized(role: String): List<InterfaceView>

    fun authorized(role: String, system: String?): List<InterfaceView>

    fun authorize(resource: String, role: String): AuthorityType?
}