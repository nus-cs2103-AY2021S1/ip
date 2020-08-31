package duke;

import java.util.Scanner;

class Ui {

    String introduction;
    Scanner reader;

    Ui() {
        reader = new Scanner(System.in);
        introduction = "Hi, I'm your Professor, Martin. I keep track of\n" +
                "your tasks and load them if you have any saved.\n" +
                "What can I do for you? You can ask me to do these:\n" +
                "list: List the current tasks in your list.\n" +
                "bye: Saves any tasks in the list and quits the program.\n" +
                "todo: Add a To-Do task.\n" +
                "event: Add an event task.\n" +
                "deadline: Add a deadline task.\n" +
                "done: Mark task as done based on the task's number.\n" +
                "delete: Deletes task based on the task's number.\n" +
                "find: Find a task which matches your description.";
    }

    String readCommand() {
        System.out.println("Enter input:");
        return reader.hasNextLine() ? reader.nextLine() : "";
    }

    void showIntroduction() {
        System.out.println(introduction);
    }

    void showLine() {
        System.out.println("------------------------------------------------");
    }

    void showMessage(String message) {
        System.out.println(message);
    }

    void closeUi() {
        reader.close();
    }

    void showException(Exception e) {
        System.out.println(e);
    }

}
