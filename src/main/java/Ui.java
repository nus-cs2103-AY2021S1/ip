import java.util.List;

public final class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showListItems(List<Task> lst, String action) {
        System.out.println(String.format("Here are the %s tasks in your list:", action.equals("search") ? "matching" : ""));

        for (int i = 0; i < lst.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, lst.get(i)));
        }
    }

    public void showAddMessage() {
        System.out.println("Got it. I've added this task: ");
    }

    public void showDoneMessage() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void showDeleteMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    public void showErrorMessage() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public void showWrongInputMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showDirErrorMessage() {
        System.out.println("Data directory or file not found, see error");
    }
}
