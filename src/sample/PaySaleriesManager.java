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
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaySaleriesManager
{
    VBox layout;
    TableView<PersonelPrivateInfoClass> tableOfPersonel;
    int saleryManager = 1000,
            salerySeller1 = 100, salerySeller2 = 110, salerySeller3 = 105,
            saleryKeeper1 = 50, saleryKeeper2 = 55;

    public PaySaleriesManager()
    {
        Stage stage = new Stage ();
        stage.setTitle ("Saleries");
        stage.initModality (Modality.APPLICATION_MODAL);
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
        TableColumn<PersonelPrivateInfoClass, String> colJobID = new TableColumn<> ("Job ID");
        colJobID.setMaxWidth (150);
        colJobID.setCellValueFactory (new PropertyValueFactory<> ("jobID"));
        TableColumn<PersonelPrivateInfoClass, String> colPaymentType = new TableColumn<> ("Payment type");
        colPaymentType.setMaxWidth (200);
        colPaymentType.setCellValueFactory (new PropertyValueFactory<> ("paymentType"));
        TableColumn<PersonelPrivateInfoClass, String> colPaymentValue = new TableColumn<> ("Payment Value ($)");
        colPaymentValue.setMaxWidth (150);
        colPaymentValue.setCellValueFactory (new PropertyValueFactory<> ("paymentValue"));
        tableOfPersonel = new TableView<> ();
        tableOfPersonel.setItems (getPersonelPrivateInfo ());
        tableOfPersonel.getColumns ().addAll (colPosition, colUsername,
                colFirstName, colLastName, colJobID,
                colPaymentType, colPaymentValue);
        Button buttonPayClose = new Button ("Pay saleries and close");
        buttonPayClose.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                PayAndClose (stage);
            }
        });
        Text textSaleryInfo = new Text ("managers: 1000 - seller1: 100 - seller2: 110 - seller3: 105 - keeper1: 50 - keeper2: 55 (dollars per month)");
        textSaleryInfo.setFont (Font.font ("Arial", FontPosture.ITALIC, 15));
        HBox hBoxSaleryInfo = new HBox (10);
        hBoxSaleryInfo.getChildren ().addAll (textSaleryInfo);
        hBoxSaleryInfo.setAlignment (Pos.CENTER);
        HBox hBoxBottom = new HBox (10);
        hBoxBottom.setAlignment (Pos.CENTER);
        hBoxBottom.getChildren ().addAll (buttonPayClose);
        layout.setAlignment (Pos.CENTER);
        layout.getChildren ().addAll (tableOfPersonel, hBoxSaleryInfo, hBoxBottom);
        Scene sceneCurrent = new Scene (layout, 1000, 650);
        stage.setScene (sceneCurrent);
        stage.showAndWait ();
    }

    private void PayAndClose(Stage previousStage)
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
        int currentMoney = 0;
        for (int i = 0; i < tableOfData.length; i++)
        {
            currentMoney = Integer.valueOf (tableOfData[i][8]);
            switch (tableOfData[i][0])
            {
                case "manager" :
                    currentMoney += saleryManager;
                    break;
                case "seller1" :
                    currentMoney += salerySeller1;
                    break;
                case "seller2" :
                    currentMoney += salerySeller2;
                    break;
                case "seller3" :
                    currentMoney += salerySeller3;
                    break;
                case "keeper1" :
                    currentMoney += saleryKeeper1;
                    break;
                case "keeper2" :
                    currentMoney += saleryKeeper2;
                    break;
            }
            tableOfData[i][8] = String.valueOf (currentMoney);
        }
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
        previousStage.close ();
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
