package com.drisq.minimalGraphicsProject.fx;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.drisq.util.fx.DrisqController;
import com.drisq.util.fx.FxUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MensTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/MensTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowMens> _mensTable;

	@FXML
	private TableColumn<TableRowMens, String> _mensItemCode;

	@FXML
	private TableColumn<TableRowMens, String> _mensGender;

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

		initMensTable();

	}

	private void initMensTable() throws SQLException {

		ResultSet mensTables = Database.testQuery();
		StringBuilder mensTable = new StringBuilder();

		try {

			while (mensTables.next()) {
				mensTable.append(mensTables.getInt("Item Code"));
				mensTable.append(mensTables.getString("Gender"));
				TableRowMens row = new TableRowMens(mensTables.getString("Item Code"), mensTables.getString("Gender"));
				_mensTable.getItems().add(row);
			}

		} catch (SQLException e) {
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
