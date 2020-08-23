import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String filePath = "/data/duke.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    public static void readFile() {
        String folderPath = "/data";
        storage = new Storage(filePath, folderPath);
        try {
            ArrayList<String> taskList = storage.load();
            tasks = new TaskList(taskList);
        } catch (DukeException ex) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void writeFile() {
        storage.save(tasks.tasks);
    }

    private static void add(String task) throws DukeException{
        String echoizer = "\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t %s\n"
                + "\t Now you have %d tasks in the list.\n"
                + "\t____________________________________________________________\n";
        Task t = tasks.addTask(task);
        System.out.printf((echoizer) + "%n", t, tasks.size());
    }

    private static void list() {
        String tasksString = tasks.listTasks();
        ui.showAction("\t Here are the tasks in your list:\n" + tasksString);
    }

    private static void exit() {
        ui.showFarewell();
        writeFile();
        System.exit(0);
    }

    private static void done(int i) throws DukeException {
        try {
            Task t = tasks.tasks.get(i - 1);
            t.markAsDone();
            ui.showAction(String.format("\t Nice! I've marked this task as done:\n"
                    + "\t   %s\n", t));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't complete a task that does not exist.");
        }
    }

    private static void delete(int i) throws DukeException {
        try {
            Task t = tasks.tasks.get(i - 1);
            tasks.tasks.remove(i - 1);
            ui.showAction(String.format("\t Noted. I've removed this task:\n"
                    + "\t   %s\n"
                    + "\t Now you have %d tasks in the list.\n", t, tasks.size()));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't delete a task that does not exist.");
        }
    }

    public static void main(String[] args) {
        ui = new Ui();
        ui.showWelcome();
        readFile();
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNext()) {
            try {
                input = sc.nextLine();

                if (input.equals("bye")) {
                    exit();
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("done")) {
                    done(Integer.parseInt(input.split(" ")[1]));
                } else if (input.startsWith("delete")) {
                    delete(Integer.parseInt(input.split(" ")[1]));
                } else {
                    add(input);
                }
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            }
        }
    }
}
