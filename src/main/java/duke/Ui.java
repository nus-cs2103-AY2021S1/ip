package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    static final String SOCCAT =
            "                 .                         .                             \n" +
            "                ...                       ...                            \n" +
            "               /@@@&*                   (@@@@@&*                         \n" +
            "              /@@@@@@@@&*            /@@@@@@@@@@#                        \n" +
            "             *&@@@@@@@@@@@@*       /@@@@@@@@@@@@&/                       \n" +
            "             %&@@@@@@@@@@@@@@%.  *@@@@@@@@@@@@@@@&/                      \n" +
            "            /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#                     \n" +
            "           /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                   \n" +
            "         .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                 \n" +
            "        *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.               \n" +
            "       #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,              \n" +
            "      %@@@@@@@@@@@@*,#@@@@@@@@@@@@@@@@@@@%..,&@@@@@@@@@@@@@/             \n" +
            "     (@@@@@@@@@@@%    *@@@@@@@@@@@@@@@@@@    ,@@@@@@@@@@@@@&             \n" +
            "     @@@@@@@@@@@@@@@   &@@@@@@@@@@@@@@@@@@&.  %@@@@@@@@@@@@@(            \n" +
            "    (@@@@@@@@@@@@(     #@@@@@@@@@@@@@@@@@.    /@@@@@@@@@@@@&%            \n" +
            "    *@&&&&&@@@@@@(     /@@@@@@@@@@@@@@@@@.    *@@@@@@&&&&&&&(            \n" +
            "     %&&&&&&&&@@@&     (@@@@@@@@@@@@@@@@@*    #@@@&@&&&&&&&%             \n" +
            "     .&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&,             \n" +
            "       ,&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&(               \n" +
            "         .(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&/                 \n" +
            "             ,#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%*.                    \n" +
            "                    ,/#@@@@@@@@@@@@@@@@@@@@@@#                           \n" +
            "                      #&@@@@@@@@@@@@@@@@@@@&&&&.                         \n" +
            "                     ,&@@@@@@@@@@@@@@@@@@@@@@@@@/                        \n" +
            "                     #@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                      \n" +
            "                    ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                     \n" +
            "                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&.                   \n" +
            "                   /@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@&,                  \n" +
            "                   %@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@&.                 \n" +
            "                   @@@@@@@@@@@@@@@@@@@@@%&@@@@@@@@@@@@&&#*,.             \n" +
            "                  *@@@@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@&&*.,#&&@@@*        \n" +
            "                  ,@@@@@@@@@&@@@@@@@@@@&@@@@@@@@@@@@&&(       .(&@@(     \n" +
            "                    ./#%&&%..,***////**(@@@@@@@@@&&%*            ,@@@*   \n" +
            "                                                                    @@(  \n" +
            "                                                       *%#*@#/@#(&@&.    \n" +
            "                                                  /&@@@%##/&@@@@&,       \n" +
            "                                                @@@@@@#&@@@@@@@@@        \n" +
            "                                           .(@@&#&@&&&&&%@@@@@@@*        \n" +
            "                                           &%&&&%&&&&&&&&&@@#            \n" +
            "                                            .*%@@@%@@%, ..               \n" +
            "                                                  .                      ";
    static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    static final String INDENT = "    ";

    public void printWindow(String s) {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + s);
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    public void printBye() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.print(INDENT + HORIZONTAL_LINE);
    }

    public void intro() {
        System.out.println(INDENT + HORIZONTAL_LINE);
        System.out.println(SOCCAT);
        System.out.println("    Hello! I'm Soccat Duke\n" +
                "    What do you meow?");
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    public void showMarkDone(Task task) {
        printWindow("Nice! I've marked this task as done: \n" +
                "      " + task);
    }

    public void showAdded(Task addedTask, int listSize) {
        printWindow("Got it. I've added this task:\n" +
                INDENT + "  " + addedTask + "\n" +
                INDENT +
                String.format("Now you have %d tasks in the in the list", listSize));
    }

    public void showDeleted(Task deletedTask, int listSize) {
        printWindow("Noted. I've removed this task: \n" +
                "      " + deletedTask +
                String.format("\n     Now you have %d tasks in the list.", listSize));
    }

    public void showLoadingError() {
        printWindow("Oops, error in loading the tasks! "
                + "Please check the duke.txt file");
    }

    public void showList(TaskList tasks) {
        printWindow(tasks.getListAsString());
    }

    public void showError(Exception e) {
        printWindow(e.getMessage());
    }

    public String readCommand() {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            return null;
        }
    }
}
