package duke.command;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

/**
 * Represents a command for finding tasks with the given time constraint.
 */
public class HappenCommand extends Command {

    public static final int HAPPEN_COMMAND_COMPONENTS = 2;
    public static final int LONG_HAPPEN_COMMAND_COMPONENTS = 3;

    /**
     * Creates a command for filtering tasks on when it happens.
     * @param input the input command classified as HappenCommand, starting with "happen "
     */
    public HappenCommand(String input) {
        super(input);
    }

    /**
     * Executes the command, prints the filtered result on ui.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string of the input time constrain with a number representing the size of the resulting task list
     * @throws InvalidCommandException if the input cannot be processed correctly or does not make sense
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException {
        assert input.startsWith("happen ") : "Happen command does not start with 'happen '";
        String description = input.substring(Parser.HAPPEN_COMMAND_PREFIX.length());
        String[] detail = description.split(Parser.SPACE_STRING);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            if (detail[0].equals(Parser.ON) && detail.length == HAPPEN_COMMAND_COMPONENTS) {
                return getHappenOnList(ui, list, detail, inputFormat, outputFormat);
            } else if (detail[0].equals(Parser.BEFORE) && detail.length == HAPPEN_COMMAND_COMPONENTS) {
                return getHappenBeforeList(ui, list, detail, inputFormat, outputFormat);
            } else if (detail[0].equals(Parser.AFTER) && detail.length == HAPPEN_COMMAND_COMPONENTS) {
                return getHappenAfterList(ui, list, detail, inputFormat, outputFormat);
            } else if (detail.length == LONG_HAPPEN_COMMAND_COMPONENTS
                    && detail[0].equals(Parser.IN) && detail[2].equals(Parser.DAYS)) {
                return getHappenInList(ui, list, detail[1]);
            } else if (detail[0].equals(Parser.BETWEEN) && detail.length == LONG_HAPPEN_COMMAND_COMPONENTS) {
                return getHappenBetweenList(ui, list, detail, inputFormat, outputFormat);
            } else {
                throw new InvalidCommandException(Parser.UNRECOGNIZED_HAPPEN_COMMAND_EXCEPTION);
            }
        } catch (InvalidCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidCommandException(Parser.INVALID_DATE_FORMAT_EXCEPTION);
        }
    }

    private String getHappenBetweenList(Ui ui, TaskList list, String[] detail, DateTimeFormatter inputFormat,
                                        DateTimeFormatter outputFormat) throws InvalidCommandException {
        LocalDate date1 = LocalDate.parse(detail[1], inputFormat);
        LocalDate date2 = LocalDate.parse(detail[2], inputFormat);
        if (!date1.isBefore(date2)) {
            throw new InvalidCommandException(Parser.HAPPEN_BETWEEN_EMPTY_PERIOD_EXCEPTION);
        }
        return ui.printList(list, t -> t.isHappeningBetween(date1, date2),
                String.format(Ui.HAPPEN_LIST_NOTE_FORMAT, Parser.BETWEEN, date1.format(outputFormat)
                        + " and " + date2.format(outputFormat) + Parser.SPACE_STRING));
    }

    private String getHappenInList(Ui ui, TaskList list, String s) throws InvalidCommandException {
        try {
            int n = Integer.parseInt(s);
            if (n <= 0) {
                throw new InvalidCommandException(Parser.HAPPEN_IN_NEGATIVE_DAYS_EXCEPTION);
            }
            return ui.printList(list, t -> t.willHappenInDays(n), String.format(Ui.HAPPEN_LIST_NOTE_FORMAT,
                    Parser.IN, n + Parser.SPACE_STRING + Parser.DAYS));
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Parser.HAPPEN_IN_NONNUMERIC_EXCEPTION);
        }
    }

    private String getHappenAfterList(Ui ui, TaskList list, String[] detail,
                                      DateTimeFormatter inputFormat, DateTimeFormatter outputFormat) {
        if (detail[1].equals(Parser.TODAY)) {
            return ui.printList(list, Task::isHappeningAfterToday,
                    String.format(Ui.HAPPEN_LIST_NOTE_FORMAT, Parser.AFTER, Parser.TODAY));
        } else {
            LocalDate date = LocalDate.parse(detail[1], inputFormat);
            return ui.printList(list, t -> t.isHappeningAfter(date),
                    String.format(Ui.HAPPEN_LIST_NOTE_FORMAT, Parser.AFTER, date.format(outputFormat)));
        }
    }

    private String getHappenBeforeList(Ui ui, TaskList list, String[] detail,
                                       DateTimeFormatter inputFormat, DateTimeFormatter outputFormat) {
        if (detail[1].equals(Parser.TODAY)) {
            return ui.printList(list, Task::hasHappenedBeforeToday,
                    String.format(Ui.HAPPEN_LIST_NOTE_FORMAT, Parser.BEFORE, Parser.TODAY));
        } else {
            LocalDate date = LocalDate.parse(detail[1], inputFormat);
            return ui.printList(list, t -> t.hasHappenedBefore(date),
                    String.format(Ui.HAPPEN_LIST_NOTE_FORMAT, Parser.BEFORE, date.format(outputFormat)));
        }
    }

    private String getHappenOnList(Ui ui, TaskList list, String[] detail,
                                   DateTimeFormatter inputFormat, DateTimeFormatter outputFormat) {
        if (detail[1].equals(Parser.TODAY)) {
            return ui.printList(list, Task::isHappeningToday,
                    String.format(Ui.HAPPEN_LIST_NOTE_FORMAT, Parser.ON, Parser.TODAY));
        } else {
            LocalDate date = LocalDate.parse(detail[1], inputFormat);
            return ui.printList(list, t -> t.isHappeningOn(date),
                    String.format(Ui.HAPPEN_LIST_NOTE_FORMAT, Parser.ON, date.format(outputFormat)));
        }
    }

    /**
     * Checks whether a command equals this one.
     * @param obj the Object to compare
     * @return true if obj is a HappenCommand and it has the same input as this one
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof HappenCommand) {
            return input.equals(((HappenCommand) obj).input);
        } else {
            return false;
        }
    }
}
