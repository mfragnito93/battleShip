import java.io._
import scala.collection.mutable.ArrayBuffer

/**
  * Created by mfrag on 4/2/2016.
  */
class LeaderBoard {
  private var mLeaderBoard = ArrayBuffer[(String,Int)]()

  //Leader board data is saved in local csv file

  //Reading leader board csv and saving it as an array
  private def set(): Unit ={
    val bufferedSource = io.Source.fromFile("leaderboard.csv")
    for (line<-bufferedSource.getLines()){
      val Array(name,shots)=line.split(",").map(_.trim)
      mLeaderBoard += ((name.toString,shots.toInt))
    }
    bufferedSource.close
  }

  //printing leader board
  def show(): Unit ={
    val header="-----LEADERBOARD------"
    println(header)
    for (i<-1 to mLeaderBoard.length){
      val beginRow=i.toString+".  "+mLeaderBoard(i-1)._1
      val spaces=header.length-beginRow.length-mLeaderBoard(i-1)._2.toString.length
      println(beginRow+" "*spaces+mLeaderBoard(i-1)._2)
    }
  }

  //Saving leader board to csv
  private def save(): Unit ={
    val pw = new PrintWriter(new File("leaderboard.csv"))
    var output=""
    for (entry<-mLeaderBoard){
      output+=entry._1+","+entry._2.toString+"\n"
    }
    pw.write(output)
    pw.close()
  }

  def update(player: Player): Unit ={
    set() //grabbing current leader board
    mLeaderBoard+=((player.getName(),player.getTotalShots())) //Adding player score
    mLeaderBoard=mLeaderBoard.sortBy(_._2).take(10) //taking only top 10
    save() //saving results
    show() //show results
  }



}
