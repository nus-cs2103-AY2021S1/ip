package duke;

import duke.exception.CalendarException;
import duke.exception.DeleteException;
import duke.exception.DoneException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void listItems() {
        if (this.tasks.size() == 0) {
            System.out.println("You don't have any task in your list.");
        } else {
            StringBuilder todoList = new StringBuilder("Here are the task(s) in your list: \n");
            int num = 1;
            for (Task item : this.tasks) {
                todoList.append(num + ". " + item.toString() + "\n");
                num++;
            }
            System.out.println(todoList);
        }
    }

    public void deleteTask(int index) throws DeleteException {
        if(index < 0 || index > tasks.size()) {
            throw new DeleteException("Please enter a valid task number.");
        } else {
            Task task = this.tasks.get(index);
            this.tasks.remove(task);
            storage.updateData(this.tasks);
            System.out.println("Noted. I've removed this task for you: \n"
                    + task.toString() + "\n"
                    + "Now you have " + this.tasks.size() + " task(s) in the list.");
        }

    }

    public void saveToList(Task task) {
        this.tasks.add(task);
        System.out.println("Okay~ I've added this task: \n" + task.toString());
        System.out.println("Now you have " + this.tasks.size() + " task(s) in the list.");
    }

    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum-1);
    }

    public void markTaskAsDone(int taskNum) throws DoneException {
            if (taskNum <= 0 || taskNum > tasks.size()) {
                throw new DoneException("Please enter a valid task number.");
            } else {
                int index = taskNum - 1;
                Task oldTask = this.tasks.get(index);
                Task newTask = oldTask.markAsDone();
                this.tasks.remove(oldTask);
                this.tasks.add(index, newTask);
                storage.updateData(this.tasks);
                System.out.println("YAYY! I've marked this task as done : \n"
                        + newTask.toString());
            }
    }

    public void showDate(String date) throws CalendarException {
        try {
            LocalDate localDate = LocalDate.parse(date);
            Boolean hasSomething = false;
            System.out.println("Task(s) on : " + localDate.getDayOfWeek() + ", "
                    + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

            for (Task task : this.tasks) {
                String type = task.getType();
                if (type.equals("E")) {
                    Event event = (Event) task;
                    LocalDate eventDate = event.getDate();
                    if (eventDate.equals(localDate)) {
                        hasSomething = true;
                        System.out.println(task);
                    }

                } else if (type.equals("D")) {
                    Deadline deadline = (Deadline) task;
                    LocalDate deadlineDate = deadline.getDeadline();
                    if (deadlineDate.equals(localDate)) {
                        hasSomething = true;
                        System.out.println(task);
                    }

                } else {
                    continue;
                }
            }

            if (!hasSomething) {
                System.out.println("You have nothing to do on this day!");
            }
        } catch (DateTimeParseException e) {
            throw new CalendarException("Invalid input date format. Please try again.");
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

}
