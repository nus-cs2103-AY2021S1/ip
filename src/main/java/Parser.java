import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public String line = "____________________________________________________________";
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Parser.
     */
    public Parser() {
        storage = new Storage("src/main/java/tasklist.txt");
        taskList = new TaskList();
    }

    /**
     * Greetings to welcome the user.
     */
    public void greeting() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println(line);
    }

    /**
     * Generate the list of tasks.
     */
    public void generateList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getList().size(); i++) {
            Task t = taskList.getList().get(i);
            System.out.println((i + 1) + ". " + t.printTask());
        }
        System.out.println(line);
    }

    public void generateSearch(ArrayList<Task> tasks) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + ". " + t.printTask());
        }
        System.out.println(line);

    }

    /**
     * Parse users' commands and respond to them.
     * @param s
     * @throws IOException
     * @throws IncorrectInputException
     */
    public void parse(String s) throws IOException, IncorrectInputException {
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
                exit();
                break;
            case "list":
                generateList();
                break;
            case "find":
                ArrayList<Task> hold = new ArrayList<>();
                String d = s.substring(j + 1);
                for (Task t : taskList.getList()) {
                    if (t.name.contains(d)) {
                        hold.add(t);
                    }
                }
                generateSearch(hold);
                break;
            case "done":
                char x = s.charAt(s.length() - 1);
                int i = Character.getNumericValue(x);
                Task t = taskList.getList().get(i - 1);
                t.markAsDone();
                taskList.getList().set(i - 1, t);
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t.printTask());
                System.out.println(line);
                break;
            case "delete":
                char y = s.charAt(s.length() - 1);
                int k = Character.getNumericValue(y);
                System.out.println(line);
                System.out.println("Noted. I've removed this task:");
                System.out.println(taskList.getList().get(k - 1).printTask());
                taskList.delete(k);
                System.out.println("Now you have " + taskList.getList().size() +
                        (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list");
                System.out.println(line);
                break;
            case "deadline":
                if (s.length() != "deadline".length()) {
                    String[] value = s.split(" /by ");
                    String date = value[1];
                    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(date, inputFormat);
                    dateTime.format(outputFormat);
                    Deadline deadline = new Deadline(value[0].replace("deadline ", ""), dateTime);
//                list.add(deadline);
                    taskList.addTask(deadline);
                    storage.saveTask(deadline);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline.printTask());
                    System.out.println("Now you have " + taskList.getList().size() +
                            (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list");
                    System.out.println(line);
                    break;
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of an deadline cannot be empty.");
                }
            case "todo":
                if (s.length() != "todo".length()) {
                    ToDo toDo = new ToDo(s.replace("todo ", ""));
//                list.add(toDo);
                    taskList.addTask(toDo);
                    storage.saveTask(toDo);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(toDo.printTask());
                    System.out.println("Now you have " + taskList.getList().size() +
                            (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list");
                    System.out.println(line);
                    break;
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            case "event":
                if (s.length() != "event".length()) {
                    String[] value = s.split(" /at ");
                    Event event = new Event(value[0].replace("event ", ""), value[1]);
//                list.add(event);
                    taskList.addTask(event);
                    storage.saveTask(event);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event.printTask());
                    System.out.println("Now you have " + taskList.getList().size() +
                            (taskList.getList().size() > 1 ? " tasks" : " task") + " on the list");
                    System.out.println(line);
                    break;
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of an event cannot be empty.");
                }
            default:
                throw new IncorrectInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Leave a goodbye message.
     */
    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
