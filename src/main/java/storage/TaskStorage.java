package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.TaskList;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * A TaskStorage object deals with loading tasks from the file and saving tasks in the file.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-09-08
 */
public class TaskStorage extends Storage {

    public TaskStorage(String filePath) {
        super(filePath);
    }

    /**
     * Store all tasks in a file.
     *
     * @param tasks List of tasks as a TaskList object.
     * @throws IOException If directory or file does not exist.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        String output;
        if (tasks.getNumberOfTasks() == 0) {
            // user has not added any task
            output = "Nothing has been added to the list yet!";
        } else {
            output = tasks.displayTasks();
        }
        File file = new File(this.filePath);
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            // directory does not exist
            directory.mkdir();
        }
        if (!file.exists()) {
            // file does not exist
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(output);
        fw.close();
    }

    /**
     * Converts date to a LocalDateTime object.
     *
     * @param taskDate Date of a task obtained from a .txt file.
     * @return LocalDateTime object representing the date related to task.Task.
     * @throws ParseException If an error has been reached while parsing.
     * @throws DukeException If user does not input both date and time.
     */
    public LocalDateTime getDate(String taskDate) throws DukeException, ParseException {
        String[] splitDate = taskDate.split(" ");
        if (splitDate.length == 1) {
            throw new DukeException("Please enter both date and time!");
        }

        int date = Integer.parseInt(splitDate[0]);

        // convert month in words to month in int
        String monthInWords = splitDate[1];
        int month = getMonthInNumber(monthInWords);
        int year = Integer.parseInt(splitDate[2]);

        // get hour and minute from time
        String time = splitDate[3];
        int hour = getHourInContinentalTime(time, splitDate[4]);
        int min = Integer.parseInt(time.split(":")[1].substring(0, 2));

        return LocalDateTime.of(year, month, date, hour, min);
    }

    /**
     * Converts month in String to integer representation.
     *
     * @param monthInWords String representation of the month.
     * @return Integer representation of the month.
     * @throws ParseException If an error has been reached while parsing.
     */
    public int getMonthInNumber(String monthInWords) throws ParseException {
        assert monthInWords != null : "Month cannot be null";
        Date checkMonth = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthInWords);
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkMonth);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * Converts time from 12-hour representation to 24-hour representation i.e. continental time.
     *
     * @param time String representation of time.
     * @param period String representation of time period of day i.e. AM or PM.
     * @return Integer representation of the time in 24-hour format.
     */
    public int getHourInContinentalTime(String time, String period) {
        assert time != null : "Time cannot be null";
        assert period != null : "Time period cannot be null";
        int hour = Integer.parseInt(time.split(":")[0]);
        if (hour == 12 && period.equals("AM")) {
            hour = 0;
        }
        if (period.equals("PM") && hour != 12) {
            hour += 12;
        }
        return hour;
    }

    /**
     * Gets all past tasks stored locally.
     *
     * @return List of previous tasks stored in an ArrayList.
     * @throws DukeException If file does not exists or from getPastTasks method.
     * @throws IOException By FileReader object.
     * @throws ParseException From getPastTasks method.
     */
    public ArrayList<Task> load() throws DukeException, IOException, ParseException {
        File file = new File(this.filePath);
        if (!file.exists() || file.length() == 0) {
            throw new DukeException("There are no saved tasks.");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        return getPastTasks(br);
    }

    /**
     * Obtain a list of tasks from .txt file.
     *
     * @param br BufferedReader object used to read the file.
     * @return List of tasks stored in an ArrayList.
     * @throws IOException If an I/O exception of some sort has occurred.
     * @throws ParseException If an error has been reached while parsing.
     * @throws DukeException If the task type is not recognized.
     */
    public ArrayList<Task> getPastTasks(BufferedReader br) throws IOException, ParseException, DukeException {
        ArrayList<Task> pastTasks = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            Pattern p = Pattern.compile("\\[(.*?)\\]");
            Matcher m = p.matcher(line);
            m.find();
            String taskType = m.group(1);
            m.find();
            String completionState = m.group(1);
            int pos = m.end();

            // check for completion
            boolean isCompleted;
            isCompleted = completionState.equals("done");

            Task currTask;
            if (taskType.equals("T")) { // Todo
                String taskName = line.substring(pos + 1);
                currTask = new Todo(taskName, isCompleted);
            } else {
                String taskNameAndDate = line.substring(pos + 1);
                String taskName1 = taskNameAndDate.split("\\(")[0];
                String taskName = taskName1.substring(0, taskName1.length() - 1);

                String taskDate = taskNameAndDate.split("\\)")[0].split("\\(")[1].substring(4);
                LocalDateTime taskTime = getDate(taskDate);

                if (taskType.equals("D")) { // Deadline
                    currTask = new Deadline(taskName, isCompleted, taskTime);
                } else if (taskType.equals("E")) { // Event
                    currTask = new Event(taskName, isCompleted, taskTime);
                } else {
                    throw new DukeException("Task type is not recognised.");
                }
            }
            pastTasks.add(currTask);
            line = br.readLine();
        }
        return pastTasks;
    }
}
