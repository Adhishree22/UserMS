package com.ecom.user.dto;



public class ProductDTO {

	String prodId;
	String productName;
	Double price;
	Integer stock;
	String description;
	String image;
	String sellerId;
	String category;
	String subcategory;
	Double productrating;
	

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Double getProductrating() {
		return productrating;
	}

	public void setProductrating(Double productrating) {
		this.productrating = productrating;
	}

	public ProductDTO() {
		super();
	}

	@Override
	public String toString() {
		return "ProductDTO [prodId=" + prodId + ", productName=" + productName + ", price=" + price + ", stock=" + stock
				+ ", description=" + description + ", image=" + image + ", sellerId=" + sellerId + ", category="
				+ category + ", subcategory=" + subcategory + ", productrating=" + productrating + "]";
	}


	public ProductDTO(String prodId, String productName,Double price, Integer stock,String description ,String image,
			String sellerId, String category, String subcategory, Double productrating) {
		this();
		this.prodId = prodId;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.image = image;
		this.sellerId = sellerId;
		this.category = category;
		this.subcategory = subcategory;
		this.productrating = productrating;
	}

}
