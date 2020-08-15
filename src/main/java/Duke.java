package main.java;

import javax.print.DocFlavor;
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
    static final String LINE = "    _____________________________________________________________________\n";
    static final String SINGLE_TAB = "  ";
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
                System.out.print(LINE);
                System.out.println(DOUBLE_TAB + "Here are the tasks in your list: ");
                System.out.print(showList());
                System.out.print(LINE);
            } else if(input.contains(DONE_COMMAND)){
                doneOutput(input);
            } else if(input.contains(TODO_COMMAND)){
                todoTask(input);
                System.out.println(DOUBLE_TAB +"Now you have " +list.size() +" tasks in the list\n" + LINE);
            } else if(input.contains(DEADLINE_COMMAND)){
                deadlineTask(input);
                System.out.println(DOUBLE_TAB +"Now you have " +list.size() +" tasks in the list\n" + LINE);
            } else if(input.contains(EVENT_COMMAND)){
                eventTask(input);
                System.out.println(DOUBLE_TAB +"Now you have " +list.size() +" tasks in the list\n"+LINE);
            }else {
                add(input);
            }
        }
    }

    private void deadlineTask(String input) {
        String taskDetails = input.split("\\s",2)[1];
        String[] splitTaskDetails = taskDetails.split("/");
        String taskDescription = splitTaskDetails[0];
        String taskTime = splitTaskDetails[1];
        final String BY = taskTime.split("\\s")[0];
        final String TIME = taskTime.split("\\s")[1];
        DeadlineTask deadlineTask = new DeadlineTask(taskDescription);
        list.add(deadlineTask);
        System.out.println(LINE + DOUBLE_TAB + "Got it. I've added this task:\n" +
                DOUBLE_TAB  + deadlineTask +" (" + BY + ": " + TIME +")");
    }

    private void eventTask(String input) {
        String taskDetails = input.split("\\s",2)[1];
        String taskDescription = taskDetails.split("/",2)[0];
        String taskTime = taskDetails.split("/",2)[1];
        final String AT = taskTime.split("\\s")[0];
        final String TIME = taskTime.split("\\s")[1];
        EventTask eventTask = new EventTask(taskDescription);
        list.add(eventTask);
        System.out.println(LINE + DOUBLE_TAB + "Got it. I've added this task:\n" +
                DOUBLE_TAB  + eventTask +" (" + AT + ": " + TIME +")");
    }

    private void todoTask(String input) {
        String taskDescription = input.split("\\s")[1];
        TodoTask todoTask = new TodoTask(taskDescription);
        list.add(todoTask);
        System.out.println(LINE + DOUBLE_TAB + "Got it. I've added this task :\n"
                + DOUBLE_TAB + todoTask);
    }

    private void doneOutput(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        Task task = list.get(index-1);
        task.setStatus(true);
        System.out.println(LINE +DOUBLE_TAB +"Nice! I've marked this task as done:\n" + DOUBLE_TAB+  task + "\n"+ LINE);

    }

    private String showList() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < list.size() ; i++) {
            sb.append(DOUBLE_TAB + (i+1) + ". " + list.get(i) +"\n");
        }
        return sb.toString();
    }

    private void greet() {
        System.out.println(LINE + DOUBLE_TAB + "Hello! I'm Rich.\n" + DOUBLE_TAB  + "What can I do for you?\n" + LINE);
    }

    private void add(String input) {
        list.add(new Task(input));
        System.out.println(LINE + DOUBLE_TAB + "added: " + input + "\n" + LINE);
    }

    public void bye() {
        System.out.println(LINE + DOUBLE_TAB +"Bye.Hope to see you again soon!\n" + LINE);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.mainProgram();
    }
}
