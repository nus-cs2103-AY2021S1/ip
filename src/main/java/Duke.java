import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static String divider = "____________________________________________________________\n";

    static void start() {
        String logo =
                                " .----------------.  .----------------.  .----------------.  .----------------. \n" +
                                        "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                                        "| |  ________    | || |     ____     | || |     ____     | || |  ___  ____   | |\n" +
                                        "| | |_   ___ `.  | || |   .'    `.   | || |   .'    `.   | || | |_  ||_  _|  | |\n" +
                                        "| |   | |   `. \\ | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |_/ /    | |\n" +
                                        "| |   | |    | | | || |  | |    | |  | || |  | |    | |  | || |   |  __'.    | |\n" +
                                        "| |  _| |___.' / | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  \\ \\_  | |\n" +
                                        "| | |________.'  | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                                        "| |              | || |              | || |              | || |              | |\n" +
                                        "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                                        " '----------------'  '----------------'  '----------------'  '----------------' \n";
        String intro =
                "\n" +
                "\n" +
                logo +
                "\n" +
                divider +
                "Hola! I am dook\n" +
                "how i can help u?\n" + divider;
        System.out.println(intro);
    }
    static void print(String str){
        String intro = divider + str + "\n" + divider;
        System.out.println(intro);
    }
    static void addTask(String str, List<Task> taskList) {
        taskList.add(new Task(str));
        print("added: " + str);
    }
    static void displayList(List<Task> list) {
        if(list.size() == 0) {
            print("No task added yet!");
        } else {
            String result = "Here are the tasks in your list:\n";
            for(int i = 0; i < list.size(); i++) {
                result += "" + (i + 1)+ ". " + list.get(i).toString() + ( i + 1 == list.size() ? "" : "\n");
            }
            print(result);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialise
        start();
        List<Task> tasks = new ArrayList<>();

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                displayList(tasks);
            } else if (input.matches("done \\d")) {
                String[] arr = input.split(" ");
                int index = Integer.parseInt(arr[1]) - 1;
                if( index < tasks.size() && index > -1) {
                    tasks.get(index).markAsDone();
                    print("Nice! I've marked this task as done:\n" + tasks.get(index));
                } else {
                    print("Invalid Index Bro");
                }
            } else {
                addTask(input, tasks);
            }

            input = sc.nextLine();
        }
        print("see u later alligator");
    }
}
