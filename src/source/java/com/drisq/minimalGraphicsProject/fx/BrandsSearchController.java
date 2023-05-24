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

public class BrandsSearchController implements DrisqController {

	public static final String FXML_RSC = "rsc/BrandsSearch.fxml";

	ObservableList<String> genderType = FXCollections.observableArrayList("Any", "Mens", "Ladies", "Boys", "Girls");

	ObservableList<String> productType = FXCollections.observableArrayList("Any", "Fleeces", "Hoodies",
			"Jackets and coats", "Jeans", "Polo shirts", "Shirts", "Shoes", "Shorts", "Sweatshirts",
			"Tracksuit bottoms", "Tracksuits", "Trousers", "T-Shirts");

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
	private ChoiceBox _productChoiceBox;

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

	public static BrandsSearchController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, BrandsSearchController.class, title);
		return (BrandsSearchController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() {

		_productChoiceBox.setValue("Any");
		_productChoiceBox.setItems(productType);

		_sizeChoiceBox.setValue("Any");
		_sizeChoiceBox.setItems(sizeType);

		_colourChoiceBox.setValue("Any");
		_colourChoiceBox.setItems(colourType);

		_genderChoiceBox.setValue("Any");
		_genderChoiceBox.setItems(genderType);

	}

	public String getProductType(ActionEvent event) {

		String productSelection = String.valueOf(_productChoiceBox.getSelectionModel().getSelectedItem());
		return productSelection;
	}

	public String getSizeType(ActionEvent event) {

		String sizeSelection = String.valueOf(_sizeChoiceBox.getSelectionModel().getSelectedItem());
		return sizeSelection;
	}

	public String getColourType(ActionEvent event) {

		String colourSelection = String.valueOf(_colourChoiceBox.getSelectionModel().getSelectedItem());
		return colourSelection;
	}

	public String getGenderType(ActionEvent event) {

		String genderSelection = String.valueOf(_genderChoiceBox.getSelectionModel().getSelectedItem());
		return genderSelection;
	}

	@FXML
	private void _launchFindButton() {

		getProductType(null);
		getSizeType(null);
		getColourType(null);
		getGenderType(null);

		ActionEvent productSelection = null;
		String productQuery = getProductType(productSelection);

		ActionEvent sizeSelection = null;
		String sizeQuery = getSizeType(sizeSelection);

		ActionEvent colourSelection = null;
		String colourQuery = getColourType(colourSelection);

		ActionEvent genderSelection = null;
		String genderQuery = getGenderType(genderSelection);

		String Query = ("SELECT [Product Description], Brands, Quantity, Available FROM OurProducts WHERE [Product Type] = "
				+ productQuery + " AND Sizes = " + sizeQuery + " AND Colour = " + colourQuery + " AND Brands = "
				+ genderQuery);

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
