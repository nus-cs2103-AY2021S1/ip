package duke;

import java.util.Scanner;


public class Ui {

    protected TaskList tasks;

    public Ui(TaskList tasks){
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

    public void showWelcome() {
        System.out.println(
                "Hello from\n" +
                        " ____        _        \n" +
                        "|  _ \\ _   _| | _____ \n" +
                        "| | | | | | | |/ / _ \\\n" +
                        "| |_| | |_| |   <  __/\n" +
                        "|____/ \\__,_|_|\\_\\___|\n" +
                        "____________________________________________________________\n" +
                        "     Hello! I'm Duke\n" +
                        "     What can I do for you?\n" +
                        "____________________________________________________________\n");
    }

    /**
     * Returns a string that inform the user about the newly added task depends on the type of task input
     *
     * @param task The newly added task
     * @return String of information about the newly added task
     */
    public String showAdd(Task task) {

        String DukeOutput = "____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + task.toString() + "\n" +
                "     Now you have " + this.tasks.size() + " " + "task" + " in the list.\n" +
                "____________________________________________________________\n";
        System.out.println(DukeOutput);
        return DukeOutput;

    }

    /**
     * Returns a string that inform the user about the task which is marked as done.
     *
     * @param index The position of the task which is going to be marked as done in the TaskList
     * @return String of information about marked task
     */
    public String showDone(int index) {
        String DukeOutput = this.tasks.get(index).markAsDone();
        System.out.println(DukeOutput);
        return DukeOutput;
    }

    /**
     * Returns a string that inform the user about the newly deleted task depends on the type of task input
     *
     * @param  index The position of the task which is going to be deleted in the TaskList
     * @return String of information about the deleted task
     */
    public String showDelete(int index) {
        Task removed = this.tasks.get(index);
        this.tasks.delete(index);
        String DukeOutput = "____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       " + removed.toString() + "\n" +
                "     Now you have "+ this.tasks.size() + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(DukeOutput);
        return DukeOutput;
    }

    /**
     * Returns a string that contains all the current tasks in the TaskList
     *
     * @return String of current tasks
     */
    public String showList() {
        String DukeOutput = "____________________________________________________________\n" +
                "Here are the tasks in your list:\n";

        for (int i = 0; i < this.tasks.size(); i++) {
            String label = Integer.toString(1 + i);
            DukeOutput = DukeOutput + label + ". " + this.tasks.get(i).toString() + "\n";
        }

        DukeOutput = DukeOutput + "____________________________________________________________";
        System.out.println(DukeOutput);
        return DukeOutput;
    }

    public String showBye() {
        String DukeOutput = "____________________________________________________________\n" +
                "       Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(DukeOutput);
        return DukeOutput;
    }


}
