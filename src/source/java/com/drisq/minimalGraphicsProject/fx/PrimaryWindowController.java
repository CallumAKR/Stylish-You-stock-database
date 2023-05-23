package com.drisq.minimalGraphicsProject.fx;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
	private Button _enterPasswordButton;

	@FXML
	private void initialize() {

		_passwordLabel.setFont(new Font("Verdana", 13));
		_enterPasswordButton.setFont(new Font("Verdana", 13));

	}

	@FXML
	private void _launchEnterPasswordButton() {
		String passwordInput = _passwordInputField.getText();
		String password = "a";
		if (passwordInput.equals(password)) {
			Window owner = _rootNode.getScene().getWindow();
			HomePageController controller = HomePageController.newInstance(owner, "Home Page");
			((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
			_passwordInputField.clear();
			if (controller.updateOnExit()) {

			}
		} else {
			System.out.println("Please Try again");
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
