import java.util.Scanner;

/**
 * Keeps track of user input. A <code>Ui</code> object contains a <code>Scanner</code> object.
 */
public class Ui {
    private static final String BOT_NAME = "Duke: ";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input and return it.
     *
     * @return user input.
     */
    public String receiveUserInput() {
        return scanner.nextLine();
    }

    /**
     * Greets the user.
     */
    public String sayGreetings() {
        String message = BOT_NAME + "Hello! How may I help you?";
        System.out.println(message);
        return message;
    }

    /**
     * Says goodbye to the user.
     */
    public String sayGoodbye() {
        String message = BOT_NAME + "Bye. Hope to see you again!";
        System.out.println(message);
        return message;
    }

    public String informFileNotFound() {
        String message = BOT_NAME + "It seems like you have no saved files! Creating one now...";
        System.out.println(message);
        return message;
    }

    /**
     *
     */
    public String sayErrorMessage(Exception e) {
        String errorMessage = BOT_NAME + "Error! " + e.getMessage();
        System.out.println(errorMessage);
        return errorMessage;
    }

    public String sayCurrentListIsEmpty() {
        String message = BOT_NAME + "Your task list is currently empty.";
        System.out.println(message);
        return message;
    }

    public String sayCurrentList(String currentList) {
        String message = BOT_NAME + "Here are your tasks:\n" + currentList;
        System.out.println(message);
        return message;
    }

    public String sayMarkedAsDone(Task task) {
        String message = BOT_NAME + "I have marked it as done!\n" + task;
        System.out.println(message);
        return message;
    }

    public String sayDeletedTask(Task task, int listSize) {
        String message = BOT_NAME + "I have deleted this task!\n" + task;
        message += "\n" + "Current list size: " + listSize;
        System.out.println(message);
        return message;
    }

    public String sayAddedTask(Task task, int listSize) {
        String message = BOT_NAME + "I have added this task!\n" + task;
        message += "\n" + "Current list size: " + listSize;
        System.out.println(message);
        return message;
    }

    public String sayFoundTasks(String foundTasks) {
        String message = BOT_NAME + "Here are the matching tasks found:\n" + foundTasks;
        System.out.println(message);
        return message;
    }

    public String sayNoMatchingFileFound() {
        String message = BOT_NAME + "No matching file found!";
        System.out.println(message);
        return message;
    }
}