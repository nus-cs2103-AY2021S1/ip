import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("\u263A Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("")) {
                System.out.println("\uD83D\uDE20 Don't enter NOTHING!!");
            } else {
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
                System.out.println("\t" + userInput);
                System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
            }
        }
    }
}
