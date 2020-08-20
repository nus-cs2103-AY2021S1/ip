import java.util.Scanner;

public class Duke {
    static Task[] list = new Task[100];
    static int index = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
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
                    printlist();
                } else if (input.split(" ")[0].equals("done")) {
                    done(Integer.parseInt(input.split(" ")[1]));
                } else if (first.equals("todo")|| first.equals("deadline") || first.equals("event")) {
                  add(input);
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
            list[index] = newTask;
            index += 1;
            System.out.println("added: " + newTask.toString());
            System.out.println("Now you have "+index+" tasks in the list.");
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
            list[index] = newTask;
            index += 1;
            System.out.println("added: " + newTask.toString());
            System.out.println("Now you have "+index+" tasks in the list.");
        } else if (input.split(" ")[0].equals("event")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of event cannot be empty!");
            }
            String[] temp2 = input.split("/by",2);
            if (temp2.length <= 1){
                throw new DukeException("You need to specify a time!");
            }
            Task newTask = new Events(input.split(" ")[1], input.split("/at")[1]);
            list[index] = newTask;
            index += 1;
            System.out.println("added: " + newTask.toString());
            System.out.println("Now you have "+index+" tasks in the list.");
        }

    }

    public static void done(int num){
        list[num-1] = list[num-1].completedTask();
        System.out.println("Now you have "+index+" tasks in the list.");
    }

    public static void printlist(){
        int tempindex = 1;
        for (int k =0; list[k] != null; k++ ){
            System.out.println(tempindex + "." + list[k]);
            tempindex += 1;
        }
    }


}

