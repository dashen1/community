##元码社区


##工具..

##脚本
```sql
create table USER
(
	ID INT auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);


```
##问题信息
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
 "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" 标红缺少包
 <dependency>
             <groupId>org.mybatis.generator</groupId>
             <artifactId>mybatis-generator-core</artifactId>
             <version>1.4.0</version>
         </dependency>
##
##
解决办法：不用上述方式编译打包，改用命令行方式跳过Test，命令如下：
mvn clean package -DskipTests
java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
这样maven就不会进行Test相关的编译了。
netstat -ntlp   //查看当前所有tcp端口
netstat -ntulp | grep 80   //查看所有80端口使用情况
netstat -ntulp | grep 3306   //查看所有3306端口使用情况
传输文件
##
##
从github上拉取代码时报，merge错误，直接强制更新
git fetch --all //只是下载代码到本地，不进行合并操作
git reset --hard origin/master  //把HEAD指向最新下载的版本
[error: Your local changes to the following files would be overwritten by merge:
 	logs/community.log/spring.log
 Please, commit your changes or stash them before you can merge.]
 
 Mysql数据库迁移
 大小写问题
 ctrl+alt+u大写变小写
 v5__-- alter table USER alter column AVATAR rename to AVATAR_URL;
 alter table USER change AVATAR AVATAR_URL VARCHAR (150);
 alter table QUESTION modify CREATOR bigint NOT NULL;
 alter table COMMENT modify COMMENTATOR bigint NOT NULL;
 只能有一个自增列ID
 
 mvn clean compile flyway:migrate -Pdev
     <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                           https://maven.apache.org/xsd/settings-1.0.0.xsd">
       <localRepository/>
       <interactiveMode/>
       <offline/>
       <pluginGroups/>
       <servers/>
       <mirrors/>
       <proxies/>
       <profiles>
               <profile>
                   <id>dev</id>
                   <properties>
                       <db.url>jdbc:h2:~/community1</db.url>
                       <db.user>sa</db.user>
                       <db.password>123</db.password>
                   </properties>
                   <activation>
                       <activeByDefault>true</activeByDefault>
                   </activation>
               </profile><profile>
                   <id>production</id>
                   <properties>
                       <db.url>jdbc:mysql://rm-2zehw066ml2180ewq.mysql.rds.aliyuncs.com:3306/maven</db.url>
                       <db.user>root</db.user>
                       <db.password>root@123456</db.password>
                   </properties>
               </profile>
           </profiles>
       <activeProfiles/>
     </settings>
##
