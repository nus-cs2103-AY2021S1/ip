import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {
    private String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private String outputFormat = "  %s\n";
    private boolean ongoing;
    private ArrayList<Task> todoList;
    private int numberOfTasks;
    private String filePath = "src/data/duke.txt";

    Duke() {
        ongoing = false;
        todoList = new ArrayList<>();
        getTodoList();
    }

    public void getTodoList() {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task = s.nextLine();
                formatStringToTask(task);
            }
            numberOfTasks = todoList.size();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void formatStringToTask(String task) {
        String[] split = task.split(" ");
        String body = task.substring(6, task.length());
        try {
            switch (split[0]) {
                case "[T][O]":
                    addTodoItem(body, true);
                    break;
                case "[T][X]":
                    addTodoItem(body, false);
                    break;
                case "[D][O]":
                    addDeadline(body, true);
                    break;
                case "[D][X]":
                    addDeadline(body, false);
                    break;
                case "[E][O]":
                    addEvent(body, true);
                    break;
                default:
                    addEvent(body, false);
            }
        } catch (DukeException e) {
            System.out.printf(outputFormat, e.getMessage());
        }
    }

    public void overwriteTodoList() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : todoList) {
                fw.write(formatTaskToString(task) + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String formatTaskToString(Task task) {
        String item = task.getItem();
        String[] split = item.split(" ");
        switch (split[0]) {
            case "[T][O]":
            case "[T][X]":
                return task.getItem();
            case "[D][O]":
            case "[D][X]":
                String deadlineTask = task.getItem();
                return deadlineTask.substring(0, deadlineTask.length()-1).replace("(by: ", "/by ");
            default:
                String eventTask = task.getItem();
                return eventTask.substring(0, eventTask.length()-1).replace("(at: ", "/at ");
        }
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
                overwriteTodoList();
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
            System.out.println("    " + Integer.toString(count) + ". " + item.getItem());
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
            System.out.println(addTodoItem(item, false));
        } else if (instruction.equals("deadline")) {
            System.out.println(addDeadline(item, false));
        } else {
            System.out.println(addEvent(item, false));
        }
        System.out.printf(outputFormat, "New todo item added to the list!");
        numberOfTasks += 1;
        System.out.printf(outputFormat, "There are now " + Integer.toString(numberOfTasks) + " todo items in the list");
    }

    public String addTodoItem(String item, boolean completed) {
        Todo newTask = new Todo(item, completed);
        todoList.add(newTask);
        return "    " + newTask.getItem();
    }

    public String addDeadline(String item, boolean completed) throws DukeException {
        String[] splitItem = item.split("/by ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a deadline to finish task by.");
        }
        Deadline newTask = new Deadline(splitItem[0], splitItem[1], completed);
        todoList.add(newTask);
        return "    " + newTask.getItem();
    }

    public String addEvent(String item, boolean completed) throws DukeException {
        String[] splitItem = item.split("/at ");
        if (splitItem.length == 1) {
            throw new DukeException("Incorrect format. Please add a time/date the event is held at.");
        }
        Event newTask = new Event(splitItem[0], splitItem[1], completed);
        todoList.add(newTask);
        return "    " + newTask.getItem();
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
