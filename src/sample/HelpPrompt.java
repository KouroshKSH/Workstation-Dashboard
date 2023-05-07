package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelpPrompt
{
    public HelpPrompt()
    {
        Stage stage = new Stage ();
        stage.setTitle ("Help");
        stage.initModality (Modality.APPLICATION_MODAL);
        Text info = new Text ();
        info.setText ("A brief instruction for this program...\n" +
                "\n" +
                "> How the 'personelData.txt' file is written:\n" +
                "\tposition|username|firstName|lastName|password|personalID|jobID|paymentType|paymentValue|birthDate|education\n" +
                "\n" +
                "> Note that multi-part names should be written as below:\n" +
                "\texample) Ali Reza => AliReza / Jahaan Khaah => JahaanKhaah\n" +
                "\t** there should be no spaces in the text **\n" +
                "\n" +
                "> Note that all of the personal IDs are 10 digits based\n" +
                "\n" +
                "> Note that the payment types are as below:\n" +
                "\t1) manager => fix\n" +
                "\t2) seller1 => profit based\n" +
                "\t3) seller2 => fix\n" +
                "\t4) seller3 => mix (a combo of profit and fix)\n" +
                "\t5) keeper1 => hourly\n" +
                "\t6) keeper2 => fix\n" +
                "\n" +
                "> Note that the payment values are on a monthly basis\n" +
                "\n" +
                "> Note that birth dates are written as below:\n" +
                "\texample) 4 May 2001 => 04052001 / 24 October 1999 => 24101999\n" +
                "\t** the dates should be written only with numbers and no extra characters **\n" +
                "\n" +
                "> Note that the education part refers to their degree and it can be written in 2 ways:\n" +
                "\t1) phd, masters, bachelor, undergrad\n" +
                "\t2) mastersOfMathematics, bachelorOfComputerScience\n" +
                "\t** there should be no spaces in the text **\n" +
                "\n" +
                "> How the 'warehouseData.txt' file is written:\n" +
                "\tname|price|quantity|dateOfLastEntry|dateOfLastSelling|cost|itemsSold\n" +
                "\n" +
                "> Note that multi-part names should be written as below:\n" +
                "\texample) Hard Drive => HardDrive / Kensington Lock => KensingtonLock \n" +
                "\t** there should be no spaces in the text **\n" +
                "\n" +
                "> Note that the fields related to pricing and such are double-based numbers\n" +
                "\n" +
                "> Note that dates are written as below:\n" +
                "\texample) 15 May 2020 => 15052020 / 9 October 2019 => 09102019\n" +
                "\t** the dates should be written only with numbers and no extra characters **\n" +
                "\n" +
                "> Note that the total earnings of the products can be calculated by:\n" +
                "\tSum of [itemsSold * (price - cost)] per product = total earnings\n" +
                "\n" +
                "> Note that the date of \"00000000\" refers to products which have been added but havn't been sold yet\n" +
                "\n" +
                "> Note that the \"0\" for number of items sold for some products refers to goods which havn't been sold yet \n" +
                "\n" +
                "> Note that for the 're-stock' function, there's a bug that won't show the user that they're now typing (eventhough they are),\n" +
                "\tand so by once clicking on the cancel button in the increasing phase window and then retrying to input the numbers,\n" +
                "\tthe program will function fully\n" +
                "\n" +
                "> Note that there are preset saleries set in the program and managers can pay off the saleries in their 'financial' tab\n" +
                "\n" +
                "program developed by: Kourosh Sharifi");
        info.setFont (Font.font (12));
        VBox layout = new VBox (20);
        layout.setPadding (new Insets (20,20,20,20));
        layout.setAlignment (Pos.CENTER);
        layout.getChildren ().addAll (info);
        Scene scene = new Scene (layout, 800, 1000);
        stage.setScene (scene);
        stage.show ();
    }
}
