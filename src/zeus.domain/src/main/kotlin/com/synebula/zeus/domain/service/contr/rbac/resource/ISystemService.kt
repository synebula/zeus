package com.synebula.zeus.domain.service.contr.rbac.resource

import com.synebula.gaea.domain.service.IService
import com.synebula.gaea.domain.service.ServiceDependency
import com.synebula.zeus.domain.model.rbac.resource.System
import com.synebula.zeus.domain.repository.rbac.resource.ISystemRepository

@ServiceDependency(clazz = System::class, repo = ISystemRepository::class)
interface ISystemService : IService<String>