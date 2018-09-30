package com.fairdapp.bank.helper.runner.bank;

 
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.fairdapp.bank.helper.runner.bank.config.Config;
import com.fairdapp.bank.helper.runner.bank.config.Reader;

import lombok.Getter;
import lombok.Setter;

/*
 * 
 */
public class TaskWorker {
	
	private ExecutorService executor = java.util.concurrent.Executors.newCachedThreadPool();
	@Getter
	@Setter
	private String configFoler ;
	@Getter
	@Setter
	private List<Config> configs;
	public TaskWorker(String configFoler) {
		this.configFoler =   configFoler;
		Runtime.getRuntime().addShutdownHook( new Thread(() ->{
			executor.shutdown();
			/*while(true) {
				if(executor.isTerminated()) {
					break;
				}
			}*/
		}));
		
	}
	
	public TaskWorker load(){
		Reader reader = new Reader();
		this.configs = reader.readconfigs(configFoler);
		return this;
	}
	
	public void putTask() {
		this.configs.forEach(config->{
			putTask(  config );
		});
	}
	private TaskWorker putTask(Config config) {
		executor.execute( new Worker(config));
		return this;
	}
}
