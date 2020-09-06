package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

    protected static boolean sleepMode = false;


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
    public String[] processRequests(String input) {
        if (sleepMode && !input.equals("hello")) {
            return new String[]{"You have left the dialog.\nPlease restart the app or enter \"hello\" to wake me up!"};
        }

        List<String> response = new ArrayList<>();
        String[] commandTask = parser.commandParser(input);

        if (commandTask.length == 1) {
            response = processSingleWordRequest(commandTask[0]);
            return response.toArray(new String[response.size()]);
        }

        String commandType = commandTask[0];
        switch (commandType) {
            case "find":
                response = processFindKeyword(commandTask[1]);
                break;
            case "exception":
                response = processException(commandTask[1]);
                break;
            case "done":
            case "delete":
                response.addAll(taskList.editTask(commandTask));
                break;
            case "todo":
            case "event":
            case "deadline":
            default:
                response.addAll(taskList.addTask(commandTask));
                break;
        }

        return response.toArray(new String[response.size()]);
    }


    public List<String> processSingleWordRequest(String command) {
        List<String> response = new ArrayList<>();

        switch(command){
        case "hello":
            response.add(processHelloCommand());
            break;
        case "bye":
            sleepMode = true;
            response.add("Bye. Hope to see you again soon!");
            break;
        case "list":
        default:
            response.add(processListCommand());
            break;
        }

        return response;
    }


    public String processListCommand() {
        int temp = 1;
        String output = "Here are the tasks in your list:\n";
        Iterator task_iter;
        task_iter = taskList.showList().iterator();
        while (task_iter.hasNext()) {
            output += "\n" + temp + ". " + task_iter.next();
            temp++;
        }
        return output;
    }


    public String processHelloCommand() {
        if (sleepMode) {
            sleepMode = false;
            return "Hello! This is J.A.R.V.I.S.\nHow may I help you?";
        }
        return "You have already started our dialog.\nPlease enter your command :)";
    }


    public List<String> processFindKeyword(String keyword) {
        List<String> response = new ArrayList<>();
        List<Task> matchList = taskList.searchTask(keyword);

        if (matchList.size() == 0) {
            response.add("Sorry, there is no match for your keyword!");
            return response;
        }

        String output = "Here are the tasks that match your keyword:\n";
        Iterator itr = matchList.iterator();
        int temp = 1;

        while (itr.hasNext()) {
            output += "\n" + temp + "." + itr.next();
            temp++;
        }
        response.add(output);

        return response;
    }


    public List<String> processException(String exceptionType) {
        List<String> response = new ArrayList<>();
        response.addAll(
                HandleException.handleException(
                        returnException(exceptionType)));
        return response;
    }


    public DukeException.ExceptionType returnException(String exceptionType) {
        switch (exceptionType) {
        case "todo":
            return DukeException.ExceptionType.TODO_INCOMPLETE;
        case "event":
            return DukeException.ExceptionType.EVENT_INCOMPLETE;
        case "deadline":
            return DukeException.ExceptionType.DEADLINE_INCOMPLETE;
        case "empty_illegal":
            return DukeException.ExceptionType.EMPTY_ILLEGAL;
        case "no_meaning":
        default:
            return DukeException.ExceptionType.NO_MEANING;
        }

    }



    /*public List<String> processEmptyTask(List<String> response, String taskType) {
        switch (taskType) {
        case "todo":
            response.addAll(HandleException.handleException(
                    DukeException.ExceptionType.TODO_INCOMPLETE));
            break;
        case "event":
            response.addAll(HandleException.handleException(
                    DukeException.ExceptionType.EVENT_INCOMPLETE));
            break;
        case "deadline":
            response.addAll(HandleException.handleException(
                    DukeException.ExceptionType.DEADLINE_INCOMPLETE));
            break;
        }
        return response;
    }*/

}