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
public class OutsourcedPart extends Part {
    
    private StringProperty outsourcedSource;
    
    public OutsourcedPart(){
         outsourcedSource = new SimpleStringProperty();
    }
  
  public StringProperty getOutsourcedSource(){
      return outsourcedSource;
  }  
  
  public void setOutsourcedSource(String sourceName){
     this.outsourcedSource.set(sourceName);
 }
    
}
