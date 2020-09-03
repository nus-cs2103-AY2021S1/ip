package duke;

import java.util.Scanner;


public class Ui {

    protected TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads the user's command and returns the command as a String
     *
     * @return String of one line command
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
    /**
     * Returns a String which indicates welcome from duke when the app is launched
     *
     * @return String;
     */
    public String showWelcome() {
        return "_____________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "_____________________________________________\n";
    }

    /**
     * Returns a string that inform the user about the newly added task depends on the type of task input
     *
     * @param task The newly added task
     * @return String of information about the newly added task
     */
    public String showAdd(Task task) {

        String dukeOutput = "_____________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + task.toString() + "\n"
                + "     Now you have " + this.tasks.size() + " " + "task" + " in the list.\n"
                + "_____________________________________________\n";
        System.out.println(dukeOutput);
        return dukeOutput;

    }

    /**
     * Returns a string that inform the user about the task which is marked as done.
     *
     * @param index The position of the task which is going to be marked as done in the TaskList
     * @return String of information about marked task
     */
    public String showDone(int index) {
        String dukeOutput = this.tasks.get(index).markAsDone();
        System.out.println(dukeOutput);
        return dukeOutput;
    }

    /**
     * Returns a string that inform the user about the newly deleted task depends on the type of task input
     *
     * @param index The position of the task which is going to be deleted in the TaskList
     * @return String of information about the deleted task
     */
    public String showDelete(int index) {
        Task removed = this.tasks.get(index);
        this.tasks.delete(index);
        String dukeOutput = "_____________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + removed.toString() + "\n"
                + "     Now you have " + this.tasks.size() + " tasks in the list.\n"
                + "_____________________________________________";
        System.out.println(dukeOutput);
        return dukeOutput;
    }

    /**
     * Returns a string that contains all the current tasks in the TaskList
     *
     * @return String of current tasks
     */
    public String showList() {
        String dukeOutput = "_____________________________________________\n"
                + "Here are the tasks in your list:\n";

        for (int i = 0; i < this.tasks.size(); i++) {
            String label = Integer.toString(1 + i);
            dukeOutput = dukeOutput + label + ". " + this.tasks.get(i).toString() + "\n";
        }

        dukeOutput = dukeOutput + "_____________________________________________";
        System.out.println(dukeOutput);
        return dukeOutput;
    }
    /**
     * Returns a String which indicates whether the keyword
     *
     * @return String;
     */
    public String showFind(TaskList matchedTasks) {
        String dukeOutput = "_____________________________________________\n";
        if (matchedTasks.size() == 0) {
            dukeOutput = dukeOutput + "Sorry, Duke can not find a matching task.\n";
        } else {
            dukeOutput = dukeOutput + "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchedTasks.size(); i++) {
                int index = i + 1;
                dukeOutput = dukeOutput + index + "." + matchedTasks.get(i).toString() + "\n";
            }
        }
        dukeOutput = dukeOutput + "_____________________________________________";
        System.out.println(dukeOutput);
        return dukeOutput;
    }
    /**
     * Returns a String when the user exit
     *
     * @return String;
     */
    public String showBye() {
        String dukeOutput = "_____________________________________________\n"
                + "       Bye. Hope to see you again soon!\n"
                + "_____________________________________________";
        System.out.println(dukeOutput);
        return dukeOutput;
    }


}
