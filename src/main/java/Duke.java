import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws ParseException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ \uD83D\uDD34 \uD83D\uDD34 \\\n"
                + "| |_| | |_| |   <  \\__/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time = timeFormat.format(today);
        boolean checkLate;

        if (timeFormat.parse(time).after(timeFormat.parse("12:00")))
        {
            checkLate = true;

        } else {
            checkLate = false;
        }

        System.out.println("Its " + dateFormat.format(today)  + " and " + "the time now is " + time);

        if (checkLate == true) {
            System.out.println("It's Late! YAWNS... Almost my bed time, but always ready to serve you, my trusted friend!");
        } else {
            System.out.println("It's still early! Let me go get my cup of coffee first... then I will be ready to answer your questions!");
            System.out.println("...");
            System.out.println("Okay, I'm back and ready!");
        }

        System.out.println("\nWhat can I do for you?");

        ArrayList<Task> inputStore = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("\u263A Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("")) {
                System.out.println("\uD83D\uDE20 Don't enter NOTHING!!");
            } else if (userInput.equals("list")) {
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < inputStore.size(); i++) {
                    System.out.println("\t " + (i + 1) + ". " +  inputStore.get(i).toString());
                }
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
            } else if (userInput.length() > 3 && userInput.substring(0,4).equals("done")) {
                String[] splitString = userInput.split(" ");
                Task task = inputStore.get(Integer.parseInt(splitString[1]) - 1);
                task.markDone();
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
                System.out.println("\t Nice! I've marked this task as done: ");
                System.out.println("\t   " + task.toString());
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
            } else {
                inputStore.add(new Task(userInput));
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
                System.out.println("\t " + "added: " + userInput);
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
            }
        }
    }
}
