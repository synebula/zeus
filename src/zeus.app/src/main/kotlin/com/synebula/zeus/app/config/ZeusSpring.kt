package com.synebula.zeus.app.config

import com.synebula.gaea.app.component.AllTypeFilter
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.FilterType
import org.springframework.stereotype.Component

@Component
@ComponentScan(
    basePackages = [
        "com.synebula.gaea.app.component",
        "com.synebula.zeus.domain.service.impl",
        "com.synebula.zeus.query.impl"
    ],
    includeFilters = [Filter(type = FilterType.CUSTOM, classes = [AllTypeFilter::class])]
)
class ZeusSpring