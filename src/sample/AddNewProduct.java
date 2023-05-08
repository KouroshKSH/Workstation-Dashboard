package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.File;
import java.io.FileWriter;

public class AddNewProduct
{
    public AddNewProduct()
    {
        Stage stageCurrent = new Stage ();
        VBox layout = new VBox (30);
        stageCurrent.setTitle ("Add a new product");
        stageCurrent.initModality (Modality.APPLICATION_MODAL);

        Label labelName = new Label ("Name");
        TextField textFieldName = new TextField ();
        textFieldName.setPromptText ("name of product");
        textFieldName.setEditable (true);
        textFieldName.setPrefWidth (300);
        HBox hBoxName = new HBox (10);
        hBoxName.getChildren ().addAll (labelName, textFieldName);

        Label labelPrice = new Label ("Price");
        TextField textFieldPrice = new TextField ();
        textFieldPrice.setPromptText ("price of product ($)");
        textFieldPrice.setEditable (true);
        textFieldPrice.setPrefWidth (190);
        HBox hBoxPrice = new HBox (10);
        hBoxPrice.getChildren ().addAll (labelPrice, textFieldPrice);

        Label labelQauntity = new Label ("Quantity");
        TextField textFieldQuantity = new TextField ();
        textFieldQuantity.setPromptText ("number of items");
        textFieldQuantity.setEditable (true);
        textFieldQuantity.setPrefWidth (190);
        HBox hBoxQuantity = new HBox (10);
        hBoxQuantity.getChildren ().addAll (labelQauntity, textFieldQuantity);

        Label labelLastEntry = new Label ("Date of entry");
        TextField textFieldLastEntry = new TextField ();
        textFieldLastEntry.setPromptText ("enter the current date (i.e 25042020 or DDMMYYYY)");
        textFieldLastEntry.setEditable (true);
        textFieldLastEntry.setPrefWidth (300);
        HBox hBoxLastEntry = new HBox (10);
        hBoxLastEntry.getChildren ().addAll (labelLastEntry, textFieldLastEntry);
        Label labelLastSelling = new Label ("Date of last selling");
        TextField textFieldLastSelling = new TextField ();
        textFieldLastSelling.setPromptText ("**can't be set before selling it for the first time**");
        textFieldLastSelling.setEditable (false);
        textFieldLastSelling.setPrefWidth (300);
        HBox hBoxLastSelling = new HBox (10);
        hBoxLastSelling.getChildren ().addAll (labelLastSelling, textFieldLastSelling);
        HBox hBoxDates = new HBox (20);
        hBoxDates.getChildren ().addAll (hBoxLastEntry, hBoxLastSelling);

        Label labelCost = new Label ("Cost");
        TextField textFieldCost = new TextField ();
        textFieldCost.setPromptText ("cost of production ($)");
        textFieldCost.setEditable (true);
        textFieldCost.setPrefWidth (150);
        HBox hBoxCost = new HBox (10);
        hBoxCost.getChildren ().addAll (labelCost, textFieldCost);

        Label labelItemsSold = new Label ("Number of sold items");
        TextField textFieldItemsSold = new TextField ();
        textFieldItemsSold.setPromptText ("0   **can't be otherwise**");
        textFieldItemsSold.setEditable (false);
        textFieldItemsSold.setPrefWidth (250);
        HBox hBoxItemsSold = new HBox (10);
        hBoxItemsSold.getChildren ().addAll (labelItemsSold, textFieldItemsSold);

        Button buttonAdd = new Button ("Add");
        buttonAdd.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                if (textFieldName.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Name can't be empty");
                    return;
                }
                if (textFieldName.getText ().contains (" "))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene ().getWindow (), "Error!", "Name can't contain spaces");
                }
                if (textFieldPrice.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Price can't be empty");
                    return;
                }
                if (textFieldPrice.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Price must be a number");
                    return;
                }
                //since the only way to input a negative price is with the '-' character...
                if (textFieldPrice.getText ().contains ("-"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Price can't be negative");
                    return;
                }
                if (textFieldQuantity.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Number of items can't be empty");
                    return;
                }
                if (textFieldQuantity.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Number of items must be a number");
                    return;
                }
                if (textFieldQuantity.getText ().contains ("-"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Number of items can't be negative");
                    return;
                }
                if (textFieldLastEntry.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry can't be empty");
                    return;
                }
                if (textFieldLastEntry.getText ().contains (" "))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry can't contain spaces");
                    return;
                }
                if (textFieldLastEntry.getText ().length () != 8)
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry must be 8 digits long");
                    return;
                }
                if (textFieldLastEntry.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Date of entry can't contain letters");
                    return;
                }
                if (textFieldCost.getText ().isEmpty ())
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Cost can't be empty");
                    return;
                }
                if (textFieldCost.getText ().contains ("-"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Cost can't be negative");
                    return;
                }
                if (textFieldCost.getText ().matches (".*[a-zA-Z]+.*"))
                {
                    ShowAlerEditInfo (Alert.AlertType.ERROR, layout.getScene().getWindow(), "Error!", "Cost must be a number");
                    return;
                }
                //for more info, refer to the README file
                textFieldLastSelling.setText ("00000000");  //as if it hasn't be sold yet
                textFieldItemsSold.setText ("0");   //as if it hasn't been sold yet
                AddProduct (stageCurrent, textFieldName.getText (), textFieldPrice.getText (),
                        textFieldQuantity.getText (), textFieldLastEntry.getText (), textFieldLastSelling.getText (),
                        textFieldCost.getText (), textFieldItemsSold.getText ());
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
        layout.getChildren ().addAll (hBoxName, hBoxPrice,
                hBoxQuantity, hBoxDates,
                hBoxCost, hBoxItemsSold, hBoxButtons);
        layout.setPadding (new Insets (30, 30, 30, 30));
        Scene sceneCurrent = new Scene (layout, 1000, 400);
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

    private void AddProduct(Stage stage, String name, String price,
                            String quantity, String lastEntry, String lastSelling,
                            String cost, String itemsSold)
    {
        try
        {
            File fileNew = new File ("warehouseData.txt");
            FileWriter fileWriter = new FileWriter (fileNew, true);
            fileWriter.write ("\n");
            fileWriter.write (name + " " + price + " " +
                    quantity + " " + lastEntry + " " + lastSelling + " " +
                    cost + " " + itemsSold);
            fileWriter.close ();
        } catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println ("Changes saved to file successfuly!");
        stage.close ();
    }
}
