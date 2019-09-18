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

2）SQL和JAVA代码分离（SqlMap把每一条SQL语句起一名字，作为Map的KEY保存  get("selecrById")   ）



手写的orm框架：

1）性能要好，是啥就是啥，不经过二次处理

2）单表操作实现NoSQl

3）ORM零配置实现自动化



原则：约定优于配置（保证代码健壮性）

