import java.util.Scanner;

import duke.tasks.Task;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void sayGreeting() {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you today?");
    }

    public void sayFarewell() {
        System.out.println("Bye bye. See you again soon!");
    }

    public String getNextCommand() {
        String nextCommand = sc.nextLine();
        return nextCommand;
    }

    public void showErrorMsg(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void fileLoaded() {
        System.out.println("I found your saved tasks. You can view them using list.");
    }

    public static void newFileCreated() {
        System.out.println("I have created a new log file to help me remember what you tell me!");
    }

    public void taskAdded(Task newTask, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n"
                + newTask + "\nNow you have "
                + taskList.numTask() + " task(s) in the list.");
    }

    public void taskDeleted(Task removeTask, TaskList taskList) {
        System.out.println("Poof! I've removed this task:\n"
                + removeTask + "\nNow you have "
                + taskList.numTask() + " task(s) in the list.");
    }

    public void markAsDone(int index, TaskList taskList) {
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(index));
    }

    public void listTasks(TaskList taskList) {
        System.out.println("Let's see what we have here:\n");
        taskList.list();
    }
}
