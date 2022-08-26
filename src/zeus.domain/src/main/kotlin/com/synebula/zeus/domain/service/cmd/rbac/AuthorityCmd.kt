package com.synebula.zeus.domain.service.cmd.rbac

import com.synebula.gaea.domain.service.Command
import com.synebula.zeus.env.AuthorityType
import com.synebula.zeus.env.ResourceType

class AuthorityCmd : Command() {
    var id: String? = null
    var role = ""
    var resource = ""
    var type: ResourceType? = null
    var authority = AuthorityType.Allow
}