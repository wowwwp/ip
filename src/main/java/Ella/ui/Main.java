package ella.ui;

import java.io.IOException;

import ella.Ella;
import ella.utils.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Helper class to launch Ella.
 */
public class Main extends Application {

    private Ella ella = new Ella();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            String loadOutput = ella.populateTasks();
            fxmlLoader.<MainWindow>getController().showMessage(Ui.greet());
            fxmlLoader.<MainWindow>getController().showMessage(loadOutput);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setElla(ella);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

