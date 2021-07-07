package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.command.InvalidCommandException;
import duke.command.SnoozeCommand;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.Ui;

/**
 * Represents an event task that consists of a description and a datetime as the happening time of the event.
 */
public class Event extends TimedTask {
    private LocalDateTime atTime;
    private LocalDateTime[] tentativeSlots;
    private String tentativeSlotsStr;

    /**
     * Creates an event task.
     * @param description the description of the task
     * @param atTime the string description of the time the event happens at
     * @throws InvalidCommandException if the input time format is not yyyy-MM-dd HH:mm
     */
    public Event(String description, String atTime) throws InvalidCommandException {
        super(description);
        handleTentativeSlots(atTime);
    }

    /**
     * Creates an event task using the resource file.
     * @param taskInfo the full line of the task
     * @throws InvalidCommandException if the resource file format is invalid
     */
    public Event(String[] taskInfo) throws InvalidCommandException {
        super("");
        assert taskInfo[0].equals("E") : "Wrong read of file";
        try {
            int done = Integer.parseInt(taskInfo[1]);
            description = taskInfo[2];
            if (taskInfo.length == 4) {
                handleTentativeSlots(taskInfo[3]);
                if (done == 1) {
                    throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
                }
            } else if (taskInfo.length <= 6) {
                atTime = LocalDateTime.parse(taskInfo[3], Parser.DATE_TIME_INPUT_FORMAT);
                repeat = Integer.parseInt(taskInfo[4]);
                if (done == 1) {
                    lastDone = LocalDate.parse(taskInfo[5], Parser.DATE_INPUT_FORMAT);
                    this.isDone = true;
                } else if (taskInfo.length == 6) {
                    throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
                }
            } else {
                throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
            }
        } catch (StackOverflowError | NumberFormatException e) {
            throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
        }
    }

    private void handleTentativeSlots(String timeStr) throws InvalidCommandException {
        String[] times = timeStr.split("/");
        tentativeSlots = new LocalDateTime[times.length];
        try {
            for (int i = 0; i < times.length; i++) {
                tentativeSlots[i] = LocalDateTime.parse(times[i], Parser.DATE_TIME_INPUT_FORMAT);
            }
            if (times.length == 1) {
                this.atTime = tentativeSlots[0];
                tentativeSlotsStr = "";
            } else {
                this.atTime = null;
                tentativeSlotsStr = String.join("/", times);
            }
        } catch (Exception e) {
            throw new InvalidCommandException(Parser.INVALID_DATE_TIME_FORMAT_EXCEPTION);
        }
    }

    @Override
    public boolean isHappeningOn(LocalDate date) {
        return atTime != null && isHappeningOn(date, atTime.toLocalDate());
    }

    @Override
    public boolean isHappeningToday() {
        return isHappeningOn(LocalDate.now());
    }

    @Override
    public boolean hasHappenedBefore(LocalDate date) {
        return atTime != null && atTime.toLocalDate().isBefore(date);
    }

    @Override
    public boolean hasHappenedBeforeToday() {
        return hasHappenedBefore(LocalDate.now());
    }

    @Override
    public boolean isHappeningAfter(LocalDate date) {
        return atTime != null && (repeat > 0 || atTime.toLocalDate().isAfter(date));
    }

    @Override
    public boolean isHappeningAfterToday() {
        return isHappeningAfter(LocalDate.now());
    }

    @Override
    public boolean isHappeningBetween(LocalDate date1, LocalDate date2) {
        return atTime != null && isHappeningBetween(date1, date2, atTime.toLocalDate());
    }

    @Override
    public boolean willHappenInDays(int n) {
        return isHappeningBetween(LocalDate.now(), LocalDate.now().plusDays(n));
    }

    /**
     * Fixes a slot from the tentative slots of the event.
     * @param timeToFix the time to fix this event at
     * @return the string output for the result of this execution
     * @throws InvalidCommandException if the input is invalid, e.g. wrong date-time format, extra arguments
     */
    public String fixSlot(String timeToFix) throws InvalidCommandException {
        try {
            LocalDateTime toFix = LocalDateTime.parse(timeToFix, Parser.DATE_TIME_INPUT_FORMAT);
            checkExist(toFix);
            atTime = toFix;
            return String.format(Ui.FIX_TASK_OUTPUT_FORMAT, this);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException(Parser.INVALID_DATE_TIME_FORMAT_EXCEPTION);
        }
    }

    private void checkExist(LocalDateTime toFix) throws InvalidCommandException {
        boolean found = false;
        for (LocalDateTime dt : tentativeSlots) {
            if (dt.isEqual(toFix)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidCommandException(Parser.FIX_TIME_NOT_EXIST_EXCEPTION);
        }
    }

    @Override
    public String snoozeTo(String[] input) throws InvalidCommandException {
        if (input.length != SnoozeCommand.SNOOZE_EVENT_COMMAND_LENGTH) {
            throw new InvalidCommandException(Parser.INVALID_DATE_TIME_FORMAT_EXCEPTION);
        }
        if (atTime == null) {
            throw new InvalidCommandException(Parser.SNOOZE_UNFIXED_EVENT_EXCEPTION);
        } else {
            String dateTimeStr = input[3] + Parser.SPACE_STRING + input[4];
            try {
                LocalDateTime newTime = LocalDateTime.parse(dateTimeStr, Parser.DATE_TIME_INPUT_FORMAT);
                if (newTime.isAfter(atTime)) {
                    LocalDateTime originalTime = atTime;
                    atTime = newTime;
                    return String.format(Ui.SNOOZE_TASK_OUTPUT_FORMAT, this, originalTime, newTime);
                } else {
                    throw new InvalidCommandException(Parser.SNOOZE_TO_EARLIER_TIME_EXCEPTION);
                }
            } catch (DateTimeParseException e) {
                throw new InvalidCommandException(Parser.INVALID_DATE_TIME_FORMAT_EXCEPTION);
            }
        }
    }

    @Override
    public String repeat(int n) throws InvalidCommandException {
        if (atTime == null) {
            throw new InvalidCommandException(Parser.REPEAT_UNFIXED_EVENT_EXCEPTION);
        } else {
            repeat = n;
            return String.format(Ui.REPEAT_TASK_OUTPUT_FORMAT, n, this);
        }
    }

    @Override
    public void markAsDone() throws InvalidCommandException {
        if (atTime == null) {
            throw new InvalidCommandException(Parser.DONE_UNFIXED_EVENT_EXCEPTION);
        } else {
            markAsDone(atTime.toLocalDate());
        }
    }

    @Override
    public String outputToFile() {
        if (atTime != null) {
            return "E" + super.outputToFile() + Storage.SPLITTER
                    + atTime.format(Parser.DATE_TIME_INPUT_FORMAT) + Storage.SPLITTER
                    + repeat + lastDoneMessage() + "\n";
        } else {
            return "E" + super.outputToFile() + Storage.SPLITTER + tentativeSlotsStr + "\n";
        }
    }

    @Override
    public String toString() {
        if (atTime != null) {
            return "[E]" + super.toString() + " (at: "
                    + atTime.format(Parser.DATE_TIME_OUTPUT_FORMAT) + repeatMessage() + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + tentativeSlotsStr + ")";
        }
    }

    /**
     * Checks whether the given object equals this Event task.
     * @param obj the given object to compare
     * @return true if the object is an Event and both the description and atTime equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event) {
            Event o = (Event) obj;
            return description.equals(o.description) && atTime.isEqual(o.atTime);
        } else {
            return false;
        }
    }
}
