package com.drisq.minimalGraphicsProject.fx;

public class TableRowBrands {

	public String ProductDescription;

	public String Quantity;

	public String Available;

	public TableRowBrands(String ProductDescription, String Quantity, String Available) {

		this.ProductDescription = ProductDescription;
		this.Quantity = Quantity;
		this.Available = Available;

	}

	public String getProductDescriptionText() {
		return ProductDescription;
	}

	public String getQuantityText() {
		return Quantity;

	}

	public String getAvailableText() {
		return Available;

	}

}
