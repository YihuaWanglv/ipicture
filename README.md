# ipicture
ipicture

## 项目目标是试用spring  cloud全套东西去实现一个微服务的ipicture图片平台

## 模块开发日志

### 1.接入eureka服务注册发现中心

### 2.接入config配置中心

### 3.接入monitor服务监控

### 4.接入fastdfs client组件和user和post两个服务

### 5.接入web组件

### 6.接入hystrik/dashboard/turbine

### 7.加入spring cloud bus

- 启动rabbitmq

- 准备一个git repo配置
```
https://github.com/YihuaWanglv/ipicture-config
```
bus所用配置在config-repo文件夹下的config-bus-dev.properties

- 启动服务中心ipicture-server-eureka

- 启动配置中心ipicture-server-config

- 启动busClient ipicture-bus-client

- 访问http://localhost:8087/show 得到
```
hello! testValue=2
```

- 修改config-bus-dev.properties中的test.value=3

- 再次访问http://localhost:8087/show, 依然得到
```
hello! testValue=2
```
没有改变

- rest的方式请求刷新配置
```
curl -X POST http://localhost:8087/bus/refresh
```

- 再次访问http://localhost:8087/show
发现配置修改生效，得到结果
```
hello! testValue=3
```
