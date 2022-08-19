package com.synebula.zeus.domain.service.contr.rbac.resource

import com.synebula.gaea.domain.service.IService
import com.synebula.gaea.domain.service.ServiceDependency
import com.synebula.zeus.domain.model.rbac.resource.Interface
import com.synebula.zeus.domain.repository.rbac.resource.IInterfaceRepository

@ServiceDependency(clazz = Interface::class, repo = IInterfaceRepository::class)
interface IInterfaceService : IService<String>