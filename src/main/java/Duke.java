package main.java;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static String separation_line = "    ____________________________________________________________";
    public static String indent = "     ";
    public static String starting_line = separation_line + "\n" + indent;
    public static String ending_line = "\n" + separation_line + "\n";

    public static boolean exception_absent;
    public static List<Task> task_collections = new ArrayList<>();
    public static DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(
            FormatStyle.MEDIUM, FormatStyle.SHORT);

    public static String processTime(String dateTime) {
        String date;
        String time;

        try {
            if (dateTime.length() > 11) {
                String[] parts = dateTime.split(" ", 2);
                time = parts[0].length() == 4 ? parts[0] : parts[1];
                date = parts[0].length() == 4 ? parts[1] : parts[0];
            } else {
                date = dateTime;
                time = "2359";
            }

            LocalTime lt = LocalTime.of(
                    Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2)));

            date = date.replaceAll("\\D", "-");
            String[] date_seg = date.split("-", 3);

            if (date_seg[0].length() != 4) {
                String temp = date_seg[0];
                date_seg[0] = date_seg[2];
                date_seg[2] = temp;
            }
            LocalDate ld = LocalDate.parse(date_seg[0] + "-" + date_seg[1] + "-" + date_seg[2]);

            LocalDateTime ldt = LocalDateTime.of(ld, lt);
            return ldt.format(dtf);
        } catch (Exception ex) {
            handleException(DukeException.ExceptionType.improper_dateTime);
        }
        return "Unknown Date/Time";
    }

    public static void handleException(DukeException.ExceptionType et) {
        exception_absent = false;
        DukeException de = new DukeException(et);
        System.out.println(de);
    }

    public static void editTask(String action, String[] input_split_arr) {
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

    public static void addTask(String type, String[] input_split_arr) {
        Task t = null;
        if (!type.equals("todo")) {
            try {
                input_split_arr = input_split_arr[1].split(
                        type.equals("event") ? " /at " : " /by ", 2);
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
                    //System.out.println(input_split_arr[1]);
                } else {
                    String time;
                    try {
                        time = input_split_arr[1];
                        //time = input_split_arr[1].split(" ", 2)[1];
                        time = processTime(time);
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
                if (exception_absent) {
                    System.out.println(indent + "Got it. I've added ths task:");
                    System.out.println(indent + "  " + task_collections.get(task_collections.size() - 1));
                    System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
                }
            }
        }
    }

    public static void main(String[] args){
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
                        editTask(type, input_split_arr);
                    }
                    else if (type.equals("deadline") || type.equals("event") || type.equals("todo")){
                        addTask(type, input_split_arr);
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