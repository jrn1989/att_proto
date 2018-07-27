package com.arena

import java.io.{FileInputStream,IOException,File};
import javafx.scene.{Group,Scene}
import javafx.scene.input._;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.{TreeView,TreeItem};
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import com.arena.maineventhandler.MainEventHandler
object ATTGUI
{
	def main(args: Array[String])
	{
		Application.launch(classOf[ATTGUI], args: _*)
	}
}


class ATTGUI extends Application
{
	override def start(stage: Stage)
	{
		val fxml = new FXMLLoader(getClass().getResource("/layout.fxml"))  
		val root: AnchorPane = fxml.load()
 		val controller:MainEventHandler = fxml.getController();
		controller.setStage(stage);

		// Create the Scene
		val scene = new Scene(root);
		// Set the Scene to the Stage
		scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm())
		stage.setScene(scene);
		// Set the Title to the Stage
		stage.setTitle("at&t");
		// Display the Stage
		stage.show();	
		}
}



