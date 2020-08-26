package main.java.duke;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Ui {

    /** Directory of local file to store tasks */
    protected String filePath;

    /** Name of local file to store tasks */
    protected String fileName;

    /** Parser object to understand requests */
    protected Parser parser;

    /** TaskList object to store Task objects and modify the list */
    protected TaskList taskList;


    /**
     * Constructor of Ui class.
     * Initialize the filepath, filename, and list of tasks already stored in memory.
     *
     * @param filePath  Directory of local file to read and write.
     * @param fileName  Name of local file to read and write.
     * @param memoTask  List of Task objects stored in memory.
     */
    public Ui(String filePath, String fileName, List<Task> memoTask) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.parser = new Parser();
        taskList = new TaskList(memoTask, filePath, fileName);
    }


    /**
     * Interact with users and process inputs.
     */
    public void processRequests() {
        String greeting = SpecialFormat.STARTING_LINE + "Hello! This is J.A.R.V.I.S.\n" +
                SpecialFormat.INDENT + "How may I help you?" + SpecialFormat.ENDING_LINE;
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        boolean exit_bye = false;

        while (!exit_bye) {
            String input = sc.nextLine();
            String[] commandTask = parser.commandParser(input);
            System.out.println(SpecialFormat.SEPARATION_LINE);

            if (commandTask.length == 1) {
                switch(commandTask[0]){
                case "bye":
                    System.out.println(SpecialFormat.INDENT + "Bye. Hope to see you again soon!");
                    exit_bye = true;
                    break;
                case "list":
                    int temp = 1;
                    System.out.println(SpecialFormat.INDENT + "Here are the tasks in your list:");
                    Iterator task_iter = taskList.showList().iterator();
                    while (task_iter.hasNext()) {
                        System.out.println(SpecialFormat.INDENT + temp + "." + task_iter.next());
                        temp++;
                    }
                    break;
                case "todo":
                    HandleException.handleException(DukeException.ExceptionType.TODO_INCOMPLETE);
                    break;
                case "event":
                    HandleException.handleException(DukeException.ExceptionType.EVENT_INCOMPLETE);
                    break;
                case "deadline":
                    HandleException.handleException(DukeException.ExceptionType.DEADLINE_INCOMPLETE);
                    break;
                }
            } else if (commandTask.length == 2 && !commandTask[0].equals("todo")) {
                if (commandTask[0].equals("exception")) {
                    switch (commandTask[1]) {
                    case "todo":
                        HandleException.handleException(DukeException.ExceptionType.TODO_INCOMPLETE);
                        break;
                    case "event":
                        HandleException.handleException(DukeException.ExceptionType.EVENT_INCOMPLETE);
                        break;
                    case "deadline":
                        HandleException.handleException(DukeException.ExceptionType.DEADLINE_INCOMPLETE);
                        break;
                    case "empty_illegal":
                        HandleException.handleException(DukeException.ExceptionType.EMPTY_ILLEGAL);
                        break;
                    case "no_meaning":
                        HandleException.handleException(DukeException.ExceptionType.NO_MEANING);
                        break;
                    }
                } else if (commandTask[0].equals("find")) {
                    List<Task> matchList = taskList.searchTask(commandTask[1]);
                    if (matchList.size() == 0) {
                        System.out.println(
                                SpecialFormat.INDENT + "Sorry, there is no match for your keyword!");
                    } else {
                        int temp = 1;
                        System.out.println(SpecialFormat.INDENT +
                                "Here are the tasks that match your keyword:");
                        Iterator itr = matchList.iterator();
                        while (itr.hasNext()) {
                            System.out.println(SpecialFormat.INDENT + temp + "." + itr.next());
                            temp++;
                        }
                    }
                }
            } else if (commandTask.length == 2 && !commandTask[0].equals("todo")) {
                if (commandTask[0].equals("exception")) {
                    switch(commandTask[1]){
                    case "todo":
                        HandleException.handleException(DukeException.ExceptionType.TODO_INCOMPLETE);
                        break;
                    case "event":
                        HandleException.handleException(DukeException.ExceptionType.EVENT_INCOMPLETE);
                        break;
                    case "deadline":
                        HandleException.handleException(DukeException.ExceptionType.DEADLINE_INCOMPLETE);
                        break;
                    case "empty_illegal":
                        HandleException.handleException(DukeException.ExceptionType.EMPTY_ILLEGAL);
                        break;
                    case "no_meaning":
                        HandleException.handleException(DukeException.ExceptionType.NO_MEANING);
                        break;
                    }
                } else {
                    taskList.editTask(commandTask);
                }
            } else {
                taskList.addTask(commandTask);
            }
            System.out.println(SpecialFormat.SEPARATION_LINE + "\n");
        }
    }

}