package com.drisq.minimalGraphicsProject.fx;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class HomePageController implements DrisqController {
	public static final String FXML_RSC = "rsc/HomePage.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private Button _homePageExitButton;

	private boolean updateOnExit;

	public static HomePageController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, HomePageController.class, title);
		return (HomePageController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	public boolean updateOnExit() {
		return updateOnExit;
	}

	@FXML
	protected final void _launchHomePageExitButton() {
		updateOnExit = false;
		done();
	}

	private void done() {
		((Stage) (_rootNode.getScene().getWindow())).close();
	}

}
