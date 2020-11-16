import parser.Parser;

public class JavaFX {
    /**
     * Gets response from Duke upon text entry in JavaFX GUI.
     *
     * @param input Text Entry in JavaFX GUI.
     * @return Kim Jong Duke's response.
     */
    public String getResponse(String input) {
        return Parser.runForJavaFx(input);
    }
}
