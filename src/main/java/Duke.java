import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    Storage storage;
    Ui ui;
    TaskList taskList;

    /**
     * Constructor for Duke.
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception e) {
            taskList = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/java/tasklist.txt");
        try {
            taskList = new TaskList(storage.loadData());
        } catch (Exception e) {
            taskList = new TaskList();
        }
    }

    /**
     * Run the program to interact with users.
     * @throws IOException
     * @throws IncorrectInputException
     */
    public void run() throws IOException, IncorrectInputException {
        ui.greeting();
        ui.interact();
    }

    public String command(String s) throws IOException, IncorrectInputException {
        try {
            int j = s.indexOf(' ');
            String firstWord = "";
            if (j > -1) {
                firstWord = s.substring(0, j);
            } else {
                firstWord = s;
            }
            switch (firstWord) {
                case "bye":
                    return "bye";
                case "done":
                    char x = s.charAt(s.length() - 1);
                    int i = Character.getNumericValue(x);
                    Task t = taskList.getList().get(i - 1);
                    t.markAsDone();
                    taskList.getList().set(i - 1, t);
                    return "Nice! I've marked this task as done:\n" + t.printTask();
                case "delete":
                    char y = s.charAt(s.length() - 1);
                    int k = Character.getNumericValue(y);
                    taskList.delete(k);
                    return "Now you have " + taskList.getList().size() +
                            (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list";
                case "deadline":
                    if (s.length() != "deadline".length()) {
                        String[] value = s.split(" /by ");
                        String date = value[1];
                        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormat);
                        dateTime.format(outputFormat);
                        Deadline deadline = new Deadline(value[0].replace("deadline ", ""), dateTime);

                        taskList.addTask(deadline);
                        storage.saveTask(deadline);
                        return "Got it. I've added this task:\n" + deadline.printTask() + "\n" +
                                "Now you have " + taskList.getList().size() +
                                (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list";
                    } else {
                        throw new IncorrectInputException("☹ OOPS!!! The description of an deadline cannot be empty.");
                    }
                case "todo":
                    if (s.length() != "todo".length()) {
                        ToDo toDo = new ToDo(s.replace("todo ", ""));
                        taskList.addTask(toDo);
                        storage.saveTask(toDo);
                        return "Got it. I've added this task:\n" + toDo.printTask() + "\n" +
                                "Now you have " + taskList.getList().size() +
                                (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list";
                    } else {
                        throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                case "event":
                    if (s.length() != "event".length()) {
                        String[] value = s.split(" /at ");
                        Event event = new Event(value[0].replace("event ", ""), value[1]);
                        taskList.addTask(event);
                        storage.saveTask(event);
                        return "Got it. I've added this task:\n" + event.printTask() + "\n" +
                                "Now you have " + taskList.getList().size() +
                                (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list";
                    } else {
                        throw new IncorrectInputException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                default:
                    throw new IncorrectInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "nothing much";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException, IncorrectInputException {
        return command(input);
    }

    public static void main(String[] args) throws IOException, IncorrectInputException {
        new Duke("src/main/java/tasklist.txt").run();
    }
}
