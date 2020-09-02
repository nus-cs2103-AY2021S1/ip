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
        //        displayLines();
        displayString(s);
        //        displayLines();
    }


    //    /**
    //     * Displays the contents to console.
    //     *
    //     * @param toDoList the Tasks to display to console
    //     */
    //    public void displayList(List<Task> toDoList) {
    ////        displayLines();
    //        String s =
    //        displayString("Here are your current tasks:");
    //        for (int i = 0; i < toDoList.size(); i++) {
    //            displayString((i + 1) + ". " + toDoList.get(i));
    //        }
    ////        displayLines();
    //    }


    /**
     * Welcomes the user with a welcome message
     */
    public void displayList(List<Task> toDoList, String s) {
        //        displayLines();

        StringBuilder sBuilder = new StringBuilder(s).append('\n');

        for (int i = 0; i < toDoList.size(); i++) {
            sBuilder.append(i + 1).append(". ").append(toDoList.get(i)).append('\n');
        }

        displayString(sBuilder.toString());
        //        displayLines();
    }

    public void welcome() {
        String logo = "____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";

        //        System.out.println(`logo);
        //        System.out.println(""
        //                + "\n    Hello! I'm duke.Duke, your Windows duke.task.Task Manager"
        //                + "\n    Valid command:"
        //                + "\n    - list"
        //                + "\n    - done <num>"
        //                + "\n    - todo <task>"
        //                + "\n    - deadline <task> /by <yyyy-mm-dd>"
        //                + "\n    - event <task> /at <yyyy-mm-dd>"
        //                + "\n    - find <keyword>\n");

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


    // todo clean up commented codes
    private void displayString(String s) {
        //        System.out.println("    " + s);
        Main.getWindow().showsToDialog(s);
    }
    //
    //
    //    private void displayLines() {
    //        System.out.println("    -------------------------------------------------");
    //    }


}
