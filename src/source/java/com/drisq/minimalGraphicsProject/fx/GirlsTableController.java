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

public class GirlsTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/GirlsTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowGender> _girlsTable;

	@FXML
	private TableColumn<TableRowGender, String> _girlsProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowGender, String> _girlsBrandColumn;

	@FXML
	private TableColumn<TableRowGender, String> _girlsQuantityColumn;

	@FXML
	private TableColumn<TableRowGender, String> _girlsAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static GirlsTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, GirlsTableController.class, title);
		return (GirlsTableController) FxUtil.getController(stage.getScene().getRoot());
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

	public void initGirlsTable(String girlsQuery) throws SQLException {

		_girlsTable.getItems().clear();
		_girlsProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_girlsBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brandsText"));
		_girlsQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_girlsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet girlsDataSet = Database.mensQuery(girlsQuery);
		StringBuilder girlsDataString = new StringBuilder();

		try {
			while (girlsDataSet.next()) {
				girlsDataString.append(girlsDataSet.getString("Product Description"));
				girlsDataString.append(girlsDataSet.getString("Brands"));
				girlsDataString.append(girlsDataSet.getString("Quantity"));
				girlsDataString.append(girlsDataSet.getString("Available"));
				TableRowGender row = new TableRowGender(girlsDataSet.getString("Product Description"),
						girlsDataSet.getString("Brands"), girlsDataSet.getString("Quantity"),
						girlsDataSet.getString("Available"));
				_girlsTable.getItems().add(row);
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
