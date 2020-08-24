import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedData) {
        this.taskList = loadedData;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    private static void printTaskAdded(Task task, ArrayList<Task> list) {
        System.out.println("Added task: " + task.display()
                + "\nYou have " + list.size() + " task(s) left in your list.");
    }

    private static String formatDate(String dateString) {
        if (dateString.contains("/")) {
            dateString = dateString.replaceAll("\\/", "-");
        }
        String[] dateStringArr = dateString.split("-");
        dateString = "";
        for (int i = 0; i < dateStringArr.length; i++) {
            if (dateStringArr[i].length() < 2) {
                dateStringArr[i] = "0" + dateStringArr[i];
            }
            if (i > 0) {
                dateString = dateString + "-" + dateStringArr[i];
            } else {
                dateString = dateStringArr[i];
            }
        }
        return dateString;
    }

    public void handleDoneInput(String input) throws DukeException {
        int index;
        if (!input.substring(4).trim().isEmpty()
                && input.substring(4).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
            index = Integer.parseInt(input.substring(4).trim()); //convert string to integer
            if (index >= 1) { //if input index is valid
                Task newTask = this.taskList.get(index - 1).markAsDone();
                this.taskList.set(index - 1, newTask);
                System.out.println("Marked task as done:\n" + newTask.display());
            } else {
                throw new DukeException("Please enter a valid task number to mark as done (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to mark as done (substring doesn't match regex)");
        }
    }

    public void handleDeleteInput(String input) throws DukeException {
        int index;
        if (!input.substring(6).trim().isEmpty()
                && input.substring(6).trim().matches("[0-9]+")) { //to make sure the input after "done" is a number
            index = Integer.parseInt(input.substring(6).trim()); //convert string to integer
            if (index >= 1) { //if input index is valid
                Task removed = this.taskList.get(index - 1);
                this.taskList.remove(index - 1);
                System.out.println("Removed task:\n" + removed.display()
                        + "\nYou have " + this.taskList.size() + " task(s) left in your list.");
            } else {
                throw new DukeException("Please enter a valid task number to delete (index is not valid)");
            }
        } else {
            throw new DukeException("Please enter a valid task number to delete (substring doesn't match regex)");
        }
    }

    public void handleTodoInput(String input) throws DukeException {
        if (!input.substring(4).trim().isEmpty()) { //to make sure to do task is not empty
            String description = input.substring(4);
            Task newTask = new Todo(description.trim());
            this.taskList.add(newTask);
            printTaskAdded(newTask, taskList);
        } else {
            throw new DukeException("Please enter a valid todo");
        }
    }

    public void handleDeadlineInput(String input) throws DukeException {
        if (!input.substring(8).trim().isEmpty() //to make sure deadline is not empty
                && input.substring(8).trim().contains("/by") //to make sure deadline contains /by
                && !input.substring(8).trim().startsWith("/by") //to make sure deadline contains a task description
                && !input.substring(8).trim().endsWith("/by")) { //to make sure deadline contains a deadline
            String descriptionAndTime = input.substring(8);
            String description = descriptionAndTime.trim().split("/by ")[0];
            String by = descriptionAndTime.trim().split("/by ")[1].trim();
            try {
                if (by.contains(" ")) { //user gave a time input
                    String dateString = by.split(" ")[0].trim();
                    String timeString = by.split(" ")[1].trim();
                    dateString = formatDate(dateString);
                    LocalDate d1 = LocalDate.parse(dateString);
                    if (timeString.length() == 4) {
                        try {
                            int time = Integer.parseInt(timeString); //convert string to integer wrap in try catch?
                            if (time >= 0000 && time <= 2359) {
                                Task newTask = new Deadline(description.trim(), d1, timeString);
                                this.taskList.add(newTask);
                                printTaskAdded(newTask, this.taskList);
                            } else {
                                throw new DukeException("Please enter a valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the time in the right format (eg. 1800)");
                        }
                    } else {
                        Task newTask = new Deadline(description.trim(), d1);
                        this.taskList.add(newTask);
                        printTaskAdded(newTask, this.taskList);
                    }
                } else { //user didn't give a time input
                    by = formatDate(by);
                    LocalDate d1 = LocalDate.parse(by);
                    Task newTask = new Deadline(description.trim(), d1);
                    this.taskList.add(newTask);
                    printTaskAdded(newTask, this.taskList);
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date and time in the format yyyy-mm-dd hhmm (eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid deadline");
        }
    }

    public void handleEventInput(String input) throws DukeException {
        if (!input.substring(5).trim().isEmpty() //to make sure event is not empty
                && input.substring(5).trim().contains("/at") //to make sure event contains at
                && !input.substring(5).trim().startsWith("/at") //to make sure event description is not empty
                && !input.substring(5).trim().endsWith("/at")) { //to make sure event contains a time/date
            String descriptionAndTime = input.substring(5);
            String description = descriptionAndTime.split("/at ")[0];
            String at = descriptionAndTime.split("/at ")[1].trim();
            try {
                if (at.contains(" ")) { //user gave a time input
                    String dateString = at.split(" ")[0].trim();
                    String timeString = at.split(" ")[1].trim();
                    dateString = formatDate(dateString);
                    LocalDate d2 = LocalDate.parse(dateString);
                    if (timeString.length() == 4) {
                        try {
                            int time = Integer.parseInt(timeString); //convert string to integer
                            if (time >= 0000 && time <= 2359) {
                                Task newTask = new Event(description.trim(), d2, timeString);
                                this.taskList.add(newTask);
                                printTaskAdded(newTask, this.taskList);
                            } else {
                                throw new DukeException("Please enter a valid time between 0000 and 2359");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("Please input the time in the right format (eg. 1800)");
                        }
                    } else {
                        Task newTask = new Event(description.trim(), d2);
                        this.taskList.add(newTask);
                        printTaskAdded(newTask, this.taskList);
                    }
                } else { //user didn't give a time input
                    at = formatDate(at);
                    LocalDate d2 = LocalDate.parse(at);
                    Task newTask = new Event(description.trim(), d2);
                    this.taskList.add(newTask);
                    printTaskAdded(newTask, this.taskList);
                }
            } catch (DateTimeException dte) {
                throw new DukeException("Please enter your date and time in the format yyyy-mm-dd hhmm (eg. 2020-08-23 1800)");
            }
        } else {
            throw new DukeException("Please enter a valid event");
        }
    }
}
