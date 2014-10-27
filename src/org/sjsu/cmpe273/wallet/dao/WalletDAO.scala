package org.sjsu.cmpe273.wallet.dao

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.sjsu.cmpe273.entity.User
import com.google.gson.Gson
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.sjsu.cmpe273.entity.User
import org.sjsu.cmpe273.entity.User

@Component
class WalletDAO {

  @Autowired
  var mongoOperations: MongoOperations = _;
  
  val userCollection:String="userCollection"
  val id:String="_id"

  val userObj: User = new User();
  var userMap = scala.collection.mutable.Map[String, User]();

  def upsertUser(userObj: User): Unit = {
    val userId = userObj.getUser_id;
    val userJson = new Gson().toJson(userObj);
    mongoOperations.save(userObj, userCollection)
    userMap.put(userId, userObj)
  }

  def findUser(usrId: String): User = {

    var query: Query = new Query();
    query.addCriteria(Criteria.where(id).is(usrId))
    mongoOperations.findOne(query, classOf[User],userCollection );

  }

  /*def updtUser(usrObj: User): Unit = {
   
    userMap.put(userObj.getUser_id, userObj)
  }*/

  def delUser(userId: String): Unit = {
    var criteria: Criteria = new Criteria(id);
    criteria.in(userId);
    var query: Query = new Query(criteria);
    mongoOperations.remove(query,userCollection)
  }

}

