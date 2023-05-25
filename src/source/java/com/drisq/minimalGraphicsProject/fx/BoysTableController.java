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

public class BoysTableController implements DrisqController {

	public static final String FXML_RSC = "rsc/BoysTable.fxml";

	@FXML
	private Node _rootNode;

	@FXML
	private javafx.scene.control.TableView<TableRowGender> _boysTable;

	@FXML
	private TableColumn<TableRowGender, String> _boysProductDescriptionColumn;

	@FXML
	private TableColumn<TableRowGender, String> _boysBrandColumn;

	@FXML
	private TableColumn<TableRowGender, String> _boysQuantityColumn;

	@FXML
	private TableColumn<TableRowGender, String> _boysAvailableColumn;

	@FXML
	private Button _homeButton;

	@FXML
	private Button _backButton;

	private boolean updateOnExit;

	private Runnable runnable;

	public static BoysTableController newInstance(Window owner, String title) {
		Stage stage = FxUtil.newStage(owner, FXML_RSC, BoysTableController.class, title);
		return (BoysTableController) FxUtil.getController(stage.getScene().getRoot());
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

	public void initBoysTable(String boysQuery) throws SQLException {

		_boysTable.getItems().clear();
		_boysProductDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("productDescriptionText"));
		_boysBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brandsText"));
		_boysQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantityText"));
		_boysAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("availableText"));

		ResultSet boysDataSet = Database.boysQuery(boysQuery);
		StringBuilder boysDataString = new StringBuilder();

		try {
			while (boysDataSet.next()) {
				boysDataString.append(boysDataSet.getString("Product Description"));
				boysDataString.append(boysDataSet.getString("Brands"));
				boysDataString.append(boysDataSet.getString("Quantity"));
				boysDataString.append(boysDataSet.getString("Available"));
				TableRowGender row = new TableRowGender(boysDataSet.getString("Product Description"),
						boysDataSet.getString("Brands"), boysDataSet.getString("Quantity"),
						boysDataSet.getString("Available"));
				_boysTable.getItems().add(row);
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
