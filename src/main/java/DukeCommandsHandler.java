import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DukeCommandsHandler {
    private static final String divider =
            "\t------------------------------------------------------------------\n";
    protected static ArrayList<Task> tasks = new ArrayList<>();

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
        System.out.println(greetings);
    }

    protected static void addressUser(String name) {
        String address = divider
                + "\tHi there, " + name + "!"
                + "\n\tHow can I help you today?\n"
                + divider;
        System.out.println(address);
    }

    protected static void addToDo(String input) throws DukeException {
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
        tasks.add(toDo);
        Duke.storage.saveData();
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + toDo + "\n\t" + "You have "
                + tasks.size() + " tasks on your list now.\n"
                + divider;
        System.out.println(printing);
    }

    protected static void addDeadline(String input) throws DukeException {
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
        tasks.add(deadline);
        Duke.storage.saveData();
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + deadline + "\n\t" + "You have "
                + tasks.size() + " tasks on your list now.\n"
                + divider;
        System.out.println(printing);
    }

    protected static void addEvent(String input) throws DukeException {
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
        tasks.add(event);
        Duke.storage.saveData();
        String printing = divider
                + "\tGotcha! I've added this task:\n\t\t"
                + event + "\n\t" + "You have "
                + tasks.size() + " tasks on your list now.\n"
                + divider;
        System.out.println(printing);
    }

    protected static void deleteTask(String input) throws DukeException {
        String indexString;
        try {
            indexString = input.substring(7);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to delete!\n"
                    + "\tYou have " + tasks.size() + " tasks on your list now.\n"
                    + divider);
        }
        if (indexString.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to delete!\n"
                    + "\tYou have " + tasks.size() + " tasks on your list now.\n"
                    + divider);
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > tasks.size())) {
            throw new DukeException(divider
                    + "\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!\n"
                    + divider);
        }
        Task taskToBeDeleted = tasks.get(index - 1);
        tasks.remove(index - 1);
        Duke.storage.saveData();
        String deletedTask = divider
                + "\tRoger that! I've removed this task:\n\t\t"
                + taskToBeDeleted
                + "\n\tYou have " + tasks.size()
                + " tasks on your list now.\n" + divider;
        System.out.println(deletedTask);
    }

    protected static void markTaskDone(String input) throws DukeException {
        String indexString;
        try {
            indexString = input.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + tasks.size() + " tasks on your list now.\n"
                    + divider);
        }
        if (indexString.isBlank()) {
            throw new DukeException(divider
                    + "\tPlease enter a task number you wish to mark done!\n"
                    + "\tYou have " + tasks.size() + " tasks on your list now.\n"
                    + divider);
        }

        int index = Integer.parseInt(indexString);
        if ((index <= 0) || (index > tasks.size())) {
            throw new DukeException(divider
                    + "\tThere is no such task number.\n"
                    + "\tPlease enter a valid one!\n"
                    + divider);
        }
        Task finishedTask = tasks.get(index - 1);
        finishedTask.markAsDone();
        Duke.storage.saveData();
        String doneTask = divider
                + "\t\\(^O^)/ Good job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n" + divider;
        System.out.println(doneTask);
    }

    protected static void listTasks() throws DukeException {
        System.out.print(divider);
        if (tasks.size() == 0) {
            throw new DukeException("\tThere are currently no tasks on your list!\n"
                    + "\tStart adding one now!");
        } else {
            System.out.print("\tHere are the tasks on your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                int number = i + 1;
                System.out.print("\t" + number + ". " + tasks.get(i) + "\n");
            }
        }
        System.out.println(divider);
    }

    protected static void exitFocus() {
        String exit = divider
                + "\tHopefully I have helped you today. Byeee! (*^O^*)/\"\n"
                + divider;
        System.out.print(exit);
    }

    protected static void invalidInput() throws DukeException {
        throw new DukeException(divider
                + "\tOops! I'm not sure what you meant!\n"
                + "\tPlease try again!\n"
                + divider);
    }
}
