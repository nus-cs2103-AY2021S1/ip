package duke.utils;

import java.util.List;

import duke.Main;
import duke.task.Task;


/**
 * Handles the printing of output to the console
 */
public class Ui {

    /**
     * Creates a instance of Ui class.
     */
    public Ui() {
    }


    /**
     * Outputs to console with lines.
     *
     * @param msg string to output to console
     */
    public void displayThis(String msg) {
        displayString(msg);
    }


    /**
     * Welcomes the user with a welcome message.
     *
     * @param toDoList    the lists to display to the user.
     * @param customTitle the custom string to display before showing the list.
     */
    public void displayList(List<Task> toDoList, String customTitle) {
        assert toDoList.size() > 0;
        StringBuilder sBuilder = new StringBuilder(customTitle).append('\n');

        for (int i = 0; i < toDoList.size(); i++) {
            sBuilder.append(i + 1).append(". ").append(toDoList.get(i)).append('\n');
        }

        displayString(sBuilder.toString());
    }


    /**
     * Displays the welcome message when the user open the app.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";


        displayString(logo
                + "\n\nHello! I'm Duke, your Windows Task Manager"
                + "\nValid command:"
                + "\n- list (l)"
                + "\n- done (do) <num>"
                + "\n- todo (t) <task>"
                + "\n- delete (d) <num>"
                + "\n- deadline (de) <task> /by <yyyy-mm-dd>"
                + "\n- event (e) <task> /at <yyyy-mm-dd>"
                + "\n- find (f) <keyword>"
                + "\n- bye (b)"
                + "\n- clear (c)\n");


    }


    private void displayString(String s) {
        assert Main.getWindow() != null;
        Main.getWindow().showsToDialog(s);
    }


}
