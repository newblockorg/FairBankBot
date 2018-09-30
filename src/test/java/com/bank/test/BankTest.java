package com.bank.test;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
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
import org.web3j.utils.Convert;

import com.fairdapp.bank.helper.runner.bank.contract.Bank;

public class BankTest {
	String nodeUrl = "https://ropsten.infura.io/v3/4201439de5304c809f1508fbc30ec6ac";//"https://mainnet.infura.io/v3/4201439de5304c809f1508fbc30ec6ac";
	Web3j web3j ;//=  new JsonRpc2_0Web3j(new HttpService(nodeUrl));   // defaults to http://localhost:8545/
	String contractAddress = "0x0dddce85cd6a5fa1e02ea071c9aca4b955502e71";// "0xb8f93e49a3278bb92f643ca2491cf80bfa0a01b7"
	String privateKey = "1e7ba33d79e4c05db34f4654e5285f22395b320952783829a45e872cc493747e";
	String myselfAddress ;
	BigInteger gasPrice  ;
	BigInteger gasLimit  ;
	Bank contract;
	@Before
	public void init() {
		web3j =  new JsonRpc2_0Web3j(new HttpService(nodeUrl)); 
		gasPrice = new BigInteger("21000");
		gasLimit = new BigInteger ("372429");
		ECKeyPair ecKeyPair = ECKeyPair.create(new BigInteger(privateKey, 16));
        Credentials credentials = Credentials.create(ecKeyPair);
		contract = Bank.load( contractAddress , web3j, credentials, gasPrice, gasLimit);
		
		myselfAddress = credentials.getAddress();
		
	}

	@Test
	public void test() throws Exception {
		 
		
		//0xb11af6e34ac945caa4ee17afab51a72fb1bb8414
		//1e7ba33d79e4c05db34f4654e5285f22395b320952783829a45e872cc493747e
		//BigInteger privateKey, BigInteger publicKey
		
	/*	PrivateKey privateKey =   BCECPrivateKey.;
		PublicKey publicKey;
		KeyPair KeyPair = new KeyPair(publicKey,privateKey);
		ECKeyPair  ecKeyPair =   ECKeyPair.create(KeyPair) ;
		File currentFile = new File("./");
		String source = WalletUtils.generateWalletFile(password, ecKeyPair, currentFile, true);
		Credentials credentials = WalletUtils.loadCredentials("1e7ba33d79e4c05db34f4654e5285f22395b320952783829a45e872cc493747e", "./");
	 */
		 
		RemoteCall<Tuple6<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256>> result = contract.getHeadInfo() ;
		Tuple6<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256>  resultArray = result.sendAsync().get();
		
		System.out.println( resultArray.getSize());
		 
		System.out.println(  resultArray.getValue1().getValue().longValue() );
		System.out.println(  resultArray.getValue2().getValue().longValue()   );
		System.out.println(  resultArray.getValue3().getValue().longValue() );
		System.out.println(  Convert.fromWei(resultArray.getValue4().getValue().toString(), Convert.Unit.ETHER) );
		System.out.println(  Convert.fromWei(resultArray.getValue5().getValue().toString(), Convert.Unit.ETHER) );
		System.out.println(  Convert.fromWei(resultArray.getValue6().getValue().toString(), Convert.Unit.ETHER) );
		
		
		
	}
	
	// @Test
	public void trancefer() throws Exception {
		BigInteger value =  BigInteger.valueOf(10000000);
		RemoteCall<TransactionReceipt> call =contract.buy( new Uint256(1), new Uint256(50),new Address(myselfAddress) , value );
		String hash = call.send().getTransactionHash();
		System.out.println(hash);
	}
	
	@Test
	public   void getNonce( ) {
		System.out.println( this.getNonce(this.web3j, this.myselfAddress).longValue() ) ;
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
}
