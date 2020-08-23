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
            String input = sc.nextLine();
            System.out.println(input);
            String[] inputArr = deconstruct(input);
            String keyWord = getKeyWord(inputArr);
            String restOfWord = getRestOfWord(inputArr);
            if (keyWord.equals("bye")) {
                Ui.goodBye();
                break;
            } else {
                manager.manageInput(keyWord, restOfWord);
            }
        }
        sc.close();
    }

    private String getKeyWord(String[] arr) {
        return arr[0].toLowerCase();
    }

    private String getRestOfWord(String[] arr) {
        return arr.length == 1 ? "" : arr[1];
    }

    private String[] deconstruct(String input) {
        String formattedString = input.trim().replaceAll("\\s{2,}", " ");
        return formattedString.split(" ", 2);
    }
}
