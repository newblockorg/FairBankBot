# FairBankBot
A jackpot bot for Fairdapp - Bank Simulator
1. Install java https://www.java.com
2. Download and extra the folder or compile the sourcecode yourself
3. Edit config.properties in the config folder
4. double click start-short.bat
5. Recommended to use a new ethereum address as the bot requires private key to work.
6. This bot is open source, it does not contain any backdoors, however your private key could still be compromised due to your computer security. 
7. Your bot is only as good as your settings. 

1. 安装java https://www.java.com
2. 下载并且解压文件夹
3. 编辑config文件夹中的config.properties配置
4. 双点击start-short.bat开始跑机器人
5. 建议使用一个新的以太坊地址，机器人需要私钥才能工作。
6. 本软件开源，没有后门，但不保证私钥的安全性，私钥有可能会因为电脑中毒而泄露。
7. 机器人的好坏跟你的设置有关。

Warning: The bot is provided without any warranties, use at your own risk. The author did not check for all possible bugs related to this bot. 
警告: 本软件作者不承担任何因机器人带来的任何后果和损失，本机器人没有经过完整的bug测试。

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

For advanced users:

start.bat

1.open ms-cmd   
2.cd current path
3.cmd :  
	start.bat %config files path%  %java JDK path%
	
	exmaple 
	start.bat ./config  C:\Program Files\Java\jdk1.8.0_181  
	start.bat ./config         # stop here stop here '#'  , if already install jdk and set  JAVA_HOME ENV
	start.bat G:\new_block_project\helper\src\main\resources   C:\Program Files\Java\jdk1.8.0_181
	start.bat G:\new_block_project\helper\src\main\resources    # stop here stop here '#'  if already install jdk and set  JAVA_HOME ENV

4. config.properties  for BankHelper.jar  , config.properties size >=1
