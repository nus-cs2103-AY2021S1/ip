public class Chat {
    private boolean isRunning;
    private NumberedList<Task> list;

    Chat() {
        isRunning = true;
        list = new NumberedList<>();
        greet();
    }

    static void greet() {
        String logo = "\n.-. .-')                .-') _  .-. .-')                .-') _\n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )\n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._\n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__)\n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--'\n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |\n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |\n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |\n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'\n";
        System.out.println("helluu! I'm\n" + logo + "\nwhat would you like me to do?\n");
    }

    boolean isRunning() {
        return isRunning;
    }

    void handleInput(String input) throws EmptyTaskException, EmptyTaskNumberException,
            InvalidFormatException, NoSuchCommandException {
        if (input.equals("bye")) {
            exit();
        } else if (input.equals("list")) {
            list();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("done ")) {
            markAsDone(input);
        } else if (input.equals("done") || (input.length() >= 5 && input.substring(0, 5).equals("done "))) {
            throw new EmptyTaskNumberException();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("todo ")) {
            addToList(new Todo(input));
        } else if (input.equals("todo") || (input.length() >= 5 && input.substring(0, 5).equals("todo "))) {
            throw new EmptyTaskException("a todo");
        } else if (input.length() >= 10 && input.substring(0, 9).equals("deadline ")) {
            if (!input.contains(" /by ")) {
                throw new InvalidFormatException(Deadline.FORMAT);
            }
            addToList(new Deadline(input));
        } else if (input.equals("deadline")
                || (input.length() >= 9 && input.substring(0, 9).equals("deadline "))) {
            throw new EmptyTaskException("a deadline");
        } else if (input.length() >= 7 && input.substring(0, 6).equals("event ")) {
            if (!input.contains(" /at ")) {
                throw new InvalidFormatException(Event.FORMAT);
            }
            addToList(new Event(input));
        } else if (input.equals("event")
                || (input.length() >= 6 && input.substring(0, 6).equals("event "))) {
            throw new EmptyTaskException("an event");
        } else {
            throw new NoSuchCommandException();
        }
    }

    void list() {
        if (list.size() <= 0) {
            System.out.println("    your list is empty!\n");
        } else {
            System.out.println("    here's your list:\n" + list);
        }
    }

    void addToList(Task task) {
        list.add(task);
        int numOfTasks = list.size();
        System.out.println(String.format("    ok! I've added this task:\n      %s\n    you now have %d task"
                + (numOfTasks > 1 ? "s" : "") + " in your list\n", task, numOfTasks));
    }

    void markAsDone(String input) {
        try {
            int id = Integer.parseInt(input.substring(5)) - 1;
            Task task = list.get(id);
            task.markAsDone();
            System.out.println("    nice! I've marked this task as done:\n      " + task + "\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("    oops! invalid task number!\n");
        }
    }

    void exit() {
        System.out.println("    bye! see you soon!");
        isRunning = false;
    }
}