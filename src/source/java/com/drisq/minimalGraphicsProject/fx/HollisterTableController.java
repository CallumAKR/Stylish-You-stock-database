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

public class HollisterTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/HollisterTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowBrands> _hollisterTable;

	@FXML
	private TableColumn<TableRowBrands, String> _hollisterProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowBrands, String> _hollisterQuantityColumn;

	@FXML
	private TableColumn<TableRowBrands, String> _hollisterAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static HollisterTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, HollisterTableController.class, title);
		return (HollisterTableController) FxUtil.getController(stage.getScene().getRoot());
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

	public void initHollisterTable(String hollisterQuery) throws SQLException {

		_hollisterTable.getItems().clear();
		_hollisterProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_hollisterQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_hollisterAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet hollisterDataSet = Database.hollisterQuery(hollisterQuery);
		StringBuilder hollisterDataString = new StringBuilder();

		try {
			while (hollisterDataSet.next()) {
				hollisterDataString.append(hollisterDataSet.getString("Product Description"));
				hollisterDataString.append(hollisterDataSet.getString("Quantity"));
				hollisterDataString.append(hollisterDataSet.getString("Available"));
				TableRowBrands row = new TableRowBrands(hollisterDataSet.getString("Product Description"),
						hollisterDataSet.getString("Quantity"), hollisterDataSet.getString("Available"));
				_hollisterTable.getItems().add(row);
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
