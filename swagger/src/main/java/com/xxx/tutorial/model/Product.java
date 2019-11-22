package com.xxx.tutorial.model;

import java.io.Serializable;

/**
 * 
 * @author wangmengjun
 *
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	/**ID*/
	private Long id;

	/**产品名称*/
	private String name;

	/**产品型号*/
	private String productClass;

	/**产品ID*/
	private String productId;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the productClass
	 */
	public String getProductClass() {
		return productClass;
	}

	/**
	 * @param productClass
	 *            the productClass to set
	 */
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productClass=" + productClass + ", productId=" + productId
				+ "]";
	}

}