package sample;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.ls.LSOutput;

public class EditAccountInfo
{
    String position, userName,
            firstName, lastName,
            password,
            personalID, jobID,
            paymentType, paymentValue,
            birthDate, education;


    public EditAccountInfo(String givenUseName)
    {
        Stage stageCurrent = new Stage ();
        VBox layout = new VBox (30);
        stageCurrent.setTitle ("Edit your account's details");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);

        //reading from text file
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
        //to identify which row of the table is our user
        int flagForMarking = 0;
        for (int i = 0; i < tableOfData.length; i++)
        {
            if (tableOfData[i][1].equals (givenUseName))
            {
                flagForMarking = i;
                break;
            }
        }
        //assign the proper values to each field
        position = tableOfData[flagForMarking][0];
        userName = tableOfData[flagForMarking][1];
        firstName = tableOfData[flagForMarking][2];
        lastName = tableOfData[flagForMarking][3];
        password = tableOfData[flagForMarking][4];
        personalID = tableOfData[flagForMarking][5];
        jobID = tableOfData[flagForMarking][6];
        paymentType = tableOfData[flagForMarking][7];
        paymentValue = tableOfData[flagForMarking][8];
        birthDate = tableOfData[flagForMarking][9];
        education = tableOfData[flagForMarking][10];

        Label labelPosition = new Label ("Position");
        TextField textFieldPosition = new TextField ();
        textFieldPosition.setPromptText (position + "   **can't change**");
        textFieldPosition.setEditable (false);
        textFieldPosition.setPrefWidth (175);
        HBox hBoxPosition = new HBox (10);
        hBoxPosition.getChildren ().addAll (labelPosition, textFieldPosition);

        Label labelUsername = new Label ("Username");
        TextField textFieldUsername = new TextField ();
        textFieldUsername.setPromptText (userName);
        textFieldUsername.setEditable (true);
        textFieldUsername.setPrefWidth (200);
        HBox hBoxUsername = new HBox (10);
        hBoxUsername.getChildren ().addAll (labelUsername, textFieldUsername);

        Label labelFirstName = new Label ("First name");
        TextField textFieldFirstName = new TextField ();
        textFieldFirstName.setPromptText (firstName);
        textFieldFirstName.setEditable (true);
        HBox hBoxFirstName = new HBox (10);
        textFieldFirstName.setMinWidth (175);
        hBoxFirstName.getChildren ().addAll (labelFirstName, textFieldFirstName);
        Label labelLastName = new Label ("Last name");
        TextField textFieldLastName = new TextField ();
        textFieldLastName.setPromptText (lastName);
        textFieldLastName.setEditable (true);
        textFieldLastName.setMinWidth (250);
        HBox hBoxLastName = new HBox (10);
        hBoxLastName.getChildren ().addAll (labelLastName, textFieldLastName);
        HBox hBoxNamesFields = new HBox (40);
        hBoxNamesFields.getChildren ().addAll (hBoxFirstName, hBoxLastName);

        Label labelCurrentPassword = new Label ("Current password");
        PasswordField passwordFieldCurrent = new PasswordField ();
        passwordFieldCurrent.setPromptText (password);
        passwordFieldCurrent.setEditable (false);
        HBox hBoxCurrentPassword = new HBox (10);
        hBoxCurrentPassword.getChildren ().addAll (labelCurrentPassword, passwordFieldCurrent);
        Label labelNewPassword = new Label ("New password");
        PasswordField passwordFieldNew = new PasswordField ();
        passwordFieldNew.setEditable (true);
        HBox hBoxNewPassword = new HBox (20);
        hBoxNewPassword.getChildren ().addAll (labelNewPassword, passwordFieldNew);
        Label labelConfirmPassword = new Label ("Confirm password");
        PasswordField passwordFieldConfirm = new PasswordField ();
        passwordFieldConfirm.setEditable (true);
        HBox hBoxConfirmPassword = new HBox (20);
        hBoxNewPassword.getChildren ().addAll (labelConfirmPassword, passwordFieldConfirm);
        HBox hBoxPassword = new HBox (50);
        hBoxPassword.getChildren ().addAll (hBoxCurrentPassword, hBoxNewPassword, hBoxConfirmPassword);

        Label labelPersonalID = new Label ("Personal ID");
        TextField textFieldPersonalID = new TextField ();
        textFieldPersonalID.setPromptText (personalID);
        textFieldPersonalID.setEditable (true);
        HBox hBoxPersonalID = new HBox (10);
        hBoxPersonalID.getChildren ().addAll (labelPersonalID, textFieldPersonalID);
        Label labelJobID = new Label ("Job ID");
        TextField textFieldJobID = new TextField ();
        textFieldJobID.setPromptText (jobID + "   **can't change**");
        textFieldJobID.setEditable (false);
        HBox hBoxJobID = new HBox (10);
        hBoxJobID.getChildren ().addAll (labelJobID, textFieldJobID);
        HBox hBoxBothIDs = new HBox (40);
        hBoxBothIDs.getChildren ().addAll (hBoxPersonalID, hBoxJobID);

