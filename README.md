# dubbo-demo

maven项目，需要对maven有基本的了解，运行之前需要下载 ```dubbo-admin``` 或者去 dubbo 下载源代码自己打包成 ```dubbo-admin.war``` 然后放在相应目录；
在 WEB-INF 下的 class 目录下编辑 dubbo.properties 文件：

        dubbo.registry.address=zookeeper://127.0.0.1:2181
        dubbo.admin.root.password=root
        dubbo.admin.guest.password=guest
## 配置zookeeper
