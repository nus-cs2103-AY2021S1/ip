public class Rogue {
    public static void main(String[] args) {
        Chatbot rogue = new Chatbot(System.in, "./data/tasks.txt");
        rogue.start();
    }
}
