import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DukeCommandsHandler {
    private static final String divider =
            "\t----------------------------------------------------\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    protected static void greetings() {
        String logo =
                "\t __\n"
                + "\t/ _|   ___    ___  _   _  ___\n"
                + "\t| |_  / _ \\  / __|| | | |/ __|\n"
                + "\t|  _|| (_) || (__ | |_| |\\__ \\ _\n"
                + "\t|_|   \\___/  \\___| \\__,_||___/(_)\n";
        System.out.println("Welcome to\n" + logo);
        String greetings = divider
                + "\t\"\\(*^O^*) I am Pocus, your personal assistant!"
                + "\n\tBefore we start, may I know your name?\n"
                + divider;
        System.out.print(greetings);
    }

    protected static void addressUser(String name) {
        String address = divider
                + "\tHi there, " + name + "!"
                + "\n\tHow can I help you today?\n"
                + divider;
        System.out.print(address);
    }

    protected static void addToDo(String input) throws ArrayIndexOutOfBoundsException {
        String information;
        try { // user did not input description of to-do task
            information = input.split("todo")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeException.toDoInvalidDescription();
            return;
        }
        if (information.isBlank()) {
            DukeException.toDoInvalidDescription();
            return;
        }

        String description = information.substring(1);
        Task toDo = new ToDo(description);
        tasks.add(toDo);
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + toDo + "\n\t" + "You have "
                + tasks.size() + " tasks on the list now.\n"
                + divider;
        System.out.print(printing);
    }

    protected static void addDeadline(String input)
            throws ArrayIndexOutOfBoundsException, StringIndexOutOfBoundsException {
        String information;
        try { // user did not input description of deadline task
            information = input.split("deadline")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeException.deadlineInvalidDescription();
            return;
        }
        if (information.isBlank()) {
            DukeException.deadlineInvalidDescription();
            return;
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            DukeException.deadlineInvalidCommand();
            return;
        }

        String description = information.substring(1, end - 1);
        String by;
        try { // user did not input date of deadline task
            by = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            DukeException.deadlineInvalidDate();
            return;
        }
        if (by.isBlank()) {
            DukeException.deadlineInvalidDate();
            return;
        }

        String formattedBy = by.replace(' ', 'T');
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(formattedBy);
        } catch (DateTimeParseException e) {
            DukeException.deadlineInvalidDateFormat();
            return;
        }
        Task deadline = new Deadline(description, date);
        tasks.add(deadline);
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + deadline + "\n\t" + "You have "
                + tasks.size() + " tasks on the list now.\n"
                + divider;
        System.out.print(printing);
    }

    protected static void addEvent(String input)
            throws ArrayIndexOutOfBoundsException, StringIndexOutOfBoundsException {
        String information;
        try { // user did not input description of event task
            information = input.split("event")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeException.eventInvalidDescription();
            return;
        }
        if (information.isBlank()) {
            DukeException.eventInvalidDescription();
            return;
        }

        int end = information.indexOf("/");
        if (end == -1) { // user did not input correct command
            DukeException.eventInvalidCommand();
            return;
        }

        String description = information.substring(1, end - 1);
        String at;
        try { // user did not input date of event task
            at = information.substring(end + 4);
        } catch (StringIndexOutOfBoundsException e) {
            DukeException.eventInvalidDate();
            return;
        }
        if (at.isBlank()) {
            DukeException.eventInvalidDate();
            return;
        }

        String formattedAt = at.replace(' ', 'T');
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(formattedAt);
        } catch (DateTimeParseException e) {
            DukeException.eventInvalidDateFormat();
            return;
        }
        Task event = new Event(description, date);
        tasks.add(event);
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + event + "\n\t" + "You have "
                + tasks.size() + " tasks on the list now.\n"
                + divider;
        System.out.print(printing);
    }

    protected static void deleteTask(String input) {
        int index = Integer.parseInt(input.substring(7));
        if ((index <= 0) || (index > tasks.size())) {
            DukeException.invalidIndex();
            return;
        }
        Task taskToBeDeleted = tasks.get(index - 1);
        tasks.remove(index - 1);
        String deletedTask = divider
                + "\tRoger that! I've removed this task:\n\t\t"
                + taskToBeDeleted
                + "\n\tYou have " + tasks.size()
                + " tasks on the list now.\n" + divider;
        System.out.print(deletedTask);
    }

    protected static void markTaskDone(String input) {
        int index = Integer.parseInt(input.substring(5));
        if ((index <= 0) || (index > tasks.size())) {
            DukeException.invalidIndex();
            return;
        }
        Task finishedTask = tasks.get(index - 1);
        finishedTask.markAsDone();
        String doneTask = divider
                + "\t\\(^O^)/ Good job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n" + divider;
        System.out.print(doneTask);
    }

    protected static void listTasks() {
        System.out.print(divider);
        System.out.print("\tHere are the tasks on your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            System.out.print("\t" + number + ". " + tasks.get(i) + "\n");
        }
        System.out.print(divider);
    }

    protected static void exitFocus() {
        String exit = divider
                + "\tHopefully I have helped you today. Byeee! (*^O^*)/\"\n"
                + divider;
        System.out.print(exit);
    }
}
