package config;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
 
public class Config
{
   Properties configFile;
   public Config()
   {
	configFile = new java.util.Properties();
	try {
	  configFile.load(this.getClass().getClassLoader().
	  getResourceAsStream("config/config.properties"));
	}catch(Exception eta){
	    eta.printStackTrace();
	}
   }
 
   public String getProperty(String key)
   {
	String value = this.configFile.getProperty(key);
	return value;
   }
   
   public static void main(String args[]){
	   Config config = new Config();
	   System.out.println(config.getProperty("test.main"));
   }
   
}

