import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> list = new ArrayList<>();
    public static final String FILENAME = "./data/duke.txt";
    public static final String DIRECTORY_NAME = "./data/";

    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listOut();
            } else if (input.contains("done")) {
                try {
                    done(input);
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            } else if (input.contains("delete")) {
                try {
                    delete(input);
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            } else {

                try {
                    if (input.contains("todo")) {
                        handleToDo(input);
                    } else if (input.contains("deadline")) {
                        handleDeadline(input);
                    } else if (input.contains("event")) {
                        handleEvent(input);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println();
                } catch (DukeException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                    System.out.println();
                }
            }
        }
        sc.close();
    }

    public static void handleEvent(String input) throws DukeException {
        // since input is confirmed to have "event", just need to ensure that stripped input > 5 letters
        // input needs to be stripped to prevent trailing whitespaces eg. "deadline    "
        if (input.strip().length() <= 5) {
            throw new DukeException(" ☹ OOPS!!! The description of a Event cannot be empty.");
        } else if (!input.contains("/")) {
            throw new DukeException(" ☹ OOPS!!! Event requires a date.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            String[] arr = taskName.split("/");
            Event task = new Event(arr[0], arr[1].substring(arr[1].indexOf(" ") + 1));
            add(task);
        }
    }

    public static void handleDeadline(String input) throws DukeException {
        // since input is confirmed to have "deadline", just need to ensure that stripped input > 8 letters
        // input needs to be stripped to prevent trailing whitespaces eg. "deadline    "
        if (input.strip().length() <= 8) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!input.contains("/")) {
            throw new DukeException(" ☹ OOPS!!! Deadline requires a date.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            String[] arr = taskName.split("/");
            Deadline task = new Deadline(arr[0], arr[1].substring(arr[1].indexOf(" ") + 1));
            add(task);
        }
    }

    public static void handleToDo(String input) throws DukeException {
        // since input is confirmed to have "to do", just need to ensure that stripped input > 4 letters
        // input needs to be stripped to prevent trailing whitespaces eg. "deadline    "
        if (input.strip().length() <= 4) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            ToDo task = new ToDo(taskName);
            add(task);
        }
    }

    public static void add(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        list.add(task);
        System.out.printf("     Now you have %d tasks in the list.\n", list.size());
        System.out.println("    ____________________________________________________________");
        save();
    }

//    // appends task info to existing duke.txt file
//    // only can be used by add method
//    public static void save(Task task) {
//        try {
//            FileWriter fw = new FileWriter(FILENAME, true); // true to mark fw to append line to existing file
//            fw.write(task.getInfo());
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("File is missing");
//        }
//    }

    // rewrites duke.txt file by iterating though task list
    // can be used by add, delete and done method
    public static void save() {
        try {
            FileWriter fw = new FileWriter(FILENAME);
            if (list.size() > 0) {
                fw.write(list.get(0).getInfo());

                for (int i = 1; i < list.size(); i++) {
                    fw.write(list.get(i).getInfo());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File is missing");
        }
    }

    public static void listOut() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("     %d. %s \n", i + 1, list.get(i));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public static void delete(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException(" ☹ OOPS!!! Please enter delete with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            if (index > list.size()) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            Task task = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task: ");

            System.out.println("       " + task);
            System.out.printf("     Now you have %d tasks in the list.\n", list.size());
            System.out.println("    ____________________________________________________________");
            System.out.println();
            save();
        }
    }

    public static void done(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException(" ☹ OOPS!!! Please enter done with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                // if string after done cannot be parsed to integer
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            if (index > list.size()) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            Task task = list.get(index - 1);
            task.completed();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");

            System.out.println("       " + task);
            System.out.println("    ____________________________________________________________");
            System.out.println();
            save();
        }
    }

    public static void init() {
        File directory = new File(DIRECTORY_NAME);

        // checks if directory exists, else directory will be created
        if (!directory.exists()){
            directory.mkdir();
        }

        File f = new File(FILENAME);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String line = sc.nextLine();
                    String[] split = line.split("\\|");
                    if (split.length < 3) {
                        // if after split, arr contains insufficient info, skip;
                        continue;
                    }
                    String taskType = split[0];
                    String status = split[1].strip();
                    String details = split[2].stripLeading();

                    if (taskType.contains("T")) {
                        ToDo task = new ToDo(details);
                        if (status.equals("1")) {
                            task.completed();
                        }
                        list.add(task);
                    } else if (taskType.contains("D")) {
                        String date = split[3].stripLeading();
                        Deadline task = new Deadline(details, date);
                        if (status.equals("1")) {
                            task.completed();
                        }
                        list.add(task);
                    } else if (taskType.contains("E")) {
                        String date = split[3].stripLeading();
                        Event task = new Event(details, date);
                        if (status.equals("1")) {
                            task.completed();
                        }
                        list.add(task);
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else {
            System.out.println("File not found, will be created");
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Missing input");
            }
        }
    }

    public static void main(String[] args) {

        greet();

        init();

        echo();

        exit();
    }
}
