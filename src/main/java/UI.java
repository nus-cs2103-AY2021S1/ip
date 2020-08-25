import java.util.ArrayList;

public class UI {

    public static void printFormattedMessage(String errorMessage) {
        printHorizontalLine();
        System.out.println(errorMessage);
        printHorizontalLine();

    }

    public static void printHorizontalLine() {
        System.out.println(" ____________________________________________________________");
    }

    public static void printByeMessage() {
        printFormattedMessage("Bye. Hope to see you again soon!");
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printTaskAdd(Task task, int sizeStore) {
        printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + String.valueOf(sizeStore) + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printDeleteMessage(Task task, int sizeStore) {
        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + String.valueOf(sizeStore) + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printListOfTasks(ArrayList<Task> tasks) {
        printHorizontalLine();
        System.out.println(" Here are the tasks in your list:");
        int sizeStore = tasks.size();
        for (int i = 1; i < sizeStore + 1; i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
        printHorizontalLine();
    }

    public static void printMarkAsDone(Task task) {
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printHorizontalLine();

    }

    public static void printKeywordTasks(String substring, ArrayList<Task> stringStore) throws DukeNoMatchesExcpetion {
        ArrayList<Task> keywordTasks = new ArrayList<>();
        for (int i = 0; i < stringStore.size(); i++) {
            if(stringStore.get(i).containsKeyword(substring)) {
                keywordTasks.add(stringStore.get(i));
            }
        }
        try {
            if(keywordTasks.size() == 0){
                throw new DukeNoMatchesExcpetion("ERROR: No matches found!");
            }
            printHorizontalLine();
            System.out.println("Here are the matching tasks in your list:");
            int sizeStore = keywordTasks.size();
            for (int i = 1; i < sizeStore + 1; i++) {
                System.out.println(i + "." + keywordTasks.get(i - 1));
            }
            printHorizontalLine();
        } catch (DukeNoMatchesExcpetion e){
            UI.printFormattedMessage("ERROR: No matches found!");

        }
    }


}
