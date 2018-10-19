package com.fairdapp.bank.helper.runner.bankfomo.config;

import lombok.Data;
import lombok.ToString;

/*
 * 配置選項的內容
 */
@Data
@ToString(exclude= {"privateKey","publicKey"})
public class Config {
	private String configName;
	private String nodeUrl;//链接节点的 ipc或者 url
	private String payLockdownStages;// 購買的時候 添加的階段數量
	private String payCapitalProtection; // 購買的時候，填寫的保本比例
	private String payValue;// 通過differPayRatio 這個 計算出來的  
	private String payValueMorest;//  最多不能超过多少  wei
	private String differNopayRatio;// 到達多少百分比后就不在打款了
	private String differPayRatio;// 打款剩餘的總數的百分比 
	private String differSeconds;// 相差多少秒后開始打款
	private String privateKey;
	private String publicKey; // 
	private String gasPrice; //gas price
	private String gasLimit;// gas limit
	private String contractAddress;
	private String sendTimes;//调用发送次数
	private String nickname;//推荐名字
	
}