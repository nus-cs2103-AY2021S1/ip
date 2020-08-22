import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    private static final String divider =
            "\t------------------------------------------------------------------\n";

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        this.taskList = new ArrayList<>();
        for (String task : tasks) {
            taskList.add(Task.textToTask(task));
        }
    }

    protected void addToDo(String input, Storage storage) throws DukeException {
        String information;
        try { // user did not input description of to-do task
            information = input.split("todo")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tThe description of a todo cannot be empty!\n"
                    + divider);
        }
        if (information.isBlank()) {
            throw new DukeException(divider
                    + "\tThe description of a todo cannot be empty!\n"
                    + divider);
        }

        String description = information.substring(1);
        Task toDo = new ToDo(description);
        taskList.add(toDo);
        storage.saveData(taskList);
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + toDo + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n"
                + divider;
        System.out.println(printing);
    }

    protected void addDeadline(String input, Storage storage) throws DukeException {
        String information;
        try { // user did not input description of deadline task
            information = input.split("deadline")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-12-09 08:00\n"
                    + divider);
        }
        if (information.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-12-09 08:00\n"
                    + divider);
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            throw new DukeException(divider
                    + "\tPlease input the appropriate command!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-12-09 08:00\n"
                    + divider);
        }

        String description = information.substring(1, end - 1);
        String by;
        try { // user did not input date of deadline task
            by = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-12-09 08:00\n"
                    + divider);
        }
        if (by.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by 2020-12-09 08:00\n"
                    + divider);
        }

        String formattedBy = by.replace(' ', 'T');
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(formattedBy);
        } catch (DateTimeParseException e) {
            throw new DukeException(divider
                    + "\tPlease input the correct date format!\n"
                    + "\tAn example would be:\n"
                    + "\tdeadline return book /by YYYY-MM-DD HH:mm\n"
                    + divider);
        }
        Task deadline = new Deadline(description, date);
        taskList.add(deadline);
        storage.saveData(taskList);
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + deadline + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n"
                + divider;
        System.out.println(printing);
    }

    protected void addEvent(String input, Storage storage) throws DukeException {
        String information;
        try { // user did not input description of event task
            information = input.split("event")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00\n"
                    + divider);
        }
        if (information.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease input an appropriate description!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00\n"
                    + divider);
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            throw new DukeException(divider
                    + "\tPlease input the appropriate command!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00\n"
                    + divider);
        }

        String description = information.substring(1, end - 1);
        String at;
        try { // user did not input date of event task
            at = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00\n"
                    + divider);
        }
        if (at.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease input the date!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at 2020-12-25 17:00\n"
                    + divider);
        }

        String formattedAt = at.replace(' ', 'T');
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(formattedAt);
        } catch (DateTimeParseException e) {
            throw new DukeException(divider
                    + "\tPlease input the correct date format!\n"
                    + "\tAn example would be:\n"
                    + "\tevent Christmas party /at YYYY-MM-DD HH:mm\n"
                    + divider);
        }
        Task event = new Event(description, date);
        taskList.add(event);
        storage.saveData(taskList);
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + event + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n"
                + divider;
        System.out.println(printing);
    }

    protected void deleteTask(String input, Storage storage) throws DukeException {
        String indexString;
        try {
            indexString = input.substring(7);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to delete!\n"
                    + "\tYou have " + taskList.size() + " tasks on your list now.\n"
                    + divider);
        }
        if (indexString.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to delete!\n"
                    + "\tYou have " + taskList.size() + " tasks on your list now.\n"
                    + divider);
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > taskList.size())) {
            throw new DukeException(divider
                    + "\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!\n"
                    + divider);
        }
        Task taskToBeDeleted = taskList.get(index - 1);
        taskList.remove(index - 1);
        storage.saveData(taskList);
        String deletedTask = divider
                + "\tRoger that! I've removed this task:\n\t\t"
                + taskToBeDeleted
                + "\n\tYou have " + taskList.size()
                + " tasks on your list now.\n" + divider;
        System.out.println(deletedTask);
    }

    protected void markTaskDone(String input, Storage storage) throws DukeException {
        String indexString;
        try {
            indexString = input.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + taskList.size() + " tasks on your list now.\n"
                    + divider);
        }
        if (indexString.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + taskList.size() + " tasks on your list now.\n"
                    + divider);
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > taskList.size())) {
            throw new DukeException(divider
                    + "\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!\n"
                    + divider);
        }
        Task finishedTask = taskList.get(index - 1);
        finishedTask.markAsDone();
        storage.saveData(taskList);
        String doneTask = divider
                + "\t\\(^O^)/ Good job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n" + divider;
        System.out.println(doneTask);
    }

    protected void listTasks() throws DukeException {
        System.out.print(divider);
        if (taskList.size() == 0) {
            throw new DukeException("\tThere are currently no tasks on your list!\n"
                    + "\tStart adding one now!\n"
                    + divider);
        } else {
            System.out.print("\tHere are the tasks on your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                int number = i + 1;
                System.out.print("\t" + number + ". " + taskList.get(i) + "\n");
            }
        }
        System.out.println(divider);
    }
}
