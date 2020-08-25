package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    public  Ui(){
    }

    /**
     * Initiates the Ui.
     * @param taskList      the TaskList that holds all the tasks
     * @param store         the Storage that handles the loading
     *                      and saving of tasks into the schedule text file
     */
    public void uiRun(TaskList taskList, Storage store) throws IOException {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        ArrayList<Task> todoList = taskList.todoList;
        System.out.println("What's new scooby doo?\n" + "How can I help you today?");
        while (sc.hasNext()) {
            parser.parse(sc, todoList, store);
        }
    }


}