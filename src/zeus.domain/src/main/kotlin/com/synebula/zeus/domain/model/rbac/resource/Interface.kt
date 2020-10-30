package com.synebula.zeus.domain.model.rbac.resource

import com.synebula.gaea.domain.model.IAggregateRoot

class Interface : Resource(), IAggregateRoot<String> {
    override var alive = true
}