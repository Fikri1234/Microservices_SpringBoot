# Microservices_SpringBoot
Spring boot microservices with Java 8, MySQL, MongoDB, eureka, zuul, feign, ribbon, zipkin, sleuth


## Prerequisites

1. Java 8
2. JPA Mysql
3. Mongo DB
4. Spring Boot Actuator (Optional)
5. Dev Tools (Optional)
6. Lombok (Optional)
7. Eureka
8. Zuul
9. Feign
10. Ribbon
11. Zipkin
12. Sleuth

## Setup MySQL
Import dump.sql to your MySQL

## Setup Mongo DB
- In the Environment Variable,click New System Variable then copy the path where Mongo DB have installed.
[Mongo DB System Variable](asset/mongo1.PNG)

- Click "Path" and then "Edit". Add MONGODB **bin** folder.
[Mongo DB System Variable](asset/mongo2.PNG)

- Open command line window and run `mongod --directoryperdb --dbpath "D:\Project\MongoDB\db" --logpath "D:\Project\MongoDB\log\mongo.log" --logappend --install`
[Install the mongod.exe as a service](asset/mongo3.PNG)

- Start a MongoDB service with the following command `mongod --directoryperdb --dbpath "D:\Project\MongoDB\db"`
[Start a MongoDB service](asset/mongo4.PNG)

- Create a database to work with.
[Create Database](asset/mongo5.PNG)

- Import family_member.json to your Mongo DB

## Eureka Server

Create Project for Eureka Server and make sure to include these dependencies: Web, Eureka Server.
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

Code for main Spring Boot application class file is as shown below 
```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEurekaServerApplication.class, args);
	}

}
```

In the application.properties, add configuration is given below
```
spring.application.name=eureka-server
server.port=8761

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

hit the URL http://localhost:8761/ in your web browser and you can find the Eureka Server running on the port 8761 as shown below

![Alt text](asset/eureka_server1.PNG?raw=true "Eureka Server")

## User Service

Create microservices that responsible to handle CRUD operation of user. Choose following dependencies; Actuator (Optional), Dev Tools(Optional), Lombok(Optional), JPA MySQL, Web, Eureka Client, Feign, Ribbon, Zipkin, Sleuth.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-netflix-ribbon</artifactId>
    <version>2.2.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

Go to the main Spring Boot application class and add below annotations;
```
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MsMySqlMstUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMySqlMstUserApplication.class, args);
	}

}
```

Create service to consume user detail, and add two annotations @FeignClient and @RibbonClient to enable ribbon and feign client.
```
@FeignClient(name="master-user-detail-service")
@RibbonClient(name="master-user-det ail-service")
public interface MUserDetailConsumer {
	
	@GetMapping("api/mst-user-dtl/user/{id}")
	public MUserDetailDTO getMUserDetailByMUserId(@PathVariable Long id);

}
```

In the application.properties, add configuration is given below
```
spring.application.name=master-user-service
server.port=8100
server.servlet.context-path=/api

spring.datasource.url=jdbc:mysql://localhost:3306/ms_account_dev?
spring.datasource.username=root
spring.datasource.password=P@ssw0rd
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.database-platform= org.hibernate.dialect.MySQL5Dialect

# Number of ms to wait before throwing an exception if no connection available
spring.datasource.tomcat.max-wait=1000

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

master-user-detail-service.ribbon.listOfServers=localhost:8200,localhost:8201
master-user-detail-service.ribbon.ServerListRefreshInterval=15000 
```

## User Detail Service

Create microservices that responsible to handle CRUD operation of user detail. Choose following dependencies; Actuator (Optional), Dev Tools(Optional), Lombok(Optional), JPA MySQL, Web, Eureka Client, Zipkin, Sleuth.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-netflix-ribbon</artifactId>
    <version>2.2.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

Go to the main Spring Boot application class and add below annotations;
```
@EnableEurekaClient
@EnableDiscoveryClient\
@EnableFeignClients
@SpringBootApplication
public class MsMySqlMstUserDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMySqlMstUserDetailApplication.class, args);
	}

}
```

In the application.properties, add configuration is given below
```

spring.application.name=master-user-detail-service
server.port=8200
server.servlet.context-path=/api

spring.datasource.url=jdbc:mysql://localhost:3306/ms_account_dev?
spring.datasource.username=root
spring.datasource.password=P@ssw0rd
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.database-platform= org.hibernate.dialect.MySQL5Dialect

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

family-member-service.ribbon.listOfServers=localhost:8300,localhost:8301
family-member-service.ribbon.ServerListRefreshInterval=15000 
```

## Family Member Service

Create microservices that responsible to handle CRUD operation of family member. Choose following dependencies; Actuator (Optional), Dev Tools(Optional), Lombok(Optional), Mongo DB, Web, Eureka Client, Zipkin, Sleuth.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

Go to the main Spring Boot application class and add below annotations;
```
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class MsMongoDbFamilyMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMongoDbFamilyMemberApplication.class, args);
	}

}
```

Create service to consume user detail, and add two annotations @FeignClient and @RibbonClient to enable ribbon and feign client.
```
@FeignClient(name="family-member-service")
@RibbonClient(name="family-member-service")
public interface FamilyMemberConsumer {
	
	@GetMapping("api/family-member/userId/{id}")
	public FamilyMemberDTO findByUserId(@PathVariable Long id);

}

```

In the application.properties, add configuration is given below
```
spring.application.name=family-member-service
server.port=8300
server.servlet.context-path=/api

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=cobates

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```


## Gateway Service

Create Gateway-Service (Zuul proxy) Application and register it in Eureka server.

In the `pom.xml` add Eureka Client and Zuul dependencies.
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

Code for main Spring Boot application class file is as shown below 
```
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class MsZuulEdgeGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsZuulEdgeGatewayApplication.class, args);
	}

}
```

In the application.properties, add configuration is given below
```
spring.application.name=zuul-gateway
server.port=8900

zuul.routes.master-user-service.path = /master-user/**
zuul.routes.master-user-service.service-id=master-user-service
zuul.routes.master-user-service.strip-prefix=true

zuul.routes.master-user-detail-service.path = /master-user-detail/**
zuul.routes.master-user-detail-service.service-id=master-user-detail-service
zuul.routes.master-user-detail-service.strip-prefix=true
```


Now hit http://localhost:8761/ in browser and check that eureka server is running well with all microservices are registed.
![Alt text](asset/eureka_server2.PNG?raw=true "Eureka Server")

Start Zipkin UI via Docker by type following code to run image directly `docker run -d -p 9411:9411 openzipkin/zipkin`.
Open your browser and hit http://localhost:9411/zipkin/ to measure where service has spent more time.


**Sample Payload**
1. [Get User By Id with Ribbon load balancer port 8200](asset/rest_user_by_id_1.PNG)
2. [Get User By Id with Ribbon load balancer port 8201](asset/rest_user_by_id_2.PNG)
3. [Get User Detail By Id with Zuul Gateway](asset/zuul_rest_user_detail_by_id.PNG)
4. [List Microservices Trace by Zipkin](asset/zipkin1.PNG)
5. [Microservices Interaction by Zipkin](asset/zipkin2.PNG)
