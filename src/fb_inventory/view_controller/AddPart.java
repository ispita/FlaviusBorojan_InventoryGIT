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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
/**
 *
 * @author flavius8
 */
public class AddPart implements Initializable {
    
   
    @FXML private RadioButton addPartInhouse;
    @FXML private RadioButton addPartOutsourced;
    @FXML private Label source;
    @FXML private TextField sourceText;
    @FXML private TextField nameText;
    @FXML private TextField invText;
    @FXML private TextField priceText;
    @FXML private TextField maxText;
    @FXML private TextField minText;
    @FXML private Button cancelButton;
    
   @Override
    public void initialize(URL url, ResourceBundle rb){
    System.out.println("initialized add part");
    sourceText.setPromptText("Mach ID");
    source.setText("Machine ID");
    addPartOutsourced.setSelected(false);
    addPartInhouse.setSelected(true);
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
private void handleAddPartsSave(ActionEvent e){
    String source = sourceText.getText();
    String name = nameText.getText();
    Integer inv = Integer.parseInt(invText.getText());
    Double price = Double.parseDouble(priceText.getText());
    Integer max = Integer.parseInt(maxText.getText());
    Integer min = Integer.parseInt(minText.getText());
   
    if (addPartInhouse.isSelected() == true ){
    InhousePart newPart = new InhousePart();
    newPart.setInhouseSource(source);
    newPart.setName(name);
    newPart.setInv(inv);
    newPart.setPrice(price);
    newPart.setMax(max);
    newPart.setMin(min);
    newPart.setPartID(Inventory.incrementPartID());
    Inventory.addPart(newPart);
    System.out.println("You Added Inhouse.");
    
    }
    else{
    OutsourcedPart newPart = new OutsourcedPart();
    newPart.setOutsourcedSource(source);
    newPart.setName(name);
    newPart.setInv(inv);
    newPart.setPrice(price);
    newPart.setMax(max);
    newPart.setMin(min);
    newPart.setPartID(Inventory.incrementPartID());
    Inventory.addPart(newPart);
    System.out.println("You Added Outourced.");
    }
    sourceText.setText("");
    nameText.setText("");
    invText.setText("");
    priceText.setText("");
    maxText.setText("");
    minText.setText("");
    
}
@FXML
private void handleCancelButton(ActionEvent e){
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
}
}
