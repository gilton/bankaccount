# Bank Account

[![License](http://img.shields.io/:License-GNU_3.0-blue.svg)](https://github.com/gilton/bankaccount/blob/main/LICENSE)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) 3.x.x version sample bank app.

## Requirements

For building and running the application you need:

- [JDK 17](http://www.oracle.com/technetwork/java/javase/downloads/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `org.labs.bank.banksling.BankslingApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
<!---
If you want to access the app from outside your OpenShift installation, you have to expose the springboot-sample-app service:

```shell
oc expose springboot-sample-app --hostname=www.example.com
```
-->

After the application runs, navigate to `http://localhost:8080/swagger-ui.html` in your web browser to access the Swagger UI portal.

## Copyright

Released under the GNU General Public License 3.0. See the [LICENSE](https://github.com/gilton/bankaccount/blob/main/LICENSE) file.
