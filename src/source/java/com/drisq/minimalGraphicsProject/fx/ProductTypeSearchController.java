package com.drisq.minimalGraphicsProject.fx;

import java.sql.SQLException;

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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ProductTypeSearchController implements DrisqController {

	public static final String FXML_RSC = "rsc/ProductTypeSearch.fxml";

	ObservableList<String> genderType = FXCollections.observableArrayList("Any", "Mens", "Ladies", "Boys", "Girls");

	ObservableList<String> productType = FXCollections.observableArrayList("Any", "Fleeces", "Hoodies",
			"Jackets and Coats", "Jeans", "Polo Shirts", "Shirts", "Shoes", "Shorts", "Sweatshirts",
			"Tracksuit Bottoms", "Tracksuits", "Trousers", "T-Shirts");

	ObservableList<String> productWomensType = FXCollections.observableArrayList("Any", "Fleeces", "Hoodies",
			"Jackets and Coats", "Jeans", "Polo Shirts", "Shirts", "Shoes", "Shorts", "Sweatshirts",
			"Tracksuit Bottoms", "Tracksuits", "Trousers", "T-Shirts", "Dresses and Skirts", "Leggings and Tights");

	ObservableList<String> sizeType = FXCollections.observableArrayList("Any", "S", "M", "L", "XL", "XXL");

	ObservableList<String> shoeSizeType = FXCollections.observableArrayList("Any", "6", "6.5", "7", "7.5", "8", "8.5",
			"9", "9.5", "10", "10.5", "11", "12", "13");

	ObservableList<String> colourType = FXCollections.observableArrayList("Any", "Beige", "Black", "Blue", "Green",
			"Grey", "Multi", "Orange", "Pink", "Purple", "Red", "Silver", "White");

	ObservableList<String> brandType = FXCollections.observableArrayList("Any", "Jack Wills", "Hollister", "Zara");

	@FXML
	private Node _rootNode;

	@FXML
	private ChoiceBox _genderChoiceBox;

	@FXML
	private ChoiceBox _productTypeChoiceBox;

	@FXML
	private ChoiceBox _sizeChoiceBox;

	@FXML
	private ChoiceBox _colourChoiceBox;

	@FXML
	private ChoiceBox _brandChoiceBox;

	@FXML
	private TextField _minPriceTextField;

	@FXML
	private TextField _maxPriceTextField;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _findButton;

	@FXML
	private Label _genderLabel;

	@FXML
	private Label _productTypeLabel;

	@FXML
	private Label _sizeLabel;

	@FXML
	private Label _colourLabel;

	@FXML
	private Label _brandLabel;

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

		Font verdanaFont = new Font("Verdana", 13);

		_homeButton.setFont(verdanaFont);
		_findButton.setFont(verdanaFont);
		_genderLabel.setFont(verdanaFont);
		_productTypeLabel.setFont(verdanaFont);
		_sizeLabel.setFont(verdanaFont);
		_colourLabel.setFont(verdanaFont);
		_brandLabel.setFont(verdanaFont);

		_genderChoiceBox.setValue("Any");
		_genderChoiceBox.setItems(genderType);

		_productTypeChoiceBox.setValue("Any");
		_productTypeChoiceBox.setItems(productType);

		_sizeChoiceBox.setValue("Any");
		_sizeChoiceBox.setItems(sizeType);

		_colourChoiceBox.setValue("Any");
		_colourChoiceBox.setItems(colourType);

		_brandChoiceBox.setValue("Any");
		_brandChoiceBox.setItems(brandType);

		_genderChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.equals("Girls")) {
				_productTypeChoiceBox.setItems(productWomensType);
			} else if (newValue.equals("Ladies")) {
				_productTypeChoiceBox.setItems(productWomensType);
			} else if (newValue.equals("Any")) {
				_productTypeChoiceBox.setItems(productWomensType);
			} else {
				_productTypeChoiceBox.setItems(productType);
			}
			_productTypeChoiceBox.setValue("Any");
		});

		_productTypeChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue.equals("Shoes")) {
						_sizeChoiceBox.setItems(shoeSizeType);
					} else {
						_sizeChoiceBox.setItems(sizeType);
					}
					_sizeChoiceBox.setValue("Any");
				});

	}

	public String getGenderType(ActionEvent event) {

		String genderSelection = String.valueOf(_genderChoiceBox.getSelectionModel().getSelectedItem());
		return genderSelection;
	}

	public String getProductType(ActionEvent event) {

		String productSelection = String.valueOf(_productTypeChoiceBox.getSelectionModel().getSelectedItem());
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

	public String getBrandType(ActionEvent event) {

		String brandSelection = String.valueOf(_brandChoiceBox.getSelectionModel().getSelectedItem());
		return brandSelection;
	}

	@FXML
	private String _launchFindButton() {

		getGenderType(null);
		getProductType(null);
		getSizeType(null);
		getColourType(null);
		getBrandType(null);

		String anyCheck = "= 'Any'";

		ActionEvent genderSelection = null;
		String genderQuery = "= '" + getGenderType(genderSelection) + "'";
		if (genderQuery.equals(anyCheck)) {
			genderQuery = "IN ('Mens', 'Ladies', 'Boys', 'Girls')";

		}

		ActionEvent productSelection = null;
		String productQuery = "= '" + getProductType(productSelection) + "'";
		if (productQuery.equals(anyCheck)) {
			productQuery = "IN ('Fleeces', 'Hoodies', 'Jackets and Coats', 'Jeans', 'Polo Shirts', 'Shirts', 'Shoes', 'Shorts', 'Sweatshirts', 'Tracksuit Bottoms', 'Tracksuits', 'Trousers', 'T-Shirts', 'Dresses and Skirts', 'Leggings and Tights')";
		}

		ActionEvent sizeSelection = null;
		String sizeQuery = "= '" + getSizeType(sizeSelection) + "'";
		if (sizeQuery.equals(anyCheck)) {
			sizeQuery = "IN ('S', 'M', 'L', 'XL', 'XXL', '6', '6.5', '7', '7.5', '8', '8.5', '9', '9.5', '10', '10.5', '11', '12', '13')";

		}

		ActionEvent colourSelection = null;
		String colourQuery = "= '" + getColourType(colourSelection) + "'";
		if (colourQuery.equals(anyCheck)) {
			colourQuery = "IN ('Beige', 'Black', 'Blue', 'Green', 'Grey', 'Multi', 'Orange', 'Pink', 'Purple', 'Red', 'Silver', 'White')";

		}

		ActionEvent brandsSelection = null;
		String brandsQuery = "= '" + getBrandType(brandsSelection) + "'";
		if (brandsQuery.equals(anyCheck)) {
			brandsQuery = "IN ('Jack Wills', 'Hollister', 'Zara')";

		}

		String minPrice = _minPriceTextField.getText();
		String maxPrice = _maxPriceTextField.getText();

		String productTypeQuery = ("SELECT [Product Description], Brands, Quantity, Available FROM OurProducts WHERE [Product Type] "
				+ productQuery + " AND Sizes " + sizeQuery + " AND Colour " + colourQuery + "AND Brands " + brandsQuery
				+ "AND Gender " + genderQuery);

		if (!minPrice.isEmpty() && !maxPrice.isEmpty()) {
			productTypeQuery += " AND Price BETWEEN '" + minPrice + "' AND '" + maxPrice + "'";
		} else if (!minPrice.isEmpty()) {
			productTypeQuery += " AND Price >= '" + minPrice + "'";
		} else if (!maxPrice.isEmpty()) {
			productTypeQuery += " AND Price <= '" + maxPrice + "'";
		}

		System.out.println(productTypeQuery);

		Window owner = _rootNode.getScene().getWindow();
		ProductTypeTableController controller = ProductTypeTableController.newInstance(owner, "Product Table");
		try {
			controller.initProductTypeTable(productTypeQuery);
		} catch (SQLException e) {

			e.printStackTrace();
		}
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

		return productTypeQuery;

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
