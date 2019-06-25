package flattenedDataFrame

object DataFramesHashValues {
  """
  {}
  ++
  ||
  ++
  ++"""
  val emptyDataFrame: Int = 0

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
  val nestedDataFrame: Int = -1900326182
}
