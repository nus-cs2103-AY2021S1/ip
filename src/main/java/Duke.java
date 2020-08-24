import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Duke {
    enum Command {
        BYE, LIST, DONE, TODO, DEADLINE, EVENT, DELETE
    }

    private static ArrayList<Task> loadFile(File file) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(file))) {
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
        }
        for (int i = 0; i < result.size(); i++) {
            String str = result.get(i);
            String[] arr = str.split(" >> ", -1);

            switch (arr[0]) {
            case "T":
                // It is a to-do task
                try {
                    if (Integer.valueOf(arr[1]).equals(0)) {
                        // Incomplete task
                        Task task = new Todo(arr[2]);
                        taskList.add(task);
                    } else if (Integer.valueOf(arr[1]).equals(1)) {
                        // Completed task
                        Task task = new Todo(arr[2]);
                        task.markAsDone();
                        taskList.add(task);
                    } else {
                        // Not recognised format
                        printFormatError(i);
                    }
                } catch (Exception ex) {
                    printFormatError(i);
                }

                break;
            case "D":
                // It is a deadline task
                try {
                    if (Integer.valueOf(arr[1]).equals(0)) {
                        // Incomplete task
                        Task task = new Deadline(arr[2], arr[3]);
                        taskList.add(task);
                    } else if (Integer.valueOf(arr[1]).equals(1)) {
                        // Completed task
                        Task task = new Deadline(arr[2], arr[3]);
                        task.markAsDone();
                        taskList.add(task);
                    } else {
                        // Not recognised format
                        printFormatError(i);
                    }
                } catch (Exception ex) {
                    printFormatError(i);
                }

                break;
            case "E":
                // It is an Event task
                try {
                    if (Integer.valueOf(arr[1]).equals(0)) {
                        // Incomplete task
                        Task task = new Event(arr[2], arr[3]);
                        taskList.add(task);
                    } else if (Integer.valueOf(arr[1]).equals(1)) {
                        // Completed task
                        Task task = new Event(arr[2], arr[3]);
                        task.markAsDone();
                        taskList.add(task);
                    } else {
                        // Not recognised format
                        printFormatError(i);
                    }
                } catch (Exception ex) {
                    printFormatError(i);
                }

                break;
            default:
                // No such type
                printFormatError(i);
                break;
            }
        }
        return taskList;
    }

    private static void printFormatError(int i) {
        System.out.println("Hello! Looks like there is a format error in your saved file!");
        if (i >= 0) {
            System.out.println("The line on " + (i + 1) + " will be ignored");
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Task> ls = new ArrayList<>();
        String horizontalDiv = "____________________________________________________________";

        String filePath = new File("").getAbsolutePath();
        filePath += "\\todolist.txt";
        File f = new File(filePath);
        boolean exists = f.exists();
        if (!exists) {
            f.createNewFile();
        }

        ls = loadFile(f);

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        System.out.println(horizontalDiv);
        System.out.println("Hello! I'm Dude\n" + "What can I do for you today?");
        System.out.println(horizontalDiv);

        WhileLoop:
        while (in.hasNextLine()) {
            String str = in.nextLine();
            String[] arr = str.split(" ", 2);

            String upperCaseCmd = arr[0].toUpperCase().trim();
            Command cmd = Command.valueOf(upperCaseCmd);

            switch (cmd) {
            case BYE:
                System.out.println(horizontalDiv);
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println(horizontalDiv);
                break WhileLoop;

            case LIST:
                System.out.println(horizontalDiv);
                if (ls.isEmpty()) {
                    System.out.println("Congratulations! You have currently no task.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < ls.size(); i++) {
                        Task task = ls.get(i);
                        int num = i + 1;
                        System.out.println(num + ". " + task.toString());
                    }
                }
                System.out.println(horizontalDiv);
                break;

            case DONE:
                try {
                    int numToBeMarkedAsDone = Integer.parseInt(str.substring(str.length() - 1)) - 1;
                    Task tsk = ls.get(numToBeMarkedAsDone);
                    tsk.markAsDone();
                    ls.set(numToBeMarkedAsDone, tsk);
                    System.out.println(horizontalDiv);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(tsk.toString());
                    System.out.println(horizontalDiv);
                } catch (Exception ex) {
                    System.out.println(horizontalDiv);
                    System.out.println("Sorry! The number does not exist in the list!");
                    System.out.println(horizontalDiv);
                }
                break;

            case TODO:
                try {
                    Task newTask = new Todo(arr[1]);
                    ls.add(newTask);
                    System.out.println(horizontalDiv);
                    System.out.println("Got it. I've added this task: \n" + newTask.toString());
                    if(ls.size() > 1) {
                        System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    } else {
                        System.out.println("Now you have " + ls.size() + " task in the list.");
                    }
                    System.out.println(horizontalDiv);
                } catch (Exception ex) {
                    System.out.println(horizontalDiv);
                    System.out.println("Sorry! The description of todo cannot be empty!!");
                    System.out.println(horizontalDiv);
                }
                break;

            case DEADLINE:
                try {
                    String secondStr = arr[1];
                    String[] arrOfStr = secondStr.split(" /by ", 2);
                    try {
                        Task newTask = new Deadline(arrOfStr[0], arrOfStr[1]);
                        ls.add(newTask);
                        System.out.println(horizontalDiv);
                        System.out.println("Got it. I've added this task: \n" + newTask.toString());
                        if(ls.size() > 1) {
                            System.out.println("Now you have " + ls.size() + " tasks in the list.");
                        } else {
                            System.out.println("Now you have " + ls.size() + " task in the list.");
                        }
                        System.out.println(horizontalDiv);
                    } catch (Exception ex) {
                        System.out.println(horizontalDiv);
                        System.out.println("Sorry! Please enter a date for the deadline using the command '/by'!");
                        System.out.println(horizontalDiv);
                    }
                } catch (Exception ex) {
                    System.out.println(horizontalDiv);
                    System.out.println("Sorry! The description of deadline cannot be empty!");
                    System.out.println(horizontalDiv);
                }
                break;

            case EVENT:
                try {
                    String secondStr = arr[1];
                    String[] arrOfStr = secondStr.split(" /at ", 2);
                    try {
                        Task newTask = new Event(arrOfStr[0], arrOfStr[1]);
                        ls.add(newTask);
                        System.out.println(horizontalDiv);
                        System.out.println("Got it. I've added this task: \n" + newTask.toString());
                        if (ls.size() > 1) {
                            System.out.println("Now you have " + ls.size() + " tasks in the list.");
                        } else {
                            System.out.println("Now you have " + ls.size() + " task in the list.");
                        }
                        System.out.println(horizontalDiv);
                    } catch (Exception ex) {
                        System.out.println(horizontalDiv);
                        System.out.println("Sorry! Please enter a duration for the event using the command '/at'!");
                        System.out.println(horizontalDiv);
                    }
                } catch (Exception ex) {
                    System.out.println(horizontalDiv);
                    System.out.println("Sorry! The description of event cannot be empty!");
                    System.out.println(horizontalDiv);
                }
                break;
            case DELETE:
                try {
                    int numToBeDeleted = Integer.parseInt(str.substring(str.length() - 1)) - 1;
                    Task tsk = ls.get(numToBeDeleted);
                    ls.remove(numToBeDeleted);
                    System.out.println(horizontalDiv);
                    System.out.println("Successfully deleted this task:");
                    System.out.println(tsk.toString());
                    if (ls.size() > 1) {
                        System.out.println("Now you have " + ls.size() + " tasks in the list.");
                    } else {
                        System.out.println("Now you have " + ls.size() + " task in the list.");
                    }
                    System.out.println(horizontalDiv);
                } catch (Exception ex) {
                    System.out.println(horizontalDiv);
                    System.out.println("Sorry! The number to be deleted does not exist in the list!");
                    System.out.println(horizontalDiv);
                }
                break;
            default:
                System.out.println(horizontalDiv);
                System.out.println("Sorry! But I don't know what that means!");
                System.out.println(horizontalDiv);
            }
        }
    }
}
