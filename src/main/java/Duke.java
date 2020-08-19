import java.util.Scanner;

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
        Task[] orderList = new Task[100];
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
                                orderList[i]);
                    }
                    System.out.println(seperateLine);
                } else if (order != null){
                    String[] command = order.split(" ", 2);
                    if(command[0].equals("done") && isInteger(command[1])
                            && Integer.parseInt(command[1]) <= numOfOders) {
                        int toMark = Integer.parseInt(command[1]) - 1;
                        orderList[toMark] = orderList[toMark].markDone();
                        System.out.println(seperateLine);
                        System.out.println(spaceBeforeOder + "Nice! I've marked this task as done:");
                        System.out.println(spaceBeforeOder + "  " + orderList[toMark]);
                        System.out.println(seperateLine);
                    } else {
                        if(command.length == 1) {
                            throw new NoDescriptionException(command[0]);
                        }else if(command[0].equals("deadline")) {
                            String[] splitAgain = command[1].split("/by ");
                            if (splitAgain.length == 1) {
                                throw new NoTimeException(command[0]);
                            }
                            orderList[numOfOders] = new Deadline(splitAgain[0], splitAgain[1]);
                        } else if(command[0].equals("event")) {
                            String[] splitAgain = command[1].split("/by ");
                            if (splitAgain.length == 1) {
                                throw new NoTimeException(command[0]);
                            }
                            orderList[numOfOders] = new Event(splitAgain[0], splitAgain[1]);
                        } else if(command[0].equals("todo")) {
                            orderList[numOfOders] = new Todo(command[1]);
                        } else {
                            throw new NoSuchOrderException();
                        }

                        System.out.println(seperateLine);
                        System.out.println(spaceBeforeOder + "Got it. I've added this task: ");
                        System.out.println(spaceBeforeOder + "  " + orderList[numOfOders]);
                        System.out.println(spaceBeforeOder + "Now you have " + (numOfOders + 1) + " tasks in the list.");
                        System.out.println(seperateLine);
                        numOfOders++;
                    }

                }

                order = sc.nextLine();
            }
        } catch (NoDescriptionException | NoTimeException | NoSuchOrderException e) {
            e.printStackTrace();
        }

        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Bye. Very nice to meet you!");
        System.out.println(spaceBeforeOder + "Hope to see you again soon! ");
        System.out.println(seperateLine);
        sc.close();
    }

}
