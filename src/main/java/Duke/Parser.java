package duke;

import duke.exception.DeleteOutOfRangeException;
import duke.exception.DoneOutOfRangeException;
import duke.exception.EmptyDeadlineException;
import duke.exception.EmptyEventException;
import duke.exception.EmptyTodoException;
import duke.exception.MissingDeadlineDateException;
import duke.exception.MissingDeleteArgumentException;
import duke.exception.MissingDoneArgumentException;
import duke.exception.MissingEventDateException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Static class that parses user inputs and executes them.
 */
public class Parser {

    /**
     * Parses and executes user input, adding tasks to given TaskList and output
     * messages through given Ui.
     *
     * @param input  A String of user input.
     * @param tasks  A TaskList object that the tasks will be modified in.
     * @param ui  An Ui object that will display the outputs and collect user inputs.
     * @return  A Boolean of whether TaskList was updated.
     * @throws MissingDoneArgumentException  If done was input without an argument.
     * @throws DoneOutOfRangeException  If done was input with an argument out of range.
     * @throws MissingDeleteArgumentException  If delete was input without an argument.
     * @throws DeleteOutOfRangeException  If delete was input with an argument out of range.
     * @throws EmptyTodoException  If todo was input without a description.
     * @throws MissingDeadlineDateException  If deadline was input without a date.
     * @throws EmptyDeadlineException  If deadline was input without a description.
     * @throws MissingEventDateException  If event was input without a date.
     * @throws EmptyEventException  If event was input without a description.
     * @throws UnknownCommandException  If input is not recognised by Duke.
     */
    public static boolean parseAndExecute(String input, TaskList tasks, Ui ui) throws MissingDoneArgumentException, DoneOutOfRangeException,
            MissingDeleteArgumentException, DeleteOutOfRangeException, EmptyTodoException, MissingDeadlineDateException,
            EmptyDeadlineException, MissingEventDateException, EmptyEventException, UnknownCommandException {
        //DONE PORTION HERE----------------------------------------------------------
        if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
            if (input.length() <= 5) {
                throw new MissingDoneArgumentException();
            }
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= tasks.getCount() || index < 0) {
                throw new DoneOutOfRangeException();
            }
            ui.sendMarkedAsDoneMessage(
                    tasks.markTaskAsDone(index) //checkthis
            );
            return true;

            //DELETE PORTION HERE---------------------------------------------------------
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            if (input.length() <= 7) {
                throw new MissingDeleteArgumentException();
            }
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= tasks.getCount() || index < 0) {
                throw new DeleteOutOfRangeException();
            }
            ui.sendDeleteTaskMessage(
                    tasks.deleteTask(index) //checkthis
            );
            ui.sendCount(tasks);
            return true;

            //TOD0 PORTION HERE-----------------------------------------------------------
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
            if (input.length() == 4) {
                throw new EmptyTodoException();
            }
            String description = input.substring(5);
            if (description.length() == 0) {
                throw new EmptyTodoException();
            }
            ToDo taskToAdd = new ToDo(description);
            ui.sendAddTaskMessage(
                    tasks.addTask(taskToAdd)
            );
            ui.sendCount(tasks);
            return true;

            //DEADLINE PORTION HERE--------------------------------------------------------
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
            int index = input.indexOf("/");
            if (index == -1) {
                throw new MissingDeadlineDateException();
            }
            if (input.length() == 8 || input.indexOf("/") <= 9) {
                throw new EmptyDeadlineException();
            }
            String description = input.substring(9,index-1);
            if (description.length() == 0) {
                throw new EmptyDeadlineException();
            }
            String date = input.substring(index+4);
            if (date.length() == 0) {
                throw new MissingDeadlineDateException();
            }
            Deadline taskToAdd = new Deadline(description, date);
            ui.sendAddTaskMessage(
                    tasks.addTask(taskToAdd)
            );
            ui.sendCount(tasks);
            return true;

            //EVENT PORTION HERE-----------------------------------------------------------
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            int index = input.indexOf("/");
            if (index == -1) {
                throw new MissingEventDateException();
            }
            if (input.length() == 5 || input.indexOf("/") <= 6) {
                throw new EmptyEventException();
            }
            String description = input.substring(6,index-1);
            if (description.length() == 0) {
                throw new EmptyEventException();
            }
            String date = input.substring(index+4);
            if (date.length() == 0) {
                throw new MissingEventDateException();
            }
            Event taskToAdd = new Event(description, date);
            ui.sendAddTaskMessage(
                    tasks.addTask(taskToAdd)
            );
            ui.sendCount(tasks);
            return true;

            //LIST PORTION HERE------------------------------------------------------------
        } else if (input.equals("list")) {
            ui.listTasks(tasks);
            return false;
        } else {
            throw new UnknownCommandException();
        }
    }
}
