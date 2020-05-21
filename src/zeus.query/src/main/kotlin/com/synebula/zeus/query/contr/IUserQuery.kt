package com.synebula.zeus.query.contr

import com.synebula.gaea.data.message.Message

interface IUserQuery {
    /**
     * 登录接口
     *
     * @param name 用户名
     * @param password 密码
     *
     * @return 返回消息体, 200为登录成功, data为用户ID
     */
    fun signIn(name: String, password: String): Message<String>
}