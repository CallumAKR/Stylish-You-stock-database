package com.drisq.minimalGraphicsProject.fx;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class IncorrectPasswordController implements DrisqController {

	public static final String FXML_RSC = "rsc/IncorrectPassword.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private Label _incorrectPassowordLabel;

	@FXML
	private Button _okButton;

	private boolean updateOnExit;

	public static IncorrectPasswordController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, IncorrectPasswordController.class, title);
		return (IncorrectPasswordController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() {

		_incorrectPassowordLabel.setFont(new Font("Verdana", 13));
		_okButton.setFont(new Font("Verdana", 13));
	}

	public boolean updateOnExit() {
		return updateOnExit;
	}

	@FXML
	protected final void _launchOkButton() {
		updateOnExit = false;
		done();
	}

	private void done() {
		((Stage) (_rootNode.getScene().getWindow())).close();
	}

}