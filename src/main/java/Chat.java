public class Chat {
    private boolean isRunning;
    private TaskList<String> list;

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
        } else if (!input.isEmpty()) {
            addToList(input);
        }
    }

    void list() {
        System.out.println(list);
    }

    void addToList(String item) {
        list.add(item);
        System.out.println("    added: " + item + "\n");
    }

    void exit() {
        System.out.println("    bye! see you soon!");
        isRunning = false;
    }
}