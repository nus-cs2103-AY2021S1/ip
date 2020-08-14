import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        echo(sc, sc.nextLine());
        exit();
    }

    private static void exit() {
        String exitMessage = "Pikachu: Pika byebye!\n";
        System.out.print(exitMessage);
    }

    public static void greet() {
        System.out.println("Pikachu: Hello, I am Pikachu! Pika pika, what can pika DO FOR YOU!\n" +
                "Type something and I will echo it for you! or you can say bye to end us </3");
    }

    public static void echo(Scanner sc, String input) {
        while (!input.equals("bye")) {
            System.out.print("Echoed Message: " + input + "\n");
            input = sc.nextLine();
        }
    }
}
