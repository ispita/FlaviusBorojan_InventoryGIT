/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.view_controller;

import fb_inventory.model.Part;
import fb_inventory.model.InhousePart;
import fb_inventory.model.OutsourcedPart;
import fb_inventory.model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 *
 * @author flavius8
 */
public class ModifyPart implements Initializable{
    
   
    @FXML private RadioButton addPartInhouse;
    @FXML private RadioButton addPartOutsourced;
    @FXML private Label source;
    @FXML private TextField sourceText;
    @FXML private TextField nameText;
    @FXML private TextField invText;
    @FXML private TextField priceText;
    @FXML private TextField maxText;
    @FXML private TextField minText;
    @FXML private TextField idText;
    @FXML private Button cancelButton;
    @FXML private Button saveButton;
    Part part;
    InhousePart ihPart;
    OutsourcedPart osPart;
    Inventory inventory;
    
   
    public void initialize(URL url, ResourceBundle rb) {
      //     
    }    
    
    @FXML
private void radioButtonSelectedInhouse(ActionEvent e) {
    sourceText.setPromptText("Mach ID");
    source.setText("Machine ID");
    addPartOutsourced.setSelected(false);
}

    @FXML
private void radioButtonSelectedOutsourced(ActionEvent e) {
    sourceText.setPromptText("Comp Nm");
    source.setText("Company Name");
    addPartInhouse.setSelected(false);
}

    @FXML
private void handleModifyPartsSave(ActionEvent e)throws Exception{
    String source = sourceText.getText();
    String name = nameText.getText();
    Integer inv = Integer.parseInt(invText.getText());
    Double price = Double.parseDouble(priceText.getText());
    Integer max = Integer.parseInt(maxText.getText());
    Integer min = Integer.parseInt(minText.getText());
   
    if (addPartInhouse.isSelected() == true ){
    InhousePart modifiedPart = (InhousePart) ihPart;
    modifiedPart.setInhouseSource(source);
    modifiedPart.setName(name);
    modifiedPart.setInv(inv);
    modifiedPart.setPrice(price);
    modifiedPart.setMax(max);
    modifiedPart.setMin(min);
    System.out.println("You Modified Inhouse.");
    
    }
    else{
    OutsourcedPart modifiedPart = (OutsourcedPart) osPart;
    modifiedPart.setOutsourcedSource(source);
    modifiedPart.setName(name);
    modifiedPart.setInv(inv);
    modifiedPart.setPrice(price);
    modifiedPart.setMax(max);
    modifiedPart.setMin(min);
    System.out.println("You Modified Outourced.");
    }
        Stage mainStage; 
        Parent mainRoot; 
        mainStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        mainRoot = loader.load();
        
        MainScreen controller = loader.getController();
        controller.setGlobalInventory(inventory);        
        Scene addProductScene = new Scene(mainRoot);
        mainStage.setScene(addProductScene);           
        mainStage.show(); 
    
}
@FXML
private void handleCancelButton(ActionEvent e) throws Exception{
        Stage mainStage; 
        Parent mainRoot; 
        mainStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        mainRoot = loader.load();
        
        MainScreen controller = loader.getController();
        controller.setGlobalInventory(inventory);
        Scene addProductScene = new Scene(mainRoot);
        mainStage.setScene(addProductScene);           
        mainStage.show(); 
}



     public void setPart(Part selectedPart, int partIndex, Inventory inventory) {
         this.inventory = inventory;
     if (selectedPart instanceof InhousePart){
        
       this.part = inventory.getInventoryParts().get(partIndex);
       int partID = selectedPart.getPartID().get();
       System.out.println(partID);
       this.ihPart = (InhousePart) selectedPart;
       String getSource = this.ihPart.getInhouseSource().get();
       
        addPartInhouse.setSelected(true);
        addPartOutsourced.setSelected(false);
        nameText.setText(this.part.getName().get());
        invText.setText(Integer.toString(this.part.getInv().get()));
        priceText.setText(Double.toString(this.part.getPrice().get()));
        maxText.setText(Integer.toString(this.part.getMax().get()));
        minText.setText(Integer.toString(this.part.getMin().get()));
        sourceText.setText(getSource);
        idText.setText(Integer.toString(partID));
       }
     else {
                
       this.part = inventory.getInventoryParts().get(partIndex);
       int partID = selectedPart.getPartID().get();
       System.out.println(partID);
       this.osPart = (OutsourcedPart) selectedPart;
       String getSource = this.osPart.getOutsourcedSource().get();
       
        addPartInhouse.setSelected(false);
        addPartOutsourced.setSelected(true);
        nameText.setText(this.part.getName().get());
        invText.setText(Integer.toString(this.part.getInv().get()));
        priceText.setText(Double.toString(this.part.getPrice().get()));
        maxText.setText(Integer.toString(this.part.getMax().get()));
        minText.setText(Integer.toString(this.part.getMin().get()));
        sourceText.setText(getSource);
        idText.setText(Integer.toString(partID));
         
     }
     }
      
}
