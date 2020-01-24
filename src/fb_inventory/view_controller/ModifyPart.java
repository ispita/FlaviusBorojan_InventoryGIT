/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.view_controller;

import fb_inventory.model.Part;
import fb_inventory.model.InhousePart;
import fb_inventory.model.OutsourcedPart;
import static fb_inventory.model.Inventory.getInventoryParts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
    
   
    public void initialize(URL url, ResourceBundle rb) {
       //InhousePart ihPart = (InhousePart) part;
      
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
private void handleModifyPartsSave(ActionEvent e){
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
    Stage stage = (Stage) saveButton.getScene().getWindow();
    stage.close();
    
}
@FXML
private void handleCancelButton(ActionEvent e){
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
}



     public void setPart(Part selectedPart, int partIndex) {
     if (selectedPart instanceof InhousePart){
        
       this.part = getInventoryParts().get(partIndex);
       int partID = selectedPart.getPartID().get();
       System.out.println(partID);
       //System.out.println(getInventoryParts().get(partID));
       this.ihPart = (InhousePart) selectedPart;
       String getSource = this.ihPart.getInhouseSource().get();
       
       // IDField.setText(new Integer(person.getID()).toString());
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
