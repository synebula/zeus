package com.synebula.zeus.domain.service.contr.rbac.resource

import com.synebula.gaea.domain.service.Domain
import com.synebula.gaea.domain.service.IService
import com.synebula.zeus.domain.model.rbac.resource.Interface

@Domain(clazz = Interface::class)
interface IInterfaceService : IService<String>