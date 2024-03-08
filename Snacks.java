
package com.mycompany.apui;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
/**
 *
 * @author day31
 */
@Entity
public class Snacks implements java.io.Serializable {
   @Id
   @Column(name="Food")
   private String food;
   @Column(name="Drinks")
   private String drinks;

    public Snacks(String food, String drinks) {
        this.food = food;
        this.drinks = drinks;
    }
   
 public Snacks(){}
 
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }
}
