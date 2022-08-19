package com.synebula.zeus.app.config

import com.google.gson.Gson
import com.synebula.gaea.data.serialization.json.IJsonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@Configuration
@ComponentScan(basePackages = ["com.synebula.gaea.app.component"])
class ZeusBeans {
    @Bean
    fun gson(): Gson = Gson()

    @Bean
    fun serializer(gson: Gson): IJsonSerializer {
        return object : IJsonSerializer {
            override fun <S> serialize(src: S): String {
                return gson.toJson(src)
            }
        }
    }
}