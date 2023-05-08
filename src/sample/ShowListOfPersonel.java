package sample;

import javafx.beans.Observable;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShowListOfPersonel
{
    TableView<PersonelPublicInfoClass> tableOfPersonel;
    VBox layout;

    public ShowListOfPersonel()
    {
        Stage stageCurrent = new Stage ();
        stageCurrent.setTitle ("List of personel's window");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);
        layout = new VBox (40);
        TableColumn<PersonelPublicInfoClass, String> colPosition = new TableColumn<> ("Position");
        colPosition.setMaxWidth (300);
        colPosition.setCellValueFactory (new PropertyValueFactory<> ("position"));
        TableColumn<PersonelPublicInfoClass, String> colFirstName = new TableColumn<> ("First name");
        colFirstName.setMaxWidth (600);
        colFirstName.setCellValueFactory (new PropertyValueFactory<> ("firstName"));
        TableColumn<PersonelPublicInfoClass, String> colLastName = new TableColumn<> ("Last name");
        colLastName.setMaxWidth (700);
        colLastName.setCellValueFactory (new PropertyValueFactory<> ("lastName"));
        tableOfPersonel = new TableView<> ();
        tableOfPersonel.setItems (getPersonelPublicInfo ());
        tableOfPersonel.getColumns ().addAll (colPosition, colFirstName, colLastName);
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
        layout.getChildren ().addAll (tableOfPersonel, buttonClose);
        layout.setAlignment (Pos.CENTER);
        Scene sceneCurrent = new Scene (layout, 960, 540);
        stageCurrent.setScene (sceneCurrent);
        stageCurrent.showAndWait ();
    }

    public ObservableList<PersonelPublicInfoClass> getPersonelPublicInfo()
    {
        ObservableList<PersonelPublicInfoClass> personel = FXCollections.observableArrayList ();
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
        //only the position, first and last name will be shown publicly
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
            personel.add (new PersonelPublicInfoClass (tableOfData[i][0], tableOfData[i][2], tableOfData[i][3]));
        }
        return personel;
    }
}
