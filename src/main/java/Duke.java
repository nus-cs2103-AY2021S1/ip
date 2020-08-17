import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // constant strings:
    private static final String lineBreak = "____________________________________________________________";
    private static final String exitGreeting = "Bye. Hope to see you again soon!";
    private static final String indent = "    ";
    private static final String mode = "list";
    private static final String doneMessage = "Nice! I've marked this task as done";
    private static final String gotMessage = "Got it. I've added this task:";

    static boolean terminate = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList myTasks = new TaskList();
        greet();
        while (!terminate) {
            respond(sc, myTasks);
        }
        sc.close();
    }

    private static void greet() {
        ArrayList<String> greeting = new ArrayList<>();
        greeting.add("Hello I'm Duke");
        greeting.add("What can I do for you?");
        printResponse(prettify(greeting));
    }

    // determines Duke's response and exit conditions:
    private static void respond(Scanner sc, TaskList myTasks) {
        Parser parser = new Parser();
        String input = sc.nextLine();
        String[] parsedOutput = parser.parseCommand(input);
        String command = parsedOutput[0];
        ArrayList<String> response = new ArrayList<>();

        if (command.equals("bye")) {
            response = exit();
        } else if(command.equals("list")) {
            response.add("Here are the tasks in your list:");
            ArrayList<Task> allTasks = myTasks.getAllTasks();
            for (Task t : allTasks) {
                response.add(t.getID() + "." + t.toString());
            }
        }

        else if (Duke.mode.equals("echo")) {
            response = echo(input);
        } else {
            response = handleTask(parsedOutput, input, myTasks);
        }
        ;
        printResponse(prettify(response));
    }
    ;



    private static ArrayList<String> handleTask(String[] parsedOutput, String description, TaskList tasks) {
        String command = parsedOutput[0];
        ArrayList<String> response = new ArrayList<>();
        String[] words = description.split(" ");

        if(command.equals("done")) {
            int taskID = Integer.parseInt(words[1]);
            response.add(doneMessage);
            response.add(tasks.completeTask(taskID));
        } else {
            response.add(gotMessage);
            String reply = tasks.addEntry(description);
            response.add(reply);
            response.add(tasks.getCurrentStatus());
        }
        return (response);
    }


    private static ArrayList<String> exit() {
        Duke.terminate = true;
        ArrayList<String> response = new ArrayList<>();
        response.add(exitGreeting);
        return (response);

    }

    private static ArrayList<String> echo(String output) {
        ArrayList<String> response = new ArrayList<>();
        response.add(output);
        return (response);
    }

    // encapsulates and indents response lines:
    private static ArrayList<String> prettify(ArrayList<String> rawResponse) {
        rawResponse.add(0, lineBreak);
        rawResponse.add(rawResponse.size(), lineBreak);
        return indentLines(rawResponse);
    }

    private static ArrayList<String> indentLines(ArrayList<String> responseLines) {
        ArrayList<String> result = new ArrayList<>();
        for (String current : responseLines) {
            current = indent + current;
            result.add(current);
        }
        return result;
    }

    private static void printResponse(ArrayList<String> response) {
        for (String s : response) {
            System.out.println(s);
        }
    }

    private static boolean isDoneAction(String[] words) {
        return words[0].equals("done") && words.length == 2;
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }


}



