package duke;

//import duke.uicomponents.DialogBox;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;


/**
 * <h1>Duke IP Project</h1>
 *
 * <p>A simple task managing chatbot. Functionalities include saving tasks, completing tasks, and </p>
 */
public class Duke {

    private Ui duke;


    /**
     * Instantiates a new Duke.
     */
    public Duke() {
        duke = new Ui();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return duke.takeInputAndReturn(input);
    }

/* ------------------------------------------------------DEPRECATED-------------------------------------------------- */


//    public static void main(String[] args) {
//        Ui duke = new Ui();
//        duke.start();
//        Scanner sc = new Scanner(System.in);
//
//        /* Initial setting of password */
//        while (!duke.checkAuth()) {
//            System.out.println("Please set a user password for auth.");
//            String p1 = sc.nextLine();
//            System.out.println("Please confirm password");
//            String p2 = sc.nextLine();
//            if (p1.trim().equals(p2.trim())) {
//                duke.setPw(p1.trim());
//            }
//        }
//
//        /* Main App Loop */
//        int x = 1;
//        //Loop until exit command given
//        while (x > 0) {
//            if (sc.hasNext()) {
//                x = duke.takeInputAndReturn(sc.nextLine());
//            } else {
//                duke.end();
//            }
//        }
//        if (x < 0) {
//            System.out.println("Fatal system error uncaught in main logic");
//        }
//    }


}
