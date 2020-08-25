package main.java;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {
    public static String separation_line = "    ____________________________________________________________";
    public static String indent = "     ";
    public static String starting_line = separation_line + "\n" + indent;
    public static String ending_line = "\n" + separation_line + "\n";
    public static String split_notn = "@#%#@%";

    public static String memory_file_dir = "./data/";
    public static String memory_file_name = "task_list.txt";

    public static boolean exception_absent;
    public static List<Task> task_collections;

    public static String taskToMemoStr(Task t) {
        String memoStr = "";
        String[] info = t.getInfo();
        memoStr += "\n" + info[0] + split_notn + info[1] + split_notn + info[2];
        if (t.getType().equals("D") || t.getType().equals("E")) {
            memoStr += split_notn + info[3];
        }
        return memoStr;
    }

    public static void write_memory(List<Task> task_list) throws IOException {
        FileWriter fw = new FileWriter(memory_file_dir + memory_file_name);
        String textToAppend = "";
        Iterator task_iter = task_list.iterator();
        while (task_iter.hasNext()) {
            Task t = (Task) task_iter.next();
            textToAppend += taskToMemoStr(t);
        }
        fw.write(textToAppend);
        fw.close();
    }

    public static void appendToFile(String filePath, Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // appending instead of overwriting
        fw.write(taskToMemoStr(t));
        fw.close();
    }

    public static void handleException(DukeException.ExceptionType et) {
        exception_absent = false;
        DukeException de = new DukeException(et);
        System.out.println(de);
    }

    public static void readTasks() {
        task_collections = new ArrayList<>();
        File data_folder = new File(memory_file_dir);

        if (!data_folder.exists() && !data_folder.isDirectory()) {
            data_folder.mkdirs();
        }

        File task_list_file = new File(memory_file_dir + memory_file_name);
        if (!task_list_file.exists()) {
            try {
                task_list_file.createNewFile();
            } catch (Exception e) {
                handleException(DukeException.ExceptionType.read_file);
            }
        }

        Scanner sc = null;
        try {
            sc = new Scanner(task_list_file);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, the memory cannot be read successfully.");
        }

        while (sc.hasNextLine()) {
            String curr_task = sc.nextLine();
            String[] temp_type = curr_task.split(split_notn, 2);
            String[] temp_details;
            switch(temp_type[0]) {
                case "T":
                    temp_details = temp_type[1].split(split_notn, 2);
                    task_collections.add(
                            new Todo(temp_details[1], temp_details[0].equals("0") ? false : true));
                case "E":
                    temp_details = temp_type[1].split(split_notn, 3);
                    task_collections.add(
                            new Event(temp_details[1], temp_details[2],
                                    temp_details[0].equals("0") ? false : true));
                    break;
                case "D":
                    temp_details = temp_type[1].split(split_notn, 3);
                    task_collections.add(
                            new Deadline(temp_details[1], temp_details[2],
                                    temp_details[0].equals("0") ? false : true));
                    break;
                default:
                    System.out.println();
            }
        }
    }

    public static void editMemoFile(String action, String[] input_split_arr) {
        int action_number = -1;
        String success_result = "";
        try {
            action_number = Integer.parseInt(input_split_arr[1]);
        } catch (Exception ex) {
            handleException(DukeException.ExceptionType.empty_illegal);
        }
        if (exception_absent) {
            try {
                if (action.equals("delete")) {
                    success_result = task_collections.get(action_number - 1).toString();
                    task_collections.remove(action_number - 1);
                } else {
                    task_collections.get(action_number - 1).markAsDone();
                }
                write_memory(task_collections);
            } catch (Exception ex) {
                handleException(DukeException.ExceptionType.empty_illegal);
            }
        }
        if (exception_absent) {
            if (action.equals("delete")) {
                System.out.println(indent + "Noted. I've removed this task:");
                System.out.println(indent + success_result);
                System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
            } else {
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + "  [\u2713] " + task_collections.get(action_number - 1).toString().split("] ", 2)[1]);
            }
        }
    }

    public static void addMemoFile(String type, String[] input_split_arr) {
        Task t = null;
        if (!type.equals("todo")) {
            try {
                input_split_arr = input_split_arr[1].split(" /", 2);
            } catch (Exception ex) {
                handleException(type.equals("deadline")
                        ? DukeException.ExceptionType.deadline_empty_incomplete
                        : DukeException.ExceptionType.event_empty_incomplete);
            }
        }
        if (exception_absent) {
            try {
                if (type.equals("todo")) {
                    t = new Todo(input_split_arr[1]);
                } else {
                    String time;
                    try {
                        time = input_split_arr[1].split(" ", 2)[1];
                        t = type.equals("event")
                                ? new Event(input_split_arr[0], time)
                                : new Deadline(input_split_arr[0], time);
                    } catch (Exception ex) {
                        handleException(type.equals("event")
                                ? DukeException.ExceptionType.event_empty_incomplete
                                : DukeException.ExceptionType.deadline_empty_incomplete);
                    }
                }
            } catch (Exception ex) {
                handleException(type.equals("todo")
                    ? DukeException.ExceptionType.todo_empty
                    : type.equals("event")
                        ? DukeException.ExceptionType.event_empty_incomplete
                        : DukeException.ExceptionType.deadline_empty_incomplete);
            }
            if (exception_absent) {
                task_collections.add(t);
                try {
                    appendToFile(memory_file_dir + memory_file_name, t);
                } catch (IOException ioe) {
                    System.out.println();
                }
                if (exception_absent) {
                    System.out.println(indent + "Got it. I've added ths task:");
                    System.out.println(indent + "  " + task_collections.get(task_collections.size() - 1));
                    System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
                }
            }
        }
    }

    public static void main(String[] args){
        readTasks();

        String greeting = starting_line + "Hello! This is J.A.R.V.I.S.\n" + indent + "How may I help you?" + ending_line;
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        boolean exit_bye = false;
        while (!exit_bye) {
            String input = sc.nextLine();
            exception_absent = true;
            if (input.equals("bye")) {
                System.out.println(starting_line + "Bye. Hope to see you again soon!" +  ending_line);
                exit_bye = true;
            } else {
                System.out.println(separation_line);
                if (input.equals("list")) {
                    int temp = 1;
                    System.out.println(indent + "Here are the tasks in your list:");
                    Iterator task_iter = task_collections.iterator();
                    while (task_iter.hasNext()) {
                        System.out.println(indent + temp + "." + task_iter.next());
                        temp++;
                    }
                } else {
                    String[] input_split_arr = input.split(" ", 2);
                    String type = input_split_arr[0];
                    if (type.equals("done") || type.equals("delete")) {
                        editMemoFile(type, input_split_arr);
                    }
                    else if (type.equals("deadline") || type.equals("event") || type.equals("todo")){
                        addMemoFile(type, input_split_arr);
                    } else {
                        handleException(DukeException.ExceptionType.no_meaning);
                    }
                }
                System.out.println(separation_line + "\n");
            }
        }
    }
}

//compile when current directory is at IndividualProject/text-ui-test
//javac -cp ../src/ ../src/main/java/Task.java   etc. (Task, Deadline, Event, Todo, Duke)
//sh runtest.sh
