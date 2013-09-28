Java EE 6 Web Application
=========================

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

$ /local/opt/glassfish3/bin/asadmin deploy target/users-web-app.war

List application in domain:

$ /local/opt/glassfish3/bin/asadmin list-applications

Open in browser:

http://localhost:8080/users-web-app

Check that data is in database:

$ psql -c "select * from users;" -U search_users search_users

Undeploy application:

$ /local/opt/glassfish3/bin/asadmin undeploy users-web-app

Stop domain:

$ /opt/glassfish3/bin/asadmin stop-domain --domaindir ./ domain1
