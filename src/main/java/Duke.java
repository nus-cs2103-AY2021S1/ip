import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Duke {
    private String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private String outputFormat = "  %s\n";
    private String taskFormat = "    %s\n";
    private boolean ongoing;
    private ArrayList<Task> todoList;
    private int numberOfTasks;

    Duke() {
        ongoing = false;
        todoList = new ArrayList<>();
    }

    public void run() {
        ongoing = true;
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (ongoing) {
            String input = scanner.nextLine();
            systemOutput(input);
        }
    }

    public void systemOutput(String input) {
        System.out.println(line + "\n");
        try {
            scenarios(input);
        } catch (DukeException e) {
            System.out.printf(outputFormat, e.getMessage());
        }
        System.out.println("\n" + line);
    }

    public void scenarios(String input) throws DukeException {
        String[] instructions = input.split(" ");
        String command = instructions[0];
        switch (command) {
            case "bye":
                ongoing = false;
                goodBye();
                break;
            case "list":
                showList();
                break;
            case "done":
                completeItem(input);
                break;
            case "delete":
                deleteItem(input);
                break;
            case "todo":
                String todo = input.substring(4, input.length());
                addItem(command, todo);
                break;
            case "deadline":
                String deadline = input.substring(8, input.length());
                addItem(command, deadline);
                break;
            case "event": {
                String event = input.substring(5, input.length());
                addItem(command, event);
                break;
            }
            default:
                throw new DukeException("Oops! I'm sorry but I have no idea what that means >.<");

        }
    }

    public void showList() {
        int count = 1;
        System.out.printf(outputFormat, "The tasks in your Todo List:");
        for (Task item : todoList) {
            System.out.printf(taskFormat, Integer.toString(count) + ". " + item.getItem());
            count += 1;
        }
    }

    public void completeItem(String input) throws DukeException {
        String indexString = input.split(" ")[1];
        try {
            int index = Integer.valueOf(indexString) - 1;
            Task item = todoList.get(index);
            item.completeTask();
            System.out.printf(outputFormat, "   \\\\(^o^)/ *.*.* \\\\(^o^)/");
            System.out.printf(outputFormat, "Yay! This task has been completed:");
            System.out.printf(outputFormat, "  " + item.getItem());
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    public void deleteItem(String input) throws DukeException {
        String indexString = input.split(" ")[1];
        try {
            int index = Integer.valueOf(indexString) - 1;
            Task item = todoList.get(index);
            System.out.printf(outputFormat, "Noted. This task has now been removed from the list:");
            System.out.printf(outputFormat, "  " + item.getItem());
            todoList.remove(index);
            numberOfTasks -= 1;
            System.out.printf(outputFormat, "There are now " + Integer.toString(numberOfTasks) + " todo items in the list");
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    public void addItem(String instruction, String item) throws DukeException {
        if (item.equals("") || item.equals(" ")) {
            throw new DukeException("Oops! The description cannot be empty >.<");
        }
        if (instruction.equals("todo")) {
            addTodoItem(item);
        } else if (instruction.equals("deadline")) {
            addDeadline(item);
        } else {
            addEvent(item);
        }
        System.out.printf(outputFormat, "New todo item added to the list!");
        numberOfTasks += 1;
        System.out.printf(outputFormat, "There are now " + Integer.toString(numberOfTasks) + " todo items in the list");
    }

    public void addTodoItem(String item) {
        Todo newTask = new Todo(item);
        todoList.add(newTask);
        System.out.printf(taskFormat, newTask.getItem());
    }

    public void addDeadline(String item) throws DukeException {
        String[] splitItem = item.split("/by ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a deadline to finish task by.");
        }
        LocalDate dueDate = Deadline.formatDate(splitItem[1]);
        String dateTime = Deadline.formatDateString(dueDate, splitItem[1]);
        Deadline newTask = new Deadline(splitItem[0], dateTime, dueDate, splitItem[1]);
        todoList.add(newTask);
        System.out.printf(taskFormat, newTask.getItem());
    }

    public void addEvent(String item) throws DukeException {
        String[] splitItem = item.split("/at ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a time/date the event is held at.");
        }
        LocalDate dueDate = Deadline.formatDate(splitItem[1]);
        String dateTime = Deadline.formatDateString(dueDate, splitItem[1]);
        Event newTask = new Event(splitItem[0], dateTime, dueDate, splitItem[1]);
        todoList.add(newTask);
        System.out.printf(taskFormat, newTask.getItem());
    }

    public void greeting() {
        System.out.println(line + "\n         (^v^)");
        System.out.printf(outputFormat, "Hey there! I'm JavaDuke");
        System.out.printf(outputFormat, "What can I do for you?\n" + line);
    }

    public void goodBye() {
        System.out.printf(outputFormat, "          *(^v^)");
        System.out.printf(outputFormat, "Bye. Hope to see you again soon!");
    }



    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        Duke duke = new Duke();
        duke.run();
    }
}
