import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static String filePath = "/data/duke.txt";
    private static Ui ui;
    private static Storage storage;

    public static void readFile() {
        String folderPath = "/data";
        storage = new Storage(filePath, folderPath);
        try {
            ArrayList<String> taskList = storage.load();
            for (String taskString : taskList) {
                String[] taskLine = taskString.split("~");
                Task task = null;
                if (taskLine[0].equals("T")) {
                    task = new ToDo(taskLine[2]);

                } else if (taskLine[0].equals("D")) {
                    task = new Deadline(taskLine[2], taskLine[3]);
                } else if (taskLine[0].equals("E")) {
                    task = new Event(taskLine[2], taskLine[3]);
                }
                if (taskLine[1].equals("1")) {
                    assert task != null;
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (DukeException ex) {
            ui.showLoadingError();
        }
    }

    public static void writeFile() {
        storage.save(tasks);
    }

    private static void add(String task) throws DukeException{
        String echoizer = "\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t %s\n"
                + "\t Now you have %d tasks in the list.\n"
                + "\t____________________________________________________________\n";
        if (task.startsWith("todo")) {
            if (task.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            tasks.add(new ToDo(task.substring(5)));
        } else if (task.startsWith("deadline")) {
            if (task.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] taskArr = task.substring(9).split(" /by ");
            try {
                tasks.add(new Deadline(taskArr[0], taskArr[1]));
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of a deadline.");
            }
        } else if (task.startsWith("event")) {
            if (task.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] taskArr = task.substring(6).split(" /at ");
            try {
                tasks.add(new Event(taskArr[0], taskArr[1]));
            } catch (IndexOutOfBoundsException ex) {
                throw new DukeException("Invalid description of an event.");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        System.out.printf((echoizer) + "%n", tasks.get(tasks.size() - 1), tasks.size());
    }

    private static void list() {
        ui.showLine();
        System.out.print("\t Here are the tasks in your list:\n");
        Task t;
        for (int i = 0; i < tasks.size(); i++) {
            t = tasks.get(i);
            System.out.printf("\t %d.%s%n", i + 1, t);
        }
        ui.showLine();
    }

    private static void exit() {
        ui.showFarewell();
        writeFile();
        System.exit(0);
    }

    private static void done(int i) throws DukeException {
        try {
            Task t = tasks.get(i - 1);
            t.markAsDone();
            ui.showAction(String.format("\t Nice! I've marked this task as done:\n"
                    + "\t   %s\n", t));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't complete a task that does not exist.");
        }
    }

    private static void delete(int i) throws DukeException {
        try {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
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
