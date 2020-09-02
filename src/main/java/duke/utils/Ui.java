package duke.utils;

import java.util.List;

import duke.Main;
import duke.task.Task;


/**
 * Handles the printing of output to the console
 */
public class Ui {


    public Ui() {
    }


    /**
     * Outputs to console with lines.
     *
     * @param s string to output to console
     */
    public void displayThis(String s) {
        displayString(s);
    }


    /**
     * Welcomes the user with a welcome message
     */
    public void displayList(List<Task> toDoList, String s) {
        StringBuilder sBuilder = new StringBuilder(s).append('\n');

        for (int i = 0; i < toDoList.size(); i++) {
            sBuilder.append(i + 1).append(". ").append(toDoList.get(i)).append('\n');
        }

        displayString(sBuilder.toString());
    }


    public void welcome() {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";

        displayString(logo);

        displayString(""
                + "\nHello! I'm duke.Duke, your Windows duke.task.Task Manager"
                + "\nValid command:"
                + "\n- list"
                + "\n- done <num>"
                + "\n- todo <task>"
                + "\n- deadline <task> /by <yyyy-mm-dd>"
                + "\n- event <task> /at <yyyy-mm-dd>"
                + "\n- find <keyword>\n");


    }


    private void displayString(String s) {
        Main.getWindow().showsToDialog(s);
    }


}
