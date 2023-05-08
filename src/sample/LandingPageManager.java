package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.naming.ldap.PagedResultsControl;
import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LandingPageManager
{
    Stage stage2;
    BorderPane borderPaneLayout;

    public LandingPageManager (Stage stage1, String username) throws Exception
    {
        stage1.close ();
        String currentUser = username;  //storing this for later on
        borderPaneLayout = BuildBorderPane ();
        AddUI (borderPaneLayout, currentUser);
        Stage stageLandingPage = new Stage ();
        stage2 = stageLandingPage;
        stage2.setTitle ("Main Program (Manager)");
        stage2.setWidth (1880);
        stage2.setHeight (1040);
        Scene scene = new Scene (borderPaneLayout);
        stage2.setScene (scene);
        stage2.show ();
    }

    private BorderPane BuildBorderPane()
    {
        BorderPane borderPane = new BorderPane ();
        return borderPane;
    }

    private void AddUI(BorderPane borderPane, String currentUser)
    {
        BorderPane borderPaneCurrent = borderPane;
        //creating 'File" menu
        Menu menuFile = new Menu ("_File");
        //creating menu items for 'File'
        Menu openInMenuFile = new Menu ("_Open");
        MenuItem openPersonelFile = new MenuItem ("Per_sonel");
        openPersonelFile.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ShowListOfPersonel ();
            }
        });

        MenuItem openProductsFile = new MenuItem ("Pro_ducts");
        openProductsFile.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ShowListOfProducts ();
            }
        });

        openInMenuFile.getItems ().addAll (openPersonelFile, openProductsFile);

        //the 'Exit' item can be used for everyone
        MenuItem exitProgram = new MenuItem ("_Exit");
        exitProgram.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new ExitPromptAsking (stage2);
            }
        });

        menuFile.getItems ().addAll (openInMenuFile, exitProgram);

        Menu menuEdit = new Menu ("_Edit");
        //creating menu items for 'Edit'
        Menu editPersonelMenuEdit = new Menu ("Edit per_sonel");
        MenuItem editPersonalInfoPersonel = new MenuItem ("_Personal account");
        editPersonalInfoPersonel.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new EditAccountInfo (currentUser);
            }
        });
        MenuItem seperatorEditMenu = new SeparatorMenuItem ();
        MenuItem addNewPersonel = new MenuItem ("_Add a new employee");
        addNewPersonel.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new AddNewPersonelByManager();
            }
        });
        MenuItem removePersonel = new MenuItem ("_Remove an employee");
        removePersonel.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new RemovePersonelByManager();
            }
        });
        editPersonelMenuEdit.getItems ().addAll (editPersonalInfoPersonel,seperatorEditMenu,
                addNewPersonel, removePersonel);
        Menu editProductMenuEdit = new Menu ("Pro_ducts");
        MenuItem increaseStock = new MenuItem ("_Increase stock");
        increaseStock.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new IncreaseStockClass ();
            }
        });
        MenuItem addNewProduct = new MenuItem ("_Add a new product");
        addNewProduct.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new AddNewProduct();
            }
        });
        MenuItem seperatorProducts = new SeparatorMenuItem ();
        MenuItem removeProduct = new MenuItem ("_Remove a product");
        removeProduct.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new RemoveProduct ();
            }
        });
        editProductMenuEdit.getItems ().addAll (increaseStock, addNewProduct,
                seperatorProducts, removeProduct);
        menuEdit.getItems ().addAll (editPersonelMenuEdit, editProductMenuEdit);
        Menu menuFinancials = new Menu ("Fi_nancials");
        MenuItem itemReports = new MenuItem ("_Monthly report");
        itemReports.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new MonthlyReportsManager ();
            }
        });
        MenuItem itemSaleries = new MenuItem ("_Saleries");
        itemSaleries.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new PaySaleriesManager ();
            }
        });
        MenuItem itemViewVacations = new MenuItem ("_View vacations");
        itemViewVacations.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new VacationsReviewByManager ();
            }
        });
        menuFinancials.getItems ().addAll (itemReports, itemSaleries, itemViewVacations);
        Menu menuHelp = new Menu ("_Help");
        MenuItem showREADME = new MenuItem ("_Show readme file");
        showREADME.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new HelpPrompt ();
            }
        });
        menuHelp.getItems ().addAll (showREADME);
        MenuBar menuBarMain = new MenuBar ();
        menuBarMain.getMenus ().addAll (menuFile, menuEdit, menuFinancials, menuHelp);
        borderPaneCurrent.setTop (menuBarMain);
    }
}









