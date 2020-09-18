import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private Storage storage;
    private ArrayList<Task> list;

    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        this.list = storage.loadData();
    }

    // Runs a loop to read inputs from user
    public void runCommands() {
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        while (true) {
            Ui.horizontalLine();
            try {
                String input = sc.nextLine();
                Command cmd = parser.parseCommand(input);
                this.list = cmd.executeCommand(this.list);
                if (cmd.getType().equals(CommandType.BYE)) {
                    // exit program if user inputs "bye"
                    break;
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter valid input");
            } catch (IndexOutOfBoundsException obe) {
                System.out.println("Please enter valid index");
            }
        }
    }

    // Returns list of Tasks
    public ArrayList<Task> getList() {
        return this.list;
    }

    // Save list of Tasks onto txt file
    public void save() {
        storage.saveData(this.list);
    }
}