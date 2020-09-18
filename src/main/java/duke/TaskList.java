package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class to handle task list functions
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * TaskList constructor to initialize a TaskList object
     * @param tasks list of tasks from the load file
     */
    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * addTodo method which takes in the user input and adds a ToDo to the list
     * @param specifications user specifications of what he wants to add
     * @throws DukeException when there is an error with the user input
     */
    public Task addTodo(String specifications) throws DukeException {
        Task newTask = new ToDo(specifications);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * addDeadline method which takes in the user input and adds a Deadlines to the list
     * @param specifications user specifications of what he wants to add
     * @throws DukeException when there is an error with the user input
     */
    public Task addDeadline(String specifications) throws DukeException {
        String[] specificationsArray = specifications.split("/by ", 2);
        if (specificationsArray.length <= 1) {
            throw new DukeException("You need to specify a date and time!");
        }
        Task newTask = new Deadlines(specificationsArray[0], specificationsArray[1]);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * addEvent method which takes in the user input and adds a Events to the list
     * @param specifications user specifications of what he wants to add
     * @throws DukeException when there is an error with the user input
     */
    public Task addEvent(String specifications) throws DukeException {
        if (!specifications.contains("/at")) {
            throw new DukeException("Please use /at to specify a date and time!");
        }
        String[] specificationsArray = specifications.split("/at ", 2);
        if (specificationsArray.length < 2) {
            throw new DukeException("Please specify a date and time range! \nEg. 25/12/2020 10pm - 11pm");
        }
        String[] dateTimeArray = specificationsArray[1].split(" ", 2);
        if (dateTimeArray.length < 2) {
            throw new DukeException("Please specify a date and time range! \nEg. 25/12/2020 10pm - 11pm");
        }
        String[] timeArray = dateTimeArray[1].split("-", 2);
        if (timeArray.length < 2) {
            throw new DukeException("Please use - to specify the time range! \nEg. 10pm - 11pm");
        }
        String date = dateTimeArray[0];
        String startTime = timeArray[0];
        String endTime = timeArray[1];
        String startDateTime = date + " " + startTime;
        String endDateTime = date + " " + endTime;
        if (dateTimeArray.length <= 1) {
            throw new DukeException("You need to specify a time!");
        }
        Task newTask = new Events(specificationsArray[0], startDateTime, endDateTime);
        tasks.add(newTask);
        return newTask;
    }


    /**
     * delete method which takes in the user input and deletes the appropriate task from the list
     * @param num user input where the user specifies what he wants to delete
     * @return returns the task deleted
     * @throws DukeException when there is an error with the user input
     */
    public Task delete(String num) throws DukeException {
        try {
            if (num.equals("all")) {
                deleteAll();
                return null;
            } else {
                int intNum = Integer.parseInt(num);
                Task temp = tasks.get(intNum - 1);
                tasks.remove(intNum - 1);
                return temp;
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please key in a number");
        } catch (IndexOutOfBoundsException r) {
            throw new DukeException("The task does not exist");
        }
    }

    /**
     * done method which sets a task as done
     * @param num the integer of the task which the user wants to set as done
     * @return returns the completed task
     */
    public Task done(int num) {
        return tasks.set(num - 1, tasks.get(num - 1).completeTask());
    }

    /**
     * getList method which returns the list of tasks
     * @return returns the list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * countList method which returns the number of tasks in the list
     * @return returns the number of tasks in the list
     */
    public int countList() {
        return tasks.size();
    }

    /**
     * deleteAll method which deletes all tasks in the list
     */
    public void deleteAll() {
        tasks = new ArrayList<>();
    }

    /**
     * findWord method that goes through all the tasks in the list and returns a new list of tasks that contain the
     * word specified by the user
     * @param word word user wants to find
     * @return returns a new list of tasks that contain specifed word
     * @throws DukeException when input is no tasks are found
     */
    public List<Task> findWord(String word) throws DukeException {
        assert !word.isBlank();
        List<Task> findArray = new ArrayList<>();
        int count = 0;
        outer:
        for (Task x: tasks) {
            String[] nameArray = x.getName().split(" ");
            inner:
            for (String y: nameArray) {
                if (word.equals(y)) {
                    findArray.add(x);
                    count += 1;
                    continue outer;
                }
            }
            findArray.add(null);
        }
        if (count == 0) {
            throw new DukeException("Sorry I can't find any tasks containing that word!");
        } else {
            return findArray;
        }
    }
    /**
     * snoozeTask method which takes a String containing an index and a number of hours
     * @return Task with new time
     * @throws DukeException when user input is unable to be processed
     */
    public Task snoozeTask(String specifications) throws DukeException {
        String[] indexAndHour = specifications.split(" ", 2);
        if (indexAndHour.length < 2) {
            throw new DukeException("Please specify the index of task and number of hours to snooze!");
        }
        try {
            int intNum = Integer.parseInt(indexAndHour[0]);
            int intHours = Integer.parseInt(indexAndHour[1]);
            Task temp = tasks.get(intNum - 1);
            temp.snoozeTask(intHours);
            tasks.set(intNum - 1, temp);
            return temp;
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the index of task and number of hours to snooze!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
    }

    /**
     * rescheduleTask method which takes a String containing an index and a date and time
     * @return Task with new date and time
     * @throws DukeException when user input is unable to be processed
     */
    public Task rescheduleTask(String index, String dateTime) throws DukeException {
        try {
            int intNum = Integer.parseInt(index);
            Task temp = tasks.get(intNum - 1);
            temp = temp.rescheduleTask(dateTime);
            tasks.set(intNum - 1, temp);
            return temp;
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the index of task and number of hours to snooze!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
    }

}
