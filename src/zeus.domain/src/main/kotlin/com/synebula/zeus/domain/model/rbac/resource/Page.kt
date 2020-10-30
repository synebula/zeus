package com.synebula.zeus.domain.model.rbac.resource

import com.synebula.gaea.domain.model.IAggregateRoot

class Page : Resource(), IAggregateRoot<String> {

    // 上级页面
    val supPage = 0

    // 页面图标
    val icon: String? = null

    // 附加参数
    val params: String? = null

    // 所属系统
    val system = 0

    override var alive = true

}