/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.model;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author flavius8
 */
public class Product {
    
    private IntegerProperty productID;
    private StringProperty name;
    private IntegerProperty inv;
    private DoubleProperty price;
    private IntegerProperty min;
    private IntegerProperty max; 
    private static ObservableList<Part> currentParts = FXCollections.observableArrayList();


 
 public Product(){
 productID = new SimpleIntegerProperty();
 name = new SimpleStringProperty();
 inv = new SimpleIntegerProperty();
 price = new SimpleDoubleProperty();
 min = new SimpleIntegerProperty();      
 max = new SimpleIntegerProperty();

     
 }
 
 public IntegerProperty  getProductID(){
     return productID;
 }
 
 public StringProperty getName(){
     return name;
 }
 
 public IntegerProperty getInv(){
     return inv;
 }
 
 public DoubleProperty getPrice(){
     return price;
 }
 
 public IntegerProperty getMin(){
     return min;
 }
    
 public IntegerProperty getMax(){
     return max;
 }
 
 public ObservableList getCurrentParts(){
    return currentParts;
 }

 public static void setCurrentParts(ObservableList currentPart){
     currentParts.addAll(currentPart);
 }
  public static void removeCurrentParts(Part currentPart){
     currentParts.remove(currentPart);
 }
  public static void modifyCurrentParts(ObservableList<Part> newPartList){
      //currentParts.clear();
      newPartList.forEach((part) -> {
      System.out.println("Entered ModifyCurrentParts " + part);
      // currentParts.addAll(currentPart);
      currentParts.add(part);
      });
 } 

 public void setProductID(int num){
     this.productID.set(num);
 }
 
 public void setName(String item){
     this.name.set(item);
 }
 
 public void setInv(int num){
     this.inv.set(num);
 }
 
 public void setPrice(Double num){
     this.price.set(num);
 }
 
 public void setMin(int num){
     this.min.set(num);
 }
 
 public void setMax(int num){
     this.max.set(num);
 }
 
 
}



