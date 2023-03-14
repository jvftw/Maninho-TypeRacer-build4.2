package yo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;

public class controladorMudarNome {

    @FXML
    public TextField nomeA;

    public void submit(ActionEvent ae) throws IOException {
        String nome = nomeA.getText();
        FileWriter myObj = new FileWriter("scores.txt");
        myObj.write(nome + ";");
        myObj.close();

        Main m = new Main();
        m.mudarScene("menu.fxml");
    }

}
