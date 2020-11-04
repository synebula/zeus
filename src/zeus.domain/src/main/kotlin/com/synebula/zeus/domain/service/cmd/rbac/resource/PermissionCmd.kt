package com.synebula.zeus.domain.service.cmd.rbac.resource

import com.synebula.gaea.domain.service.Command
import com.synebula.zeus.env.PermissionType
import com.synebula.zeus.env.ResourceType

class PermissionCmd : Command() {
    var id: String? = null
    var role = ""
    var resource = ""
    var type: ResourceType? = null
    var authority = PermissionType.Allow
}