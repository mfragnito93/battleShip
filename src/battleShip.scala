/**
  * Created by mfrag on 3/26/2016.
  */
import scala.io.StdIn.{readLine,readInt}

object battleShip {
  def main(args:Array[String]): Unit ={
    val game = new Game(10, 10)
    val leaderBoard =new LeaderBoard

    //Pre-Game Sequence
    println(Console.BOLD+Console.RED+"--------WELCOME TO BATTLESHIP--------")
    Thread.sleep(500)
    //Initializing game
    printf("Enter name to begin: ")
    val name = scala.io.StdIn.readLine()
    val player = new Player(name)
    val missile = new Missile("x,x",player)

    game.setPlayer(player)
    game.createFleetMap()

    //In game sequence
    while(game.status()>0) {
      game.show("GameBoard")
      //Coordinate entry until valid coordinates are entered
      do {
        missile.takeCords() //prompts user for coordinates
      } while (!missile.valid)
      game.fire(missile)
    }

    //End of game sequence - Stats and LeaderBoard
    game.show("Fleet")

    println("-----GAME OVER-----\n")
    Thread.sleep(500)

    println("----PLAYER STATS----")
    player.showStats()

    leaderBoard.update(player)
  }
}
