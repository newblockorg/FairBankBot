package com.fairdapp.bank.helper.kerl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.fairdapp.bank.helper.runner.bank.Run;

public class MainEntry {

	public static void main(String[] w) throws SQLException, IOException {
		if(w!=null)
		{
			 
			for(int  i = 0 ; i< w.length ; i++){
				System.out.println(w[i]);
				
				if(w[i]!=null && w[i].startsWith("-")){
					
					if(Command.debug .equals(w[i] ) ){
						Command.cmd.put(w[i], "true");
						continue;
					}
					if(/*cmd.containsKey( w[i]) &&*/  i+1 < w.length ){
						Command.cmd.put(w[i], w[i+1]);
					}
				}
			}
		}
		
		
		System.out.println(Command.cmd);
		
		String filepath = Command.cmd.get(Command.filepath  );
		
		if("bank".equals(Command.cmd.get(Command.runtype  ) )  ) {
			File currentFilePath = new File("./");
			String data = currentFilePath.getAbsolutePath()+File.separator+"config";
			String[] args = new String[] { /*filepath*/data };
			System.out.println( data );
			 
			Run.main(args);
		}else if("importSql".equals(Command.cmd.get(Command.runtype  ) )  ) {
			 
		}else if(  "exportsql".equals(Command.cmd.get(Command.runtype  ) ) ) {
			 
		}
		else if(  "kbstat".equals(Command.cmd.get(Command.runtype  ) ) ) {
		 
		 
		}else if(  "redis".equals(Command.cmd.get(Command.runtype  ) ) ) {
			 
			 
		}else {
			System.err.println("no run type : "+Command.cmd.get(Command.runtype  ));
		}
	}

}
