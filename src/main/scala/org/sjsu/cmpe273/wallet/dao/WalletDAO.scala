package org.sjsu.cmpe273.wallet.dao

import org.sjsu.cmpe273.entity.User;

object WalletDAO {

  val userObj: User = new User();
  var userMap = scala.collection.mutable.Map[Int, User]();

  def createUser(userObj: User): Unit = {
    val userId = userObj.getUser_id;
    userMap.put(userId, userObj);
  }

  def findUser(usrId: Int): User = {
    if (userMap.get(usrId).isEmpty) {
      return null
    } else {
      return userMap.get(usrId).get
    }
  }

  def updtUser(usrObj: User): Unit = {
    userMap.put(userObj.getUser_id, userObj)
  }

  def delUser(userId: Int): Unit = {
    userMap.remove(userId)
  }

}

