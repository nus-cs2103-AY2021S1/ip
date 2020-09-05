package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.command.Command;
import duke.command.CommandType;
import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.QueryCommand;
import duke.command.EditCommand;

import java.util.Scanner;

public class Parser {
    public static Command parse(String string) throws DukeException {
        Scanner parsingScanner = new Scanner(string);
        if (parsingScanner.hasNext()) {
            String commandString = parsingScanner.next();
            Command command; // Has to be declared beforehand for correct scope
            switch (commandString.toLowerCase()) {
            case "bye":
                command = new ExitCommand(CommandType.BYE);
                break;
            case "list":
                command = new QueryCommand(CommandType.LIST);
                break;
            case "done":
                if (parsingScanner.hasNext()) {
                    try {
                        int index = Integer.parseInt(parsingScanner.next());
                        command = new EditCommand(CommandType.DONE, index);
                    } catch (NumberFormatException e) {
                        throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
                    }
                } else {
                    throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
                }
                break;
            case "delete":
                if (parsingScanner.hasNext()) {
                    try {
                        int index = Integer.parseInt(parsingScanner.next());
                        command = new EditCommand(CommandType.DELETE, index);
                    } catch (NumberFormatException e) {
                        throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
                    }
                } else {
                    throw DukeException.INVALID_TASK_INDEX_EXCEPTION;
                }
                break;
            case "todo":
                if (parsingScanner.hasNext()) {
                    command = new AddCommand(CommandType.TODO, parsingScanner.nextLine().trim());
                } else {
                    throw DukeException.INVALID_TASK_DESCRIPTION_EXCEPTION;
                }
                break;
            case "deadline":
                if (parsingScanner.hasNext()) {
                    try {
                        String[] details = parsingScanner.nextLine().trim().split("( /by )", 2);
                        command = new AddCommand(CommandType.DEADLINE, details[0], details[1]);
                    } catch (IndexOutOfBoundsException e) {
                        throw DukeException.INVALID_DEADLINE_FORMAT_EXCEPTION;
                    }
                } else {
                    throw DukeException.INVALID_TASK_DESCRIPTION_EXCEPTION;
                }
                break;
            case "event":
                if (parsingScanner.hasNext()) {
                    try {
                        String[] details = parsingScanner.nextLine().trim().split("( /at )", 2);
                        command = new AddCommand(CommandType.EVENT, details[0], details[1]);
                    } catch (IndexOutOfBoundsException e) {
                        throw DukeException.INVALID_EVENT_FORMAT_EXCEPTION;
                    }
                } else {
                    throw DukeException.INVALID_TASK_DESCRIPTION_EXCEPTION;
                }
                break;
            default:
                throw DukeException.INVALID_COMMAND_EXCEPTION;
            }
            return command;
        } else {
            throw DukeException.INVALID_COMMAND_EXCEPTION;
        }
    }

    public static Task parseTaskFromFile(String line) throws DukeException {
        Task task; // Has to be declared beforehand for correct scope
        Scanner parsingScanner = new Scanner(line);
        String type = parsingScanner.next();

        switch (type) {
        case "T":
            parsingScanner.next();
            int isDoneInteger = parsingScanner.nextInt();
            parsingScanner.next();
            ToDo toDo = new ToDo(parsingScanner.nextLine());
            if (isDoneInteger == 1) {
                toDo.markAsDone();
            }
            task = toDo;
            break;
        case "D":
            parsingScanner.next();
            isDoneInteger = parsingScanner.nextInt();
            parsingScanner.next();
            String[] details = parsingScanner.nextLine().split("( by )", 2);
            Deadline deadline = new Deadline(details[0], details[1]);
            if (isDoneInteger == 1) {
                deadline.markAsDone();
            }
            task = deadline;
            break;
        case "E":
            parsingScanner.next();
            isDoneInteger = parsingScanner.nextInt();
            parsingScanner.next();
            details = parsingScanner.nextLine().split("( at )", 2);
            Event event = new Event(details[0], details[1]);
            if (isDoneInteger == 1) {
                event.markAsDone();
            }
            task = event;
            break;
        default:
            throw DukeException.FILE_PARSING_EXCEPTION;
        }
        return task;
    }
}
