package org.sjsu.cmpe273.wallet.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.sjsu.cmpe273.wallet.service.WalletService
import com.google.gson.JsonObject
import com.google.gson.JsonElement
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Value
import com.google.common.net.MediaType
import org.springframework.http.MediaType
import org.apache.tomcat.util.http.ContentType
import com.sun.org.apache.xml.internal.serialize.Method
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import com.google.gson.JsonParser
import java.util.Date
import org.sjsu.cmpe273.entity.IDCard
import scala.collection.Iterator
import com.google.gson.JsonArray
import org.sjsu.cmpe273.entity.User
import org.sjsu.cmpe273.entity.WebLogin
import javax.validation.Valid
import org.springframework.validation.BindingResult
import org.sjsu.cmpe273.entity.BankAccount
import org.springframework.cache.annotation.Cacheable
import org.xml.sax.helpers.DefaultHandler

@RestController
class WalletController {

  @Autowired
  var walletSvcObj: WalletService = _
  val abc = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  val expDateFormat = new java.text.SimpleDateFormat("MM-dd-yyyy")

  //1. Create User
  @RequestMapping(value = Array("/api/v1/users"), produces = Array("application/json"),
    method = Array(RequestMethod.POST))
  def createValidUsers(@Valid usr: User): String = {

    var usrObj = walletSvcObj.addUser(usr.getEmail, usr.getPassword)

    var jsonResponse: JsonObject = new JsonObject()
    jsonResponse.addProperty("id", usrObj.getUser_id)
    jsonResponse.addProperty("email", usrObj.getEmail)
    jsonResponse.addProperty("password", usrObj.getPassword)
    jsonResponse.addProperty("created_at", abc.format(usrObj.getCreatedAt) + "Z")

    return jsonResponse.toString()

  }

  //2.View User
  @RequestMapping(value = Array("/api/v1/users/{user_id}"), produces = Array("application/json"),
    method = Array(RequestMethod.GET))
  def ViewUser(@PathVariable(value = "user_id") userId: String): String = {
    var usrObj = walletSvcObj.viewUser(userId)
    var jsonResponse: JsonObject = new JsonObject()

    jsonResponse.addProperty("id", usrObj.getUser_id)
    jsonResponse.addProperty("email", usrObj.getEmail)
    jsonResponse.addProperty("password", usrObj.getPassword)
    jsonResponse.addProperty("created_at", abc.format(usrObj.getCreatedAt) + "Z")

    return jsonResponse.toString()
  }

  //3. Update User
  @RequestMapping(value = Array("/api/v1/users/{user_id}"), produces = Array("application/json"),
    method = Array(RequestMethod.PUT))
  def updtUsers(@Valid usr: User, @PathVariable(value = "user_id") userId: String): String = {

    var updtUserObj = walletSvcObj.updtUserInformation(userId, usr.getEmail, usr.getPassword)

    var jsonResponse: JsonObject = new JsonObject()
    jsonResponse.addProperty("id", updtUserObj.getUser_id)
    jsonResponse.addProperty("email", updtUserObj.getEmail)
    jsonResponse.addProperty("password", updtUserObj.getPassword)
    jsonResponse.addProperty("created_at", abc.format(updtUserObj.getCreatedAt) + "Z")

    return jsonResponse.toString()

  }

