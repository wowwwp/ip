package ella.ui;

import ella.Ella;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ella ella;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image ellaImage = new Image(this.getClass().getResourceAsStream("/images/star.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Ella instance */
    public void setElla(Ella e) {
        ella = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ella's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ella.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEllaDialog(response, ellaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            Platform.exit();
        }
    }

    /**
     * Shows messages which does not require any user input.
     *
     * @param greeting
     */
    public void showMessage(String greeting) {
        dialogContainer.getChildren().addAll(DialogBox.getEllaDialog(greeting, ellaImage)
        );
    }
}


