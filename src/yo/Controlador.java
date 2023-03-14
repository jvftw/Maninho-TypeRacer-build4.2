package yo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controlador implements Initializable {

    @FXML
    private Label timeLabel;
    @FXML
    private Label mostrarNome;
    @FXML
    private Text total;
    @FXML
    private Text wpm;
    @FXML
    private Text invalido;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public TextField nomeN;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File newFile = new File("username.txt");
        if (newFile.length() != 0) {
            Scanner reader = null;
            try {
                reader = new Scanner(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String data = reader.nextLine();
            mostrarNome.setText("Bem vindo, "+data);

        }
        Date date = new Date();
        Locale locale = new Locale("en");
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        String strDay = formatter.format(date);

        timeLabel.setText("Hj é " + strDay);

        int[] data = FileHandling.sumUpNumbers("src/data");
        total.setText(String.valueOf(data[0]));
        wpm.setText(String.valueOf(Math.round(data[1]*1.0/data[3])));
        invalido.setText(String.valueOf(data[2]));
    }


    public void playGame(ActionEvent ddd) throws IOException {
        Main m = new Main();

        File newFile = new File("username.txt");
        if (newFile.length() == 0) {
            try {
                m.mudarScene("novoNome.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            m.mudarScene("oJogo.fxml");
        }
        
    }
    public void mudarpraMudarNome(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("MudarNome.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    
    
 }
    
    public void submitNome(ActionEvent ae) throws IOException {
        String nome = nomeN.getText();
        FileWriter myObj = new FileWriter("username.txt");
        myObj.write(nome);
        myObj.close();

    }
}
