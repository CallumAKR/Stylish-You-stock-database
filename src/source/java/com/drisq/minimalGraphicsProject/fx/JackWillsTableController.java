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

public class JackWillsTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/JackWillsTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowBrands> _jackWillsTable;

	@FXML
	private TableColumn<TableRowBrands, String> _jackWillsProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowBrands, String> _jackWillsQuantityColumn;

	@FXML
	private TableColumn<TableRowBrands, String> _jackWillsAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static JackWillsTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, JackWillsTableController.class, title);
		return (JackWillsTableController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() throws SQLException {

	}

	public void initJackWillsTable(String jackWillsQuery) throws SQLException {

		_jackWillsTable.getItems().clear();
		_jackWillsProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_jackWillsQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_jackWillsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet jackWillsDataSet = Database.jackWillsQuery(jackWillsQuery);
		StringBuilder jackWillsDataString = new StringBuilder();

		try {
			while (jackWillsDataSet.next()) {
				jackWillsDataString.append(jackWillsDataSet.getString("Product Description"));
				jackWillsDataString.append(jackWillsDataSet.getString("Quantity"));
				jackWillsDataString.append(jackWillsDataSet.getString("Available"));
				TableRowBrands row = new TableRowBrands(jackWillsDataSet.getString("Product Description"),
						jackWillsDataSet.getString("Quantity"), jackWillsDataSet.getString("Available"));
				_jackWillsTable.getItems().add(row);
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
