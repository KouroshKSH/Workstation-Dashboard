package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MonthlyReportsManager
{
    TableView<WarehouseProductsClass> tableOfProducts;
    VBox layout;

    public MonthlyReportsManager()
    {
        Stage stageCurrent = new Stage ();
        stageCurrent.setTitle ("Monthly Reports");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);
        layout = new VBox (40);
        TableColumn<WarehouseProductsClass, String> colName = new TableColumn<> ("Name");
        colName.setMaxWidth (150);
        colName.setCellValueFactory (new PropertyValueFactory<> ("nameOfProduct"));
        TableColumn<WarehouseProductsClass, String> colPrice= new TableColumn<> ("Price ($)");
        colPrice.setMaxWidth (70);
        colPrice.setCellValueFactory (new PropertyValueFactory<> ("pricePerProduct"));
        TableColumn<WarehouseProductsClass, String> colQuantity= new TableColumn<> ("Quantity");
        colQuantity.setMaxWidth (60);
        colQuantity.setCellValueFactory (new PropertyValueFactory<> ("quantityOfProdcut"));
        TableColumn<WarehouseProductsClass, String> colDateOfLastEntry= new TableColumn<> ("Last entry (D/M/Y)");
        colDateOfLastEntry.setMaxWidth (140);
        colDateOfLastEntry.setCellValueFactory (new PropertyValueFactory<> ("dateOfLastEntry"));
        TableColumn<WarehouseProductsClass, String> colDateOfLastSelling= new TableColumn<> ("Last selling (D/M/Y)");
        colDateOfLastSelling.setMaxWidth (140);
        colDateOfLastSelling.setCellValueFactory (new PropertyValueFactory<> ("dateOfLastSelling"));
        TableColumn<WarehouseProductsClass, String> colCost= new TableColumn<> ("Cost ($)");
        colCost.setMaxWidth (70);
        colCost.setCellValueFactory (new PropertyValueFactory<> ("costPerProduct"));
        TableColumn<WarehouseProductsClass, String> colItemsSold= new TableColumn<> ("Items sold");
        colItemsSold.setMaxWidth (90);
        colItemsSold.setCellValueFactory (new PropertyValueFactory<> ("itemsSoldPerProduct"));
        tableOfProducts = new TableView<> ();
        tableOfProducts.setItems (getWarehouseProducts ());
        tableOfProducts.getColumns ().addAll (colName, colPrice, colQuantity,
                colDateOfLastEntry, colDateOfLastSelling, colCost,
                colItemsSold);
        Label labelTotalEarnings = new Label ("Total amount of earnings : ");
        labelTotalEarnings.setFont (Font.font ("Arial", FontWeight.BOLD, 30));
        Label labelMoney = new Label (CalculateTotalEarnings () + " $");
        labelMoney.setFont (Font.font ("Arial", FontWeight.SEMI_BOLD, 35));
        HBox hBoxEarnings = new HBox (30);
        hBoxEarnings.getChildren ().addAll (labelTotalEarnings, labelMoney);
        hBoxEarnings.setAlignment (Pos.CENTER);
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
        layout.getChildren ().addAll (tableOfProducts, hBoxEarnings, buttonClose);
        layout.setAlignment (Pos.CENTER);
        Scene sceneCurrent = new Scene (layout, 1000, 600);
        stageCurrent.setScene (sceneCurrent);
        stageCurrent.showAndWait ();
    }

    private String CalculateTotalEarnings()
    {
        int total = 0;
        String[] stringArray = null;
        List<String> listStringItems = new ArrayList<String> ();
        try
        {
            FileInputStream fstreamItems = new FileInputStream("warehouseData.txt");
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
        String[][] tableOfData = new String[stringArray.length][7];
        for (int i = 0; i < tableOfData.length; i++)
        {
            String singleStringLine = stringArray[i];
            String[] singleStringArrayLine = singleStringLine.split (" ");
            for (int j = 0; j < tableOfData[i].length; j++)
            {
                tableOfData[i][j] = singleStringArrayLine[j];
            }
        }
        int price = 0, cost = 0, numOfSellings = 0;
        for (int i = 0; i < tableOfData.length; i++)
        {
            price = Integer.valueOf (tableOfData[i][1]);
            cost = Integer.valueOf (tableOfData[i][5]);
            numOfSellings = Integer.valueOf (tableOfData[i][6]);
            total += numOfSellings * (price - cost);
        }
        return String.valueOf (total);
    }

    public ObservableList<WarehouseProductsClass> getWarehouseProducts()
    {
        ObservableList<WarehouseProductsClass> products = FXCollections.observableArrayList ();
        String[] stringArray = null;
        List<String> listStringItems = new ArrayList<String> ();
        try
        {
            FileInputStream fstreamItems = new FileInputStream("warehouseData.txt");
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
        String[][] tableOfData = new String[stringArray.length][7];
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
            products.add (new WarehouseProductsClass (tableOfData[i][0], tableOfData[i][1], tableOfData[i][2],
                    tableOfData[i][3], tableOfData[i][4], tableOfData[i][5], tableOfData[i][6]));
        }
        return products;
    }
}
