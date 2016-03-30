/**
  * Created by mfrag on 3/29/2016.
  */
class GameBoard (var rows: Int, var columns: Int) {
  var mRows=rows
  var mCols=columns
  var mGameBoard = Array.fill[String](mRows,mCols)(" - ")

  def getGameBoard(): Array[Array[String]] ={
    mGameBoard
  }

  def showGameBoard(): Unit ={
    var row=""
    var header=""
    for (j <- 0 until mCols){
      header+="  "+j.toString()
    }
    println(header +"  X")
    for (i<- 0 until mRows){
      row=""
      for (j<- 0 until mCols){
        row+=mGameBoard(i)(j)
      }
      row=i.toString+row
      println(row)
    }
    println("Y")
  }

  def updateGameBoard(missile: Missile): Unit ={
    var cords=missile.getCords()
    mGameBoard(cords(1))(cords(0))=missile.getStatus()
  }

}
