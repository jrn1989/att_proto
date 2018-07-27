package com.arena
import javafx.scene.control.{TreeView,TreeItem,Button,CheckBoxTreeItem};
import javafx.scene.text.Text


object person {
  case class PersonPrototype(attuid: Int, nombre: String, nivel:Int, infInm: List[PersonPrototype]) {

    def jsonString():String = {
      import net.liftweb.json._
      import net.liftweb.json.Serialization.{read, write}
			import scala.io.Source
			
      implicit val formats = Serialization.formats(NoTypeHints)
      val json = parse(write(this))
      prettyRender(json)
    }

		/*def returnNode():PersonPrototype={
			var x: PersonPrototype=null
			x.nombre=this.nombre
			x.attuid=this.attuid
			x.nivel=this.nivel
			x.infInm=this.infInm
			return x
		}*/
	
		def getNombre():String={return this.nombre}	
		def getAttuid():Int={return this.attuid}	
		def createTreeViewString( ):TreeItem[String]={
			if(!this.infInm.isEmpty){
				val temp  = new TreeItem[String] ("Nombre: " + this.nombre + ", ATTUID: " +this.attuid + ", Nivel: " + this.nivel)
				temp.setExpanded(true)
				temp.getChildren().add(this.infInm.head.createTreeViewString)
				if(!this.infInm.tail.isEmpty){ for(t<-this.infInm.tail) temp.getChildren().add(t.createTreeViewString) }
				return temp
			}else return (new TreeItem[String] ("Nombre: " + this.nombre + ", ATTUID: " +this.attuid + ", Nivel: " + this.nivel))

		}

		def createTreeView( ):CheckBoxTreeItem[PersonPrototype]={
			if(!this.infInm.isEmpty){
				val temp  = new CheckBoxTreeItem[PersonPrototype] (this)
				temp.setExpanded(true)
				temp.getChildren().add(this.infInm.head.createTreeView)
				if(!this.infInm.tail.isEmpty){ for(t<-this.infInm.tail) temp.getChildren().add(t.createTreeView) }
				return temp
			}else return (new CheckBoxTreeItem[PersonPrototype] (this))

		}
		override def toString = "Nombre: " + this.nombre + ", ATTUID: " +this.attuid + ", Nivel: " + this.nivel   

		def getName():String= {
            return nombre;
        }

		// Post-order traversal
		def postOrder( f: PersonPrototype => Unit ):Unit={
			if(!this.infInm.isEmpty){
				this.infInm.head.postOrder(f)
				for(t<-this.infInm.tail) t.postOrder(f)
				f(this)
			} else f(this)
		}
		// Pre-order traversal
		def preOrder( f: PersonPrototype => Unit ):Unit={
			if(!this.infInm.isEmpty){
				f(this)
				this.infInm.head.preOrder(f)
				for(t<-this.infInm.tail) t.preOrder(f)
			} else f(this)
		}

		// In-order traversal
		def inOrder( f: PersonPrototype => Unit ):Unit={
			if(!this.infInm.isEmpty){
				this.infInm.head.inOrder(f)
				f(this)
				for(t<-this.infInm.tail) t.inOrder(f)
			} else f(this)
		}

		// Node counting
    def headcount(): Int = {
      if (this.infInm.isEmpty) 1
      else this.infInm.foldLeft[Int](1)((a,b) => a+b.headcount)
    }
  }

	def readFromJson(jsonFile:String):PersonPrototype = {
			import scala.io.Source
			import net.liftweb.json._

			implicit val formats = DefaultFormats
			val fileContents = Source.fromFile(jsonFile)
			val json = parse(	fileContents.getLines.mkString )
			fileContents.close
			val tree = json.extract[PersonPrototype]
			return tree	

		}

}
