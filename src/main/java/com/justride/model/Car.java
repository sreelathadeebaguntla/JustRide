package com.justride.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

@DynamoDBTable(tableName = "Car")
public class Car {

	@DynamoDBHashKey
	@DynamoDBAutoGeneratedKey
	private String id;
	@DynamoDBAttribute
	private String name;
	@DynamoDBAttribute
	private String make;
	@DynamoDBTypeConvertedEnum
	@DynamoDBAttribute
	private BodyTypeEnum bodyType;
	@DynamoDBAttribute
	private String vin;
	@DynamoDBAttribute
	private String color;
	// @ElementCollection
	@DynamoDBAttribute
	private List<String> details;
	// @ElementCollection
	@DynamoDBAttribute
	private List<String> features;

	public Car() {

	}

	public Car(String name, String make, BodyTypeEnum bodyType, String vin, String color, List<String> details,
			List<String> features) {
		super();
		this.name = name;
		this.make = make;
		this.bodyType = bodyType;
		this.vin = vin;
		this.color = color;
		this.details = details;
		this.features = features;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public BodyTypeEnum getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyTypeEnum bodyType) {
		this.bodyType = bodyType;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", make=" + make + ", bodyType=" + bodyType + ", vin=" + vin
				+ ", color=" + color + ", details=" + details + ", features=" + features + "]";
	}

}
