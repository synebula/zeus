package com.synebula.zeus.query.view.resource

import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType

class PermissionView() {
    var id: String? = null
    var role = ""
    var resource = ""
    var type: ResourceType? = null
    var authority = PermissionType.Allow
}