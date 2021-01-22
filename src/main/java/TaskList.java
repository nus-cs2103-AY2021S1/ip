import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> database;

    TaskList() {
        this.database = new ArrayList<>();
    }

    TaskList(ArrayList<Task> database) {
        this.database = database;
    }

    private String addTask(Task task) throws DukeException {
        this.detectDuplicates(task);
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
     */
    public String addNewDeadline(String deadlineDescription, String date, String time) throws DukeException {
        return this.addTask(new Deadline(deadlineDescription, date + " " + time));
    }

    /**
     * Adds a new Event to the database.
     *
     * @return details of the event added to the database.
     */
    public String addNewEvent(String eventDescription, String date, String startTime, String endTime)
            throws DukeException {
        return this.addTask(new Event(eventDescription, date + " " + startTime + " to " + endTime));
    }

    /**
     * Adds a new ToDo to the database.
     *
     * @return details of the ToDo added to the database.
     */
    public String addNewToDo(String description) throws DukeException {
        return this.addTask(new ToDo(description));
    }

    /**
     * Deletes a specific task from the database.
     *
     * @return a success string if the task was successfully deleted.
     * @throws DukeException if database is empty, if the index passed in is out of bounds.
     */
    public String deleteTask(int index) throws DukeException {
        if (this.database.size() == 0) {
            throw new DukeException("There are no tasks in the database to delete");
        }
        if (index > this.database.size()) {
            throw new DukeException("Index cannot be greater than size of database");
        }
        if (index <= 0) {
            throw new DukeException("Index cannot be smaller than 1");
        }

        Task removedTask = this.database.remove(index - 1);
        return "Noted! I have removed this task:\n"
                + removedTask
                + "\n"
                + "Now you have " + this.database.size() + " tasks in your list";
    }

    /**
     * Checks if a particular task exists in the database.
     * This function is case sensitive. "Buy books" and "buy books" will be
     * treated as 2 different tasks.
     *
     * @param task takes in the task to be checked.
     * @throws DukeException if the task exists in the database.
     */
    private void detectDuplicates(Task task) throws DukeException {
        String description = task.getDescription();

        for (int i = 0; i < this.database.size(); i++) {
            String currentTask = database.get(i).toString();
            if (currentTask.contains(description)) {
                throw new DukeException("This task already exists in the database");
            }
        }
    }

    /**
     * Searches for all relevant tasks using the given keyword.
     *
     * @return a string of all the relevant tasks.
     * @throws DukeException if no relevant tasks are found.
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
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getDatabase() {
        return this.database;
    }

    /**
     * Returns all the saved tasks in the database.
     *
     * @return a string of all the tasks in the database.
     * @throws DukeException if the database is empty.
     */
    public String listAllTasks() throws DukeException {
        if (this.database.size() == 0) {
            throw new DukeException("There are no tasks in the database");
        }

        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.database.size(); i++) {
            output += ((i + 1) + ". " + this.database.get(i));
            output += "\n";
        }

        return output;
    }

    /**
     * Marks a specific task as done.
     *
     * @return a success string if the task was successfully deleted.
     * @throws DukeException if the database is empty, or if the index is out of bounds.
     */
    public String markTaskAsDone(int index) throws DukeException {
        if (this.database.size() == 0) {
            throw new DukeException("There are no tasks in the database to mark as done");
        }

        if (index > this.database.size()) {
            throw new DukeException("Index cannot be greater than size of database");
        }

        if (index <= 0) {
            throw new DukeException("Index cannot be lesser than 1");
        }

        this.database.get(index - 1).markAsDone();
        return "Nice! I have marked this task as done!";
    }
}
