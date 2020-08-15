import java.util.ArrayList;
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
        String[] tokens = str.split(" ");
        boolean bl;
        System.out.println("-----------------");
        if (tokens[0].equals("bye")){
            System.out.println("Bye. Hope to see you again!");
            bl = false;
        } else{
            if (tokens[0].equals("done")){
                int ind = Integer.parseInt(tokens[1])-1;
                tasks.get(ind).completeTask();
                System.out.println(tasks.get(ind).toString());
            }else if (tokens[0].equals("list")){
                System.out.println("Here are the tasks in your list:");
                int i=0;
                for(Task task:tasks){
                    System.out.println(String.format("%d.%s", ++i, task));
                }
            }else{
                System.out.println("Added: "+str);
                tasks.add(new Task(str));

            }
            bl = true;
        }
        System.out.println("-----------------");
        return bl;

    }
}
