package com.fairdapp.bank.helper.runner.bank;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.utils.Convert;

import com.fairdapp.bank.helper.runner.bank.config.Config;
import com.fairdapp.bank.helper.runner.bank.contract.Bank;

public class Worker implements Runnable{

	private Config config;
	public Worker( Config c) {
		this.config = c;
		this.init();
	}
	private Web3j web3j ; 
	private String myselfAddress ;
	private BigInteger gasPrice =new BigInteger ("20000000000") ;
	private BigInteger gasLimit =new BigInteger ("672429") ;
	private Bank contract;
	private Integer sendTimes = 1;   
	private String nickname;
	public void init() {
		web3j =  new JsonRpc2_0Web3j(new HttpService(this.config.getNodeUrl())); 
		 
		if(this.config.getGasPrice()!=null && !this.config.getGasPrice().isEmpty())
		{
			gasPrice = new BigInteger(this.config.getGasPrice());
		}
		
		if(this.config.getGasLimit()!=null && !this.config.getGasLimit().isEmpty()) {
			gasLimit = new BigInteger (this.config.getGasLimit());
		}
		
		ECKeyPair ecKeyPair = ECKeyPair.create(new BigInteger(this.config.getPrivateKey(), 16));
        Credentials credentials = Credentials.create(ecKeyPair);
		contract = Bank.load( this.config.getContractAddress() , web3j, credentials, gasPrice, gasLimit);
		myselfAddress = credentials.getAddress();
		sendTimes =   Integer.parseInt(  config.getSendTimes() );
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
		int sendtimeMark = 0 ;
		//记录打款阶段
		Long stageMark = 1L ;
		long sleeptime = 1_000;
		 for(;;) {
				try {
					System.out.println("#######################config name is :"+config.getConfigName()+"###################");
					RemoteCall<Tuple6<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256>> result = contract.getHeadInfo() ;
					Tuple6<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256> resultArray;
					resultArray = result.sendAsync().get();
				 
					Long round 		   = resultArray.getValue1().getValue().longValue()  ;
					Long stage 		   = resultArray.getValue2().getValue().longValue()  ;
					Long timesSecond   = resultArray.getValue3().getValue().longValue()  ;
					BigInteger divi    = resultArray.getValue4().getValue() ;
					BigInteger target  = resultArray.getValue5().getValue()  ;
					BigInteger Jackpot = resultArray.getValue6().getValue() ;
					long currentTimeSecond = System.currentTimeMillis()/1000;
					//同一个阶段  如果sendTimes 没有设置0 的情况下，只能最多打出 sendTimes 多次款，并且符合赋值条件
					if(stageMark==stage && sendTimes !=0 && sendtimeMark >= sendTimes) {
						//long sleeptime=(timesSecond - currentTimeSecond)*800 ;
						System.out.println("#######################config name is :"+config.getConfigName()+"###################sleeptime="+sleeptime+" ms");
						Thread.sleep(sleeptime);
						continue;
					}
					
					if(stageMark!=stage) {
						sendtimeMark=0;
						stageMark = stage ;
					}
					 
					Tuple8<Uint256, Uint256, Bool, Uint256, Uint256, Uint256, Uint256, Uint256>  roundState = contract.round( new Uint256(round) ).send();
					org.web3j.abi.datatypes.Bool roundend = roundState.getValue3();
					//Tuple4<Uint256, Uint256, Uint256, Uint256> stageState = contract.stage( new Uint256(round), new Uint256(stage) ).send();
					
					boolean modifyCountdown =  contract.modifyCountdown().send().getValue();
					
					System.out.println( "round="+round		+	"\nstage="+stage
										+"\n Achieved="		+	Convert.fromWei(divi.toString(), Convert.Unit.ETHER)
										+"\n Target  ="		+	Convert.fromWei(target.toString(), Convert.Unit.ETHER)
										+"\n jackpot ="		+   Convert.fromWei(Jackpot.toString(), Convert.Unit.ETHER) 
										+"\n round state="	+   roundend.getValue() 
										+"\n can modify Time Remaining ="		+   modifyCountdown 
										+"\n Time Remaining ====Second====>"		+   ( timesSecond - currentTimeSecond  ) 
							);
					if(roundend.getValue()) {
						System.out.println(" stage end...");
						System.out.println("#######################config name is :"+config.getConfigName()+"###################");
						continue;
					}
					
					//round end
					if(currentTimeSecond >=timesSecond) {
						 
						System.out.println( "Checking countdown => currentTimeSecond >=timesSecond is : "+(currentTimeSecond >=timesSecond)+", now need sleep : "+sleeptime/1000 +" seconds");
						System.out.println("#######################config name is :"+config.getConfigName()+"###################");
						Thread.sleep( sleeptime );
						continue;
					}
					//时间不够
					if( (timesSecond - currentTimeSecond <= Long.valueOf( this.config.getDifferSeconds()) == false)) {
						Thread.sleep( sleeptime  );
						System.out.println("########### Fair Bank Bot Ready to Snipe ############config name is :"+config.getConfigName()+"###################");
						continue;
					}
					//当前差额比例
					Float remain =  Convert.fromWei(divi.toString(), Convert.Unit.ETHER).floatValue()*100/Convert.fromWei(target.toString(), Convert.Unit.ETHER).floatValue();
					//到过多少比例不在投资
					Float deng = Float.parseFloat( this.config.getDifferNopayRatio());
					if(remain >=deng) {
						System.out.println("DifferNopayRatio is :"+deng + " remain%:"+remain);
						continue;
					}
					//计算需要补上的 差额 百分比
					BigInteger imbalance = target.subtract( divi);
					imbalance = imbalance.multiply( new BigInteger(this.config.getDifferPayRatio() ) ).divide(new BigInteger("100"));
					//设置每笔交易最多打款多少
					BigInteger moreThan = new BigInteger(this.config.getPayValueMorest());
					BigInteger value = imbalance.min(  moreThan )  ;
					
					System.out.println( "******current use Nonce is: " + getNonce( )+"******" );
					System.out.println( "use config is ："+this.config );
					System.out.println( "pay eth is :"+value.longValue() +" Wei , & is ETHER:"+ Convert.fromWei(value.toString(), Convert.Unit.ETHER)  );
					
					RemoteCall<TransactionReceipt> call = null;
					
					try {
						
						Float mark = Float.parseFloat(this.config.getPayCapitalProtection());
						int protection = (int)  ( mark + (  mark *  (  12/88)  )) ;
		                if(mark>44){
		                    protection = (int) Math.ceil(mark );
		                }else{
		                   // protection =   protection  ;
		                }
		                
						if(this.nickname!=null && !this.nickname.isEmpty() &&  this.nickname.length()>=4 &&this.nickname.length()<32 && this.nickname.matches("^[a-z0-9A-Z]+$" ) && !this.nickname.toLowerCase().startsWith("0x")) {
							 //合约buyXname
							call = contract.buyXname( new Uint256(new BigInteger(this.config.getPayLockdownStages())), new Uint256(new BigInteger( String.valueOf(protection ))),new Utf8String(this.nickname) , value );
						}else {
							call = contract.buy( new Uint256(new BigInteger(this.config.getPayLockdownStages())), new Uint256(new BigInteger(  String.valueOf(protection )  )),new Address(myselfAddress) , value );
						}
						 
						String hash = call.send().getTransactionHash();
						System.out.println("config name is :"+config.getConfigName()+ ", confirm pay  :"+Convert.fromWei(value.toString(), Convert.Unit.ETHER)  +"ETH, hash="+hash  );
						
					}finally {
						sendtimeMark++;
						stageMark = stage ;
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					
					
				}
		 }
		
	}

}
