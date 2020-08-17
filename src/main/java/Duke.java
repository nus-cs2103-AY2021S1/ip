import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Scanner input;
    private ArrayList<Task> list;


    Duke(Scanner input, ArrayList<Task> list) {
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
            } else if (command.split(" ")[0].equals("done")) {
                int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                this.taskDone(taskNo);
            } else {
                this.addToList(command);
            }
            System.out.println("___________________________________________________");
        }
    }


    void addToList(String command) {
        Task task = new Task(command);
        this.list.add(task);
        System.out.println("added: " + task);
    }

    void taskDone(int taskNo) {
        Task toBeDone = this.list.get(taskNo);
        toBeDone.markAsDone();
        this.list.set(taskNo, toBeDone);
        System.out.println("Good Job, this task is now done:");
        System.out.println(toBeDone);
    }

    void printList() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
    }

    public static void main(String[] args) {
        System.out.println("___________________________________________________");
        System.out.println("Yo what's up! The name's Juke");
        System.out.println("What do you need?");
        System.out.println("___________________________________________________");

        Scanner input = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        Duke duke = new Duke(input, list);

        duke.commandHandler();
    }
}
