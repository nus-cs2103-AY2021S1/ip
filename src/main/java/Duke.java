package main.java;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Duke {
    private List<Task> list = new ArrayList<>();

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static final String TERMINATE_COMMAND = "bye";
    static final String LIST_COMMAND  = "list";
    static final String DONE_COMMAND = "done";
    static final String TODO_COMMAND = "todo";
    static final String DEADLINE_COMMAND =  "deadline";
    static final String EVENT_COMMAND = "event";
    static final String HELP_COMMAND = "--help";

    static final String LINE = "     ____________________________________________________________\n";
    static final String DOUBLE_TAB = "      ";


    public void mainProgram() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals(TERMINATE_COMMAND)) {
                bye();
                break;
            } else if (input.equals(LIST_COMMAND)) {
                runList();
            } else if(input.contains(DONE_COMMAND)){
                done(input);
            } else if(input.contains(TODO_COMMAND)){
                try {
                    todoTask(input);
                } catch (IndexOutOfBoundsException e) {
                    printException(new TodoException());
                }
            } else if(input.contains(DEADLINE_COMMAND)){
                try {
                    deadlineTask(input);
                } catch (IndexOutOfBoundsException e) {
                    printException(new DeadlineException());
                }
            } else if(input.contains(EVENT_COMMAND)){
                try {
                    eventTask(input);
                } catch(IndexOutOfBoundsException e) {
                   printException(new EventException());
                }
            } else if(input.contains(HELP_COMMAND)){
                help();
            } else {
                printException(new AnonymousException(input));
            }
        }
    }

    private void help() {
        printCommandList("COMMAND","FORMAT");
        printCommandList("todo","todo <TASK_NAME>");
        printCommandList("event","event <EVENT_NAME> / <EVENT_TIME>");
        printCommandList("deadline", "deadline <DEADLINE_NAME> / <DEADLINE_TIME>");
    }

    private void printCommandList(String command, String format) {
        String indentation = "%-20s%s%n" ;
        String SINGLE_TAB = "   ";
        System.out.printf(indentation,command,format);
    }


    private void runList() {
        printMessage("Here are the tasks in your list:\n" + showList());
    }

    private void printException(Exception e) {
        System.out.print(LINE);
        System.out.println(format(e.toString()));
        System.out.println(LINE);
    }

    private String format(String text) {
        return DOUBLE_TAB + text;
    }
    private void printMessage(String text) {
        System.out.print(LINE);
        System.out.println(format(text));
        System.out.println(LINE);
    }

    private void deadlineTask(String input) {
        String taskDetails = input.split("\\s", 2)[1];
        String[] splitTaskDetails = taskDetails.split("/");
        String taskDescription = splitTaskDetails[0];
        String taskTime = splitTaskDetails[1];
        final String BY = taskTime.split("\\s",2)[0];
        final String TIME = taskTime.split("\\s",2)[1];
        DeadlineTask deadlineTask = new DeadlineTask(taskDescription);
        list.add(deadlineTask);
        printMessage("Got it. I've added this task:\n" +
                format(deadlineTask + " (" + BY + ": " + TIME + ")\n")
                + format("Now you have " + list.size() + " tasks in the list") );

    }

    private void eventTask(String input) {
        String taskDetails = input.split("\\s", 2)[1];
        String taskDescription = taskDetails.split("/", 2)[0];
        String taskTime = taskDetails.split("/", 2)[1];
        final String AT = taskTime.split("\\s", 2)[0];
        final String TIME = taskTime.split("\\s", 2)[1];
        EventTask eventTask = new EventTask(taskDescription);
        list.add(eventTask);
        printMessage("Got it. I've added this task:\n" +
                format(eventTask + " (" + AT + ": " + TIME + ")\n")
        + format("Now you have " + list.size() + " tasks in the list" ));
    }

    private void todoTask(String input) {
        String taskDescription = input.split("\\s", 2)[1];
        TodoTask todoTask = new TodoTask(taskDescription);
        list.add(todoTask);
        printMessage("Got it. I've added this task :\n" + format(todoTask +"\n" +
                format("Now you have " + list.size() + " tasks in the list")));
    }

    private void done(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        Task task = list.get(index-1);
        task.setStatus(true);
        printMessage("Nice! I've marked this task as done:\n" + DOUBLE_TAB + task);
    }

    private String showList() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size() ; i++) {
            if(i != list.size()-1) {
                sb.append(DOUBLE_TAB + (i + 1) + ". " + list.get(i) + "\n");
            } else {
                sb.append(DOUBLE_TAB + (i + 1) + ". " + list.get(i));
            }
        }
        return sb.toString();
    }

    private void greet() {
        System.out.println(LINE + DOUBLE_TAB + "Hello! I'm Rich.\n" + DOUBLE_TAB  + "What can I do for you?\n" + LINE);
    }


    public void bye() {
        printMessage("Bye.Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.mainProgram();
    }
}
