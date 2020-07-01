package com.synebula.zeus.query.impl

import com.synebula.gaea.data.message.Message
import com.synebula.gaea.data.message.Status
import com.synebula.gaea.extension.toMd5
import com.synebula.gaea.mongo.query.MongoGenericQuery
import com.synebula.gaea.mongo.whereId
import com.synebula.zeus.query.contr.IUserQuery
import com.synebula.zeus.query.view.GroupView
import com.synebula.zeus.query.view.RoleView
import com.synebula.zeus.query.view.SignUserView
import com.synebula.zeus.query.view.UserView
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo

class UserQuery(template: MongoTemplate) :
        MongoGenericQuery<UserView>("user", UserView::class.java, template), IUserQuery {

    override fun signIn(name: String, password: String): Message<SignUserView> {
        this.check()
        val query = Query.query(
                Criteria.where("name").isEqualTo(name)
                        .and("password").isEqualTo(password.toMd5())
                        .and("alive").isEqualTo(true)
        )
        val user = this.template.findOne(query, this.clazz!!, "user")
        return if (user != null) {
            val role = this.template.findOne(whereId(user.role), RoleView::class.java, "role")
            val group = this.template.findOne(whereId(user.group), GroupView::class.java, "group")
            Message(SignUserView(user.id, user.name, user.realName ?: "",
                    user.role ?: "", role?.name ?: "",
                    user.group ?: "", group?.name ?: ""))
        } else
            Message(Status.Failure, "用户名或密码错误")
    }
}