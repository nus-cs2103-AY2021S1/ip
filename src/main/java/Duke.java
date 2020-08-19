import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____   ____  \n"
                + "|  _ \\ |  _ \\ \n"
                + "| | | || | | | \n"
                + "| |_| || |_| | \n"
                + "|____/ |____/ \n";
        System.out.println("Hi! I'm\n" + logo + "How can I help you? :)\n"
                + "_________________________________________");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String bye = "bye";

        do {
            System.out.println(input + "\n_________________________________________");
            input = sc.nextLine();
        } while (!input.equals(bye));

        System.out.println("You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________");
    }
}
