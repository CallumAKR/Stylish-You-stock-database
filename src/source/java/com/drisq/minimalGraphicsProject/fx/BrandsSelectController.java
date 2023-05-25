package com.drisq.minimalGraphicsProject.fx;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BrandsSelectController implements DrisqController {
	public static final String FXML_RSC = "rsc/BrandSelect.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private Button _jackWillsButton;

	@FXML
	private Button _hollisterButton;

	@FXML
	private Button _zaraButton;

	@FXML
	private Button _homeButton;

	private boolean updateOnExit;

	public static BrandsSelectController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, BrandsSelectController.class, title);
		return (BrandsSelectController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() {
		Font verdanaFont = new Font("Verdana", 13);

		_homeButton.setFont(verdanaFont);
		_jackWillsButton.setFont(verdanaFont);
		_zaraButton.setFont(verdanaFont);
		_hollisterButton.setFont(verdanaFont);

	}

	@FXML
	private void _launchJackWillsButton() {

		Window owner = _rootNode.getScene().getWindow();
		JackWillsSearchController controller = JackWillsSearchController.newInstance(owner, "Jack Wills Search");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				done();

			}
		};
		controller.initRunnable(runnable);
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
	}

	@FXML
	private void _launchHollisterButton() {

		Window owner = _rootNode.getScene().getWindow();
		HollisterSearchController controller = HollisterSearchController.newInstance(owner, "Hollister Search");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				done();

			}
		};
		controller.initRunnable(runnable);
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
	}

	@FXML
	private void _launchZaraButton() {

		Window owner = _rootNode.getScene().getWindow();
		ZaraSearchController controller = ZaraSearchController.newInstance(owner, "Zara Search");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				done();

			}
		};
		controller.initRunnable(runnable);
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
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
