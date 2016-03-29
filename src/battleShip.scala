/**
  * Created by mfrag on 3/26/2016.
  */
import scala.io.StdIn.{readLine,readInt}

object battleShip {
  def main(args:Array[String]): Unit ={
    printf("Enter the x,y attack coordinates: ")
    var missileCords=scala.io.StdIn.readLine()
    printf("Firing missile to location %s!\n",missileCords)
    var missile = new Missile(missileCords)
    missile.updateStatus()
    println(missile.getStatus())
    missile.getCords()
  }
}
