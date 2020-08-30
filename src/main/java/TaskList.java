import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> database;

    TaskList() {
        this.database = new ArrayList<>();
    }

    TaskList(ArrayList<Task> database) {
        this.database = database;
    }

    private void addTask(Task task) {
        this.database.add(task);
        System.out.println("Looking good! I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.database.size() + " tasks in your list");
    }

    /**
     * Adds a new Deadline to the database.
     *
     * @throws DukeException if an invalid date time format is passed in
     */
    public void addNewDeadline(Scanner sc) throws DukeException {
        String toParse = sc.nextLine();

        if (!toParse.equals("")) {
            String[] parsed = toParse.split("/by ");

            if (parsed.length == 1) {
                throw new DukeException("Please input the deadline in a valid format e.g. /by 20/02/2020 1600");
            } else {
                String[] dateTime = parsed[1].trim().split(" ");
                LocalDate date = LocalDate.parse(dateTime[0], Parser.DATE_INPUT_FORMAT);
                LocalTime time = LocalTime.parse(dateTime[1].substring(0, 2) + ":" + dateTime[1].substring(2));

                String parsedDate = date.format(Parser.DATE_OUTPUT_FORMAT);
                String parsedTime = time.format(Parser.TIME_OUTPUT_FORMAT);
                Deadline deadline = new Deadline(parsed[0].trim(), parsedDate + " " + parsedTime);
                this.addTask(deadline);
            }
        } else {
            throw new DukeException("Description and deadline cannot be empty");
        }
    }

    /**
     * Adds a new Event to the database.
     *
     * @throws DukeException if an invalid date time format is passed in
     */
    public void addNewEvent(Scanner sc) throws DukeException {
        String toParse = sc.nextLine();

        if (!toParse.equals("")) {
            String[] parsed = toParse.split("/at ");
            if (parsed.length == 1) {
                throw new DukeException("Please input the start and end timing in a valid format e.g. /at 20/02/2020 1600-1800");
            } else {
                String[] dateTime = parsed[1].trim().split(" ");
                LocalDate date = LocalDate.parse(dateTime[0], Parser.DATE_INPUT_FORMAT);
                String[] startEndTime = dateTime[1].split("-");
                LocalTime startTime = LocalTime.parse(startEndTime[0].substring(0, 2) + ":" + startEndTime[0].substring(2));
                LocalTime endTime = LocalTime.parse(startEndTime[1].substring(0, 2) + ":" + startEndTime[1].substring(2));

                String parsedDate = date.format(Parser.DATE_OUTPUT_FORMAT);
                String parsedStartTime = startTime.format(Parser.TIME_OUTPUT_FORMAT);
                String parsedEndTime = endTime.format(Parser.TIME_OUTPUT_FORMAT);
                Event event = new Event(parsed[0].trim(), parsedDate + " " + parsedStartTime + " to " + parsedEndTime);
                this.addTask(event);
            }
        } else {
            throw new DukeException("Description, start time and end time of the event cannot be empty");
        }
    }

    /**
     * Adds a new ToDo to the database.
     *
     * @throws DukeException if description is empty
     */
    public void addNewToDo(Scanner sc) throws DukeException {
        String description = sc.nextLine();

        if (description.equals("")) {
            throw new DukeException("To-do description cannot be empty");
        } else {
            ToDo toDo = new ToDo(description.trim());
            this.addTask(toDo);
        }
    }

    /**
     * Deletes a specific task from the database.
     *
     * @throws DukeException if database is empty or if the index passed in is out of bounds
     * @throws InputMismatchException if index passed in is not a number
     */
    public void deleteTask(Scanner sc) throws DukeException {
        if (this.database.size() == 0) {
            throw new DukeException("There are no tasks in the database to delete");
        } else {
            try {
                int index = sc.nextInt();

                if (index > this.database.size()) {
                    throw new DukeException("Index cannot be greater than size of database");
                }

                Task removedTask = this.database.remove(index - 1);
                System.out.println("Noted! I have removed this task:");
                System.out.println(removedTask);
                System.out.println("Now you have " + this.database.size() + " tasks in your list");
            } catch (InputMismatchException e) {
                System.out.println("Index must be a number");
            }
        }

        sc.nextLine();
    }

    /**
     * Searches for all relevant tasks using the given keyword.
     * Relevant tasks will then be printed out.
     *
     * @throws DukeException if no relevant tasks are found
     */
    public void findTasks(Scanner sc) throws DukeException {
        String keyword = sc.next();
        int tasksFound = 0;

        for (int i = 0; i < this.database.size(); i++) {
            String currentTask = database.get(i).toString();
            if (currentTask.contains(keyword)) {
                tasksFound += 1;
                System.out.println(database.get(i));
            }
        }

        if (tasksFound == 0) {
            throw new DukeException("None of the tasks matches the keyword");
        }
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
     * Prints all the saved tasks in the database.
     *
     * @throws DukeException if the database is empty
     */
    public void listAllTasks() throws DukeException {
        if (this.database.size() == 0) {
            throw new DukeException("There are no tasks in the database");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.database.size(); i++) {
                System.out.println((i + 1) + ". " + this.database.get(i));
            }
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @throws DukeException if the database is empty, or if the index is out of bounds
     * @throws InputMismatchException if index passed in is not a number
     */
    public void markTaskAsDone(Scanner sc) throws DukeException {
        if (this.database.size() != 0) {
            try {
                int index = sc.nextInt();

                if (index > this.database.size()) {
                    throw new DukeException("Index cannot be greater than size of database");
                }

                this.database.get(index - 1).markAsDone();
                System.out.println("Nice! I have marked this task as done!");
            } catch (InputMismatchException e) {
                System.out.println("Index must be a number");
            }
        } else {
            throw new DukeException("There are no tasks in the database to mark as done");
        }

        sc.nextLine();
    }
}
