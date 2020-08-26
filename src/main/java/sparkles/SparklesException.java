package sparkles;

/**
 * A custom exception class to handle exceptions with regards to Sparkles chatbot.
 */
public class SparklesException extends Exception{
    public SparklesException(String message) {
        super(message);
    }
}
