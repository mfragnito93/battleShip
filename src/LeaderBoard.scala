import java.io._

import scala.collection.mutable.ArrayBuffer

/**
  * Created by mfrag on 4/2/2016.
  */
class LeaderBoard {
  var mLeaderBoard = ArrayBuffer[(String,Int)]()

  def set(): Unit ={
    val bufferedSource = io.Source.fromFile("leaderboard.csv")
    for (line<-bufferedSource.getLines()){
      val Array(name,shots)=line.split(",").map(_.trim)
      mLeaderBoard += ((name.toString,shots.toInt))
    }
    bufferedSource.close
  }

  def show(): Unit ={
    val header="-----LEADERBOARD------"
    println(header)
    for (i<-1 to mLeaderBoard.length){
      val beginRow=i.toString+".  "+mLeaderBoard(i-1)._1
      val spaces=header.length-beginRow.length-mLeaderBoard(i-1)._2.toString.length
      println(beginRow+" "*spaces+mLeaderBoard(i-1)._2)
    }
  }

  def save(): Unit ={
    val pw = new PrintWriter(new File("leaderboard.csv"))
    var output=""
    for (entry<-mLeaderBoard){
      output+=entry._1+","+entry._2.toString+"\n"
    }
    pw.write(output)
    pw.close()
  }

  def update(player: Player): Unit ={
    set()
    mLeaderBoard+=((player.mName,player.getTotalShots()))
    mLeaderBoard=mLeaderBoard.sortBy(_._2).take(10)
    save()
  }



}
