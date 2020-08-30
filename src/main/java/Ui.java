import java.util.List;
import java.util.Scanner;

/**
 * Handles the interaction with user, such as reading input and
 * printing output.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads user command.
     * @return User command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Eh yo, I am Duke! Some kids call me 'Lao Duke' (which means old Duke).\n"
                + "I like to speak Singlish but I also can remember your tasks one. Come try la! ";
        System.out.println(logo);
        System.out.println(intro);
    }

    /**
     * Prints the goodbye message.
     */
    public void byeMessage() {
        String bye = "Alamak, you sure you finished all your tasks? Ok lah I also need to sleep anyway Zzzz.\n"
                + "Goodbye!";
        System.out.println(bye);
    }

    /**
     * Prints the list of tasks when user calls for 'list'.
     * @param taskList The list of task to be printed.
     */
    public String showListTasks(List<Task> taskList) {
        StringBuilder reply = new StringBuilder();
        reply.append("I remembered your tasks well because I have such good memory! :D Here you go:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task tsk = taskList.get(i);
            reply.append("Task " + (i + 1) + ": " + tsk + (i == taskList.size() - 1 ? "" : "\n"));
        }
        return reply.toString();
    }

    /**
     * Prints the list of tasks with same date as what user demands
     * when user calls for 'print /date'.
     * @param taskList The list of tasks with same date.
     */
    public String showRequiredTasks(List<Task> taskList) {
        if (taskList.size() > 0) {
            StringBuilder reply = new StringBuilder();
            reply.append("I have matched your queries as below. Don't forget to complete them if not your mom will scold you!\n");
            for (int i = 0; i < taskList.size(); i++) {
                reply.append((i + 1) + ". " + taskList.get(i) + (i == taskList.size() - 1 ? "" : "\n"));
            }
            return reply.toString();
        } else {
            return "Oh no >_< I can't seem to find any matching tasks.";
        }
    }

    /**
     * Prints the task that has been added to the list.
     * @param tsk The task that has been added.
     */
    public String showAddTask(Task tsk) {
        return "I have added this task for you:\n" + tsk;
    }

    /**
     * Prints the task that has been marked as done when user calls 'done /task'.
     * @param tsk The task that is completed.
     */
    public String showDoneTask(Task tsk) {
        return "Excellent! Don't procrastinate like Nobita :P. You have completed:\n" + tsk;
    }

    /**
     * Prints the task that has been deleted when user calls for 'delete /task'
     * @param tsk The task that is deleted.
     */
    public String showDeleteTask(Task tsk) {
        return "I have removed this task for you:\n" + tsk;
    }

    /**
     * Prints the total number of tasks in list.
     * @param num The total number of tasks.
     */
    public String showTotalTasks(int num) {
        return "You have " + num + " task(s) in your list!";
    }

    /**
     * Prints the error when an IOException occurs.
     */
    public String showLoadingError() {
        return "Error with reading / writing file!";
    }

    /**
     * Prints the error when a DukeException occurs.
     * @param e The DukeException that arises.
     */
    public String showDukeError(DukeException e) {
        return e.toString();
    }
}
