package org.sjsu.cmpe273.wallet.service

import java.util.ArrayList
import java.util.Date
import java.util.List

import org.sjsu.cmpe273.entity.BankAccount
import org.sjsu.cmpe273.entity.IDCard
import org.sjsu.cmpe273.entity.User
import org.sjsu.cmpe273.entity.WebLogin
import org.sjsu.cmpe273.wallet.dao.WalletDAO
import org.springframework.stereotype.Component

@Component
class WalletService {

  var date: String = _;
  var currSeqNbr: Int = 123450;
  val expDateFormat = new java.text.SimpleDateFormat("MM-dd-yyyy")

  //Used to generate a random number for UserId, CardId
  def getNextNumber() : Int = {
    currSeqNbr = currSeqNbr +1;
    return currSeqNbr
  }
  
  //below are conversion of id's from string to Int and vice versa.
  def convertUserIdToString(usrIdInInt: Int): String = {
    return ("u-" + usrIdInInt.toString())
  }
  
   def convertUserIdToInt(usrIdInString: String): Int = {
    return (usrIdInString.substring(2)).toInt
 }
  
    def convertCardIdToString(cardIdInInt: Int): String = {
    return ("c-" + cardIdInInt.toString())
  }
    
  def convertCardIdToInt(cardIdInString: String): Int = {
    return (cardIdInString.substring(2)).toInt
  }
  
  def convertWebLoginIdToString(loginIdInInt: Int): String = {
    return ("l-" + loginIdInInt.toString())
  }
  
  def convertWebLoginIdToInt(loginIdInString: String): Int = {
    return (loginIdInString.substring(2)).toInt
 }
  
  def convertBankAccIdToString(BankIdInInt: Int): String = {
    return ("b-" + BankIdInInt.toString())
  }
  
  def convertBankAccIdToInt(BankIdInString: String): Int = {
    return (BankIdInString.substring(2)).toInt
 }
  
 
  
  //formating the Date-Time for created at to "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
  def generateCreatedAt(): Date = {

    val abc = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ" + "Z")
    var date = abc.format(new Date());

    
    return abc.parse(date)
  }
  
  
  //1. Create User
  def addUser(email: String, password: String): User = {
    val createUser: User = new User
    createUser.setUser_id(getNextNumber())
    createUser.setEmail(email)
    createUser.setPassword(password)
    createUser.setCreatedAt(generateCreatedAt())
    WalletDAO.createUser(createUser)
    
    return createUser
  }
  
  //2. View User
  def viewUser(usrId: String): User = {
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId);
    val obj=WalletDAO.findUser(usrIdAftrConversion)
    
