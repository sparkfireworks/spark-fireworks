package flattenedDataFrame

import org.apache.spark.sql.functions.{array, col, explode}
import org.apache.spark.sql.types.{ArrayType, StructType}
import org.apache.spark.sql.{Column, DataFrame}

case class FlattenedDataFrame(val df: DataFrame)

object FlattenedDataFrame {
  def apply(nestedDataFrame: DataFrame, columnChar: String = "_", columnsToExclude: List[String] = List()): FlattenedDataFrame = {
    FlattenedDataFrame(df = flatten(df = nestedDataFrame, columnChar = columnChar, columnsToExclude = columnsToExclude))
  }

  def flatten(df: DataFrame, columnChar: String, columnsToExclude: List[String]): DataFrame = {

    val columnsToExplode: List[String] = df.schema.fields
      .filter(field => field.dataType.isInstanceOf[ArrayType])
      .map(field => field.name).toList

    val columns: List[String] = columnsToExplode.toSet.diff(columnsToExclude.toSet).toList

    val explodedDataFrame: DataFrame = explodeColumns(dataFrame = df, columns = columns)

    val nestedColumns: List[String] = explodedDataFrame.schema.fields
      .filter(field => field.dataType.isInstanceOf[StructType])
      .map(field => field.name).toList

    val otherColumns: List[String] = explodedDataFrame.schema.fields
      .filter(field => !field.dataType.isInstanceOf[StructType])
      .map(field => field.name).toList

    val flatColumns: List[String] = columnsToExclude.foldLeft(otherColumns)((acc: List[String], column: String) =>
      if (nestedColumns.contains(column)) column :: acc else acc)

    val newNestedColumns: List[String] = nestedColumns.toSet.diff(columnsToExclude.toSet).toList

    val columnsToCastToArray: List[String] = columnsToExclude.filter(df.columns.contains)

    val dataFrameWithCorrectTypes: DataFrame = columnsToCastToArray.foldLeft(explodedDataFrame)((acc: DataFrame, column: String) =>
      if (!isColumnOfType(dataFrame = acc, column = column)) acc.withColumn(column, array(col(column))) else acc)

    val columnsToSelect: List[Column] = (for {
      nestedColumn: String <- newNestedColumns
      column: String <- dataFrameWithCorrectTypes.select(nestedColumn + ".*").columns
    } yield col(nestedColumn + '.' + column).alias(nestedColumn + columnChar + column)) ++ flatColumns.map(c => col(c))

    val dataFrame: DataFrame = dataFrameWithCorrectTypes.select(columnsToSelect.map(x => x): _*)

    dataFrame.schema.fields.toSet match {
      case xs if xs == df.schema.fields.toSet => dataFrame
      case _ => flatten(df = dataFrame, columnChar = columnChar, columnsToExclude = columnsToExclude)
    }
  }

  def explodeColumns(dataFrame: DataFrame, columns: List[String]): DataFrame = {
    columns.foldLeft(dataFrame)((acc: DataFrame, column: String) => acc.withColumn(column, explode(col(column))))
  }

  def isColumnOfType(dataFrame: DataFrame, column: String): Boolean = {
    dataFrame.select(column).schema.fields(0).dataType.equals(org.apache.spark.sql.types.ArrayType)
  }
}
