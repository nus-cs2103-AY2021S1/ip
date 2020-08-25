import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDate;

public class Duke {
    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"+"What can I do for you?");
        ArrayList<Task> listOfItems = DataManager.loadTaskFromFile();
        String echo= sc.nextLine();
        while(!echo.equals("bye")) {
            try {
                String[] tempArray = echo.split(" ");
                if (echo.equals("list")) {
                    int iterator = 1;
                    System.out.println("Here are the tasks in your list:");
                    for (Task s : listOfItems) {
                        System.out.println(iterator + "." + s.toString());
                        iterator++;
                    }
                }

                else if (tempArray[0].equals("done")) {
                    int index = Integer.parseInt(tempArray[1]) - 1;
                    listOfItems.get(index).markDone();
                    System.out.println("Nice! I've marked this task as done: \n" + listOfItems.get(index).toString());
                } else if(tempArray[0].equals("delete")) {
                    int index = Integer.parseInt(tempArray[1]) - 1;
                    Task tobeRemove = listOfItems.get(index);
                    listOfItems.remove(index);
                    System.out.println("Noted. I've removed this task: \n" + tobeRemove.toString() + "\nNow you have " + listOfItems.size() + " tasks in the list");
                }
                else {
                    if (tempArray[0].equals("todo")) {
                        if(tempArray.length == 1) {
                            throw new InvalidTodoException();
                        }
                        Todo newTodo = new Todo(echo.substring(5), false);
                        listOfItems.add(newTodo);
                        System.out.println("Got it. I've added this task:\n" + newTodo.toString() +
                                "\nNow you have " + listOfItems.size() + " tasks in total");

                    }
                    else if (tempArray[0].equals("deadline")) {
                        if(tempArray.length == 1) {
                            throw new InvalidDeadlineException();
                        }
                        String[] tempString = echo.substring(9).split(" /by");
                        Deadline newDeadline = new Deadline(tempString[0], false, LocalDate.parse(tempString[1]));
                        listOfItems.add(newDeadline);
                        System.out.println("Got it. I've added this task:\n" + newDeadline.toString()
                                + "\nNow you have " + listOfItems.size() + " tasks in total");

                    }
                    else if (tempArray[0].equals("event")) {
                        if(tempArray.length == 1) {
                            throw new InvalidEventException();
                        }
                        String[] tempString = echo.substring(7).split(" /at");
                        Event newEvent = new Event(tempString[0], false,  LocalDate.parse(tempString[1]));
                        listOfItems.add(newEvent);
                        System.out.println("Got it. I've added this task:\n" + newEvent.toString()
                                + "\nNow you have " + listOfItems.size() + " tasks in total");
                    }
                    else {
                        throw new InvalidInputException();
                    }

                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            echo = sc.nextLine();
        }
        DataManager.writeToFile(listOfItems);
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args){
        try {
            Duke duke = new Duke();
            duke.run();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