    return obj
  }
  
  //3. Update User
  def updtUserInformation(usrId: String, email: String, passwrd: String): User = {
    var usr:User=new User
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId);
    usr = WalletDAO.findUser(usrIdAftrConversion);
    usr.setEmail(email)
    usr.setPassword(passwrd)
    WalletDAO.updtUser(usr)
    return usr;
  }
  
  //4. Create ID Card
  def addIDCard(usrId: String, crdName: String, crdNmbr: String, expDte: String):IDCard = {
    val createIdCard: IDCard = new IDCard
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId);
    val usrObj: User = WalletDAO.findUser(usrIdAftrConversion)
    
    //createUser.idCardMap(usrIdAftrConversion)
    createIdCard.setCard_id(getNextNumber)
    createIdCard.setCard_name(crdName)
    createIdCard.setCard_number(crdNmbr)
    if (expDte ==null){
      createIdCard.setExpiration_date(null)
    }else {
      createIdCard.setExpDateAsDate(expDateFormat.parse(expDte)) 
    }
    usrObj.getIdCardMap.put( createIdCard.getCard_id, createIdCard)
    
    return createIdCard
   }
  
  //5. List All ID Cards
  def viewIDCard(usrId: String): List[IDCard] = {
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    var usrObj:User= WalletDAO.findUser(usrIdAftrConversion)
    var idMap = usrObj.getIdCardMap
    var setOfKeys = idMap.keySet
    var listOfCards= new ArrayList[IDCard]()
    setOfKeys.foreach((cardIdKey) =>
       listOfCards.add(idMap.get(cardIdKey).get)
    )
    return listOfCards
  }
  

  //6. Delete ID Card
  def deleteIDCard(usrId:String, cardId:String) ={
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    val cardIdAftrConversion: Int = convertCardIdToInt(cardId)
    var usrObj:User= WalletDAO.findUser(usrIdAftrConversion)
    
    usrObj.idCardMap.remove(cardIdAftrConversion)
  }
  
  //7. Create Web Login
  def addWebLogin(usrId:String, url:String, login:String, loginPasswrd:String): WebLogin={
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    val createWebLogin: WebLogin= new WebLogin
    val usrObj: User= WalletDAO.findUser(usrIdAftrConversion)
    
    createWebLogin.setLogin_id(getNextNumber)
    createWebLogin.setUrl(url) 
    createWebLogin.setLogin(login)
    createWebLogin.setPassword(loginPasswrd)
    
    usrObj.getWebLoginMap.put(createWebLogin.getLogin_id,createWebLogin )
    
    return createWebLogin
 }
  
  //8. List All Web-site Logins
  def viewAllWebLogin(usrId: String): List[WebLogin] = {
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    var usrObj:User= WalletDAO.findUser(usrIdAftrConversion)
    
    var idMap = usrObj.getWebLoginMap
    var setOfKeys = idMap.keySet
    var listOfWebLogin= new ArrayList[WebLogin]()
    setOfKeys.foreach((webLoginIdKey) =>
       listOfWebLogin.add(idMap.get(webLoginIdKey).get)
    )
    return listOfWebLogin
  }
  
  //9. Delete Web Login
  def deleteWebLogin(usrId:String, loginId:String)={
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    val webLoginIdAftrConversion: Int = convertWebLoginIdToInt(loginId)
    
    var usrObj:User= WalletDAO.findUser(usrIdAftrConversion)
    usrObj.webLoginMap.remove(webLoginIdAftrConversion)
    
  }
  
  //10. Create Bank Account
  def addBankAccount(usrId:String, accName:String, routNmber:String, accNmber:String ): BankAccount={
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    val createBankAccId: BankAccount= new BankAccount
    val usrObj: User= WalletDAO.findUser(usrIdAftrConversion)
    
    createBankAccId.setBa_id(getNextNumber)
    
    if (accName ==null){
      createBankAccId.setAccount_name(null)
    }else {
      createBankAccId.setAccount_name(accName) 
    }
    
    createBankAccId.setRouting_number(routNmber)
    createBankAccId.setAccount_number(accNmber)
    
    usrObj.getBankAccountMap.put(createBankAccId.getBa_id,createBankAccId )
    
    return createBankAccId
  }
  
  //11. List All Bank Accounts
  def viewAllBankAccounts(usrId: String): List[BankAccount] = {
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    var usrObj:User= WalletDAO.findUser(usrIdAftrConversion)
    
    var idMap = usrObj.getBankAccountMap()
    var setOfKeys = idMap.keySet
    var listOfBankAcc= new ArrayList[BankAccount]()
    setOfKeys.foreach((bnkAccIdKey) =>
       listOfBankAcc.add(idMap.get(bnkAccIdKey).get)
    )
    return listOfBankAcc
  }
  
 //12. Delete Bank Account
  def deleteBankAccount(usrId: String, bankAccId:String)= {
    val usrIdAftrConversion: Int = convertUserIdToInt(usrId)
    val bankAccIdAftrConversion: Int = convertWebLoginIdToInt(bankAccId)
    
    var usrObj:User= WalletDAO.findUser(usrIdAftrConversion)
    usrObj.bankAccMap.remove(bankAccIdAftrConversion)
    
  }
  
}
	
	


