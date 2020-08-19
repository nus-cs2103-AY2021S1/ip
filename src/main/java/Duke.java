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
        String list = "list";

        String[] taskList = new String[100];
        int taskSize = 0;

        do {
            if (input.equals(list)) {
                int curr = 0;
                while (curr < taskSize) {
                    System.out.println((curr + 1) + ". " + taskList[curr]);
                    curr += 1;
                }
                System.out.println("_________________________________________");
                input = sc.nextLine();
            }
            else {
                taskList[taskSize] = input;
                System.out.println("I have added: " + input
                        + "\n_________________________________________");
                input = sc.nextLine();
                taskSize += 1;
            }
        }
        while (!input.equals(bye));

        System.out.println("You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________");
    }
}
