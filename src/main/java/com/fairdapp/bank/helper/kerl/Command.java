package com.fairdapp.bank.helper.kerl;

import java.util.HashMap;
import java.util.Map;

public class Command {

	final public	static	String filepath 	= 	"-file" ;			//  配置文件路徑 或者別的文件路徑...
	final public	static	String runtype 		= 	"-runtp" ;			//  
	final public	static	String sqlName 		= 	"-sqlname" ;		//  
	final public	static	String sqlParams 	= 	"-sqlparams" ;		//  
	final public	static	String database 	= 	"-db" ;				// 	只用的主机地址
	final public	static	String host 		= 	"-host" ;			// 	只用的主机地址
	final public	static	String port 		= 	"-port" ;			// 	端口
	final public	static	String password 	= 	"-password" ;		//	秘密
	final public	static	String command 		= 	"-command" ;		// 	命令值
	final public	static	String key 			= 	"-key" ;			//  key-value
	final public	static	String debug 		= 	"-debug" ;			//   
	
	
	
	public	static Map<String,String> cmd = new HashMap<String,String>(){ 
		private static final long serialVersionUID = 1L;
		{
			this.put(filepath, null);
			this.put(runtype, null);
			this.put(debug, null);
			this.put(host, null);
			this.put(port, null);
			this.put(password, null);
			this.put(command, null);
			this.put(key, null);
		}
	};
	
	static{
		System.out.println ("#############commands -------->");
		System.out.println (cmd.keySet());
	}
	
}
