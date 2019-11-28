package com.myservice.logging

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Component
@Aspect
class DataAccessLogger {

    @Around("execution(* com.myservice.controller.PersonController.*(*))")
    fun logTime(pjp: ProceedingJoinPoint): Any{

        val stopWatch = StopWatch()
        stopWatch.start()
        val returnedValue = pjp.proceed()
        stopWatch.stop()

        println("${pjp.signature.declaringTypeName}.${pjp.signature.name} took ${stopWatch.lastTaskTimeMillis} ms")

        return returnedValue
    }
}