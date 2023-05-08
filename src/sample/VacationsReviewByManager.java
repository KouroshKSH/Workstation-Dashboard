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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VacationsReviewByManager
{
    TableView<VacationInfoClass> tableOfRequests;
    VBox layout;

    public VacationsReviewByManager()
    {
        Stage stageCurrent = new Stage ();
        stageCurrent.setTitle ("List of vacation requests' window");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);
        layout = new VBox (40);
        TableColumn<VacationInfoClass, String> colPosition = new TableColumn<> ("Position");
        colPosition.setMaxWidth (150);
        colPosition.setCellValueFactory (new PropertyValueFactory<> ("position"));
        TableColumn<VacationInfoClass, String> colUsername = new TableColumn<> ("Username");
        colUsername.setMaxWidth (200);
        colUsername.setCellValueFactory (new PropertyValueFactory<> ("username"));
        TableColumn<VacationInfoClass, String> colFirstname = new TableColumn<> ("Firstname");
        colFirstname.setMaxWidth (250);
        colFirstname.setCellValueFactory (new PropertyValueFactory<> ("firstname"));
        TableColumn<VacationInfoClass, String> colLastname = new TableColumn<> ("Lastname");
        colLastname.setMaxWidth (300);
        colLastname.setCellValueFactory (new PropertyValueFactory<> ("lastname"));
        TableColumn<VacationInfoClass, String> colJobID = new TableColumn<> ("Job ID");
        colJobID.setMaxWidth (180);
        colJobID.setCellValueFactory (new PropertyValueFactory<> ("jobID"));
        TableColumn<VacationInfoClass, String> colPaymentType = new TableColumn<> ("Payment type");
        colPaymentType.setMaxWidth (150);
        colPaymentType.setCellValueFactory (new PropertyValueFactory<> ("paymentType"));
        TableColumn<VacationInfoClass, String> colPaymentValue = new TableColumn<> ("Payment Value");
        colPaymentValue.setMaxWidth (130);
        colPaymentValue.setCellValueFactory (new PropertyValueFactory<> ("paymentValue"));
        TableColumn<VacationInfoClass, String> colDaysOff = new TableColumn<> ("Days-off");
        colDaysOff.setMaxWidth (100);
        colDaysOff.setCellValueFactory (new PropertyValueFactory<> ("daysOff"));
        tableOfRequests = new TableView<> ();
        tableOfRequests.setItems (getVacationInfo ());
        tableOfRequests.getColumns ().addAll (colPosition, colUsername,
                colFirstname, colLastname, colJobID,
                colPaymentType, colPaymentValue, colDaysOff);

        Button buttonRemove = new Button ("Remove");
        buttonRemove.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                ObservableList<VacationInfoClass> selectedRequests, allRequests;
                allRequests = tableOfRequests.getItems ();
                selectedRequests = tableOfRequests.getSelectionModel ().getSelectedItems ();
                selectedRequests.forEach (allRequests::remove);
                DeleteClickedItems (allRequests);
            }
        });
        Button buttonAcceptClose = new Button ("Accept and close");
        buttonAcceptClose.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ExitPromptAsking(stageCurrent);
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonRemove, buttonAcceptClose);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);
        layout.getChildren ().addAll (tableOfRequests, hBoxButtons);
        layout.setAlignment (Pos.CENTER);
        Scene sceneCurrent = new Scene (layout, 900, 600);
        stageCurrent.setScene (sceneCurrent);
        stageCurrent.showAndWait ();
    }

    public void DeleteClickedItems(ObservableList<VacationInfoClass> allRequests)
    {
        String[] editedTable = new String[allRequests.size ()];
        for (int i = 0; i < allRequests.size (); i++)
        {
            editedTable[i] = allRequests.get (i).getPosition () + " " +
                    allRequests.get (i).getUsername () + " " +
                    allRequests.get (i).getFirstname () + " " +
                    allRequests.get (i).getLastname () + " " +
                    allRequests.get (i).getJobID () + " " +
                    allRequests.get (i).getPaymentType () + " " +
                    allRequests.get (i).getPaymentValue () + " " +
                    allRequests.get (i).getDaysOff ();
        }
        try
        {
            File fileNew = new File ("vacationRequests.txt");
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

    public ObservableList<VacationInfoClass> getVacationInfo()
    {
        ObservableList<VacationInfoClass> requests= FXCollections.observableArrayList ();
        String[] stringArray = null;
        List<String> listStringItems = new ArrayList<String> ();
        try
        {
            FileInputStream fstreamItems = new FileInputStream("vacationRequests.txt");
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
        String[][] tableOfData = new String[stringArray.length][8];
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
            requests.add (new VacationInfoClass (tableOfData[i][0], tableOfData[i][1], tableOfData[i][2],
                    tableOfData[i][3], tableOfData[i][4], tableOfData[i][5], tableOfData[i][6], tableOfData[i][7]));
        }
        return requests;
    }
}
