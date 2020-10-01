package duke.command;

import java.util.HashMap;
import java.util.Map;

import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Container for macros. Ensures a macro's name is not taken before
 * allowing it to be added.
 */
public class MacroList {
    // TODO: handle infinite loops: disable macros internally?
    private Map<String, Macro> macros;

    public MacroList() {
        this.macros = new HashMap<>();
    }

    public boolean hasMacro(String name) {
        return this.macros.containsKey(name);
    }

    private boolean isMacroNameTaken(String name) {
        return this.hasMacro(name) || Command.hasCommand(name);
    }

    /**
     * Adds a macro to the MacroList silently (no ui interaction).
     * @param declaration Macro declaration string. Refer to the user guide.
     * @throws DukeException if the macro's name has already been taken.
     */
    public void addMacro(String declaration) throws DukeException {
        Macro newMacro = Macro.newMacro(declaration);
        if (this.isMacroNameTaken(newMacro.getName())) {
            throw DukeException.Errors.MACRO_ALREADY_DEFINED.create();
        }
        this.macros.put(newMacro.getName(), newMacro);
    }

    /**
     * Adds a macro to the MacroList. Prints a success message to the Ui.
     * @param declaration Macro declaration string. Refer to the user guide.
     * @throws DukeException if the macro's name has already been taken.
     */
    public void addMacro(String declaration, Ui ui) throws DukeException {
        this.addMacro(declaration);
        ui.systemMessage("macro have been add ;)");
    }

    /**
     * Add default macro shortcuts for the user into the MacroList.
     */
    public void addDefaultMacros() {
        String[] macros = {"t; todo \\$",
            "e at; event \\$ -at \\at",
            "d by; deadline \\$ -by \\by",
            "x; delete \\$",
            "f; find \\$",
            "c; done \\$",
            "l; list"};
        for (String macro : macros) {
            try {
                this.addMacro(macro);
            } catch (DukeException e) {
                // should never happen, but if it does:
                assert false : "default macro definition failed:" + e.getMessage();
            }
        }
    }

    public Macro getMacro(String name) {
        return this.macros.get(name);
    }
}
