import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> tasks = new ArrayList<>();

    private static String filePath = "../../../data/duke.txt";

    void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void completeTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        task.done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    void deleteTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        tasks.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            String message = String.valueOf(i) + ".";
            message += task;
            System.out.println(message);
        }
    }

    private void getLocalData() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" \\| ");
            switch(task[0]) {
                case "T": {
                    tasks.add(new Todo(task[2], task[1].equals("1")));
                    break;
                }
                case "E": {
                    tasks.add(new Event(task[2], task[3], task[1].equals("1")));
                    break;
                }
                case "D": {
                    tasks.add(new Deadline(task[2], task[3], task[1].equals("1")));
                    break;
                }
                default:
                    break;
            }
        }
        s.close();
    }

    void start() {
        greet();

        // Get local data and fill task list
        try {
            getLocalData();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            try {
                Command command = Command.getValue(sc.next());
                if(command == null) {
                    throw new InvalidCommandException("Unknown command.");
                }
                switch (command) {
                    case BYE: {
                        exit();
                        isRunning = false;
                        break;
                    }
                    case LIST:
                        list();
                        break;
                    case DONE: {
                        int taskNo = sc.nextInt();
                        completeTask(taskNo);
                        break;
                    }
                    case DELETE: {
                        int taskNo = sc.nextInt();
                        deleteTask(taskNo);
                        break;
                    }
                    case TODO: {
                        String description = sc.nextLine().trim();
                        if (description.length() == 0) {
                            throw new EmptyInputException("The description of a todo cannot be empty.");
                        }
                        addTask(new Todo(description));
                        break;
                    }
                    case DEADLINE: {
                        String input = sc.nextLine().trim();
                        String[] details = input.split(" /by ");
                        if (details[0].length() == 0) {
                            throw new EmptyInputException("The description of a deadline cannot be empty.");
                        }
                        if (details.length <= 1 || details[1].length() == 0) {
                            throw new EmptyInputException("The due date of a deadline cannot be empty.");
                        }
                        addTask(new Deadline(details[0], details[1]));
                        break;
                    }
                    case EVENT: {
                        String input = sc.nextLine().trim();
                        String[] details = input.split(" /at ");
                        if (details[0].length() == 0) {
                            throw new EmptyInputException("The description of an event cannot be empty.");
                        }
                        if (details.length <= 1 || details[1].length() == 0) {
                            throw new EmptyInputException("The date of an event cannot be empty.");
                        }
                        addTask(new Event(details[0], details[1]));
                        break;
                    }
                    default:
                        throw new InvalidCommandException("Unknown command.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
