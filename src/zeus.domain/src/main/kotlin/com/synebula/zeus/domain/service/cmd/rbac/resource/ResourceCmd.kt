package com.synebula.zeus.domain.service.cmd.rbac.resource

import com.synebula.gaea.domain.service.Command


abstract class ResourceCmd(var id: String? = null) : Command() {
    var name = ""
    //资源定位符，唯一标识。可以是uil，也可以是别名
    var uri = ""
    var order = 0
    var desc: String? = null
}