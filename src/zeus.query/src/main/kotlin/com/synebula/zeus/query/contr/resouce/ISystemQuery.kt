package com.synebula.zeus.query.contr.resouce

import com.synebula.gaea.query.IQuery
import com.synebula.zeus.env.AuthorityType
import com.synebula.zeus.query.view.resource.SystemView

interface ISystemQuery : IQuery {

    fun authorized(role: String): List<SystemView>

    fun authorize(resource: String, role: String): AuthorityType?
}