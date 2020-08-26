import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        load(tasks);

        System.out.println("Hello! I'm Duke\n" + "How can I help you today?\n");

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String command = sc.nextLine().strip();
                String[] commandArr = command.split(" ", 2);
                Task task;
                String[] strings;
                int index;
                if (commandArr[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                switch (commandArr[0]) {
                    case "todo":
                        if (commandArr.length == 1 || commandArr[1].strip().equals("")) {
                            throw new DukeException("\nUh-oh! The description of a todo cannot be empty.\n");
                        }
                        task = new ToDos(commandArr[1]);
                        tasks.add(task);
                        System.out.println("\nGot it. I've added this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
                        break;
                    case "deadline":
                        if (commandArr.length == 1 || commandArr[1].strip().equals("")) {
                            throw new DukeException("\nUh-oh! The description of a deadline cannot be empty.\n");
                        }
                        strings = commandArr[1].split("/by");
                        try {
                            task = new Deadlines(strings[0].strip(), LocalDate.parse(strings[1].strip()));
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Uh-oh! Please enter the correct date format.");
                        }
                        tasks.add(task);
                        System.out.println("\nGot it. I've added this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
                        break;
                    case "event":
                        if (commandArr.length == 1 || commandArr[1].strip().equals("")) {
                            throw new DukeException("\nUh-oh! The description of an event cannot be empty.\n");
                        }
                        strings = commandArr[1].split("/at");
                        try {
                            task = new Events(strings[0].strip(), LocalDate.parse(strings[1].strip()));
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Uh-oh! Please enter the correct date format.");
                        }
                        tasks.add(task);
                        System.out.println("\nGot it. I've added this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
                        break;
                    case "list":
                        System.out.println("\nHere are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        System.out.println();
                        break;
                    case "done":
                        index = Integer.parseInt(commandArr[1]) - 1;
                        tasks.get(index).markAsDone();
                        System.out.println("\nNice! I've marked this task as done:\n " + tasks.get(index) + "\n");
                        break;
                    case "delete":
                        index = Integer.parseInt(commandArr[1]) - 1;
                        Task removedTask = tasks.remove(index);
                        System.out.println("\nNoted. I've removed this task:\n " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.\n");
                        break;
                    default:
                        throw new DukeException("\nUh-oh! I'm sorry, but I don't know what that means.\n");
                }
                save(tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("./data/tasks.txt");
            for (Task t : tasks) {
                fw.write(t.saveAs() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Uh-oh! An error occured while saving.");
        }
    }

    public static void load(List<Task> tasks) {
        System.out.println("Loading previous task list....");
        try {
            File file = new File("./data/tasks.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String string = sc.nextLine();
                String[] strings;
                Task task;
                if (string.startsWith("T")) {
                    strings = string.split("\\|", 3);
                    task = new ToDos(strings[2].strip());
                    if (strings[1].strip().equals("true")) {
                        task.markAsDone();
                    }
                } else if (string.startsWith("D")) {
                    strings = string.split("\\|", 4);
                    task = new Deadlines(strings[2].strip(), LocalDate.parse(strings[3].strip()));
                    if (strings[1].strip().equals("true")) {
                        task.markAsDone();
                    }
                } else {
                    strings = string.split("\\| ", 4);
                    task = new Events(strings[2].strip(), LocalDate.parse(strings[3].strip()));
                    if (strings[1].strip().equals("true")) {
                        task.markAsDone();
                    }
                }
                tasks.add(task);
            }
            System.out.println("\nTasks loaded successfully!\n");
        } catch (FileNotFoundException f) {
            System.out.println("\nNo previous task list was found. Creating a new task list....");
            try {
                File directory = new File("./data");
                File file = new File("./data/tasks.txt");

                if (!(file.exists())) {
                    if (!(directory.exists())) {
                        directory.mkdir();
                    }
                    file.createNewFile();
                }
            } catch (IOException i) {
                System.out.println("Uh-oh! An error occured while creating the new task list.");
            }
            System.out.println("\nA new task list has been created!\n");
        }
    }
}