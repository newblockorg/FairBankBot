package com.fairdapp.bank.helper.runner.exchange.runner;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.fairdapp.bank.helper.runner.exchange.config.ExchangeOneConfig;
import com.fairdapp.bank.helper.runner.exchange.contract.Exchange;
 
public class Worker implements Runnable{

	private ExchangeOneConfig config;
	public Worker( ExchangeOneConfig c) {
		this.config = c;
		this.init();
	}
	private Web3j web3j ; 
	private String myselfAddress ;
	private BigInteger gasPrice =new BigInteger ("20000000000") ;
	private BigInteger gasLimit =new BigInteger ("672429") ;
	private Exchange contract;
	private Long sendTimesSecond = 1_539_478_800L;
 
	public void init() {
		web3j =  new JsonRpc2_0Web3j(new HttpService(this.config.getNodeUrl())); 
		 
		if(this.config.getGasPrice()!=null && !this.config.getGasPrice().isEmpty())
		{
			gasPrice = new BigInteger(this.config.getGasPrice());
		}
		
		if(this.config.getGasLimit()!=null && !this.config.getGasLimit().isEmpty()) {
			gasLimit = new BigInteger (this.config.getGasLimit());
		}
		
		ECKeyPair ecKeyPair 	= ECKeyPair.create(new BigInteger(this.config.getPrivateKey(), 16));
        Credentials credentials = Credentials.create(ecKeyPair);
		contract 				= Exchange.load( this.config.getContractAddress() , web3j, credentials, gasPrice, gasLimit);
		myselfAddress   		= credentials.getAddress();
		sendTimesSecond 		= Long.parseLong( config.getStartTimeSecond() );
	}
	
	public   BigInteger getNonce( ) {
		return this.getNonce(this.web3j, this.myselfAddress);
	}
	
	public   BigInteger getNonce(Web3j web3j, String addr) {
        try {
            EthGetTransactionCount getNonce = web3j.ethGetTransactionCount(addr, DefaultBlockParameterName.PENDING).send();
            if (getNonce == null){
                throw new RuntimeException("net error");
            }
            return getNonce.getTransactionCount();
        } catch (IOException e) {
            throw new RuntimeException("net error");
        }
    }

 
	public void run() {
		  
		//记录打款阶段
		Long stageMark = 0L ;
		 
		 for(;;) {
			 	 System.out.println("#######################config name is :"+config.getConfigName()+"###################");
				
				 if(stageMark> Integer.parseInt(this.config.getTimesTry())) {
					 System.out.println("超过重试次数，退出");
					 break;
				 }
				try {
					 
				 
					 BigInteger startTime = contract.startTime().send();
					 if(sendTimesSecond!=startTime.longValue()) {
						 if(startTime!=null && (startTime.longValue() < sendTimesSecond  || (sendTimesSecond+1000>=startTime.longValue()) ) ) {
							 sendTimesSecond = startTime.longValue();
						 }
					 }
					 
					 long currentTimeSecond = System.currentTimeMillis()/1000;
					 long minsecond = System.currentTimeMillis()%1000;
					 
					 System.out.println( " 相差 "+(sendTimesSecond-currentTimeSecond)+"秒开始购买，购买信息为："+config.toString());
					 
					 if(currentTimeSecond-sendTimesSecond >6000) {
						 System.out.println("currentTimeSecond-sendTimesSecond >6000，退出");
						 break;
					 }
					 
					 if(   ( sendTimesSecond - currentTimeSecond <1  ||  ( (sendTimesSecond - currentTimeSecond) <= 1 && minsecond < 150 ) ) ) {
						 String referredAddress = config.getReferredAddress();
						 if(referredAddress==null || referredAddress.isEmpty()||"".equals(referredAddress.trim())) {
							 referredAddress = this.myselfAddress;
						 } 
						 
						 RemoteCall<TransactionReceipt> call =   contract.buy(referredAddress, Convert.toWei(config.getEtherValue() , Unit.ETHER).toBigInteger() );
					 
						 String hash = call.send() .getTransactionHash();
						 System.out.println("购买完成:config name is :"+config.getConfigName()+ ", confirm pay  :"+config.getEtherValue() +"ETH, hash="+hash  );
						 
						 if(hash!= null && !hash.isEmpty()) {
							 break;
						 }
					 }
					
				}  catch (Exception e) {
					stageMark++;
					e.printStackTrace();
				}finally {
					
					
				}
				try {
					TimeUnit.MILLISECONDS .sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		 }
		
	}

}
