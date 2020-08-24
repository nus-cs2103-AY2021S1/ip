import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static String INDENT = "    ";
    static String divider = INDENT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";
    static Path path = Paths.get("./data/duke.txt");
    static File memoryFile;

    static List<Task> parseData(File f) throws FileNotFoundException {
        List<Task> tasklist = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
           String str = s.nextLine();
           String[] args = str.split(" \\| ");
           boolean done = args[1].equals("1");
           String description = args[2];
           Task task;
           switch (args[0]) {
               case "T":
                   task = new Todo(description);
                   if (done) {
                       task.markAsDone();
                   }
                   tasklist.add(task);
                   break;
               case "D":
                   task = new Deadline(description, LocalDateTime.parse(args[3]));
                   if (done) {
                       task.markAsDone();
                   }
                   tasklist.add(task);
                   break;
               case "E":
                   task = new Event(description, LocalDateTime.parse(args[3]));
                   if (done) {
                       task.markAsDone();
                   }
                   tasklist.add(task);
                   break;
               default:
                   System.out.println(INDENT + "Corrupted Data Entry found : " + str);
           }
        }
        return tasklist;
    }

    static void writeData(File f, Task task) {
        try {
            FileWriter fw = new FileWriter(path.toString(), true);
            fw.write(task.convertToData() + "\n");
            fw.close();
        } catch (IOException e) {
            print("Error Writing Message");
        }
    }

    static void rewriteData(File f, List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(path.toString());
            for(Task t : tasks) {
                fw.write(t.convertToData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            print("Error Writing Message");
        }
    }

    static void initialiseTasks(List<Task> tasklist){
        System.out.print(divider);
        memoryFile = new File(path.toString());
        if (Files.exists(path)) {
            System.out.println(INDENT + "Loading Tasks from Memory...");
            try {
                List<Task> memoryList = parseData(memoryFile);
                tasklist.addAll(memoryList);
            } catch (FileNotFoundException e) {
                System.out.println(INDENT + "Error loading data.");
            }
            System.out.println(INDENT + "Load Successful!");
        } else {
            System.out.println(INDENT + "No Memory Found.");
            try {
                FileWriter fw = new FileWriter(path.toString());
                System.out.println(INDENT + "Creating new memory file..");
                fw.write("");
                fw.close();
                System.out.println(INDENT + "Done.");
            } catch (IOException e) {
                System.out.print(INDENT + e.getMessage());
            }
        }
        System.out.print(divider);
    }
    static void start() {
        String logo =
                                " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                                        "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                                        "| |  ________    | || |     ____     | || |     ____     | || |  ___  ____   | |\n" +
                                        "| | |_   ___ `.  | || |   .'    `.   | || |   .'    `.   | || | |_  ||_  _|  | |\n" +
                                        "| |   | |   `. \\ | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |_/ /    | |\n" +
                                        "| |   | |    | | | || |  | |    | |  | || |  | |    | |  | || |   |  __'.    | |\n" +
                                        "| |  _| |___.' / | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  \\ \\_  | |\n" +
                                        "| | |________.'  | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                                        "| |              | || |              | || |              | || |              | |\n" +
                                        "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                                        " '----------------'  '----------------'  '----------------'  '----------------'\n";
        String intro =
                "\n" +
                "\n" +
                logo +
                "\n" +
                divider +
                INDENT +
                "Hola! I am dook\n" +
                INDENT +
                "how i can help u?\n" + divider;
        System.out.println(intro);
    }
    static void print(String str){
        String[] arr = str.split("\n");
        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(INDENT).append(s).append("\n");
        }
        String intro = divider +  res.toString() + divider;
        System.out.println(intro);
    }
    static void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        writeData(memoryFile, task);
        print("Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
    static void doneTask(List<Task> tasks, int index) {
        tasks.get(index).markAsDone();
        rewriteData(memoryFile, tasks);
    }
    static void displayList(List<Task> list) {
        if(list.size() == 0) {
            print("No task added yet!");
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
            for(int i = 0; i < list.size(); i++) {
                result.append(i + 1).append(". ").append(list.get(i).toString()).append(i + 1 == list.size() ? "" : "\n");
            }
            print(result.toString());
        }
    }
    static LocalDateTime parseDateTime(String s) {
        // s will be in the format : yyyy-mm-dd HHmm
        // return format : yyyy-mm-ddTHH:mm
        String[] dateTimeSplit = s.split(" ");
        String date = dateTimeSplit[0];
        String hour = dateTimeSplit[1].substring(0, 2);
        String min = dateTimeSplit[1].substring(2);
        return LocalDateTime.parse(date + "T" + hour + ":" + min);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialise
        start();
        List<Task> tasks = new ArrayList<>();
        initialiseTasks(tasks);

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    displayList(tasks);
                } else if (input.matches("done.*")) {
                    if(input.matches("done\\s*")) {
                        throw new EmptyArgumentException("Task Index");
                    }
                    if(!input.matches("done \\d+")) {
                        throw new InvalidArgumentException("Task Index");
                    }
                    String[] arr = input.split(" ");
                    int index = Integer.parseInt(arr[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        throw new InvalidArgumentException("index");
                    }
                    doneTask(tasks, index);
                    print("Nice! I've marked this task as done:\n" + tasks.get(index));
                } else if (input.matches("todo.*")) {
                    if(input.matches("todo\\s*")) {
                        throw new EmptyArgumentException("todo's description");
                    }
                    addTask(new Todo(input.substring(5)), tasks);
                } else if (input.matches("deadline.*")) {
                    if(input.matches("deadline\\s*")) {
                        throw new EmptyArgumentException("deadline's description");
                    }
                    if(!input.matches("deadline .+/by \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
                        throw new InvalidArgumentException("deadline's description (proper date format: yyyy-mm-dd HHmm)");
                    }
                    String[] split = input.substring(9).split("/by");
                    String dateTimeString = split[1].stripLeading();
                    LocalDateTime dateTime = parseDateTime(dateTimeString);
                    addTask(new Deadline(split[0].stripTrailing(), dateTime), tasks);
                } else if (input.matches("event.*")) {
                    if(input.matches("event\\s*")) {
                        throw new EmptyArgumentException("event's description");
                    }
                    if(!input.matches("event .+/at \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
                        throw new InvalidArgumentException("event description (proper date format: yyyy-mm-dd HHmm)");
                    }
                    String[] split = input.substring(6).split("/at");
                    String dateTimeString = split[1].stripLeading();
                    addTask(new Event(split[0].stripTrailing(), parseDateTime(dateTimeString)), tasks);
                } else if (input.matches("delete.*")) {
                    if(input.matches("delete\\s*")) {
                        throw new EmptyArgumentException("Task Index");
                    }
                    if(!input.matches("delete \\d+")) {
                        throw new InvalidArgumentException("Task Index");
                    }
                    String[] arr = input.split(" ");
                    int index = Integer.parseInt(arr[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        throw new InvalidArgumentException("index");
                    }
                    Task deleted = tasks.remove(index);
                    print("Noted. I've removed this task:\n  " + deleted.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new InvalidCommandException();
                }
            } catch (Exception e) {
                print(e.toString());
            }

            input = sc.nextLine();
        }
        print("see u later alligator");
    }
}
