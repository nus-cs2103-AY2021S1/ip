import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    // Attributes
    private static final String NAME = "DUKE";
    private static final String INTRODUCTION = String.format(
            "     Hello! I'm %s\n" +
                    "     I was created for the module CS2103T\n" +
                    "     What can I do for you?", NAME);
    private static final String EXIT = "     Bye. Hope to see you again soon!";
    private static final String LINE = "    ____________________________________________________________";

    private static List<Task> lst = new ArrayList<>();

    // Scanner
    private final static Scanner sc = new Scanner(System.in);

    // Greeting
    private static void greeting() {
        System.out.println(wrapText(INTRODUCTION));
    }

    // Wrap text in lines
    private static String wrapText(String text) {
        return LINE + "\n" + text + "\n" + LINE + "\n";
    }

    // Prints list
    public static void printList() {
        StringBuilder sb = new StringBuilder();
        int sizeOfList = lst.size();
        sb.append("     Here are the tasks in your list:");

        for (int i = 0; i < sizeOfList; i++) {
            int number = i + 1;
            String text = lst.get(i).toString();
            sb.append(String.format("\n     %s. %s", number, text));
        }

        System.out.println(wrapText(sb.toString()));
    }


    // Exit
    private static void exit() {
        System.out.println(wrapText(EXIT));
    }

    public static void main(String[] args) {
        greeting();
        while (sc.hasNext()) {

            String input = sc.nextLine();
            String[] words = input.split(" ");
            String firstWord = words[0];
            switch (firstWord) {
                case "bye" -> exit();
                case "list" -> printList();
                case "done" -> {
                    int taskNumber = Integer.parseInt(words[1]);
                    Task task = lst.get(taskNumber - 1);
                    task.markAsDone();
                    String doneText = String.format("     Nice! I've marked this task as done: \n" +
                                                    "     %s", task);
                    System.out.println(wrapText(doneText));
                }

                default -> {
                    Task newTask = new Task(input);
                    lst.add(newTask);
                    System.out.println(wrapText("     added: " + input));
                }
            }
        }
    }
}
