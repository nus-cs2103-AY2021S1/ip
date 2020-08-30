package duke;

import java.util.Scanner;

public class Parser {
    Scanner in;
    TaskList taskList;

    Parser(TaskList taskList) {
        in = new Scanner(System.in);
        this.taskList = taskList;
    }

    void run() {
        while (in.hasNextLine()) {
            try {
                String command = in.nextLine();
                if (command.equals("bye")) {
                    Ui.exit();
                    break;
                } else if (command.equals("list")) {
                    taskList.list();
                } else if (command.startsWith("done")) {
                    taskList.markDone(command.substring(5));
                } else if (command.startsWith("deadline") || command.startsWith("event") || command.startsWith("todo")) {
                    taskList.addTask(command);
                } else if (command.startsWith("delete")) {
                    taskList.delete(command.substring(7));
                } else {
                    throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IllegalArgumentException e) {
                Ui.printException(e);
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printException(new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
        }
    }
}
