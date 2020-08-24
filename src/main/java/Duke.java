import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    // converts text in file to task
    public static Task textToTask(String input) {
        String des = "";
        Character first = input.charAt(0);
        Character num = input.charAt(4);
        if (first == 'T') {
            des = "todo ";
            des += input.substring(8);

        } else {
            String temp = input.substring(8);
            String taskDescription = temp.substring(0, temp.indexOf(" |"));
            String date = temp.substring(temp.indexOf("|") + 2);
            if (first == 'D') {
                des = "deadline " + taskDescription + " /by " + date;
            } else {
                des = "event " + taskDescription + " /by " + date;
            }
        }

        Task t = new Task(des);
        if (num == '0') {
            return t;
        } else {
            t.markAsDone();
            return t;
        }
    }

//    public static void saveTaskList(String filePath) throws FileNotFoundException {
//        File f = new File(filePath);
//        Scanner fileScanner = new Scanner(f);
//        while (fileScanner.hasNext()) {
//            System.out.println(fileScanner.nextLine());
//        }
//    }

    // Adds text in file to tasks in an ArrayList
    public static void addFileContentsToArrayList(String filePath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            Task t = textToTask(fileScanner.nextLine());
            tasks.add(t);
        }
    }


    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

//    private static void appendToFile(String filePath, String textToAppend) throws IOException {
//        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
//        fw.write(textToAppend);
//        fw.close();
//    }




    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        String str = ""; // used to store user input

        // print welcome message
        System.out.println("    _________________________________\n"
                            + "    Hello! I'm Duke\n"
                            + "    What can I do for you?\n"
                            + "    _________________________________");

        ArrayList<Task> tasks = new ArrayList<>(); // to store tasks from txt file
        String filePath = "/Users/tengjianling/ip/data/duke.txt";
        String fileString = ""; // used to overwrite .txt file after while loop

        // add tasks from file to ArrayList
        try {
            addFileContentsToArrayList(filePath, tasks);
            System.out.println("You have " + tasks.size() + " tasks at the moment.");

        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        // loop through user input bye he inputs "bye"
        while (sc.hasNext()) {
            str = sc.next();
            Task t = new Task(str);

            if (str.isBlank()) {
                // no user input, does not register as an entry
            } else {
                System.out.println("    _________________________________");
                if (t.getDescription().equals("list")) {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task curr = tasks.get(i);
                        String toPrint = "";
                        if (curr.isTodo()) {
                            ToDo todo = new ToDo(curr.getDescription());
                            if (curr.isDone) {
                                todo.markAsDone();
                            }
                            toPrint = (i + 1) + ". " + todo;
                        } else if (curr.isDeadline()) {
                            Deadline deadline = new Deadline(curr.getDescription(), curr.getDate());
                            if (curr.isDone) {
                                deadline.markAsDone();
                            }
                            toPrint = (i + 1) + ". " + deadline;
                        } else {
                            Event event = new Event(curr.getDescription(), curr.getDate());
                            if (curr.isDone) {
                                event.markAsDone();
                            }
                            toPrint = (i + 1) + ". " + event;
                        }

                        System.out.println("    " + toPrint);
                    }

                } else if (t.isTodo()) {
                    try {
                        t.validate(); // checks if exception is thrown
                        ToDo todo = new ToDo(t.getDescription());
                        tasks.add(todo);
                        System.out.println("    Got it. I've added this task:\n"
                                            + "        " + todo + '\n'
                                            + "    Now you have " + tasks.size() + " tasks in the list.");


                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (t.isDeadline()) {
                    try {
                        t.validate();
                        String date = t.getDate();
                        Deadline deadline = new Deadline(t.getDescription(), date);
                        tasks.add(deadline);
                        System.out.println("    Got it. I've added this task:\n"
                                            + "        " + deadline + '\n'
                                            + "    Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (t.isEvent()) {
                    try {
                        t.validate();
                        String date = t.getDate();
                        Event event = new Event(t.getDescription(), date);
                        tasks.add(event);
                        System.out.println("    Got it. I've added this task:\n"
                                            + "        " + event + '\n'
                                            + "    Now you have " + tasks.size() + " tasks in the list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (t.getFirstWord().equals("done")) {
                    int taskNumber = t.getNumber();
                    tasks.get(taskNumber - 1).markAsDone();
                    System.out.println("    Nice! I've marked this task as done:\n"
                                        + "        " + tasks.get(taskNumber - 1));

                } else if (str.equals("bye")) {

                    System.out.println("    Bye. Hope to see you again soon!\n"
                                        + "    _________________________________");
                    break;
                } else if (t.getFirstWord().equals("delete")) {
                    int taskNumber = t.getNumber();
                    System.out.println("    Noted. I've removed this task:\n"
                            + "        " + tasks.get(taskNumber - 1));
                    tasks.remove(taskNumber - 1);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
                } else if (t.getFirstWord().equals("get")) {

                    System.out.println("Here are your tasks on this date:");

                    String date = str.substring(4);
                    String tasksOnDate = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        Task curr = tasks.get(i);
                        if (curr.isTodo()) {
                            ToDo todo = new ToDo(curr.getDescription());
                            if (curr.isDone) {
                                todo.markAsDone();
                            }

                            if (curr.getDate().equals(date)) {
                                tasksOnDate += todo + "\n";
                            }
                        } else if (curr.isDeadline()) {
                            Deadline deadline = new Deadline(curr.getDescription(), curr.getDate());
                            if (curr.isDone) {
                                deadline.markAsDone();
                            }
                            if (curr.getDate().equals(date)) {
                                tasksOnDate += deadline + "\n";
                            }
                        } else {
                            Event event = new Event(curr.getDescription(), curr.getDate());
                            if (curr.isDone) {
                                event.markAsDone();
                            }
                            if (curr.getDate().equals(date)) {
                                tasksOnDate += event + "\n";
                            }
                        }
                    }

                    if (!tasksOnDate.isBlank()) {
                        System.out.println(tasksOnDate);
                    } else {
                        System.out.println("No tasks on this date.");
                    }

                } else {
                    try {
                        t.validate();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("    _________________________________");
            }
        }

        // add tasks in ArrayList to fileString
        for (int i = 0; i < tasks.size(); i++) {
            fileString += tasks.get(i).taskToText() + "\n";
        }

        // writes fileString to .txt file
        try {
            writeToFile("/Users/tengjianling/ip/data/duke.txt", fileString);
        } catch (IOException e) {
            System.out.println("Oops, something went wrong: " + e.getMessage());
        }

    }
}
