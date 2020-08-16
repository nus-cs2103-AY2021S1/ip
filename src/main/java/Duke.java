import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String line = "____________________________________________________________";

    private String name = "Bolot";
    private String end = "bye";
    private ArrayList<Task> list = new ArrayList<>();

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

    public void addList() {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();

        while (!type.equalsIgnoreCase(end)) {

            System.out.println(line);

            if (type.equalsIgnoreCase("list")) {
                int i = 1;
                for (Task todo: list) {
                    System.out.println(String.format("%d. %s", i, todo.toString()));
                    i++;
                }
            } else {
                String firstWord = type.contains(" ")
                        ? type.substring(0, type.indexOf(" ")).toLowerCase()
                        : type;

                if (firstWord.equals("done")) {
                    int taskNo = Integer.parseInt(type.substring(5)) - 1;
                    list.set(taskNo, list.get(taskNo).markDone());

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(taskNo));

                } else {

                    String task = firstWord.equals("todo")
                                ? type.substring(firstWord.length() + 1)
                                : type.substring(firstWord.length() + 1, type.indexOf('/'));

                    String date = firstWord.equals("todo")
                                    ? null
                                    : type.substring(type.indexOf('/') + 4);

                    System.out.println("Got it. I've added this task:");

                    switch (firstWord) {
                        case "todo":
                            list.add(new ToDo(task));
                            break;
                        case "deadline":
                            list.add(new Deadline(task, date));
                            break;
                        case "event":
                            list.add(new Event(task, date));
                            break;
                    }

                    System.out.println(list.get(list.size() - 1).toString());

                    String taskText = list.size() <= 1 ? " task " : " task ";
                    System.out.println("Now you have " + list.size() + taskText + "in the list.");
                }
            }

            System.out.println(line);

            type = sc.nextLine();
        }

        bye();
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//
//        System.out.println("Hello from\n" + logo);

        String logo =
                " ______      ___   _____       ___    _________\n"
                + "|_   _ \\   .'   `.|_   _|    .'   `. |  _   _  |\n"
                + "  | |_) | /  .-.  \\ | |     /  .-.  \\|_/ | | \\_| \n"
                + " _| |__) |\\  `-'  /_| |__/ |\\  `-'  /   _| |_   \n"
                + "|_______/  `.___.'|________| `.___.'   |_____|";

        System.out.println("Greetings, human. I am\n" + logo);

        Duke bot = new Duke();
        bot.greet();
//        bot.echo();
        bot.addList();
    }
}
