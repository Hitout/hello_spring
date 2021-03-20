# RabbitMQ
## 安装
```
docker run -d --name rabbitmq -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15672:15672 rabbitmq:management
```
端口（https://www.rabbitmq.com/networking.html ）：
- 5671、5672：AMQP端口
- 15672：web管理后台端口
- 4369，25672：Erlang发现&集群端口

## 使用
### 简单模式
一个生产者、一个消费者，不需要设置交换机（使用默认的交换机）。

### 工作队列模式 Work Queue
一个生产者、多个消费者（竞争关系），不需要设置交换机（使用默认的交换机）。

### 发布订阅模式 Publish/subscribe
需要设置类型为 fanout 的交换机，并且交换机和队列进行绑定，当发送消息到交换机后，交换机会将消息发送到绑定的队列。

### 路由模式 Routing
需要设置类型为 direct 的交换机，交换机和队列进行绑定，并且指定`routing key`，当发送消息到交换机后，交换机会根据`routing key`将消息发送到对应的队列。

### 通配符模式 Topic
需要设置类型为 topic 的交换机，交换机和队列进行绑定，并且指定**通配符**方式的`routing key`，当发送消息到交换机后，交换机会根据`routing key`将消息发送到对应的队列。
