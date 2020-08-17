import java.util.*;

public class Duke {

    private static final String line = "____________________________________________________________";

    private final String name = "Bolot";
    private final String end = "bye";
    private ArrayList<Task> list = new ArrayList<>();
    private final List<String> commands = Arrays.asList("todo", "deadline", "event", "done");

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
        System.out.println(String.format("Hello! I am %s, your personal chat-bot companion.", name));
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
            System.out.println(String.format("%d. %s", i, todo.toString()));
            i++;
        }
    }

    private String getFirstWord(String input) throws UnrecognizedTaskException {
        if (input.contains(" ")) {
            return input.substring(0, input.indexOf(" ")).toLowerCase();
        } else {

            if (!commands.contains(input.toLowerCase())) {
                throw new UnrecognizedTaskException();
            }

            return input.toLowerCase().trim();
        }
    }

    private void addTask(String firstWord, String input) throws EmptyTaskException, InvalidDateException {

        String task = "";
        String date = "";

        try {
            task = input.substring(firstWord.length() + 1);
        } catch (IndexOutOfBoundsException indexError) {
            throw new EmptyTaskException();
        }

        switch (firstWord) {
            case "todo":
                list.add(new ToDo(task));
                break;

            default:

                try {
                    task = task.substring(0, task.indexOf('/'));
                    date = input.substring(input.indexOf('/') + 4);
                } catch (IndexOutOfBoundsException indexError) {
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

                break;
        }

        System.out.println("Got it. I've added this task:");

        System.out.println(list.get(list.size() - 1).toString());

        String taskText = list.size() < 1 ? " task " : " tasks ";
        System.out.println("Now you have " + list.size() + taskText + "in the list.");
    }

    private void markDone(int taskNo) {
        list.set(taskNo, list.get(taskNo).markDone());

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(taskNo));
    }

    public void addToList() {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase(end)) {

            System.out.println(line);

            if (input.trim().equalsIgnoreCase("list")) {

                printList();

            } else {

                String firstWord = "";
                try {

                    firstWord = getFirstWord(input);

                    if (firstWord.equals("done")) {

                        int taskNo = Integer.parseInt(input.substring(5)) - 1;
                        markDone(taskNo);

                    } else {

                        addTask(firstWord, input.trim());

                    }

                } catch (DukeException error) {
                    System.out.println(error.getMessage());
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
