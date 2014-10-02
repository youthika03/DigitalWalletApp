package org.sjsu.cmpe273.entity

import java.util.Date
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.Email

class User {
  

  var user_id :Int=_;
  
  @NotEmpty
  @Email
  var email : String=_;
  
  @NotEmpty
  var password : String=_;
  
  var name : String=_;
  var created_at : Date=_ ;
  var updated_at : Date=_;
 
  var idCardMap = scala.collection.mutable.Map[Int, IDCard]();
  var bankAccMap = scala.collection.mutable.Map[Int, BankAccount]();
  var webLoginMap = scala.collection.mutable.Map[Int, WebLogin]();

 
   def getUser_id(  ) : Int = {
      return user_id;
   }
   
   def setUser_id(user_id :Int  ) : Unit = {
      this.user_id = user_id;
   }
   
   def getEmail(  ) : String = {
      return email;
   }
   
   def setEmail(email:String  ) : Unit = {
      this.email = email;
   }
   
   def getPassword(  ) : String = {
      return password;
   }
   
   def setPassword(password :String  ) : Unit = {
      this.password = password;
   }

   def getName(  ) : String = {
      return name;
   }
   
   def setName(name :String  ) : Unit = {
      this.name = name;
   }
   
   def getCreatedAt(  ) : Date = {
      return created_at;
   }
   
   def setCreatedAt(created_at :Date  ) : Unit = {
      this.created_at = created_at;
   }
   
   def getUpdatedAt(  ) : Date = {
      return updated_at;
   }
   
   def setUpdatedAt(updated_at :Date  ) : Unit = {
      this.updated_at = updated_at;
   }
  
    def getIdCardMap(  ) : scala.collection.mutable.Map[Int,IDCard] = {
      return idCardMap;
   }
    
    def setIDCardMap(idCardMap: scala.collection.mutable.Map[Int,IDCard]):Unit={
      this.idCardMap = idCardMap;
    }
    
    def getBankAccountMap(  ) : scala.collection.mutable.Map[Int,BankAccount] = {
      return bankAccMap;
   }
    
    def setBankAccountMap(bankAccMap: scala.collection.mutable.Map[Int,BankAccount]):Unit={
      this.bankAccMap = bankAccMap;
    }
    
     def getWebLoginMap(  ) : scala.collection.mutable.Map[Int,WebLogin] = {
      return webLoginMap;
   }
     
    def setWebLoginMap(webLoginMap: scala.collection.mutable.Map[Int,WebLogin]):Unit={
      this.webLoginMap = webLoginMap;
    }
    
    
  
}
