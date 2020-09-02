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
            switch(commandTask[0]){
            case "hello":
                if (sleepMode) {
                    sleepMode = false;
                    response.add("Hello! This is J.A.R.V.I.S.\nHow may I help you?");
                } else {
                    response.add("You have already started our dialog.\nPlease enter your command :)");
                }
                break;
            case "bye":
                response.add("Bye. Hope to see you again soon!");
                sleepMode = true;
                break;
            case "list":
                int temp = 1;
                String output = "Here are the tasks in your list:\n";
                Iterator task_iter;
                task_iter = taskList.showList().iterator();
                /*try {

                } catch (Exception e) {
                    taskList = new TaskList(new Storage(filePath, fileName).readMemoTasks(), filePath, fileName);
                    response.addAll(HandleException.handleException(
                            DukeException.ExceptionType.READ_FILE));
                    return response.toArray(new String[response.size()]);
                }*/


                while (task_iter.hasNext()) {
                    output += "\n" + temp + ". " + task_iter.next();
                    temp++;
                }
                response.add(output);
                break;
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

        } else if (commandTask.length == 2 && !commandTask[0].equals("todo")) {
            if (commandTask[0].equals("exception")) {
                switch (commandTask[1]) {
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
                case "empty_illegal":
                    response.addAll(HandleException.handleException(
                            DukeException.ExceptionType.EMPTY_ILLEGAL));
                    break;
                case "no_meaning":
                    response.addAll(HandleException.handleException(
                            DukeException.ExceptionType.NO_MEANING));
                    break;
                }

            } else if (commandTask[0].equals("find")) {
                List<Task> matchList = taskList.searchTask(commandTask[1]);
                if (matchList.size() == 0) {
                    response.add("Sorry, there is no match for your keyword!");
                } else {
                    int temp = 1;
                    String output = "Here are the tasks that match your keyword:\n";
                    Iterator itr = matchList.iterator();
                    while (itr.hasNext()) {
                        output += "\n" + temp + "." + itr.next();
                        temp++;
                    }
                    response.add(output);
                }
            } else {
                response.addAll(taskList.editTask(commandTask));
                /*try {

                } catch (FileNotFoundException e) {
                    Storage s = new Storage(filePath, fileName);
                    s.reachFile();
                    s.write_memory(taskList.showList());
                    try {

                    } catch (Exception ex) {
                        response.addAll(HandleException.handleException(
                                DukeException.ExceptionType.READ_FILE));
                    }
                }*/
            }
        } else {
            response.addAll(taskList.addTask(commandTask));
            /*try {
                taskList = new TaskList(new Storage(filePath, fileName).readMemoTasks(), filePath, fileName);

            } catch (Exception e) {
                response.addAll(HandleException.handleException(
                        DukeException.ExceptionType.READ_FILE));
                return response.toArray(new String[response.size()]);
            }*/

        }

        return response.toArray(new String[response.size()]);

    }

}