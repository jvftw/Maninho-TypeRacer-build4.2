package yo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primeiroStage) throws Exception{
        stg = primeiroStage;
        primeiroStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primeiroStage.setTitle("Maninho TypeRacer");
        primeiroStage.getIcons().add(new Image("images/icon.png"));
        primeiroStage.setScene(new Scene(root, 600, 450));
        primeiroStage.show();
    }

    public void mudarScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    

    public static void main(String[] args) {
        launch(args);
    }
    
}
