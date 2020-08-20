import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final String VERSION_NUMBER = "1.0.0";
    private final Scanner sc = new Scanner(System.in);
    private final String NEW_LINE = "\n";
    private final String HORIZONTAL_LINE =
            "    ____________________________________________________________";
    private final String PADDING = "      ";

    private enum Commands {
        EXIT("BYE"), ADD("ADD"), LIST("LIST"),
        DONE("DONE");
        private String str;
        Commands(String str){
            this.str = str;
        }
        public String getString() {
            return this.str;
        }
    }

    private final String MESSAGE_TEMPLATE = HORIZONTAL_LINE + NEW_LINE + PADDING + "%s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final String MESSAGE_TEMPLATE_VERBAL = HORIZONTAL_LINE + NEW_LINE + PADDING + "Deuk: %s"
            + NEW_LINE + HORIZONTAL_LINE + NEW_LINE + NEW_LINE;
    private final List<Task> storageList = new ArrayList<>();

    public void init() {
        sayHello();

        while(true) {
            if (sc.hasNext()) {
                String input = sc.nextLine().trim();
                if (input.toUpperCase().equals(Commands.EXIT.getString())) { // to make command case-insensitive
                    sayGoodbye();
                    break;
                }
                if (input.toUpperCase().equals(Commands.LIST.getString())) {
                    displayStorageList();
                }
                else if (input.length() >= 4 && input.substring(0,4).toUpperCase().equals(Commands.DONE.getString())) {
                    int index = Integer.valueOf(input.substring(4).trim()); // TODO: CHECK IF INTEGER
                    setTaskDone(index);
                }
                else {
                    addToStorageList(new Task(input));
                }
            }
        }
    }

    private void echoBack(String message) {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, message);
    }

    private void addToStorageList(Task obj) {
        this.storageList.add(obj);
        System.out.printf(MESSAGE_TEMPLATE, "Added: " + obj.getName());
    }

    private void displayStorageList() {
        String output = "";
        int counter = 1;
        for (Task ele: this.storageList) {
            if (counter == 1) {
                output += counter + ". " + ele.toString() + NEW_LINE;
                counter ++;
                continue;
            }
            output += PADDING + counter + ". " + ele.toString() + NEW_LINE;
            counter++;
        }
        output = output.substring(0, output.length()-2);
        System.out.printf(MESSAGE_TEMPLATE, output);
    }

    private void setTaskDone(int index) {
        // TODO: CHECK INDEX WITHIN BOUND
        this.storageList.get(index-1).setDoneness(true);
        String message = "Nice job! I'll mark that as done:" + NEW_LINE + PADDING + this.storageList.get(index-1).toString();
        System.out.printf(MESSAGE_TEMPLATE, message);
    }

    private void sayGoodbye() {
        System.out.printf(MESSAGE_TEMPLATE_VERBAL, "Goodbye, hope to see you again!");
    }

    private void sayHello() {
        String logo =
                  "     _____             _    \n"
                + "    |  __ \\           | |   \n"
                + "    | |  | | ___ _   _| | __\n"
                + "    | |  | |/ _ \\ | | | |/ /\n"
                + "    | |__| |  __/ |_| |   < \n"
                + "    |_____/ \\___|\\__,_|_|\\_\\  v" + VERSION_NUMBER + "\n";
        String introMessage = "I'm Deuk, nice to meet you\n" + PADDING +
                "How can I be of service today?";
        System.out.printf(logo + MESSAGE_TEMPLATE_VERBAL, introMessage);
    }

    public static void main(String[] args) {
        new Duke().init();
    }
}
