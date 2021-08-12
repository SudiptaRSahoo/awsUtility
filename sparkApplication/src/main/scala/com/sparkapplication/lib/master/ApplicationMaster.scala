package com.sparkapplication.lib.master

import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait ApplicationMaster {

  final def main(args: Array[String]): Unit = {
    val appConfig = ConfigFactory.load
    val sparkConf = new SparkConf().setAppName(appName).setMaster("local[*]")
    setup(appConfig, sparkConf)
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    var error: Option[Exception] = None

    try {
      run(spark, appConfig)
    } catch {
      case e: Exception =>
        error = Some(e)
        throw e
    } finally {
      spark.close()
      tearDown(spark, appConfig, error)
    }
  }

  protected def appName: String = "Spark-app"

  protected def logger: Logger = LoggerFactory.getLogger(this.getClass)

  protected def setup(config: Config, conf: SparkConf): Unit = {}

  protected def tearDown(sparkSession: SparkSession, appConf: Config, error: Option[Exception]): Unit = {
    if (error.isDefined) {
      logger.error("Job Failed.")
    } else {
      logger.info("Job Completed Successfully.")
    }
  }

  protected def run(sparkSession: SparkSession, appConf: Config): Unit = {}
}
