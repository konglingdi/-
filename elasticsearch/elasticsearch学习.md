## elasticsearch学习

es依赖jdk

### 一、单机版es

#### 1、下载es

wget  https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.6.2-linux-x86_64.tar.gz

#### 2、安装es

###### 注意：出于安全考虑，es不允许root账户启动es.

##### 2.1创建一个用户

useradd es

##### 2.2解压es

tar -zxvf xxx.tar.gz

##### 2.3修改es配置文件

###### 2.3.1jvm.options（虚拟机启动参数）文件

```java
-Xms512m
-Xmx512m
堆大小
```

###### 2.3.2elasticsearch.yml文件

```
node.name: node-1 #配置当前es节点名称（默认是被注释的，并且默认有一个节点名）
cluster.name: my-application #默认是被注释的，并且默认有一个集群名

path.data: /home/es/data # 数据目录位置
path.logs: /home/es/logs # 日志目录位置
network.host: 0.0.0.0   #绑定的ip：默认只允许本机访问，修改为0.0.0.0后则可以远程访问
cluster.initial_master_nodes: ["node-1", "node-2"] #默认是被注释的 设置master节点列表 用逗号分隔
```

###### elasticsearch.yml的其它可配置信息：

| 属性名                             | 说明                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| cluster.name                       | 配置elasticsearch的集群名称，默认是elasticsearch。建议修改成一个有意义的名称。 |
| node.name                          | 节点名，es会默认随机指定一个名字，建议指定一个有意义的名称，方便管理 |
| path.conf                          | 设置配置文件的存储路径，tar或zip包安装默认在es根目录下的config文件夹，rpm安装默认在/etc/ elasticsearch |
| path.data                          | 设置索引数据的存储路径，默认是es根目录下的data文件夹，可以设置多个存储路径，用逗号隔开 |
| path.logs                          | 设置日志文件的存储路径，默认是es根目录下的logs文件夹         |
| path.plugins                       | 设置插件的存放路径，默认是es根目录下的plugins文件夹          |
| bootstrap.memory_lock              | 设置为true可以锁住ES使用的内存，避免内存进行swap             |
| network.host                       | 设置bind_host和publish_host，设置为0.0.0.0允许外网访问       |
| http.port                          | 设置对外服务的http端口，默认为9200。                         |
| transport.tcp.port                 | 集群结点之间通信端口                                         |
| discovery.zen.ping.timeout         | 设置ES自动发现节点连接超时的时间，默认为3秒，如果网络延迟高可设置大些 |
| discovery.zen.minimum_master_nodes | 主结点数量的最少值 ,此值的公式为：(master_eligible_nodes / 2) + 1 ，比如：有3个符合要求的主结点，那么这里要设置为2 |

##### 2.4 修改/etc/security/limits.conf文件 增加配置

```linux
vi /etc/security/limits.conf 
```

在文件最后添加

```
* soft nofile 65536
* hard nofile 65536
```

在/etc/sysctl.conf文件最后添加一行 vm.max_map_count=655360 添加完毕之后，执行命令： sysctl -p



###### 启动时遇到的问题：

```java
xpack的异常
修改文件elasticsearch.yml，在末尾添加
    xpack.ml.enabled: false

[1]: JVM is using the client VM [Java HotSpot(TM) Client VM] but should be using a server VM for the best performance
修改文件 
JAVA_HOME\jre\lib\i386\jvm.cfg
    -server KNOWN      //原来在第二行
    -client IF_SERVER_CLASS -server
    -minimal KNOWN

[2]: system call filters failed to install; check the logs and fix your configuration or disable system call filters at your own risk
修改文件elasticsearch.yml
        bootstrap.memory_lock: false
 		bootstrap.system_call_filter: false
```



###### 外部网络能访问linux服务器端口号：

```
修改iptables文件
    #安装iptables
    yum install -y iptables
    #安装iptables-services
    yum install iptables-services
    iptables-services 和 iptables 是不一样的
    安装了 services才有/etc/sysconfig/iptables
    
    -A INPUT -p tcp -m state --state NEW -m tcp --dport 8080 -j ACCEPT
    
    service iptables restart
 
```

#### 3、教程

参考链接：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html

#### 4、安装ik分词器

https://github.com/medcl/elasticsearch-analysis-ik

注意版本号：pom.xml处可以查看ik的版本号。

```java
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.3.0/elasticsearch-analysis-ik-6.3.0.zip
```

#### 5、安装head插件，可视化界面

5.1安装node.js环境

```java
# wget https://nodejs.org/dist/v10.9.0/node-v10.9.0-linux-x64.tar.xz    // 下载
# xz xf  node-v10.9.0-linux-x64.tar.xz       // 解压
# tar node-v10.9.0-linux-x64.tar
# cd node-v10.9.0-linux-x64/                  // 进入解压目录
# ./bin/node -v                               // 执行node命令 查看版本
创建软连接（绝对路径）
ln -s /usr/local/node-v10.9.0-linux-x64 /bin/node /usr/local/bin/node
ln -s /usr/local/node-v10.9.0-linux-x64 /bin/npm /usr/local/bin/npm

配置环境变量
vim /etc/profile
export NODE_HOME=/usr/local/node
export PATH=$PATH:$NODE_HOME/bin

source /etc/profile
```

5.2下载head插件

```java
yum install –y git

git clone https://github.com/mobz/elasticsearch-head.git
```

5.3安装grunt

```java
cd elasticsearch-head
npm install -g grunt --registry=https://registry.npm.taobao.org
```

5.4安装插件

```java
npm install

在elasticsearch-head目录下node_modules/grunt下如果没有grunt二进制程序，需要执行：
npm install grunt --save
```

5.5修改配置 elasticsearch-head下Gruntfile.js文件

修改connect配置节点

![img](https://images2018.cnblogs.com/blog/969723/201808/969723-20180808135255854-397386573.png)

修改 _site/app.js 修改http://localhost:9200字段到本机ES端口与IP

 ![img](https://images2018.cnblogs.com/blog/969723/201808/969723-20180808135653133-708174991.png)

5.6修改 elasticsearch配置文件 

 修改elasticsearch.yml文件加入以下内容：

```
# 是否支持跨域
http.cors.enabled: true

# *表示支持所有域名
http.cors.allow-origin: "*"
```

![img](https://images2018.cnblogs.com/blog/969723/201808/969723-20180808135838136-1385270717.png)

5.7启动head插件服务（后台运行）

```java
/elasticsearch-head/node_modules/grunt/bin/grunt server &
```

安装head插件遇到的问题：

npm install时总是失败。

```java
Error: Command failed: tar jxf /tmp/phantomjs/phantomjs-2.1.1-linux-x86_64.tar.bz2

解决方法：
yum install bzip2
```

```java
npm ERR! Failed at the phantomjs-prebuilt@2.1.16 install script.

解决方法：
npm install phantomjs-prebuilt@2.1.14 --ignore-scripts
```

最后再npm install就好了。