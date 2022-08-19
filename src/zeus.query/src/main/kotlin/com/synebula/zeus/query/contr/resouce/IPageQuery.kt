package com.synebula.zeus.query.contr.resouce

import com.synebula.gaea.query.IQuery
import com.synebula.zeus.env.AuthorityType
import com.synebula.zeus.query.view.resource.PageView

interface IPageQuery : IQuery<PageView, String>  {

    fun authorized(role: String): List<PageView>

    fun authorized(role: String, system: String? ): List<PageView>

    fun authorize(resource: String, role: String): AuthorityType?

    fun uriAuthorize(path: String, role: String): AuthorityType?
}