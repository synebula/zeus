package com.synebula.zeus.app.component

import com.synebula.gaea.spring.aop.AppAspect
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class ZeusAspect : AppAspect() {

    /**
     * 切片执行所有继承[com.synebula.gaea.app.controller.IApplication]接口的类
     */
    @Pointcut("target(com.synebula.gaea.app.controller.IApplication)")
    override fun func() {
    }
}