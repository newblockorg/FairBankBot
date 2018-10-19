package com.fairdapp.bank.helper.runner.apexone.runner;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.fairdapp.bank.helper.runner.apexone.config.ApexOneConfig;
import com.fairdapp.bank.helper.runner.apexone.config.Reader;
 

public class ApexoneMain {

	private static ExecutorService executor = java.util.concurrent.Executors.newCachedThreadPool();
	
	public static void main(String[] args) {
	 
		if(args.length<1) {
			System.exit(1);
		}
		Reader reader = new Reader();
		List<ApexOneConfig>configs = reader.readconfigs(args[0]);
		if(configs!=null && configs.size()>0) {
			configs.forEach(config->{
				ApexoneMain.runWorker(ApexoneMain.run(config) ); 
			});
			
		}
		
		Runtime.getRuntime().addShutdownHook( new Thread(() ->{
			executor.shutdown();
		}));
		
	}
	
	
	private static Worker run(ApexOneConfig config) {
		Worker w = new Worker(config);
		return w;
	}
	
	private static void runWorker(Worker w) {
		executor.execute( w);
	}
}
