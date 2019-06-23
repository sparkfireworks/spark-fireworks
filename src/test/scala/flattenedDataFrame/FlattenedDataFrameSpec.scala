package flattenedDataFrame

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.spark.sql.DataFrame
import org.scalatest.FunSuite

class FlattenedDataFrameSpec extends FunSuite with DataFrameSuiteBase {
  test("empty dataframe") {
    """
    {}
    ++
    ||
    ++
    ++
    """
    val emptyDataFrame: DataFrame = spark.emptyDataFrame
    val actual: DataFrame = FlattenedDataFrame(nestedDataFrame = emptyDataFrame).df
    val expected: DataFrame = spark.emptyDataFrame
    assertDataFrameEquals(expected, actual)
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
    +-------------------+-------------------+---+
    """
    val filePath: String = getClass.getResource("/test_data.json").getPath
    val nestedDataFrame: DataFrame = spark.read.json(filePath)
    val actual: DataFrame = FlattenedDataFrame(nestedDataFrame = nestedDataFrame, columnsToExclude = List()).df
    println(hashed_dataframe(actual))
    // val expected: DataFrame = get_ordered_dataframe(fileName = "/expected_data.json", columns = actual.columns)
    val expected: DataFrame = spark.emptyDataFrame
    assertDataFrameEquals(expected, actual)
  }

  private def hashed_dataframe(df: DataFrame) ={
    df.columns.mkString("_")
  }

  private def get_ordered_dataframe(fileName: String, columns: Array[String]) = {
    spark.read.json(getClass.getResource(fileName).getPath)
      .select(columns.head, columns.tail: _*)
  }
}
