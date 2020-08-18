import java.util.*;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n" +
                "Send me a task and I'll store it for you.\n" +
                "Send \"list\" to see all tasks." +
                "Send \"done <item number>\" to mark an item as done\n" +
                "Send \"bye\" to end our conversation.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("Theres currently nothing in your list.");
                } else {
                    for (int i = 0; i < tasks.size() ; i++) {
                        System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                    }
                }
                input = scanner.nextLine();
            } else if (input.split(" ")[0].equals("done")) {
                try {
                    int itemNumber = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(itemNumber - 1).isDone = true;
                    System.out.println("Nice, I've marked this item as done:");
                    System.out.println("\t" + tasks.get(itemNumber - 1));
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    System.out.println("Please key in a valid number for \"done\"");
                } finally {
                    input = scanner.nextLine();
                }

            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
                input = scanner.nextLine();
            }
        }
        System.out.println("Bye, hope to chat again soon!");
    }
}
