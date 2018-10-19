package com.fairdapp.bank.helper.runner.exchange.config;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Reader {
	
	public List<ExchangeOneConfig> readconfigs(String folderpath){
		List<ExchangeOneConfig> result = new ArrayList<ExchangeOneConfig>();
		List<Properties> configs = this.readProperties( new File(folderpath));
		
		for(Properties p:configs) {
			
			for(int i = 1 ; i <100 ;i++ ) {
				 String pk = p.getProperty( "privateKey."+i) ;
				 if(pk!=null) {
					 	ExchangeOneConfig config =  new ExchangeOneConfig();
						config.setConfigName( 		p.getProperty("configName"));
						config.setEtherValue(  		p.getProperty("etherValue"));  
						config.setGasLimit( 		p.getProperty("gasLimit")  );
						config.setGasPrice( 		p.getProperty( "gasPrice") );
						config.setNodeUrl( 			p.getProperty( "NodeUrl")  );
						config.setStartTimeSecond(	p.getProperty( "startTimeSecond") );
						config.setTimesTry(  		p.getProperty( "timesTry")   );
						config.setContractAddress(  p.getProperty( "contractAddress")   );
						config.setReferredAddress(  p.getProperty( "referredAddress")   );
						
						config.setPrivateKey(pk);
						
						result.add(config);
				 }else {
					 break;
				 }
			}
			
		}
		
		return result;
	}
	
	List<Properties> readProperties(File folder){
		
		if(folder!=null && folder.isDirectory()) {
			File[] files = folder.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					 if(name.indexOf(".properties")>0) {
						 return true;
					 }
					return false;
				}});
			
			List<Properties> result = new ArrayList<Properties>();
			for(File f:files) {
				Properties p =    new Properties();
				try {
					p.load(  new FileReader(f));
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.setProperty("configName", f.getName());
				result.add( p );
			}
			return result;
			
		}
		
		return Collections.emptyList();
		
	}
}
