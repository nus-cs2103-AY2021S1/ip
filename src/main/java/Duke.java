import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class Duke {
    List<String> toDoLst;
    HashMap<Integer, Boolean> toDoItemToStatus;

    Duke() {
        toDoLst = new ArrayList<>();
        toDoItemToStatus = new HashMap<>();
    }

    public List<String> getToDoLst() {
        return toDoLst;
    }

    // needed?
    public HashMap<Integer, Boolean> getToDoItemToStatus() {
        return toDoItemToStatus;
    }

    public void printToDoItem(int i) {
        System.out.println(String.format("%d.[%s] %s", i + 1, toDoItemToStatus.get(i) ? "✓" : "✗", toDoLst.get(i)));
    }

    public void setToDoItemStatus(int i, boolean bool) {
        toDoItemToStatus.put(i, bool);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        Scanner scanner = new Scanner(System.in);
        String line = "";

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(!line.equals("bye")) {
            if (!line.equals("")) {
                if (line.equals("list")) {
                    for (int i = 0; i < duke.getToDoLst().size(); i++) {
                        duke.printToDoItem(i);
                    }
                } else if (line.split(" ")[0].equals("done")) {
                    String[] lineData = line.split(" ");
                    int i = Integer.parseInt(lineData[1]) - 1;

                    duke.setToDoItemStatus(i, true);

                    System.out.println("Nice! I've marked this task as done:");

                    duke.printToDoItem(i);
                } else {
                    duke.setToDoItemStatus(duke.getToDoLst().size(), false);
                    duke.getToDoLst().add(line);

                    System.out.println("added: " + line);
                }
            }

            line = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}