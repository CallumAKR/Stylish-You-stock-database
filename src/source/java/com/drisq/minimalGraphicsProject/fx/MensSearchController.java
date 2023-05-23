package com.drisq.minimalGraphicsProject.fx;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MensSearchController implements DrisqController {

	public static final String FXML_RSC = "rsc/MensSearch.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private ChoiceBox<String> _productChoiceBox;

	@FXML
	private ChoiceBox<String> _sizeChoiceBox;

	@FXML
	private ChoiceBox<String> _colourChoiceBox;

	@FXML
	private ChoiceBox<String> _brandChoiceBox;

	@FXML
	private TextField _minPriceTextField;

	@FXML
	private TextField _maxPriceTextField;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _findButton;

	@FXML
	private Label Label;

	private boolean updateOnExit;

	public static MensSearchController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, MensSearchController.class, title);
		return (MensSearchController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void _launchFindButton() {

	}

	public boolean updateOnExit() {
		return updateOnExit;
	}

	@FXML
	protected final void _launchHomeButton() {
		updateOnExit = false;
		done();
	}

	private void done() {
		((Stage) (_rootNode.getScene().getWindow())).close();
	}

}
