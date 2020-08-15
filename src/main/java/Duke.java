import java.util.Scanner;

public class Duke {

    public static void order() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("** Bye. Hope to see you soon!! **");
                break;
            } else {
                String output = "**\n" + input + "\n**";
                System.out.println(output);
            }
        }


    }

    public static void welcome() {
        String logo = " ___    ___        ______\n"
                + "|   \\  /   |_    _|  ____|\n"
                + "|    \\/    | |  | |  |  _ \n"
                + "|          | |__| |  |_| |\n"
                + "|__/\\__/\\__|\\___,_|______|\n";

        String welcome = logo
                + "\n"
                + "** Hello! I'm MUG  **\n"
                + "** What can I do for you ?_? **";

        System.out.println(welcome);
    }

    public static void main(String[] args) {
        Duke.welcome();
        Duke.order();
    }
}
