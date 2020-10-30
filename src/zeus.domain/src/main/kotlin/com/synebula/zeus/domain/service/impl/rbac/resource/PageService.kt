package com.synebula.zeus.domain.service.impl.rbac.resource

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.resource.Page
import com.synebula.zeus.domain.service.ctr.rbac.resource.IPageService

class PageService(
        repository: IRepository,
        converter: IObjectConverter, logger: ILogger
) : Service<Page, String>(Page::class.java, repository, converter, logger), IPageService