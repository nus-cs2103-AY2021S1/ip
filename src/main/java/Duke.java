import java.util.Scanner;

public class Duke {
    private static final String HOME_DIRECTORY = System.getProperty("user.dir") + "/data/";
    private static final String FILE_NAME = "duke.txt";
    static Scanner scanner = new Scanner(System.in);
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(filePath));
        } catch (DukeException e) {
//            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        showGreeting();
        String[] inputArr = getInputArr();
        String command = inputArr[0];
        while (!command.equals("bye")) {
            switch (command) {
            case "list":
                showList();
                break;
            case "done":
                doneTask(inputArr);
                break;
            case "deadline":
                try {
                    addTask("deadline", inputArr);
                } catch (DukeException e) {
                    String message = "Sorry!! Deadline is missing description or date/time.";
                    System.out.println(wrapMessage(message));
                }
                break;
            case "event":
                try {
                    addTask("event", inputArr);
                } catch (DukeException e) {
                    String message = "Sorry!! Event is missing description or date/time.";
                    System.out.println(wrapMessage(message));
                }
                break;
            case "todo":
                try {
                    addTask("todo", inputArr);
                } catch (DukeException e) {
                    String message = "Sorry!! The description of a todo cannot be empty.";
                    System.out.println(wrapMessage(message));
                }
                break;
            case "delete":
                deleteTask(inputArr);
                break;
            default:
                String message = "Sorry I dont understand!! Please give a proper command.";
                System.out.println(wrapMessage(message));
            }
            inputArr = getInputArr();
            command = inputArr[0];
        }
        showGoodbye();
    }

    public static void main(String[] args) {
        new Duke(HOME_DIRECTORY + FILE_NAME).run();
    }

    private String[] getInputArr() {
        String input = scanner.nextLine();
        return input.split("\\s+");
    }

    private void showGreeting() {
        String message = "Eh what's up\n"
                + "What do you want?";
        System.out.println(wrapMessage(message));
    }

    private void showGoodbye() {
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            System.out.println("Cannot write to file!");
        }
        String message = "Alright I'll see you around!";
        System.out.println(wrapMessage(message));
    }

    private void showList() {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            message += (i + 1)
                    + ". "
                    + task
                    +"\n";
        }
        System.out.println(wrapMessage(message));
    }

    private void addTask(String type, String[] inputArr) throws DukeException {
        String dateTime = "";
        String desc = "";
        Task task;

        if (inputArr.length < 2) {
            throw new DukeException();
        }

        switch (type) {
        case "deadline":
            desc =  getTaskDescription(inputArr);
            dateTime = getTaskTimeDate(inputArr);
            task = new Deadline(desc, dateTime);
            break;
        case "event":
            desc =  getTaskDescription(inputArr);
            dateTime = getTaskTimeDate(inputArr);
            task = new Event(desc, dateTime);
            break;
        case "todo":
            desc =  getTaskDescription(inputArr);
            task = new Todo(desc);
            break;
        default:
            throw new IllegalStateException("Unexpected type value: " + type);
        }

        tasks.addTask(task);

        String message = "Got it. I've added this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    private String getTaskDescription(String[] inputArr) {
        String desc = "";
        int i = 1;
        while ((i < inputArr.length) && (!inputArr[i].contains("/by")) && (!inputArr[i].contains("/at"))) {
            // Get description of the task, which is after the command in inputArr
            desc = desc + inputArr[i] + " ";
            i++;
        }
        return desc.substring(0, desc.length() - 1);
    }

    private String getTaskTimeDate(String[] inputArr) {
        String dateTime = "";
        int i = 0;
        while (!inputArr[i].contains("/by") && (!inputArr[i].contains("/at"))) {
            // Get description of the task, which is after the command in inputArr
            i++;
        }
        i++;
        while (i < inputArr.length) {
            dateTime = dateTime + inputArr[i] + " ";
            i++;
        }
        return dateTime.substring(0, dateTime.length() - 1);
    }

    private void deleteTask(String[] inputArr) {
        String lastChar = inputArr[inputArr.length - 1];
        int i = Integer.parseInt(lastChar);
        Task task = tasks.getTask(i - 1);
        tasks.removeTask(task);
        String message = "Noted! I've removed this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        System.out.println(wrapMessage(message));
    }

    private void doneTask(String[] inputArr) {
        String lastChar = inputArr[inputArr.length - 1];
        int i = Integer.parseInt(lastChar);
        Task task = tasks.getTask(i - 1);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:\n"
                + task;
        System.out.println(wrapMessage(message));
    }

    private String wrapMessage(String message) {
        if (message.endsWith("\n")) {
            // If the message ends with a newline, remove the newline
            message = message.substring(0, message.length() - 1);
        }

        String line = "____________________________________________________________\n";
        return line
                + message
                + "\n"
                + line;
    }
}