/**
  * Created by mfrag on 3/26/2016.
  */
import scala.io.StdIn.{readLine,readInt}

object battleShip {
  def main(args:Array[String]): Unit ={
    var valid=true
    var missileCords = new String
    var missile = new Missile("x,x")
    do {
      printf("Enter the X,Y attack coordinates: ")
      missileCords = scala.io.StdIn.readLine()
      missile.setCords(missileCords)
      valid=missile.validateCords()
    }while(!valid)

    printf("Firing missile to location %s!\n",missileCords)
    missile.updateStatus()
    println(missile.getStatus())
    missile.validateCords()
    missile.getCords()
  }
}
