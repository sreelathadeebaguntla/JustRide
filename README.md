-To start DynamoDB local database:

Step 1: Download AWS DynamoDB to your local system.

Step 2: Start the DynamoDB database with following command pointing to dynamodb.jar file just downloaded.

     Windows: java -D"java.library.path=./DynamoDBLocal_lib" -jar DynamoDBLocal.jar
     Mac: java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

- For additional info, refer the steps listed at https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

-This AWS DynamoDB will be at the port 8000. To check the connection to the 8000 port use below command from powershell
      Test-NetConnection -ComputerName localhost -Port 8000

-To list the DynamoDB tables, use the below command from powershell(you should have AWS CLI)
      aws dynamodb list-tables --endpoint-url http://localhost:8000

-To configure aws DynamoDB, use the below commandfrom powershell(you should have AWS CLI)
      aws configure

-Check AWS KeyID, AWS Access Key. More details are given in https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

-Quick guide to integerate DynamoDB to Springboot application is given in https://www.baeldung.com/spring-data-dynamodb
      
