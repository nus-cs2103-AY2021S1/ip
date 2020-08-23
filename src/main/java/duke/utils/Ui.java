package duke.utils;

import duke.task.Task;

import java.util.List;

public class Ui {


    public Ui() {
    }

    public void displayThis(String s) {
        displayLines();
        displayString(s);
        displayLines();
    }


    public void displayList(List<Task> toDoList) {
        displayLines();
        displayString("Here are your current tasks:");
        for (int i = 0; i < toDoList.size(); i++) {
            displayString((i + 1) + ". " + toDoList.get(i));
        }
        displayLines();
    }


    public void displayList(List<Task> toDoList, String s) {
        displayLines();
        displayString(s);
        for (int i = 0; i < toDoList.size(); i++) {
            displayString((i + 1) + ". " + toDoList.get(i));
        }
        displayLines();
    }

    public void welcome() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|";

        System.out.println(logo);

        System.out.println("" +
                "\n    Hello! I'm duke.Duke, your Windows duke.task.Task Manager" +
                "\n    Valid command:" +
                "\n    - list" +
                "\n    - done <num>" +
                "\n    - todo <task>" +
                "\n    - deadline <task> /by <yyyy-mm-dd>" +
                "\n    - event <task> /at <yyyy-mm-dd>\n");


    }


    private void displayString(String s) {
        System.out.println("    " + s);
    }


    private void displayLines() {
        System.out.println("    -------------------------------------------------");
    }


}