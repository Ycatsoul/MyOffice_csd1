# CapGemini China Spring Boot Project Demo
该项目是基于CapGemini CoE项目构建的单体应用示例项目，下面会对新项目的创建使用方式、目录结构及各类规范进行详细说明。

## 详细说明
这是一个标准的Maven项目，为了简化项目初始阶段的依赖配置我们提供了凯捷自己的Maven父依赖`coe-spring-boot-parent`项目来管理工程中所需要使用到的公共依赖及其版本，因此所有的Spring Boot工程都需要配置对应的依赖：
```
    <parent>
        <groupId>com.capgemini.cn</groupId>
        <artifactId>coe-spring-boot-parent</artifactId>
        <version>1.0.0</version>
    </parent>
```

### 项目包说明
所有的项目都应该使用`com.capgemini.cn`作为基础的包路径，然后再到后面接上项目自己的业务名称，例如：`com.capgemini.cn.xxx`。在基础包路径下我们做了一些基本的子包划分：

|包路径|说明|
|:---|:---|
|com.capgemini.cn.xxx.config|系统中个性化的配置文件加载类都存放在此包路径中|
|com.capgemini.cn.xxx.utils|当前项目需要使用到的公共工具类存放包路径|
|com.capgemini.cn.xxx.data.domain|用以存放数据库对应的实体类|
|com.capgemini.cn.xxx.data.repository|用以存放操作数据库的Repository接口|
|com.capgemini.cn.xxx.data.dao|用以存放操作数据库的Dao接口，通常需要操作多个Repository形成新的DTO时会使用到DAO层|
|com.capgemini.cn.xxx.data.dao.impl|用以存放操作数据库的Dao接口实现类，通常需要操作多个Repository形成新的DTO时会使用到DAO层|
|com.capgemini.cn.xxx.data.dto|用以存放数据转换对象实体类|
|com.capgemini.cn.xxx.data.cache|用以存放操作缓存相关的类|
|com.capgemini.cn.xxx.service|用以存放业务逻辑处理的Service接口|
|com.capgemini.cn.xxx.service.impl|用以存放业务逻辑处理的Service接口实现类|
|com.capgemini.cn.xxx.vo.request|求值对象存放包路径，所有的类都应该以xxxReq进行命名|
|com.capgemini.cn.xxx.vo.response|响应值对象存放包路径，所有的类都应该以xxxResp进行命名|
|com.capgemini.cn.xxx.controller|提供给外部客户端调用的接口都应该放在该包路径下，所有的类都应该以xxxController进行命名|