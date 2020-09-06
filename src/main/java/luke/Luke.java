package luke;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

import luke.commands.Command;
import luke.exception.*;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

public class Luke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Luke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (LukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        this.ui = new Ui();
    }

    public String getResponse(String input) {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parseCommand(input);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (LukeException e) {
                ui.showError(e.getMessage());
            }
        }
        System.exit(0);
        return "";
    }

//    /**
//     * Creates a task.Todo object with the given input.
//     *
//     * @param input description of the task.Todo object
//     * @return the todo object with the given input
//     */
//    private static Todo createTodo(String input) throws EmptyTodoException {
//        String todo = input.replaceAll("todo ", "");
//        if (input.equals("todo") || input.equals("todo ")) {
//            throw new EmptyTodoException("\n\tThe description of todo cannot be empty.\n\tPlease make sure you follow the correct format.");
//        } else {
//            return new Todo(todo);
//        }
//    }
//
//    /**
//     * Creates a task.Deadline object with the given input.
//     *
//     * @param input description of the task.Deadline object
//     * @return the task.Deadline object with the given input
//     */
//    private static Deadline createDeadline(String input) throws EmptyDeadlineException, InvalidDeadlineException {
//        String[] deadline = input.split("deadline | /by ");
//        if (input.equals("deadline") || input.equals("deadline ")) {
//            throw new EmptyDeadlineException("\n\tThe description of deadline cannot be empty.\n\tPlease make sure you follow the correct format.");
//        } else if (deadline.length != 3) {
//            throw new InvalidDeadlineException("\n\tYou have typed in an invalid deadline.\n\tPlease make sure you follow the correct format.");
//        } else {
//            return new Deadline(deadline[1], LocalDate.parse(deadline[2]));
//        }
//    }
//
//    /**
//     * Creates a task.Event object with the given input.
//     *
//     * @param input description of the task.Event object
//     * @return the task.Event object with the given input
//     */
//    private static Event createEvent(String input) throws EmptyEventException, InvalidEventException {
//        String[] event = input.split("event | /at ");
//        if (input.equals("event") || input.equals("event ")) {
//            throw new EmptyEventException("\n\tThe description of event cannot be empty.\n\tPlease make sure you follow the correct format.");
//        } else if (event.length != 3) {
//            throw new InvalidEventException("\n\tYou have typed in an invalid event.\n\tPlease make sure you follow the correct format.");
//        } else {
//            return new Event(event[1], event[2]);
//        }
//    }
}