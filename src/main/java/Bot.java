import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bot {
    public Bot() {
    }

    String line = "____________________________________________________________";
    ArrayList<Listing> list = new ArrayList<Listing>();


    public void serve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] arr = s.split(" ");
            String firstWord = arr[0];
            if (firstWord.equals("bye")) {
                return;
            } else if (firstWord.equals("list")) {
                returnListings(s);
            } else if (firstWord.equals("done")) {
                Integer value = Integer.valueOf(arr[1]);
                doneListings(value);
            } else {
                addListings(s);
            }

        }
    }

    public void addListings(String title) {
        Listing newListing = new Listing(title);
        list.add(newListing);
        System.out.println(line + "\n" + "added: " + newListing.toString() + "\n" + line);

    }

    public void returnListings(String listing) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String s = (i + 1) + "." + list.get(i).doneness() + list.get(i).toString();
            System.out.println(s);
        }
        System.out.println(line);
    }

    public void doneListings(Integer value) {
        Listing item = list.get(value - 1);
        item.complete(); //completes the list
        String s = line + "\n" + "Nice! I've marked this task as done: \n" + item.doneness()
                + item.toString() +"\n" + line;
        System.out.println(s);
    }


}
