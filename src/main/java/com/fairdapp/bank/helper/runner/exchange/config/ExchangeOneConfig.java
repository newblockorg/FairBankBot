package com.fairdapp.bank.helper.runner.exchange.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude= {"privateKey" })
public class ExchangeOneConfig {
	private String ConfigName;
	private String NodeUrl;
	private String gasLimit;//使用的gaslimit
	private String gasPrice;//使用的gasprice
	private String startTimeSecond;//什么时间开启
	private String etherValue;//多少以太
	private String timesTry;//失败的话重试次数
	private String privateKey;
	private String contractAddress;
	private String referredAddress;
}
