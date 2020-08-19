package main.java;

import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String separation_line = "    ____________________________________________________________";
        String indent = "     ";
        String starting_line = separation_line + "\n" + indent;
        String ending_line = "\n" + separation_line + "\n";

        Task[] task_arr = new Task[100];
        int count = 0;

        String greeting = starting_line + "Hello! This is J.A.R.V.I.S.\n" + indent + "How may I help you?" + ending_line;
        System.out.println(greeting);

        boolean exit_bye = false;
        while (!exit_bye) {
            String input = sc.nextLine();
            boolean exception_absent = true;
            if (input.equals("bye")) {
                System.out.println(starting_line + "Bye. Hope to see you again soon!" +  ending_line);
                exit_bye = true;
            } else {
                System.out.println(separation_line);
                if (input.equals("list")) {
                    int temp = 1;
                    System.out.println(indent + "Here are the tasks in your list:");
                    while (task_arr[temp - 1] != null) {
                        System.out.println(indent + temp + "." + task_arr[temp - 1]);
                        temp++;
                    }
                } else {
                    String[] input_split_arr = input.split(" ", 2);
                    String type = input_split_arr[0];
                    if (type.equals("done")) {
                        int done_number = -1;
                        try {
                            done_number = Integer.parseInt(input_split_arr[1]);
                        } catch (Exception ex) {
                            exception_absent = false;
                            DukeException de = new DukeException("done", null, "empty");
                            System.out.println(de);
                        }
                        if (exception_absent) {
                            try {
                                task_arr[done_number - 1].markAsDone();
                            } catch (Exception ex) {
                                exception_absent = false;
                                DukeException de = new DukeException("done", null, "illegal");
                                System.out.println(de);
                            }
                        }
                        if (exception_absent) {
                            System.out.println(indent + "Nice! I've marked this task as done:");
                            System.out.println(indent + "  [\u2713] " + task_arr[done_number - 1].toString().split("] ", 2)[1]);
                        }
                    } else if (type.equals("deadline") || type.equals("event") || type.equals("todo")){
                        if (type.equals("todo")) {
                            try {
                                task_arr[count] = new Todo(input_split_arr[1]);
                            } catch (Exception ex) {
                                exception_absent = false;
                                DukeException de = new DukeException("todo", null, "empty");
                                System.out.println(de);
                            }
                        } else if (type.equals("deadline")) {
                            try {
                                input_split_arr = input_split_arr[1].split(" /", 2);
                            } catch (Exception ex) {
                                exception_absent = false;
                                DukeException de = new DukeException("deadline", "content", "empty");
                                System.out.println(de);
                            } if (exception_absent) {
                                try {
                                    task_arr[count] = new Deadline(input_split_arr[0], input_split_arr[1].split(" ", 2)[1]);
                                } catch (Exception ex) {
                                    exception_absent = false;
                                    DukeException de = new DukeException("deadline", "date", "empty");
                                    System.out.println(de);
                                }
                            }
                        } else if (type.equals("event")) {
                            try {
                                input_split_arr = input_split_arr[1].split(" /", 2);
                            } catch (Exception ex) {
                                exception_absent = false;
                                DukeException de = new DukeException("event", "content", "empty");
                                System.out.println(de);
                            }
                            if (exception_absent) {
                                try {
                                    task_arr[count] = new Event(input_split_arr[0], input_split_arr[1].split(" ", 2)[1]);
                                } catch (Exception ex) {
                                    exception_absent = false;
                                    DukeException de = new DukeException("event", "date", "empty");
                                    System.out.println(de);
                                }
                            }
                        }
                        if (exception_absent) {
                            System.out.println(indent + "Got it. I've added ths task:");
                            System.out.println(indent + "  " + task_arr[count]);
                            count++;
                            System.out.println(indent + "Now you have " + count + " tasks in the list.");
                        }
                    } else {
                        DukeException de = new DukeException("input", null, "no_meaning");
                        System.out.println(de);
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
