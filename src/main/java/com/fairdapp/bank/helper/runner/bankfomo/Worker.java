package com.fairdapp.bank.helper.runner.bankfomo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.utils.Convert;

import com.fairdapp.bank.helper.runner.bankfomo.config.Config;
import com.fairdapp.bank.helper.runner.bankfomo.contract.Bankfomo;

public class Worker implements Runnable{

	private Config config;
	public Worker( Config c) {
		this.config = c;
		this.init();
	}
	private Web3j web3j ; 
	private String myselfAddress ;
	private BigInteger gasPrice =new BigInteger ("25000000000") ;
	private BigInteger gasLimit =new BigInteger ("672429") ;
	private Bankfomo contract;
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
		contract = Bankfomo.load( this.config.getContractAddress() , web3j, credentials, gasPrice, gasLimit);
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
					RemoteCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> result = contract.getHeadInfo()  ;
					Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> resultArray;
					resultArray = result.sendAsync().get();
				 
					Long round 		   = resultArray.getValue1().longValue() ;
					Long stage 		   = resultArray.getValue2() .longValue()  ;
					
					Long timesstart   = resultArray.getValue3() .longValue()  ;
					
					Long timesendSecond   = resultArray.getValue4() .longValue()  ;
					BigInteger divi    = resultArray.getValue5()  ;
					BigInteger target  = resultArray.getValue6()   ;
					BigInteger Jackpot = resultArray.getValue7()  ;
					long currentTimeSecond = System.currentTimeMillis()/1000;
					//同一个阶段  如果sendTimes 没有设置0 的情况下，只能最多打出 sendTimes 多次款，并且符合赋值条件
					if(stageMark==stage && sendTimes !=0 && sendtimeMark >= sendTimes) {
						//long sleeptime=(timesSecond - currentTimeSecond)*800 ;
						String value= "round:="+round+",stage="+stage+",距离开始="+ (timesstart-currentTimeSecond)+"秒,距离结束="+( timesendSecond-currentTimeSecond ) +"秒,divi="+divi+",target="+target+",Jackpot="+Jackpot;
						System.out.println("#######################config name is :"+config.getConfigName()+"###################sleeptime="+sleeptime+" ms");
						System.out.println("########value:=\n"+value );
						
						Thread.sleep(sleeptime);
						continue;
					}
					
					if(stageMark!=stage) {
						sendtimeMark=0;
						stageMark = stage ;
					}
					 
					RemoteCall<Tuple8<BigInteger, BigInteger, Boolean, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>  roundState = contract.round(  new BigInteger(round.toString() ) ) ;
					Boolean roundend = roundState.send().getValue3();
					//Tuple4<Uint256, Uint256, Uint256, Uint256> stageState = contract.stage( new Uint256(round), new Uint256(stage) ).send();
					
					boolean modifyCountdown =  contract.modifyCountdown().send() ;
					
					System.out.println( "round="+round		+	"\nstage="+stage
										+"\n Achieved="		+	Convert.fromWei(divi.toString(), Convert.Unit.ETHER)
										+"\n Target  ="		+	Convert.fromWei(target.toString(), Convert.Unit.ETHER)
										+"\n jackpot ="		+   Convert.fromWei(Jackpot.toString(), Convert.Unit.ETHER) 
										+"\n round state="	+   roundend 
										+"\n can modify Time Remaining ="		+   modifyCountdown 
										+"\n Time Remaining ====Second====>"		+   ( timesendSecond - currentTimeSecond  ) 
							);
					if(roundend ) {
						System.out.println(" stage end...");
						System.out.println("#######################config name is :"+config.getConfigName()+"###################");
						continue;
					}
					
					//round end
					if(currentTimeSecond >=timesendSecond) {
						 
						System.out.println( "Checking countdown => currentTimeSecond >=timesSecond is : "+(currentTimeSecond >=timesendSecond)+", now need sleep : "+sleeptime/1000 +" seconds");
						System.out.println("#######################config name is :"+config.getConfigName()+"###################");
						Thread.sleep( sleeptime );
						continue;
					}
					//时间不够
					if( (timesendSecond - currentTimeSecond <= Long.valueOf( this.config.getDifferSeconds()) == false)) {
						Thread.sleep( sleeptime  );
						System.out.println("########### Fair Bank Bot Ready to Snipe ############config name is :"+config.getConfigName()+"###################");
						continue;
					}
					//当前差额比例
					Float remain = divi.intValue()==0?100:  Convert.fromWei(divi.toString(), Convert.Unit.ETHER).floatValue()*100/Convert.fromWei(target.toString(), Convert.Unit.ETHER).floatValue();
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
						//int protection =  (mark.intValue() );
						Float protection =    ( mark + (  mark *  (  (float)12/(float)88)  )) ;
		                if(protection>44){
		                    protection =   (float) Math.ceil(protection );
		                }else{
		                   // protection =   protection  ;
		                } 
		                call = contract.buy(new BigInteger(this.config.getPayLockdownStages()), new BigInteger(  String.valueOf(protection.intValue() )  ), value);//.buy( new Uint256(new BigInteger(this.config.getPayLockdownStages())), new Uint256(new BigInteger(  String.valueOf(protection )  )) , value );
						 
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
