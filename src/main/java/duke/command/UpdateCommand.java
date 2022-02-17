package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.common.DukeException;
import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class UpdateCommand extends Command {
    private final String fullCommand;

    public UpdateCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }


    /**
     * Update description or data value of the task.
     *
     * @param taskList arraylist of task.
     * @param ui       ui class for print.
     * @param storage  storage for read, write to file.
     * @throws DukeException Exception for wrong date or time format or invalid update.
     */
    @SuppressWarnings("checkstyle:SeparatorWrap")
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (!fullCommand.contains(" ")) {
            throw new DukeException("☹ OOPS!!! The changes cannot be empty.");
        }
        if (!(fullCommand.contains("/desc") || fullCommand.contains("/date"))) {
            throw new DukeException("☹ OOPS!!! Change value have to be a description or a date");
        }

        String fullDetails = fullCommand.substring(fullCommand.indexOf(" ")).trim();
        String index = fullDetails.substring(0, fullDetails.indexOf("/")).trim();
        String updateDetails = fullDetails.substring(fullDetails.indexOf("/") + 1).trim();
        String updateType = updateDetails.substring(0, updateDetails.indexOf(" ")).trim();
        String updateValue = updateDetails.substring(updateDetails.indexOf(" ")).trim();

        String resultSb = String.format("%s\n %s\n", ui.showLine(), "Got it. I've updated this task:") + "\t";
        Task toUpdate = taskList.retrieveTask(Integer.parseInt(index) - 1);
        if (updateType.equals("desc")) {
            toUpdate.setDescription(updateValue);
            storage.write(taskList);
        } else {
            String[] inputDateTime = updateValue.split(" ");
            String[] date = inputDateTime[0].split("[/\\\\]|-");
            LocalDate updateLocalDate = convertDateToLocalDate(date);
            if (toUpdate.getClass().equals(Deadline.class)) {
                ((Deadline) toUpdate).setBy(LocalDateTime.of(updateLocalDate,
                        convertTimeToLocalTime(inputDateTime[1])));
                storage.write(taskList);
            } else if (toUpdate.getClass().equals(Event.class)) {
                LocalTime startLocalTime = LocalTime.of(0, 0);
                LocalTime endLocalTime = LocalTime.of(23, 59);
                if (inputDateTime.length > 1) {
                    String[] timeArr = inputDateTime[1].split("-");
                    if (timeArr.length > 1) {
                        endLocalTime = convertTimeToLocalTime(timeArr[1]);
                    }
                    startLocalTime = convertTimeToLocalTime(timeArr[0]);
                }
                ((Event) toUpdate).setAt(LocalDateTime.of(updateLocalDate, startLocalTime));
                ((Event) toUpdate).setEnd(LocalDateTime.of(updateLocalDate, endLocalTime));
                storage.write(taskList);
            } else {
                throw new DukeException("☹ OOPS!!! No date time value to update");
            }
        }
        resultSb = toUpdate + String.format("\nNow you have %o tasks in list" + ".\n",
                taskList.sizeOfList()) + ui.showLine();
        Ui.printString(resultSb);
    }

    private static LocalDate convertDateToLocalDate(String[] date) throws DukeException {
        LocalDate localDate;
        if (date.length == 3) {
            int day = Integer.parseInt(date[0]);
            assert day < 32
                    : "Not Valid Date";
            assert day > 0
                    : "Not Valid Date";
            int month = Integer.parseInt(date[1]);
            assert month < 13
                    : "Not Valid Month";
            assert month > 0
                    : "Not Valid Month";
            int year = Integer.parseInt(date[2]);
            assert year > 0
                    : "Not Valid Year";
            localDate = LocalDate.of(year, month, day);
        } else {
            throw new DukeException("Error with input date!");
        }
        return localDate;
    }

    private static LocalTime convertTimeToLocalTime(String time) throws DukeException {
        int timeLength = time.length();
        LocalTime localTime;
        if (timeLength == 4) {
            int hour = Integer.parseInt(time.substring(0, 2));
            assert hour >= 0
                    : "Not Valid Hour";
            assert hour <= 23
                    : "Not Valid Hour";
            int minute = Integer.parseInt(time.substring(2, 4));
            assert minute >= 0
                    : "Not Valid Minutes";
            assert minute < 60
                    : "Not Valid Minutes";
            localTime = LocalTime.of(hour, minute);
        } else if (timeLength == 3) {
            int hour = Integer.parseInt(String.valueOf(time.charAt(0)));
            assert hour >= 0
                    : "Not Valid Hour";
            assert hour < 10
                    : "Not Valid Hour";
            int minute = Integer.parseInt(time.substring(1, 3));
            assert minute >= 0
                    : "Not Valid Minutes";
            assert minute < 60
                    : "Not Valid Minutes";
            localTime = LocalTime.of(hour, minute);
        } else {
            throw new DukeException("Error with input time");
        }
        return localTime;
    }

    /**
     * Indicator for application to end.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
