# commom-netty
Netty使用还是比较复杂的，做个工具封装下

## 简单体验一下
1. 运行server包中的ServerTest测试用例
  ![服务端](https://github.com/esiyuan/commom-netty/blob/master/Snipaste_2018-10-13_20-55-21.png)
2. 然后运行Client包的ClientTest测试用例
  ![客户端](https://github.com/esiyuan/commom-netty/blob/master/Snipaste_2018-10-13_20-55-49.png)
3. 观察结果
  ![运行结果](https://github.com/esiyuan/commom-netty/blob/master/Snipaste_2018-10-13_21-05-04.png)

## 使用注意
为了灵活替换消息处理对象，采用配置文件的方式。如果不配置，则采用默认的init.properties配置文件，执行测试逻辑。

接入方可以在**classpath**下增加**rpc.properties**文件。

### 服务端
 可以配置自定义请求如何返回参考默认的**DefaultResponseHandlerImpl**,
 也可以自定义ChannelHandler，参考服务端包中的 **DefaultChannelInitializerFactory**
 
 **默认文件为：init.properties**
 ```
  channelInitializerFactory=com.esiyuan.netty.server.DefaultChannelInitializerFactory
  responseHandler=com.esiyuan.netty.server.DefaultResponseHandlerImpl
 ```

### 客户端
可以自定义ChannelHandler，参考客户端包中的**DefaultChannelInitializerFactory**

 **默认文件为：init.properties**
 ```
 channelInitializerFactory=com.esiyuan.netty.client.msg.DefaultChannelInitializerFactory
 ```
