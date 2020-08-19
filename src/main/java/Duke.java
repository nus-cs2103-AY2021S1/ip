import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Setup
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>(100);

        //constant
        String tab = "  ";
        String lineBreaker = tab + "_________________________________________________________________";
        String greet = tab + " Hello! I'm Duke" + "\n" + tab + " What can I do for you?";
        String end = tab + " Bye. Hope to see you again soon!";
        String addTaskTitle = tab + " Got it. I've added this task:";
        String listTitle = tab + " Here are the tasks in your list:";
        String doneTitle = tab + " Nice! I've marked this task as done:";


        //Greeting
        System.out.println(lineBreaker);
        System.out.println(greet);
        System.out.println(lineBreaker);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(lineBreaker);

            if(input.equals("bye")) {
                System.out.println(end);
                System.out.println(lineBreaker);
                break;

            } else if(input.equals("list")) {
                int i = 1;
                System.out.println(listTitle);
                for (Task task : listOfTasks) {
                    System.out.println(tab + " " + i++ + "." + task);
                }

            } else if(input.length() >= 6 && input.substring(0, 4).equals("done")) {
                int indexOfMarkingTask = Integer.parseInt(input.charAt(5) + "") - 1;
                Task markingTask = listOfTasks.get(indexOfMarkingTask);
                Task markedTask = markingTask.markAsDone();
                listOfTasks.set(indexOfMarkingTask, markedTask);

                System.out.println(doneTitle);
                System.out.println(tab + "   " + markedTask);

            } else { //add task
                String firstWord = input.substring(0, input.indexOf(' '));
                System.out.println(addTaskTitle);

                if (firstWord.equals("todo")) {
                    String description = input.substring(input.indexOf(' ') + 1);
                    Todo newTask = new Todo(description);
                    listOfTasks.add(newTask);
                    System.out.println(tab + "   " +  newTask);

                } else { //event or deadline task
                    String description = input.substring(input.indexOf(' ') + 1, input.indexOf('/'));
                    String time = input.substring(input.indexOf('/') + 4);
                    if (firstWord.equals("deadline")) {
                        Deadline newTask = new Deadline(description, time);
                        listOfTasks.add(newTask);
                        System.out.println(tab + "   " +  newTask);
                    } else { //event task
                        Event newTask = new Event(description, time);
                        listOfTasks.add(newTask);
                        System.out.println(tab + "   " +  newTask);
                    }
                }

                System.out.println(tab + " Now you have " + listOfTasks.size() + " tasks in the list.");
            }

            System.out.println(lineBreaker);
        }
    }
}
