package com.synebula.zeus.domain.model.rbac

import com.synebula.gaea.domain.model.AggregateRoot

class Role(override var id: String? = null) : AggregateRoot<String>() {
    var name = ""
    var desc = ""
}