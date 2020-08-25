package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {

    Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void greetUser() {
        String logo = "Duke Melvin";
        System.out.println("Hello from\n" + logo + "\n" + "What can I do for you?");
    }

    public void sayGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        List<Task> ls = taskList.getTaskList();
        for (int x = 0; x < ls.size(); x++) {
            System.out.println(x + 1 + ":" + ls.get(x).toString());
        }
    }

    public String readCommand() {
        return in.nextLine();
    }
}
