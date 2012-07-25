Schneider TDD DOJO User Story
--------------------------------------------------------------------------------
1. 作为散户，我想买入股票，这样当股价上涨时我可以卖出盈利
2. 作为散户，我想卖掉股票，这样在股价上涨时我可以赚钱，下跌时我可以避免蒙受更大损失
3. 作为散户，我想查询成交，这样我可以查看哪些成交已完成或对未完成的成交进行撤单
4. 作为散户，我想查看账户，这样我可以知道持仓状况和查看交易流水
5. 作为系统，我可以轮询行情的分笔成交，这样可以按照价格优先、时间优先的原则，先成交排在最前面的委托

模拟炒股系统交易规则
--------------------------------------------------------------------------------
1. 不能透支及买空卖空
2. T+1（当天买入的股票不能抛出）
3. 股票实际价格到了委托价即全部成交，不受实际买卖委托盘数量的限制
4. 股票价格到了涨跌停板后不再成交，直到停板被打开
5. 撮合成交按照价格优先时间优先的原则，佣金双向收取0.3‰(一次最少5元)

AppFuse Modular Struts 2 Archetype
--------------------------------------------------------------------------------
If you're reading this then you've created your new project using Maven and
tdd.  You have only created the shell of an AppFuse Java EE
application.  The project object model (pom) is defined in the file pom.xml.
The application is ready to run as a web application. The pom.xml file is
pre-defined with Hibernate as a persistence model and Struts 2 as the web
framework.

There are two modules in this project. The first is "core" and is meant to 
contain Services and DAOs. The second is "web" and contains any web-related
files. Using this modular archetype is recommended when you're planning on
using "core" in multiple applications, or you plan on having multiple clients
for the same backend.

To get started, complete the following steps:

1. Download and install a MySQL 5.x database from
   http://dev.mysql.com/downloads/mysql/5.0.html#downloads.

2. From the command line, cd into the core directory and run "mvn install".

3. From the command line, cd into the web directory and run "mvn jetty:run".

4. View the application at http://localhost:8080.

5. More information can be found at:

    http://appfuse.org/display/APF/AppFuse+QuickStart

