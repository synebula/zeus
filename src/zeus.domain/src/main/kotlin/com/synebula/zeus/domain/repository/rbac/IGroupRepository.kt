package com.synebula.zeus.domain.repository.rbac

import com.synebula.gaea.domain.repository.IRepository
import com.synebula.zeus.domain.model.rbac.Group

interface IGroupRepository : IRepository<Group, String> {
}