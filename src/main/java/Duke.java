import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private static Task genTaskFromString(String string) {
        boolean status = (string.charAt(4) == '\u2713');
        if (string.charAt(1) == 'T') {
            return new ToDo(string.substring(7), status);
        } else if (string.charAt(1) == 'D') {
            int endIndex = string.indexOf("(by:") - 1;
            return new Deadline(string.substring(7, endIndex), string.substring(endIndex + 6, string.length() - 1), status);
        } else {
            int endIndex = string.indexOf("(at:") - 1;
            return new Event(string.substring(7, endIndex), string.substring(endIndex + 6, string.length() - 1), status);
        }
    }

    private static void printAndLoadFileContents(String filePath, ArrayList<Task> task) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentTask = s.nextLine();
            System.out.println(currentTask);
            task.add(genTaskFromString(currentTask));
        }
    }

    private static void clearFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        String divider = "_____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ArrayList<Task> task = new ArrayList<>();
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println("Beep Boop! Hello there!\n" + "What can I do for you?");
        System.out.println(divider);

        try {
            System.out.println("Welcome back! Here are the tasks in your list:");
            printAndLoadFileContents("C:\\Users\\Cedric\\Desktop\\Repo\\iP\\src\\main\\java\\data\\duke.txt", task);
            System.out.println(divider);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        /* Takes in user inputs. Program terminates when the String "bye" is entered.
        Program stores user inputs as Tasks and returns the list when the String "list" is entered.
        Tasks are categorised into "todo", "deadline" (to specify "by") and "event"  (to specify "at").
        When "done xx" is entered, Task xx in the list is marked as done.
        When "delete xx" is entered, Task xx in the list is removed from the list.
         */
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(divider);

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < task.size() + 1; i++) {
                    System.out.println(i + ". " + task.get(i - 1));
                }
            } else if (input.startsWith("done")) {
                if (input.length() == 4) {
                    System.out.println(new DukeException("Hold up! Please specify which task is done.").getMessage());
                } else {
                    int taskToMark = Integer.parseInt(input.substring(5)) - 1;
                    task.get(taskToMark).markAsDone();
                    System.out.println("Task Accomplished! I've marked this task as done:");
                    System.out.println(task.get(taskToMark));
                }
            } else if (input.startsWith("delete")) {
                if (input.length() == 6) {
                    System.out.println(new DukeException("Hold up! Please specify which task to delete.").getMessage());
                } else {
                    int taskToDelete = Integer.parseInt(input.substring(7)) - 1;
                    System.out.println("Alright! I've removed this task:");
                    System.out.println(task.remove(taskToDelete));
                    System.out.println("Now you have " + task.size() + " tasks in your list.");
                }
            } else {
                if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
                    System.out.println(new DukeException("Sorry, I'm not sure what you mean by that :(").getMessage());
                } else {

                    if (input.startsWith("todo")) {
                        if (input.length() == 4) {
                            System.out.println(new DukeException("Hold up! The description of todo cannot be empty...").getMessage());
                        } else {
                            System.out.println("Got it! I've added this task:");
                            task.add(new ToDo(input.substring(5)));
                            System.out.println(task.get(task.size() - 1));
                            System.out.println("Now you have " + task.size() + " tasks in your list.");
                        }

                    } else if (input.startsWith("deadline")) {
                        if (input.length() == 8) {
                            System.out.println(new DukeException("Hold up! The description of deadline cannot be empty...").getMessage());
                        } else if (!input.contains("/by")) {
                            System.out.println(new DukeException("Please specify deadline using: /by (deadline)").getMessage());
                        } else {
                            System.out.println("Got it! I've added this task:");
                            int endIndex = input.indexOf("/by") - 1;
                            task.add(new Deadline(input.substring(9, endIndex), input.substring(endIndex + 5)));
                            System.out.println(task.get(task.size() - 1));
                            System.out.println("Now you have " + task.size() + " tasks in your list.");
                        }

                    } else {
                        if (input.length() == 5) {
                            System.out.println(new DukeException("Hold up! The description of event cannot be empty...").getMessage());
                        } else if (!input.contains("/at")) {
                            System.out.println(new DukeException("Please specify timing using: /at (timing)").getMessage());
                        } else {
                            System.out.println("Got it! I've added this task:");
                            int endIndex = input.indexOf("/at") - 1;
                            task.add(new Event(input.substring(6, endIndex), input.substring(endIndex + 5)));
                            System.out.println(task.get(task.size() - 1));
                            System.out.println("Now you have " + task.size() + " tasks in your list.");
                        }
                    }
                }
            }

            System.out.println(divider);
            input = sc.nextLine();
        }

        System.out.println(divider);
        System.out.println("Goodbye, have a nice day :D");
        System.out.println(divider);

        clearFile("C:\\Users\\Cedric\\Desktop\\Repo\\iP\\src\\main\\java\\data\\duke.txt");
        for (Task t : task) {
            appendToFile("C:\\Users\\Cedric\\Desktop\\Repo\\iP\\src\\main\\java\\data\\duke.txt", t.toString());
        }
    }
}
