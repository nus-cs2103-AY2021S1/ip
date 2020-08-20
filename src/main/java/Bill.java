import java.util.*;

public class Bill {
    
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    
    private static final String horizontal_line = "------------------------------------------";
    private static final List<Task> list_of_Content = new ArrayList<>();
    
    private static void welcome_message() {
        System.out.println("Hello from Bill \n" + logo);
        System.out.println(horizontal_line);
        System.out.println("Hello! I'm Bill ");
        System.out.println("What Can I do for you? ");
        System.out.println(horizontal_line);
    }
    
    private static void session_end() {
        System.out.println(horizontal_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontal_line);
    }
    
    private static void print_the_list() {
        
        int no_of_tasks = list_of_Content.size();

        System.out.println(horizontal_line);
        if (no_of_tasks == 0) {
            System.out.println("There is no tasks in the list, please add some tasks first ");
        } else {
            for (int i = 0; i < list_of_Content.size(); i = i + 1) {
                String counter = Integer.toString(i + 1) + ". ";
                System.out.println(counter + list_of_Content.get(i));
            }
        }
        System.out.println(horizontal_line);
    }
    
    public static void main(String[] args) {
        
        welcome_message();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    session_end();
                    sc.close();
                    break;
                } else {
                    
                    int length = input.length();
                    String[] isDone = input.split(" ");
                    String firstChar = isDone[0];
                    if (input.equals("list")) {
                        System.out.println("Here are the tasks in your list: ");
                        print_the_list();
                    } else if (firstChar.equals("done")) {

                        System.out.println("reach done");
                        String lastChar = isDone[isDone.length - 1];
                        int index = Integer.parseInt(lastChar);
                        Task current = list_of_Content.get(index - 1);
                        current.set_Task_As_Done();
                        System.out.println(horizontal_line);
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(current);
                        System.out.println(horizontal_line);

                    } else {
                        if (firstChar.equals("todo")) {
                            ToDo new_task = new ToDo(input.substring(firstChar.length() + 1));
                            list_of_Content.add(new_task);
                            System.out.println(horizontal_line);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(new_task);
                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
                            System.out.println(horizontal_line);
                        } else if (firstChar.equals("deadline")) {
                            int index = input.indexOf("/by");
                            String task = input.substring(firstChar.length() + 1, index);
                            String time = input.substring(index + 4);
                            Deadline deadline = new Deadline(task, time);
                            list_of_Content.add(deadline);
                            System.out.println(horizontal_line);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(deadline);
                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
                            System.out.println(horizontal_line);
                        } else if (firstChar.equals("event")) {
                            int index = input.indexOf("/at");
                            String task = input.substring(firstChar.length() + 1, index);
                            String duration = input.substring(index + 4);
                            Event event = new Event(task, duration);
                            list_of_Content.add(event);
                            System.out.println(horizontal_line);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(event);
                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
                            System.out.println(horizontal_line);
                        } else {
                            Task task = new Task(input);
                            list_of_Content.add(task);
                            System.out.println(horizontal_line);
                            System.out.println("Got it. I've added this task: " + task);
                            System.out.println("Now you have " + list_of_Content.size() + " tasks in the list.");
                            System.out.println(horizontal_line);
                        }
                    }
                    
                }
                
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
