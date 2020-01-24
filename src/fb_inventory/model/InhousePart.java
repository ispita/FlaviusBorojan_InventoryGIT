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
public class InhousePart extends Part {
    
    private StringProperty inhouseSource;
    
    public InhousePart(){
         inhouseSource = new SimpleStringProperty();
    }
  
  public StringProperty getInhouseSource(){
      return inhouseSource;
  }  
  
  public void setInhouseSource(String sourceName){
     this.inhouseSource.set(sourceName);
 }
    
}
