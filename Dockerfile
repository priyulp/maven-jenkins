FROM tomcat:latest
LABEL Author="KK"

# Copy WAR file from target directory (assuming it's built using Maven)
ADD target/*.war /usr/local/tomcat/webapps/

# Ensure the Tomcat binaries are executable
RUN chmod +x $CATALINA_HOME/bin

EXPOSE 8080
CMD ["catalina.sh", "run"]
