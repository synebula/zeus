package com.synebula.zeus.query.contr

import com.synebula.gaea.data.message.DataMessage
import com.synebula.zeus.query.view.SignUserView
import com.synebula.zeus.query.view.UserView

interface IUserQuery {
    /**
     * 登录接口
     *
     * @param name 用户名
     * @param password 密码
     *
     * @return 返回消息体, 200为登录成功, data为用户ID
     */
    fun signIn(name: String, password: String): DataMessage<SignUserView>

    /**
     * 列出用户列表
     *
     * @param idList 用户ID列表
     */
    fun listUsers(idList: List<String>): List<UserView>
}