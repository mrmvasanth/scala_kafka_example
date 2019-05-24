package com.packs.ScalaFindWorks.services

import java.io.{BufferedReader, FileReader}
import java.util.regex.{Matcher, Pattern}

import com.packs.ScalaFindWorks.constants.ConstantsUtil
import org.springframework.stereotype.Service


@Service
class FileReaderService {

  private var pattern: Pattern =  Pattern.compile(ConstantsUtil.URL_PATTERN, Pattern.CASE_INSENSITIVE)

  def getJobsCount(): Int = {
    var reader: BufferedReader = null
    var jobsCount: Int = 0
    try {
      reader = new BufferedReader(new FileReader(ConstantsUtil.LOG_FILE))
      var line: String = reader.readLine()
      while (line != null) {
        val matcher: Matcher = pattern.matcher(line)
        if (matcher.find()) {
          { jobsCount += 1; jobsCount - 1 }
        }
        line = reader.readLine()
      }
      reader.close()
      jobsCount
    }
    jobsCount
  }

}
