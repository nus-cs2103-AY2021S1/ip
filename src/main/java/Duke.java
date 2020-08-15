import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("_____________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("_____________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while(!input.equals("bye")){
            System.out.println("_____________________________");
            if (input.equals("list")){
                print(list);
                System.out.println("_____________________________");
                input = sc.nextLine();
                continue;
            }
            String[] request = input.split(" ");
            if (request[0].equals("done")){
                if (request.length <= 1){
                    throw new DukeException("The list doesn't contain what you mentioned");
//                    System.out.println("_____________________________");
                } else {
                    int index = Integer.parseInt(request[1]);
                    list.get(index - 1).updateStatus();
                    System.out.println("Nice! I've marked this task as done: \n " + list.get(index - 1));
                    input = sc.nextLine();
                }
            } else if (request[0].equals("todo")){
                String sb = input.substring(4);
                if (sb.equals("")){
                    throw new EmptyTodoException();
                }
                ToDos todo = new ToDos(sb);
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                list.add(todo);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("_____________________________");
                input = sc.nextLine();
            } else if (request[0].equals("deadline")){
                String sb = input.substring(8);
                Deadline deadline = new Deadline(sb);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                list.add(deadline);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("_____________________________");
                input = sc.nextLine();
            } else if (request[0].equals("event")){
                String sb = input.substring(5);
                Event event = new Event(sb);
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                list.add(event);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("_____________________________");
                input = sc.nextLine();
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
//                System.out.println("_____________________________");
//                input = sc.nextLine();
            }
        }
        System.out.println("_____________________________");
        System.out.println("Bye. Hope to see you again soon!\n_____________________________");
    }

    public static void print(ArrayList<Task> list){
        for (int i = 0; i < list.size(); i++){
            System.out.println("" + (i + 1) + "." + list.get(i));
        }
    }
}
