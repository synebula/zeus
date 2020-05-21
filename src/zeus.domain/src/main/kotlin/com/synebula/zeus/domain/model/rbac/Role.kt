package com.synebula.zeus.domain.model.rbac

import com.synebula.gaea.domain.model.AggregateRoot

class Role : AggregateRoot<String>() {
    override var id: String? = null
    var name = ""
}