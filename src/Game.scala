/**
  * Created by mfrag on 3/29/2016.
  */
import scala.collection.mutable.ArrayBuffer

class Game (var rows: Int, var columns: Int) {
  var mRows = rows
  var mCols = columns
  var mGameBoard = Array.fill[Object](mRows, mCols)("  - ")


  var mFleetMap = Array.fill[Object](mRows, mCols)("NA")
  var mFleetDisplay = Array.fill[Object](mRows, mCols)("  - ")
  var mAvPoints = ArrayBuffer[(Int, Int)]()
  val mRand = scala.util.Random

  var mAircraftCarrier = new Boat("Aircraft Carrier", 5)
  var mBattleship = new Boat("Battleship", 4)
  var mSubmarine = new Boat("Submarine", 3)
  var mCruiser = new Boat("Cruiser", 2)
  var mDestroyer = new Boat("Destroyer", 1)

  for (i <- 0 until mRows) {
    for (j <- 0 until mCols) {
      mAvPoints += i -> j
    }
  }

  def getGameBoard(): Array[Array[Object]] = {
    mGameBoard
  }

  def show(map: String): Unit = {
    var game = Array.fill[Object](mRows, mCols)("")
    if (map == "Fleet") {
      game = mFleetDisplay
    }
    else {
      game = mGameBoard
    }

    var row = ""
    var header = ""
    var status = ""

    for (j <- 0 until mCols) {
      header += Console.BOLD+Console.YELLOW + "   " + j.toString()
    }
    println(header + "  -X-")
    for (i <- 0 until mRows) {
      row = ""
      for (j <- 0 until mCols) {
        status=game(i)(j).toString

        if (status=="  - ") {
          row += Console.BLUE + status
        }
        else if (status=="  O ") {
          row += Console.YELLOW + status
        }
        else{
          row += Console.BOLD+Console.RED+status
        }

      }
      row = Console.BOLD+Console.YELLOW+i.toString + row
      println(row)
    }
    println(Console.BOLD+Console.YELLOW+"-Y-")
  }

  def updateGameBoard(missile: Missile): Unit = {
    var cords = missile.getCords()
    mGameBoard(cords(1))(cords(0)) = missile.getStatus()
  }


  def setValue(i: Int, j: Int, value: Object): Unit = {
    mGameBoard(i)(j) = value
  }

  def getValue(i: Int, j: Int): Object = {
    mGameBoard(i)(j)
  }

  //Create map function
  def move(point: (Int, Int), dir: Int, i: Int): (Int, Int) = {
    var tmp = (0, 0)
    if (dir == 0)
      tmp = (point._1 + i, point._2)
    if (dir == 1)
      tmp = (point._1 - i, point._2)
    if (dir == 2)
      tmp = (point._1, point._2 + i)
    if (dir == 3)
      tmp = (point._1, point._2 - i)
    tmp
  }


  def createFleetMap(): Unit = {
    var shipLocation = ArrayBuffer[(Int, Int)]()
    var rPoint = (0, 0)
    var orient = 0
    var pointsPlaced = 0
    var tmp = (0, 0)
    var shipPlaced = false
    var done = false
    var size = 0

    val fleet = Array(mAircraftCarrier, mBattleship, mSubmarine, mCruiser, mDestroyer)

    for (ship <- fleet) {
      shipPlaced = false

      while (!shipPlaced) {
        shipLocation = ArrayBuffer[(Int, Int)]()
        rPoint = mAvPoints(mRand.nextInt(mAvPoints.length))
        orient = mRand.nextInt(3) //0 is up 1 down 2 right 3 left
        pointsPlaced = 0
        size = ship.getSize()
        done = false
        while (pointsPlaced < size && !done) {
          tmp = move(rPoint, orient, pointsPlaced)
          if (mAvPoints contains tmp) {
            pointsPlaced += 1
            shipLocation += tmp
          }
          else {
            done = true
          }
        }
        if (pointsPlaced == size) {
          shipPlaced = true
        }
      }

      for (point <- shipLocation) {
        mFleetMap(point._1)(point._2) = ship
        mFleetDisplay(point._1)(point._2) = " " + ship.getShort() + " "
        mAvPoints -= point
      }

    }
  }
}
