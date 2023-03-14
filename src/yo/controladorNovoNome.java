package yo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class controladorNovoNome {

    @FXML
    private TextField username;

    public void submit(ActionEvent ae) throws IOException {
        String nome = username.getText();
        FileWriter myObj = new FileWriter("username.txt");
        myObj.write(nome);
        myObj.close();

        Main m = new Main();
        m.mudarScene("menu.fxml");
    }

}
