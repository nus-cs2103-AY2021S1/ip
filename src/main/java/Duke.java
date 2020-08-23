import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    
    public static void readTasksFromFile() {
        try {
            File f = new File("data/data.txt");
            Scanner diskScanner = new Scanner(f);
            while (diskScanner.hasNext()) {
                System.out.println(diskScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void writeListToDataFile() {
        try {
            String filePath = "data/data.txt";
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) { 
                fw.write (String.format ("%d.%s \n", i + 1, tasks.get(i)));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println ("Something went wrong: " + e.getMessage());
        }
    }

    public static void sort (String next){
        try {
            if (next.equalsIgnoreCase("list")) {      //Listing out all the tasks
                list();

            } else if (next.startsWith("done ")) {    //When a task is done
                done(next);
                writeListToDataFile();

            } else if (next.startsWith("delete")) {
                delete(next);
                writeListToDataFile();

            } else {        //Adding a to do, deadline, event
                tasks(next);
                writeListToDataFile();
            }

        } catch (IllegalArgumentException e) {
            System.out.println ("Oops Sorry! >_< I don't know what that means... ");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please specify a time!");
        } catch (DukeException e) {
            System.out.println ("Description of a task cannot be empty!!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println ("Sorry that task does not exist");
        }
    }


    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s \n", i + 1, tasks.get(i));
        }
    }


    public static void done(String s) { 
        int index = Integer.parseInt(s.replaceAll("[^0-9]", ""));
        Task t = tasks.get (index - 1);
        t.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }


    public static void tasks(String s) throws DukeException, IllegalArgumentException {
        Task task;
        if (s.contains("todo")) {
            if (s.substring(4).trim().length() == 0) {
                throw new DukeException();
            }
            task = new Todo(s.substring(5));

        } else if (s.contains("deadline")) {
            String[] str = s.split("/");
            if (str[0].substring(8).trim().length() == 0) {
                throw new DukeException();
            }
            task = new Deadline(str[0].substring(9), str[1].substring(3));

        } else if (s.contains ("event")) {
            String[] str = s.split ("/");
            if (str[0].substring(5).trim().length() == 0) {
                throw new DukeException();
            }
            task = new Event (str[0].substring(6), str[1].substring(3));

        } else {
            throw new IllegalArgumentException();
        }

        System.out.println ("Got it. I've added this task:");
        tasks.add(task);
        System.out.println(task);
        System.out.printf ("Now you have %d tasks in the list.\n", tasks.size());
    }


    public static void delete(String s) {
        int index = Integer.parseInt(s.replaceAll("[^0-9]", ""));
        Task t = tasks.get(index - 1);
        System.out.println ("Noted. I've removed this task:");
        System.out.println (t);
        tasks.remove (index - 1);
        System.out.printf ("Now you have %d tasks in the list.\n", tasks.size());
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
        System.out.println ("Here are your tasks from last time");
        readTasksFromFile();

        String next = sc.nextLine();

        while (!next.equalsIgnoreCase("bye")){
            sort(next);
            next = sc.nextLine();
        }

        //Exit
        System.out.println ("Bye! Hope to see you again soon! :)");
    }
}

