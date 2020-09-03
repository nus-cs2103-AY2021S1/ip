package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    public Scanner sc;

    public  Ui(){
        this.sc = new Scanner(System.in);
    }

    public static String showWelcome(){
        return "What's new scooby doo?\n" + "How can I help you today?";
    }

    public void showLine() {
        System.out.println("--------------------------------------------------");
    }

//    /**
//     * Initiates the Ui.
//     * @param taskList      the TaskList that holds all the tasks
//     * @param store         the Storage that handles the loading and saving of tasks into the schedule text file
//     */
//    public void uiRun(TaskList taskList, Storage store) throws IOException {
//        Parser parser = new Parser();
//        ArrayList<Task> todoList = taskList.todoList;
//        while (sc.hasNext()) {
//            parser.parse(this, todoList, store);
//        }
//    }


}