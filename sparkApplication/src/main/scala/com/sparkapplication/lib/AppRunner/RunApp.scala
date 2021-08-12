package com.sparkapplication.lib.AppRunner

import com.sparkapplication.lib.master.ApplicationMaster
import com.typesafe.config.Config
import org.apache.spark.sql.SparkSession
import java.time.Instant
object RunApp extends ApplicationMaster{
  override def run(sparkSession: SparkSession, appConf: Config): Unit = {
   logger.info("Application Started at : {}", Instant.now())

  }

}
