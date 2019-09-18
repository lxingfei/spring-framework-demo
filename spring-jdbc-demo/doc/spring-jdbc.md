###### JDBC过程：

1）加载驱动类 --》基于mysql

2）获取连接（被封装到DataSource里面）

3）创建语句集（预处理语句集和标准语句集）

4）执行语句集（执行事务操作）

5）获取结果集（若是增删改，返回int类型，表示影响的行数，若是查询，返回一个ResultSet）



###### mybatis 是一个半自动的ORM框架  

不用写jdbc的代码但需要配置 mapping.xml 文件

如果类结构和数据库表结构完全一样，它能利用反射机制自动匹配成一个java对象。



###### Hibernate 是一个全自动的ORM框架  Hibernate 5  HQL

不用写jdbc代码也不用配置



###### spring-jdbc是一个手动的ORM框架

spring-jdbc 采用Tempalte设计模式，只定义了一个RowMapper的接口

mapping方法。这个方法没有实现类。ORM由自己实现。

spring-jdbc手写sql实现多对多，一对多，1对1



###### 手写一个Jdbc框架

1）单表操作实现nosql，由程序自动生成sql

2）实现手动ORM----》自动ORM



spring 万能胶 基于BOP编程 只要是java语言就能整合

org.springframework.jdbc.core.JdbcTemplate



Hibernate 优点：

1）API丰富，实现无sql操作（HQL）,为了兼容所有数据库（都会先解释为HQL，再由HQL翻译成SQL，也有直接执行SQL的API，为了考虑用户需求的复杂性，对所有数据库方言都支持的不错

2）ORM全自动化



Mybatis优点：

1）轻量级，性能好

2）SQL和JAVA代码分离（核心-》SqlMap-》把每一条SQL语句起一名字，作为Map的KEY保存  get("selecrById")   ）



手写的orm框架：（则其善者而从之，开发出适合自己的框架）

1）性能要好，是啥就是啥，不经过二次处理（不对SQL语句进行二次包装）

2）单表操作实现NoSQl（只要用JDBC,SQL都不能省,只不过拼接SQL的过程不要用户自己去写,而是由程序自动拼接生成,最终生成一个字符串）

   QueryRule工具类--> 自动生成SQL语句
   
3）ORM零配置实现自动化 （利用反射机制，把字段和属性对应，自动实例化并返回结果）




原则：约定优于配置（保证代码健壮性）
     DAO原则：一个DAO只操作一张表-->对应一个实体
     做修改和删除等操作时根据主键进行操作
     尽量使用单表操作,若实在需要多表操作,可以先把数据查出来放内存中,然后在内存中进行计算
     约定：支持读写分离 （提升性能）
     约定：支持分库分表 （解决单表数据量过大）
     约定：原则上ORM支持的类型只认java 8大基本类型 + String（降低复杂度）

