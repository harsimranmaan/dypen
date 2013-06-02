Lab: javarmi

Contributors:
Harsimran Singh Maan
Kuntal Joshi
Prabal Sharma

Operating system:
Win 8, Win 7

Java compiler (version):
1.7.0_17

Compilation instructions:
There are three different Netbeans projects and can be complied in the following order:
	1. StockExInterface
	2. StockExServer
	3. StockExClient
ant build scripts are included in each of the project. Using Netbeans for build makes the process run smoother.


Execution instructions:
Before execution, a local Mysql database can be configured(although a MySql instance on Amazon EC2 is set as default which might have some users already setup(hsm(client), prabal(admin)).)
Each project is set to use the "config.properties" file in project root which has configuration details for the specific project.
The database password is encrypted using AES.
Encryption utility can be found at https://github.com/harsimranmaan/red/tree/master/EnDec or the jar can used directly at https://github.com/harsimranmaan/red/blob/master/Red/lib/EnDec.jar

The client can be configured into two types of clients: Admin and non-admin
By default the client console is in non-admin mode. In order to change it to admin mode, set the property "admin" to true in config.properties of the client.
Note: Only creation of new user as admin/non-admin is decoded by the client. Existing user's access is decided at the server.

Run the server first.
Run the client


Known bugs: 
NONE

References:
stackoverflow.com
http://littletutorials.com/2008/07/14/the-10-minutes-getting-started-with-rmi-tutorial/
https://github.com/harsimranmaan/red
https://github.com/whistler/ActiveEnroll


