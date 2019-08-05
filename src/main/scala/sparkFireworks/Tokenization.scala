package sparkFireworks

import org.apache.spark.sql._
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions._

case class Tokenization(tokenizedDf: DataFrame, lookup: DataFrame)

object Tokenization {
  def tokenizeDf(emptyDf: DataFrame, df: DataFrame, columnsToTokenize: Map[String, String]): DataFrame = {
    val hashColumn: UserDefinedFunction = udf((x: Any) => { x.toString.hashCode })
    columnsToTokenize.foldLeft(emptyDf: DataFrame)((acc: DataFrame, x: (String, String)) => acc.withColumn(x._2, df(x._1)))
  }
}