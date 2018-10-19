package com.fairdapp.bank.helper.runner.apexone.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Apexone extends Contract {
    private static final String BINARY = "60c0604052600d60808190527f617065784f4e4520546f6b656e0000000000000000000000000000000000000060a09081526200004091600091906200022f565b506040805180820190915260048082527f4150583100000000000000000000000000000000000000000000000000000000602090920191825262000087916001916200022f565b5068056bc75e2d631000006002556722b1c8c1227a0000600355680410d586a20a4c00006004556000600655348015620000c057600080fd5b50336000818152600e602052604081208054600160ff1991821681179092557fca34d320fc9079ee0eb1b8070a5658527cc77efd5cd7747824f7834e9fe02a6580548216831790557f3328e4bfec0662ca23281148bdf422e9f26c443520650649df0b87f9ed4c245280548216831790557f94e14bd8337cb3aa3de5f9d8ef96742e3cb698735946a6ee517ec4c388c9708a80548216831790557fc46bec84407c374c98e520b0cfdcb3cc53802a23df46790beb1ad79e971c5c3780548216831790557f0b5ce25cfb2b07f6e1d6f29273ae74a14c0739b41f0786382ee9d53be01a28d180548216831790557f9cba59e9bd88f578865ed970a5e3ac553c316c0653a8c757495c4ff051ec7b05805482168317905573408c2a514aff2fe88d274c82b61256ef74da58119092527f0565363c4ba82598f98035e807fe324e0092455753fe2d3078aadf0e1a18a89b805490921617905560058054600160a060020a0319169091179055620002d4565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200027257805160ff1916838001178555620002a2565b82800160010185558215620002a2579182015b82811115620002a257825182559160200191906001019062000285565b50620002b0929150620002b4565b5090565b620002d191905b80821115620002b05760008155600101620002bb565b90565b6114de80620002e46000396000f3006080604052600436106101895763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166265318b81146102a757806306fdde03146102da57806310d0ffdd1461036457806318160ddd1461037c578063226093731461039157806323b3b704146103a9578063313ce567146103c15780633ccfd60b146103ec5780633e0a322d146104035780634b7503341461041b578063544736e61461043057806356d399e814610459578063585bc2811461046e5780636284ae4114610483578063688abbf7146104985780636b2f4632146104b257806370a08231146104c757806378e97925146104e85780638620410b146104fd57806391e3310714610512578063949e8acd1461052757806395d89b411461053c578063a9059cbb14610551578063caa877e714610575578063d6dda33d1461058f578063da7af32d146105a4578063e4849b32146105b9578063e9fad8ee146105d1578063f088d547146105e6578063fdb5a03e146105fa578063ff295c5d1461060f575b6004543430310310156101a5576003543411156101a557600080fd5b600d5415156101e457336000908152600e602052604090205460ff1680156101d45750346703782dace9d90000145b15156101df57600080fd5b610266565b6006600d54101561021d57336000908152600e602052604090205460ff1680156101d45750670a688906bd8b000034146101df57600080fd5b600d54600614806102305750600d546007145b1561026657336000908152600e602052604090205460ff16801561025b575034670de0b6b3a7640000145b151561026657600080fd5b642ecc8899ff3a1061027757600080fd5b61027f610640565b8061028d575061028d61064a565b151561029857600080fd5b6102a434600033610665565b50005b3480156102b357600080fd5b506102c8600160a060020a03600435166108cf565b60408051918252519081900360200190f35b3480156102e657600080fd5b506102ef61090a565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610329578181015183820152602001610311565b50505050905090810190601f1680156103565780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561037057600080fd5b506102c8600435610998565b34801561038857600080fd5b506102c86109cb565b34801561039d57600080fd5b506102c86004356109d1565b3480156103b557600080fd5b506102c8600435610a16565b3480156103cd57600080fd5b506103d6610a3f565b6040805160ff9092168252519081900360200190f35b3480156103f857600080fd5b50610401610a44565b005b34801561040f57600080fd5b50610401600435610b17565b34801561042757600080fd5b506102c8610b51565b34801561043c57600080fd5b5061044561064a565b604080519115158252519081900360200190f35b34801561046557600080fd5b506102c8610ba9565b34801561047a57600080fd5b506102c8610baf565b34801561048f57600080fd5b506103d6610bb5565b3480156104a457600080fd5b506102c86004351515610c1d565b3480156104be57600080fd5b506102c8610c5e565b3480156104d357600080fd5b506102c8600160a060020a0360043516610c63565b3480156104f457600080fd5b506102c8610c7e565b34801561050957600080fd5b506102c8610c84565b34801561051e57600080fd5b506102c8610ccf565b34801561053357600080fd5b506102c8610cd5565b34801561054857600080fd5b506102ef610ce7565b34801561055d57600080fd5b50610445600160a060020a0360043516602435610d41565b6102c8600160a060020a0360043581169060243516610e70565b34801561059b57600080fd5b50610445610640565b3480156105b057600080fd5b506102c8610f8d565b3480156105c557600080fd5b50610401600435610f93565b3480156105dd57600080fd5b50610401611106565b6102c8600160a060020a0360043516611133565b34801561060657600080fd5b50610401611250565b34801561061b57600080fd5b50610624611307565b60408051600160a060020a039092168252519081900360200190f35b600d546007101590565b6000600654600014158015610660575060065442115b905090565b60008080808080808061068361067c8c600c611316565b6064611341565b965061069361067c88600c611316565b955061069f8787611358565b94506106ab8b88611358565b93506106b68461136a565b925068010000000000000000850291506000831180156106e05750600b546106de8482611402565b115b15156106eb57600080fd5b600160a060020a038a1615801590610715575088600160a060020a03168a600160a060020a031614155b801561073b5750600254600160a060020a038b1660009081526007602052604090205410155b1561078157600160a060020a038a166000908152600860205260409020546107639087611402565b600160a060020a038b1660009081526008602052604090205561079c565b61078b8587611402565b945068010000000000000000850291505b6000600b541115610800576107b3600b5484611402565b600b8190556801000000000000000086028115156107cd57fe5b600c8054929091049091019055600b546801000000000000000086028115156107f257fe5b048302820382039150610806565b600b8390555b600160a060020a0389166000908152600760205260409020546108299084611402565b600160a060020a03808b16600081815260076020908152604080832095909555600c54600a909152939020805493870286900393840190559192508b16907f8032875b28d82ddbd303a9e4e5529d047a14ecb6290f80012a81b7e6227ff1ab8d8642610893610c84565b604080519485526020850193909352838301919091526060830152519081900360800190a35050600d8054600101905598975050505050505050565b600160a060020a03166000908152600a6020908152604080832054600790925290912054600c54680100000000000000009102919091030490565b6000805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156109905780601f1061096557610100808354040283529160200191610990565b820191906000526020600020905b81548152906001019060200180831161097357829003601f168201915b505050505081565b60008080806109ab61067c86600c611316565b92506109b78584611358565b91506109c28261136a565b95945050505050565b600b5490565b600080600080600b5485111515156109e857600080fd5b6109f185611411565b9250610a0a61067c84610a02610bb5565b60ff16611316565b91506109c28383611358565b600080600b548311151515610a2a57600080fd5b610a3383611411565b90508091505b50919050565b601281565b6000806000610a536001610c1d565b11610a5d57600080fd5b339150610a6a6000610c1d565b600160a060020a0383166000818152600a6020908152604080832080546801000000000000000087020190556008909152808220805490839055905193019350909183156108fc0291849190818181858888f19350505050158015610ad3573d6000803e3d6000fd5b50604080518281529051600160a060020a038416917fccad973dcd043c7d680389db4378bd6b9775db7124092e9e0422c9e46d7985dc919081900360200190a25050565b600554600160a060020a031633148015610b365750610b3461064a565b155b8015610b4157508042105b1515610b4c57600080fd5b600655565b600080600080600b5460001415610b6f576414f46b04009350610ba3565b610b80670de0b6b3a7640000611411565b9250610b9161067c84610a02610bb5565b9150610b9d8383611358565b90508093505b50505090565b60025481565b60035481565b600080600080600060065460001415610bd15760309450610c16565b600654421015610be45760009450610c16565b6006544203935062278d008410610bfe57600c9450610c16565b6024925062278d008484020491508160300390508094505b5050505090565b60003382610c3357610c2e816108cf565b610c57565b600160a060020a038116600090815260086020526040902054610c55826108cf565b015b9392505050565b303190565b600160a060020a031660009081526007602052604090205490565b60065481565b600080600080600b5460001415610ca25764199c82cc009350610ba3565b610cb3670de0b6b3a7640000611411565b9250610cc361067c84600c611316565b9150610b9d8383611402565b60045481565b600033610ce181610c63565b91505090565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156109905780601f1061096557610100808354040283529160200191610990565b6000806000610d4e610cd5565b11610d5857600080fd5b5033600081815260076020526040902054831115610d7557600080fd5b6000610d816001610c1d565b1115610d8f57610d8f610a44565b600160a060020a038116600090815260076020526040902054610db29084611358565b600160a060020a038083166000908152600760205260408082209390935590861681522054610de19084611402565b600160a060020a03858116600081815260076020908152604080832095909555600c8054948716808452600a83528684208054968b02909603909555548383529185902080549289029092019091558351878152935191937fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929081900390910190a3600191505b5092915050565b600454600090343031031015610e8f57600354341115610e8f57600080fd5b600d541515610ece57336000908152600e602052604090205460ff168015610ebe5750346703782dace9d90000145b1515610ec957600080fd5b610f50565b6006600d541015610f0757336000908152600e602052604090205460ff168015610ebe5750670a688906bd8b00003414610ec957600080fd5b600d5460061480610f1a5750600d546007145b15610f5057336000908152600e602052604090205460ff168015610f45575034670de0b6b3a7640000145b1515610f5057600080fd5b642ecc8899ff3a10610f6157600080fd5b610f69610640565b80610f775750610f7761064a565b1515610f8257600080fd5b610e69348484610665565b600d5481565b6000806000806000806000610fa6610cd5565b11610fb057600080fd5b33600081815260076020526040902054909650871115610fcf57600080fd5b869450610fdb85611411565b9350610fec61067c85610a02610bb5565b9250610ff88484611358565b9150611006600b5486611358565b600b55600160a060020a03861660009081526007602052604090205461102c9086611358565b600160a060020a038716600090815260076020908152604080832093909355600c54600a909152918120805492880268010000000000000000860201928390039055600b5491925010156110a25761109e600c54600b5468010000000000000000860281151561109857fe5b04611402565b600c555b85600160a060020a03167f8d3a0130073dbd54ab6ac632c05946df540553d3b514c9f8165b4ab7f2b1805e8684426110d8610c84565b604080519485526020850193909352838301919091526060830152519081900360800190a250505050505050565b33600081815260076020526040812054908111156111275761112781610f93565b61112f610a44565b5050565b6004546000903430310310156111525760035434111561115257600080fd5b600d54151561119157336000908152600e602052604090205460ff1680156111815750346703782dace9d90000145b151561118c57600080fd5b611213565b6006600d5410156111ca57336000908152600e602052604090205460ff1680156111815750670a688906bd8b0000341461118c57600080fd5b600d54600614806111dd5750600d546007145b1561121357336000908152600e602052604090205460ff168015611208575034670de0b6b3a7640000145b151561121357600080fd5b642ecc8899ff3a1061122457600080fd5b61122c610640565b8061123a575061123a61064a565b151561124557600080fd5b610a39348333610665565b6000806000806112606001610c1d565b1161126a57600080fd5b6112746000610c1d565b336000818152600a60209081526040808320805468010000000000000000870201905560089091528120805490829055909201945092506112b790849084610665565b905081600160a060020a03167fbe339fc14b041c2b0e0f3dd2cd325d0c3668b78378001e53160eab36153264588483604051808381526020018281526020019250505060405180910390a2505050565b600554600160a060020a031681565b6000808315156113295760009150610e69565b5082820282848281151561133957fe5b0414610c5757fe5b600080828481151561134f57fe5b04949350505050565b60008282111561136457fe5b50900390565b600b546000906c01431e0fae6d7217caa00000009082906402540be4006113ef6113e9730380d4bd8a8678c1bb542c80deb4800000000000880268056bc75e2d631000006002860a02017005e0a1fd2712875988becaad0000000000850201780197d4df19d605767337e9f14d3eec8920e4000000000000000161147d565b85611358565b8115156113f857fe5b0403949350505050565b600082820183811015610c5757fe5b600b54600090670de0b6b3a764000083810191810190839061146a6414f46b04008285046402540be40002018702600283670de0b6b3a763ffff1982890a8b900301046402540be4000281151561146457fe5b04611358565b81151561147357fe5b0495945050505050565b80600260018201045b81811015610a3957809150600281828581151561149f57fe5b04018115156114aa57fe5b0490506114865600a165627a7a72305820994294bd0658f666d9695b8bd30b25889946d40241e6cb00099b1019974b10d80029";

    public static final String FUNC_DIVIDENDSOF = "dividendsOf";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_CALCULATETOKENSRECEIVED = "calculateTokensReceived";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_CALCULATEETHEREUMRECEIVED = "calculateEthereumReceived";

    public static final String FUNC_CALCULATEUNTAXEDETHEREUMRECEIVED = "calculateUntaxedEthereumReceived";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_SETSTARTTIME = "setStartTime";

    public static final String FUNC_SELLPRICE = "sellPrice";

    public static final String FUNC_ISSTARTED = "isStarted";

    public static final String FUNC_STAKINGREQUIREMENT = "stakingRequirement";

    public static final String FUNC_MAXEARLYSTAKE = "maxEarlyStake";

    public static final String FUNC_EXITFEE = "exitFee";

    public static final String FUNC_MYDIVIDENDS = "myDividends";

    public static final String FUNC_TOTALETHEREUMBALANCE = "totalEthereumBalance";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_STARTTIME = "startTime";

    public static final String FUNC_BUYPRICE = "buyPrice";

    public static final String FUNC_WHALEBALANCELIMIT = "whaleBalanceLimit";

    public static final String FUNC_MYTOKENS = "myTokens";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_BUYFOR = "buyFor";

    public static final String FUNC_ISPREMINE = "isPremine";

    public static final String FUNC_DEPOSITCOUNT_ = "depositCount_";

    public static final String FUNC_SELL = "sell";

    public static final String FUNC_EXIT = "exit";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_REINVEST = "reinvest";

    public static final String FUNC_APEX = "apex";

    public static final Event ONTOKENPURCHASE_EVENT = new Event("onTokenPurchase", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ONTOKENSELL_EVENT = new Event("onTokenSell", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ONREINVESTMENT_EVENT = new Event("onReinvestment", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ONWITHDRAW_EVENT = new Event("onWithdraw", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    protected Apexone(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Apexone(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> dividendsOf(String _customerAddress) {
        final Function function = new Function(FUNC_DIVIDENDSOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customerAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> calculateTokensReceived(BigInteger _ethereumToSpend) {
        final Function function = new Function(FUNC_CALCULATETOKENSRECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ethereumToSpend)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> calculateEthereumReceived(BigInteger _tokensToSell) {
        final Function function = new Function(FUNC_CALCULATEETHEREUMRECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokensToSell)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> calculateUntaxedEthereumReceived(BigInteger _tokensToSell) {
        final Function function = new Function(FUNC_CALCULATEUNTAXEDETHEREUMRECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokensToSell)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> withdraw() {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setStartTime(BigInteger _startTime) {
        final Function function = new Function(
                FUNC_SETSTARTTIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_startTime)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> sellPrice() {
        final Function function = new Function(FUNC_SELLPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> isStarted() {
        final Function function = new Function(FUNC_ISSTARTED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> stakingRequirement() {
        final Function function = new Function(FUNC_STAKINGREQUIREMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> maxEarlyStake() {
        final Function function = new Function(FUNC_MAXEARLYSTAKE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> exitFee() {
        final Function function = new Function(FUNC_EXITFEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> myDividends(Boolean _includeReferralBonus) {
        final Function function = new Function(FUNC_MYDIVIDENDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(_includeReferralBonus)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> totalEthereumBalance() {
        final Function function = new Function(FUNC_TOTALETHEREUMBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> balanceOf(String _customerAddress) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_customerAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> startTime() {
        final Function function = new Function(FUNC_STARTTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> buyPrice() {
        final Function function = new Function(FUNC_BUYPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> whaleBalanceLimit() {
        final Function function = new Function(FUNC_WHALEBALANCELIMIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> myTokens() {
        final Function function = new Function(FUNC_MYTOKENS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _toAddress, BigInteger _amountOfTokens) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_toAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(_amountOfTokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> buyFor(String _referredBy, String _customerAddress, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUYFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_referredBy), 
                new org.web3j.abi.datatypes.Address(_customerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Boolean> isPremine() {
        final Function function = new Function(FUNC_ISPREMINE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> depositCount_() {
        final Function function = new Function(FUNC_DEPOSITCOUNT_, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> sell(BigInteger _amountOfTokens) {
        final Function function = new Function(
                FUNC_SELL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amountOfTokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> exit() {
        final Function function = new Function(
                FUNC_EXIT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> buy(String _referredBy, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_referredBy)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> reinvest() {
        final Function function = new Function(
                FUNC_REINVEST, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> apex() {
        final Function function = new Function(FUNC_APEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<Apexone> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Apexone.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Apexone> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Apexone.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<OnTokenPurchaseEventResponse> getOnTokenPurchaseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ONTOKENPURCHASE_EVENT, transactionReceipt);
        ArrayList<OnTokenPurchaseEventResponse> responses = new ArrayList<OnTokenPurchaseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OnTokenPurchaseEventResponse typedResponse = new OnTokenPurchaseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.referredBy = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.incomingEthereum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokensMinted = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OnTokenPurchaseEventResponse> onTokenPurchaseEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OnTokenPurchaseEventResponse>() {
            @Override
            public OnTokenPurchaseEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ONTOKENPURCHASE_EVENT, log);
                OnTokenPurchaseEventResponse typedResponse = new OnTokenPurchaseEventResponse();
                typedResponse.log = log;
                typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.referredBy = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.incomingEthereum = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tokensMinted = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OnTokenPurchaseEventResponse> onTokenPurchaseEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONTOKENPURCHASE_EVENT));
        return onTokenPurchaseEventObservable(filter);
    }

    public List<OnTokenSellEventResponse> getOnTokenSellEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ONTOKENSELL_EVENT, transactionReceipt);
        ArrayList<OnTokenSellEventResponse> responses = new ArrayList<OnTokenSellEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OnTokenSellEventResponse typedResponse = new OnTokenSellEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokensBurned = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.ethereumEarned = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OnTokenSellEventResponse> onTokenSellEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OnTokenSellEventResponse>() {
            @Override
            public OnTokenSellEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ONTOKENSELL_EVENT, log);
                OnTokenSellEventResponse typedResponse = new OnTokenSellEventResponse();
                typedResponse.log = log;
                typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tokensBurned = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.ethereumEarned = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OnTokenSellEventResponse> onTokenSellEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONTOKENSELL_EVENT));
        return onTokenSellEventObservable(filter);
    }

    public List<OnReinvestmentEventResponse> getOnReinvestmentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ONREINVESTMENT_EVENT, transactionReceipt);
        ArrayList<OnReinvestmentEventResponse> responses = new ArrayList<OnReinvestmentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OnReinvestmentEventResponse typedResponse = new OnReinvestmentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.ethereumReinvested = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokensMinted = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OnReinvestmentEventResponse> onReinvestmentEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OnReinvestmentEventResponse>() {
            @Override
            public OnReinvestmentEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ONREINVESTMENT_EVENT, log);
                OnReinvestmentEventResponse typedResponse = new OnReinvestmentEventResponse();
                typedResponse.log = log;
                typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.ethereumReinvested = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.tokensMinted = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OnReinvestmentEventResponse> onReinvestmentEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONREINVESTMENT_EVENT));
        return onReinvestmentEventObservable(filter);
    }

    public List<OnWithdrawEventResponse> getOnWithdrawEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ONWITHDRAW_EVENT, transactionReceipt);
        ArrayList<OnWithdrawEventResponse> responses = new ArrayList<OnWithdrawEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OnWithdrawEventResponse typedResponse = new OnWithdrawEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.ethereumWithdrawn = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OnWithdrawEventResponse> onWithdrawEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OnWithdrawEventResponse>() {
            @Override
            public OnWithdrawEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ONWITHDRAW_EVENT, log);
                OnWithdrawEventResponse typedResponse = new OnWithdrawEventResponse();
                typedResponse.log = log;
                typedResponse.customerAddress = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.ethereumWithdrawn = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OnWithdrawEventResponse> onWithdrawEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ONWITHDRAW_EVENT));
        return onWithdrawEventObservable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventObservable(filter);
    }

    public static Apexone load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Apexone(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Apexone load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Apexone(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class OnTokenPurchaseEventResponse {
        public Log log;

        public String customerAddress;

        public String referredBy;

        public BigInteger incomingEthereum;

        public BigInteger tokensMinted;

        public BigInteger timestamp;

        public BigInteger price;
    }

    public static class OnTokenSellEventResponse {
        public Log log;

        public String customerAddress;

        public BigInteger tokensBurned;

        public BigInteger ethereumEarned;

        public BigInteger timestamp;

        public BigInteger price;
    }

    public static class OnReinvestmentEventResponse {
        public Log log;

        public String customerAddress;

        public BigInteger ethereumReinvested;

        public BigInteger tokensMinted;
    }

    public static class OnWithdrawEventResponse {
        public Log log;

        public String customerAddress;

        public BigInteger ethereumWithdrawn;
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger tokens;
    }
}
