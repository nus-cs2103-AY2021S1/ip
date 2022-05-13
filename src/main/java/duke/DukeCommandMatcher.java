package duke;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

import duke.command.metacommands.ContentMetaCommand;
import duke.command.metacommands.DurationMetaCommand;
import duke.command.metacommands.MetaCommand;
import duke.command.metacommands.TimeMetaCommand;
import duke.exceptions.CommandNotFoundException;
import duke.exceptions.DukeException;
import duke.exceptions.NullCommandException;
import duke.parsers.CommandParser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.SingletonTaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Printer;
import duke.utils.Constants;


/**
 * The class to match the command to Duke's response.
 */
public class DukeCommandMatcher {


    private static final String DURATION_DELIMITER = " ~ ";
    private static final DateTimeFormatter STANDARD_DATE_TIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern("MMM d yyyy")
                    .optionalStart().appendPattern(" HH:mm")
                    .optionalEnd().toFormatter();

    private SingletonTaskList taskList;

    public DukeCommandMatcher(Storage database) {
        this.taskList = SingletonTaskList.getInstance(database);
    }

    /**
     * Match the command to corresponding behavior.
     * @param command
     * @return message of implementation
     * @throws DukeException when error occurs during Duke implementation
     */
    public String handleCommand(String command) throws DukeException {
        if (Objects.equals(command, "")) {
            throw new NullCommandException(command);
        }

        CommandParser parser = new CommandParser(command);
        MetaCommand outcome = parser.parse();


        switch (outcome.getType()) {
        case EXIT:
            return handleExit();
        case LIST:
            return handleList();
        case DONE:
            return handleDone(outcome);
        case TODO:
            return handleTodo(outcome);
        case DEADLINE:
            return handleDeadline(outcome);
        case EVENT:
            return handleEvent(outcome);
        case DELETE:
            return handleDelete(outcome);
        case FIND:
            return handleFind(outcome);
        case HELP:
            return handleHelp();
        default:
            break;
        }

        throw new CommandNotFoundException(command);
    }

    private String handleExit() {
        Printer.printBye();
        return Constants.EXITRESPONSE;
    }

    private String handleAdd(Task task) {
        return taskList.add(task);
    }

    private String handleList() throws DukeException {
        return taskList.listAll();
    }

    private String handleDone(MetaCommand metaCommand) throws DukeException {
        String idxStr = ((ContentMetaCommand) metaCommand).getContent();
        int targetTaskPos = Integer.parseInt(idxStr) - 1;
        return taskList.setTaskDone(targetTaskPos);
    }

    private String handleTodo(MetaCommand metaCommand) {
        String content = ((ContentMetaCommand) metaCommand).getContent();
        ToDo todo = new ToDo(content);
        return handleAdd(todo);
    }

    private String handleDeadline(MetaCommand metaCommand) {
        String content = ((ContentMetaCommand) metaCommand).getContent();
        String time = STANDARD_DATE_TIME_FORMATTER.format(((TimeMetaCommand) metaCommand).getTime());
        Deadline deadline = new Deadline(content, time);
        System.out.println("deadline time: " + deadline.getTask());
        return handleAdd(deadline);
    }

    private String handleEvent(MetaCommand metaCommand) {
        String content = ((DurationMetaCommand) metaCommand).getContent();
        String startTime = STANDARD_DATE_TIME_FORMATTER.format(((DurationMetaCommand) metaCommand).getStartTime());
        String endTime = STANDARD_DATE_TIME_FORMATTER.format(((DurationMetaCommand) metaCommand).getEndTime());
        Event event = new Event(content, startTime + DURATION_DELIMITER + endTime);
        return handleAdd(event);
    }

    private String handleDelete(MetaCommand metaCommand) throws DukeException {
        String idxStr = ((ContentMetaCommand) metaCommand).getContent();
        int targetTaskPos = Integer.parseInt(idxStr) - 1;
        return taskList.delete(targetTaskPos);
    }

    private String handleFind(MetaCommand metaCommand) throws DukeException {
        String queryKey = ((ContentMetaCommand) metaCommand).getContent();
        return taskList.query(queryKey);
    }

    private String handleHelp() {
        return Printer.printListCommands();
    }

}
