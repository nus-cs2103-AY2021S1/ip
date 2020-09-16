package duke.parser;

import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidKeyException;
import duke.exceptions.InvalidRequestException;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interpret the command and do the instruction.
 */
public class Parser {

    public static TaskList taskList;
    private static boolean canClose = false;


    /**
     * Sets taskList for Parser.
     *
     * @param list TaskList that manages tasks.
     */
    public static void setTaskList(TaskList list) {
        taskList = list;
    }


    /**
     * Processes a command sentence.
     *
     * @param command Take in the command to be processed.
     * @throws Exception Throws an exception if the command can not be interpreted.
     */
    public static String processCommand(String command) throws Exception {

        if (command.contains("list")) {

            return taskList.printTaskList();

        } else if (command.contains("hi") || command.contains("hello")) {

            return "Hi!" + "\n"
                    + "What do you want to know about your tasks?";

        } else if (command.contains("bye")) {

            canClose = true;

            return "Bye!";

        } else {

            String[] words = command.split(" ");

            int numberOfWords = words.length;

            assert numberOfWords >= 1 : "Command is incomplete!";

            if (numberOfWords == 0) {
                throw new InvalidRequestException("Command is empty. Please tell me a valid command.");
            }

            if (words[0].equals("done")) {

                if (numberOfWords == 1) {
                    throw new InvalidRequestException("Which task would you like to mark as done?");
                }
                if (numberOfWords > 2) {
                    throw new InvalidRequestException("Sorry. I can only handle one task at a time!!");
                }

                Integer index = Integer.parseInt(words[1]);

                if (taskList.findListSize() < index) {
                    throw new InvalidRequestException("I could not find this task, please tell me a valid task index.");
                }
                if (index < 0) {
                    throw new InvalidRequestException("Task index is invalid, please tell me a valid one.");
                }

                return taskList.markAsDone(index);

            } else if (words[0].equals("delete")) {

                if (numberOfWords == 1) {
                    throw new InvalidRequestException("What task would you like to delete from the list?");
                }

                if (numberOfWords > 2) {
                    throw new InvalidRequestException("Sorry, I can only handle one task at a time.");
                }

                Integer index = Integer.parseInt(words[1]);

                if (taskList.findListSize() < index) {
                    throw new InvalidRequestException("I could not find this task, please enter a valid task index.");
                }
                if (index < 0) {
                    throw new InvalidRequestException("Task index is invalid, please tell me a valid one.");
                }

                return taskList.deleteTask(index);

            } else if (words[0].equals("find")) {

                if (words.length > 2) {

                    throw new InvalidKeyException("Sorry, I can only handle one keyword.");
                }

                return taskList.findTask(words[1]);

            } else {

                Task newTask;

                if (words[0].equals("todo")) {

                    if (numberOfWords == 1) {
                        throw new InvalidRequestException("What is the Todo that you would like to be added to list?");
                    }

                    String name = command.split(" ", 2)[1];

                    newTask = new Todo(name);

                } else if (words[0].equals("event")) {

                    if (numberOfWords == 1) {
                        throw new InvalidEventException("What is the event that you would like to be added to list?");
                    }

                    String content = command.split(" ", 2)[1];

                    if (content.split(" /at ").length < 2) {
                        throw new InvalidEventException("Please tell me both the name as well as the period of time of the event!");
                    }

                    String name = content.split(" /at ")[0];

                    String timePeriod = content.split(" /at ")[1];

                    newTask = new Event(name, timePeriod);

                } else if (words[0].equals("deadline")) {

                    if (numberOfWords == 1) {
                        throw new InvalidDeadlineException("What is the Deadline that you would like to be added to list?");
                    }

                    String content = command.split(" ", 2)[1];

                    if (content.split(" /by ").length < 2) {
                        throw new InvalidDeadlineException("Please tell me both the name as well as the due date of the event!");
                    }

                    String name = content.split(" /by ")[0];

                    String dueDate = content.split(" /by ")[1];

                    TimeConverter timeConverter = new TimeConverter();

//                    String formattedDueDate = timeConverter.convertTime(dueDate);

//                    newTask = new Deadline(name, formattedDueDate);

                    newTask = new Deadline(name, dueDate);

                } else {

                    throw new InvalidRequestException("Sorry, I could not understand what you said. Please say another one!");

                }

                return taskList.addTask(newTask);

            }
        }
    }

    public static boolean canClose() {
        return canClose;
    }

}
