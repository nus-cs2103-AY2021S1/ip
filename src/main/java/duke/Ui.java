package duke;

import duke.task.*;

public class Ui {

    private static final String BORDER
            = "==========================================";

    public void printGreeting() {
        System.out.println(BORDER
                + "\nHi, my name is Duke."
                + "\nWhat can I do for you today?"
                + "\n==========================================");
    }

    public void printFarewell() {
        System.out.println("Thanks for chatting with me, "
                + "see you soon!\n"
                + BORDER);
    }

    public void printList(TaskList tasks) {
        System.out.println("Here are the tasks"
                + " in your list:");
    }

    public void printSave() {
        System.out.println("List saved to hard disk "
                + "at data/tasks.csv");
    }

    public void printAdded(Task task, int taskNumber) {
        System.out.println(">" + "added: " + task + "<");
        System.out.println("You now have "
                + taskNumber
                + " task(s) in your list.");
    }

    public void printDeleted(Task task, int taskNumber) {
        System.out.println("I have deleted this task: \n" + task);
        System.out.println("You now have "
                + taskNumber
                + " task(s) in your list.");
    }

    public void printDone(TaskList tasks, int taskNumber) {
        System.out.println("I have marked this task as done: \n"
                + tasks.getTask(taskNumber));
    }
}
