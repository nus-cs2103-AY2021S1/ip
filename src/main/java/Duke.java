
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Main class which cretaes the application Duke that creates a checklist for tasks to do
 */
public class Duke extends Application{





    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hi if you are reviewing my code uh this the lvl10 things so not sure how it runs with it try looking at the tag with A-Gradle"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        runDuke();
    }

    /**
     * main program to execute
     * @param args
     */
    public static void main(String[] args) {


        runDuke();

    }


    /**
     * runs the program
     */


    public static void runDuke() {


        UI.introduction();
        Parser.parseCode(Storage.load(Storage.FILE_PATH), new UI(), false);
    }

}


