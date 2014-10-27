package org.sjsu.cmpe273

import com.mongodb.Mongo
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.context.annotation.Configuration
import com.mongodb.ServerAddress
import com.mongodb.MongoURI

@Configuration
class MongoConfig extends AbstractMongoConfiguration {
  
  def getDatabaseName:String = "digitalwallet"
 
  def mongo:Mongo = new Mongo( new MongoURI("mongodb://mongoDB:mongoDB@ds049130.mongolab.com:49130/digitalwallet"))
 

}