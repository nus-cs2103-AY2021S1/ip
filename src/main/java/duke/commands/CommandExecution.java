package duke.commands;

import static duke.parser.Parser.dateParser;
import static duke.parser.Parser.dateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;


/**
 * A class representing command execution.
 */
public class CommandExecution {

    /**
     * Executes the commands from the input.
     *
     * @param enumCommand the enumeration type of the input command.
     * @param instruction the string of input command.
     * @param tasks the TaskList storing the list of tasks.
     * @throws DukeException if the description or the datetime format of the task is illegal.
     */
    public static void executeCommand(EnumCommand enumCommand, String instruction, TaskList tasks) throws DukeException {
        Ui ui = new Ui();
        switch (enumCommand) {
        case TODO:
            if (instruction.substring(4).strip().equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            tasks.add(new ToDo(instruction.substring(4).strip()));
            ui.addTaskAlert(tasks);
            break;
        case DEADLINE:
            if (instruction.substring(8).strip().equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }

            String[] tempDeadline = instruction.substring(8).strip().split("/by");

            if (tempDeadline.length < 2) {
                throw new DukeException("The date and time of the deadline cannot be empty.");
            }

            String deadlineDescription = tempDeadline[0].strip();
            String deadlineDateTime = tempDeadline[1].strip();
            LocalDateTime deadlineDate = dateTimeParser(deadlineDateTime);
            tasks.add(new Deadline(deadlineDescription, deadlineDate));
            ui.addTaskAlert(tasks);
            break;

        case EVENT:
            if (instruction.substring(5).strip().equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            }

            String[] tempEvent = instruction.substring(5).strip().split("/at");

            if (tempEvent.length < 2) {
                throw new DukeException("The date and time of the event cannot be empty.");
            }

            String eventDescription = tempEvent[0].strip(); // clear the white spaces at the front and at the back
            String eventDateTime = tempEvent[1].strip(); // clear the white spaces at the front and at the back
            LocalDateTime eventDate = dateTimeParser(eventDateTime);
            tasks.add(new Event(eventDescription, eventDate));
            ui.addTaskAlert(tasks);
            break;
        case BYE:
            ui.farewell();
            System.exit(0);
            break;
        case DONE:

            if (instruction.substring(4).strip().equals("")) {
                throw new DukeException("The description of a done message cannot be empty.");
            }

            Integer indexDone = Integer.valueOf(instruction.substring(5).strip()) - 1;

            if (indexDone + 1 > tasks.getSize()) {
                throw new DukeException("The index of the task to be done is out of range.");
            }

            Task tempDone = tasks.get(indexDone);
            tempDone.markAsDone();
            tasks.set(indexDone, tempDone);
            ui.doneAlert(tempDone);
            break;
        case DELETE:
            if (instruction.substring(6).strip().equals("")) {
                throw new DukeException("The description of a delete message cannot be empty.");
            }

            Integer indexDelete = Integer.valueOf(instruction.substring(7).strip()) - 1;

            if (indexDelete + 1 > tasks.getSize()) {
                throw new DukeException("The index of the task to be deleted is out of range.");
            }

            Task tempDelete = tasks.get(indexDelete);
            tasks.remove((int) indexDelete);
            ui.deleteTaskAlert(tempDelete, tasks);
            break;
        case LIST:
            ui.showList(tasks);
            break;
        case CHECK:
            if (instruction.substring(5).strip().equals("")) {
                throw new DukeException("The \"check\" command is not entered correctly");
            }

            String dateTimeToCheck = instruction.substring(5).strip();
            LocalDate dateToCheck = dateParser(dateTimeToCheck);
            TaskList occurings = searchTasksByTime(dateToCheck, tasks);
            ui.showList(occurings);
            break;
        case FIND:
            if (instruction.substring(4).strip().equals("")) {
                throw new DukeException("The \"find\" command is not entered correctly");
            }

            String keyword = instruction.substring(4).strip();
            TaskList matches = findTaskByKeyword(keyword, tasks);
            ui.findTaskAlert(matches);
            break;
        }

    }

    /**
     * Returns a TaskList of tasks that meet the input date requirement.
     *
     * @param localDate the date from input that of tasks.
     * @param tasks the TaskList of all tasks.
     * @return the TaskList of tasks that meet the input date requirement.
     */
    public static TaskList searchTasksByTime(LocalDate localDate, TaskList tasks) {
        TaskList occurings = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            boolean isMatch = false;
            Task temp = tasks.get(i);
            if (temp instanceof Deadline) {
                Deadline deadline = (Deadline) temp;
                if (deadline.getDate().equals(localDate)) {
                    isMatch = true;
                }
            }
            if (temp instanceof Event) {
                Event deadline = (Event) temp;
                if (deadline.getDate().equals(localDate)) {
                    isMatch = true;
                }
            }
            if (isMatch) {
                occurings.add(temp);
            }
        }

        return occurings;
    }


    /**
     * Finds the list of tasks that match the keyword.
     *
     * @param keyword the string of keyword that the user wants to search for.
     * @param tasks the current list of tasks.
     * @return the tasklist of tasks that matches the keyword in the input.
     */
    public static TaskList findTaskByKeyword(String keyword, TaskList tasks) {
        TaskList matches = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            boolean isMatch = false;
            Task temp = tasks.get(i);
            if (temp.getDescription().contains(keyword)) {
                isMatch = true;
            }
            if (isMatch) {
                matches.add(temp);
            }
        }

        return matches;
    }
}
