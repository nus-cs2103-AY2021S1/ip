import java.util.Scanner;

public class Ui {
    protected static final String[] CMD_ARR = {"help", "add", "list", "done", "delete", "date", "bye"};
    protected static final String DIVIDER = "____________________________________________________________";
    protected static final String LOGO = " __________________________________________________________\n"
            + "|                                                          |\n"
            + "|  ____     _     _____   ____  _   _ ____   ____ _______  |\n"
            + "| |  _ \\   / \\   |  __ \\ / __ \\| \\ | |  _ \\ / __ \\__   __| |\n"
            + "| | |_) | /   \\  | |__) | |  | |  \\| | |_) | |  | | | |    |\n"
            + "| |  _ < / /_\\ \\ |  _  /| |  | | . ` |  _ <| |  | | | |    |\n"
            + "| | |_) / _____ \\| | \\ \\| |__| | |\\  | |_) | |__| | | |    |\n"
            + "| |____/_/     \\_\\_|  \\_\\\\____/|_| \\_|____/ \\____/  |_|    |\n"
            + "|                                                          |\n"
            + "|__________________________________________________________|\n";

    public void start(TaskList taskList) {
        this.printDivider();
        System.out.println(LOGO);
        this.printDivider();
        System.out.println("Hello, I am BaronBot!");
        System.out.println("What can I do for you?");
        this.printDivider();

        Parser.parse(this, taskList);
    }

    public void showEmptyListException(EmptyListException e) {
        System.out.println(e.getMessage());
        System.out.println("Use the 'add' command to start adding tasks!");
    }

    public void showUnknownCommandException(UnknownCommandException e) {
        System.out.println(e.getMessage());
        System.out.println("How about entering 'help' instead?");
    }

    public void showInvalidTaskTypeException(InvalidTaskTypeException e) {
        System.out.println(e.getMessage());
    }

    public void showEmptyDescriptionException(EmptyDescriptionException e) {
        System.out.println(e.getMessage());
    }

    public void showArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
        System.out.println("Hmmm, looks like the format didn't work");
        System.out.println("Refer to the example for help! :P");
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printAdditionActionMessage() {
        this.printDivider();
        System.out.println("What else would you like to do?");
        this.printDivider();
    }

    public void printGoodbyeMessage() {
        this.printDivider();
        System.out.println("Bye! See you around :)");
        this.printDivider();
    }

    public void printHelp() {
        System.out.println("Here are the commands you can use:");
        for (int i = 0; i < CMD_ARR.length; i++) {
            System.out.println((i + 1) + ". " + CMD_ARR[i]);
        }
    }

    public void printTaskTypes() {
        System.out.println("What kind of task is it?");
        System.out.print(" - Todo\n"
                + " - Deadline\n"
                + " - Event\n");
    }

    public void printEnterTaskPrompt() {
        System.out.println("Please enter the task");
    }

    public void printDeadlineExample() {
        System.out.println("Please enter the task followed by the date and time of the deadline");
        System.out.println("e.g., submit report ,11/10/2019 1700");
    }

    public void printEventExample() {
        System.out.println("Please enter the event followed by the date and time of the event");
        System.out.println("e.g., team project meeting ,2/10/2019 1400-1600");
    }

    public void printAddAcknowledgement(TaskList taskList) {
        System.out.println("Alright, I've added this task:");
        System.out.println(taskList.getMostRecentTask().toString());
        System.out.println("You now have " + taskList.getTaskListSize() + " tasks on your list");
    }

    public void printList(TaskList taskList) {
        System.out.println("These are the tasks on your list:");
        for (int j = 0; j < taskList.getTaskListSize(); j++) {
            System.out.println((j + 1)
                    + ". "
                    + taskList.getTask(j).toString());
        }
    }

    public void printDonePrompt() {
        System.out.println("Which task do you want to mark as done?");
    }

    public void printDoneAcknowledgement(TaskList taskList, int taskNum) {
        System.out.println("Good job! This task is now marked as done:");
        System.out.println(taskList.getTask(taskNum - 1).toString());
    }

    public void showIndexOutOfBoundsExceptionForDone(IndexOutOfBoundsException e){
        System.out.println("Sorry that task doesn't exist :/");
        System.out.println("Try using 'list' to find out what tasks you have!");
    }

    public void showInvalidDoneCommandException(InvalidDoneCommandException e) {
        System.out.println(e.getMessage());
        System.out.println("Check out each task's status by using 'list'!");
    }

    public void printDeletePrompt() {
        System.out.println("Which task do you want to delete?");
    }

    public void printDeleteAcknowledgement(TaskList taskList, Task task) {
        System.out.println("Alright, the following task has been removed:");
        System.out.println(task.toString());
        System.out.println("You now have " + taskList.getTaskListSize() + " tasks on your list");
    }

    public void showIndexOutOfBoundsExceptionForDelete(IndexOutOfBoundsException e) {
        System.out.println("Sorry that task doesn't exist :/");
        System.out.println("Try using 'list' to find out what tasks you have!");
    }
}
