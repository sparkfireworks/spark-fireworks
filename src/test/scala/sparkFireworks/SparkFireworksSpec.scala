package sparkFireworks

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.spark.sql.functions.{col, concat_ws}
import org.apache.spark.sql.{Column, DataFrame}
import org.scalatest.FunSuite

class SparkFireworksSpec extends FunSuite with DataFrameSuiteBase {
  private def hashed_dataframe(df: DataFrame): Int = {
    val selection: Array[Column] = df.columns.map(col)
    val joined_values: String = "joined_values"
    (df.columns.mkString("_") +
      df.withColumn(joined_values, concat_ws(sep = "_", selection: _*)).select(joined_values).
        collect.foldLeft("") { (acc, x) => acc + x(0) }).hashCode
  }

  test(testName = "empty dataframe") {
    """
    {}
    ++
    ||
    ++
    ++"""
    val emptyDataFrame: DataFrame = spark.emptyDataFrame
    val actual: Int = hashed_dataframe(SparkFireworks(nestedDataFrame = emptyDataFrame).df)
    val expected: Int = DataFramesHashValues.emptyDataFrame
    assert(expected == actual)
  }

  test("nested dataframe") {
    """
    {"id": 1,"nested": {"level1": {"data1": [1,2,3],"data2": "Hello"}}}
    {"id": 2,"nested": {"level1": {"data1": [4,5,6],"data2": "Nice"}}}
    {"id": 3,"nested": {"level1": {"data1": [7,8,9],"data2": "Weather"}}}
    +-------------------+-------------------+---+
    |nested_level1_data1|nested_level1_data2| id|
    +-------------------+-------------------+---+
    |                  1|              Hello|  1|
    |                  2|              Hello|  1|
    |                  3|              Hello|  1|
    |                  4|               Nice|  2|
    |                  5|               Nice|  2|
    |                  6|               Nice|  2|
    |                  7|            Weather|  3|
    |                  8|            Weather|  3|
    |                  9|            Weather|  3|
    +-------------------+-------------------+---+"""
    val filePath: String = getClass.getResource("/test_data.json").getPath
    val nestedDataFrame: DataFrame = spark.read.json(filePath)
    val actual: Int = hashed_dataframe(SparkFireworks(nestedDataFrame = nestedDataFrame, columnsToExclude = List()).df)
    val expected: Int = DataFramesHashValues.nestedDataFrame
    assert(expected == actual)
  }

  test(testName = "isColumnOfArrayType given a Dataframe with array column and that column name should return true") {
    val filePath: String = getClass.getResource("/test_data_2.json").getPath
    val columnOfArrayType: String = "data1"
    val actual: Boolean = SparkFireworks.isColumnOfArrayType(dataFrame = spark.read.json(filePath), column = columnOfArrayType)
    assertTrue(actual)
  }

  test(testName = "isColumnOfArrayType given a Dataframe and a column name that is not of array type should return false") {
    val filePath: String = getClass.getResource("/test_data_2.json").getPath
    val columnOfArrayType: String = "data2"
    val actual: Boolean = SparkFireworks.isColumnOfArrayType(dataFrame = spark.read.json(filePath), column = columnOfArrayType)
    assertTrue(!actual)
  }
}
