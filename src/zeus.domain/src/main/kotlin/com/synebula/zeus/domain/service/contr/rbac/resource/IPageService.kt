package com.synebula.zeus.domain.service.contr.rbac.resource

import com.synebula.gaea.domain.service.IService
import com.synebula.gaea.domain.service.ServiceDependency
import com.synebula.zeus.domain.model.rbac.resource.Page
import com.synebula.zeus.domain.repository.rbac.resource.IPageRepository

@ServiceDependency(clazz = Page::class, repo = IPageRepository::class)
interface IPageService : IService<String>