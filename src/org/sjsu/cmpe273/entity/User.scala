package org.sjsu.cmpe273.entity

import java.util.Date
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.Email
import org.springframework.data.annotation.Id
import java.util.HashMap

class User {

  @Id
  var user_id: String = _;

  @NotEmpty
  @Email
  var email: String = _;

  @NotEmpty
  var password: String = _;

  var name: String = _;
  var created_at: Date = _;
  var updated_at: Date = _;

  var idCardMap = new HashMap[Int, IDCard]();
  var bankAccMap = new HashMap[Int, BankAccount]();
  var webLoginMap = new HashMap[Int, WebLogin]();

  def getUser_id(): String = {
    return user_id;
  }

  def setUser_id(user_id: String): Unit = {
    this.user_id = user_id;
  }

  def getEmail(): String = {
    return email;
  }

  def setEmail(email: String): Unit = {
    this.email = email;
  }

  def getPassword(): String = {
    return password;
  }

  def setPassword(password: String): Unit = {
    this.password = password;
  }

  def getName(): String = {
    return name;
  }

  def setName(name: String): Unit = {
    this.name = name;
  }

  def getCreatedAt(): Date = {
    return created_at;
  }

  def setCreatedAt(created_at: Date): Unit = {
    this.created_at = created_at;
  }

  def getUpdatedAt(): Date = {
    return updated_at;
  }

  def setUpdatedAt(updated_at: Date): Unit = {
    this.updated_at = updated_at;
  }

  def getIdCardMap(): HashMap[Int, IDCard] = {
    return idCardMap;
  }

  def setIDCardMap(idCardMap: HashMap[Int, IDCard]): Unit = {
    this.idCardMap = idCardMap;
  }

  def getBankAccountMap(): HashMap[Int, BankAccount] = {
    return bankAccMap;
  }

  def setBankAccountMap(bankAccMap: HashMap[Int, BankAccount]): Unit = {
    this.bankAccMap = bankAccMap;
  }

  def getWebLoginMap(): HashMap[Int, WebLogin] = {
    return webLoginMap;
  }

  def setWebLoginMap(webLoginMap: HashMap[Int, WebLogin]): Unit = {
    this.webLoginMap = webLoginMap;
  }

}
