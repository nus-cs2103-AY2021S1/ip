import java.io.IOException;

public class Ui {
    static void readUserInput(Parser parser) throws IOException {
        parser.scan();
    }

    static void showWelcomeMessage() {
        System.out.println("Hi I'm Duke, your personal task-tracker bot!");
        System.out.println("You can add todos, deadlines, or events to my " +
                                   "list.");
    }

    static void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again");
    }

    static void showSuccessfulLoad() {
        System.out.println("Your existing task list has been retrieved from disk.");
    }

    static void showNoExistingSave() {
        System.out.println("You don't have an existing saved task list.");
    }

    static void showSuccessfulAdd(Task task) {
        System.out.println("added: " + task);
    }

    static void showSuccessfulSave() {
        System.out.println("Alright, your list has been saved!");
    }

    static void showErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    static void showErrorMessage(String customMessage, Exception exception) {
        System.out.println(customMessage + exception.getMessage());
    }

    static void showSuccessfulRemoval(Task task) {
        System.out.println("This task has been removed: " + task);
    }

    static void showMarkedAsDone(Task task) {
        System.out.println("I've marked this task as done:\n" + task);
    }
}
