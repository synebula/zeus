package com.synebula.zeus.domain.service.contr.rbac

import com.synebula.gaea.domain.service.IService
import com.synebula.gaea.domain.service.ServiceDependency
import com.synebula.zeus.domain.model.rbac.Group
import com.synebula.zeus.domain.repository.rbac.IGroupRepository

@ServiceDependency(clazz = Group::class, repo = IGroupRepository::class)
interface IGroupService : IService<String>