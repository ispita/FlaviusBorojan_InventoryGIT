/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.view_controller;

import fb_inventory.model.Part;
import fb_inventory.model.InhousePart;
import fb_inventory.model.Inventory;
import static fb_inventory.model.Inventory.getInventoryParts;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 *
 * @author flavius8
 */
public class MainScreen implements Initializable {
    
   @FXML
   private TableView<Part> partsTable;
   @FXML
   private TableColumn<Part, Integer> partsID;
   @FXML
   private TableColumn<Part, String> partsName;
   @FXML
   private TableColumn<Part, Integer> partsInv;
   @FXML
   private TableColumn<Part, Double> partsPrice;
   @FXML
   private Button exitButton;


    @FXML
   private void  handleAddPartsButton(ActionEvent e) throws Exception {
        Parent addPartRoot = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Stage addPartStage = new Stage();
        Scene addPartScene = new Scene(addPartRoot);

        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }
   
       @FXML
   public void  handleModifyPartsButton(ActionEvent e) throws Exception {
        Stage modifyPartsStage; 
        Parent modifyPartsRoot; 
        modifyPartsStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        modifyPartsRoot = loader.load();
        Scene scene = new Scene(modifyPartsRoot);
        modifyPartsStage.setScene(scene);
        modifyPartsStage.show(); 
        
        ModifyPart controller = loader.getController();
        Part selectedPart=partsTable.getSelectionModel().getSelectedItem();
        int partIndex = Inventory.getInventoryParts().indexOf(selectedPart);
        controller.setPart(selectedPart, partIndex);
  
       
        
    }
   
     public void updatePartsTable() {
        partsTable.setItems(getInventoryParts());
    }
   @FXML  
   private void handleExitButton(ActionEvent e){
    Stage stage = (Stage) exitButton.getScene().getWindow();
    stage.close();
}
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    partsID.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
    partsName.setCellValueFactory(cellData -> cellData.getValue().getName());
    partsInv.setCellValueFactory(cellData -> cellData.getValue().getInv().asObject());
    partsPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
    
    updatePartsTable();
    }    
    
}
