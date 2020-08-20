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
        String addedMessage = "ok can i've added it\n";
        String countMessage = "Now got " + taskList.size() + " task in the list\n";
        if (userInput.equals("bye")) {
            System.out.println(borders + "Bye. Hope to see you again soon!" + borders);
        } else if (userInput.equals("list")) {
            int counter = 0;
            String returnString = borders + "faster do don't netflix already";
            Iterator<Task> taskIterator = taskList.iterator();
            while (taskIterator.hasNext()) {
                Task thisTask = taskIterator.next();
                returnString += "\n" + (counter + 1) + ". " + thisTask.toString();
                counter++;
            }
            System.out.println(returnString + borders);
            response(new Scanner(System.in), taskList);
        } else if (userInput.startsWith("done")) {
            String returnString = borders + "ok sure good job i guess\n";
            int taskDone = Integer.parseInt(userInput.substring(5));
            Task thisTask = taskList.get(taskDone - 1);
            thisTask.done();
            returnString += thisTask.getStatusIcon() + " " + thisTask.toString();
            System.out.println(returnString + borders);
            response(new Scanner(System.in), taskList);
        } else if (userInput.startsWith("todo")) {
            Task thisTask = new Task(userInput);
            taskList.add(thisTask);
            System.out.println(borders + "added: " + userInput + borders);
            System.out.println(borders + addedMessage + thisTask.toString() + "\n" + countMessage + borders);
            Duke.response((new Scanner(System.in)), taskList);
        } else if (userInput.startsWith("deadline")) {
            String[] StringArr = userInput.split(" /by");
            Task thisTask = new Deadline(StringArr[0].replace("deadline", ""), StringArr[1]);
            taskList.add(thisTask);
            System.out.println(borders + addedMessage + thisTask.toString() + "\n" + countMessage + borders);
            Duke.response((new Scanner(System.in)), taskList);
        } else if (userInput.startsWith("event")) {
            String[] StringArr = userInput.split(" /at");
            Task thisTask = new Event(StringArr[0].replace("event", ""), StringArr[1]);
            taskList.add(thisTask);
            System.out.println(borders + addedMessage + thisTask.toString() + "\n" + countMessage + borders);
            Duke.response((new Scanner(System.in)), taskList);
        } else {
            Duke.response((new Scanner(System.in)), taskList);
        }
    }
}