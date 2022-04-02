
package edd_udrawing_fase2_202002793;

import estructuras.ArbolB;
import gui.Login;

public class App {

    public static void main(String[] args) {
        ArbolB arbolitoB = new ArbolB();
        Login login = new Login(arbolitoB);
         login.setVisible(true);
        login.setLocationRelativeTo(null);        
        
    }
    
}
