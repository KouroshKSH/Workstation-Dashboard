package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    Stage stage1;

    @Override
    public void start(Stage stageLogin) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage1 = stageLogin;
        stage1.setTitle("Login");
        GridPane gridPane = BuildLoginGridPane();
        AddUI(gridPane);
        Scene scene1 = new Scene (gridPane, 750, 600);
        stage1.setScene(scene1);
        stage1.show();
    }

    private GridPane BuildLoginGridPane()
    {
        GridPane gridPaneLogin = new GridPane ();
        gridPaneLogin.setAlignment (Pos.CENTER);
        gridPaneLogin.setPadding (new Insets (60, 60, 60, 60));
        gridPaneLogin.setHgap (15);
        gridPaneLogin.setVgap (15);
        ColumnConstraints col1 = new ColumnConstraints (100, 100, Double.MAX_VALUE);
        col1.setHalignment (HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints (200, 200, Double.MAX_VALUE);
        col2.setHgrow (Priority.ALWAYS);
        gridPaneLogin.getColumnConstraints ().addAll (col1, col2);
        return gridPaneLogin;
    }

    private void AddUI(GridPane gridPane)
    {
        Label labelHeader = new Label ("Login Form");
        labelHeader.setFont (Font.font ("Arial", FontWeight.BOLD, 45));
        gridPane.add (labelHeader, 0, 0, 2, 1);
        GridPane.setHalignment (labelHeader, HPos.CENTER);
        GridPane.setMargin (labelHeader, new Insets (30, 0, 30, 0));
        Label labelUsername = new Label ("Username : ");
        gridPane.add (labelUsername, 0, 1);
        TextField textFieldUsername = new TextField ();
        textFieldUsername.setPromptText ("enter username...");
        textFieldUsername.setPrefHeight (30);
        gridPane.add (textFieldUsername, 1, 1);
        Label labelPassword = new Label ("Password : ");
        gridPane.add (labelPassword, 0, 2);
        PasswordField textFieldPassword = new PasswordField ();
        textFieldPassword.setPromptText ("enter password...");
        textFieldPassword.setPrefHeight (30);
        gridPane.add (textFieldPassword, 1, 2);
        Button buttonLogin = new Button ("Login");
        buttonLogin.setPrefHeight (30);
        buttonLogin.setDefaultButton (true);
        buttonLogin.setPrefWidth (90);
        gridPane.add (buttonLogin, 0, 4, 2, 1);
        GridPane.setHalignment (buttonLogin, HPos.CENTER);
        GridPane.setMargin (buttonLogin, new Insets (30, 0, 30, 0));
        buttonLogin.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                //show error when username is not given
                if (textFieldUsername.getText ().isEmpty ())
                {
                    ShowAlertLogin (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter your username");
                    return;
                }

                //show error when password is not given
                if (textFieldPassword.getText ().isEmpty ())
                {
                    ShowAlertLogin (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Please enter your password");
                    return;
                }

                //reading from text file
                String[] stringArray = null;
                List<String> listStringItems = new ArrayList<String>();
                try
                {
                    FileInputStream fstreamItems = new FileInputStream("personelData.txt");
                    DataInputStream dataInput = new DataInputStream(fstreamItems);
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(dataInput));
                    String stringLine;
                    while ((stringLine = buffer.readLine()) != null)
                    {
                        stringLine = stringLine.trim();
                        if ((stringLine.length()!=0))
                        {
                            listStringItems.add(stringLine);
                        }
                    }
                    stringArray = (String[])listStringItems.toArray(new String[listStringItems.size()]);
                }catch (Exception e)
                {
                    //catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }
                //how the username file is written (as mentioned in the 'README.txt' file):
                //position|username|firstName|lastName|password|personalID|jobID|paymentType|paymentValue|birthDate|education
                String[][] tableOfData = new String[stringArray.length][11];    //11 is the number of fields
                for (int i = 0; i < tableOfData.length; i++)
                {
                    String singleStringLine = stringArray[i];
                    String[] singleStringArrayLine = singleStringLine.split (" ");
                    for (int j = 0; j < tableOfData[i].length; j++)
                    {
                        tableOfData[i][j] = singleStringArrayLine[j];
                    }
                }

                //show error when the given username doesn't exist in the data bases
                String[] stringArrayOfUsernames = new String[tableOfData.length];
                for (int i = 0; i < tableOfData.length; i++)
                {
                    stringArrayOfUsernames[i] = tableOfData[i][1];
                }
                List<String> listOfUserNames = Arrays.asList (stringArrayOfUsernames);
                if (!(listOfUserNames.contains (textFieldUsername.getText ())))
                {
                    ShowAlertLogin (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "User does not exist");
                    return;
                }

                //show error when the given password does not match the username
                for (int i = 0; i < tableOfData.length; i++)
                {
                    if (textFieldUsername.getText ().equals (tableOfData[i][1]))
                    {
                        if (!(textFieldPassword.getText ().equals (tableOfData[i][4])))
                        {
                            ShowAlertLogin (Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error!", "Password is incorrect");
                            return;
                        }
                    }
                }

                //the correct user and password inputs
                for (int i = 0; i < tableOfData.length; i++)
                {
                    if (textFieldUsername.getText ().equals (tableOfData[i][1]))
                    {
                        if (textFieldPassword.getText ().equals (tableOfData[i][4]))
                        {
                            switch (tableOfData[i][0])
                            {
                                case "manager" :
                                    try
                                    {
                                        new LandingPageManager (stage1, tableOfData[i][1]);
                                    } catch (Exception e)
                                    {
                                        e.printStackTrace ();
                                    }
                                    break;

                                case "seller1" :

                                case "seller2" :

                                case "seller3" :
                                    try
                                    {
                                        new LandingPageSeller (stage1, tableOfData[i][1], tableOfData[i][0]);
                                    } catch (Exception e)
                                    {
                                        e.printStackTrace ();
                                    }
                                    break;

                                case "keeper1" :

                                case "keeper2" :
                                    try
                                    {
                                        new LandingPageKeeper (stage1, tableOfData[i][1], tableOfData[i][0]);
                                    } catch (Exception e)
                                    {
                                        e.printStackTrace ();
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        });
    }

    private void ShowAlertLogin(Alert.AlertType alertType, Window owner, String title, String message)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
