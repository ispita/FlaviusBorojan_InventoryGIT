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
public class ModifyProduct implements Initializable {

    /**
     * Initializes the controller class.
     */


    
    @FXML private TextField nameText;
    @FXML private TextField invText;
    @FXML private TextField priceText;
    @FXML private TextField maxText;
    @FXML private TextField minText;
    @FXML private TextField idText;
    @FXML private Button cancelButton;
    Product product;
    private ObservableList<Part> tempCurrentPartList = FXCollections.observableArrayList();
    private int selectedProductIndex;
    private Inventory inventory;

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
    private TableView<Part> currentPartsTable;
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
    //updateCurrentPartsTable();
    //updateAvailablePartsTable();
    }    
    
    public void updateAvailablePartsTable() {
        availablePartsTable.setItems(inventory.getInventoryParts());
    }
    
  //  public void updateActualPartsTable() {
   //     availablePartsTable.setItems(getCurrentParts());
  //  }
    
   public void updateCurrentPartsTable() {
        currentPartsTable.setItems(tempCurrentPartList);
    }
    
        @FXML
private void handleModifyProductsSave(ActionEvent e) throws Exception{
    
    Product modifyProduct = new Product();
    String name = nameText.getText();
    Integer inv = Integer.parseInt(invText.getText());
    Double price = Double.parseDouble(priceText.getText());
    Integer max = Integer.parseInt(maxText.getText());
    Integer min = Integer.parseInt(minText.getText());
    Integer productID = Integer.parseInt(idText.getText());
    
    modifyProduct.setName(name);
    modifyProduct.setInv(inv);
    modifyProduct.setPrice(price);
    modifyProduct.setMax(max);
    modifyProduct.setMin(min);
    modifyProduct.setProductID(productID);
  //  product.removeAllCurrentParts();
    //currentPartsTable.getItems().clear();
     //tempCurrentPartList.forEach((part) -> {
     modifyProduct.modifyCurrentParts(tempCurrentPartList);
     //System.out.println(part);
    //});
    inventory.modifyProduct(selectedProductIndex, modifyProduct);
    //updateCurrentPartsTable();
   // Inventory.addProduct(newProduct);




            //System.out.println("this should be empty: " + tempCurrentPartList); 
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
private void handleModifyPartsSave (ActionEvent e){
     Part selectedPart=availablePartsTable.getSelectionModel().getSelectedItem();
     tempCurrentPartList.add(selectedPart);
     currentPartsTable.refresh();
     


} 

@FXML
private void handleModifyPartsDelete (ActionEvent e){
     Part selectedPart=currentPartsTable.getSelectionModel().getSelectedItem();
     tempCurrentPartList.remove(selectedPart);
     


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
    
 
public void setProduct(Product selectedProduct, int productIndex, ObservableList selectProductPartsList, Inventory inventory) {
       this.inventory = inventory;
       this.selectedProductIndex = productIndex;
       //this.product = getInventoryProducts().get(productIndex);
       this.product = selectedProduct;

       int productID = selectedProduct.getProductID().get();
       System.out.println(productID);
       System.out.println("This is the Index: " + productIndex);
       
        nameText.setText(this.product.getName().get());
        invText.setText(Integer.toString(this.product.getInv().get()));
        priceText.setText(Double.toString(this.product.getPrice().get()));
        maxText.setText(Integer.toString(this.product.getMax().get()));
        minText.setText(Integer.toString(this.product.getMin().get()));
        idText.setText(Integer.toString(productID));

        System.out.println("temp parts on open: " + tempCurrentPartList);
        System.out.println("selected product parts list: " + selectProductPartsList);
        tempCurrentPartList.addAll(selectProductPartsList);
        currentPartsTable.setItems(tempCurrentPartList);
         updateCurrentPartsTable();
         updateAvailablePartsTable();
       }

}
