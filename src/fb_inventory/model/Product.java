/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.model;
import javafx.beans.property.*;
import javafx.collections.*;

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
    private ObservableList<Part> currentParts;


 
 public Product(){
 productID = new SimpleIntegerProperty();
 name = new SimpleStringProperty();
 inv = new SimpleIntegerProperty();
 price = new SimpleDoubleProperty();
 min = new SimpleIntegerProperty();      
 max = new SimpleIntegerProperty();
 currentParts = FXCollections.observableArrayList();

     
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

 public void setCurrentParts(ObservableList<Part> currentPart){
     currentPart.forEach((part)-> {
         currentParts.add(part);
         System.out.println("Another Part Added");
     });
    // currentParts.add(currentPart);
 }
  public void removeCurrentParts(Part currentPart){
     currentParts.remove(currentPart);
 }
/*  public static void modifyCurrentParts(ObservableList<Part> newPartList){
      //currentParts.clear();
      newPartList.forEach((part) -> {
      System.out.println("Entered ModifyCurrentParts " + part);
      // currentParts.addAll(currentPart);
      currentParts.add(part);
      });
 } */
  
  public void modifyCurrentParts(ObservableList<Part> currentPartList){
      //this.currentParts = currentPartList;
      this.currentParts.setAll(currentPartList);
      System.out.println("Current parts in the products class: " + this.currentParts);
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



