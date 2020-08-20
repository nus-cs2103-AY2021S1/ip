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

        ArrayList<Task> taskList = new ArrayList<>();
        Duke.response(new Scanner(System.in), taskList);
    }

    static void response(Scanner scanner, ArrayList<Task> taskList) {
        String userInput = scanner.nextLine();
        String borders = "\n\n\\   / \\   / \\   / \\   / im not very creative \\   / \\   / \\   / \\   /\n \\ /   \\ /   \\ /   \\ /      EuZin's Duke      \\ /   \\ /   \\ /   \\ /\n\n";
        if (userInput.equals("bye")) {
            System.out.println(borders + "Bye. Hope to see you again soon!" + borders);
        } else if (userInput.equals("list")) {
            int counter = 0;
            String returnString = borders + "faster do don't netflix already";
            Iterator<Task> taskIterator = taskList.iterator();
            while (taskIterator.hasNext()) {
                Task thisTask = taskIterator.next();
                returnString += "\n" + (counter + 1) + ". " + thisTask.getStatusIcon() + " " + thisTask.description;
                counter++;
            }
            System.out.println(returnString + borders);
            response(new Scanner(System.in), taskList);
        } else if (userInput.length() >= 4 && userInput.substring(0,4).equals("done")) {
            String returnString = borders + "ok sure good job i guess\n";
            int taskDone = Integer.parseInt(userInput.substring(5));
            Task thisTask = taskList.get(taskDone-1);
            thisTask.done();
            returnString += thisTask.getStatusIcon() + " " + thisTask.description;
            System.out.println(returnString + borders);
            response(new Scanner(System.in), taskList);
        } else {
            taskList.add(new Task(userInput));
            System.out.println(borders + "added: " + userInput + borders);
            Duke.response((new Scanner(System.in)), taskList);
        }
    }
}
