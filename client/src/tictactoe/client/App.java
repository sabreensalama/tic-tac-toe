package tictactoe.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tictactoe.client.gui.*;

public class App extends Application {

    HashMap<String, Pane> screens = new HashMap<>();
    Scene mainScene;
    private Socket s;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private JsonHandler jsonHandler;

    public App() {
        jsonHandler = new JsonHandler(this);
        try {
            s = new Socket(Config.SERVER_IP, Config.PORT);
            dataInputStream = new DataInputStream(s.getInputStream());
            dataOutputStream = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        String line = dataInputStream.readUTF();
                        if (line != null) {
                            JsonObject obj = JsonParser.parseString(line).getAsJsonObject();
                            System.out.println(obj);
                            jsonHandler.handle(obj);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    private void addScreens() {
        screens.put("signin", new SigninScreen(this));
        screens.put("signup", new SignupScreen(this));
        screens.put("hardLuck", new HardLuckScreen(this));
        screens.put("main", new MainScreen(this));
    }

    public void setScreen(String screenName) {
        mainScene.setRoot(screens.get(screenName));
    }
    
    public void showAlert(String title, String msg){
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle(title);
                        a.setHeaderText("");
                        a.setContentText(msg);
                        a.show();
                    }
                });
        
    }
    
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setOnCloseRequest(e->{
            Platform.exit();
            System.exit(0);
        });
        addScreens();
        primaryStage.setTitle("TIC TAC TOE!");

        mainScene = new Scene(screens.get("signin"), 1350, 700);
        mainScene.getStylesheets().add(getClass().getResource("/css/style.css").toString());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
