package sample;

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

public class IncreaseStockClass
{
    TableView<WarehouseProductsClass> tableOfProducts;
    VBox layout1;
    Stage stage1;

    public IncreaseStockClass()
    {
        stage1 = new Stage ();
        stage1.setTitle ("List of products' window");
        stage1.initModality (Modality.APPLICATION_MODAL);
        layout1 = new VBox (40);
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
        Button buttonIncrease = new Button ("Increase");
        buttonIncrease.setPrefWidth (100);
        buttonIncrease.setPrefHeight (30);
        buttonIncrease.setFont (Font.font (15));
        buttonIncrease.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                ObservableList<WarehouseProductsClass> selectedItem;
                selectedItem = tableOfProducts.getSelectionModel ().getSelectedItems ();
                String nameSelected = selectedItem.get (0).getNameOfProduct ();
                String priceSelected = selectedItem.get (0).getPricePerProduct ();
                String quantitySelected = selectedItem.get (0).getQuantityOfProdcut ();
                String lastEntrySelected = selectedItem.get (0).getDateOfLastEntry ();
                String lastSellingSelected = selectedItem.get (0).getDateOfLastSelling ();
                String costSelected = selectedItem.get (0).getCostPerProduct ();
                String itemsSoldSelected = selectedItem.get (0).getItemsSoldPerProduct ();
                IncreaseQuantity (stage1, nameSelected,
                        priceSelected, quantitySelected,
                        lastEntrySelected, lastSellingSelected,
                        costSelected, itemsSoldSelected);
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
                new ExitPromptAsking (stage1);
            }
        });
        HBox hBoxButtons = new HBox (20);
        hBoxButtons.getChildren ().addAll (buttonIncrease, buttonClose);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);
        layout1.getChildren ().addAll (tableOfProducts, hBoxButtons);
        layout1.setAlignment (Pos.CENTER);
        Scene scene1 = new Scene (layout1, 900, 600);
        stage1.setScene (scene1);
        stage1.showAndWait ();
    }

    VBox layoutNew;
    Stage stageCurrent;

    public void IncreaseQuantity(Stage previousStage, String name, String price,
                                 String quantity, String lastEntry, String lastSelling,
                                 String cost, String soldItems)
    {
        previousStage.close ();
        stageCurrent = new Stage ();
        layoutNew = new VBox (30);
        stageCurrent.setTitle ("Set the desired amount for quantity");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);

        Label labelQauntity = new Label ("Quantity");
        TextField textFieldQuantity = new TextField ();
        textFieldQuantity.setPromptText (quantity + "  **enter a higher number**");
        textFieldQuantity.setEditable (true);
        textFieldQuantity.setPrefWidth (300);
        HBox hBoxQuantity = new HBox (10);
        hBoxQuantity.getChildren ().addAll (labelQauntity, textFieldQuantity);

        Label labelLastEntry = new Label ("Date of current entry");
        TextField textFieldLastEntry = new TextField ();
        textFieldLastEntry.setPromptText ("enter the current date (i.e 25042020 or DDMMYYYY)");
        textFieldLastEntry.setEditable (true);
        textFieldLastEntry.setPrefWidth (350);
        HBox hBoxLastEntry = new HBox (10);
        hBoxLastEntry.getChildren ().addAll (labelLastEntry, textFieldLastEntry);
        Label labelLastSelling = new Label ("Date of last selling");
        TextField textFieldLastSelling = new TextField ();
        textFieldLastSelling.setPromptText (lastSelling);
        textFieldLastSelling.setEditable (false);
        textFieldLastSelling.setPrefWidth (300);
        HBox hBoxLastSelling = new HBox (10);
        hBoxLastSelling.getChildren ().addAll (labelLastSelling, textFieldLastSelling);
        HBox hBoxDates = new HBox (20);
        hBoxDates.getChildren ().addAll (hBoxLastEntry, hBoxLastSelling);

        Label labelName = new Label ("Name");
        TextField textFieldName = new TextField ();
        textFieldName.setPromptText (name);
        textFieldName.setEditable (false);
        textFieldName.setPrefWidth (300);
        HBox hBoxName = new HBox (10);
        hBoxName.getChildren ().addAll (labelName, textFieldName);

        Label labelPrice = new Label ("Price ($)");
        TextField textFieldPrice = new TextField ();
        textFieldPrice.setPromptText (price);
        textFieldPrice.setEditable (false);
        textFieldPrice.setPrefWidth (190);
        HBox hBoxPrice = new HBox (10);
        hBoxPrice.getChildren ().addAll (labelPrice, textFieldPrice);

        Label labelCost = new Label ("Cost ($)");
        TextField textFieldCost = new TextField ();
        textFieldCost.setPromptText (cost);
        textFieldCost.setEditable (false);
        textFieldCost.setPrefWidth (150);
        HBox hBoxCost = new HBox (10);
        hBoxCost.getChildren ().addAll (labelCost, textFieldCost);

        Label labelItemsSold = new Label ("Number of sold items");
        TextField textFieldItemsSold = new TextField ();
        textFieldItemsSold.setPromptText (soldItems);
        textFieldItemsSold.setEditable (false);
        textFieldItemsSold.setPrefWidth (250);
        HBox hBoxItemsSold = new HBox (10);
        hBoxItemsSold.getChildren ().addAll (labelItemsSold, textFieldItemsSold);

        Button buttonSubmit = new Button ("Submit");
        buttonSubmit.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                if (textFieldQuantity.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layoutNew.getScene().getWindow(), "Error!", "Quantity can't be empty");
                    return;
                }
                if (textFieldQuantity.getText ().contains ("-"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layoutNew.getScene().getWindow(), "Error!", "Quantity can't be negative");
                    return;
                }
                if (textFieldQuantity.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layoutNew.getScene().getWindow(), "Error!", "Quantity must be a number");
                    return;
                }
                if (textFieldLastEntry.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layoutNew.getScene().getWindow(), "Error!", "Date of entry can't be empty");
                    return;
                }
                if (textFieldLastEntry.getText ().contains (" "))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layoutNew.getScene().getWindow(), "Error!", "Date of entry can't contain spaces");
                    return;
                }
                if (textFieldLastEntry.getText ().length () != 8)
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layoutNew.getScene().getWindow(), "Error!", "Date of entry must be 8 digits long");
                    return;
                }
                if (textFieldLastEntry.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layoutNew.getScene().getWindow(), "Error!", "Date of entry can't contain letters");
                    return;
                }
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
                int flagForMarking = 0;
                for (int i = 0; i < tableOfData.length; i++)
                {
                    if (tableOfData[i][0].equals (name))
                    {
                        flagForMarking = i;
                        break;
                    }
                }
                tableOfData[flagForMarking][0] = name;
                tableOfData[flagForMarking][1] = price;
                tableOfData[flagForMarking][2] = textFieldQuantity.getText ();
                tableOfData[flagForMarking][3] = textFieldLastEntry.getText ();
                tableOfData[flagForMarking][4] = lastSelling;
                tableOfData[flagForMarking][5] = cost;
                tableOfData[flagForMarking][6] = soldItems;
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
                System.out.println ("Changes saved to file successfuly!");
                stageCurrent.close ();
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
        hBoxButtons.getChildren ().addAll (buttonSubmit, buttonCancel);
        hBoxButtons.setAlignment (Pos.BOTTOM_RIGHT);
        layoutNew.getChildren ().addAll (hBoxQuantity, hBoxDates,
                hBoxName, hBoxPrice,
                hBoxCost, hBoxItemsSold, hBoxButtons);
        layoutNew.setPadding (new Insets (30, 30, 30, 30));
        Scene scene2 = new Scene (layoutNew, 1300, 500);
        stageCurrent.setScene (scene2);
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
