homework1 Hadoop

2.在搭建的伪分布式系统上完成一个文本的词频统计，即文本中每个单词出现的次数。
命令：
	hadoop wc.jar input output
解释：
	wc.jar 该代码打包后形成的 jar 文件
	input 需要被统计的文件
	output 统计后形成的文件

3.在上述基础上完成单词按出现频率降序排列，并输出文本中含有的单词总数（不包含a，an，the等冠词）
命令：
	hadoop wc1.jar input output
解释：
	wc1.jar 该代码打包后形成的 jar 文件
	input 需要被统计的文件,这里是 2 中的输出内容
	output 统计后形成的文件

homework2 RMI

1. 编译 javac -Djava.ext.dirs=lib *.java
2. start rmiregistry.exe
3. java -Djava.ext.dirs=lib RMIServer
4. java -Djava.ext.dirs=lib RMIClient

homework3 upload file

1. 导入到eclipse
2. 在 E 盘准备 a.txt 文件
3. 执行Server
4. 执行Client
5. 终端显示上传成功，在 E 盘查看上传的文件 s_a.txt

