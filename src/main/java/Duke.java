import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui.printWelcome();
        Scanner sc = new Scanner(System.in);

        AddList a = new AddList();

        String input = "";

        while (!input.equals("bye")) {
            input = sc.nextLine();
            a.allocate(input);
        }

        Ui.printGoodbye();
    }
}
