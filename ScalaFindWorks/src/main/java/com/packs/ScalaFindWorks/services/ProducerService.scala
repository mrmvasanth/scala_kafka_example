package com.packs.ScalaFindWorks.services

import com.packs.ScalaFindWorks.constants.ConstantsUtil
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

object ProducerService {

  private val logger: Logger =    LoggerFactory.getLogger(classOf[ProducerService])

}

@Service
class ProducerService {

  @Autowired
  private var kafkaTemplate: KafkaTemplate[String, String] = _

  @Autowired
  var fileReaderService: FileReaderService = _

  def startReadingThread(): Unit = {
    while (true) try {
      val jobsCount: Int = fileReaderService.getJobsCount
      sendMessage(java.lang.Integer.toString(jobsCount))
      Thread.sleep(20000)
    } catch {
      case ex: InterruptedException => {}

    }
  }

  def sendMessage(jobsCount: String): Unit = {
    this.kafkaTemplate.send(ConstantsUtil.TARGET_TOPIC, jobsCount)
  }

}
