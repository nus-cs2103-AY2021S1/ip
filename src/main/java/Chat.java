public class Chat {
    private boolean isRunning;
    private TaskList<Task> list;

    Chat() {
        isRunning = true;
        list = new TaskList<>();
        greet();
    }

    static void greet() {
        String logo = "\n.-. .-')                .-') _  .-. .-')                .-') _    \n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )   \n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._  \n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__) \n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--' \n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |    \n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |    \n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |    \n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'    \n";
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
        } else if (!input.isEmpty()) {
            addToList(new Task(input));
        }
    }

    void list() {
        System.out.println(list);
    }

    void addToList(Task item) {
        list.add(item);
        System.out.println("    ok! I've added this task:\n      " + item + "\n");
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
}