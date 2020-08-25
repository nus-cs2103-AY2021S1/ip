import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void displayList() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, tasks.get(i));
            sb.append(temp);
        }
        Ui.reply(sb.toString());
    }
    public void addTask(String type, String details) {
        StringBuilder sb = new StringBuilder("Aye Aye Captain! I've added this task:\n  ");
        try {
            switch (type) {
            case "todo":
                Todo todo = new Todo(details);
                tasks.add(todo);
                sb.append(todo);
                break;
            case "deadline":
                try {
                    String[] taskDetails = details.trim().split("/by");
                    String description = taskDetails[0];
                    LocalDate dueDate = Sparrow.stringToDate(taskDetails[1].trim());

                    Deadline deadline = new Deadline(description, dueDate);
                    tasks.add(deadline);
                    sb.append(deadline);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidDeadlineByException("/by not passed", e);
                }
            case "event":
                try {
                    String[] taskDetails = details.trim().split("/at");
                    String description = taskDetails[0];
                    LocalDate date = Sparrow.stringToDate(taskDetails[1].trim());

                    Event event = new Event(description, date);
                    tasks.add(event);
                    sb.append(event);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidEventAtException("/at not passed", e);
                }

            }
            String summary = String.format("\nNow you have %d task(s) in the list.", tasks.size());
            sb.append(summary);
            Ui.reply(sb.toString());
        } catch (InvalidDeadlineByException e) {
            System.out.println(Sparrow.standardExceptionMessage() + "️ Please pass a /by to the deadline");
        } catch (InvalidEventAtException e) {
            System.out.println(Sparrow.standardExceptionMessage() + "️ Please pass a /at to the event");
        } catch (DateTimeParseException e) {
            System.out.println(Sparrow.standardExceptionMessage() + "Please enter a date in this format: yyyy-mm-dd");
        }
    }

    public ArrayList<Task> filterList(String dateFilter) throws DateTimeParseException {
        String[] dateArr = dateFilter.trim().split("\\s+", 2);
        LocalDate dateToCompare = Sparrow.stringToDate(dateArr[1]);
        ArrayList<Task> filteredList = new ArrayList<>();

        switch (dateArr[0]) {
        case "on":
            for (Task task : tasks) {
                LocalDate taskDate;

                // Get date from Deadline/Event
                if (task instanceof Deadline) {
                    taskDate = ((Deadline) task).getDueDate();
                } else if (task instanceof Event) {
                    taskDate = ((Event) task).getDate();
                } else {
                    continue;
                }

                // Check if task's date is on date specified
                if (taskDate.isEqual(dateToCompare)) {
                    filteredList.add(task);
                }
            }
            break;
        case "before":
            for (Task task : tasks) {
                LocalDate taskDate;

                // Get date from Deadline/Event
                if (task instanceof Deadline) {
                    taskDate = ((Deadline) task).getDueDate();
                } else if (task instanceof Event) {
                    taskDate = ((Event) task).getDate();
                } else {
                    continue;
                }

                // Check if task's date is before date specified
                if (taskDate.isBefore(dateToCompare)) {
                    filteredList.add(task);
                }
            }
            break;
        }
        return filteredList;
    }
    public void markAsDone(String taskNum) {
        try {
            int taskNumber = Integer.parseInt(taskNum);
            if (taskNumber <= tasks.size() && taskNumber > 0) {
                tasks.get(taskNumber - 1).markAsDone();
                Ui.reply("Jolly riddance! I've marked this task as done:\n" + tasks.get(taskNumber - 1));
            } else {
                System.out.println("Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }
    public void deleteTask(String taskNum) {
        try {
            int taskNumber = Integer.parseInt(taskNum);
            if (taskNumber <= tasks.size() && taskNumber > 0) {
                Task removedTask = tasks.remove(taskNumber - 1);
                Ui.reply("Jolly riddance! I've deleted this task:\n" + "  " + removedTask.toString() + String.format("\nNow you have %d task(s) in the list.", tasks.size()));
            } else {
                System.out.println("Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number.");
        }
    }
}
