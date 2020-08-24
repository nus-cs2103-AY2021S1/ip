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
        String deleteTitle = tab + " Noted. I've removed this task:";


        //Greeting
        System.out.println(lineBreaker);
        System.out.println(greet);
        System.out.println(lineBreaker);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] words = input.split("\\s");
            String firstWord = words[0];

            try {
                System.out.println(lineBreaker);

                if (input.equals("bye")) { //bye command
                    System.out.println(end);
                    System.out.println(lineBreaker);
                    break; //end here

                } else if (input.equals("list")) { //list command
                    int i = 1;
                    System.out.println(listTitle);
                    for (Task task : listOfTasks) {
                        System.out.println(tab + " " + i++ + "." + task);
                    }

                } else if (firstWord.equals("done")) { //done command
                    if (words.length == 1) { //incomplete done command
                        throw new DoneException(" ☹ OOPS!!! The description of a done cannot be empty.");
                    }

                    int indexOfMarkingTask = Integer.parseInt(words[1]) - 1;
                    if (indexOfMarkingTask + 1 > listOfTasks.size() || indexOfMarkingTask + 1 <= 0) {
                        throw new DoneException(" ☹ OOPS!!! There is no such task.");
                    }

                    //complete done command
                    Task markingTask = listOfTasks.get(indexOfMarkingTask);
                    Task markedTask = markingTask.markAsDone();
                    listOfTasks.set(indexOfMarkingTask, markedTask);

                    System.out.println(doneTitle);
                    System.out.println(tab + "   " + markedTask);

                } else if (firstWord.equals("delete")) { //done command
                    if (words.length == 1) { //incomplete done command
                        throw new DeleteException(" ☹ OOPS!!! The description of a delete cannot be empty.");
                    }

                    int indexOfMarkingTask = Integer.parseInt(words[1]) - 1;
                    if (indexOfMarkingTask + 1 > listOfTasks.size() || indexOfMarkingTask + 1 <= 0) {
                        throw new DeleteException(" ☹ OOPS!!! There is no such task.");
                    }

                    //complete done command
                    Task deletingTask = listOfTasks.get(indexOfMarkingTask);
                    listOfTasks.remove(indexOfMarkingTask);

                    System.out.println(deleteTitle);
                    System.out.println(tab + "   " + deletingTask);
                    System.out.println(tab + " Now you have " + listOfTasks.size() + " tasks in the list.");

                } else if (firstWord.equals("todo")) { //todo command
                    //complete command
                    Todo newTask = Todo.create(input);
                    System.out.println(addTaskTitle);
                    listOfTasks.add(newTask);
                    System.out.println(tab + "   " + newTask);
                    System.out.println(tab + " Now you have " + listOfTasks.size() + " tasks in the list.");
                } else if (firstWord.equals("deadline")) {//complete deadline command
                    Deadline newTask = Deadline.create(input);
                    listOfTasks.add(newTask);
                    System.out.println(addTaskTitle);
                    System.out.println(tab + "   " + newTask);
                    System.out.println(tab + " Now you have " + listOfTasks.size() + " tasks in the list.");
                } else if (firstWord.equals("event")) {
                    Event newTask = Event.create(input);
                    listOfTasks.add(newTask);
                    System.out.println(tab + "   " + newTask);
                    System.out.println(addTaskTitle);
                    System.out.println(tab + " Now you have " + listOfTasks.size() + " tasks in the list.");
                } else { //exception handler
                    throw new DukeException();
                }
            } catch (DukeException | DoneException | TodoException | DeadlineException | EventException | DeleteException e) {
                System.out.println(tab + e.getMessage());
            }
            System.out.println(lineBreaker);
        }
    }
}