  //4. Create ID Card
  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards"), produces = Array("application/json"),
    method = Array(RequestMethod.POST))
  def createIDCards(@PathVariable(value = "user_id") usrId: String, @Valid idcard: IDCard): String = {

    //var crdName = objetJson.get("card_name").getAsString()
    //var crdNumber = objetJson.get("card_number").getAsString()
    var expDte: String = " "

    if (idcard.getExpiration_date == null) {
      expDte = null
    } else {
      expDte = idcard.getExpiration_date
    }

    var newCardObj = walletSvcObj.addIDCard(usrId, idcard.getCard_name, idcard.getCard_number, expDte)

    var jsonResponse: JsonObject = new JsonObject()
    jsonResponse.addProperty("card_id", "c-" + newCardObj.getCard_id)
    jsonResponse.addProperty("card_name", newCardObj.getCard_name)
    jsonResponse.addProperty("card_number", newCardObj.getCard_number)
    if (expDte != null) {
      jsonResponse.addProperty("expiration_date", expDateFormat.format(newCardObj.getExpDateAsDate))

    }

    return jsonResponse.toString()

  }

  //5. List All ID Cards
  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards"), produces = Array("application/json"),
    method = Array(RequestMethod.GET))
  def viewAllCard(@PathVariable(value = "user_id") usrId: String): String = {

    var idCards = walletSvcObj.viewIDCard(usrId)
    var jsnArr: JsonArray = new JsonArray()

    var iterator = idCards.iterator();
    while (iterator.hasNext()) {
      var idCard = iterator.next();
      var jsonResponse: JsonObject = new JsonObject()

      jsonResponse.addProperty("card_id", "c-" + idCard.getCard_id)
      jsonResponse.addProperty("card_name", idCard.getCard_name)
      jsonResponse.addProperty("card_number", idCard.getCard_number)

      if (idCard.getExpDateAsDate != null) {

        jsonResponse.addProperty("expiration_date", expDateFormat.format(idCard.getExpDateAsDate))
      }

      jsnArr.add(jsonResponse)
    }

    return jsnArr.toString()

  }

  //6. Delete ID Card
  @RequestMapping(value = Array("/api/v1/users/{user_id}/idcards/{card_id}"), produces = Array("application/json"),
    method = Array(RequestMethod.DELETE))
  def delCardID(@PathVariable(value = "user_id") usrId: String,
    @PathVariable(value = "card_id") cardID: String): Unit = {

    walletSvcObj.deleteIDCard(usrId, cardID)
  }

  //7. Create Web Login
  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins"), produces = Array("application/json"),
    method = Array(RequestMethod.POST))
  def createWebLogin(@PathVariable(value = "user_id") usrId: String,
    @Valid weblogin: WebLogin): String = {

    var webloginObj = walletSvcObj.addWebLogin(usrId, weblogin.getUrl, weblogin.getLogin, weblogin.getPassword)

    var jsnRsponse: JsonObject = new JsonObject
    jsnRsponse.addProperty("login_id", "l-" + webloginObj.getLogin_id)
    jsnRsponse.addProperty("url", webloginObj.getUrl)
    jsnRsponse.addProperty("login", webloginObj.getLogin)
    jsnRsponse.addProperty("password", webloginObj.getPassword)

    return jsnRsponse.toString()
  }

  //8. List All Web-site Logins
  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins"), produces = Array("application/json"),
    method = Array(RequestMethod.GET))
  def viewAllWebLogins(@PathVariable(value = "user_id") usrId: String): String = {
    var webLogins = walletSvcObj.viewAllWebLogin(usrId)
    var jsnArr = new JsonArray()

    var iterWebLogin = webLogins.iterator()
    while (iterWebLogin.hasNext()) {
      var webLogin = iterWebLogin.next();

      var jsonResponse = new JsonObject()
      jsonResponse.addProperty("login_id", "l-" + webLogin.getLogin_id)
      jsonResponse.addProperty("url", webLogin.getUrl)
      jsonResponse.addProperty("login", webLogin.getLogin)
      jsonResponse.addProperty("password", webLogin.getPassword)
      jsnArr.add(jsonResponse)
    }

    return jsnArr.toString()

  }

  //9. Delete Web Login
  @RequestMapping(value = Array("/api/v1/users/{user_id}/weblogins/{login_id}"), produces = Array("application/json"),
    method = Array(RequestMethod.DELETE))
  def delWebLogin(@PathVariable(value = "user_id") usrId: String,
    @PathVariable(value = "login_id") webLoginID: String): Unit = {

    walletSvcObj.deleteWebLogin(usrId, webLoginID)

  }

  //10. Create Bank Account
  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), produces = Array("application/json"),
    method = Array(RequestMethod.POST))
  def createBankAccount(@PathVariable(value = "user_id") usrId: String,
    @Valid bankAccount: BankAccount): String = {

    var acc_name: String = " "

    if (bankAccount.getAccount_name == null) {
      acc_name = bankAccount.getAccount_name
    } else {
      acc_name = bankAccount.getAccount_name
    }

    var bankAccObj = walletSvcObj.addBankAccount(usrId, acc_name, bankAccount.getRouting_number, bankAccount.getAccount_number)

    var jsonResponse: JsonObject = new JsonObject
    jsonResponse.addProperty("ba_id", "b-" + bankAccObj.getBa_id)

 
      jsonResponse.addProperty("account_name", bankAccObj.getAccount_name)

  

    jsonResponse.addProperty("routing_number", bankAccObj.getRouting_number)
    jsonResponse.addProperty("account_number", bankAccObj.getAccount_number)

    return jsonResponse.toString()

  }

  //11. List All Bank Accounts
  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts"), produces = Array("application/json"),
    method = Array(RequestMethod.GET))
  def viewAllBankAccounts(@PathVariable(value = "user_id") usrId: String): String = {
    var userBankAccount = walletSvcObj.viewAllBankAccounts(usrId)

    var jsonArrayForBnkAcc = new JsonArray()

    var iterbankAcc = userBankAccount.iterator()
    while (iterbankAcc.hasNext()) {
      var bnkAccObj = iterbankAcc.next();

      var jsonResponseBankAcc = new JsonObject()
      jsonResponseBankAcc.addProperty("ba_id", "b-" + bnkAccObj.getBa_id)
      jsonResponseBankAcc.addProperty("account_name", bnkAccObj.getAccount_name)
      jsonResponseBankAcc.addProperty("routing_number", bnkAccObj.getRouting_number)
      jsonResponseBankAcc.addProperty("account_number", bnkAccObj.getAccount_number)

      jsonArrayForBnkAcc.add(jsonResponseBankAcc)
    }

    return jsonArrayForBnkAcc.toString()

  }

  //12. Delete Bank Account
  @RequestMapping(value = Array("/api/v1/users/{user_id}/bankaccounts/{ba_id}"), produces = Array("application/json"),
    method = Array(RequestMethod.DELETE))
  def delBankAcc(@PathVariable(value = "user_id") usrId: String,
    @PathVariable(value = "ba_id") bankAccId: String): Unit = {

    walletSvcObj.deleteBankAccount(usrId, bankAccId)
  }

}