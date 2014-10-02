package org.sjsu.cmpe273.entity

import org.hibernate.validator.constraints.NotEmpty



class WebLogin {
  
  var login_id: Int=_;
  var url:String=_;
  var login: String=_;
  var password: String=_;
  
  
  def getLogin_id(  ) : Int = {
      return login_id;
   }
   
   def setLogin_id(login_id :Int  ) : Unit = {
      this.login_id = login_id;
   }
   
   @NotEmpty
   def getUrl(  ) : String = {
      return url;
   }
   
   def setUrl(url :String  ) : Unit = {
      this.url = url;
   }
   
   @NotEmpty
   def getLogin(  ) : String = {
      return login;
   }
   
   def setLogin(login :String  ) : Unit = {
      this.login = login;
   }
   
   @NotEmpty
   def getPassword(  ) : String = {
      return password;
   }
   
   def setPassword(password :String  ) : Unit = {
      this.password = password;
   }

    
  

}