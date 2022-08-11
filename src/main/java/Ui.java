package main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Ui {

    public static List<Task> task_collections = new ArrayList<>();
    protected String FILE_PATH;
    protected String FILE_NAME;
    protected Parser parser;
    protected TaskList TASK_LIST;

    public boolean exception_absent;

    public Ui(String FILE_PATH, String FILE_NAME, List<Task> MEMO_TASK) {
        this.FILE_PATH = FILE_PATH;
        this.FILE_NAME = FILE_NAME;
        this.parser = new Parser();
        TASK_LIST = new TaskList(MEMO_TASK, FILE_PATH, FILE_NAME);
    }

    public void processRequests() {
        String greeting = SpecialFormat.starting_line + "Hello! This is J.A.R.V.I.S.\n" +
                SpecialFormat.indent + "How may I help you?" + SpecialFormat.ending_line;
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        boolean exit_bye = false;
        while (!exit_bye) {
            String input = sc.nextLine();
            String[] COMMAND_RESULT = parser.commandParser(input);
            System.out.println(SpecialFormat.separation_line);
            if (COMMAND_RESULT.length == 1) {
                switch(COMMAND_RESULT[0]){
                    case "bye":
                        System.out.println(SpecialFormat.indent + "Bye. Hope to see you again soon!");
                        exit_bye = true;
                        break;
                    case "list":
                        int temp = 1;
                        System.out.println(SpecialFormat.indent + "Here are the tasks in your list:");
                        Iterator task_iter = TASK_LIST.task_collections.iterator();
                        while (task_iter.hasNext()) {
                            System.out.println(SpecialFormat.indent + temp + "." + task_iter.next());
                            temp++;
                        }
                        break;
                    case "todo":
                        HandleException.handleException(DukeException.ExceptionType.todo_empty);
                        break;
                    case "event":
                        HandleException.handleException(DukeException.ExceptionType.event_empty_incomplete);
                        break;
                    case "deadline":
                        HandleException.handleException(DukeException.ExceptionType.deadline_empty_incomplete);
                        break;
                }
            } else if (COMMAND_RESULT.length == 2 && !COMMAND_RESULT[0].equals("todo")) {
                if (COMMAND_RESULT[0].equals("exception")) {
                    switch(COMMAND_RESULT[1]){
                        case "todo":
                            HandleException.handleException(DukeException.ExceptionType.todo_empty);
                            break;
                        case "event":
                            HandleException.handleException(DukeException.ExceptionType.event_empty_incomplete);
                            break;
                        case "deadline":
                            HandleException.handleException(DukeException.ExceptionType.deadline_empty_incomplete);
                            break;
                        case "empty_illegal":
                            HandleException.handleException(DukeException.ExceptionType.empty_illegal);
                            break;
                    }
                } else {
                    TASK_LIST.editTask(COMMAND_RESULT);
                }
            } else {
                TASK_LIST.addTask(COMMAND_RESULT);
            }
            System.out.println(SpecialFormat.separation_line + "\n");
        }
    }
}

