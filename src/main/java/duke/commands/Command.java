package duke.commands;

import duke.exception.DukeException;
import duke.tasks.*;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static duke.parser.Parser.dateProcessor;
import static duke.parser.Parser.dateTimeProcessor;

public class Command {

    public static void executeCommand(EnumCommand enumCommand, String instruction, TaskList result) throws DukeException {
        Ui ui = new Ui();

        switch (enumCommand) {
        case TODO:
            if (instruction.substring(4).strip().equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            result.add(new ToDo(instruction.substring(4).strip()));
            ui.addTaskAlert(result);
            break;
        case DEADLINE:
            if (instruction.substring(8).strip().equals("")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }

            String[] tempDeadline = instruction.substring(8).strip().split("/by");

            if (tempDeadline.length < 2) {
                throw new DukeException("The date and time of the deadline cannot be empty.");
            }

            String descDeadline = tempDeadline[0].strip();
            String dateDeadline = tempDeadline[1].strip();
            LocalDateTime dtDeadLine = dateTimeProcessor(dateDeadline);
            result.add(new Deadlines(descDeadline, dtDeadLine));
            ui.addTaskAlert(result);
            break;
        case EVENT:
            if (instruction.substring(5).strip().equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            }

            String[] tempEvent = instruction.substring(5).strip().split("/at");

            if (tempEvent.length < 2) {
                throw new DukeException("The date and time of the event cannot be empty.");
            }

            String descEvent = tempEvent[0].strip(); // clear the white spaces at the front and at the back
            String dateEvent = tempEvent[1].strip(); // clear the white spaces at the front and at the back
            LocalDateTime dtEvent = dateTimeProcessor(dateEvent);
            result.add(new Events(descEvent, dtEvent));
            ui.addTaskAlert(result);
            break;
        case BYE:
            ui.farewell();
            System.exit(1);
        case DONE:

            if (instruction.substring(4).strip().equals("")) {
                throw new DukeException("The description of a done message cannot be empty.");
            }

            Integer indexDone = Integer.valueOf(instruction.substring(5).strip()) - 1;

            if (indexDone + 1 > result.getSize()) {
                throw new DukeException("The index of the task to be done is out of range.");
            }

            Task tempDone = result.get(indexDone);
            tempDone.markAsDone();
            result.set(indexDone, tempDone);
            ui.doneAlert(tempDone);
            break;
        case DELETE:
            if (instruction.substring(6).strip().equals("")) {
                throw new DukeException("The description of a delete message cannot be empty.");
            }

            Integer indexDelete = Integer.valueOf(instruction.substring(7).strip()) - 1;

            if (indexDelete + 1 > result.getSize()) {
                throw new DukeException("The index of the task to be deleted is out of range.");
            }

            Task tempDelete = result.get(indexDelete);
            result.remove((int) indexDelete);
            ui.deleteTaskAlert(tempDelete, result);
            break;
        case LIST:
            ui.showList(result);
            break;
        case CHECK:
            if (instruction.substring(5).strip().equals("")) {
                throw new DukeException("The \"check\" command is not entered correctly");
            }

            String dateTimeTmp = instruction.substring(5).strip();
            LocalDate dtCheck = dateProcessor(dateTimeTmp);
            TaskList occurings = searchTasksByTime(dtCheck, result);
            ui.showList(occurings);
            break;
        case FIND:
            if (instruction.substring(4).strip().equals("")) {
                throw new DukeException("The \"find\" command is not entered correctly");
            }

            String keyword = instruction.substring(4).strip();
            TaskList matchingList = findTaskByKeyword(keyword, result);
            ui.findTaskAlert(matchingList);
            break;
        }

    }

    public static TaskList searchTasksByTime(LocalDate localDate, TaskList tasks) {
        TaskList occurings = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            boolean isMatch = false;
            Task temp = tasks.get(i);
            if (temp instanceof Deadlines) {
                Deadlines deadlines = (Deadlines) temp;
                if (deadlines.getDate().equals(localDate)) {
                    isMatch = true;
                }
            }
            if (temp instanceof Events) {
                Events deadlines = (Events) temp;
                if (deadlines.getDate().equals(localDate)) {
                    isMatch = true;
                }
            }
            if (isMatch) {
                occurings.add(temp);
            }
        }

        return occurings;
    }


    public static TaskList findTaskByKeyword(String keyword, TaskList tasks) {
        TaskList matchingList = new TaskList();

        for (int i = 0; i < tasks.getSize(); i++) {
            boolean isMatch = false;
            Task temp = tasks.get(i);
            if (temp.getDescription().contains(keyword)) {
                isMatch = true;
            }
            if (isMatch) {
                matchingList.add(temp);
            }
        }

        return matchingList;
    }
}
