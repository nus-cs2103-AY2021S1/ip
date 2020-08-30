package parser;
/**
 * Parser class parses the given input.
 * @author Maguire Ong
 */

public class Parser {

    /**
     * Takes in the input and splits the line into individual words.
     * @param echo represents the input to the Duke bot
     * @return first word.
     */
    public String parse(String echo) {
        String[] s = echo.split("\\s");
        String first = s[0];
        return first;
    }
}