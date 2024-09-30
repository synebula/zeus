package com.synebula.zeus.domain.service.cmd.rbac.resource

class PageCmd : ResourceCmd() {

    // 上级页面
    var parent = ""

    // 页面图标
    var icon: String? = null

    // 附加参数
    var params: String? = null

    // 所属系统
    var system = ""

    var avalible = true

}