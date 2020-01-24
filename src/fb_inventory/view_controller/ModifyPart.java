/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.view_controller;

import fb_inventory.model.Part;
import fb_inventory.model.InhousePart;
import static fb_inventory.model.InhousePart.getInhouseSource;
import fb_inventory.model.OutsourcedPart;
import fb_inventory.model.Inventory;
import static fb_inventory.model.Inventory.getInventoryParts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
/**
 *
 * @author flavius8
 */
public class ModifyPart {
    
   
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
    Part part;
    InhousePart ihPart;
    
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
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
   
    if (sourceText.getPromptText() == "Mach ID"){
    InhousePart newPart = new InhousePart();
    newPart.setInhouseSource(source);
    newPart.setName(name);
    newPart.setInv(inv);
    newPart.setPrice(price);
    newPart.setMax(max);
    newPart.setMax(min);
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
    newPart.setMax(min);
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



     public void setPart(Part part, int partIndex) {
     if (part instanceof InhousePart){
    
       this.part = getInventoryParts().get(partIndex);
       int partID = getInventoryParts().get(partIndex).getPartID().get() - 1;
       System.out.println(partID);
       System.out.println(getInventoryParts().get(partID));
   
       
       // IDField.setText(new Integer(person.getID()).toString());
        nameText.setText(this.part.getName().get());
        invText.setText(Integer.toString(this.part.getInv().get()));
        priceText.setText(Double.toString(this.part.getPrice().get()));
        maxText.setText(Integer.toString(this.part.getMax().get()));
        minText.setText(Integer.toString(this.part.getMin().get()));
        sourceText.setText(getInhouseSource().get());
       }
     }
      
}
