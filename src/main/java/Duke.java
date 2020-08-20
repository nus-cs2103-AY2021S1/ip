import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello im Eu Zin's Duke, he spent thursday afternoon creating me cuz he forgot abt the iP");

        ArrayList<String> taskList = new ArrayList<>();
        Duke.response(new Scanner(System.in), taskList);
    }

    static void response(Scanner scanner, ArrayList<String> taskList) {
        String userInput = scanner.nextLine();
        String borders = "\n\n\\   / \\   / \\   / \\   / im not very creative \\   / \\   / \\   / \\   /\n \\ /   \\ /   \\ /   \\ /      EuZin's Duke      \\ /   \\ /   \\ /   \\ /\n\n";
        if (userInput.equals("bye")) {
            System.out.println(borders + "Bye. Hope to see you again soon!" + borders);
        } else if (userInput.equals("list")) {
            int counter = 0;
            String returnString = borders + "faster do don't netflix already";
            Iterator<String> taskIterator = taskList.iterator();
            while(taskIterator.hasNext()) {
                returnString += "\n" + (counter+1) + ". " + taskIterator.next();
                counter++;
            }
            System.out.println(returnString + borders);
        } else {
            taskList.add(userInput);
            System.out.println(borders + "added: " + userInput + borders);
            Duke.response((new Scanner(System.in)), taskList);
        }
    }
}
