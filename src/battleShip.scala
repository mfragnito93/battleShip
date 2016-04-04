/**
  * Created by mfrag on 3/26/2016.
  */
import scala.io.StdIn.{readLine,readInt}

object battleShip {
  def main(args:Array[String]): Unit ={
    //Pre-Game Sequence
    println(Console.BOLD+Console.RED+"--------WELCOME TO BATTLESHIP--------")
    Thread.sleep(500)
    //Initializing game
    printf("Enter name to begin: ")

    val name = scala.io.StdIn.readLine()
    val player = new Player(name)
    val missile = new Missile("x,x",player)
    val game = new Game(10, 10)

    val leaderBoard =new LeaderBoard

    var valid = true
    var missileCords = new String

    game.setPlayer(player)
    game.createFleetMap()

    //In game sequence
    while(game.status()>0) {
      game.show("GameBoard")
      //Coordinate entry until valid coordinates are entered
      do {
        //Could make this one function of the missile class
        //Taking coordinates
        printf(Console.BOLD+Console.YELLOW+"Enter the X,Y attack coordinates: ")
        missileCords = scala.io.StdIn.readLine()
        //Using coordinates
        missile.setCords(missileCords)
        valid = missile.validateCords() //Checks if coordinates are valid
      } while (!valid)

      game.fire(missile)
    }

    //End of game sequence - Stats and LeaderBoard
    game.show("Fleet")
    println("-----GAME OVER-----\n")
    Thread.sleep(500)
    println("----PLAYER STATS----")
    println("NAME: "+player.getName())
    println("TOTAL SHOTS: "+player.getTotalShots())
    println("HITS: "+player.getHits())
    println("MISSES: "+player.getMisses())
    printf("HIT PERC: %.2f\n\n",player.getHitPercentage())

    leaderBoard.update(player)
  }
}
