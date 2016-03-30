/**
  * Created by mfrag on 3/26/2016.
  */
import scala.io.StdIn.{readLine,readInt}

object battleShip {
  def main(args:Array[String]): Unit ={

    println("------WELCOME TO BATTLESHIP------")
    Thread.sleep(500)

    printf("Enter name to begin: ")
    var name = scala.io.StdIn.readLine()
    var player = new Player(name)

    var valid = true
    var missileCords = new String
    var missile = new Missile("x,x",player)
    var gameBoard = new GameBoard(10, 10)

    for (i<-0 until 5) {
      gameBoard.showGameBoard()
      do {
        printf("Enter the X,Y attack coordinates: ")
        missileCords = scala.io.StdIn.readLine()
        missile.setCords(missileCords)
        valid = missile.validateCords()
      } while (!valid)
      printf("Firing missile to location %s...\n", missileCords)
      Thread.sleep(2000)
      missile.updateStatus()
      gameBoard.updateGameBoard(missile)
    }
  }
}
