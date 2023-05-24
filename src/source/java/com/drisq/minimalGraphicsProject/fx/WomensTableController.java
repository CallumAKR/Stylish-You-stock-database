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
import javafx.stage.Stage;
import javafx.stage.Window;

public class WomensTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/WomensTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowGender> _womensTable;

	@FXML
	private TableColumn<TableRowGender, String> _womensProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowGender, String> _womensBrandColumn;

	@FXML
	private TableColumn<TableRowGender, String> _womensQuantityColumn;

	@FXML
	private TableColumn<TableRowGender, String> _womensAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static WomensTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, WomensTableController.class, title);
		return (WomensTableController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() throws SQLException {

	}

	public void initWomensTable(String mensQuery) throws SQLException {

		_womensTable.getItems().clear();
		_womensProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_womensBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brandsText"));
		_womensQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_womensAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet womensDataSet = Database.mensQuery(mensQuery);
		StringBuilder womensDataString = new StringBuilder();

		try {
			while (womensDataSet.next()) {
				womensDataString.append(womensDataSet.getString("Product Description"));
				womensDataString.append(womensDataSet.getString("Brands"));
				womensDataString.append(womensDataSet.getString("Quantity"));
				womensDataString.append(womensDataSet.getString("Available"));
				TableRowGender row = new TableRowGender(womensDataSet.getString("Product Description"),
						womensDataSet.getString("Brands"), womensDataSet.getString("Quantity"),
						womensDataSet.getString("Available"));
				_womensTable.getItems().add(row);
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
