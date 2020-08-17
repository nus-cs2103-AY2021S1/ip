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
        while (processLine(sc)){}
    }

    public static boolean processLine(Scanner sc) {
        String str;
        str = sc.nextLine();
        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(str.split(" ")));
        boolean bl;
        System.out.println("-----------------");
        if (tokens.get(0).equals("bye")){
            System.out.println("Bye. Hope to see you again!");
            bl = false;
        } else {
            if (tokens.get(0).equals("done")) {
                int ind = Integer.parseInt(tokens.get(1))-1;
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
        System.out.println("-----------------");
        return bl;

    }

    public static Task addNewTask(ArrayList<String> tokens) {
        if (tokens.get(0).equals("todo")) {
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
            return new Deadline(stringCombiner(tokens, 1, ind),
                    stringCombiner(tokens, ind+1, tokens.size()));
        } else {
            int ind = 0;
            boolean found= false;
            while (!found && ind < tokens.size()-1) {
                ind++;
                if (tokens.get(ind).equals("/at")) {
                    found = true;
                }
            }
            return new Event(stringCombiner(tokens, 1, ind),
                    stringCombiner(tokens, ind+1, tokens.size()));
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
