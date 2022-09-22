package com.synebula.zeus.query.impl

import com.synebula.gaea.data.message.DataMessage
import com.synebula.gaea.data.message.Status
import com.synebula.gaea.ext.toMd5
import com.synebula.gaea.mongodb.query.MongodbQuery
import com.synebula.gaea.mongodb.whereId
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
    MongodbQuery<UserView, String>(UserView::class.java, template), IUserQuery {

    override fun signIn(name: String, password: String): DataMessage<SignUserView> {
        val query = Query.query(
            Criteria.where("name").isEqualTo(name)
                .and("password").isEqualTo(password.toMd5())
                .and("alive").isEqualTo(true)
        )
        val user = this.template.findOne(query, this.clazz, "user")
        return if (user != null) {
            val role = this.template.findOne(whereId(user.role), RoleView::class.java, "role")
            val group = this.template.findOne(whereId(user.group), GroupView::class.java, "group")
            DataMessage(
                SignUserView(
                    user.id, user.realName ?: "",
                    user.role ?: "", role?.name ?: "",
                    user.group ?: "", group?.name ?: ""
                )
            )
        } else
            DataMessage(Status.Failure, "用户名或密码错误")
    }


    override fun listUsers(idList: List<String>): List<UserView> {
        return this.template.find(Query.query(Criteria.where("_id").`in`(idList)), this.clazz, "user")
    }
}