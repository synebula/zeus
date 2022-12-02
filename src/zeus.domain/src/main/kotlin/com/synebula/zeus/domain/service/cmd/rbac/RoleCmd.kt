package com.synebula.zeus.domain.service.cmd.rbac

import com.synebula.gaea.data.permission.PermissionType
import com.synebula.gaea.domain.service.Command

class RoleCmd : Command() {
    var id: String? = null
    var name = ""
    var desc = ""
    var permissionType = PermissionType.Minimum
}