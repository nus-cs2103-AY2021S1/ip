import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    // constant strings:
    private static final String lineBreak = "____________________________________________________________";
    private static final String indent = "    ";
    private static final String mode = "list";
    
    static boolean canExit = false;
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        TaskList myTasks = Storage.load();
        greet();
        while (!canExit) {
            respond(sc, myTasks);
        }
        sc.close();
        Storage.save(myTasks);
    }
    
    private static void greet() {
        ArrayList<String> greeting = new ArrayList<>();
        greeting.add("Hello I'm Duke");
        greeting.add("What can I do for you?");
        printResponse(prettify(greeting));
    }
    
    private static void respond(Scanner sc, TaskList myTasks) {
        Parser parser = new Parser();
        String input = sc.nextLine();
        ArrayList<String> response = new ArrayList<>();
        try {
            String[] parsedOutput = parser.parseCommand(input);
            String command = parsedOutput[0];
            if (command.equals(Command.EXIT_CMD.getCmd())) {
                response = exit();
            } else if (command.equals(Command.LIST_CMD.getCmd())) {
                response.add(Message.FETCHING_MSG.getMsg());
                ArrayList<Task> allTasks = myTasks.getAllTasks();
                for (Task t : allTasks) {
                    response.add(t.getID() + "." + t.toString());
                }
            } else if (Duke.mode.equals(Command.ECHO_MODE.getCmd())) {
                response = echo(input);
            } else {
                response = handleTask(parsedOutput, myTasks);
            }
        } catch (DukeException e) {
            response.add(e.getMessage());
        } finally {
            printResponse(prettify(response));
        }
    }
    
    
    private static ArrayList<String> handleTask(String[] parsedOutput, TaskList tasks) throws DukeException {
        String command = parsedOutput[0];
        ArrayList<String> response = new ArrayList<>();
        if (command.equals(Command.DELETE_CMD.getCmd()) || command.equals(Command.DONE_CMD.getCmd())) {
            boolean toDelete = command.equals(Command.DELETE_CMD.getCmd());
            int taskID = Integer.parseInt(parsedOutput[1]);
            if (toDelete) {
                response.add(Message.DELETE_MSG.getMsg());
                response.add(tasks.deleteTask(taskID));
                response.add(tasks.getCurrentStatus());
            } else {
                response.add(Message.DONE_MSG.getMsg());
                response.add(tasks.completeTask(taskID));
            }
        } else {
            response.add(Message.CONFIRMATION_MSG.getMsg());
            String reply = tasks.addEntry(parsedOutput);
            response.add(reply);
            response.add(tasks.getCurrentStatus());
        }
        return response;
    }
    
    
    private static ArrayList<String> exit() {
        Duke.canExit = true;
        ArrayList<String> response = new ArrayList<>();
        response.add(Message.EXIT_GREETING.getMsg());
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
    
    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }
    
}




