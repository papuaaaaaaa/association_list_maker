import scala.io.Source;
import java.io.File;

class CsvReader {
	def execute(readed_file:String){
		for(line <- Source.fromFile(readed_file,"utf-8").getLines()){
			//println(makeString(increase(line)))
			for(i <- increase1(line)){
				println(i.mkString("\t"));
			}
		}
	}
	
	def increase(line:String):Array[Array[String]]={
		var tags = line.split(",")
		var box = Array.ofDim[String](tags.length,tags.length)
		for(i<-intWrapper(0) to tags.length-1){
			for(j<-intWrapper(0) to tags.length-1){
				box(j)((i+j)%tags.length) = tags(i)
			}
		}
		return box;
	}
	
	def increase1(line:String):scala.collection.immutable.IndexedSeq[scala.collection.immutable.IndexedSeq[String]]={
		val tags = line.split(",")
		(0 to tags.length-1).map(i => {
		  (0 to tags.length-1).map(j =>{
		    tags((i+j)%(tags.length))
		  })
		})
	}
	
	def makeString(box:Array[Array[String]]):String={
		var str = new StringBuffer();
		for(tags <- refArrayOps(box)){
			for(tag <- refArrayOps(tags)){
				str.append(tag + "\t")
			}
			str.append("\n")
		}
		return str.toString();
	}
}

//無限リストとスライディング
//list操作をリピートしたい
//listから繰り返しとりだしたい