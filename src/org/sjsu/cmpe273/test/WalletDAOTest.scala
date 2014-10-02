package org.sjsu.cmpe273.test

import org.sjsu.cmpe273.wallet.dao.WalletDAO
import org.sjsu.cmpe273.entity.User
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Formatter.DateTime
import java.util.Date
import org.sjsu.cmpe273.wallet.service.WalletService

object WalletDAOTest {
  def main(args: Array[String]) {
    
    
    var walletSvc:WalletService = new WalletService
    
    walletSvc.addUser("abc@cde.com","xyz" )
    
    walletSvc.addUser("ert@cde.com","asdf" )
    
    walletSvc.viewUser("u-123451")
    
    walletSvc.viewUser("u-123452")
    var date=new java.text.SimpleDateFormat("MM-DD-YYYY")
    var newDate = date.parse("date")
    
    walletSvc.addIDCard("u-123451", "Card1", "11223344", "12/31/2015")
    
    
    
    
	  
	}
}
     