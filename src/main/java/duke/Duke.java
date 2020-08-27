package duke; 

import java.util.Scanner;
import duke.task.TaskList; 
import duke.storage.Storage; 
import duke.ui.Ui;

public class Duke {
    private TaskList tasks;
    private Scanner sc;
    private Storage storage;

    public Duke() {
        this.storage = new Storage();
        this.tasks = new TaskList(storage.loadTasks());
        this.sc = new Scanner(System.in);
    }

    public void run() {
        Ui.sayHello();
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] split = input.split(" ", 2);

                switch (split[0]) {
                case "bye":
                    Ui.sayBye();
                    break;
                case "list":
                    tasks.displayTasks();
                    break;
                case "done":
                    tasks.markTaskAsDone(Integer.parseInt(split[1]) - 1);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    tasks.addTask(input);
                    break;
                case "delete":
                    tasks.deleteTask(Integer.parseInt(split[1]) - 1);
                    break;
                default:
                    throw new DukeException("\tApologies! I do not understand what that means :')");
                }

                if (split[0].equals("bye")) {
                    break;
                }

                storage.saveTasks(tasks.getTasks());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
