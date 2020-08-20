package main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

        //Task[] task_arr = new Task[100];
        List<Task> task_collections = new ArrayList<>();
        //int count = 0;

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
                    Iterator task_iter = task_collections.iterator();
                    /*while (task_collections.get(temp - 1) != null) {
                        System.out.println(indent + temp + "." + task_collections.get(temp - 1));
                        temp++;
                    }*/
                    while (task_iter.hasNext()) {
                        System.out.println(indent + temp + "." + task_iter.next());
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
                            //DukeException de = new DukeException("done", null, "empty");
                            DukeException de = new DukeException(DukeException.ExceptionType.empty_illegal);
                            System.out.println(de);
                        }
                        if (exception_absent) {
                            try {
                                task_collections.get(done_number - 1).markAsDone();
                            } catch (Exception ex) {
                                exception_absent = false;
                                //DukeException de = new DukeException("done", null, "illegal");
                                DukeException de = new DukeException(DukeException.ExceptionType.empty_illegal);
                                System.out.println(de);
                            }
                        }
                        if (exception_absent) {
                            System.out.println(indent + "Nice! I've marked this task as done:");
                            System.out.println(indent + "  [\u2713] " + task_collections.get(done_number - 1).toString().split("] ", 2)[1]);
                        }
                    } else if (type.equals("delete")) {
                        int delete_number = -1;
                        String success_result = "";
                        try {
                            delete_number = Integer.parseInt(input_split_arr[1]);
                        } catch (Exception ex) {
                            exception_absent = false;
                            //DukeException de = new DukeException("delete", null, "empty");
                            DukeException de = new DukeException(DukeException.ExceptionType.empty_illegal);
                            System.out.println(de);
                        }
                        if (exception_absent) {
                            try {
                                success_result = task_collections.get(delete_number - 1).toString();
                                task_collections.remove(delete_number - 1);
                            } catch (Exception ex) {
                                exception_absent = false;
                                //DukeException de = new DukeException("delete", null, "illegal");
                                DukeException de = new DukeException(DukeException.ExceptionType.empty_illegal);
                                System.out.println(de);
                            }
                        }
                        if (exception_absent) {
                            System.out.println(indent + "Noted. I've removed this task:");
                            System.out.println(indent + success_result);
                            System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
                        }
                    } else if (type.equals("deadline") || type.equals("event") || type.equals("todo")){
                        if (type.equals("todo")) {
                            try {
                                task_collections.add(new Todo(input_split_arr[1]));
                            } catch (Exception ex) {
                                exception_absent = false;
                                //DukeException de = new DukeException("todo", null, "empty");
                                DukeException de = new DukeException(DukeException.ExceptionType.todo_empty);
                                System.out.println(de);
                            }
                        } else if (type.equals("deadline")) {
                            try {
                                input_split_arr = input_split_arr[1].split(" /", 2);
                            } catch (Exception ex) {
                                exception_absent = false;
                                //DukeException de = new DukeException("deadline", "content", "empty");
                                DukeException de = new DukeException(DukeException.ExceptionType.deadline_empty_incomplete);
                                System.out.println(de);
                            } if (exception_absent) {
                                try {
                                    task_collections.add(new Deadline(input_split_arr[0], input_split_arr[1].split(" ", 2)[1]));
                                } catch (Exception ex) {
                                    exception_absent = false;
                                    //DukeException de = new DukeException("deadline", "date", "empty");
                                    DukeException de = new DukeException(DukeException.ExceptionType.deadline_empty_incomplete);
                                    System.out.println(de);
                                }
                            }
                        } else if (type.equals("event")) {
                            try {
                                input_split_arr = input_split_arr[1].split(" /", 2);
                            } catch (Exception ex) {
                                exception_absent = false;
                                //DukeException de = new DukeException("event", "content", "empty");
                                DukeException de = new DukeException(DukeException.ExceptionType.event_empty_incomplete);
                                System.out.println(de);
                            }
                            if (exception_absent) {
                                try {
                                    task_collections.add(new Event(input_split_arr[0], input_split_arr[1].split(" ", 2)[1]));
                                } catch (Exception ex) {
                                    exception_absent = false;
                                    //DukeException de = new DukeException("event", "date", "empty");
                                    DukeException de = new DukeException(DukeException.ExceptionType.event_empty_incomplete);
                                    System.out.println(de);
                                }
                            }
                        }
                        if (exception_absent) {
                            System.out.println(indent + "Got it. I've added ths task:");
                            System.out.println(indent + "  " + task_collections.get(task_collections.size() - 1));
                            //count++;
                            System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
                        }
                    } else {
                        //DukeException de = new DukeException("input", null, "no_meaning");
                        DukeException de = new DukeException(DukeException.ExceptionType.no_meaning);
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
