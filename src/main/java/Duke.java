import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static final String line = "____________________________________________________________";

    private final String name = "Bolot";
    private final String end = "bye";
    private ArrayList<Task> list = new ArrayList<>();
    private final List<String> commands = Arrays.asList("todo", "deadline", "event", "done", "delete");

    public void printLogo() {
        System.out.println("Greetings, human. I am");
        System.out.println(" ______      ___   _____       ___    _________");
        System.out.println("|_   _ \\   .'   `.|_   _|    .'   `. |  _   _  |");
        System.out.println("  | |_) | /  .-.  \\ | |     /  .-.  \\|_/ | | \\_|");
        System.out.println(" _| |__) |\\  `-'  /_| |__/ |\\  `-'  /   _| |_");
        System.out.println("|_______/  `.___.'|________| `.___.'   |_____|");
    }

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I am " + name + ", your personal chat-bot companion.");
        System.out.println("How may I help you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye! Thank you for chatting with Bolot!");
        System.out.println("Hope to see you again soon!");
        System.out.println(line);
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();

        while (!type.equalsIgnoreCase(end)) {
            System.out.println(line);
            System.out.println(type);
            System.out.println(line);

            type = sc.nextLine();
        }

        bye();
    }

    private void printList() {

        if (list.size() == 0) {
            System.out.println("You have nothing on your list!");
        }

        int i = 1;
        for (Task todo: list) {
            System.out.println(i + ". " + todo);
            i++;
        }
    }

    private String getFirstWord(String input) throws UnrecognizedTaskException {

        String firstWord = input.contains(" ")
                ? input.substring(0, input.indexOf(" ")).toLowerCase()
                : input.toLowerCase();

        if (!commands.contains(firstWord)) {
            throw new UnrecognizedTaskException();
        } else {
            return firstWord;
        }
    }

    private void addTask(String firstWord, String input) throws EmptyTaskException, InvalidDateException {

        String task = "";
        String date = "";

        try {
            task = input.substring(firstWord.length() + 1);
        } catch (StringIndexOutOfBoundsException indexError) {
            throw new EmptyTaskException();
        }

        if ("todo".equals(firstWord)) {
            list.add(new ToDo(task));
        } else {
            try {
                task = task.substring(0, task.indexOf('/'));
                date = input.substring(input.indexOf('/') + 4);
            } catch (StringIndexOutOfBoundsException indexError) {
                switch (firstWord) {
                    case "deadline":
                        throw new DeadlineInvalidDate();
                    case "event":
                        throw new EventInvalidDate();
                }
            }

            switch (firstWord) {
                case "deadline":
                    list.add(new Deadline(task, date));
                    break;
                case "event":
                    list.add(new Event(task, date));
                    break;
            }
        }

        System.out.println("Got it. I've added this task:");

        System.out.println(list.get(list.size() - 1).toString());

        String taskText = list.size() == 1 ? " task " : " tasks ";
        System.out.println("Now you have " + list.size() + taskText + "in the list.");
    }

    private void markDone(int taskNo) throws IndexOutOfBoundsException {
        list.set(taskNo, list.get(taskNo).markDone());

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(taskNo));
    }

    private void deleteTask(int taskNo) throws IndexOutOfBoundsException {
        Task deleted = list.get(taskNo);
        list.remove(taskNo);

        System.out.println("Noted. I've removed this task:");
        System.out.println(deleted);
    }

    public void addToList() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase(end)) {

            System.out.println(line);

            if (input.trim().equalsIgnoreCase("list")) {

                printList();

            } else {

                try {

                    String firstWord = getFirstWord(input.trim());

                    if (firstWord.equals("done") || firstWord.equals("delete")) {

                        if (input.equals(firstWord)) {
                            System.out.print("Invalid format. After ");
                            System.out.print("\"" + firstWord + "\", ");
                            System.out.println("you need to put a positive integer");
                        } else {

                            try {
                                int taskNo = firstWord.equals("done")
                                        ? Integer.parseInt(input.substring(5)) - 1
                                        : Integer.parseInt(input.substring(7)) - 1;

                                if (firstWord.equals("done")) {
                                    markDone(taskNo);
                                } else {
                                    deleteTask(taskNo);
                                }

                            } catch (NumberFormatException numError) {
                                System.out.print("Invalid format. After ");
                                System.out.print("\"" + firstWord + "\", ");
                                System.out.println("you need to put a positive integer");
                            }
                        }

                    } else {

                        addTask(firstWord, input.trim());

                    }

                } catch (DukeException error) {
                    System.out.println(error.getMessage());
                } catch (IndexOutOfBoundsException indexError) {
                    System.out.println("Invalid index.");
                    String taskText = list.size() == 1 ? " task " : " tasks ";
                    System.out.println("You have " + list.size() + taskText + "on your list.");
                }
            }

            System.out.println(line);

            input = sc.nextLine();
        }

        bye();
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.printLogo();
        bot.greet();
//        bot.echo();
        bot.addToList();
    }
}
