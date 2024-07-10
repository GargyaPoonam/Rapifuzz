package browserUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfiDataProvider {
	
	Properties pro;
	public ConfiDataProvider() throws IOException {
		
		File file = new File("/Users/poonamsharma/eclipse-workspace1/Erail.in/config/Config.properties");
		FileInputStream fis = new FileInputStream(file);
		
		pro = new Properties();
		pro.load(fis);
	}
	
	
	public String getDataFromConfig(String Keytosearch) {
		return pro.getProperty(Keytosearch);
		
	}
	
	public String getBrowser() {
		return pro.getProperty("browser");
		
	}
	public String getUrl() {
		return pro.getProperty("url");
		
	}

}
