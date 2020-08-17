import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> arr = new ArrayList<>();
    static String s;

     static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("______________________");
        System.out.println(logo);
        System.out.println("welcome to my crib");
        System.out.println("______________________");
        s = sc.nextLine();
    }

    static void adios() {
        System.out.println("ok u can leave lmao");
        System.out.println("______________________");
    }

    static void ls() {
        for (int i = 0; i < arr.size(); i ++) {
            System.out.println(Integer.toString(i + 1) + ". " + arr.get(i).toString());
        }
        System.out.println("______________________");
        s = sc.nextLine();
    }

    static void del() {
        System.out.println("removed!! ^^");
        Task t = arr.remove(Integer.parseInt(s.substring(7)) - 1);
        System.out.println(t);
        System.out.println("total task: " + Integer.toString(arr.size()) + "\n:o");
        System.out.println("______________________");
        s = sc.nextLine();
    }

    static void finish() {
        System.out.println("gfy youve managed to finish the following...");
        Task t = arr.get(Integer.parseInt(s.substring(5)) - 1);
        t = t.completeTask();
        arr.set(Integer.parseInt(s.substring(5)) - 1, t);
        System.out.println(t);
        System.out.println("______________________");
        s = sc.nextLine();
    }

    static void handleTodo() throws IncompleteInputException {
         try {
             String name = s.substring(5);
             if (name.isBlank()) {
                 throw new IncompleteInputException();
             } else {
                 Task t = new Todo(name);
                 arr.add(t);
                 System.out.println("added!");
                 System.out.println(t);
                 System.out.println("total task: " + Integer.toString(arr.size()) + "\n:o");
                 System.out.println("______________________");
                 s = sc.nextLine();
             }
         } catch (StringIndexOutOfBoundsException e) {
             // command is incomplete
             throw new IncompleteInputException();
         }
    }

    static void handleDeadline() throws IncompleteInputException {
         try {
             String name = s.split("/")[0].substring(9);
             String deadline = s.split("/")[1].substring(3);
             if (name.isBlank() || deadline.isBlank()) {
                 throw new IncompleteInputException();
             } else {
                 Task t = new Deadline(name, deadline);
                 arr.add(t);
                 System.out.println("added!");
                 System.out.println(t);
                 System.out.println("total task: " + Integer.toString(arr.size()) + "\n:o");
                 System.out.println("______________________");
                 s = sc.nextLine();
             }
         } catch (StringIndexOutOfBoundsException e) {
             // command is incomplete
             throw new IncompleteInputException();
         } catch (ArrayIndexOutOfBoundsException e) {
             // command is incomplete
             throw new IncompleteInputException();
         }
    }

    static void handleEvent() throws IncompleteInputException {
        try {
            String name = s.split("/")[0].substring(6);
            String time = s.split("/")[1].substring(3);
            if (name.isBlank() || time.isBlank()) {
                throw new IncompleteInputException();
            } else {
                Task t = new Event(name, time);
                arr.add(t);
                System.out.println("added!");
                System.out.println(t);
                System.out.println("total task: " + Integer.toString(arr.size()) + "\n:o");
                System.out.println("______________________");
                s = sc.nextLine();
            }
        } catch (StringIndexOutOfBoundsException e) {
            // command is incomplete
            throw new IncompleteInputException();
        } catch (ArrayIndexOutOfBoundsException e) {
            // command is incomplete
            throw new IncompleteInputException();
        }
    }

    static void talk() throws UnknownInputException {
        while (1 == 1) {
            try {
                if (s.equals("bye")) {
                    adios();
                    break;
                } else if (s.equals("list")) {
                    ls();
                } else if (s.startsWith("done")) {
                    finish();
                } else if (s.startsWith("delete")) {
                    del();
                } else if (s.startsWith("todo")) {
                    handleTodo();
                } else if (s.startsWith("deadline")) {
                    handleDeadline();
                } else if (s.startsWith("event")) {
                    handleEvent();
                } else { // unknown input
                    throw new UnknownInputException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("______________________");
                s = sc.nextLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        greet();

        talk();
    }
}
