package com.drisq.minimalGraphicsProject.fx;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ProductTypeSearchController implements DrisqController {

	public static final String FXML_RSC = "rsc/ProductTypeSearch.fxml";

	ObservableList<String> genderType = FXCollections.observableArrayList("Any", "Mens", "Ladies", "Boys", "Girls");

	ObservableList<String> brandType = FXCollections.observableArrayList("Any", "Jack Wills", "Hollister", "Zara");

	ObservableList<String> sizeType = FXCollections.observableArrayList("Any", "S", "M", "L", "XL", "XXL");

	ObservableList<String> shoeSizeType = FXCollections.observableArrayList("Any", "6", "6.5", "7", "7.5", "8", "8.5",
			"9", "9.5", "10", "10.5", "11", "12", "13");

	ObservableList<String> colourType = FXCollections.observableArrayList("Any", "Beige", "Black", "Blue", "Green",
			"Grey", "Multi", "Orange", "Pink", "Purple", "Red", "Silver", "White");

	@FXML
	private Node _rootNode;

	@FXML
	private ChoiceBox _genderChoiceBox;

	@FXML
	private ChoiceBox _brandChoiceBox;

	@FXML
	private ChoiceBox _sizeChoiceBox;

	@FXML
	private ChoiceBox _colourChoiceBox;

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

	public static ProductTypeSearchController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, ProductTypeSearchController.class, title);
		return (ProductTypeSearchController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() {

		_genderChoiceBox.setValue("Any");
		_genderChoiceBox.setItems(genderType);

		_sizeChoiceBox.setValue("Any");
		_sizeChoiceBox.setItems(sizeType);

		_colourChoiceBox.setValue("Any");
		_colourChoiceBox.setItems(colourType);

		_brandChoiceBox.setValue("Any");
		_brandChoiceBox.setItems(brandType);

	}

	public String getGenderType(ActionEvent event) {

		String genderSelection = String.valueOf(_genderChoiceBox.getSelectionModel().getSelectedItem());
		return genderSelection;
	}

	public String getSizeType(ActionEvent event) {

		String sizeSelection = String.valueOf(_sizeChoiceBox.getSelectionModel().getSelectedItem());
		return sizeSelection;
	}

	public String getColourType(ActionEvent event) {

		String colourSelection = String.valueOf(_colourChoiceBox.getSelectionModel().getSelectedItem());
		return colourSelection;
	}

	public String getBrandType(ActionEvent event) {

		String brandSelection = String.valueOf(_brandChoiceBox.getSelectionModel().getSelectedItem());
		return brandSelection;
	}

	@FXML
	private void _launchFindButton() {

		getGenderType(null);
		getSizeType(null);
		getColourType(null);
		getBrandType(null);

		ActionEvent genderSelection = null;
		String genderQuery = getGenderType(genderSelection);

		ActionEvent sizeSelection = null;
		String sizeQuery = getSizeType(sizeSelection);

		ActionEvent colourSelection = null;
		String colourQuery = getColourType(colourSelection);

		ActionEvent brandsSelection = null;
		String brandsQuery = getBrandType(brandsSelection);

		String Query = ("SELECT [Product Description], Brands, Quantity, Available FROM OurProducts WHERE Gender = "
				+ genderQuery + " AND Sizes = " + sizeQuery + " AND Colour = " + colourQuery + " AND Brands = "
				+ brandsQuery);

		System.out.println("here: " + Query);

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
