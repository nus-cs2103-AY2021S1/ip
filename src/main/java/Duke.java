import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Duke {
    public static List<Task> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String inputLine = sc.nextLine().trim();

        while (!inputLine.equals("bye")) {
            String[] arr = inputLine.split(" ");
            if (inputLine.equals("list")) {
                displayList();
            }
            else if (inputLine.equals("save")) {
                hrTag();
                saveData();
                hrTag();
            }
            else if (arr.length == 2 && (arr[0].equals("done") || arr[0].equals("delete")) && isInteger(arr[1])) {
                int num = Integer.parseInt(arr[1]);
                try {
                    if (num < 1 || num > toDoList.size()) {
                        throw new InvalidRangeError();
                    }
                    if (arr[0].equals("done")) {
                        doneTask(num);
                    }
                    else if (arr[0].equals("delete")) {
                        deleteTask(num);
                    }
                }
                catch (DukeError e) {
                    hrTag();
                    System.out.println(e.getMessage());
                    hrTag();
                }
            }
            else {
                try {
                    switch (arr[0]) {
                        case "todo":
                            String todo = inputLine.substring(4).trim();
                            if (todo.equals("")) {
                                throw new EmptyTaskError();
                            } else {
                                addTask(new ToDo(todo));
                            }
                            break;
                        case "deadline":
                            int by = inputLine.indexOf("/by");
                            String deadline, byDate;
                            if (by == -1) {
                                deadline = inputLine.substring(8).trim();
                                byDate = "";
                            } else {
                                deadline = inputLine.substring(8, by).trim();
                                byDate = inputLine.substring(by + 3).trim();
                            }
                            if (deadline.equals("")) {
                                throw new EmptyTaskError();
                            } else if (byDate.equals("")) {
                                throw new EmptyDateError();
                            } else {
                                addTask(new Deadline(deadline, byDate));
                            }
                            break;
                        case "event":
                            int at = inputLine.indexOf("/at");
                            String event, atDate;
                            if (at == -1) {
                                event = inputLine.substring(5).trim();
                                atDate = "";
                            } else {
                                event = inputLine.substring(5, at).trim();
                                atDate = inputLine.substring(at + 3).trim();
                            }
                            if (event.equals("")) {
                                throw new EmptyTaskError();
                            } else if (atDate.equals("")) {
                                throw new EmptyDateError();
                            } else {
                                addTask(new Event(event, atDate));
                            }
                            break;
                        default:
                            throw new UnknownCommandError();
                    }
                }
                catch (DukeError e) {
                    hrTag();
                    System.out.println(e.getMessage());
                    hrTag();
                }
            }
            inputLine = sc.nextLine();
        }
        goodbyeMessage();
        sc.close();
    }

    public static void hrTag() {
        System.out.println("____________________________________________________________");
    }

    private static void welcomeMessage() {
        hrTag();
        System.out.println("Hello! I am your mother!");
        readData();
        hrTag();
    }

    private static void readData() {
        Path path = FileSystems.getDefault().getPath("data");
        if (Files.exists(path)) {
            try {
                File data = new File("data/task.txt");
                Scanner scanner = new Scanner(data);
                System.out.println("Fetching old data...");
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    String[] str = s.split("\\|");
                    switch (str[0]) {
                        case "T":
                            ToDo toDo = new ToDo(str[2]);
                            if (str[1].equals("true")) {
                                toDo.markAsDone();
                            }
                            toDoList.add(toDo);
                            break;
                        case "D":
                            Deadline deadline = new Deadline(str[2], str[3]);
                            if (str[1].equals("true")) {
                                deadline.markAsDone();
                            }
                            toDoList.add(deadline);
                            break;
                        case "E":
                            Event event = new Event(str[2], str[3]);
                            if (str[1].equals("true")) {
                                event.markAsDone();
                            }
                            toDoList.add(event);
                            break;
                        default:
                            throw new IOException();
                    }
                }
                System.out.println("Data successfully read. ^^");
                scanner.close();
            } catch (IOException e) {
                System.out.println("Uh oh! An error has occurred. T_T");
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addTask(Task task) {
        hrTag();
        try {
            if (task.getDescription().contains("|") || task.getDate().contains("|")) {
                throw new IllegalCharacterError();
            }
            toDoList.add(task);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + task);
            if (toDoList.size() == 1) {
                System.out.printf(" Now you have %d task in the list.%n", toDoList.size());
            } else {
                System.out.printf(" Now you have %d tasks in the list.%n", toDoList.size());
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }
        hrTag();
    }

    private static void displayList() {
        hrTag();
        if (toDoList.isEmpty()) {
            System.out.println("You have no tasks left! Good job my child!");
        } else {
            if (toDoList.size() == 1) {
                System.out.println(" Here is the task in your list:");
            } else {
                System.out.println(" Here are the tasks in your list:");
            }
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println(" " + (i+1) + "." + toDoList.get(i));
            }
        }
        hrTag();
    }

    private static void goodbyeMessage() {
        hrTag();
        System.out.println("Goodbye my child!");
        saveData();
        hrTag();
    }

    private static void saveData() {
        try {
            // Find if /data folder exists
            Path path = FileSystems.getDefault().getPath("data");
            if (!Files.exists(path)) {
                File folder = new File("data");
                folder.mkdir();
            }
            File data = new File("data/task.txt");
            FileWriter fileWriter = new FileWriter(data);
            PrintWriter writer = new PrintWriter(fileWriter);

            System.out.println("Saving...");
            for (Task task : toDoList) {
                String s = String.format("%s|%b|%s", task.getTaskType(),
                        task.getIsDone(), task.getDescription());
                if (!task.getTaskType().equals("T")) {
                    s = s.concat(String.format("|%s", task.getDate()));
                }

                writer.println(s);
            }
            writer.close();
            System.out.println("Saved successfully.");
        } catch (IOException e) {
            System.out.println("Uh oh! An error has occurred. T_T");
            System.out.println(e.getMessage());
        }
    }

    private static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void doneTask(int num) {
        hrTag();
        if (toDoList.get(num - 1).getIsDone()) {
            System.out.printf("Sorry! You have already completed '%s'.%n",
                    toDoList.get(num - 1).getDescription());
        } else {
            System.out.printf("Great job son! I'll mark '%s' as done for you. ^^%n",
                    toDoList.get(num - 1).getDescription());
            toDoList.get(num - 1).markAsDone();
        }
        hrTag();
    }

    private static void deleteTask(int num) {
        hrTag();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("  " + toDoList.get(num - 1));
        toDoList.remove(num - 1);
        if (toDoList.size() == 0) {
            System.out.println(" Great job son! You're left with no more tasks!");
        }
        else if (toDoList.size() == 1) {
            System.out.println(" Now you have only 1 task in the list!");
        } else {
            System.out.printf(" Now you have %d tasks in the list.%n", toDoList.size());
        }
        hrTag();
    }
}
