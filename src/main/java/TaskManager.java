import java.util.Scanner;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    private final List<Task> tasks;

    private boolean isExit = false;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void initialise() {
        this.greet();
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = sc.nextLine();
                handleInput(input);
                this.saveTasks();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        this.exit();
    }

    private void greet() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void handleInput(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];
        String errorMessage = "";
        try {
            if (command.equals("bye")) { // Exits while loop
                isExit = true;
            } else if (command.equals("list")) {
                displayTasks();
            } else if (command.equals("done")) {
                markTaskAsDone(inputArray[1]);
            } else if (command.equals("todo")) {
                String description = inputArray[1];
                Task task = new Todo(description);
                addTask(task);
            } else if (command.equals("deadline")) {
                String[] deadline = inputArray[1].split("/by");
                String description = deadline[0].trim();
                String by = deadline[1].trim();
                Task task = new Deadline(description, by);
                addTask(task);
            } else if (command.equals("event")) {
                String[] event = inputArray[1].split("/at");
                String description = event[0].trim();
                String at = event[1].trim();
                System.out.println(at);
                Task task = new Event(description, at);
                addTask(task);
            } else if (command.equals("delete")) {
                deleteTask(inputArray[1]);
            } else {
                errorMessage = "Sorry! I don't know what that means...\n";
                throw new DukeException(errorMessage);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (inputArray.length <= 1) { // Entered keyword without description/task number
                if (isTask(command)) {
                    errorMessage = "OOPS!!! Description of a task cannot be empty :(\n";
                } else if (command.equals("done") || command.equals("delete")) {
                    errorMessage = "Missing task number! " + "Please ensure to key in the task number :)\n";
                }
            } else { // Deadline/Event missing their respective keywords
                if (command.equals("deadline")) {
                    errorMessage = "Please indicate a deadline using the \"/by\" keyword.\n";
                } else if (command.equals("event")) {
                    errorMessage = "Please indicate a timing using the \"/at\" keyword.\n";
                }
            }
            throw new DukeException(errorMessage);
        }
    }

    private boolean isTask(String command) {
        return command.equals("todo") || command.equals("deadline") || command.equals("event");
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("Okay! Task added for you!");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list." + "\n");
    }

    private void deleteTask(String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = tasks.remove(index);
            System.out.println("Noted. The following task is removed:");
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " task(s) in the list." + "\n");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // Invalid task number or number out of range
            String errorMessage = "Invalid task number! " + "Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }

    private void markTaskAsDone(String taskNumber) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            System.out.println("Good job! I've marked this task as done:");
            System.out.println(task + "\n");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // Invalid task number or number out of range
            String errorMessage = "Invalid task number! " + "Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }

    private void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added to your list yet!\n");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println("");
        }
    }

    private void saveTasks() {
        try {
            FileWriter myWriter = new FileWriter("data/duke.txt");
            for (Task task : tasks) {
                char letter = task.toString().charAt(1);
                int bit = task.isDone() ? 1 : 0;
                String description = task.getDescription();
                String data;
                if (letter == 'T') {
                    data = letter + " | " + bit + " | " + description;
                } else if (letter == 'D') {
                    String time = task.getTime();
                    data = letter + " | " + bit + " | " + description + " | " + time; 
                } else { // letter == 'E'
                    String time = task.getTime();
                    data = letter + " | " + bit + " | " + description + " | " + time; 
                }
                myWriter.write(data + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}