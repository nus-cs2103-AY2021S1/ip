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
     * Initializes the filepath, filename, and list of tasks already stored in memory.
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
     * Returns processed user input of commands once the system is awake.
     * Returns system response if the chatbot is in sleeping mode.
     *
     * @param input  User input of command.
     * @return processed system response to user commands.
     */
    public String[] processRequests(String input) {
        if (sleepMode && !input.equals("hello")) {
            return new String[]{"You have left the dialog.\nPlease restart the app or enter \"hello\" to wake me up!"};
        }

        List<String> response;
        String[] commandTask = parser.commandParser(input);

        if (commandTask.length == 1) {
            response = processSingleWordRequest(commandTask[0]);
        } else {
            response = processComplexRequest(commandTask);
        }

        return response.toArray(new String[response.size()]);
    }


    /**
     * Returns system response to single-word commands.
     *
     * @param command  user input of command.
     * @return output list in response to simple commands.
     */
    public List<String> processSingleWordRequest(String command) {
        List<String> response = new ArrayList<>();

        switch(command){
        case "hello":
            response.addAll(processHelloCommand());
            break;
        case "help":
            response.add(DukeException.fullGuide);
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


    /**
     * Returns list of all tasks in response to 'list' command.
     *
     * @return system response to 'hello' command.
     */
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


    /**
     * Returns opening address in response to 'hello' command that wakes the system.
     * Modifies the system sleep-status when the system is successfully waken.
     *
     * @return system response to 'hello' command.
     */
    public List<String> processHelloCommand() {
        List<String> output = new ArrayList<>();
        if (sleepMode) {
            sleepMode = false;
            output.add("Hello! This is J.A.R.V.I.S.\nHow may I help you?");
            output.add("Enter 'help' for command guide.");
        } else {
            output.add("You have already started our dialog.\nPlease enter your command :)");
        }
        return output;

    }


    /**
     * Returns system response to multiple-word commands.
     *
     * @param commandTask  processed user input of command.
     * @return output list containing system response to complex commands.
     */
    public List<String> processComplexRequest(String[] commandTask) {

        String commandType = commandTask[0];
        switch (commandType) {
        case "find":
            return processFindKeyword(commandTask[1]);
        case "exception":
            return processException(commandTask[1]);
        case "done":
        case "delete":
            return processEditRequest(commandTask);
        case "todo":
        case "event":
        case "deadline":
        default:
            return processAddRequest(commandTask);
        }
    }


    /**
     * Returns specified user input of 'editing' requests, i.e. 'done' and 'delete'.
     *
     * @param commandTask  processed user input of command.
     * @return further-processed user input of modification commands.
     */
    public List<String> processEditRequest(String[] commandTask) {
        List<String> response = taskList.editTask(commandTask);
        return response;
    }


    /**
     * Returns output list containing system response to task-addition commands'.
     *
     * @param commandTask  processed user input of task-addition command.
     * @return output list containing system response to todo', 'event', 'deadline' commands.
     */
    public List<String> processAddRequest(String[] commandTask) {
        List<String> response = taskList.addTask(commandTask);
        return response;
    }


    /**
     * Returns output list containing system response to 'find' command.
     *
     * @param keyword  word to be searched in the task list.
     * @return output list containing system response to 'find' command.
     */
    public List<String> processFindKeyword(String keyword) {
        List<String> response = new ArrayList<>();
        List<Task> matchList = taskList.searchTask(keyword);

        if (matchList.size() == 0) {
            response.add("Sorry, there is no match for your keyword!");
            return response;
        }

        String output = "Here are the tasks that match your keyword:\n";
        Iterator itr = matchList.iterator();
        int taskNumber = 1;
        while (itr.hasNext()) {
            output += "\n" + taskNumber + "." + itr.next();
            taskNumber++;
        }
        response.add(output);
        return response;
    }


    /**
     * Returns output list after adding the information about the exception.
     *
     * @param exceptionType  type of exception encountered.
     * @return output list after adding the information about the exception.
     */
    public List<String> processException(String exceptionType) {
        List<String> response = new ArrayList<>();
        response.addAll(
                HandleException.handleException(
                        returnException(exceptionType)));
        return response;
    }


    /**
     * Returns ExceptionType in response to different exceptions caught.
     *
     * @param exceptionType  type of exception encountered.
     * @return ExceptionType that corresponds to the exception caught.
     */
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
        case "improperDateTime":
            return DukeException.ExceptionType.IMPROPER_DATETIME;
        case "find":
            return DukeException.ExceptionType.FIND_INCOMPLETE;
        case "no_meaning":
        default:
            return DukeException.ExceptionType.NO_MEANING;
        }

    }

}