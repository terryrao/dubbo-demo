# dubbo-demo

maven 项目，需要对 maven 有基本的了解，运行之前需要下载 ```dubbo-admin``` 或者去 [dubbo](https://github.com/alibaba/dubbo.git) 下载源代码自己打包成 ```dubbo-admin.war``` 然后放在相应目录；
在 WEB-INF 下的 class 目录下编辑 dubbo.properties 文件：

        dubbo.registry.address=zookeeper://127.0.0.1:2181
        dubbo.admin.root.password=root
        dubbo.admin.guest.password=guest
## 配置zookeeper
linux 上的 zookeeper 的配置请自行搜索百度或者 google。
配置 windows 上的需要 
 1. 复制 %ZOOKEEPER_HOME%/conf 的 zoo_sample.cfg 为 zoo.cfg
 2. 配置 dataDir=yourDataDir
 3. 配置 dataLogDir=yourDataLogDir
其它使用默认配置即可；启动 zookeeper。

## 配置 tomcat

参考 [Java Web开发Tomcat中三种部署项目的方法](http://shuyangyang.blog.51cto.com/1685768/1040127)。
 

 
