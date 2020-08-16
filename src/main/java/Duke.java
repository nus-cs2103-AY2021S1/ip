import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lines = "\t____________________\n";
        String farewell = "\tSad to see you go!\n";
        System.out.println(lines + "\tHello, I'm your chatbot!\n\tWhat can I do for you?\n" + lines);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(lines + farewell + lines);
                break;
            } else {
                System.out.println(lines + "\t" +input + "\n" + lines);
            }
        }
    }
}