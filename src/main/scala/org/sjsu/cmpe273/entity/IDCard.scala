

package org.sjsu.cmpe273.entity

import java.util.Date
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.Pattern

class IDCard {

  var card_id: Int = _;

  var card_name: String = _;

  var card_number: String = _;

  var expiration_date: String = _;

  var expDateAsDate: Date = _;

  def getCard_id(): Int = {
    return card_id;
  }

  def setCard_id(card_id: Int): Unit = {
    this.card_id = card_id;
  }

  @NotEmpty
  def getCard_name(): String = {
    return card_name;
  }

  def setCard_name(cardName: String): Unit = {
    this.card_name = cardName;
  }

  @NotEmpty
  def getCard_number(): String = {
    return card_number;
  }
  def setCard_number(cardNumber: String): Unit = {
    this.card_number = cardNumber;
  }

  def getExpDateAsDate(): Date = {
    return expDateAsDate;
  }

  def setExpDateAsDate(expDateAsDate: Date): Unit = {
    this.expDateAsDate = expDateAsDate;
  }

  def getExpiration_date(): String = {
    return expiration_date;
  }
  def setExpiration_date(expDateinString: String): Unit = {
    this.expiration_date = expDateinString;
  }

}