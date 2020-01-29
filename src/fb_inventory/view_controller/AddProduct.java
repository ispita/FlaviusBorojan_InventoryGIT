/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.view_controller;


import fb_inventory.model.Inventory;
import static fb_inventory.model.Inventory.getInventoryParts;
//import fb_inventory.model.Product.getCurrentParts;
import fb_inventory.model.Part;
import fb_inventory.model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author flavius8
 */
public class AddProduct implements Initializable {

    /**
     * Initializes the controller class.
     */


    
    @FXML private TextField nameText;
    @FXML private TextField invText;
    @FXML private TextField priceText;
    @FXML private TextField maxText;
    @FXML private TextField minText;
    @FXML private Button cancelButton;
    private static ObservableList<Part> tempCurrentParts = FXCollections.observableArrayList();

    @FXML
    private TableView<Part> availablePartsTable;
    @FXML
    private TableColumn<Part, Integer> availablePartsID;
    @FXML
    private TableColumn<Part, String> availablePartsName;
    @FXML
    private TableColumn<Part, Integer> availablePartsInv;
    @FXML
    private TableColumn<Part, Double> availablePartsPrice;
    @FXML
    private TableView<Part> tempCurrentPartsTable;
    @FXML
    private TableColumn<Part, Integer> currentPartsID;
    @FXML
    private TableColumn<Part, String> currentPartsName;
    @FXML
    private TableColumn<Part, Integer> currentPartsInv;
    @FXML
    private TableColumn<Part, Double> currentPartsPrice;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    availablePartsID.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
    availablePartsName.setCellValueFactory(cellData -> cellData.getValue().getName());
    availablePartsInv.setCellValueFactory(cellData -> cellData.getValue().getInv().asObject());
    availablePartsPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
    currentPartsID.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
    currentPartsName.setCellValueFactory(cellData -> cellData.getValue().getName());
    currentPartsInv.setCellValueFactory(cellData -> cellData.getValue().getInv().asObject());
    currentPartsPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
    updateCurrentPartsTable();
    updateAvailablePartsTable();
    }    
    
    public void updateAvailablePartsTable() {
        availablePartsTable.setItems(getInventoryParts());
    }
    
    public void updateCurrentPartsTable() {
        tempCurrentPartsTable.setItems(tempCurrentParts);
    }
    
        @FXML
private void handleAddProductsSave(ActionEvent e){
    
    String name = nameText.getText();
    Integer inv = Integer.parseInt(invText.getText());
    Double price = Double.parseDouble(priceText.getText());
    Integer max = Integer.parseInt(maxText.getText());
    Integer min = Integer.parseInt(minText.getText());
   
    Product newProduct = new Product();
    newProduct.setName(name);
    newProduct.setInv(inv);
    newProduct.setPrice(price);
    newProduct.setMax(max);
    newProduct.setMin(min);
    newProduct.setProductID(Inventory.incrementProductID());
    newProduct.setCurrentParts(tempCurrentParts);
    Inventory.addProduct(newProduct);
    System.out.println("You Added a Product");
    tempCurrentPartsTable.getItems().clear();
    nameText.setText("");
    invText.setText("");
    priceText.setText("");
    maxText.setText("");
    minText.setText("");
    Stage stage = (Stage) cancelButton.getScene().getWindow();
     stage.close();
}
@FXML
private void handleAddPartsSave (ActionEvent e){
     Part selectedPart=availablePartsTable.getSelectionModel().getSelectedItem();
     tempCurrentParts.add(selectedPart);
     


} 

@FXML
private void handleAddPartsDelete (ActionEvent e){
     Part selectedPart=availablePartsTable.getSelectionModel().getSelectedItem();
     tempCurrentParts.remove(selectedPart);
     


} 
@FXML
private void handleCancelButton(ActionEvent e){
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
}
    
}
