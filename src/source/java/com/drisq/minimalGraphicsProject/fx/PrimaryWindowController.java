package com.drisq.minimalGraphicsProject.fx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PrimaryWindowController {

	@FXML
	private Node _rootNode;

	@FXML
	private Label _passwordLabel;

	@FXML
	private PasswordField _passwordInputField;

	@FXML
	private ImageView passwordScreenLogo;

	@FXML
	private Button _enterPasswordButton;

	@FXML
	private void initialize() {
//Sets the fonts to be the required Verdana
		_passwordLabel.setFont(new Font("Verdana", 13));
		_enterPasswordButton.setFont(new Font("Verdana", 13));

	}

	@FXML
	private void _launchEnterPasswordButton() {
		// retrieves the text input by the user
		String passwordInput = _passwordInputField.getText();
		// sets the password
		String password = "a";
		// checks to see if the users input matches the password
		if (passwordInput.equals(password)) {
			// if it does, then open the home page
			Window owner = _rootNode.getScene().getWindow();
			HomePageController controller = HomePageController.newInstance(owner, "Home Page");
			((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
			_passwordInputField.clear();
			if (controller.updateOnExit()) {

			}
		} else {
			// if the passwords do not match, open an incorrect password pop up
			Window owner = _rootNode.getScene().getWindow();
			IncorrectPasswordController controller = IncorrectPasswordController.newInstance(owner,
					"Incorrect Password");
			((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
			_passwordInputField.clear();
			if (controller.updateOnExit()) {

			}

		}
	}
}
