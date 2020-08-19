import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Greetings
        String greetings = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today, Master?\n";
        System.out.println(greetings);

        // Reply Loop
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            String reply = "\nI have received your command as follows:\n"
                    + input
                    + "\n";
            System.out.println(reply);

            input = sc.nextLine();
        }

        sc.close();

        // Farewells
        String farewells = "\nIt is my honor to have served you.\n"
                + "Please come back again.";
        System.out.println(farewells);
    }
}
