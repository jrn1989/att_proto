package com.arena.maineventhandler

	//import scalafx.scene.control.{TableView,TableColumn}
	import scalafx.beans.property.{ObjectProperty}

import javafx.collections.{FXCollections,ObservableList,ListChangeListener};
import javafx.fxml.FXMLLoader;
import java.io.{FileInputStream,File,FileWriter};
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.{TreeView,TreeItem,Button,TreeTableColumn,TreeTableView,TreeCell,CheckBoxTreeItem,TextField};
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.{BorderPane,AnchorPane};
import javafx.scene.control.cell.{TreeItemPropertyValueFactory,CheckBoxTreeTableCell};
import javafx.util.StringConverter;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.{ActionEvent,EventHandler};
import com.arena.person.{PersonPrototype,readFromJson};

import javafx.beans.binding.Bindings;
//import org.controlsfx.control.CheckTreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.Tooltip;
import javafx.css.PseudoClass
import javafx.beans.value.ObservableValue
import javafx.scene.control.TreeItem.TreeModificationEvent
import javafx.scene.input.MouseEvent
import javafx.scene.Scene;
import javafx.stage.{Stage,FileChooser,Modality};
import com.arena.popupeventhandler.PopupEventHandler
//import javafx.stage.Modality;
//import com.arena.person.readFromJson
import scala.collection.mutable.ListBuffer


class MainEventHandler {
	
	//@FXML
  //private var treeViewID1: TreeView[String] = _

	//@FXML
  //private var mainAnchorPane: AnchorPane = _

	@FXML
  private var botonCargarPU: Button = _

	@FXML
  private var generarRama: Button = _

	@FXML
  private var cargarModificaciones: Button = _

	@FXML
  private var centro: BorderPane = _

	val colorNivel0 = PseudoClass.getPseudoClass("colorNivelNada");
	val colorNivel2 = PseudoClass.getPseudoClass("colorNivel2");
	val colorNivel3 = PseudoClass.getPseudoClass("colorNivel3");
	val colorNivel4 = PseudoClass.getPseudoClass("colorNivel4");
	val colorNivel5 = PseudoClass.getPseudoClass("colorNivel5");
	val colorNivel6 = PseudoClass.getPseudoClass("colorNivel6");
	val colorNivel7 = PseudoClass.getPseudoClass("colorNivel7");

	var rama:PersonPrototype=_
	var checkedItems = new ListBuffer[CheckBoxTreeItem[PersonPrototype]]()
	/*The myStage variable let us keep a reference to the main window*/
	private var myStage:Stage = _
	def setStage(stage:Stage):Unit={
		myStage = stage
	}

	private var nombreBack:String = _
	def setNombreBack(x:String):Unit={
		nombreBack = x
	}

	/*private var attuidBack:Int = _
	def setAttuidBack(x:Int):Unit={
		attuidBack = x
	}*/

	def max(s1: CheckBoxTreeItem[PersonPrototype], s2: CheckBoxTreeItem[PersonPrototype]): 
		PersonPrototype = if (s1.getValue().nivel > s2.getValue().nivel) s1.getValue().getNode() else s2.getValue().getNode()


