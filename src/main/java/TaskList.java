import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> database;

    TaskList() {
        this.database = new ArrayList<>();
    }

    TaskList(ArrayList<Task> database) {
        this.database = database;
    }

    private String addTask(Task task) {
        this.database.add(task);
        String output = "Looking good! I have added this task:\n"
                + task.toString()
                + "\n"
                + "Now you have " + this.database.size() + " tasks in your list";

        return output;
    }

    /**
     * Adds a new Deadline to the database.
     *
     * @return the details of the deadline added to the database.
     * @throws DukeException if an invalid date time format is passed in
     */
    public String addNewDeadline(String[] taskInformation) throws DukeException {
        if (taskInformation.length == 0) {
            throw new DukeException("Description of deadline cannot be empty");
        } else if (taskInformation.length >= 1 && taskInformation.length < 4) {
            throw new DukeException("Please input the deadline in a valid format e.g. by 20/02/2020 1600");
        }

        LocalDate date = LocalDate.parse(taskInformation[2], Parser.DATE_INPUT_FORMAT);
        LocalTime time = LocalTime.parse(taskInformation[3].substring(0, 2) + ":" + taskInformation[3].substring(2));

        String parsedDate = date.format(Parser.DATE_OUTPUT_FORMAT);
        String parsedTime = time.format(Parser.TIME_OUTPUT_FORMAT);
        Deadline deadline = new Deadline(taskInformation[0], parsedDate + " " + parsedTime);
        return this.addTask(deadline);
    }

    /**
     * Adds a new Event to the database.
     *
     * @return details of the event added to the database.
     * @throws DukeException if an invalid date time format is passed in
     */
    public String addNewEvent(String[] taskInformation) throws DukeException {
        if (taskInformation.length == 0) {
            throw new DukeException("Description of the event cannot be empty");
        } else if (taskInformation.length >= 1 && taskInformation.length < 4) {
            throw new DukeException("Please input the start and end timing in a valid format"
                    + "e.g. at 20/02/2020 1600-1800");
        }

        LocalDate date = LocalDate.parse(taskInformation[2], Parser.DATE_INPUT_FORMAT);
        String[] startEndTime = taskInformation[3].split("-");
        LocalTime startTime = LocalTime.parse(startEndTime[0].substring(0, 2) + ":"
                + startEndTime[0].substring(2));
        LocalTime endTime = LocalTime.parse(startEndTime[1].substring(0, 2) + ":"
                + startEndTime[1].substring(2));

        String parsedDate = date.format(Parser.DATE_OUTPUT_FORMAT);
        String parsedStartTime = startTime.format(Parser.TIME_OUTPUT_FORMAT);
        String parsedEndTime = endTime.format(Parser.TIME_OUTPUT_FORMAT);
        Event event = new Event(taskInformation[0], parsedDate + " " + parsedStartTime + " to " + parsedEndTime);
        return this.addTask(event);
    }

    /**
     * Adds a new ToDo to the database.
     *
     * @return details of the ToDo added to the database.
     * @throws DukeException if description is empty.
     */
    public String addNewToDo(String description) throws DukeException {
        String output;

        if (description.equals("")) {
            throw new DukeException("To-do description cannot be empty");
        } else {
            ToDo toDo = new ToDo(description.trim());
            output = this.addTask(toDo);
        }

        return output;
    }

    /**
     * Deletes a specific task from the database.
     *
     * @return a success string if the task was successfully deleted.
     * @throws DukeException if database is empty, if the index passed in is out of bounds
     * Or if index passed in is not a number.
     */
    public String deleteTask(String indexStr) throws DukeException {
        String output;

        if (this.database.size() == 0) {
            throw new DukeException("There are no tasks in the database to delete");
        } else {
            try {
                int index = Integer.parseInt(indexStr);
                if (index > this.database.size()) {
                    throw new DukeException("Index cannot be greater than size of database");
                }

                if (index <= 0) {
                    throw new DukeException("Index cannot be smaller than 1");
                }

                Task removedTask = this.database.remove(index - 1);
                output = "Noted! I have removed this task:\n"
                        + removedTask
                        + "\n"
                        + "Now you have " + this.database.size() + " tasks in your list";
            } catch (NumberFormatException e) {
                throw new DukeException("Index must be a number");
            }
        }

        return output;
    }

    /**
     * Searches for all relevant tasks using the given keyword.
     *
     * @return a string of all the relevant tasks.
     * @throws DukeException if no relevant tasks are found
     */
    public String findTasks(String keyword) throws DukeException {
        String output = "";
        int tasksFound = 0;

        for (int i = 0; i < this.database.size(); i++) {
            String currentTask = database.get(i).toString();
            if (currentTask.contains(keyword)) {
                tasksFound += 1;
                output += database.get(i).toString();
                output += "\n";
            }
        }

        if (tasksFound == 0) {
            throw new DukeException("None of the tasks matches the keyword");
        }

        return output;
    }

    /**
     * Returns the database.
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getDatabase() {
        return this.database;
    }

    /**
     * Returns all the saved tasks in the database.
     *
     * @return a string of all the tasks in the database.
     * @throws DukeException if the database is empty
     */
    public String listAllTasks() throws DukeException {
        String output;
        if (this.database.size() == 0) {
            throw new DukeException("There are no tasks in the database");
        } else {
            output = "Here are the tasks in your list:\n";
            for (int i = 0; i < this.database.size(); i++) {
                output += ((i + 1) + ". " + this.database.get(i));
                output += "\n";
            }

            return output;
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @return a success string if the task was successfully deleted.
     * @throws DukeException if the database is empty, or if the index is out of bounds
     * Or if index passed in is not a number.
     */
    public String markTaskAsDone(String indexStr) throws DukeException {
        String output;

        if (this.database.size() != 0) {
            try {
                int index = Integer.parseInt(indexStr);
                if (index > this.database.size()) {
                    throw new DukeException("Index cannot be greater than size of database");
                }

                if (index <= 0) {
                    throw new DukeException("Index cannot be lesser than 1");
                }

                this.database.get(index - 1).markAsDone();
                output = "Nice! I have marked this task as done!";
            } catch (NumberFormatException e) {
                throw new DukeException("Index must be a number");
            }
        } else {
            throw new DukeException("There are no tasks in the database to mark as done");
        }

        return output;
    }
}
