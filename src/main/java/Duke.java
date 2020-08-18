import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<Task>(100);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        boolean hasNext = true;
        while (hasNext){
            System.out.println("-----------------");
            try{
                hasNext = processLine(sc);
            }  catch(DukeException ex) {
                System.out.println(ex);
            }
            System.out.println("-----------------");

        }
    }

    public static boolean processLine(Scanner sc) throws DukeException{
        String str;
        str = sc.nextLine();
        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(str.split(" ")));
        if (tokens.size()==0||tokens.get(0).equals("")){
            throw new EmptyCommandException();
        }
        boolean bl;
        if (tokens.get(0).equals("bye")){
            System.out.println("Bye. Hope to see you again!");
            bl = false;
        } else {
            if (tokens.get(0).equals("done")) {
                if (tokens.size() < 2) {
                    throw new MissingArgumentException("Must provide number after done!");
                }
                int ind;
                try{
                ind = Integer.parseInt(tokens.get(1))-1;
                } catch(Exception ex) {
                    throw new InvalidCommandException(tokens.get(1)+" is not a number!");
                }
                if (tasks.size()<=ind || ind < 0) {
                    throw new DukeException("Task "+ tokens.get(1) +" does not exist!");
                }
                tasks.get(ind).completeTask();
                System.out.println(tasks.get(ind).toString());
            } else if (tokens.get(0).equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int i=0;
                for(Task task:tasks){
                    System.out.println(String.format("%d.%s", ++i, task));
                }
            } else {
                Task task = addNewTask(tokens);
                tasks.add(task);
                System.out.println(
                        String.format("I have added this task: \n\t%s\nYou now have %d tasks in the list",
                                task.toString(), tasks.size()));
            }
            bl = true;
        }
        return bl;

    }

    public static Task addNewTask(ArrayList<String> tokens) throws DukeException {
        if (tokens.get(0).equals("todo")) {
            if (tokens.size() < 2) {
                throw new MissingArgumentException("todo cannot be empty!");
            }
            return new ToDo(stringCombiner(tokens, 1, tokens.size()));
        } else if (tokens.get(0).equals("deadline")) {

            int ind = 0;
            boolean found= false;
            while (!found && ind < tokens.size()-1) {
                ind++;
                if (tokens.get(ind).equals("/by")) {
                    found = true;
                }
            }
            if (!found) {
                throw new MissingArgumentException("Deadline need an /by time!");
            }
            if (ind == 1) {
                throw new MissingArgumentException("Deadline needs a name!");
            }
            if (ind == tokens.size()-1) {
                throw new MissingArgumentException("Deadline needs a deadline time!");
            }
            return new Deadline(stringCombiner(tokens, 1, ind),
                    stringCombiner(tokens, ind+1, tokens.size()));
        } else if (tokens.get(0).equals("event")){
            int ind = 0;
            boolean found= false;
            while (!found && ind < tokens.size()-1) {
                ind++;
                if (tokens.get(ind).equals("/at")) {
                    found = true;
                }
            }
            if (!found) {
                throw new MissingArgumentException("Event need an /at time!");
            }
            if (ind == 1) {
                throw new MissingArgumentException("Event needs a name!");
            }
            if (ind == tokens.size()-1) {
                throw new MissingArgumentException("Event needs a event time!");
            }
            return new Event(stringCombiner(tokens, 1, ind),
                    stringCombiner(tokens, ind+1, tokens.size()));
        } else {
            throw new InvalidCommandException("I do not recognise this command!");
        }
    }

    public static String stringCombiner(ArrayList<String> tokens, int start, int end) {
        StringBuilder str = new StringBuilder();
        for (int i = start; i < end; i++) {
            str.append(tokens.get(i));
            if (i != end - 1) {
                str.append(" ");
            }
        }
        return str.toString();
    }
}
