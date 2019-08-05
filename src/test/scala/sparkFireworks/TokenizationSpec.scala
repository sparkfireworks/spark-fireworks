package sparkFireworks

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.spark.sql.DataFrame
import org.scalatest.FunSuite

class TokenizationSpec extends FunSuite with DataFrameSuiteBase {
  test(testName = "testTokenizeDF") {
    val emptyDataFrame: DataFrame = spark.emptyDataFrame
    val actual: Int = SparkUtils.hashed_dataframe(Tokenization.tokenizeDf(
      emptyDf = emptyDataFrame, 
      df = emptyDataFrame, 
      columnsToTokenize = Map.empty[String, String]))
    val expected: Int = DataFramesHashValues.emptyDataFrame
    assert(expected == actual)
  }
}
