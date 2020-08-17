import java.util.Scanner;

public class Duke {

    private Scanner input;

    Duke(Scanner input) {
        this.input = input;
    }

    void commandHandler() {
        while (input.hasNextLine()) {
            String command = input.nextLine();

            System.out.println("___________________________________________________");
            if (command.equals("bye")) {
                System.out.println("That's it? That's a shame. Well, see you later then.");
                System.out.println("___________________________________________________");
                this.input.close();
                break;
            } else if (command.equals("list")) {
                System.out.println("list");
            }  else if (command.equals("blah")) {
                System.out.println("blah");
            } else {
                System.out.println("Say that again?");
            }
            System.out.println("___________________________________________________");
        }
    }

    public static void main(String[] args) {
        System.out.println("___________________________________________________");
        System.out.println("Yo what's up! The name's Juke");
        System.out.println("What do you need?");
        System.out.println("___________________________________________________");

        Scanner input = new Scanner(System.in);
        Duke duke = new Duke(input);

        duke.commandHandler();
    }
}
