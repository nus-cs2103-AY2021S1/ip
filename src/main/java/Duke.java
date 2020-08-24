import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static String INDENT = "    ";
    static String divider = INDENT + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";

    static void start() {
        String logo =
                                " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                                        "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                                        "| |  ________    | || |     ____     | || |     ____     | || |  ___  ____   | |\n" +
                                        "| | |_   ___ `.  | || |   .'    `.   | || |   .'    `.   | || | |_  ||_  _|  | |\n" +
                                        "| |   | |   `. \\ | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |_/ /    | |\n" +
                                        "| |   | |    | | | || |  | |    | |  | || |  | |    | |  | || |   |  __'.    | |\n" +
                                        "| |  _| |___.' / | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  \\ \\_  | |\n" +
                                        "| | |________.'  | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                                        "| |              | || |              | || |              | || |              | |\n" +
                                        "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                                        " '----------------'  '----------------'  '----------------'  '----------------'\n";
        String intro =
                "\n" +
                "\n" +
                logo +
                "\n" +
                divider +
                INDENT +
                "Hola! I am dook\n" +
                INDENT +
                "how i can help u?\n" + divider;
        System.out.println(intro);
    }
    static void print(String str){
        String[] arr = str.split("\n");
        String res = "";
        for(int i = 0; i < arr.length; i++ ){
            res += INDENT + arr[i] + "\n";
        }
        String intro = divider +  res + divider;
        System.out.println(intro);
    }
    static void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        print("Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
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
    static LocalDateTime parseDateTime(String s) {
        // s will be in the format : yyyy-mm-dd HHmm
        // return format : yyyy-mm-ddTHH:mm
        String[] dateTimeSplit = s.split(" ");
        String date = dateTimeSplit[0];
        String hour = dateTimeSplit[1].substring(0, 2);
        String min = dateTimeSplit[1].substring(2);
        return LocalDateTime.parse(date + "T" + hour + ":" + min);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialise
        start();
        List<Task> tasks = new ArrayList<>();

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    displayList(tasks);
                } else if (input.matches("done.*")) {
                    if(input.matches("done\\s*")) {
                        throw new EmptyArgumentException("Task Index");
                    }
                    if(!input.matches("done \\d+")) {
                        throw new InvalidArgumentException("Task Index");
                    }
                    String[] arr = input.split(" ");
                    int index = Integer.parseInt(arr[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        throw new InvalidArgumentException("index");
                    }
                    tasks.get(index).markAsDone();
                    print("Nice! I've marked this task as done:\n" + tasks.get(index));
                } else if (input.matches("todo.*")) {
                    if(input.matches("todo\\s*")) {
                        throw new EmptyArgumentException("todo's description");
                    }
                    addTask(new Todo(input.substring(5)), tasks);
                } else if (input.matches("deadline.*")) {
                    if(input.matches("deadline\\s*")) {
                        throw new EmptyArgumentException("deadline's description");
                    }
                    if(!input.matches("deadline .+/by \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
                        throw new InvalidArgumentException("deadline's description (proper date format: yyyy-mm-dd HHmm)");
                    }
                    String[] split = input.substring(9).split("/by");
                    String dateTimeString = split[1].stripLeading();
                    LocalDateTime dateTime = parseDateTime(dateTimeString);
                    addTask(new Deadline(split[0].stripTrailing(), dateTime), tasks);
                } else if (input.matches("event.*")) {
                    if(input.matches("event\\s*")) {
                        throw new EmptyArgumentException("event's description");
                    }
                    if(!input.matches("event .+/at \\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
                        throw new InvalidArgumentException("event description (proper date format: yyyy-mm-dd HHmm)");
                    }
                    String[] split = input.substring(6).split("/at");
                    String dateTimeString = split[1].stripLeading();
                    addTask(new Event(split[0].stripTrailing(), parseDateTime(dateTimeString)), tasks);
                } else if (input.matches("delete.*")) {
                    if(input.matches("delete\\s*")) {
                        throw new EmptyArgumentException("Task Index");
                    }
                    if(!input.matches("delete \\d+")) {
                        throw new InvalidArgumentException("Task Index");
                    }
                    String[] arr = input.split(" ");
                    int index = Integer.parseInt(arr[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        throw new InvalidArgumentException("index");
                    }
                    Task deleted = tasks.remove(index);
                    print("Noted. I've removed this task:\n  " + deleted.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new InvalidCommandException();
                }
            } catch (Exception e) {
                print(e.toString());
            }

            input = sc.nextLine();
        }
        print("see u later alligator");
    }
}
