package com.packs.ScalaFindWorks.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Configuration, PropertySource}
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

import scala.beans.BeanProperty

@Configuration
@PropertySource(Array("classpath:application.properties"))
@EnableCassandraRepositories
class CassandraConfig {

  @Value("spring.data.cassandra.port")
  @BeanProperty
  protected var contactPoints: String = _

  @Value("9042")
  @BeanProperty
  protected var port: Int = _

  @Value("${spring.data.cassandra.keyspace-name}")
  private var keySpace: String = _

  protected def getKeyspaceName(): String = keySpace

  def getSchemaAction(): SchemaAction = SchemaAction.CREATE_IF_NOT_EXISTS

}
