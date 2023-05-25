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

public class MensTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/MensTable.fxml";

	@FXML
	private Node _rootNode;

	// Creates the table and columns using the TableRowGender table view

	@FXML
	private javafx.scene.control.TableView<TableRowGender> _mensTable;

	@FXML
	private TableColumn<TableRowGender, String> _mensProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowGender, String> _mensBrandColumn;

	@FXML
	private TableColumn<TableRowGender, String> _mensQuantityColumn;

	@FXML
	private TableColumn<TableRowGender, String> _mensAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static MensTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, MensTableController.class, title);
		return (MensTableController) FxUtil.getController(stage.getScene().getRoot());
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

	public void initMensTable(String mensQuery) throws SQLException {

		// clears the table first and then creates the cells of the table

		_mensTable.getItems().clear();
		_mensProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_mensBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brandsText"));
		_mensQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_mensAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		// gets the result set from the Database method using the query string and
		// creates a string builder

		ResultSet mensDataSet = Database.mensQuery(mensQuery);
		StringBuilder mensDataString = new StringBuilder();

		// adds each step of the result set to the string builder

		try {
			while (mensDataSet.next()) {
				mensDataString.append(mensDataSet.getString("Product Description"));
				mensDataString.append(mensDataSet.getString("Brands"));
				mensDataString.append(mensDataSet.getString("Quantity"));
				mensDataString.append(mensDataSet.getString("Available"));

				// creates a row using the above

				TableRowGender row = new TableRowGender(mensDataSet.getString("Product Description"),
						mensDataSet.getString("Brands"), mensDataSet.getString("Quantity"),
						mensDataSet.getString("Available"));

				// adds the row to the table

				_mensTable.getItems().add(row);

				// will then repeat back round the the first step, in this case "Product
				// Description" therefore starting a new row
			}
		}

		// a catch if you cannot get data from the result set

		catch (SQLException e) {
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

	// Does the runnable method to close both this and the search instances

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
