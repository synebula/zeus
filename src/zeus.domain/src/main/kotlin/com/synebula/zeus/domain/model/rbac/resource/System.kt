package com.synebula.zeus.domain.model.rbac.resource

import com.synebula.gaea.domain.model.IAggregateRoot

class System : Resource(), IAggregateRoot<String> {
    override var avalible = true
}