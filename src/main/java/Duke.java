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
        System.out.println("Pikachu: Hello, I am Pikachu! My pika service creates a to-do list for you!\n" +
                "1. type 'list' and I list all that you said, along if it is completed\n" +
                "2. type 'done x' where x is the index of the item you want to be indicated done\n" +
                "3. or you can say 'bye' to end us </3");
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
                for (int i = 0; i < size; i++) {
                    System.out.print(String.format("   %2d. %s\n", i + 1, storage.get(i)));
                }
            } else if (firstWord.equals("list") && size == 0){
                System.out.print("There is nothing added to the storage!\n");
            } else if (firstWord.equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                storage.get(index).makeDone();
                System.out.print(String.format("    Swee la, task done liao!:\n     %s", storage.get(index)));
            } else {
                Task newTask = new Task(input);
                storage.add(newTask);
                System.out.print(String.format("    added: %s\n", input));
            }

            input = sc.nextLine();
        }
    }

    public static void exit() {
        String exitMessage = "Pikachu: Pika byebye!\n";
        System.out.print(exitMessage);
    }

}
