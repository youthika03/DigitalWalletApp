package org.sjsu.cmpe273.entity

import org.hibernate.validator.constraints.NotEmpty

class BankAccount {

  var ba_id :Int=_;
  var account_name: String=_;
  var routing_number: String =_;
  var account_number: String=_;
  
  
  def getBa_id(  ) : Int = {
      return ba_id;
   }
   
   def setBa_id(ba_id :Int  ) : Unit = {
      this.ba_id = ba_id;
   }
  
   
   def getAccount_name(  ) : String = {
      return account_name;
   }
   
   def setAccount_name(account_name :String  ) : Unit = {
      this.account_name = account_name;
   }
   
   @NotEmpty
   def getRouting_number(  ) : String = {
      return routing_number;
   }
   
   def setRouting_number(routing_number :String  ) : Unit = {
      this.routing_number = routing_number;
   }
   
   @NotEmpty
   def getAccount_number(  ) : String = {
      return account_number;
   }
   
   def setAccount_number(account_number :String  ) : Unit = {
      this.account_number = account_number;
   }
   
  
}