package nekochan;

import nekochan.command.Response;
import nekochan.exceptions.IncompleteNekoCommandException;
import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoStorageException;
import nekochan.storage.Storage;
import nekochan.ui.Clui;

public class CliWrapper {

    private NekoChan nekoChan;
    private Clui ui;

    private CliWrapper(String filePath) {
        ui = new Clui();
        try {
            nekoChan = new NekoChan(filePath, true);
        } catch (NekoStorageException e) {
            ui.print(e.getMessage());
            nekoChan = new NekoChan(filePath, false);
        }
    }

    private void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Response response = nekoChan.getResponse(fullCommand);

                assert response.getResponseMessage().length() > 0 : "response message should not be empty";

                ui.print(response.getResponseMessage());
                isExit = response.shouldExit();
            } catch (IncompleteNekoCommandException e) {
                ui.print("Something went wrong, but I'm not sure what...");
            } catch (NekoException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new CliWrapper(Storage.FILE_PATH).run();
    }
}
