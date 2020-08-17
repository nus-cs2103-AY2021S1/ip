import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Scanner input;
    private ArrayList<String> list;

    Duke(Scanner input, ArrayList<String> list) {
        this.input = input;
        this.list = list;
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
                this.printList();
            } else {
                this.addToList(command);
            }
            System.out.println("___________________________________________________");
        }
    }

    void addToList(String task) {
        this.list.add(task);
        System.out.println("added: " + task);
    }

    void printList() {
        for(int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
    }

    public static void main(String[] args) {
        System.out.println("___________________________________________________");
        System.out.println("Yo what's up! The name's Juke");
        System.out.println("What do you need?");
        System.out.println("___________________________________________________");

        Scanner input = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        Duke duke = new Duke(input, list);

        duke.commandHandler();
    }
}
