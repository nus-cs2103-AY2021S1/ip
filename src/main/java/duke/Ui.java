package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    static final String DIVIDE = "_____________________________________________________________";
    static final String WELCOME_MESSAGE = "Hello! I'm duke.Duke\nWhat can I do for you?";
    static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:\n";
    static final String DELETE_MESSAGE = "Noted. I've removed this task:\n";

    static final String LOADING_ERROR_MESSAGE = "Something went wrong when loading previously saved tasks!\n" +
            " Starting with an empty tasks list instead...";

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public void botReply(String message) {
        System.out.printf("%s\n%s\n%s\n", Ui.DIVIDE, message, Ui.DIVIDE);
    }

    public void showLoadingError() {
        this.botReply(Ui.LOADING_ERROR_MESSAGE);
    }

    public void showWelcome() {
        this.botReply(Ui.WELCOME_MESSAGE);
    }

    public void showGoodbye() {
        this.botReply(Ui.EXIT_MESSAGE);
    }

    public void replyList(String content) {
        this.botReply(Ui.LIST_MESSAGE + content);
    }

    public void replyDone(String content) {
        this.botReply(Ui.DONE_MESSAGE + content);
    }

    public void replyAdd(String content) {
        this.botReply(Ui.TASK_ADDED_MESSAGE + content);
    }

    public void replyDelete(String content) {
        this.botReply(Ui.DELETE_MESSAGE + content);
    }

    public void showError(String message) {
        this.botReply(message);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
