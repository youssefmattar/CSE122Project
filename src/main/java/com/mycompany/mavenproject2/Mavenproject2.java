/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author youssef-mattar
 */
public class Mavenproject2 {

    public static void main(String args[]){
        // Create an instance of mainForm
         mainForm myForm = new mainForm();
        // Ensure the form runs on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Create an instance of mainForm
            // Set the form visible
            myForm.setVisible(true);
        });
        
        
        


    }
}
