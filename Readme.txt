Um den Service zu testen

1. Tomcat installieren
	- Linux apt-get install tomcat7
	- Windows am besten gleich XAMPP installieren
	
2. Ordner "digatus" in den "webapps" Ordner von Tomcat kopieren
	- Linux:	 "var/lib/tomcat7/webapps"
	- Windows:	 "C:\xampp\tomcat\webapps"
	
	Info:
	Jeder direkt im webapps Ordner liegende Ordner ist ein Tomcatprojekt
	Normalerweise befindet sich hier auch ein "Root" und ein "examples"-Projekt
	
	Also
	-tomcat/
		-bin/
		-conf/
		-lib/
		-logs/
		-temp/
		-webapps/
			-ROOT/
			-examples/
			-digatus/
				-WEB-INF/
					-classes/
					-lib/
					-web.xml
				-index.html
				-Transaction.js
				-TransactionTester.js
				
3. Tomcat starten
	- Linux: sudo service tomcat7 restart
	- Windows mit XAMPP tomcat im Control Pane stoppen und neu starten
		
	Info:
	Tomcat liesst die neue "web.xml" aus und leitet im "digatus"-Projekt alle HTTP-Request mit der Endung 
	"transactionservice/*" an das TransactionServlet weiter.
	
4. Testen
	http://localhost:8080/digatus -> index.html kleiner Testclient, der put und get methoden mit minimaler GUI aufruft
	http://localhost:8080/digatus/transactionservice -> angefordertes Backend
