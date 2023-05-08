package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SellProductSellers
{
    TableView<WarehouseProductsClass> tableOfProducts;
    VBox layout;

    public SellProductSellers()
    {
        Stage stage1 = new Stage ();
        stage1.initModality (Modality.APPLICATION_MODAL);
        stage1.setTitle ("Sell products");
        layout = new VBox (40);
        Label labelAmount = new Label ("Amount");
        TextField textFieldAmount = new TextField ();
        textFieldAmount.setPromptText ("**enter a valid number based on the selected product**");
        textFieldAmount.setEditable (true);
        textFieldAmount.setPrefWidth (300);
        HBox hBoxAmount = new HBox (20);
        hBoxAmount.getChildren ().addAll (labelAmount, textFieldAmount);
        Label labelDate = new Label ("Date");
        TextField textFieldDate = new TextField ();
        textFieldDate.setPromptText ("**enter today's date as such : DDMMYYYY **");
        textFieldDate.setEditable (true);
        textFieldDate.setPrefWidth (330);
        HBox hBoxDate = new HBox (20);
        hBoxDate.getChildren ().addAll (labelDate, textFieldDate);
        VBox vBoxTop = new VBox (10);
        vBoxTop.setPadding (new Insets (20, 20, 20, 20));
        vBoxTop.getChildren ().addAll (hBoxAmount, hBoxDate);

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

        Button buttonSell = new Button ("Sell");
        buttonSell.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                if (textFieldAmount.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Number of items can't be empty");
                    return;
                }
                if (textFieldAmount.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Number of items must be a number");
                    return;
                }
                if (textFieldAmount.getText ().contains ("-"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Number of items can't be negative");
                    return;
                }
                if (textFieldDate.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry can't be empty");
                    return;
                }
                if (textFieldDate.getText ().contains (" "))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry can't contain spaces");
                    return;
                }
                if (textFieldDate.getText ().length () != 8)
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry must be 8 digits long");
                    return;
                }
                if (textFieldDate.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry can't contain letters");
                    return;
                }
                ObservableList<WarehouseProductsClass> selectedItem;
                selectedItem = tableOfProducts.getSelectionModel ().getSelectedItems ();
                int maxAmount = Integer.valueOf (selectedItem.get (0).getQuantityOfProdcut ());
                int currentAmount = Integer.valueOf (textFieldAmount.getText ());
                if (currentAmount > maxAmount)
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Can't sell more than the limit");
                    return;
                }
                int mark = 0;
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
                    if (tableOfData[i][0].equals (selectedItem.get (0).getNameOfProduct ()))
                    {
                        mark = i;
                        break;
                    }
                }
                SellItemRewriteFile (stage1, mark, selectedItem.get (0).getQuantityOfProdcut (),
                        textFieldAmount.getText (), textFieldDate.getText (),
                        selectedItem.get (0).getItemsSoldPerProduct (),selectedItem);
            }
        });
        Button buttonClose = new Button ("Close");
        buttonClose.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ExitPromptAsking (stage1);
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonSell, buttonClose);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);
        layout.getChildren ().addAll (vBoxTop, tableOfProducts, hBoxButtons);
        layout.setAlignment (Pos.CENTER);
        Scene sceneCurrent = new Scene (layout, 1300, 700);
        stage1.setScene (sceneCurrent);
        stage1.showAndWait ();
    }

    private void SellItemRewriteFile(Stage previousStage, int mark, String originalAmount, String desiredAmount, String enteredDate,
                                     String originalSoldItems, ObservableList<WarehouseProductsClass> selectedItem)
    {
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
        selectedItem.get (0).setQuantityOfProdcut (String.valueOf (Integer.valueOf (originalAmount) - Integer.valueOf (desiredAmount)));
        selectedItem.get (0).setDateOfLastSelling (enteredDate);
        selectedItem.get (0).setItemsSoldPerProduct (String.valueOf (Integer.valueOf (originalSoldItems) + Integer.valueOf (desiredAmount)));
        tableOfData[mark][0] = selectedItem.get (0).getNameOfProduct ();
        tableOfData[mark][1] = selectedItem.get (0).getPricePerProduct ();
        tableOfData[mark][2] = selectedItem.get (0).getQuantityOfProdcut ();
        tableOfData[mark][3] = selectedItem.get (0).getDateOfLastEntry ();
        tableOfData[mark][4] = selectedItem.get (0).getDateOfLastSelling ();
        tableOfData[mark][5] = selectedItem.get (0).getCostPerProduct ();
        tableOfData[mark][6] = selectedItem.get (0).getItemsSoldPerProduct ();
        try
        {
            File fileNew = new File ("warehouseData.txt");
            FileWriter fileWriter = new FileWriter (fileNew.getAbsoluteFile ());
            BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
            for (int i = 0; i < tableOfData.length; i++)
            {
                bufferedWriter.write (tableOfData[i][0] + " " + tableOfData[i][1] + " " +
                        tableOfData[i][2] + " " + tableOfData[i][3] + " " +
                        tableOfData[i][4] + " " + tableOfData[i][5] + " " +
                        tableOfData[i][6]);
                bufferedWriter.newLine ();
            }
            bufferedWriter.close ();
        }catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println ("Changes saved successfuly!");
        previousStage.close ();
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
