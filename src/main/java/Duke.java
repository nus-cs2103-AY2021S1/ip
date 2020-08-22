import exceptions.DukeException;
import exceptions.DukeInvalidMessageException;
import exceptions.DukeUnknownCommandException;
import exceptions.DukeEmptyMessageException;

import java.io.*;
import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "--------------------------------------";
    static final String GREETING = "Hello Boss! How can I help you?";
    static final String BYE = "Bye Boss! Hope to see you again!";
    static final String SHOW_TASK = "Here are the tasks in your list:";
    static final String TAB = "   ";

    public static void main(String[] args) throws IOException {

        handleLoad();
        System.out.println(GREETING);
        handleList();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            try {
                String toEcho = sc.nextLine();
                System.out.println(HORIZONTAL_LINE);
                String[] command = toEcho.split(" ", 2);
                if (toEcho.equals("bye")) {
                    saveTasks();
                    System.out.println(BYE + "\n" + HORIZONTAL_LINE);
                } else if (toEcho.equals("list")) {
                    handleList();
                } else if (toEcho.startsWith("done")) {
                    if (toEcho.length() == 4) {
                        throw new DukeEmptyMessageException("Done");
                    } else if (Integer.parseInt(command[1]) > Task.tasks.size()) {
                        throw new DukeInvalidMessageException();
                    } else {
                        int index = Integer.parseInt(command[1]) - 1;
                        System.out.println("Nice! I've marked this task as done:");
                        Task.tasks.get(index).markAsDone();
                        System.out.println(TAB + Task.tasks.get(index));
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else if (toEcho.startsWith("todo")) {
                    if (toEcho.length() == 4) {
                        throw new DukeEmptyMessageException("Todo");
                    }
                    handleTodo(command[1]);
                } else if (toEcho.startsWith("deadline")) {
                    if (toEcho.length() == 8) {
                        throw new DukeEmptyMessageException("Deadline");
                    }
                    handleDeadline(command[1]);
                } else if (toEcho.startsWith("event")) {
                    if (toEcho.length() == 5) {
                        throw new DukeEmptyMessageException("Event");
                    }
                    handleEvent(command[1]);
                } else if (toEcho.startsWith("delete")) {
                    if (toEcho.length() == 6) {
                        throw new DukeEmptyMessageException("Delete");
                    } else if (Integer.parseInt(command[1]) > Task.tasks.size() ||
                        Integer.parseInt(command[1]) < 0) {
                        throw new DukeInvalidMessageException();
                    }
                    System.out.println("Noted. I've removed this task:");
                    int indexToDelete = Integer.parseInt(command[1]) - 1;
                    System.out.println(TAB + Task.tasks.get(indexToDelete));
                    Task.tasks.remove(indexToDelete);
                    System.out.println("Now you have " + Task.tasks.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else {
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n" + HORIZONTAL_LINE);
            }
        }
    }

    public static void saveTasks() throws IOException {
        BufferedWriter taskWriter = new BufferedWriter(new FileWriter(".//text-ui-test/saved-tasks.txt"));
        String tasks = "";
        for (Task task: Task.tasks) {
            tasks += task.toSaveString() + "\n";
        }
        taskWriter.write(tasks);
        taskWriter.close();
    }

    public static void handleList() {
        System.out.println(SHOW_TASK);
        for (int i = 0; i < Task.tasks.size(); i++) {
            int number = i + 1;
            System.out.println(number + "." + Task.tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void handleTodo(String description) {
        System.out.println("Got it. I've added this task:");
        Todo todo = new Todo(description);
        Task.tasks.add(todo);
        System.out.println(TAB + todo);
        System.out.println("Now you have " + Task.tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void handleDeadline(String description) {
        String[] strArr = description.split("/by", 2);
        String todo = strArr[0];
        String time = strArr[1];
        Deadline deadline = new Deadline(todo, time);
        System.out.println("Got it. I've added this task:");
        Task.tasks.add(deadline);
        System.out.println(TAB + deadline);
        System.out.println("Now you have " + Task.tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void handleEvent(String description) {
        String[] strArr = description.split("/at", 2);
        String todo = strArr[0];
        String time = strArr[1];
        Event event = new Event(todo, time);
        System.out.println("Got it. I've added this task:");
        Task.tasks.add(event);
        System.out.println(TAB + event);
        System.out.println("Now you have " + Task.tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void handleLoad() throws IOException {
        BufferedReader taskLoader = new BufferedReader(new FileReader(".//text-ui-test/saved-tasks.txt"));
        String longCommand = taskLoader.readLine();
        while (longCommand != null) {
            String[] keywords = longCommand.split(" \\|\\| ");
            Task cur = null;
            switch (keywords[1]) {
                case "todo":
                    cur = new Todo(keywords[2]);
                    break;
                case "deadline":
                    cur = new Deadline(keywords[2], keywords[3]);
                    break;
                case "event":
                    cur = new Event(keywords[2], keywords[3]);
                    break;
                default:
                    System.out.println("error");
                    break;
            }

            if (keywords[0].equals("1")) {
                cur.markAsDone();
            }
            Task.tasks.add(cur);
            longCommand = taskLoader.readLine();
        }
        taskLoader.close();
    }
}
