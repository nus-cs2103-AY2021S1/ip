package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand {
    private TaskList tasks;
    private String[] words;
    private String taskName = "";
    private String taskDate = "";
    private Ui userInteract = new Ui();

    EventCommand(String[] words, TaskList tasks) {
        this.tasks = tasks;
        this.words = words;
    }

    /**
     * Handles event tasks and return duke responses
     *
     * @return String which is duke response for deadline command
     * @throws DukeException exception occurs when the date format is not yyyy-mm-dd.
     */
    public String handleEvent() throws DukeException {
        String dukeOutput = "";
        parseEvent();
        try {
            LocalDate date = dateConversion(taskDate);
            Event newEvent = new Event(taskName, date);
            this.tasks.add(newEvent);
            dukeOutput = this.userInteract.showAdd(newEvent, this.tasks);
            return dukeOutput;
        } catch (DateTimeParseException e) {
            throw DukeException.invalidDateException();
        }
    }

    /**
     * Returns a LocalDate object of the date input
     *
     * @param date date of the event
     * @return a LocalDate object
     * @throws DukeException exception occurs when the date format is not yyyy-mm-dd.
     */

    public LocalDate dateConversion(String date) throws DukeException {
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        if (!date.matches(datePattern)) {
            throw DukeException.invalidDateException();
        }
        return LocalDate.parse(date);
    }

    /**
     * Iterates through the user input to get useful information of task description and date.
     *
     * @throws DukeException exception occurs when the description is empty or there is no /at.
     */

    public void parseEvent() throws DukeException {
        if (words.length == 1) {
            throw DukeException.emptyDescriptionException();
        }
        boolean beforeAt = true;
        for (int i = 1; i < words.length; i++) {
            if (!words[i].equals("/at") && beforeAt) {
                taskName = taskName + words[i] + " ";
            } else if (words[i].equals("/at")) {
                beforeAt = false;
            } else if (!words[i].equals("/at") && !beforeAt) {
                taskDate = taskDate + words[i];
            }
        }
        if (beforeAt) {
            throw DukeException.missingConnectorException();
        }
        if (taskName == "") {
            throw DukeException.emptyDescriptionException();
        }
        if (taskDate == "") {
            throw DukeException.invalidDateException();
        }
        taskName = taskName.trim();
    }
}
