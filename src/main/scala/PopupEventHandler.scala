package com.arena.popupeventhandler
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.{TextField,Button}
import javafx.scene.layout.VBox;
import com.arena.maineventhandler.MainEventHandler
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.{Stage,Modality}
import javafx.scene.{Scene}
class PopupEventHandler {

	private var data:String=_

  def getData():String= { return data}

	def showStage(n:String, a:Int):Unit={

		val root2 = new VBox()
		val scene2 = new Scene(root2);
		val stage2 = new Stage();	
		val tf = new TextField();
		val bt = new Button("Submit")
		root2.getChildren().addAll(tf, bt);
		stage2.setScene(scene2);
	
    println(n)
		println(a.toString)
		tf.setText(n)

		bt.setOnAction((e: ActionEvent) => {
			data = tf.getText();
      stage2.close();
		})
		stage2.initModality(Modality.APPLICATION_MODAL);
		stage2.showAndWait();
		

  }

}

