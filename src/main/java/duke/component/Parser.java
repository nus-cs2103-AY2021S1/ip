package duke.component;

import duke.exception.*;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class to handle different user inputs
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Parser object
     *
     * @param tasks
     * @param storage
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Method to read String input and sort cases to produce output
     * @param input String input from user
     * @return output by in accordance to user input
     */
    public String parse(String input) {
        String error = DukeExceptionHandler.handleException(input);
        if (error != null) {
            return error;
        }
        if (input.equals("bye")) {
            return Ui.exit();
        } else if (input.equals("list")) {
            return tasks.printTasks();
        } else {
            String[] words = input.split(" ", 2);
            String inputType = words[0];
            String description = words[1];

            try {
                switch (inputType) {
                    case "todo": {
                        Todo todo = new Todo(description);
                        tasks.addTask(todo, storage);
                        return Ui.print("Got it. I've added this task:\n" + "" + todo.toString() +
                                "\nNow you have " + tasks.getTaskList().size() + " tasks in the list");
                    }
                    case "deadline": {
                        Deadline deadline = new Deadline(description);
                        tasks.addTask(deadline, storage);
                        return Ui.print("Got it. I've added this task:\n" + "" + deadline.toString() +
                                "\nNow you have " + tasks.getTaskList().size() + " tasks in the list");
                    }
                    case "event": {
                        Event event = new Event(description);
                        tasks.addTask(event, storage);
                        return Ui.print("Got it. I've added this task:\n" + "" + event.toString() +
                                "\nNow you have " + tasks.getTaskList().size() + " tasks in the list");
                    }
                    case "done": {
                        int numberInput = Integer.parseInt(description);
                        assert (numberInput > 0 && numberInput < tasks.getTaskList().size() + 1) :
                                "Input number is not within range";
                        tasks.setAllDone(numberInput, storage);
                        Task doneTask = tasks.getTaskList().get(numberInput - 1);
                        return Ui.print("Nice! I've marked this task as done:\n" + doneTask);

                    }
                    case "delete": {
                        int numberInput = Integer.parseInt(description);
                        assert (numberInput > 0 && numberInput <= tasks.getTaskList().size() + 1) :
                                "Input number is not within range";
                        tasks.delete(numberInput);
                        return Ui.printList(tasks.getTaskList());

                    }
                    case "find": {
                        assert !description.equals("") : "Nothing to find!";
                        return tasks.find(input.substring(5));
                    }
                    case "bye": {
                        System.exit(0);
                    }
                    default:
                        return finalErrorMessage();
                }
            } catch (FileNotFoundException e) {
                return e.getMessage();
            } catch (IOException e) {
                return "Something went wrong: " + e.getMessage();
            } catch (DateTimeParseException e) {
                return "Date must be in the YYYY-MM-DD format!";
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }

    public void load(String filePath) throws IOException {
        File data = new File(filePath);
        Scanner read = new Scanner(data);
        ArrayList<Task> list = tasks.getTaskList();
        while (read.hasNext()) {
            String currLine = read.nextLine();
            Scanner cL = new Scanner(currLine);

            String type = cL.next();
            System.out.println(type);
            cL.next();
            String isDone = cL.next();
            cL.next();
            if (type.equals("T")) {
                String desc = cL.nextLine().substring(1);
                Todo toAdd = new Todo(desc);
                list.add(toAdd);
            } else if (type.equals("D")) {
                Deadline toAdd = new Deadline(cL.nextLine().substring(1));
                list.add(toAdd);
            } else if (type.equals("E")) {
                Event toAdd = new Event(cL.nextLine().substring(1));
                list.add(toAdd);
            }
        }
    }


    /**
     * Static method to return a error message
     *
     * @return String of error message
     */
    public static String finalErrorMessage() {
        return "Input not understood";
    }
}