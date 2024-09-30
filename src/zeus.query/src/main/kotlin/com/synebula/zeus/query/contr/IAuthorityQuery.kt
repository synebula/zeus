package com.synebula.zeus.query.contr

import com.synebula.gaea.data.permission.AuthorityType
import com.synebula.gaea.db.query.IQuery
import com.synebula.zeus.env.ResourceType
import com.synebula.zeus.query.view.AuthorityView

interface IAuthorityQuery : IQuery {

    /**
     * 获取角色已授权的资源
     *
     * @param resourceType 资源类型
     * @param role 角色id
     */
    fun authorized(resourceType: ResourceType, role: String): List<AuthorityView>

    /**
     * 获取角色资源的授权信息
     *
     * @param resourceType 资源里欸选哪个
     * @param resource 资源id
     * @param role 角色id
     */
    fun authorize(resourceType: ResourceType, resource: String, role: String): AuthorityType
}
