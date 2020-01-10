package tictactoe.client.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tictactoe.client.App;
import static javafx.application.Application.launch;

/**
 *
 * @author KeR
 */
public class InvitationScreen extends StackPane {

 
    public  InvitationScreen(App app) {

        Region rec = new Region();
        rec.setPrefSize(498, 460);
        rec.setId("rec");

        Region over = new Region();
        over.setId("over");
        over.setPrefSize(130, 130);

        DropShadow e = new DropShadow();
        e.setOffsetX(0.0f);
        e.setOffsetY(4.0f);
        e.setBlurType(BlurType.GAUSSIAN);
        e.setColor(Color.BLACK);
        String str = new String("Kareem");

        Button lose = new Button(str + " Challenges you");
        lose.setPrefSize(250, 20);

        lose.setId("lose");
        lose.setEffect(e);
        ToggleButton Acccept = new ToggleButton("Acccept");
        Acccept.setPrefSize(200, 20);
        Acccept.setId("back");
        ToggleButton Decline = new ToggleButton("Decline");
        Decline.setPrefSize(200, 20);
        Decline.setId("playAgain");
        HBox buttonBox = new HBox(20, Acccept, Decline);

        VBox vbox = new VBox(100, lose, buttonBox);
        vbox.setId("vbox");

        getChildren().addAll(rec, vbox);
        setId("stackInvitation");
    }
}