import scala.collection.mutable.ArrayBuffer

/**
  * Created by mfrag on 4/7/2016.
  */
object algoTest {
  def main(args:Array[String]): Unit ={
    var algo=new Algo
    val m1=ArrayBuffer(ArrayBuffer(0,1,2,3,4,5,6,7,8,9))
    val m1a=Array(Array(0,1,2,3,4,5,6,7,8,9),Array(0,1,2,3,4,5,6,7,8,9))
    var map=algo.constructMap(5,5)
    println(map)
    map=map+(0->algo.split(map(0),0,2))
    println(map)
  }

}
