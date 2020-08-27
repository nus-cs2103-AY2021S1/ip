package duke;

import java.time.LocalDate;
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
            Commands command = null;
            try {
                command = Commands.valueOf(splitted[0].toUpperCase());
                this.processInput(command);
            } catch (UnknownCommandException ex) {
                System.out.println(ex);
                continue;
            }
            
            if (command.equals(Commands.BYE)) {
                break;
            } else if (command.equals(Commands.LIST)) {
                buildChatSeparator();
                System.out.println(" Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(" " + i + ". " + list.get(i - 1));
                }
                buildChatSeparator();
            } else if (command.equals(Commands.DONE)){
                try {
                    this.makeDone(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DELETE)){
                try {
                    this.deleteTask(Integer.parseInt(splitted[1]) - 1);
                } catch (ArrayIndexOutOfBoundsException | DukeErrorException ex) {
                    System.out.println(ex);
                }
            } else if (command.equals(Commands.DEADLINE)) {
                try {
                    this.addDeadline(splitted[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidDeadlineException ex) {
                    System.out.println(ex + ". ☹ Task deadline must be specified.");
                }
            } else if (command.equals(Commands.TODO)) {
                try {
                    this.addTodo(splitted[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidTodoException ex) {
                    System.out.println(ex + ". ☹ The description of a todo cannot be empty.");
                }
            } else if (command.equals(Commands.EVENT)) {
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

    public void processInput(Commands cmd) throws UnknownCommandException {
        if (!cmd.equals(Commands.BYE) &&
                !cmd.equals(Commands.EVENT) &&
                !cmd.equals(Commands.DEADLINE) &&
                !cmd.equals(Commands.DONE) &&
                !cmd.equals(Commands.LIST) &&
                !cmd.equals(Commands.TODO) &&
                !cmd.equals(Commands.DELETE)) {
            throw new UnknownCommandException();
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
        String[] event = str.split("/at");
        if (event.length > 2) {
            throw new InvalidEventException("☹ Event time must be written after `/at`.");
        }
        if (event[0].equals("")) {
            throw new InvalidEventException("☹ Event description must be specified.");
        }
        if (event[1].equals("")) {
            throw new InvalidEventException("☹ Event time must be specified.");
        }
        String description = event[0].trim();
        Event curr = new Event(description, false, LocalDate.parse(event[1].trim()));
        list.add(curr);
        this.describeTask(curr);
    }

    private void addTodo(String str) throws InvalidTodoException {
        String description = str.trim();
        if (description.equals("")) {
            throw new InvalidTodoException("☹ Todo description must be specified.");
        }
        Todo curr = new Todo(description, false);
        list.add(curr);
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
        String description = deadline[0].trim();
        Deadline curr = new Deadline(description, false, LocalDate.parse(deadline[1].trim()));
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
