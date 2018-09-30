package com.fairdapp.bank.helper.runner.bank.config;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Reader {
	
	public List<Config> readconfigs(String folderpath){
		List<Config> result = new ArrayList<Config>();
		List<Properties> configs = this.readProperties( new File(folderpath));
		
		for(Properties p:configs) {
			Config config =  new Config();
			config.setConfigName( p.getProperty("configName"));
			config.setContractAddress(p.getProperty( "contract.address" ));
			config.setDifferNopayRatio(p.getProperty( "differ.nopay.ratio"));
			config.setDifferPayRatio(p.getProperty( "differ.pay.ratio"));
			config.setDifferSeconds(p.getProperty( "differ.seconds"));
			config.setGasLimit(p.getProperty( "gas.limit"));
			config.setGasPrice(p.getProperty( "gas.price"));
			config.setNodeUrl(p.getProperty( "node.url"));
			config.setPayCapitalProtection(p.getProperty( "pay.capital.protection"));
			config.setPayLockdownStages(p.getProperty( "pay.lockdown.stages"));
			config.setPayValueMorest(p.getProperty( "pay.value.morest"));
			config.setPrivateKey(p.getProperty( "private.key"));
			config.setPublicKey(p.getProperty( "public.key"));
			config.setSendTimes( p.getProperty( "send.times") );
			config.setNickname(  p.getProperty( "referral.address") );
			
			result.add(config);
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
