public class Chat {
    private boolean isRunning;
    private TaskList<Task> list;

    Chat() {
        isRunning = true;
        list = new TaskList<>();
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

    void handleInput(String input) {
        if (input.equals("bye")) {
            exit();
        } else if (input.equals("list")) {
            list();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("done ")) {
            markAsDone(input);
        } else if (input.length() >= 6 && input.substring(0, 5).equals("todo ")) {
            addToList(new Todo(input));
        } else if (input.length() >= 10 && input.substring(0, 9).equals("deadline ")) {
            if (input.contains(" /by ")) {
                addToList(new Deadline(input));
            } else {
                invalidFormatHandler(Deadline.FORMAT);
            }
        } else if (input.length() >= 7 && input.substring(0, 6).equals("event ")) {
            if (input.contains(" /at ")) {
                addToList(new Event(input));
            } else {
                invalidFormatHandler(Event.FORMAT);
            }
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
            System.out.println("    invalid task number!\n");
        }
    }

    void exit() {
        System.out.println("    bye! see you soon!");
        isRunning = false;
    }

    void invalidFormatHandler(String format) {
        System.out.println(String.format("    invalid format! please follow '%s'\n", format));
    }
}