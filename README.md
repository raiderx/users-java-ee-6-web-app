Java EE 6 Web Application
=========================

This project is my first attempt to develop an application for Java EE 6.

The application represents user list with ability to:

- view list of existing users,

- create new user,

- view existing user,

- edit existing user,

- remove existing user,

- find existing user by his name.

The application was developed using:

- Apache Lucene 4.4.0

- Apache Maven 3.1.0

- GlassFish 3.1.1.2 (Java EE Web Profile)

- IntelliJ IDEA 11.1.5

- PostgreSQL 9.1

- Ubuntu 12.04

Requirements:

1) GlassFish 3.1 (JPA 2.0, JSF 2.1)

2) PostgreSQL 9.1

Create database user:

$ createuser -U postgres -D -P -R -S search_users

Enter password for new role:

Enter it again:

Create database:

$ createdb -E UTF-8 -O search_users -U postgres search_users

Create domain:

$ /opt/glassfish3/bin/asadmin create-domain --domaindir ./ domain1

Start domain:

$ /opt/glassfish3/bin/asadmin start-domain --domaindir ./ domain1

Add JDBC pool:

$ /opt/glassfish3/bin/asadmin add-resources ./setup/glassfish-resources.xml

Build application:

$ mvn package

Deploy application:

$ /opt/glassfish3/bin/asadmin deploy target/users-web-app.war

List application in domain:

$ /opt/glassfish3/bin/asadmin list-applications

Open in browser:

http://localhost:8080/users-web-app/

Check that data is in database:

$ psql -c "select * from users;" -U search_users search_users

Undeploy application:

$ /opt/glassfish3/bin/asadmin undeploy users-web-app

Stop domain:

$ /opt/glassfish3/bin/asadmin stop-domain --domaindir ./ domain1
