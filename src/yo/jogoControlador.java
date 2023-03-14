package yo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class jogoControlador implements Initializable {


    private int contaPalavra = 0;
    private int first = 1;

    private File saveData;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


    @FXML
    public Text segundos;
    @FXML
    private Text wordsPerMin;
    @FXML
    private Text acc;
    @FXML
    private Text programWord;
    @FXML
    private Text secondProgramWord;
    @FXML
    private TextField userWord;
    @FXML
    private ImageView pogChamp;
    @FXML
    private ImageView omegaLul;

    @FXML
    private Button desceProPlay;
    public TextField nomeN;
    public String NOME = null;


    ArrayList<String> palavras = new ArrayList<>();

    public void addToList() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new
                    FileReader("wordsList"));
            String line = reader.readLine();
            while (line != null) {
                palavras.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void submitNome(ActionEvent ae) throws IOException {
        String nome = nomeN.getText();
        String NOME = nome;
        FileWriter myObj = new FileWriter("username.txt");
        myObj.write(nome);
        myObj.close();
    }
    public void toMainMenu(ActionEvent ae) throws IOException {
        Main m = new Main();
        m.mudarScene("menu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        desceProPlay.setVisible(false);
        desceProPlay.setDisable(true);
        segundos.setText("60");
        addToList();
        Collections.shuffle(palavras);
        programWord.setText(palavras.get(contaPalavra));
        secondProgramWord.setText(palavras.get(contaPalavra+1));
        contaPalavra++;


        Date dia = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        saveData = new File("src/data/"+formatter.format(dia).strip()+".txt");

        try {
            if (saveData.createNewFile()) {
                System.out.println("Log criado: " + saveData.getName());
            } else {
                System.out.println("log ja existe burro");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int timer = 5;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (timer > -1) {
                segundos.setText(String.valueOf(timer));
                timer -= 1;
            }

            else {
                if (timer == -1) {
                    userWord.setDisable(true);
                    userWord.setText("GGWP");

                    try {
                        FileWriter myWriter = new FileWriter(saveData);                        
                        myWriter.write(countAll +";");
                        myWriter.write(counter +";");
                        myWriter.write(String.valueOf(countAll-counter));
                        myWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (timer == -4) {
                    desceProPlay.setVisible(true);
                    desceProPlay.setDisable(false);
                    executor.shutdown();
                }

                timer -= 1;
            }
        }
    };

    Runnable fadePog = new Runnable() {
        @Override
        public void run() {
            pogChamp.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pogChamp.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pogChamp.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pogChamp.setOpacity(0);

        }
    };

    Runnable fadeKek = new Runnable() {
        @Override
        public void run() {
            omegaLul.setOpacity(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            omegaLul.setOpacity(50);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            omegaLul.setOpacity(100);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            omegaLul.setOpacity(0);
        }
    };


    private int countAll = 0;
    private int counter = 0;

    public void startGame(KeyEvent ke) {

        if (first == 1) {
            first = 0;
            executor.scheduleAtFixedRate(r, 0, 1, TimeUnit.SECONDS);
        }

        if (ke.getCode().equals(KeyCode.ENTER)) {

            String s = userWord.getText();
            String real = programWord.getText();
            countAll++;

            if (s.equals(real)) {
                counter++;
                wordsPerMin.setText(String.valueOf(counter));

                Thread t = new Thread(fadePog);
                t.start();

            }
            else {
                Thread t = new Thread(fadeKek);
                t.start();
            }
            userWord.setText("");
            acc.setText(String.valueOf(Math.round((counter*1.0/countAll)*100)));
            programWord.setText(palavras.get(contaPalavra));
            secondProgramWord.setText(palavras.get(contaPalavra+1));
            contaPalavra++;
        }

    }
}
