package com.synebula.zeus.domain.service.ctr.rbac

import com.synebula.gaea.data.message.Message
import com.synebula.gaea.domain.service.IService

interface IUserService : IService<String> {
    /**
     * 激活用户
     *
     * @param key 用户ID
     * @param token 激活密令
     */
    fun active(key: String, token: String): Message<Any>

    /**
     * 激活用户
     *
     * @param key 用户ID
     * @param password 旧密码
     * @param newPassword 新密码
     */
    fun changePassword(key: String, password: String, newPassword: String): Message<Any>

    /**
     * 激活用户
     *
     * @param key 用户ID
     * @param password 新密码
     */
    fun resetPassword(key: String, password: String, token: String?): Message<Any>


    /**
     * 激活用户
     *
     * @param key 用户ID
     */
    fun forgotPassword(key: String): Message<String>
}