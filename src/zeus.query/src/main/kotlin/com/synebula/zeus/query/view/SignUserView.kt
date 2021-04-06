package com.synebula.zeus.query.view

class SignUserView(
    /**
     * 用户id
     */
    var uid: String = "",

    /**
     * 用户名称
     */
    var uname: String = "",

    /**
     * 角色id
     */
    var rid: String = "",

    /**
     * 角色名称
     */
    var rname: String = "",

    /**
     * 组id
     */
    var gid: String = "",

    /**
     * 组名称
     */
    var gname: String = "",
    var remember: Boolean = false
)