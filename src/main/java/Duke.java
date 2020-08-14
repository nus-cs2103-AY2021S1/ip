import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        greet();
        pikaService();
        exit();
    }

    public static void greet() {
        System.out.println("Pikachu: Hello, I am Pikachu! My pika service creates a to-do list for you!\n\n" +
                "1. type 'list' and I list all that you said, along if it is completed\n" +
                "2. type either 'event', 'deadline', 'todo', followed by the task!\n" +
                "   2.1. if 'event', type the task followed by a '/at <duration>' to indicate duration\n" +
                "   2.2. if 'deadline', type the task followed by a '/by <deadline>' to indicate deadline\n" +
                "3. type 'done x' where x is the index of the item you want to be indicated done\n" +
                "4. or you can say 'bye' to end us </3");
    }

    public static void pikaService() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<Task> storage = new ArrayList();

        while (true) {

            String firstWord = input.split(" ")[0];
            int size = storage.size();

            if (firstWord.equals("bye")) {
                break;
            } else if (firstWord.equals("list")) {
                System.out.print("Retrieving your list, patient ah!\n");
                for (int i = 0; i < size; i++) {
                    System.out.print(String.format("   %2d. %s\n", i + 1, storage.get(i)));
                }
            } else if (firstWord.equals("list") && size == 0){
                System.out.print("  There is nothing added to the storage!\n");
            } else if (firstWord.equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                storage.get(index).makeDone();
                System.out.print(String.format("    Swee la, task done liao!:\n     " +
                        "%s\n", storage.get(index)));
            } else {
                String message = input.substring(firstWord.length());
                taskSorter(storage, firstWord, message);
            }
            input = sc.nextLine();
        }
    }

    public static void taskSorter(List<Task> storage, String tag, String message) {
        Task newTask = null;
        if (tag.equals("todo")) {
            newTask = new Todo(message);
        } else if (tag.equals("deadline")) {
            String[] parsedMessage = message.split("/by");
            newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
        } else if (tag.equals("event")) {
            String[] parsedMessage = message.split("/at");
            newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
        }
        storage.add(newTask);
        System.out.print("    Steady! I add... wait ah...\n");
        System.out.print(String.format("        added: %s\n", newTask));
        System.out.print(String.format("    Now you got %d tasks in list, " +
                "don't procrastinate hor\n", storage.size()));
    }

    public static void exit() {
        String exitMessage = "Pikachu: Pika byebye!\n";
        System.out.print(exitMessage);
    }

}
