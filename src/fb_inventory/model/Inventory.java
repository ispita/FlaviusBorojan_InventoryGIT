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
    private static ObservableList<Part> inventoryParts = FXCollections.observableArrayList();
  //  private static ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private static int partIDNum = 0;
  //  private static int productIDCount = 0;
    
     public static ObservableList<Part> getInventoryParts() {
        return inventoryParts;
    }
     public static void addPart(Part part) {
        inventoryParts.add(part);
    }
    
     public static int incrementPartID(){
         partIDNum = partIDNum + 1;
         return partIDNum;
     }
}
