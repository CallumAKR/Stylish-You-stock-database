package com.drisq.minimalGraphicsProject.fx;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MensTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/MensTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowMens> _mensTable;

	@FXML
	private TableColumn<TableRowMens, String> _mensProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowMens, String> _mensBrandColumn;

	@FXML
	private TableColumn<TableRowMens, String> _mensQuantityColumn;

	@FXML
	private TableColumn<TableRowMens, String> _mensAvailableColumn;

	private boolean updateOnExit;

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

	}

	public void initMensTable(String mensQuery) throws SQLException {

		_mensTable.getItems().clear();
		_mensProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_mensBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brandsText"));
		_mensQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_mensAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet mensDataSet = Database.mensQuery(mensQuery);
		StringBuilder mensDataString = new StringBuilder();

		try {
			while (mensDataSet.next()) {
				mensDataString.append(mensDataSet.getString("Product Description"));
				mensDataString.append(mensDataSet.getString("Brands"));
				mensDataString.append(mensDataSet.getString("Quantity"));
				mensDataString.append(mensDataSet.getString("Available"));
				TableRowMens row = new TableRowMens(mensDataSet.getString("Product Description"),
						mensDataSet.getString("Brands"), mensDataSet.getString("Quantity"),
						mensDataSet.getString("Available"));
				_mensTable.getItems().add(row);
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
	protected final void _launchExitButton() {
		updateOnExit = false;
		done();
	}

	private void done() {
		((Stage) (_rootNode.getScene().getWindow())).close();
	}

}
