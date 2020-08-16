import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // constant strings:
    private static final String lineBreak = "____________________________________________________________";
    private static final String exitGreeting = "Bye. Hope to see you again soon!";
    private static final String indent = "    ";
    private static final String mode = "list";

    static boolean terminate = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList allTasks = new TaskList();
        greet();
        while(!terminate) {
            respond(sc, allTasks);
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
    private static void respond(Scanner sc, TaskList allTasks) {
        String input = sc.nextLine();
        ArrayList<String> response = new ArrayList<>();

        if(input.equals("bye")) {
            response = exit();
        } else if(Duke.mode.equals("echo")) {
            response = echo(input);
        } else {
            response = handleTask(input, allTasks);
        }
        ;
        printResponse(response);
    };

    private static ArrayList<String> handleTask(String description, TaskList tasks) {
        ArrayList<String> response = new ArrayList<>();
        // enumerates all tasks:
        if (description.equals("list")) {
            ArrayList<Task> allTasks = tasks.getAllTasks();
            for(Task t : allTasks) {
                response.add(t.toString());
            }
        } else {
            String reply = tasks.addEntry(description);
            response.add(reply);
        }
        return prettify(response);
    }

    private static ArrayList<String> exit() {
        Duke.terminate = true;
        ArrayList<String> response = new ArrayList<>();
        response.add(exitGreeting);
        return prettify(response);

    }

    private static ArrayList<String> echo(String output) {
        ArrayList<String> response = new ArrayList<>();
        response.add(output);
        return prettify(response);
    }

    // encapsulates and indents response lines:
    private static ArrayList<String> prettify(ArrayList<String> rawResponse){
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



    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }



}



