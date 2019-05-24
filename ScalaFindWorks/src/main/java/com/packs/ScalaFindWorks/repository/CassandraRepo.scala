package com.packs.ScalaFindWorks.repository

import com.packs.ScalaFindWorks.model.JobDetails
import org.springframework.data.repository.CrudRepository

trait CassandraRepo extends CrudRepository[JobDetails,Long]{

}
