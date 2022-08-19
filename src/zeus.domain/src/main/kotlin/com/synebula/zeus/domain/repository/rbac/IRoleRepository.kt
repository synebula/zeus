package com.synebula.zeus.domain.repository.rbac

import com.synebula.gaea.domain.repository.IRepository
import com.synebula.zeus.domain.model.rbac.Role

interface IRoleRepository : IRepository<Role, String> {
}