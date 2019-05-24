package com.packs.ScalaFindWorks.services

import java.sql.Timestamp
import java.util.{Date, UUID}

import com.packs.ScalaFindWorks.constants.ConstantsUtil
import com.packs.ScalaFindWorks.model.JobDetails
import com.packs.ScalaFindWorks.repository.CassandraRepo
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
class ConsumerService {

  @Autowired
  var cassandraRepo: CassandraRepo = _

  private val logger: Logger =
    LoggerFactory.getLogger(classOf[ConsumerService])

  @KafkaListener(groupId = ConstantsUtil.LISTEN_GROUP_ID, topics = Array(ConstantsUtil.LISTEN_TOPIC))
  def consume(jobsCount: String): Unit = {
    println("Consumed:" + jobsCount)
    insertIntoCassandra(java.lang.Integer.parseInt(jobsCount))
    logger.info(String.format("$$ -> Consumed Message -> %s", jobsCount))
  }

  def insertIntoCassandra(jobsCount: Int): Unit = {
    //        clearData ();
    saveData(jobsCount)
  }

  def clearData(): Unit = {
    cassandraRepo.deleteAll()
  }

  def saveData(jobsCount: Int): Unit = {

    val date = new Date
    val time = date.getTime
    val timestamp = new Timestamp(time)
    val job: JobDetails =   new JobDetails(UUID.randomUUID(), timestamp, jobsCount)
    cassandraRepo.save(job)
  }

}