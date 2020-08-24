import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidNumberException;
import exceptions.UnknownCommandException;

import java.util.Scanner;

public class UI {
    Scanner sc;
    Storage storage;

    public UI(Storage store) {
        this.storage = store;
        this.sc = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println("_____________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("_____________________________");
    }

    public void run() {
        String input = sc.nextLine();
        TaskList list = storage.load();
        while (!input.equals("bye")) {
            input = input.trim().replaceAll("\\s{2,}", " ");
            System.out.println("_____________________________");
            if (input.equals("list")) {
                System.out.println(list);
                System.out.println("_____________________________");
                input = sc.nextLine();
                continue;
            }
            String[] request = input.split(" ");
            try {
                Parser.validity(input);
                if (request[0].equals("done")) {
                    Parser.update(request[1], list);
                } else if (request[0].equals("todo")) {
                    Parser.addTodo(input, list);
                } else if (request[0].equals("deadline")) {
                    try {
                        Parser.getDeadline(input, list);
                    } catch (InvalidDeadlineException ex) {
                        System.out.println("Oops, somewhere your deadline was wrong. Please check whether you used a /by tag");
                        System.out.println("_____________________________");
                    }
                } else if (request[0].equals("event")) {
                    try {
                        Parser.getEvent(input, list);
                    } catch (InvalidEventException ex) {
                        System.out.println("Oops seems like your event is invalid. Please check your /at tag");
                        System.out.println("_____________________________");
                    }
                } else if (request[0].equals("find")){
                    try {
                        Parser.find(input, list);
                    } catch (InvalidNumberException ex){
                        System.out.println("More than one keyword was entered");
                    }
                } else {
                    Parser.delete(request[1], list);
                }
            } catch (UnknownCommandException ex) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n_____________________________");
            }
            input = sc.nextLine();
        }
    }

    public void escape(){
        storage.save();
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!\n_____________________________");
    }
}