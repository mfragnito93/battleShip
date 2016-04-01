/**
  * Created by mfrag on 3/26/2016.
  */
import scala.io.StdIn.{readLine,readInt}

object battleShip {
  def main(args:Array[String]): Unit ={
    println(Console.BOLD+Console.RED+"--------WELCOME TO BATTLESHIP--------")
    Thread.sleep(500)

    printf("Enter name to begin: ")

    val name = scala.io.StdIn.readLine()
    val player = new Player(name)
    val missile = new Missile("x,x",player)
    val game = new Game(10, 10)

    var valid = true
    var missileCords = new String

    game.setPlayer(player)
    game.createFleetMap()
//    game.show("Fleet")


    while(game.status()>0) {
      game.show("GameBoard")
      do {
        printf(Console.BOLD+Console.YELLOW+"Enter the X,Y attack coordinates: ")
        missileCords = scala.io.StdIn.readLine()
        missile.setCords(missileCords)
        valid = missile.validateCords()
      } while (!valid)

//      printf("Firing missile to location %s...\n", missileCords)
      game.fire(missile)
    }
    println("---GAME OVER---")
    game.show("Fleet")
    Thread.sleep(500)
    println("NAME: "+player.getName())
    println("HITS: "+player.getHits())
    println("MISSES: "+player.getMisses())
    println("HIT %: "+player.getHitPercentage())
  }
}
