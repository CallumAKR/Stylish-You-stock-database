package com.drisq.minimalGraphicsProject.fx;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ProductTypeTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/ProductTypeTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowGender> _productTypeTable;

	@FXML
	private TableColumn<TableRowGender, String> _productTypeProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowGender, String> _productTypeBrandColumn;

	@FXML
	private TableColumn<TableRowGender, String> _productTypeQuantityColumn;

	@FXML
	private TableColumn<TableRowGender, String> _productTypeAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static ProductTypeTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, ProductTypeTableController.class, title);
		return (ProductTypeTableController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() throws SQLException {

		Font verdanaFont = new Font("Verdana", 13);

		_homeButton.setFont(verdanaFont);
		_backButton.setFont(verdanaFont);

	}

	public void initProductTypeTable(String productTypeQuery) throws SQLException {

		_productTypeTable.getItems().clear();
		_productTypeProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_productTypeBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brandsText"));
		_productTypeQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_productTypeAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet productTypeDataSet = Database.productTypeQuery(productTypeQuery);
		StringBuilder productTypeDataString = new StringBuilder();

		try {
			while (productTypeDataSet.next()) {
				productTypeDataString.append(productTypeDataSet.getString("Product Description"));
				productTypeDataString.append(productTypeDataSet.getString("Brands"));
				productTypeDataString.append(productTypeDataSet.getString("Quantity"));
				productTypeDataString.append(productTypeDataSet.getString("Available"));
				TableRowGender row = new TableRowGender(productTypeDataSet.getString("Product Description"),
						productTypeDataSet.getString("Brands"), productTypeDataSet.getString("Quantity"),
						productTypeDataSet.getString("Available"));
				_productTypeTable.getItems().add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InternalError("Unable to extract from result set.");

		}

	}

	public boolean updateOnExit() {
		return updateOnExit;
	}

	@FXML
	protected final void _launchBackButton() {
		updateOnExit = false;
		done();
	}

	public void initRunnable(Runnable runnable) {
		this.runnable = runnable;

	}

	@FXML
	protected final void _launchHomeButton() {
		updateOnExit = false;
		runnable.run();
		done();
	}

	private void done() {
		((Stage) (_rootNode.getScene().getWindow())).close();
	}

}
