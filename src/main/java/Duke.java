import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void sort (String next){
        try {
            if (next.equalsIgnoreCase("list")) {      //Listing out all the tasks
                list();

            } else if (next.startsWith("done ")) {    //When a task is done
                done(next);

            } else if (next.startsWith("delete")) {
                delete(next);

            } else {        //Adding a to do, deadline, event
                tasks(next);
            }

        } catch (IllegalArgumentException e) {
            System.out.println ("Oops Sorry! >_< I don't know what that means... ");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a time!");
        } catch (DukeException e) {
            System.out.println ("Description of a task cannot be empty!!");
        } 
    }


    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s \n", i + 1, tasks.get(i));
        }
    }


    public static void done(String s) {
        try {
            int index = Integer.parseInt(s.replaceAll("[^0-9]", ""));
            tasks.get(index - 1).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index - 1));

        } catch (IndexOutOfBoundsException e){
            System.out.println ("Sorry that task does not exist");
        }
    }


    public static void tasks(String s) throws DukeException, IllegalArgumentException{
        Task task;
        if (s.contains("todo")) {
            if (s.substring(4).trim().length() == 0) {
                throw new DukeException();
            }
            task = new Todo(s.substring(5));

        } else if (s.contains("deadline")) {
            String[] str = s.split("/by");
            if (str[0].substring(8).trim().length() == 0) {
                throw new DukeException();
            }
            task = new Deadline(str[0].substring(9), str[1].trim());

        } else if (s.contains ("event")) {
            String[] str = s.split ("/at");
            if (str[0].substring(5).trim().length() == 0) {
                throw new DukeException();
            }
            task = new Event (str[0].substring(6), str[1].trim());

        } else {
            throw new IllegalArgumentException();
        }

        System.out.println ("Got it. I've added this task:");
        tasks.add(task);
        System.out.println(task);
        System.out.printf ("Now you have %d tasks in the list.\n", tasks.size());
    }


    public static void delete(String s) {
        try {
            int index = Integer.parseInt(s.replaceAll("[^0-9]", ""));
            System.out.println ("Noted. I've removed this task:");
            System.out.println (tasks.get(index - 1));
            tasks.remove (index - 1);
            System.out.printf ("Now you have %d tasks in the list.\n", tasks.size());


        } catch (IndexOutOfBoundsException e){
            System.out.println ("Sorry that task does not exist");
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner (System.in);

        //Greeting the user
        System.out.println ("Hello! I'm Duke  ^_^");
        System.out.println ("What can I do for you??");

        String next = sc.nextLine();

        while (!next.equalsIgnoreCase("bye")){
            sort(next);
            next = sc.nextLine();
        }

        //Exit
        System.out.println ("Bye! Hope to see you again soon! :)");
    }
}

