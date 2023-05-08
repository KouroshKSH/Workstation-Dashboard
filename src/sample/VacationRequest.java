package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VacationRequest
{
    public VacationRequest(String givenUseName)
    {
        Stage stage = new Stage ();
        stage.setTitle ("Vacation request window");
        VBox layout = new VBox (30);
        stage.initModality (Modality.APPLICATION_MODAL);
        String[] stringArray = null;
        List<String> listStringItems = new ArrayList<String> ();
        try
        {
            FileInputStream fstreamItems = new FileInputStream("personelData.txt");
            DataInputStream dataInput = new DataInputStream(fstreamItems);
            BufferedReader buffer = new BufferedReader(new InputStreamReader (dataInput));
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
            System.err.println("Error: " + e.getMessage());
        }
        String[][] tableOfData = new String[stringArray.length][11];
        for (int i = 0; i < tableOfData.length; i++)
        {
            String singleStringLine = stringArray[i];
            String[] singleStringArrayLine = singleStringLine.split (" ");
            for (int j = 0; j < tableOfData[i].length; j++)
            {
                tableOfData[i][j] = singleStringArrayLine[j];
            }
        }
        int flagForMarking = 0;
        for (int i = 0; i < tableOfData.length; i++)
        {
            if (tableOfData[i][1].equals (givenUseName))
            {
                flagForMarking = i;
                break;
            }
        }
        String position = tableOfData[flagForMarking][0];
        String username = tableOfData[flagForMarking][1];
        String firstname = tableOfData[flagForMarking][2];
        String lastname = tableOfData[flagForMarking][3];
        String jobID = tableOfData[flagForMarking][6];
        String paymentType = tableOfData[flagForMarking][7];
        String paymentValue = tableOfData[flagForMarking][8];

        Label labelPosition = new Label ("Position");
        TextField textFieldPosition = new TextField ();
        textFieldPosition.setPromptText (position);
        textFieldPosition.setEditable (false);
        textFieldPosition.setPrefWidth (175);
        HBox hBoxPosition = new HBox (10);
        hBoxPosition.getChildren ().addAll (labelPosition, textFieldPosition);

        Label labelUsername = new Label ("Username");
        TextField textFieldUsername = new TextField ();
        textFieldUsername.setPromptText (username);
        textFieldUsername.setEditable (false);
        textFieldUsername.setPrefWidth (200);
        HBox hBoxUsername = new HBox (10);
        hBoxUsername.getChildren ().addAll (labelUsername, textFieldUsername);

        Label labelFirstName = new Label ("First name");
        TextField textFieldFirstName = new TextField ();
        textFieldFirstName.setPromptText (firstname);
        textFieldFirstName.setEditable (false);
        HBox hBoxFirstName = new HBox (10);
        textFieldFirstName.setMinWidth (175);
        hBoxFirstName.getChildren ().addAll (labelFirstName, textFieldFirstName);
        Label labelLastName = new Label ("Last name");
        TextField textFieldLastName = new TextField ();
        textFieldLastName.setPromptText (lastname);
        textFieldLastName.setEditable (false);
        textFieldLastName.setMinWidth (250);
        HBox hBoxLastName = new HBox (10);
        hBoxLastName.getChildren ().addAll (labelLastName, textFieldLastName);
        HBox hBoxNamesFields = new HBox (40);
        hBoxNamesFields.getChildren ().addAll (hBoxFirstName, hBoxLastName);

        Label labelJobID = new Label ("Job ID");
        TextField textFieldJobID = new TextField ();
        textFieldJobID.setPromptText (jobID);
        textFieldJobID.setEditable (false);
        HBox hBoxJobID = new HBox (10);
        hBoxJobID.getChildren ().addAll (labelJobID, textFieldJobID);

        Label labelPaymentType = new Label ("Payment type");
        TextField textFieldPaymentType = new TextField ();
        textFieldPaymentType.setPromptText (paymentType);
        textFieldPaymentType.setEditable (false);
        HBox hBoxPaymentType = new HBox (10);
        hBoxPaymentType.getChildren ().addAll (labelPaymentType, textFieldPaymentType);
        Label labelPaymentValue = new Label ("Payment value");
        TextField textFieldPaymentValue = new TextField ();
        textFieldPaymentValue.setPromptText (paymentValue);
        textFieldPaymentValue.setEditable (false);
        HBox hBoxPaymentValue = new HBox (10);
        hBoxPaymentValue.getChildren ().addAll (labelPaymentValue, textFieldPaymentValue);
        HBox hBoxPayments = new HBox (40);
        hBoxPayments.getChildren ().addAll (hBoxPaymentType, hBoxPaymentValue);

        Label labelVacation = new Label ("Vacation");
        TextField textFieldVacation = new TextField ();
        switch (position)
        {
            case "seller1" :
            case "seller2" :
            case "seller3" :
                textFieldVacation.setPromptText ("max is 3 days");
                textFieldVacation.setEditable (true);
                break;
            case "keeper1" :
                textFieldVacation.setPromptText ("can't take a day off");
                textFieldVacation.setEditable (false);
                break;
            case "keeper2" :
                textFieldVacation.setPromptText ("max is 2 days");
                textFieldVacation.setEditable (true);
                break;

        }
        HBox hBoxVacation = new HBox (10);
        hBoxVacation.getChildren ().addAll (labelVacation, textFieldVacation);

        Button buttonRequest = new Button ("Submit");
        buttonRequest.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                if (textFieldVacation.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Vacation can't be empty");
                    return;
                }
                if (textFieldVacation.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Vacation day must be a number");
                    return;
                }
                //for checking the sellers, only need to check the beggining of the string
                if (textFieldPosition.getText ().startsWith ("s"))
                {
                    String daysString = textFieldVacation.getText ();
                    int daysInt = Integer.parseInt (daysString);
                    if (daysInt > 3)
                    {
                        ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Sellers can't submit more than 3 days");
                        return;
                    }
                }
                //for checking the keepers...
                if (textFieldPosition.getText ().startsWith ("k"))
                {
                    String daysString = textFieldVacation.getText ();
                    int daysInt = Integer.parseInt (daysString);
                    if (daysInt > 2)
                    {
                        ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Keepers can't submit more than 2 days");
                        return;
                    }
                }
                SumbitVacationRequest (stage, position, username,
                        firstname, lastname, jobID,
                        paymentType, paymentValue, textFieldVacation.getText ());
            }
        });
        Button buttonCancel = new Button ("Cancel");
        buttonCancel.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ExitPromptAsking (stage);
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonRequest, buttonCancel);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);
        layout.getChildren ().addAll (hBoxPosition, hBoxUsername,
                hBoxFirstName, hBoxLastName,
                hBoxJobID, hBoxPayments,
                hBoxVacation, hBoxButtons);
        layout.setPadding (new Insets (30, 30, 30, 30));
        Scene sceneCurrent = new Scene (layout, 800, 500);
        stage.setScene (sceneCurrent);
        stage.showAndWait ();
    }

    private void SumbitVacationRequest(Stage stage, String position, String username,
                                       String firstname, String lastname, String jobID,
                                       String paymentType, String paymentValue, String numOfDays)
    {
        try
        {
            File fileNew = new File ("vacationRequests.txt");
            FileWriter fileWriter = new FileWriter (fileNew, true);
            fileWriter.write ("\n");
            fileWriter.write (position + " " + username + " " +
                    firstname + " " + lastname + " " +
                    jobID + " " + paymentType + " " + paymentValue + " " +
                    numOfDays);
            fileWriter.close ();
        } catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println ("Changes saved to file successfuly!");
        stage.close ();
    }

    private void ShowAlerEditInfo(Alert.AlertType alertType, Window owner, String title, String message)
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
