/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fb_inventory.model;
import javafx.beans.property.*;
/**
 *
 * @author flavius8
 */
public abstract class Part {
    
    private IntegerProperty partID;
    private StringProperty name;
    private IntegerProperty inv;
    private DoubleProperty price;
    private IntegerProperty min;
    private IntegerProperty max; 


 /*   
 public Part(int partID, String name, int inv, double price, int min, int max){
 this.partID = partID;
 this.name = name;
 this.inv = inv;
 this.price = price;
 this.min = min;
 this.max = max;
 }
 */
 public Part(){
 partID = new SimpleIntegerProperty();
 name = new SimpleStringProperty();
 inv = new SimpleIntegerProperty();
 price = new SimpleDoubleProperty();
 min = new SimpleIntegerProperty();      
 max = new SimpleIntegerProperty();
     
 }
 
 public IntegerProperty  getPartID(){
     return partID;
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
 

 
 public void setPartID(int num){
     this.partID.set(num);
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



