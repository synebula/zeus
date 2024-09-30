package com.synebula.zeus.domain.model.rbac.resource

import com.synebula.gaea.domain.model.IAggregateRoot

class Page : Resource(), IAggregateRoot<String> {

    // 上级页面
    var parent = ""

    // 页面图标
    var icon: String? = null

    // 附加参数
    var params: String? = null

    // 所属系统
    var system = ""

    override var avalible = true

}