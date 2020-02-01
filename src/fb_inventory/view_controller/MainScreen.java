/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.view_controller;

import fb_inventory.model.Part;
import fb_inventory.model.Product;
import fb_inventory.model.Inventory;
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
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.cell.PropertyValueFactory;

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
   private TableView<Product> productsTable;
   @FXML
   private TableColumn<Product, Integer> productsID;
   @FXML
   private TableColumn<Product, String> productsName;
   @FXML
   private TableColumn<Product, Integer> productsInv;
   @FXML
   private TableColumn<Product, Double> productsPrice;
   @FXML
   private Button exitButton;
   @FXML
   private TextField partSearchField;
   @FXML
   private TextField productSearchField;
   private Inventory inventory;


    @FXML
   private void  handleAddPartsButton(ActionEvent e) throws Exception {
        Stage addPartsStage; 
        Parent addPartsRoot; 
        addPartsStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        addPartsRoot = loader.load();
        Scene addPartsScene = new Scene(addPartsRoot);
        addPartsStage.setScene(addPartsScene);           
        addPartsStage.show();
        AddPart controller = loader.getController();
        controller.setGlobalInventory(inventory);
    
    }
   
       @FXML
   private void  handleAddProductButton(ActionEvent e) throws Exception {
       
        Stage addProductStage; 
        Parent addProductRoot; 
        addProductStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        addProductRoot = loader.load();
        
        AddProduct controller = loader.getController();
        controller.setGlobalInventory(inventory);
        Scene addProductScene = new Scene(addProductRoot);
        addProductStage.setScene(addProductScene);           
        addProductStage.show(); 

    }
   
       @FXML
   public void  handleModifyPartsButton(ActionEvent e) throws Exception {
        Stage modifyPartsStage; 
        Parent modifyPartsRoot; 
        modifyPartsStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        modifyPartsRoot = loader.load();
        Scene scene = new Scene(modifyPartsRoot);
        modifyPartsStage.setScene(scene);
        modifyPartsStage.show(); 
        
        ModifyPart controller = loader.getController();
        Part selectedPart=partsTable.getSelectionModel().getSelectedItem();
        int partIndex = inventory.getInventoryParts().indexOf(selectedPart);
        controller.setPart(selectedPart, partIndex, inventory);

    }
   
       @FXML
   public void  handleModifyProductsButton(ActionEvent e) throws Exception {
        Stage modifyProductsStage; 
        Parent modifyProductsRoot; 
        modifyProductsStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        modifyProductsRoot = loader.load();
      
        
        ModifyProduct controller = loader.getController();
        Product selectedProduct=productsTable.getSelectionModel().getSelectedItem();
        ObservableList selectProductPartsList = selectedProduct.getCurrentParts();
        int productIndex = inventory.getInventoryProducts().indexOf(selectedProduct);
        controller.setProduct(selectedProduct, productIndex, selectProductPartsList, inventory);
        Scene scene = new Scene(modifyProductsRoot);
        modifyProductsStage.setScene(scene);
        modifyProductsStage.show(); 

    }
   @FXML
   public void handleRemoveProductButton(ActionEvent e){
       Product selectedProduct=productsTable.getSelectionModel().getSelectedItem();
       int productIndex = inventory.getInventoryProducts().indexOf(selectedProduct);
       inventory.removeProduct(productIndex);
       
   } 
   
      @FXML
   public void handleRemovePartsButton(ActionEvent e){
       Part selectedPart=partsTable.getSelectionModel().getSelectedItem();
       int partIndex = inventory.getInventoryParts().indexOf(selectedPart);
       inventory.removePart(partIndex);
       
   } 
   
   @FXML
   public void handleSearchPartsButton(ActionEvent e){
      FilteredList<Part> filteredPartList = new FilteredList<>(inventory.getInventoryParts(), p -> true);
      String searchValue = partSearchField.getText();
            filteredPartList.setPredicate(part -> {                              
                if(part.getName().get().contains(searchValue)) {
                    return true; 
                } 
                return false; 
            });
            SortedList<Part> sortedPartList = new SortedList<>(filteredPartList);

       
        sortedPartList.comparatorProperty().bind(partsTable.comparatorProperty());
        partsID.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
        partsName.setCellValueFactory(cellData -> cellData.getValue().getName());
        partsInv.setCellValueFactory(cellData -> cellData.getValue().getInv().asObject());
        partsPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        partsTable.setItems(sortedPartList);
  
   }
   
      @FXML
   public void handleSearchProductsButton(ActionEvent e){
      FilteredList<Product> filteredProductList = new FilteredList<>(inventory.getInventoryProducts(), p -> true);
      String searchValue = productSearchField.getText();
            filteredProductList.setPredicate(product -> {                              
                if(product.getName().get().contains(searchValue)) {
                    return true; 
                } 
                return false; 
            });
            SortedList<Product> sortedProductList = new SortedList<>(filteredProductList);

       
        sortedProductList.comparatorProperty().bind(productsTable.comparatorProperty());
        partsID.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
        partsName.setCellValueFactory(cellData -> cellData.getValue().getName());
        partsInv.setCellValueFactory(cellData -> cellData.getValue().getInv().asObject());
        partsPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
        productsTable.setItems(sortedProductList);
  
   }
   
     public void updatePartsTable() {
       partsTable.setItems(inventory.getInventoryParts());

   }
        
     public void updateProductsTable() {
        productsTable.setItems(inventory.getInventoryProducts());
    }
   @FXML  
   private void handleExitButton(ActionEvent e){
    Stage stage = (Stage) exitButton.getScene().getWindow();
    stage.close();
}
   public void setGlobalInventory(Inventory inventory){
    this.inventory = inventory;
       updateProductsTable();
       updatePartsTable();
}
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    this.inventory = new Inventory();
    partsID.setCellValueFactory(cellData -> cellData.getValue().getPartID().asObject());
    partsName.setCellValueFactory(cellData -> cellData.getValue().getName());
    partsInv.setCellValueFactory(cellData -> cellData.getValue().getInv().asObject());
    partsPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
    productsID.setCellValueFactory(cellData -> cellData.getValue().getProductID().asObject());
    productsName.setCellValueFactory(cellData -> cellData.getValue().getName());
    productsInv.setCellValueFactory(cellData -> cellData.getValue().getInv().asObject());
    productsPrice.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
    updateProductsTable();
    updatePartsTable();
    }    
    
}
