-To start DynamoDB local database:

Step 1: We have to download AWS DynamoDB to you local system.
Step 2: Start the DynamoDB database with following command from the location where your dynamodb.jar file
      java -D"java.library.path=./DynamoDBLocal_lib" -jar DynamoDBLocal.jar
-Steps are given in https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

-This AWS DynamoDB will be at the port 8000. To check the connection to the 8000 port use below command from powershell
      Test-NetConnection -ComputerName localhost -Port 8000

-To list the DynamoDB tables, use the below command from powershell(you should have AWS CLI)
      aws dynamodb list-tables --endpoint-url http://localhost:8000

-To configure aws DynamoDB, use the below commandfrom powershell(you should have AWS CLI)
      aws configure

-Check AWS KeyID, AWS Access Key. More details are given in https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

-Quick guide to integerate DynamoDB to Springboot application is given in https://www.baeldung.com/spring-data-dynamodb
      
