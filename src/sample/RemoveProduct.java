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

public class RemoveProduct
{
    TableView<WarehouseProductsClass> tableOfProducts;
    VBox layout;

    public RemoveProduct()
    {
        Stage stageCurrent = new Stage ();
        stageCurrent.setTitle ("List of products' window");
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
        Button buttonRemove = new Button ("Remove");
        buttonRemove.setPrefWidth (100);
        buttonRemove.setPrefHeight (30);
        buttonRemove.setFont (Font.font (15));
        buttonRemove.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                ObservableList<WarehouseProductsClass> selectedProducts, allProducts;
                allProducts = tableOfProducts.getItems ();
                selectedProducts = tableOfProducts.getSelectionModel ().getSelectedItems ();
                selectedProducts.forEach (allProducts::remove);
                DeleteClickedItems (allProducts);
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
        layout.getChildren ().addAll (tableOfProducts, hBoxButtons);
        layout.setAlignment (Pos.CENTER);
        Scene sceneCurrent = new Scene (layout, 900, 600);
        stageCurrent.setScene (sceneCurrent);
        stageCurrent.showAndWait ();
    }

    public void DeleteClickedItems(ObservableList<WarehouseProductsClass> allProducts)
    {
        String[] editedTable = new String[allProducts.size ()];
        for (int i = 0; i < allProducts.size (); i++)
        {
            editedTable[i] = allProducts.get (i).getNameOfProduct () + " " +
                    allProducts.get (i).getPricePerProduct () + " " +
                    allProducts.get (i).getQuantityOfProdcut () + " " +
                    allProducts.get (i).getDateOfLastEntry () + " " +
                    allProducts.get (i).getDateOfLastSelling () + " " +
                    allProducts.get (i).getCostPerProduct () + " " +
                    allProducts.get (i).getItemsSoldPerProduct ();
        }
        try
        {
            File fileNew = new File ("warehouseData.txt");
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
