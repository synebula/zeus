package com.synebula.zeus.domain.model.rbac.resource

import com.synebula.gaea.domain.model.Entity

abstract class Resource(override var id: String? = null) : Entity<String>() {
    var name = ""
    var signature = ""
    val uri: String? = null
    var order = 0
    var desc = ""
}