package com.car.rental.justride.repository;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.car.rental.justride.modal.Car;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.car.rental.justride.repository")
public class DynamoDBConfig {

	@Value("${amazon.aws.accesskey}")
	private String awsAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String awsSecretKey;

	@Value("${amazon.dynamodb.endpoint}")
	private String awsDynamoDBEndpoint;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		 AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
	                .withEndpointConfiguration(new AwsClientBuilder
	                        .EndpointConfiguration(awsDynamoDBEndpoint, "us-east-2"))
	                .withCredentials(new AWSStaticCredentialsProvider(
	                        new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
	                .build();

	        // Create table if not exists
	        DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder().build();
	        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB, mapperConfig);
	        CreateTableRequest createTableRequest = dynamoDBMapper
	                .generateCreateTableRequest(Car.class)
	                .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // Adjust the throughput as needed
	        try {
	            amazonDynamoDB.createTable(createTableRequest);
	        } catch (ResourceInUseException e) {
	            System.out.println("Table already exists");
	        }

	        return amazonDynamoDB;
	    }
}