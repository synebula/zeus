package com.synebula.zeus.domain.model.rbac

import com.synebula.gaea.domain.model.AggregateRoot

class Account(override var id: String? = null) : AggregateRoot<String>() {
    var name: String = ""
    var password: String = ""
}