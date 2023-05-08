package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LandingPageSeller
{
    Stage      stage2;
    BorderPane borderPaneLayout;

    public LandingPageSeller (Stage stage1, String username, String typeOfPosition) throws Exception
    {
        stage1.close ();
        switch (typeOfPosition)
        {
            case "seller1" :
                Seller1Program (username);
                break;
            case "seller2" :
                Seller2Program (username);
                break;
            case "seller3" :
                Seller3Program (username);
                break;
        }
    }

    private void Seller1Program(String username)
    {
        String currentUser = username;  //storing this for later on
        Stage stageLandingPage = new Stage ();
        stage2 = stageLandingPage;
        stage2.setTitle ("Main Program (Seller)");
        borderPaneLayout = BuildBorderPane ();
        AddUI (borderPaneLayout, currentUser);
        stage2.setWidth (1880);
        stage2.setHeight (1040);
        Scene scene = new Scene (borderPaneLayout);
        stage2.setScene (scene);
        stage2.show ();
    }

    private void Seller2Program (String username)
    {
        String currentUser = username;  //storing this for later on
        Stage stageLandingPage = new Stage ();
        stage2 = stageLandingPage;
        stage2.setTitle ("Main Program (Seller)");
        borderPaneLayout = BuildBorderPane ();
        AddUI (borderPaneLayout, currentUser);
        stage2.setWidth (1880);
        stage2.setHeight (1040);
        Scene scene = new Scene (borderPaneLayout);
        stage2.setScene (scene);
        stage2.show ();
    }

    private void Seller3Program (String username)
    {
        String currentUser = username;  //storing this for later on
        Stage stageLandingPage = new Stage ();
        stage2 = stageLandingPage;
        stage2.setTitle ("Main Program (Seller)");
        borderPaneLayout = BuildBorderPane ();
        AddUI (borderPaneLayout, currentUser);
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
        Menu menuFile = new Menu ("_File");
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
        MenuItem editPersonalInfoPersonel = new MenuItem ("_Personal account");
        editPersonalInfoPersonel.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new EditAccountInfo (currentUser);
            }
        });
        menuEdit.getItems ().addAll (editPersonalInfoPersonel);
        Menu menuFinancials = new Menu ("Fi_nancials");
        MenuItem itemSell = new MenuItem ("_Sell product");
        itemSell.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new SellProductSellers ();
            }
        });
        MenuItem itemVacation = new MenuItem ("_Vacation");
        itemVacation.setOnAction (new EventHandler<ActionEvent> ()
        {
            @Override
            public void handle (ActionEvent actionEvent)
            {
                new VacationRequest (currentUser);
            }
        });
        menuFinancials.getItems ().addAll (itemSell, itemVacation);
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









