import java.util.List;
import java.util.Scanner;

public class Duke {

    private Storage storage;

    private TaskManager manager;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            List<Task> tasks = storage.loadTasks();
            manager = new TaskManager(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            manager = new TaskManager();
        }
    }

    public void run() {
        manager.greet();   
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = sc.nextLine();
                manager.handleInput(input);
                List<Task> tasks = manager.getTasks();
                storage.saveTasks(tasks);
                isExit = manager.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        manager.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
