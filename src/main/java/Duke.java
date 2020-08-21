import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks;
//    public static void markTaskDone(ArrayList<Task> tasks) {

    public static void displayTasks(ArrayList<Task> tasks) {
        System.out.println("________________________________________");
        System.out.println("Here is your current list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("________________________________________");
    }

    public static void exitProgram(ArrayList<Task> tasks) throws IOException {
        Storage.writeToFile(tasks);
        display("Bye! Hope to see you again! :D");
        System.exit(0);
    }

    public static void display(String s) {
        System.out.println("________________________________________");
        System.out.println(s);
        System.out.println("________________________________________");
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");

        tasks = Storage.readFile();
        if (!tasks.isEmpty()) {
            displayTasks(tasks);
//            System.out.println(tasks.toString());
        } else {
            System.out.println("________________________________________");
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            String s = sc.next();
            if (s.equals("bye")) {
                exitProgram(tasks);
            } else if (s.equals("list")) {
                displayTasks(tasks);
            } else if (s.equals("done")) {
                try {
                    int index = Integer.parseInt(sc.next()) - 1;
                    tasks.get(index).markAsDone();
                    display("Nice! I've marked this task as done:\n   " + tasks.get(index));
                    Storage.writeToFile(tasks);
                } catch (Exception e) {
                    display("Task does not exist!");
                }
            } else if (s.equals("delete")) {
                try {
                    int index = Integer.parseInt(sc.next()) - 1;
                    tasks.remove(index);
                    display("Noted. I've removed this task:\n   " + tasks.get(index) +
                            "\nNow you have " + tasks.size() + " tasks in your list.");
                    Storage.writeToFile(tasks);
                } catch (Exception e) {
                    display("Task does not exist!");
                }
            } else {
                Task temp;
                try {
                    if (s.startsWith("todo")) {
                        s = sc.nextLine();
                        if (s.isEmpty()) {
                            throw new EmptyDescriptionException("Description cannot be empty");
                        } else {
                            temp = new ToDo(s.trim());
                            tasks.add(temp);
                            Storage.appendToFile(temp);
                        }
                    } else if (s.startsWith("deadline")) {
                        s = sc.nextLine();
                        if (s.isEmpty()) {
                            throw new EmptyDescriptionException("Description cannot be empty");
                        } else {
                            String[] parts = s.split("\\s*/by\\s*");
                            temp = new Deadline(parts[0].trim(), parts[1].trim());
                            tasks.add(temp);
                            Storage.appendToFile(temp);
                        }
                    } else if (s.startsWith("event")) {
                        s = sc.nextLine();
                        if (s.isEmpty()) {
                            throw new EmptyDescriptionException("Description cannot be empty");
                        } else {
                            String[] parts = s.split("\\s*/at\\s*");
                            temp = new Event(parts[0].trim(), parts[1].trim());
                            tasks.add(temp);
                            Storage.appendToFile(temp);
                        }
                    } else {
                        sc.nextLine();
                        throw new Exception("Wrong command.");
                    }
                    display("Yay! New task added:\n   " + temp +
                            "\nNow you have " + tasks.size() + " tasks in your list.");

                } catch (EmptyDescriptionException e) {
                    display("Description cannot be empty!");
                } catch (Exception e) {
                    display("Format is wrong!");
                }
            }
        }
    }
}
