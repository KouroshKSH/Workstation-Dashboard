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
import java.io.File;
import java.io.FileWriter;

public class AddNewPersonelByManager
{
    String[] stringPossiblePositions = {"manager", "seller1", "seller2", "seller3", "keeper1", "keeper2"};  //for checking the input from the texField
    String[] stringPossiblePayments = {"fix", "mix", "profit", "hourly"};   //for checking the input from paymentTypeField

    public AddNewPersonelByManager()
    {
        Stage stageCurrent = new Stage ();
        VBox layout = new VBox (30);
        stageCurrent.setTitle ("Add a new employee");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);

        Label labelPosition = new Label ("Position");
        TextField textFieldPosition = new TextField ();
        textFieldPosition.setPromptText ("manager/seller1/seller2/seller3/keeper1/keeper2");
        textFieldPosition.setEditable (true);
        textFieldPosition.setPrefWidth (300);
        HBox hBoxPosition = new HBox (10);
        hBoxPosition.getChildren ().addAll (labelPosition, textFieldPosition);

        Label labelUsername = new Label ("Username");
        TextField textFieldUsername = new TextField ();
        textFieldUsername.setPromptText ("enter a userame");
        textFieldUsername.setEditable (true);
        textFieldUsername.setPrefWidth (250);
        HBox hBoxUsername = new HBox (10);
        hBoxUsername.getChildren ().addAll (labelUsername, textFieldUsername);

        Label labelFirstName = new Label ("First name");
        TextField textFieldFirstName = new TextField ();
        textFieldFirstName.setPromptText ("enter their firstname");
        textFieldFirstName.setEditable (true);
        HBox hBoxFirstName = new HBox (10);
        textFieldFirstName.setMinWidth (200);
        hBoxFirstName.getChildren ().addAll (labelFirstName, textFieldFirstName);
        Label labelLastName = new Label ("Last name");
        TextField textFieldLastName = new TextField ();
        textFieldLastName.setPromptText ("enter their lastname");
        textFieldLastName.setEditable (true);
        textFieldLastName.setMinWidth (275);
        HBox hBoxLastName = new HBox (10);
        hBoxLastName.getChildren ().addAll (labelLastName, textFieldLastName);
        HBox hBoxNamesFields = new HBox (40);
        hBoxNamesFields.getChildren ().addAll (hBoxFirstName, hBoxLastName);

        Label labelSetPassword = new Label ("Set password");
        PasswordField passwordFieldSet = new PasswordField ();
        passwordFieldSet.setPromptText ("enter a password");
        passwordFieldSet.setEditable (true);
        HBox hBoxSetPassword = new HBox (20);
        hBoxSetPassword.getChildren ().addAll (labelSetPassword, passwordFieldSet);
        Label labelConfirmPassword = new Label ("Confirm password");
        PasswordField passwordFieldConfirm = new PasswordField ();
        passwordFieldConfirm.setPromptText ("re-enter the password");
        passwordFieldConfirm.setEditable (true);
        HBox hBoxConfirmPassword = new HBox (20);
        hBoxConfirmPassword.getChildren ().addAll (labelConfirmPassword, passwordFieldConfirm);
        HBox hBoxPassword = new HBox (50);
        hBoxPassword.getChildren ().addAll (hBoxSetPassword, hBoxConfirmPassword);

        Label labelPersonalID = new Label ("Personal ID");
        TextField textFieldPersonalID = new TextField ();
        textFieldPersonalID.setPromptText ("enter their personal ID");
        textFieldPersonalID.setEditable (true);
        HBox hBoxPersonalID = new HBox (10);
        hBoxPersonalID.getChildren ().addAll (labelPersonalID, textFieldPersonalID);
        Label labelJobID = new Label ("Job ID");
        TextField textFieldJobID = new TextField ();
        textFieldJobID.setPromptText ("enter their job ID (i.e 001, 002 etc...)");
        textFieldJobID.setEditable (true);
        HBox hBoxJobID = new HBox (10);
        hBoxJobID.getChildren ().addAll (labelJobID, textFieldJobID);
        HBox hBoxBothIDs = new HBox (40);
        hBoxBothIDs.getChildren ().addAll (hBoxPersonalID, hBoxJobID);

