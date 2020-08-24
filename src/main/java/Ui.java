public class Ui {
    public void showWelcomeMessage() {
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(message);
    }

    public void showExitMessage() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    public void showResponse(CommandResult result) {
        System.out.println(result.responseToUser);
    }
}
