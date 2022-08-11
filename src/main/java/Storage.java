package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public String fileDirectory;
    public String fileName;
    public File memoFile;
    public Parser parser;

    public Storage(String fileDirectory, String fileName) {
        this.fileDirectory = fileDirectory;
        this.fileName = fileName;
        this.parser = new Parser();
    }

    public void reachFile() {
        File data_folder = new File(fileDirectory);
        if (!data_folder.exists() && !data_folder.isDirectory()) {
            data_folder.mkdirs();
        }
        File task_list_file = new File(fileDirectory + fileName);
        if (!task_list_file.exists()) {
            try {
                task_list_file.createNewFile();
            } catch (Exception e) {
                HandleException.handleException(DukeException.ExceptionType.read_file);
            }
        }
        this.memoFile = task_list_file;
    }

    public List<Task> readMemoTasks() {
        reachFile();
        List<Task> task_collections = new ArrayList<>();
        Scanner sc;
        try {
            sc = new Scanner(memoFile);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, the memory cannot be read successfully.");
            return task_collections;
        }
        while (sc.hasNextLine()) {
            String curr_task = sc.nextLine();
            if (curr_task.equals("") || curr_task.isBlank()) {
                continue;
            }
            String[] task_info = this.parser.memoTaskParser(curr_task);
            switch (task_info[0]) {
                case "T":
                    task_collections.add(
                            new Todo(task_info[2], task_info[1].equals("0") ? false : true));
                    break;
                case "E":
                    task_collections.add(
                            new Event(task_info[2], task_info[3],
                                    task_info[1].equals("0") ? false : true));
                    break;
                case "D":
                    task_collections.add(
                            new Deadline(task_info[2], task_info[3],
                                    task_info[1].equals("0") ? false : true));
                    break;
            }
        }
        return task_collections;
    }

    public String taskToMemoStr(Task t) {
        String memoStr = "";
        String[] info = t.getInfo();
        memoStr += "\n" + info[0] + SpecialFormat.split_notn + info[1] + SpecialFormat.split_notn + info[2];
        if (t.getType().equals("D") || t.getType().equals("E")) {
            memoStr += SpecialFormat.split_notn + info[3];
        }
        return memoStr;
    }

    public void write_memory(List<Task> task_list) {
        try {
            FileWriter fw = new FileWriter(fileDirectory + fileName);
            String textToAppend = "";
            Iterator task_iter = task_list.iterator();
            while (task_iter.hasNext()) {
                Task t = (Task) task_iter.next();
                textToAppend += taskToMemoStr(t);
            }
            fw.write(textToAppend);
            fw.close();
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.read_file);
        }
    }

    public void appendToFile(String filePath, Task t) {
        try {
            FileWriter fw = new FileWriter(filePath, true); // appending instead of overwriting
            fw.write(taskToMemoStr(t));
            fw.close();
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.read_file);
        }
    }

    /*public void editMemoFile(String action, String[] input_split_arr) {
        boolean exception_absent = false;
        int action_number = -1;
        String success_result = "";
        try {
            action_number = Integer.parseInt(input_split_arr[1]);
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.empty_illegal);
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
                HandleException.handleException(DukeException.ExceptionType.empty_illegal);
            }
        }
        if (exception_absent) {
            if (action.equals("delete")) {
                System.out.println(SpecialFormat.indent + "Noted. I've removed this task:");
                System.out.println(SpecialFormat.indent + success_result);
                System.out.println(SpecialFormat.indent + "Now you have " + task_collections.size() + " tasks in the list.");
            } else {
                System.out.println(SpecialFormat.indent + "Nice! I've marked this task as done:");
                System.out.println(SpecialFormat.indent + "  [\u2713] " + task_collections.get(action_number - 1).toString().split("] ", 2)[1]);
            }
        }
    }

    public void addMemoFile(String type, String[] input_split_arr) {
        Task t = null;
        if (!type.equals("todo")) {
            try {
                input_split_arr = input_split_arr[1].split(
                        type.equals("event") ? " /at " : " /by ", 2);
            } catch (Exception ex) {
                Ui.handleException(type.equals("deadline")
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
                        time = input_split_arr[1];
                        time = this.parser.dateTimeParser(time);
                        t = type.equals("event")
                                ? new Event(input_split_arr[0], time)
                                : new Deadline(input_split_arr[0], time);
                    } catch (Exception ex) {
                        Ui.handleException(type.equals("event")
                                ? DukeException.ExceptionType.event_empty_incomplete
                                : DukeException.ExceptionType.deadline_empty_incomplete);
                    }
                }
            } catch (Exception ex) {
                Ui.handleException(type.equals("todo")
                        ? DukeException.ExceptionType.todo_empty
                        : type.equals("event")
                        ? DukeException.ExceptionType.event_empty_incomplete
                        : DukeException.ExceptionType.deadline_empty_incomplete);
            }
            if (exception_absent) {
                task_collections.add(t);
                appendToFile(this.fileDirectory + this.fileName, t);
                if (exception_absent) {
                    System.out.println(SpecialFormat.indent + "Got it. I've added ths task:");
                    System.out.println(SpecialFormat.indent + "  " + task_collections.get(task_collections.size() - 1));
                    System.out.println(SpecialFormat.indent + "Now you have " + task_collections.size() + " tasks in the list.");
                }
            }
        }
    }*/
}
