package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Entry point for application, initializes the UI
 */
public class Duke extends Application {

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        String welcomeGraphic = "                      .,,uod8B8bou,,.\n" +
                "              ..,uod8BBBBBBBBBBBBBBBBRPFT?l!i:.\n" +
                "         ,=m8BBBBBBBBBBBBBBBRPFT?!||||||||||||||\n" +
                "         !...:!TVBBBRPFT||||||||||!!^^\"\"'   ||||\n" +
                "         !.......:!?|||||!!^^\"\"'            ||||\n" +
                "         !.........||||                     ||||\n" +
                "         !.........||||       Welcome!      ||||\n" +
                "         !.........||||                     ||||\n" +
                "         !.........||||      I am your      ||||\n" +
                "         !.........||||  personal assistant ||||\n" +
                "         !.........||||       Zachary       ||||\n" +
                "         `.........||||                    ,||||\n" +
                "          .;.......||||               _.-!!|||||\n" +
                "   .,uodWBBBBb.....||||       _.-!!|||||||||!:'\n" +
                "!YBBBBBBBBBBBBBBb..!|||:..-!!|||||||!iof68BBBBBb....\n" +
                "!..YBBBBBBBBBBBBBBb!!||||||||!iof68BBBBBBRPFT?!::   `.\n" +
                "!....YBBBBBBBBBBBBBBbaaitf68BBBBBBRPFT?!:::::::::     `.\n" +
                "!......YBBBBBBBBBBBBBBBBBBBRPFT?!::::::;:!^\"`;:::       `.\n" +
                "!........YBBBBBBBBBBRPFT?!::::::::::^''...::::::;         iBBbo.\n" +
                "`..........YBRPFT?!::::::::::::::::::::::::;iof68bo.      WBBBBbo.\n" +
                "  `..........:::::::::::::::::::::::;iof688888888888b.     `YBBBP^'\n" +
                "    `........::::::::::::::::;iof688888888888888888888b.     `\n" +
                "      `......:::::::::;iof688888888888888888888888888888b.\n" +
                "        `....:::;iof688888888888888888888888888888888899fT!\n" +
                "          `..::!8888888888888888888888888888888899fT|!^\"'\n" +
                "            `' !!988888888888888888888888899fT|!^\"'\n" +
                "                `!!8888888888888888899fT|!^\"'\n" +
                "                  `!988888888899fT|!^\"'\n" +
                "                    `!9899fT|!^\"'\n\n\n";
        System.out.println(welcomeGraphic);
        Ui ui = new Ui();
        ui.run();
    }
}
