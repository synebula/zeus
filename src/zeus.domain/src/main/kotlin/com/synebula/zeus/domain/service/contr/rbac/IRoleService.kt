package com.synebula.zeus.domain.service.contr.rbac

import com.synebula.gaea.domain.service.IService
import com.synebula.gaea.domain.service.ServiceDependency
import com.synebula.zeus.domain.model.rbac.Role
import com.synebula.zeus.domain.repository.rbac.IRoleRepository

@ServiceDependency(clazz = Role::class, repo = IRoleRepository::class)
interface IRoleService : IService<String>