        Label labelPaymentType = new Label ("Payment type");
        TextField textFieldPaymentType = new TextField ();
        textFieldPaymentType.setPromptText ("enter their payment type (fix, mix, profit, hourly)");
        textFieldPaymentType.setEditable (true);
        HBox hBoxPaymentType = new HBox (10);
        hBoxPaymentType.getChildren ().addAll (labelPaymentType, textFieldPaymentType);
        Label labelPaymentValue = new Label ("Payment value");
        TextField textFieldPaymentValue = new TextField ();
        textFieldPaymentValue.setPromptText ("enter their paymet value ($)");
        textFieldPaymentValue.setEditable (true);
        HBox hBoxPaymentValue = new HBox (10);
        hBoxPaymentValue.getChildren ().addAll (labelPaymentValue, textFieldPaymentValue);
        HBox hBoxPayments = new HBox (40);
        hBoxPayments.getChildren ().addAll (hBoxPaymentType, hBoxPaymentValue);

        Label labelBirth = new Label ("Birth date");
        TextField textFieldBirth = new TextField ();
        textFieldBirth.setPromptText ("enter their birthdate");
        textFieldBirth.setEditable (true);
        HBox hBoxBirth = new HBox (10);
        hBoxBirth.getChildren ().addAll (labelBirth, textFieldBirth);

        Label labelEducation = new Label ("Degree");
        TextField textFieldEducation = new TextField ();
        textFieldEducation.setPromptText ("enter their degree");
        textFieldEducation.setEditable (true);
        textFieldEducation.setPrefWidth (200);
        HBox hBoxEducation = new HBox (10);
        hBoxEducation.getChildren ().addAll (labelEducation, textFieldEducation);

        Button buttonAdd = new Button ("Add");
        buttonAdd.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                if (textFieldPosition.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Position can't be empty");
                    return;
                }
                boolean flagForPosition = true;
                for (int i = 0; i < stringPossiblePositions.length; i++)
                {
                    if (stringPossiblePositions[i].equals (textFieldPosition.getText ()))
                    {
                        flagForPosition = true;
                        break;
                    } else
                    {
                        flagForPosition = false;
                    }
                }
                if (!(flagForPosition))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Position isn't valid");
                    return;
                }
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
                if (passwordFieldSet.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Password can't be empty");
                    return;
                }
                if (!(passwordFieldSet.getText ().equals (passwordFieldConfirm.getText ())))
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
                if (textFieldJobID.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Job ID can't be empty");
                    return;
                }
                if (textFieldJobID.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Job ID can't contain letters");
                    return;
                }
                if (textFieldPaymentType.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Payment type can't be empty");
                    return;
                }
                boolean flagForPaymentType = true;
                for (int i = 0; i < stringPossiblePayments.length; i++)
                {
                    if (stringPossiblePayments[i].equals (textFieldPaymentType.getText ()))
                    {
                        flagForPaymentType = true;
                        break;
                    } else
                    {
                        flagForPaymentType = false;
                    }
                }
                if (!(flagForPaymentType))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Payment type isn't valid");
                    return;
                }
                if (textFieldBirth.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Birth date can't be empty");
                    return;
                }
                if (textFieldPaymentValue.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Payment value can't be empty");
                    return;
                }
                if (textFieldPaymentValue.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Payment value can't contain letters");
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

                AddNewPerson (stageCurrent, textFieldPosition.getText (), textFieldUsername.getText (),
                        textFieldFirstName.getText (), textFieldLastName.getText (),
                        passwordFieldConfirm.getText (), textFieldPersonalID.getText (), textFieldJobID.getText (),
                        textFieldPaymentType.getText (), textFieldPaymentValue.getText (),
                        textFieldBirth.getText (), textFieldEducation.getText ());
            }
        });
        Button buttonCancel = new Button ("Cancel");
        buttonCancel.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ExitPromptAsking(stageCurrent);
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonAdd, buttonCancel);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);

        layout.getChildren ().addAll (hBoxPosition, hBoxUsername, hBoxNamesFields, hBoxBothIDs,
                hBoxPassword, hBoxPayments, hBoxBirth, hBoxEducation,
                hBoxButtons);
        layout.setPadding (new Insets (30, 30, 30, 30));
        Scene sceneCurrent = new Scene (layout, 1050, 530);
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

    private void AddNewPerson(Stage stage, String position, String username,
                              String firstname, String lastname,
                              String password, String personalID, String jobID,
                              String paymentType, String paymentValue,
                              String birthdate, String degree)
    {
        try
        {
            File fileNew = new File ("personelData.txt");
            FileWriter fileWriter = new FileWriter (fileNew, true); //makes it so that it appends the new person to the personel file
            fileWriter.write ("\n");    //makes it so that the new info get appended after a line break
            fileWriter.write (position + " " + username + " " +
                    firstname + " " + lastname + " " +
                    password + " " + personalID + " " + jobID + " " +
                    paymentType + " " + paymentValue + " " +
                    birthdate + " " + degree);
            fileWriter.close ();
        } catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println ("Changes saved to file successfuly!");
        stage.close ();
    }
}
