package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitPromptAsking
{
    public ExitPromptAsking (Stage stagePrevious)
    {
        Stage window = new Stage ();
        window.initModality (Modality.APPLICATION_MODAL);
        window.setTitle ("Exiting application");
        Label  labelAskingExit = new Label ("Do you want to exit?");
        labelAskingExit.setFont (Font.font ("Arial", FontWeight.SEMI_BOLD, 25));
        Button buttonYesExit = new Button ("Yes");
        buttonYesExit.setPrefWidth (65);
        buttonYesExit.setPrefHeight (25);
        buttonYesExit.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                window.close ();
                stagePrevious.close ();
                System.out.println ("Application closed successfuly.");
            }
        });
        Button buttonNoExit = new Button ("No");
        buttonNoExit.setPrefWidth (65);
        buttonNoExit.setPrefHeight (25);
        buttonNoExit.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                window.close ();
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonYesExit, buttonNoExit);
        hBoxButtons.setAlignment (Pos.CENTER);
        VBox layout = new VBox (25);
        layout.getChildren ().addAll (labelAskingExit, hBoxButtons);
        layout.setAlignment (Pos.CENTER);
        Scene sceneCurrent = new Scene (layout, 500, 200);
        window.setScene (sceneCurrent);
        window.showAndWait ();
    }

}
