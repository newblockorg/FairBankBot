package com.fairdapp.dice.test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class TestBet {

	BigDecimal balance 	= BigDecimal.TEN;
	BigDecimal  banker 	= BigDecimal.ZERO;
	BigDecimal payout 	= BigDecimal.valueOf( 19.98);
	BigDecimal betsize 	= BigDecimal.valueOf( 0.000001);
	
	java.util.concurrent.atomic.AtomicLong times = new AtomicLong(0);
	
	public static void main(String[] args) throws InterruptedException {
		 
		TestBet t = new TestBet();
		
		for(int i = 10 ; i <30;i++) {
			Thread thread = t.run();
			thread.start();
			//thread.join();
		}
		
		t.check();
		 
		//Thread.currentThread().join();
	}
	
	public void check() {
		for(;;) {
			  
			if(balance.subtract(betsize ).doubleValue()<0) {
				System.out.println( "運行次數-》times="+times.toString());
				System.out.println( "balance="+balance.toString());
				System.out.println( "payout="+payout.toString());
				System.out.println( "betsize="+betsize.toString());
				System.out.println( "banker="+banker.toString());
				
				
				break;
			}else {
				/*System.out.println( "balance="+balance.doubleValue());
				System.out.println( "payout="+payout.doubleValue());
				System.out.println( "betsize="+betsize.doubleValue());*/
			}
		}
	}
	
	public Thread run() {
		
		Thread t = new Thread(()->{
			do {
				if(BlanceSubBetSize()) {
					break;
				}
				
				Random r = new Random();
				int random = r.nextInt( 1000000);
				if(random<=50000) {
					subBetSize();
				}else {
					addBetSize();
				}
				long i = times.addAndGet(1);
				System.out.println(i);
			}while(true);
			
		} ) ;
		
		return t;
	}
	
	
	public synchronized  boolean BlanceSubBetSize() {
	 
		 double remain =  balance.subtract(betsize ).doubleValue();
		 if(remain>0) {
			 return false;
		 }else {
			 return true;
		 }
	}
	
	public synchronized  void subBetSize() {
		balance = balance.add(betsize.multiply( payout ));
		banker = banker.subtract(betsize.multiply( payout ));
		betsize = betsize.subtract( BigDecimal.valueOf( 0.000103));
		if(betsize.doubleValue()<0.000001) {
			betsize =   BigDecimal.valueOf( 0.000001 ) ;
		}
		
	}

	public synchronized  void addBetSize( ) {
		banker = banker.add(betsize);
		balance = balance.subtract(betsize);
		betsize = betsize.add( BigDecimal.valueOf( 0.000001));
		
		
	}

}
