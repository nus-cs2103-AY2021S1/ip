public class Greet extends Response {
    public static final String defaultGreet =
            "Wonderful! It is nice to see you! \n" +
                    "   Is there anything I can help";

    public Greet(String greeting) {
        super(greeting);
    }

}
