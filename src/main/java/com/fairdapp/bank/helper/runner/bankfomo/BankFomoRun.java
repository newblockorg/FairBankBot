package com.fairdapp.bank.helper.runner.bankfomo;

public class BankFomoRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if(args==null || args.length<1) {
			System.err.println(" config file path not exsit");
		}
		String configPaths = args[0];
		
		TaskWorker  twoker = new TaskWorker(configPaths);
		
		twoker
			.load()
				.putTask();
	}

}
