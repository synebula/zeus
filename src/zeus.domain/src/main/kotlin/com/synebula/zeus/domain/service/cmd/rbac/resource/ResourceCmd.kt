package com.synebula.zeus.domain.service.cmd.rbac.resource


abstract class ResourceCmd(var id: String? = null) {
    var name = ""
    var signature = ""
    val uri: String? = null
    var order = 0
    var desc = ""
}