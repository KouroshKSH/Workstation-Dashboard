package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemovePersonelByManager
{
    TableView<PersonelPrivateInfoClass> tableOfPersonel;
    VBox layout;

    public RemovePersonelByManager()
    {
        Stage stageCurrent = new Stage ();
        stageCurrent.setTitle ("List of personel's window");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);
        layout = new VBox (40);
        TableColumn<PersonelPrivateInfoClass, String> colPosition = new TableColumn<> ("Position");
        colPosition.setMaxWidth (300);
        colPosition.setCellValueFactory (new PropertyValueFactory<> ("position"));
        TableColumn<PersonelPrivateInfoClass, String> colUsername = new TableColumn<> ("Username");
        colUsername.setMaxWidth (300);
        colUsername.setCellValueFactory (new PropertyValueFactory<> ("username"));
        TableColumn<PersonelPrivateInfoClass, String> colFirstName = new TableColumn<> ("First name");
        colFirstName.setMaxWidth (600);
        colFirstName.setCellValueFactory (new PropertyValueFactory<> ("firstname"));
        TableColumn<PersonelPrivateInfoClass, String> colLastName = new TableColumn<> ("Last name");
        colLastName.setMaxWidth (700);
        colLastName.setCellValueFactory (new PropertyValueFactory<> ("lastname"));
        TableColumn<PersonelPrivateInfoClass, String> colPassword = new TableColumn<> ("Password");
        colPassword.setMaxWidth (400);
        colPassword.setCellValueFactory (new PropertyValueFactory<> ("password"));
        TableColumn<PersonelPrivateInfoClass, String> colPersonalID = new TableColumn<> ("Personal ID");
        colPersonalID.setMaxWidth (300);
        colPersonalID.setCellValueFactory (new PropertyValueFactory<> ("personalID"));
        TableColumn<PersonelPrivateInfoClass, String> colJobID = new TableColumn<> ("Job ID");
        colJobID.setMaxWidth (150);
        colJobID.setCellValueFactory (new PropertyValueFactory<> ("jobID"));
        TableColumn<PersonelPrivateInfoClass, String> colPaymentType = new TableColumn<> ("Payment type");
        colPaymentType.setMaxWidth (200);
        colPaymentType.setCellValueFactory (new PropertyValueFactory<> ("paymentType"));
        TableColumn<PersonelPrivateInfoClass, String> colPaymentValue = new TableColumn<> ("Payment Value ($)");
        colPaymentValue.setMaxWidth (150);
        colPaymentValue.setCellValueFactory (new PropertyValueFactory<> ("paymentValue"));
        TableColumn<PersonelPrivateInfoClass, String> colBirth = new TableColumn<> ("Birth date");
        colBirth.setMaxWidth (200);
        colBirth.setCellValueFactory (new PropertyValueFactory<> ("birthdate"));
        TableColumn<PersonelPrivateInfoClass, String> colDegree = new TableColumn<> ("Education");
        colDegree.setMaxWidth (400);
        colDegree.setCellValueFactory (new PropertyValueFactory<> ("degree"));
        tableOfPersonel = new TableView<> ();
        tableOfPersonel.setItems (getPersonelPrivateInfo ());
        tableOfPersonel.getColumns ().addAll (colPosition, colUsername,
                colFirstName, colLastName, colPassword,
                colPersonalID, colJobID,
                colPaymentType, colPaymentValue,
                colBirth, colDegree);
        Button buttonRemove = new Button ("Remove");
        buttonRemove.setPrefWidth (100);
        buttonRemove.setPrefHeight (30);
        buttonRemove.setFont (Font.font (15));
        buttonRemove.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                ObservableList<PersonelPrivateInfoClass> selectedPersonel, allPersonel;
                allPersonel = tableOfPersonel.getItems ();
                selectedPersonel = tableOfPersonel.getSelectionModel ().getSelectedItems ();
                selectedPersonel.forEach (allPersonel::remove);
                DeleteClickedItems (allPersonel);
            }
        });
        Button buttonClose = new Button ("Close");
        buttonClose.setPrefHeight (30);
        buttonClose.setPrefWidth (100);
        buttonClose.setFont (Font.font (15));
        buttonClose.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ExitPromptAsking (stageCurrent);
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonRemove, buttonClose);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);
        layout.getChildren ().addAll (tableOfPersonel, hBoxButtons);
        layout.setAlignment (Pos.CENTER);
        Scene sceneCurrent = new Scene (layout, 1000, 650);
        stageCurrent.setScene (sceneCurrent);
        stageCurrent.showAndWait ();
    }

    public void DeleteClickedItems(ObservableList<PersonelPrivateInfoClass> listPersonel)
    {
        String[] editedTable = new String[listPersonel.size ()];
        for (int i = 0; i < editedTable.length; i++)
        {
            editedTable[i] = listPersonel.get (i).getPosition () + " " +
                    listPersonel.get (i).getUsername () + " " +
                    listPersonel.get (i).getFirstname () + " " +
                    listPersonel.get (i).getLastname () + " " +
                    listPersonel.get (i).getPassword () + " " +
                    listPersonel.get (i).getPersonalID () + " " +
                    listPersonel.get (i).getJobID () + " " +
                    listPersonel.get (i).getPaymentType () + " " +
                    listPersonel.get (i).getPaymentValue () + " " +
                    listPersonel.get (i).getBirthdate () + " " +
                    listPersonel.get (i).getDegree ();
        }
        try
        {
            File fileNew = new File ("personelData.txt");
            FileWriter fileWriter = new FileWriter (fileNew.getAbsoluteFile ());
            BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
            for (int i = 0; i < editedTable.length; i++)
            {
                bufferedWriter.write (editedTable[i]);
                if (i != editedTable.length - 1)
                {
                    bufferedWriter.newLine ();
                }
            }
            bufferedWriter.close ();
        }catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println ("Changes saved to file successfuly!");
    }

    public ObservableList<PersonelPrivateInfoClass> getPersonelPrivateInfo()
    {
        ObservableList<PersonelPrivateInfoClass> personel = FXCollections.observableArrayList ();
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
        for (int i = 0; i < tableOfData.length; i++)
        {
            personel.add (new PersonelPrivateInfoClass (tableOfData[i][0], tableOfData[i][1], tableOfData[i][2],
                    tableOfData[i][3], tableOfData[i][4], tableOfData[i][5], tableOfData[i][6],
                    tableOfData[i][7], tableOfData[i][8], tableOfData[i][9], tableOfData[i][10]));
        }
        return personel;
    }
}
