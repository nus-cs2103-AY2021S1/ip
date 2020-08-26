import java.text.ParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

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
                    try {
                        int index = Integer.parseInt(split[1]) - 1;
                        tasks.markTaskAsDone(index);
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tPaise! :') Please use the correct format: done <order of task in the list>");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("\tPaise! :') The index is out of bounds!");
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    if (split.length < 2) {
                        throw new DukeException("\tSorry! The description of a todo cannot be empty :')");
                    }
                    tasks.addTask(split[0], split[1]);
                    break;
                case "delete":
                    try {
                        int index = Integer.parseInt(split[1]) - 1;
                        tasks.deleteTask(index);
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tPaise! :') Please use the correct format: delete <order of task in the list>");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("\tPaise! :') The index is out of bounds!");
                    }
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
