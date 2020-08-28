package duke;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * <h1>Duke IP Project</h1>
 *
 * <p>A simple task managing chatbot. Functionalities include saving tasks, completing tasks, and </p>
 */
public class Duke {

    /**
     * Entry point of Duke. Simulates and main application loop.
     * @param args
     */
    public static void main(String[] args) {
        Ui duke = new Ui();
        duke.start();
        Scanner sc = new Scanner(System.in);
        int x = 1;
        //Loop until exit command given
        while (x > 0) {
            if (sc.hasNext()) {
                x = duke.takeInputAndReturn(sc.nextLine());
            } else {
                duke.end();
            }
        }
    }

}
