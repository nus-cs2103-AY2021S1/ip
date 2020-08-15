public class Output {
    public void response(String s) {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (s.isEmpty()) {
            System.out.println("Please enter valid input");
        } else {
            System.out.println(s);
        }
    }
}
