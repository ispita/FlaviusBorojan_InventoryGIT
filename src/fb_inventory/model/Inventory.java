/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author flavius8
 */
public class Inventory {
    private ObservableList<Part> inventoryParts;
    private ObservableList<Product> inventoryProducts;
   // public static ObservableList<Part> currentParts = FXCollections.observableArrayList();
    private static int partIDNum = 0;
    private static int productIDNum = 0;
    
    public Inventory(){
        this.inventoryParts = FXCollections.observableArrayList();
        this.inventoryProducts  = FXCollections.observableArrayList();
    }
            
            
     public ObservableList<Part> getInventoryParts() {
        return inventoryParts;
    }
     public void addPart(Part part) {
        inventoryParts.add(part);
    }
    
    public Part searchPart(int index){
       return inventoryParts.get(index);
    }
    
     
    public void removePart(int index){
        inventoryParts.remove(index);
    }
    
     public static int incrementPartID(){
         partIDNum = partIDNum + 1;
         return partIDNum;
     }
     
    public ObservableList<Product> getInventoryProducts() {
        return inventoryProducts;
    }
     public void addProduct(Product product) {
        inventoryProducts.add(product);
    }
     
    public void modifyProduct(int index, Product product){
        inventoryProducts.set(index,product);
    }
    
    public void removeProduct(int index){
        inventoryProducts.remove(index);
    }
    
     public static int incrementProductID(){
         productIDNum = productIDNum + 1;
         return productIDNum;
     }
}
