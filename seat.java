/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apui;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class seat implements java.io.Serializable{
     @Id
    @Column(name = "seatLocation")
    private String SeatLocation;

    public seat(String SeatLocation) {
        this.SeatLocation = SeatLocation;
    }

    public String getSeatLocation() {
        return SeatLocation;
    }

    public void setSeatLocation(String SeatLocation) {
        this.SeatLocation = SeatLocation;
    }
     
    
}
