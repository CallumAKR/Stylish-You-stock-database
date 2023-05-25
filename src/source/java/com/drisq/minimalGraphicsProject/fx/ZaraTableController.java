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

public class ZaraTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/ZaraTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowBrands> _zaraTable;

	@FXML
	private TableColumn<TableRowBrands, String> _zaraProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowBrands, String> _zaraQuantityColumn;

	@FXML
	private TableColumn<TableRowBrands, String> _zaraAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static ZaraTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, ZaraTableController.class, title);
		return (ZaraTableController) FxUtil.getController(stage.getScene().getRoot());
	}

	@Override
	public Node getRootNode() {
		return _rootNode;
	}

	@FXML
	private void initialize() throws SQLException {

	}

	public void initZaraTable(String zaraQuery) throws SQLException {

		_zaraTable.getItems().clear();
		_zaraProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_zaraQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_zaraAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet zaraDataSet = Database.zaraQuery(zaraQuery);
		StringBuilder zaraDataString = new StringBuilder();

		try {
			while (zaraDataSet.next()) {
				zaraDataString.append(zaraDataSet.getString("Product Description"));
				zaraDataString.append(zaraDataSet.getString("Quantity"));
				zaraDataString.append(zaraDataSet.getString("Available"));
				TableRowBrands row = new TableRowBrands(zaraDataSet.getString("Product Description"),
						zaraDataSet.getString("Quantity"), zaraDataSet.getString("Available"));
				_zaraTable.getItems().add(row);
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
