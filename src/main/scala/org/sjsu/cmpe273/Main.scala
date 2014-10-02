package org.sjsu.cmpe273

import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RestController
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.RequestMapping
import java.util.Calendar
import java.text.SimpleDateFormat
import org.springframework.context.annotation.Bean
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.web.bind.annotation.PathVariable

object Main {
  def main(args: Array[String]) {
    val configuration: Array[Object] = Array(classOf[Config])
    SpringApplication.run(configuration, args)
  }
}

@EnableAutoConfiguration
@ComponentScan
class Config {
  
  @Bean
  def filterRegistrationBean(): FilterRegistrationBean = {
    var registrationBean: FilterRegistrationBean = new FilterRegistrationBean()
    
    registrationBean.setFilter(new org.springframework.web.filter.HttpPutFormContentFilter());
    registrationBean.setOrder(1);
    registrationBean;
  }


}

