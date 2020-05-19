package com.synebula.zeus.domain.model.rbac

import com.synebula.gaea.domain.model.AggregateRoot

class User(override var id: String? = null) : AggregateRoot<String>() {
    var name: String = ""
    var password: String = ""
    var realName: String? = null
    var phone: String? = null
}