        Label labelPaymentType = new Label ("Payment type");
        TextField textFieldPaymentType = new TextField ();
        textFieldPaymentType.setPromptText (paymentType + "   **can't change**");
        textFieldPaymentType.setEditable (false);
        HBox hBoxPaymentType = new HBox (10);
        hBoxPaymentType.getChildren ().addAll (labelPaymentType, textFieldPaymentType);
        Label labelPaymentValue = new Label ("Payment value");
        TextField textFieldPaymentValue = new TextField ();
        textFieldPaymentValue.setPromptText (paymentValue + "   **can't change**");
        textFieldPaymentValue.setEditable (false);
        HBox hBoxPaymentValue = new HBox (10);
        hBoxPaymentValue.getChildren ().addAll (labelPaymentValue, textFieldPaymentValue);
        HBox hBoxPayments = new HBox (40);
        hBoxPayments.getChildren ().addAll (hBoxPaymentType, hBoxPaymentValue);

        Label labelBirth = new Label ("Birth date");
        TextField textFieldBirth = new TextField ();
        textFieldBirth.setPromptText (birthDate);
        textFieldBirth.setEditable (true);
        HBox hBoxBirth = new HBox (10);
        hBoxBirth.getChildren ().addAll (labelBirth, textFieldBirth);

        Label labelEducation = new Label ("Degree");
        TextField textFieldEducation = new TextField ();
        textFieldEducation.setPromptText (education);
        textFieldEducation.setEditable (true);
        textFieldEducation.setPrefWidth (200);
        HBox hBoxEducation = new HBox (10);
        hBoxEducation.getChildren ().addAll (labelEducation, textFieldEducation);

        Button buttonSave = new Button ("Save");
        int finalFlagForMarking = flagForMarking;
        buttonSave.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                if (textFieldUsername.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Username can't be empty");
                    return;
                }
                if (textFieldFirstName.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "First name can't be empty");
                    return;
                }
                if (textFieldFirstName.getText ().matches (".*\\d.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "First name can't contain numbers");
                    return;
                }
                if (textFieldLastName.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Last name can't be empty");
                    return;
                }
                if (textFieldLastName.getText ().matches (".*\\d.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Last name can't contain numbers");
                    return;
                }
                if (passwordFieldCurrent.getText ().equals (passwordFieldNew.getText ()))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Old and new password are the same");
                    return;
                }
                if (!(passwordFieldNew.getText ().equals (passwordFieldConfirm.getText ())))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "New password isn't confirmed right");
                    return;
                }
                if (textFieldPersonalID.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Personal ID can't be empty");
                    return;
                }
                if (textFieldPersonalID.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Personal ID can't contain letters");
                    return;
                }
                if (textFieldPersonalID.getLength () != 10)
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Personal ID isn't 10 digits long");
                    return;
                }
                if (textFieldBirth.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Birth date can't be empty");
                    return;
                }
                if (textFieldBirth.getLength () != 8)
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Birth date isn't 8 digits long");
                    return;
                }
                if (textFieldBirth.getText ().contains (" "))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Birth date can't contain spaces");
                    return;
                }
                if (textFieldEducation.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Degree can't be empty");
                    return;
                }
                if (textFieldEducation.getText ().contains (" "))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Degree can't contain spaces");
                    return;
                }
                SaveAccountInfo (stageCurrent, finalFlagForMarking, textFieldUsername.getText (),
                            textFieldFirstName.getText (), textFieldLastName.getText (),
                            passwordFieldConfirm.getText (), textFieldPersonalID.getText (),
                            textFieldBirth.getText (), textFieldEducation.getText ());
            }
        });

        Button buttonCancel = new Button ("Cancel");
        buttonCancel.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ExitPromptAsking (stageCurrent);
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonSave, buttonCancel);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);

        layout.getChildren ().addAll (hBoxPosition, hBoxUsername, hBoxNamesFields, hBoxBothIDs,
                    hBoxPassword, hBoxPayments, hBoxBirth, hBoxEducation,
                    hBoxButtons);
        layout.setPadding (new Insets (30, 30, 30, 30));
        Scene sceneCurrent = new Scene (layout, 970, 530);
        stageCurrent.setScene (sceneCurrent);
        stageCurrent.showAndWait ();
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

    private void SaveAccountInfo(Stage currentStage, int flag, String usernameToSave,
                                 String firstnameToSave, String lastnameToSave,
                                 String passwordToSave, String personalIDToSave,
                                 String birthdateToSave, String degreeToSave)
    {
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
        //reassigning the values for the flagged elements
        tableOfData[flag][1] = usernameToSave;
        tableOfData[flag][2] = firstnameToSave;
        tableOfData[flag][3] = lastnameToSave;
        tableOfData[flag][4] = passwordToSave;
        tableOfData[flag][5] = personalIDToSave;
        tableOfData[flag][9] = birthdateToSave;
        tableOfData[flag][10] = degreeToSave;
        //writting the file
        try
        {
            File fileNew = new File ("personelData.txt");
            FileWriter fileWriter = new FileWriter (fileNew.getAbsoluteFile ());
            BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
            for (int i = 0; i < tableOfData.length; i++)
            {
                bufferedWriter.write (tableOfData[i][0] + " " + tableOfData[i][1] + " " +
                        tableOfData[i][2] + " " + tableOfData[i][3] + " " +
                        tableOfData[i][4] + " " + tableOfData[i][5] + " " +
                        tableOfData[i][6] + " " + tableOfData[i][7] + " " +
                        tableOfData[i][8] + " " + tableOfData[i][9] + " " +
                        tableOfData[i][10]);
                bufferedWriter.newLine ();
            }
            bufferedWriter.close ();
        }catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println ("Changes saved to file successfuly!");
        currentStage.close ();
    }
}
