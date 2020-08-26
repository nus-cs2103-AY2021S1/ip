import java.io.IOException;
import java.util.Scanner;

public class Ui {

    String wrapMessage(String message) {
        String line = "----------------------------------------------";
        return line + "\n " + message + "\n" + line;
    }

    void greet() {
        String greeting = "Hello! I'm Duke\n What can I do for you? (◠  ◠✿)";
        System.out.println(wrapMessage(greeting));
    }

    String readInput() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        return str;
    }

    void exit() {
        String byeMessage = "Bye! ( ´ ▽ ` )/";
        System.out.println(wrapMessage(byeMessage));
        System.exit(0);
    }

    void addTask(Task task, int size) {
        String message = "Got it. I've added this task: \n  " + task +
                "\n Now you have " + size + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    void completeTask(Task task) {
        String message = "Nice! I've marked this task as done: \n  " + task;
        System.out.println(wrapMessage(message));
    }

    void deleteTask(Task task, int size) {
        String message = "Noted. I've removed this task: \n " + task +
                "\n Now you have " + size + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    void list(String formattedList) {
        String list = "Here are the tasks in your list: \n  ";
        list += formattedList;
        System.out.println(wrapMessage(list));
    }

    void showDukeError(DukeException e) {
        System.out.println(wrapMessage(e.toString()));
    }

    void showSaveError() {
        System.out.println(wrapMessage("Something went wrong while saving your tasks..."));
    }

    void showLoadError() {
        System.out.println(wrapMessage("Something went wrong while loading your tasks..."));
    }
}
