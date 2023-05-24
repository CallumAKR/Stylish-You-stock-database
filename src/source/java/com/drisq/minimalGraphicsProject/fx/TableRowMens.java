package com.drisq.minimalGraphicsProject.fx;

public class TableRowMens {

	public String ProductDescription;

	public String Brands;

	public String Quantity;

	public String Available;

	public TableRowMens(String ProductDescription, String Brands, String Quantity, String Available) {

		this.ProductDescription = ProductDescription;
		this.Brands = Brands;
		this.Quantity = Quantity;
		this.Available = Available;

	}

	public String getProductDescriptionText() {
		return ProductDescription;
	}

	public String getBrandsText() {
		return Brands;

	}

	public String getQuantityText() {
		return Quantity;

	}

	public String getAvailableText() {
		return Available;

	}

}
