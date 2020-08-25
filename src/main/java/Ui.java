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
        String intro = "Eh yo, I am Duke! Some kids call me 'Lao Duke' (which means old Duke).\n" +
                "I like to speak Singlish but I also can remember your tasks one. Come try la! ";
        System.out.println(logo);
        System.out.println(intro);
    }

    /**
     * Prints the goodbye message.
     */
    public void byeMessage() {
        String bye = "Alamak, you sure you finished all your tasks? Ok lah I also need to sleep anyway Zzzz.\n" +
                "Goodbye!";
        System.out.println(bye);
    }

    /**
     * Prints the list of tasks when user calls for 'list'.
     * @param taskList The list of task to be printed.
     */
    public void showListTasks(List<Task> taskList) {
        System.out.println("Lao Duke not so blur like you. Tsk. I got remember your tasks one hor.");
        for (int i = 0; i < taskList.size(); i++) {
            Task tsk = taskList.get(i);
            System.out.println("Task " + (i + 1) + ": "  + tsk);
        }
    }

    /**
     * Prints the list of tasks with same date as what user demands
     * when user calls for 'print /date'.
     * @param taskList The list of tasks with same date.
     */
    public void showSameDateTasks(List<Task> taskList){
        for (Task tsk : taskList) {
            System.out.println(tsk);
        }
    }

    /**
     * Prints the task that has been added to the list.
     * @param tsk The task that has been added.
     */
    public void showAddTask(Task tsk) {
        System.out.println("Lao Duke has added this task for you:\n" + tsk);
    }

    /**
     * Prints the task that has been marked as done when user calls 'done /task'.
     * @param tsk The task that is completed.
     */
    public void showDoneTask(Task tsk) {
        System.out.println("Wah very good! I am proud that you got do your task!\n" + tsk);
    }

    /**
     * Prints the task that has been deleted when user calls for 'delete /task'
     * @param tsk The task that is deleted.
     */
    public void showDeleteTask(Task tsk) {
        System.out.println("Can can I removed this task for you:\n" + tsk);
    }

    /**
     * Prints the total number of tasks in list.
     * @param num The total number of tasks.
     */
    public void showTotalTasks(int num) {
        System.out.println("You have " + num + " task(s) in your list!");
    }

    /**
     * Prints the error when an IOException occurs.
     */
    public void showLoadingError() {
        System.out.println("Error with reading / writing file!");
    }

    /**
     * Prints the error when a DukeException occurs.
     * @param e The DukeException that arises.
     */
    public void showDukeError(DukeException e) {
        System.out.println(e);
    }
}
