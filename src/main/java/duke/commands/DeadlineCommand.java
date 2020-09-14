package duke.commands;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import duke.tasks.Deadline;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles when input is deadline
 */
public class DeadlineCommand extends AddCommand {
    /**
     * assigns string to a value of string and initialize Deadline Command
     *
     * @param input assigns string to this this.string
     */
    public DeadlineCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * to add deadline into a task list in TaskList
     *
     * @param tasks to change the taskList if necessary
     * @param ui to store the DukeException that may be thrown if there is an error in user input
     * @param storage to change the file in the if necessary
     * @return String returns the string of the output that informs the action is successful
     * @throws DukeException whenever there is an error, where the time adn or date is absent or in wrong format, no
     * description
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isNumberOrDescriptionAbsent()) {
            ui.setDukeException(new DeadlineException(true, false, false));
            throw new DeadlineException(true, false, false); //Since description is absent
        }

        try {
            String[] dataSplit = splitData(); //Split into description name and time and/or date
            Deadline d = deadlineTask(dataSplit[0], dataSplit[1]); //gives the Deadline
            return updateTaskList(storage, d, tasks); //updates the tasks and file in storage
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }

    /**
     * splits the data into Deadline description and the Deadline date and/ or time. If the date and/or time is absent
     * then DeadlineException is thrown.
     *
     * @return the String array where the first String is the name of the Deadline and the second is the date
     * and/or time of deadline
     * @throws DeadlineException thrown when the time and/or date is absent.
     */
    private String[] splitData() throws DeadlineException {
        String s = "";
        int index = -1;
        boolean time = false;
        for (int i = lengthOfKeyword; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '/') {
                index = i;
                time = true; //since date appears after
                break;
            }
            s = s + commandDescription.charAt(i);
        }
        if (!time) {
            throw new DeadlineException(false, false, true);
        }
        assert !s.substring(1, s.length() - 1).contains("/"); // description should not contain /
        assert !commandDescription.substring(index + 4).contains("/by"); ////date and/or time should not contain /at
        String[] dataSplit = new String[]{s.substring(1, s.length() - 1), commandDescription.substring(index + 4)};
        return dataSplit;
    }

    /**
     * This method creates a deadline task by checking whether the date and/or time given is in the correct
     * format. If it is then Deadline task is returned else, DeadlineException is returned.
     *
     * @param name description of Deadline task
     * @param dateTime gives the dateTime, to check whether they are in the correct format
     * @return deadline if the dateTime is in the correct format
     * @throws DeadlineException if the dateTime is in the incorrect format
     */
    private static Deadline deadlineTask(String name, String dateTime) throws DeadlineException {
        Deadline e;
        try {
            LocalDate parsedDate = stringToLocalDate(dateTime); //converts string to date
            e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        } catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = stringToLocalDateTime(dateTime); //converts string to date and time
                e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = stringToLocalTime(dateTime); //converts string to date
                    e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                } catch (DateTimeException f) {
                    throw new DeadlineException(false, true, false);
                }
            }
        }
        return e;
    }

}