	def initialize(): Unit= {
		var rootItem = new CheckBoxTreeItem[PersonPrototype]
		generarRama.setDisable(true)
		cargarModificaciones.setOnAction((e: ActionEvent) => {
			for(a<-checkedItems){println(a.getValue().getNombre())}
								/*val fxml2 = new FXMLLoader(getClass().getResource("/popup.fxml"))  
								val root2: AnchorPane = fxml2.load()
								val controller:PopupEventHandler = fxml2.getController();
			println(controller.getData())*/
			
		})
		// Button generar rama
		// Toma la rama que este marcada y la guarda en un .json	
		generarRama.setOnAction((e: ActionEvent) => {
			println("Generar rama")

			val fileChooser = new FileChooser();
			fileChooser.setTitle("Guardar Plantilla Única");
			val extFilter = new FileChooser.ExtensionFilter("Archivo .json", "*.json");
			fileChooser.getExtensionFilters().add(extFilter);
			var file = fileChooser.showSaveDialog(myStage);
			if(file!=null){
				if(!file.getPath().endsWith(".json")){
					file = new File(file.getPath() + ".json");
				}
				val fileWriter = new FileWriter(file)
				//fileWriter.write(rama.jsonString)
			  //sval topStudent = checkedItems.reduceLeft(max)
				if(checkedItems.last.getValue()!=null)
				{fileWriter.write(checkedItems.last.getValue().getNode().jsonString)
				fileWriter.close}
			}

		})

		/********************/
		/***TO UPLOAD A PU***/
		/********************/
		botonCargarPU.setOnAction((e: ActionEvent) => {
			/*This block creates a file browser associate with the main window*/	
			val fileChooser = new FileChooser();
			fileChooser.setTitle("Cargar Plantilla Única");
			val extFilter = new FileChooser.ExtensionFilter("Archivo .json", "*.json");
			fileChooser.getExtensionFilters().add(extFilter);
			val file = fileChooser.showOpenDialog(myStage);
			
			/*****************************************************************/
			if(file!=null){
				generarRama.setDisable(false)
				/*This block creates the tree view from the json file*/
				rootItem = readFromJson(file.toString).createTreeView(checkedItems)
				val treeTable = new TreeView[PersonPrototype](rootItem)
				/*rama = readFromJson(file.toString) TODO*/
				treeTable.setEditable(true)
				/*****************************************************/

				/*This block puts colors and tooltips to the tree view*/
				treeTable.setCellFactory(column =>{
					new CheckBoxTreeCell[PersonPrototype](){ 
						override def updateItem( item:PersonPrototype, empty:Boolean ):Unit={
							super.updateItem(item, empty);
							if (empty) { 
								setText(null);
								setTooltip(null);
								//pseudoClassStateChanged(colorNivel0, item==null)
								pseudoClassStateChanged(colorNivel2, false);
								pseudoClassStateChanged(colorNivel3, false);
								pseudoClassStateChanged(colorNivel4, false);
								pseudoClassStateChanged(colorNivel5, false);
								setStyle("");
							}else{
								setText(item.nombre)
								pseudoClassStateChanged(colorNivel2, item.nivel==2);
								pseudoClassStateChanged(colorNivel3, item.nivel==3);
								pseudoClassStateChanged(colorNivel4, item.nivel==4);
								pseudoClassStateChanged(colorNivel5, item.nivel==5);
								//if(item.nivel==5){ setStyle("-fx-background-color: black; -fx-text-fill: white; ")}
								setTooltip(new Tooltip("Nombre: " + item.nombre + "\nATTUID: " +item.attuid + "\nNivel: " + item.nivel));
							}
						}
					};
				});
				/******************************************************/
				
				/*This block opens a new window to edit attibutes in the tree view nodes*/
				treeTable.setOnMouseClicked(new EventHandler[MouseEvent](){
					override def handle(mouseEvent:MouseEvent):Unit={ 
						if(mouseEvent.getClickCount() == 2){
							val item = treeTable.getSelectionModel().getSelectedItem();
						if(item!=null){
							val wc = new PopupEventHandler()
							wc.showStage(item.getValue().getNombre(),item.getValue().getAttuid())
							println(wc.getNombreToMain(),wc.getAttuidToMain())
							//System.out.println("Texto seleccionado: " + item.getValue());
								//println("Texto seleccionado: " + item.getValue().getNombre());
								//val fxml2 = new FXMLLoader(getClass().getResource("/popup.fxml"))  
								//val root2: AnchorPane = fxml2.load()
								//val controller:PopupEventHandler = fxml2.getController();
								//controller.setNombre(item.getValue().getNombre())
								//controller.setAttuid(item.getValue().getAttuid())
								//controller.poblarPopup(item.getValue().getNombre(),item.getValue().getAttuid())
								
          			//val scene2 = new Scene(root2);
								//val stage2 = new Stage();
								//controller.setStage(stage2)
								//stage2.initModality(Modality.APPLICATION_MODAL);
								//stage2.initModality(Modality.WINDOW_MODAL);
								//stage2.initOwner(myStage)
								//stage2.setTitle("Modificar atributos");
        				//stage2.setScene(scene2);
								
        				//stage2.show();
								//setNombreBack(controller.getData())
							}
						}
					}
				})
				
				/************************************************************************/
				centro.setCenter(treeTable)

			}
		}) 
	}
}
