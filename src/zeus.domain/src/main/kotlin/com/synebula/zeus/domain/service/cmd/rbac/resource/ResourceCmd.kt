package com.synebula.zeus.domain.service.cmd.rbac.resource

import com.synebula.gaea.domain.service.Command


abstract class ResourceCmd(var id: String? = null) : Command() {
    var name = ""
    var signature = ""
    val uri: String? = null
    var order = 0
    var desc = ""
}