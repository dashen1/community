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