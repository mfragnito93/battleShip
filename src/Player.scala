/**
  * Created by mfrag on 3/29/2016.
  */
import scala.collection.mutable.ArrayBuffer

class Player (var name: String){
  var mName=name
  var mHits=0
  var mMisses=0
  var mHistory = ArrayBuffer[String]() //shot history

  def getName():String=mName

  def getHits():Int=mHits

  def getMisses():Int=mMisses

  def getTotalShots():Int=mHits+mMisses

  def getHitPercentage():Float={
    (mHits/getTotalShots().toFloat)*100
  }

  def addHit(): Unit ={
    mHits+=1
  }

  def addMiss(): Unit ={
    mMisses+=1
  }

  //Checks to make sure the coordinates haven't been used then adds them to the history
  def logMissile(cords : String): Boolean ={
    if(mHistory contains cords) {
      println("You have already fired a missile at this coordinate")
      false
    }
    else {
      mHistory += cords
      true
    }
  }



}
