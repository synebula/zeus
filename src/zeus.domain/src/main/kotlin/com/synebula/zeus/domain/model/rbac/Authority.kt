package com.synebula.zeus.domain.model.rbac

import com.synebula.gaea.data.permission.AuthorityType
import com.synebula.gaea.domain.model.AggregateRoot
import com.synebula.zeus.env.ResourceType

class Authority(override var id: String? = null) : AggregateRoot<String>() {
    var role = ""
    var resource = ""
    var type: ResourceType? = null
    var authority = AuthorityType.Allow

    constructor(role: String, resource: String, type: ResourceType?, authority: AuthorityType) : this() {
        this.role = role
        this.resource = resource
        this.type = type
        this.authority = authority
    }
}