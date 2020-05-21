package com.synebula.zeus.domain.service.contr.rbac

import com.synebula.gaea.domain.service.IService

interface IUserService : IService<String> {
    /**
     * 激活用户
     *
     * @param key 用户ID
     */
    fun active(key: String)
}