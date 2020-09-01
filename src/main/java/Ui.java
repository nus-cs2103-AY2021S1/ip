import java.util.ArrayList;

public class Ui {

    public Ui() {
    }

    public void displayTaskList(ArrayList<Task> task_list) throws DukeInvalidCommandException {
        if (task_list.size() == 0) {
            throw new DukeInvalidCommandException("There are no task_list in the list");
        }

        System.out.println("    ____________________________________________________________\n" +
                           "     Here are the tasks in your list:");
        for (int i = 1; i <= task_list.size(); i++) {
            System.out.println("     " + i + "." + task_list.get(i-1));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void printError(Exception e) {
        System.out.println(e.toString());
        System.out.println("Please try again.");
    }

    public void printInvalidDateFormatError() {
        System.out.println("Invalid Date Format!");
        System.out.println("Please enter the date as yyyy-mm-dd");
    }

    public void hello() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String hello = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" + logo +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(hello);

    }


    public void bye() {
        String bye = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(bye);
        System.exit(0);
    }

}
