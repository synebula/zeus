package com.synebula.zeus.app.component

import com.synebula.gaea.app.component.aop.AppAspect
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class ZeusAspect : AppAspect() {

    @Pointcut("target(com.synebula.gaea.app.IApplication)")
    override fun func() {
    }
}