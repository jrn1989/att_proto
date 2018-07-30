package com.arena.popupeventhandler
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.{TextField,Button,Label}
import com.arena.maineventhandler.MainEventHandler
import javafx.scene.layout.{AnchorPane,VBox,HBox,Region,Priority};
import javafx.fxml.FXMLLoader;
import javafx.stage.{Stage,Modality}
import javafx.scene.{Scene}
import javafx.geometry.Insets;
class PopupEventHandler {

	private var data:String=_

  def getData():String= { return data}

	def showStage(n:String, a:Int):Unit={

		val root2 = new AnchorPane()
		root2.setPrefSize(300,200)

		val vbox = new VBox()		
		vbox.setPrefSize(300,200)
		//vbox.setMargin(button1,new Insets(1,1,1,1))

		val labelNombre = new Label("Nombre:")
		val labelAttuid = new Label("Attuid:")

		val tfNombre = new TextField()
		tfNombre.setPrefSize(300,55)

		val tfAttuid = new TextField()
		tfAttuid.setPrefSize(300,55)

		VBox.setMargin(labelNombre,new Insets(10,10,10,10))
		VBox.setMargin(labelAttuid,new Insets(10,10,10,10))
		VBox.setMargin(tfNombre,new Insets(0,10,10,10))
		VBox.setMargin(tfAttuid,new Insets(10,10,10,10))


		val btnAceptar = new Button("Aceptar")
		val btnCancelar = new Button("Cancelar")
		val regionh = new Region()
		regionh.setPrefSize(55,55)

		val hbox = new HBox()
		hbox.setPrefSize(300,55)
		hbox.getChildren().addAll(btnAceptar,regionh,btnCancelar)
		HBox.setMargin(btnAceptar,new Insets(20,10,10,10))
		HBox.setMargin(btnCancelar,new Insets(20,10,10,10))		

		HBox.setHgrow(regionh, Priority.ALWAYS)


		vbox.getChildren().addAll(labelNombre,tfNombre,labelAttuid,tfAttuid,hbox)


		val scene2 = new Scene(root2);
		val stage2 = new Stage();	
		val tf = new TextField();
		val bt = new Button("Submit")
		root2.getChildren().addAll(vbox);
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

