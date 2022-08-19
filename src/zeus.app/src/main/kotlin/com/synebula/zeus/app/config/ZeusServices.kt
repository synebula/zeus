package com.synebula.zeus.app.config

import com.synebula.gaea.app.autoconfig.service.ServiceScan
import com.synebula.gaea.mongodb.autoconfig.MongodbRepoScan
import org.springframework.stereotype.Component

@Component
@ServiceScan(basePackages = ["com.synebula.zeus.domain.service"])
@MongodbRepoScan(basePackages = ["com.synebula.zeus.domain.repository", "com.synebula.zeus.repository", "com.synebula.zeus.query"])
class ZeusServices