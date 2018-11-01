# FitFriends
To connect to the FitFriends database, see the ConnectionManager class.
/**
 * ConnectionManager uses the MySQL Connector/J driver to connect to your local MySQL instance.
 * 
 * Instructions:
 * 1. Install MySQL Community Server. During installation, you will need to set up a user,
 * password, and port. Keep track of these values.
 * 2. Download and install Connector/J: http://dev.mysql.com/downloads/connector/j/
 * 3. Add the Connector/J JAR to your buildpath. This allows your application to use the
 * Connector/J library. You can add the JAR using either of the following methods:
 *   A. When creating a new Java project, on the "Java Settings" page, go to the 
 *   "Libraries" tab.
 *   Click on the "Add External JARs" button.
 *   Navigate to the Connector/J JAR. On Windows, this looks something like:
 *   C:\Program Files (x86)\MySQL\Connector.J 5.1\mysql-connector-java-5.1.34-bin.jar
 *   B. If you already have a Java project created, then go to your project properties.
 *   Click on the "Java Build Path" option.
 *   Click on the "Libraries" tab, click on the "Add External Jars" button, and
 *   navigate to the Connector/J JAR.
 * 4. Update the 4 "private final" variables in the ConnectionManager class.
 */
