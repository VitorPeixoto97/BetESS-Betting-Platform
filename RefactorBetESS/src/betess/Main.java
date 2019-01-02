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
        
        Data data = new Data();
        try{ data = data.load(); } 
        catch (IOException i){
            data = data.povoar();
            try { data.save(data); } 
            catch (IOException ex) { Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex); }
        }
        BetESS betess = new BetESS(data);
        Login form = new Login(betess);
        form.setVisible(true);
    }
    
    @Override
    public void start(Stage primaryStage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
