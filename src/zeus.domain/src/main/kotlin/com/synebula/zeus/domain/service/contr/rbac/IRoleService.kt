package com.synebula.zeus.domain.service.contr.rbac

import com.synebula.gaea.domain.service.Domain
import com.synebula.gaea.domain.service.IService
import com.synebula.zeus.domain.model.rbac.Role

@Domain(clazz = Role::class)
interface IRoleService : IService<String>