package com.synebula.zeus.domain.model.rbac.resource

import com.synebula.gaea.domain.model.Entity

abstract class Resource(override var id: String? = null) : Entity<String>() {
    var name = ""
    //资源定位符，唯一标识。可以是uil，也可以是别名
    var uri = ""
    var order = 0
    var desc: String? = null
}