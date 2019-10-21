1-Environnement 
	java 8	
	SprinBoot 2
	maven 3
	JUnit 4
	Swagger 2.9

2-Instruction :

	-update java_home to java 8 jdk path
	-update maven_home to maven 3 path
	-use the  cmd toe execute "command lines" in the pom.xml path:
		>mvn clean install
		>mvn package spring-boot:repackage
		>cd target 
		>java -jar kata-api-1.0-SNAPSHOT.jar

3-Instruction to test:

	-Use swagger UI to test webservices:
		link:http://localhost:8080/swagger-ui.html
