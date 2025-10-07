# RESTful Booker API Automation Framework

This project demonstrates an API automation framework developed in Java using Maven, RestAssured, JUnit 5, AssertJ, and Allure Report for testing the [RESTful Booker API](https://restful-booker.herokuapp.com/apidoc/index.html).

The main goal of this project is to showcase code organization, structure, and the tools utilized to set up a working solution for API testing.

## Table of Contents
1.  [Project Overview](#1-project-overview)
2.  [Features](#2-features)
3.  [Technologies Used](#3-technologies-used)
4.  [Prerequisites](#4-prerequisites)
5.  [Setup Instructions](#6-setup-instructions)
6.  [How to Run Tests](#7-how-to-run-tests)
7.  [Allure Report](#8-allure-report)
8.  [Future Enhancements](#10-future-enhancements)
9.  [Contact](#11-contact)

## 1. Project Overview
This framework provides automated tests for several endpoints of the RESTful Booker API, focusing on demonstrating best practices in API test automation. The API is a mock service and does not persist state between calls, requiring tests to be self-contained and idempotent.

## 2. Features
*   **API Interactions:** Automated testing for `Auth` and `Booking` endpoints.
*   **CRUD Operations:** Covers Create, Update, and Delete operations for bookings.
*   **Data Serialization/Deserialization:** Uses POJOs (Plain Old Java Objects) for clear and type-safe request/response handling.
*   **Dynamic Test Data:** Utilizes a factory pattern to generate random test data for bookings, ensuring test independence.
*   **Modular Design:** Organized into distinct layers (config, models, API services, utilities, tests) for maintainability and scalability.
*   **Comprehensive Reporting:** Integrated with Allure Report for rich, interactive, and detailed test execution reports.

## 3. Technologies Used
*   **Java 11+**: Programming Language
*   **Maven**: Build Automation Tool
*   **RestAssured (v5.4.0)**: HTTP Client for API testing
*   **JUnit 5 (v5.10.0)**: Testing Framework
*   **AssertJ (v3.25.1)**: Fluent Assertion Library
*   **Jackson Databind (v2.16.0)**: JSON processing (serialization/deserialization)
*   **Lombok (v1.18.42)**: Boilerplate code reduction for POJOs
*   **Allure Report (v2.30.0 for adapter, v2.16.1 for Maven plugin)**: Test Reporting Tool

## 4. Prerequisites
Before running the tests, ensure you have the following installed:
*   **Java Development Kit (JDK) 11 or higher**: Make sure `JAVA_HOME` environment variable is set correctly.
    *   You can check your Java version by running `java -version` in your terminal.
*   **Apache Maven 3.6.0 or higher**:
    *   You can check your Maven version by running `mvn -v` in your terminal.

## 5. Setup Instructions
1.  **Clone the repository:**
    ```bash
    git clone https://github.com/umitolmez/restful-booker-api-test
    cd restful-booker-api-test
    ```
2.  **Verify Java and Maven Setup:**
    Ensure your JDK and Maven installations are correct.
    ```bash
    java -version
    mvn -v
    ```
3.  **Build the project dependencies:**
    This command will download all necessary libraries defined in `pom.xml`.
    ```bash
    mvn clean install
    ```
    *   **Note on Java Version:** If you encounter `invalid target release` errors during `mvn clean install` or `mvn clean test`, ensure your `pom.xml`'s `<maven.compiler.source>` and `<maven.compiler.target>` match your installed JDK version (e.g., `11` for Java 11, `17` for Java 17). The current `pom.xml` targets Java 11.

## 6. How to Run Tests

### a. Run All Tests from Command Line (Recommended for CI/CD)
Navigate to the project's root directory in your terminal and execute:
```bash
mvn clean test
```

b. Run Specific Test Class
```bash
mvn clean test -Dtest=BookingTests
```

c. Run Specific Test Method
```bash
mvn clean test -Dtest=BookingTests#testCreateBookingSuccessfully
```

## 7. Allure Report
Allure Report provides an interactive and detailed visualization of test results.

1.Run tests to generate Allure results: 
```bash
mvn clean test
```
This will create Allure result files (e.g., .json, .xml) in the target/allure-results directory.

2.Generate and open the Allure HTML report:
```bash
mvn allure:report # Generates the HTML report in target/allure-report
mvn allure:serve  # Starts a local web server and opens the report in your default browser
```

## 8. Future Enhancements
- Parameterized Tests: Implement data-driven testing for various scenarios (e.g., testing different booking values, negative cases) using @ParameterizedTest from JUnit 5.
- Schema Validation: Add JSON Schema validation using json-schema-validator (already included in pom.xml) to ensure API responses conform to expected structures.
- Error Handling: Implement more robust error handling and assertions for negative test scenarios.
- Environment Configuration: Enhance ConfigReader to support different environments (dev, QA, prod) via Maven profiles or environment variables.
- Advanced Logging: Integrate a more configurable logging framework like Logback to capture detailed request/response logs.
- Parallel Test Execution: Configure Maven Surefire Plugin for parallel test execution to reduce overall test run time.

## 9. Contact

Umit Olmez,
Test Automation Engineer,
umitolmez455@gmail.com, +905457950348


