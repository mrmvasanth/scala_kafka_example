package com.packs.ScalaFindWorks.config

import java.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration, PropertySource}

@Configuration
@PropertySource(Array("classpath:application.yml"))
class KafkaConsumerConfig {

  @Value("${bootstrap-servers}")
  private var bootstrapServers: String = _

  @Value("${group-id}")
  private var groupId: String = _

  @Value("${auto-offset-reset}")
  private var autoOffsetReset: String = _

  @Bean
  def kafkaConsumerConf(): util.HashMap[String, Any] = {
    val config: util.HashMap[String, Any] = new util.HashMap[String, Any]()
    config.put("bootstrap.servers", bootstrapServers)
    config.put("group.id", groupId)
    config.put("auto-offset-reset", autoOffsetReset)
    config
  }

}
