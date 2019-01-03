package betess;

import business.BetESS;
import data.Data;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import presentation.Login;

public class Main extends Application implements Serializable{

    public static void main(String[] args) {
        
        BetESS betess = new BetESS();
        try{ betess = betess.load(); } 
        catch (IOException i){
            betess = betess.povoar();
            betess.save();
        }
        betess = new BetESS(betess);
        Login form = new Login(betess);
        form.setVisible(true);
    }
    
    @Override
    public void start(Stage primaryStage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
