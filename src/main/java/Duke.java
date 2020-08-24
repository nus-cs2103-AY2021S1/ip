import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Duke {

    public static void doneTask(ArrayList<Task> store, int num) {
        store.get(num-1).markAsDone();
        System.out.println("Nice! This task is marked as done!");
        System.out.println(store.get(num-1));
    }

    public static void main(String[] args) throws DukeException, IOException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();
        String file2 = "dataa";
        try {
            ReadFile.updateContents(file2, store);
        } catch (FileNotFoundException e) {
            System.out.println(new DukeException("file not found"));
        }

        System.out.println("Hello! I'm meimei ^_^\nI could scream at you all day!");
        String command = sc.nextLine();

        while(!command.equals("bye")) {
            String key = command.split(" ",2)[0];
            if (key.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < store.size(); i++) {
                    System.out.println((i+1) + "." + store.get(i).toString());
                }
                command = sc.nextLine();
            }
            else if (key.equals("done")) {
//                int k = Integer.parseInt(command.split(" ")[1]);
//                store.get(k-1).markAsDone();
//                System.out.println("Nice! This task is marked as done!");
//                System.out.println(store.get(k-1));
                doneTask(store, Integer.parseInt(command.split(" ")[1]));
                command = sc.nextLine();
            }
            else if (key.equals("delete")) {
                int k = Integer.parseInt(command.split(" ")[1]);
                Task remov = store.get(k-1);
                store.remove(remov);
                System.out.println("Meimei will forget about this task!");
                System.out.println(remov);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                command = sc.nextLine();
            }
            //adding of task
            else {
                try {
                    Task.addTask(command, store, true);
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    if (key.equals("todo")) {
                        System.out.println(new DukeException("todo"));
                    }
                    else if (key.equals("deadline")) {
                        System.out.println(new DukeException("deadline"));
                    }
                    else if (key.equals("event")) {
                        System.out.println(new DukeException("event"));
                    } else {
                        System.out.println("Oops! Meimei does not understand this command");
                    }
                }
                command = sc.nextLine();
            }
        }
        if (store.isEmpty()) {
            WriteFile.writeToFile(file2, "");
        } else {
            for (int i = 0; i < store.size(); i++) {
                if (i == 0) {
                    WriteFile.writeToFile(file2, store.get(i).inputStyle());
                } else {
                    WriteFile.appendToFile(file2, System.lineSeparator() + store.get(i).inputStyle());
                }
            }
        }
        System.out.println("Bye. Meimei will miss you!");
    }
}

