package duke;

import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

import exception.*;

public class Duke {
    Scanner sc;
    ArrayList<Task> list;
    String input;

    public Duke() {
        sc = new Scanner(System.in);
        list = new ArrayList<>();
    }

    public void start() {
        Duke.introduce();
        interact();
        Duke.bye();
    }

    public static void buildChatSeparator() {
        System.out.println("____________________________________________________________");
    }

    public void interact() {
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            String[] splitted = input.split(" ", 2);
            try {
                this.processInput(splitted[0].trim());
            } catch (DukeErrorException ex) {
                System.out.println(ex);
                continue;
            }
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                buildChatSeparator();
                System.out.println(" Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(" " + i + ". " + list.get(i - 1));
                }
                buildChatSeparator();
            } else if (splitted[0].equals("done")){
                try {
                    this.makeDone(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (splitted[0].equals("delete")){
                try {
                    this.deleteTask(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (splitted[0].equals("deadline")) {
                try {
                    this.addDeadline(splitted[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidDeadlineException ex) {
                    System.out.println(ex + ". ☹ Task deadline must be specified.");
                }
            } else if (splitted[0].equals("todo")) {
                try {
                    this.addTodo(splitted[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidTodoException ex) {
                    System.out.println(ex + ". ☹ The description of a todo cannot be empty.");
                }
            } else if (splitted[0].equals("event")) {
                try {
                    this.addEvent(splitted[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidEventException ex) {
                    System.out.println(ex + ". ☹ The description of an event cannot be empty.");
                }
            } else {
                buildChatSeparator();
                System.out.println("added: " + input);
                buildChatSeparator();
                list.add(new Task(input, false));
            }
        }
    }

    private void deleteTask(int index) throws DukeErrorException {
        if (index >= list.size() || index < 0) {
            throw new DukeErrorException("Operation: delete " + (index + 1) + " fails ☹.");
        }
        Task deleted = list.remove(index);
        buildChatSeparator();
        System.out.println(" Noted. I've removed this task: ");
        System.out.println(" " + deleted);
        System.out.println(" Now you have " + (list.size() <= 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.");
        buildChatSeparator();
    }

    public void processInput(String str) throws DukeErrorException {
        if (!str.equals("todo") &&
                !str.equals("event") &&
                !str.equals("deadline") &&
                !str.equals("done") &&
                !str.equals("list") &&
                !str.equals("done") &&
                !str.equals("delete")) {
            throw new DukeErrorException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void makeDone(int index) throws DukeErrorException {
        if (index >= list.size() || index < 0) {
            throw new DukeErrorException("Operation: done " + (index + 1) + " fails ☹.");
        }
        list.set(index, list.get(index).completeTask());
        buildChatSeparator();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + list.get(index));
        buildChatSeparator();
    }

    private void addEvent(String str) throws InvalidEventException {
        String[] deadline = str.split("/at");
        if (deadline.length > 2) {
            throw new InvalidEventException("☹ Event time must be written after `/at`.");
        }
        if (deadline[0].equals("")) {
            throw new InvalidEventException("☹ Event description must be specified.");
        }
        if (deadline[1].equals("")) {
            throw new InvalidEventException("☹ Event time must be specified.");
        }
        String description = deadline[0].trim() + " (at: " + deadline[1].trim() + ")";
        Event curr = new Event(description, false);
        list.add(curr);
        this.describeTask(curr);
    }

    private void addTodo(String str) throws InvalidTodoException {
        String description = str.trim();
        if (description.equals("")) {
            throw new InvalidTodoException("☹ Todo description must be specified.");
        }
        Deadline curr = new Deadline(description, false);
        list.add(curr);
        buildChatSeparator();
        this.describeTask(curr);
    }

    private void addDeadline(String str) throws InvalidDeadlineException {
        String[] deadline = str.split("/by");
        if (deadline.length > 2) {
            throw new InvalidDeadlineException("☹ Task deadline must be written after `/by`.");
        }
        if (deadline[0].equals("")) {
            throw new InvalidDeadlineException("☹ Task description must be specified.");
        }
        if (deadline[1].equals("")) {
            throw new InvalidDeadlineException("☹ Task deadline must be specified.");
        }
        String description = deadline[0].trim() + " (by: " + deadline[1].trim() + ")";
        Deadline curr = new Deadline(description, false);
        list.add(curr);
        this.describeTask(curr);
    }

    public void describeTask(Task curr) {
        buildChatSeparator();
        System.out.println(" Got it. I've added this task: ");
        System.out.println(" " + curr);
        System.out.println(" Now you have " + (list.size() == 1
                ? list.size() + " task"
                : list.size() + " tasks")
                + " in the list.");
        buildChatSeparator();
    }

    public static void introduce() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(introduction);
    }

    public static void bye() {
        buildChatSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        buildChatSeparator();
    }
}
