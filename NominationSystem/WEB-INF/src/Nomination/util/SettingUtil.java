package Nomination.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SettingUtil {
	private static SettingUtil inst = new SettingUtil();
	//こうしないと取れない。。。
	private static final String PRPFNAME = "/Nomination/jdbc.properties";

	private Properties prop;
	
	private SettingUtil(){
		prop = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PRPFNAME);
		try{
			prop.load(in);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static String getProperty(String key){
		return inst.prop.getProperty(key);
	}
}
