import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        pikaService(sc, sc.nextLine());
        exit();
    }

    public static void greet() {
        System.out.println("Pikachu: Hello, I am Pikachu! Pika pika, what can pika DO FOR YOU!\n" +
                "Type a list of things for me to store and I will list it for you when you need it! " +
                "type 'list' and I list all that you said, or you can say bye to end us </3");
    }

    public static void pikaService(Scanner sc, String input) {
        List<String> storage = new ArrayList();
        while (!input.equals("bye")) {
            int size = storage.size();
            if (input.equals("list")) {
                for (int i = 0; i < size; i++) {
                    System.out.print(String.format("   %2d: %s\n", i + 1, storage.get(i)));
                }
            } else if (input.equals("list") && size == 0) {
                System.out.print("There is nothing added to the storage!");
            } else {
                storage.add(input);
                System.out.print(String.format("    added: %s\n", input));
            }
            input = sc.nextLine();
        }
    }



    private static void exit() {
        String exitMessage = "Pikachu: Pika byebye!\n";
        System.out.print(exitMessage);
    }

}
