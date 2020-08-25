import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    protected String lines = "____________________________________________________________";

    protected String greeting = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";

    protected ArrayList<Task> TaskList = new ArrayList<>();

    private void start() {
        System.out.println(greeting);
    }

    private void end() {
        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lines);
        System.exit(0);
    }

    private void provideList() {
        System.out.println(lines);
        System.out.println("Here are the tasks in your list:");
        for (Task task : this.TaskList) {
            String stringedIndex = Integer.toString(this.TaskList.indexOf(task) + 1);
            String outputLine = stringedIndex + ". " + task;
            System.out.println(outputLine);
        }
        System.out.println(lines);
    }

    private void markAsDone(String input) {
        String stringIndex = input.substring(5, input.length());
        int index = Integer.parseInt(stringIndex);
        Task chosen = this.TaskList.get(index - 1);
        chosen.finish();
        System.out.println(lines);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(chosen);
        System.out.println(lines);
    }

    private void delete(String input) throws EmptyListException, InvalidListIndexException {
        String stringIndex = input.substring(7, input.length());
        int index = Integer.parseInt(stringIndex);
        if (this.TaskList.isEmpty()) {
            throw new EmptyListException();
        } else if (index > 0 && index <= this.TaskList.size()) {
            Task chosen = this.TaskList.get(index - 1);
            this.TaskList.remove(chosen);
            System.out.println(lines);
            System.out.println(" Noted. I've removed this task: ");
            System.out.println(chosen);
            System.out.println(" Now you have " + Integer.toString(this.TaskList.size()) + " tasks in the list.");
            System.out.println(lines);
        } else {
            throw new InvalidListIndexException();
        }
    }

    private void addTaskToTasklist(Task task) {
        System.out.println(lines);
        System.out.println(" Got it. I've added this task: ");
        this.TaskList.add(task);
        System.out.println("  " + task);
        int listCount = this.TaskList.size();
        System.out.println(" Now you have " + Integer.toString(listCount) + " tasks in the list.");
        System.out.println(lines);
    }

    private void createAndAddTodo(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.length() < 5 || input.substring(5).replaceAll("\\s", "").equals("")) {
            throw new EmptyDescriptionException("todo");
        } else if (!Character.toString(input.charAt(4)).equals(" ")) {
            throw new WrongFormatException("todo");
        } else {
            Task task = new Todo(input.substring(5, input.length()));
            this.addTaskToTasklist(task);
        }
    }


    private void createAndAddDeadline(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.length() < 8 || input.substring(8).replaceAll("\\s", "").equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else if (input.contains("/by")
                && Character.toString(input.charAt(8)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
            String desc = input.substring(9, input.indexOf("/") - 1);
            String by = input.substring(input.indexOf("/") + 4, input.length());
            if (by.matches(".*[a-zA-Z]+.*")) {
                throw new WrongFormatException("deadline");
            }
            LocalDate date = LocalDate.parse(by);
            Task task = new Deadline(desc, date);
            this.addTaskToTasklist(task);
        } else {
            throw new WrongFormatException("deadline");
        }
    }

    private void createAndAddEvent(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.length() < 5 || input.substring(5).replaceAll("\\s", "").equals("")) {
            throw new EmptyDescriptionException("event");
        } else if (input.contains("/at")
                && Character.toString(input.charAt(5)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") + 3)).equals(" ")
                && Character.toString(input.charAt(input.indexOf("/") - 1)).equals(" ")) {
            String desc = input.substring(6, input.indexOf("/") - 1);
            String at = input.substring(input.indexOf("/") + 4, input.length());
            Task task = new Event(desc, at);
            this.addTaskToTasklist(task);
        } else {
            throw new WrongFormatException("event");
        }
    }



    private void newTaskEntry(String input) throws EmptyDescriptionException, WrongFormatException {
        if (input.contains("todo")) {
            this.createAndAddTodo(input);
        } else if (input.contains("deadline")) {
            this.createAndAddDeadline(input);
        } else if (input.contains("event")) {
            this.createAndAddEvent(input);
        }
    }

    private void processInput(String input) throws DukeException {
        if (input.equals("list")) {
            this.provideList();
        } else if (input.contains("done")) {
            this.markAsDone(input);
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            this.newTaskEntry(input);
        } else if (input.equals("bye")) {
            this.end();
        } else if (input.contains("delete")) {
            this.delete(input);
        } else {
            throw new InputNotRecognisedException();
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String input;
        duke.start();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            try {
                duke.processInput(input);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

        }
    }
}
