public class Responder {
    public static void responder() {
        String echo = Echo.echo();
        while (echo != Exit.exit()) {
            System.out.println(echo);
            echo = Echo.echo();
        }
        System.out.println(Exit.exit());
    }
}
