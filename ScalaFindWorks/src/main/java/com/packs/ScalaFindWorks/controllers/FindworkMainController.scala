package com.packs.ScalaFindWorks.controllers

import com.packs.ScalaFindWorks.services.ProducerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/kafka"))
class FindworkMainController{

  @Autowired
  val producerService: ProducerService=null

  @GetMapping(Array("/start"))
  def sendMessageToKafkaTopic(): String = {
      producerService.startReadingThread();
    "started"
  }

}