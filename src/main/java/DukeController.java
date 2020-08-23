import java.util.ArrayList;
import java.util.Scanner;

public class DukeController {

    private final Manager manager;
    private final FileManager fileManager = new FileManager();

    protected DukeController() {
        this.manager = new Manager(new ArrayList<Task>(fileManager.getTasks()), fileManager);
    }

    protected void simulate() {
        Scanner sc = new Scanner(System.in);
        Ui.greetings();
        while (true) {
            try {
                String input = sc.nextLine();
                System.out.println(input);
                String[] arr = Parser.parse(input);
                String keyWord = arr[0];
                String restOfWord = arr[1];
                if (keyWord.equals("bye")) {
                    Ui.goodBye();
                    break;
                } else {
                    manager.manageInput(keyWord, restOfWord);
                }
            } catch (DukeException e) {
                Ui.printMsg(e.getMessage());
            }
        }
        sc.close();
    }
}
