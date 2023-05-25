package com.drisq.minimalGraphicsProject.fx;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class HomePageController implements DrisqController {
	public static final String FXML_RSC = "rsc/HomePage.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private Button _homePageExitButton;

	@FXML
	private Button _mensButton;

	@FXML
	private Button _womensButton;

	@FXML
	private Button _boysButton;

	@FXML
	private Button _girlsButton;

	@FXML
	private Button _brandsButton;

	@FXML
	private Button _productTypeButton;

	private boolean updateOnExit;

	public static HomePageController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, HomePageController.class, title);
		return (HomePageController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() {

		Font verdanaFont = new Font("Verdana", 13);

		_homePageExitButton.setFont(verdanaFont);
		_mensButton.setFont(verdanaFont);
		_womensButton.setFont(verdanaFont);
		_boysButton.setFont(verdanaFont);
		_girlsButton.setFont(verdanaFont);
		_brandsButton.setFont(verdanaFont);
		_productTypeButton.setFont(verdanaFont);

	}

	// _launchButtons open a new instance of another controller with a diffferent
	// fxml file, each one launching a different controller and file

	@FXML
	private void _launchMensButton() {

		Window owner = _rootNode.getScene().getWindow();
		MensSearchController controller = MensSearchController.newInstance(owner, "Mens Search");
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
	}

	@FXML
	private void _launchWomensButton() {

		Window owner = _rootNode.getScene().getWindow();
		WomensSearchController controller = WomensSearchController.newInstance(owner, "Ladies Search");
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
	}

	@FXML
	private void _launchBoysButton() {

		Window owner = _rootNode.getScene().getWindow();
		BoysSearchController controller = BoysSearchController.newInstance(owner, "Boys Search");
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
	}

	@FXML
	private void _launchGirlsButton() {

		Window owner = _rootNode.getScene().getWindow();
		GirlsSearchController controller = GirlsSearchController.newInstance(owner, "Girls Search");
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
	}

	@FXML
	private void _launchBrandsButton() {

		Window owner = _rootNode.getScene().getWindow();
		BrandsSelectController controller = BrandsSelectController.newInstance(owner, "Brands Search");
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
	}

	@FXML
	private void _launchProductTypeButton() {

		Window owner = _rootNode.getScene().getWindow();
		ProductTypeSearchController controller = ProductTypeSearchController.newInstance(owner, "Brands Search");
		((Stage) controller.getRootNode().getScene().getWindow()).showAndWait();
		if (controller.updateOnExit()) {

		}
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
