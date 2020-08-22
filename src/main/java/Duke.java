import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("_____________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("_____________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        TaskList list = new TaskList();
        while(!input.equals("bye")){
            System.out.println("_____________________________");
            if (input.equals("list")){
                System.out.println(list);
                System.out.println("_____________________________");
                input = sc.nextLine();
                continue;
            }
            String[] request = input.split(" ");
            try{
                boolean keep = validity(input);
                if (request[0].equals("done")){
                    try {
                        int index = Integer.parseInt(request[1]);
                        isNumeric(index, list);
                        list.updateStatus(index);
                        System.out.println("Nice! I've marked this task as done: \n " + list.get(index));
                        System.out.println("Now you have " + list.getSize() + " tasks in the list \n_____________________________");
                        input = sc.nextLine();
                    } catch (NumberFormatException e){
                        System.out.println("You have not provided a valid number\n_____________________________");
                        input = sc.nextLine();
                    } catch (InvalidNumberException e){
                        System.out.println("The number provided was greater or lesser than the number of items in the list\n_____________________________");
                        input = sc.nextLine();
                    }
                } else if (request[0].equals("todo")){
                    try {
                        ToDos todo = getTodo(input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo);
                        list.update(todo);
                        System.out.println("Now you have " + list.getSize() + " tasks in the list.\n_____________________________");
                        input = sc.nextLine();
                    } catch (EmptyTodoException ex){
                        System.out.println("Oops!!! I'm sorry, but the description of a todo cannot be empty\n_____________________________");
                        input = sc.nextLine();
                    }
                } else if (request[0].equals("deadline")){
                    try {
                        Deadline deadline = getDeadline(input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline);
                        list.update(deadline);
                        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
                        System.out.println("_____________________________");
                        input = sc.nextLine();
                    } catch (InvalidDeadlineException ex){
                        System.out.println("Oops, somewhere your deadline was wrong. Please check whether you used a /by tag");
                        System.out.println("_____________________________");
                        input = sc.nextLine();
                    }
                } else if (request[0].equals("event")){
                    try {
                        Event event = getEvent(input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event);
                        list.update(event);
                        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
                        System.out.println("_____________________________");
                        input = sc.nextLine();
                    } catch (InvalidEventException ex){
                        System.out.println("Oops seems like your event is invalid. Please check your /at tag");
                        System.out.println("_____________________________");
                        input = sc.nextLine();
                    }
                } else {
                    try {
                        int index = Integer.parseInt(request[1]);
                        isNumeric(index, list);
                        System.out.println("The event has been removed as per your request: \n " + list.get(index));
                        list.delete(index);
                        System.out.println("Now you have " + list.getSize() + " tasks in the list+ \n_____________________________");
                        input = sc.nextLine();
                    } catch (NumberFormatException e){
                        System.out.println("You have not provided a valid number\n_____________________________");
                        input = sc.nextLine();
                    } catch (InvalidNumberException e){
                        System.out.println("The number provided was greater or lesser than the number of items in the list\n_____________________________");
                        input = sc.nextLine();
                    }
                }
            } catch (UnknownCommandException ex) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n_____________________________");
                input = sc.nextLine();
            }
        }

        try {
            File file = new File("Data/duke.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(list.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("No File found");
        }
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!\n_____________________________");
    }

    public static void isNumeric(int n, TaskList list) throws InvalidNumberException{
        if (list.getSize() < n || n <= 0){
            throw new InvalidNumberException("The number provided is bigger tha the list size");
        }
    }

    public static Deadline getDeadline(String word) throws InvalidDeadlineException{
        if (word.contains("/by") && !word.substring(word.indexOf("/by") + 3).equals("")){
            return new Deadline(word.substring(8));
        } else {
            throw new InvalidDeadlineException();
        }
    }

    public static Event getEvent(String word) throws InvalidEventException{
        if (word.contains("/at") && !word.substring(word.indexOf("/at") + 3).equals("")){
            return new Event(word.substring(5));
        } else {
            throw new InvalidEventException();
        }
    }

    public static boolean validity(String line) throws UnknownCommandException{
        ArrayList<String> list = new ArrayList<>(Arrays.asList("delete", "done", "todo", "event", "deadline"));
        String[] words = line.split(" ");
        if (list.contains(words[0]) && words.length > 1){
            return true;
        } else {
            throw new UnknownCommandException();
        }
    }

    public static ToDos getTodo(String work) throws EmptyTodoException{
        if (work.length() > 4){
            return new ToDos(work.substring(4));
        } else {
            throw new EmptyTodoException();
        }
    }

    public static void print(ArrayList<Task> list){
        for (int i = 0; i < list.size(); i++){
            System.out.println("" + (i + 1) + "." + list.get(i));
        }
    }
}
