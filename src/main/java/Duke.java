import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
<<<<<<< .merge_file_a01804

    static List<Task> list = new ArrayList<>();
    static int index = 0;
=======
    static List<Task> list = new ArrayList<>();
<<<<<<< HEAD
>>>>>>> .merge_file_a07988

=======
    static final String line = "--------------------------------------------------"; //50 dashes
>>>>>>> 32c6ef0793bb972d423637fb0338749ea00d32a7

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        bot();
    }

    public static void bot(){
        Scanner sc = new Scanner(System.in);

        try {
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String first = input.split(" ")[0];
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    printList();
                } else if (input.split(" ")[0].equals("done")) {
                    done(Integer.parseInt(input.split(" ")[1]));
                } else if (first.equals("todo")|| first.equals("deadline") || first.equals("event")) {
                    add(input);
                } else if (first.equals("delete")){
                    delete(input);
                } else {
                    throw new DukeException("Sorry I don't know what you mean");
                }
            }
        }
        catch (DukeException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void add(String input) throws DukeException{

        if (input.split(" ")[0].equals("todo")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of todo cannot be empty!");
            }
            Task newTask = new ToDo(input.split(" ")[1]);
            list.add(newTask);
            System.out.println("added: " + newTask.toString());
            System.out.println("Now you have "+list.size()+" tasks in the list.");
        } else if (input.split(" ")[0].equals("deadline")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of deadline cannot be empty!");
            }
            String[] temp2 = input.split("/by",2);
            if (temp2.length <= 1){
                throw new DukeException("You need to specify a time!");
            }
            Task newTask = new Deadlines(input.split(" ")[1], input.split("/by")[1]);
            list.add(newTask);
            System.out.println("added: " + newTask.toString());
            System.out.println("Now you have "+list.size()+" tasks in the list.");
        } else if (input.split(" ")[0].equals("event")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of event cannot be empty!");
            }
            String[] temp2 = input.split("/at",2);
            if (temp2.length <= 1){
                throw new DukeException("You need to specify a time!");
            }
            Task newTask = new Events(input.split(" ")[1], input.split("/at")[1]);
            list.add(newTask);
            System.out.println("added: " + newTask.toString());
            System.out.println("Now you have "+list.size()+" tasks in the list.");
        }
        System.out.println(line);

    }

    public static void delete(String input){
        int num = Integer.parseInt(input.split(" ")[1]);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(num-1).toString());
        list.remove(num-1);
        System.out.println("Now you have "+list.size()+" tasks in the list.");
        System.out.println(line);
    }

    public static void done(int num){
        list.set(num-1, list.get(num-1).completedTask());
        System.out.println("Now you have "+list.size()+" tasks in the list.");
<<<<<<< HEAD
<<<<<<< .merge_file_a01804
=======
        System.out.println(line);
>>>>>>> 32c6ef0793bb972d423637fb0338749ea00d32a7
    }

    public static void printList(){
        int tempIndex = 1;
        for (Task x: list){
            System.out.println(tempIndex + "." + x.toString());
            tempIndex += 1;
=======
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printlist();
            } else {
                add(input);
            }
        }
        System.out.println(line);
    }

    public static void add(String input){
        list[index] = input;
        index += 1;
        System.out.println("added: " + input);
    }

    public static void printlist(){
        int tempindex = 1;
        for (int k =0; list[k] != null; k++ ){
            System.out.println(tempindex + ". " + list[k]);
            tempindex += 1;
>>>>>>> master
=======
    }

    public static void printList(){
        int tempIndex = 1;
        for (Task x: list){
            System.out.println(tempIndex + "." + x.toString());
            tempIndex += 1;
>>>>>>> .merge_file_a07988
        }
    }


}
