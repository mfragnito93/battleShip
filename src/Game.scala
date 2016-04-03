/**
  * Created by mfrag on 3/29/2016.
  */
import scala.collection.mutable.ArrayBuffer

class Game (var rows: Int, var columns: Int) {
  var mRows = rows
  var mCols = columns
  var mGameBoard = Array.fill[Object](mRows, mCols)("  - ") //Holds the player's hits and misses


  var mFleetMap = Array.fill[Object](mRows, mCols)("NA") //FleetMap is used to hold the boat objects, backend checking
  var mFleetDisplay = Array.fill[Object](mRows, mCols)("  - ") //Copy of fleet map in a display friendly manner
  var mAvPoints = ArrayBuffer[(Int, Int)]() //Used to determine available points left on the board
  val mRand = scala.util.Random //random number generator class

  //Defining boats that will be used in the game
  var mAircraftCarrier = new Boat("Aircraft Carrier", 6)
  var mBattleship = new Boat("Battleship", 5)
  var mSubmarine = new Boat("Submarine", 4)
  var mCruiser = new Boat("Cruiser", 3)
  var mDestroyer = new Boat("Destroyer", 2)
  val mFleet = Array(mAircraftCarrier, mBattleship, mSubmarine, mCruiser, mDestroyer)

  var mPlayer = new Player ("CHANGE ME")

  //Initializing available points to all points on the board
  for (i <- 0 until mRows) {
    for (j <- 0 until mCols) {
      mAvPoints += i -> j //adding the tuple to the available points
    }
  }

  def getGameBoard(): Array[Array[Object]] = {
    mGameBoard
  }

  //Used to display either the fleet display or the current state of the game
  def show(map: String): Unit = {
    var game = Array.fill[Object](mRows, mCols)("")
    //Deciding which tpe of map to display
    if (map == "Fleet") {
      game = mFleetDisplay
    }
    else {
      game = mGameBoard
    }

    var row = ""
    var header = ""
    var status = ""

    //Constructing row of column numbers
    for (j <- 0 until mCols) {
      header += Console.BOLD+Console.YELLOW + "   " + j.toString()
    }
    println(header + "  -X-")

    //Constructing each subsequent row and printing out
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
  //Changes the value on the game state 2D array to the status of the missile
  def updateGameBoard(missile: Missile): Unit = {
    var cords = missile.getCords()
    mGameBoard(cords(1))(cords(0)) = missile.getStatus()
  }

  //****Come back
  def setValue(i: Int, j: Int, value: Object): Unit = {
    mGameBoard(i)(j) = value
  }

  def getValue(i: Int, j: Int): Object = {
    mGameBoard(i)(j)
  }

  /*Constructing the positions of the boat
  Algorithm:
  1 - Select a random point on the board for the boats starting point
  2 - Choose a direction
  3 - Try to place the boat on the points in that direction
  4 - If the boat doesn't fit start over
   */

  //Move helper function for the create fleet map function
  //Moves the point in the direction needed, 0=UP 1=DOWN 2=RIGHT 3=LEFT
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
    var rPoint = (0, 0) //random point
    var orient = 0 //direction
    var pointsPlaced = 0 //tracks how many points of the boat are placed
    var tmp = (0, 0)
    var shipPlaced = false
    var done = false
    var size = 0

    //setting ship locations one-by-one starting with the biggest boat
    for (ship <- mFleet) {
      shipPlaced = false
      while (!shipPlaced) {
        shipLocation = ArrayBuffer[(Int, Int)]() //resetting boat location
        rPoint = mAvPoints(mRand.nextInt(mAvPoints.length)) //randomly selecting point on the board
        orient = mRand.nextInt(3) //randomly selecting orientation
        pointsPlaced = 0
        size = ship.getSize()
        done = false
        while (pointsPlaced < size && !done) {
          tmp = move(rPoint, orient, pointsPlaced) //finding next point
          //if point is in the available list then add it to the ship's location
          if (mAvPoints contains tmp) {
            pointsPlaced += 1
            shipLocation += tmp
          }
          else {
            done = true //if the point is not available then restart with the same ship
          }
        }
        if (pointsPlaced == size) {
          shipPlaced = true
        }
      }
      //Once the ships location is confirmed placing the boat and updating necessary components
      for (point <- shipLocation) {
        mFleetMap(point._1)(point._2) = ship
        mFleetDisplay(point._1)(point._2) = " " + ship.getShort() + " "
        mAvPoints -= point
      }

    }
  }

  def setPlayer(player: Player): Unit ={
    mPlayer=player
  }

  //checking if missiles coordinates hit a boat
  def fire(missile: Missile): Unit ={
    var cords = missile.getCords()
    var point = mFleetMap(cords(1))(cords(0))

    if (point=="NA") {
      mPlayer.addMiss()
      missile.status = "  O "
    }
    else {
      mPlayer.addHit()
      point.asInstanceOf[Boat].hit()
      point.asInstanceOf[Boat].showStatus()
      missile.status = "  X "
    }
    updateGameBoard(missile)
  }

  //status is determined by summing the status of each boat -- 0 = GAME OVER
  def status():Int={
    var status=0
    for (ship<-mFleet)
      status+=ship.getStatus()
    status
  }

}
