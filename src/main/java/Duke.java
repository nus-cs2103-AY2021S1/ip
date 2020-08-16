import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // constant strings:
    private static final String horizontalLine = "____________________________________________________________";
    private static final String exitGreeting = "Bye. Hope to see you again soon!";
    private static final String indent = "    "; // set to 4 spaces

    static boolean exit = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while(!exit) {
            respond(sc);
        }
        sc.close();
    }


    // ================= HELPERS ======================
    private static void greet() {
        ArrayList<String> greeting = new ArrayList<>();
        greeting.add(horizontalLine);
        greeting.add("Hello I'm Duke");
        greeting.add("What can I do for you?");
        greeting.add(horizontalLine);
        printResponse(indentLines(greeting));
    }

    private static void respond(Scanner sc) {
        String input = sc.nextLine();
        ArrayList<String> response = new ArrayList<>();
        if(input.equals("bye")) {
            // ^dumb things: don't check pointer equality! use .equals
            Duke.exit = true;
            input = exitGreeting;
        }
        // insert horiz line before and after reponse msg:
        response.add(horizontalLine);
        response.add(input);
        response.add(horizontalLine);
        printResponse(indentLines(response));
    };

    private static void printResponse(ArrayList<String> response) {
        for (String s : response) {
            System.out.println(s);
        }
    }

    private static ArrayList<String> indentLines(ArrayList<String> responseLines) {
        ArrayList<String> result = new ArrayList<>();
        for (String current : responseLines) {
            current = indent + current;
            result.add(current);
        }
        return result;
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



