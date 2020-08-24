import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static boolean isInteger(String s) {
        if(s == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> orderList = new ArrayList<Task>();
        Scanner sc =  new Scanner(System.in);
        String seperateLine = "    _______________________________________";
        String spaceBeforeOder = "      ";
        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Hello! I'm Duke yy\n      What can I do for you?");
        System.out.println(seperateLine);
        String order = sc.nextLine();
        int numOfOders = 0;
        try {
            while (!order.equals("bye")) {
                if (order.equals("list")) {
                    System.out.println(seperateLine);
                    System.out.println(spaceBeforeOder + "Here are the tasks in your list:");
                    for (int i = 0; i < numOfOders; i++){
                        System.out.println(spaceBeforeOder + (i + 1) + ". " +
                                orderList.get(i));
                    }
                    System.out.println(seperateLine);
                } else if (order != null){
                    String[] command = order.split(" ", 2);
                    if(command[0].equals("done")) {
                        if(command.length == 1) {
                            throw new NoTaskChosenException(command[0]);
                        } else if(!isInteger(command[1]) || Integer.parseInt(command[1]) > numOfOders){
                            throw new NoThisNumOfTaskException();
                        } else {
                            int toMark = Integer.parseInt(command[1]) - 1;
                            orderList.get(toMark).markDone();
                            System.out.println(seperateLine);
                            System.out.println(spaceBeforeOder + "Nice! I've marked this task as done:");
                            System.out.println(spaceBeforeOder + "  " + orderList.get(toMark));
                            System.out.println(spaceBeforeOder + "Now you have " + numOfOders + " tasks in the list.");
                            System.out.println(seperateLine);
                        }
                    } else if(command[0].equals("delete")) {
                        if(command.length == 1) {
                            throw new NoTaskChosenException(command[0]);
                        } else if(!isInteger(command[1]) || Integer.parseInt(command[1]) > numOfOders){
                            throw new NoThisNumOfTaskException();
                        } else {
                            Task removed = orderList.remove(Integer.parseInt(command[1]) - 1);
                            System.out.println(seperateLine);
                            System.out.println(spaceBeforeOder + "Noted. I've removed this task:");
                            System.out.println(spaceBeforeOder + "  " + removed);
                            System.out.println(spaceBeforeOder + "Now you have " + (numOfOders - 1) + " tasks in the list.");
                            System.out.println(seperateLine);
                            numOfOders--;
                        }
                    }
                    else {
                        if(command.length == 1) {
                            throw new NoDescriptionException(command[0]);
                        }else if(command[0].equals("deadline")) {
                            String[] splitAgain = command[1].split("/by ");
                            if (splitAgain.length == 1) {
                                throw new NoTimeException(command[0]);
                            }
                            orderList.add(new Deadline(splitAgain[0], LocalDateTime.parse(splitAgain[1], validFormat)));
                        } else if(command[0].equals("event")) {
                            String[] splitAgain = command[1].split("/by ");
                            if (splitAgain.length == 1) {
                                throw new NoTimeException(command[0]);
                            }
                            orderList.add(new Event(splitAgain[0], LocalDateTime.parse(splitAgain[1], validFormat)));
                        } else if(command[0].equals("todo")) {
                            orderList.add( new Todo(command[1]));
                        } else {
                            throw new NoSuchOrderException();
                        }

                        System.out.println(seperateLine);
                        System.out.println(spaceBeforeOder + "Got it. I've added this task: ");
                        System.out.println(spaceBeforeOder + "  " + orderList.get(numOfOders));
                        System.out.println(spaceBeforeOder + "Now you have " + (numOfOders + 1) + " tasks in the list.");
                        System.out.println(seperateLine);
                        numOfOders++;
                    }

                }

                order = sc.nextLine();
            }
        } catch (NoDescriptionException | NoTimeException | NoSuchOrderException | NoTaskChosenException | NoThisNumOfTaskException e) {
            e.printStackTrace();
        }

        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Bye. Very nice to meet you!");
        System.out.println(spaceBeforeOder + "Hope to see you again soon! ");
        System.out.println(seperateLine);
        sc.close();
    }

}
