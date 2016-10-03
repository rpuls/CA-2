/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enitity;

import javax.persistence.Persistence;

/**
 *
 * @author rasmus
 */
public class createscheme {
    public static void main(String[] args) {
        
        Persistence.generateSchema("remote", null);
    }
    
}
