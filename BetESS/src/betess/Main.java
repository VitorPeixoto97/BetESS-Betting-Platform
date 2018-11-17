/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import business.BetESS;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import presentation.Login;

/**
 *
 * @author vitorpeixoto
 */
public class Main extends Application implements Serializable{


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BetESS betess = new BetESS();
        betess = betess.populate();
        betess.save();
        betess = betess.load();
        Login form = new Login(betess);
        form.setVisible(true);
        
    }
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
