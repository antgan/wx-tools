### 注意： 有兴趣可以看源码或进群讨论交流学习~
Wx-tools是基于微信公众平台API的轻量级框架。
基于Wx-tools你可以开速开发一个订阅号/服务号的web应用后台。

## 特性：
* 统一、简单的API，可以快速上手。
* 链式赋值编程，更加容易理解和使用。
* 对于微信服务器发来的消息，提供匹配器（Matcher），拦截器（interceptor），处理器（Handler）接口，定制实现，具有可扩展性。
 
## 超级详细的实例教程，基于SpringBoot+Wx-tools
* [音乐爬虫推送公众号](https://blog.csdn.net/antgan/article/details/80288061)

## w3cschool官方文档
* [wx-tools文档](https://www.w3cschool.cn/wxtools/)

## 讨论：
* BUG反馈及建议：https://github.com/antgan/wx-tools/issues
* 微信开发交流QQ群：一群570937047(已满)，二群（577984291），欢迎加入讨论学习~

## 博主有话说：
* 哟哟哟，终于有时间更新啦！这次教程写得浅显易懂。希望喜欢的给个star咯！！略略略~

## 本地打包
> mvn clean install -Dmaven.test.skip=true -U

## maven依赖
```
<dependency>
  <groupId>com.okracode.wx</groupId>
  <artifactId>wx-tools</artifactId>
  <version>2.1.4-RELEASE-1.0.0</version>
</dependency>
```
