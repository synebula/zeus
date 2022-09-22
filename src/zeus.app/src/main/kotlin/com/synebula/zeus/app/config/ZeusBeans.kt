package com.synebula.zeus.app.config

import com.google.gson.Gson
import com.synebula.gaea.app.component.security.WebSecurity
import com.synebula.gaea.data.serialization.json.IJsonSerializer
import com.synebula.gaea.domain.repository.IRepositoryFactory
import com.synebula.gaea.mongodb.query.MongodbQueryFactory
import com.synebula.gaea.mongodb.repository.MongodbRepositoryFactory
import com.synebula.gaea.query.IQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
@ComponentScan(
    basePackages = ["com.synebula.gaea.app.component"],
    excludeFilters = [Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [WebSecurity::class])]
)
class ZeusBeans {

    @Bean
    fun repoFactory(template: MongoTemplate): IRepositoryFactory {
        return MongodbRepositoryFactory(template)
    }

    @Bean
    fun queryFactory(template: MongoTemplate): IQueryFactory {
        return MongodbQueryFactory(template)
    }

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