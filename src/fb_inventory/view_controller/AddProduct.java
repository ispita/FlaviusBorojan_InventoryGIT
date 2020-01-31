/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.view_controller;


import fb_inventory.model.Inventory;
import fb_inventory.model.Part;
import fb_inventory.model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ObservableList<Part> tempCurrentParts = FXCollections.observableArrayList();

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
    Inventory inventory;
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
   
    }    
    
    public void updateAvailablePartsTable() {
        availablePartsTable.setItems(inventory.getInventoryParts());
    }
    
    public void updateCurrentPartsTable() {
        tempCurrentPartsTable.setItems(tempCurrentParts);
    }
    
        @FXML
private void handleAddProductsSave(ActionEvent e)throws Exception{
    
    String name = nameText.getText();
    Integer inv = Integer.parseInt(invText.getText());
    Double price = Double.parseDouble(priceText.getText());
    Integer max = Integer.parseInt(maxText.getText());
    Integer min = Integer.parseInt(minText.getText());
   
    Product newProduct = new Product();
    inventory.addProduct(newProduct);
    int testIndex = inventory.getInventoryProducts().indexOf(newProduct);
    newProduct.setName(name);
    newProduct.setInv(inv);
    newProduct.setPrice(price);
    newProduct.setMax(max);
    newProduct.setMin(min);
    newProduct.setProductID(Inventory.incrementProductID());
    ObservableList<Part> test = FXCollections.observableArrayList();
    test.addAll(tempCurrentParts);
    inventory.getInventoryProducts().get(testIndex).setCurrentParts(test);
    
    

    tempCurrentPartsTable.getItems().clear();
    tempCurrentParts.removeAll(tempCurrentParts);
    nameText.setText("");
    invText.setText("");
    priceText.setText("");
    maxText.setText("");
    minText.setText("");
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

public void setGlobalInventory(Inventory inventory){
    this.inventory = inventory;
     updateCurrentPartsTable();
    updateAvailablePartsTable();
}
    
}
