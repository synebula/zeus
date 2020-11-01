package com.synebula.zeus.domain.model.rbac.resource

import com.synebula.gaea.domain.model.AggregateRoot
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType

class Permission(override var id: String? = null) : AggregateRoot<String>() {
    var role = ""
    var resource = ""
    var type: ResourceType? = null
    var authorization = PermissionType.Allow
}