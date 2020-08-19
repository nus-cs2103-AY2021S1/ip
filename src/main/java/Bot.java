import java.util.Scanner;

public class Bot {
    public Bot() {
    }

    String line = "____________________________________________________________";
    String[] list = new String[100];
    int listSize = 0;


    public void serve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                return;
            } else if (s.equals("list")) {
                returnListings(s);
            } else {
                addListings(s);
            }

        }
    }

    public void addListings(String listing) {
        listing = "added: " + listing;
        list[listSize] = listing;
        listSize++;
        System.out.println(line + "\n" + listing + "\n" + line);

    }

    public void returnListings(String listing) {
        System.out.println(line);
        for (int i = 0; i < listSize; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println(line);
    }


}
