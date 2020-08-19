public class Chatbot {
    public String line = "____________________________________________________________";

    public void greeting() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println(line);
    }

    public void echo(String s) {
        System.out.println(line);
        System.out.println(s);
        System.out.println(line);
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
