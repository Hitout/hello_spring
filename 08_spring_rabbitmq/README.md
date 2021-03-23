# RabbitMQ
## 使用
```
docker run -d --name rabbitmq -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15672:15672 rabbitmq:management
```
端口（https://www.rabbitmq.com/networking.html ）：
- 5671、5672：AMQP端口
- 15672：web管理后台端口
- 4369，25672：Erlang发现&集群端口

## 架构
![RabbitMQ](http://gxyan.gitee.io/file/img/RabbitMQ.png)

- Broker：接收和分发消息的应用
- Virtual Host：虚拟分组，不同的用户可以在不同的Virtual Host中创建 exchange/queue 等
- Connection：publisher/consumer 和 broker 之间建立 TCP 连接
- Channel：在 connection 内部建立的逻辑连接，通常每个thread创建单独的channel进行通讯，AMQP method包含channel id帮助客户端和message broker识别channel，所以channel之间是完全隔离的。Channel作为轻量级的Connection极大减少了操作系统建立TCP连接的开销


## 工作模式
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

## 高级特征
### 消息的可靠投递
#### 消息发送确认
RabbitMQ 为我们提供了两种方式用来控制消息的投递可靠性（防止消息丢失或者投递失败的场景）：
##### confirm：确认模式
使用rabbitTemplate.setConfirmCallback设置回调函数，当消息发送到exchange后回调confirm方法。

需开启发送回调`publisher-confirm-type: SIMPLE`
##### return：退回模式
使用rabbitTemplate.setReturnCallback设置退回函数，当消息从exchange路由到queue失败执行returnedMessage方法。

需开启消息路由失败回调`publisher-returns: true`

#### 消息消费确认
消息消费确认模式有：
- AcknowledgeMode.NONE：自动确认，consumer收到消息后自动将相应的message从RabbitMQ中移除
- AcknowledgeMode.AUTO：根据情况确认
- AcknowledgeMode.MANUAL：手动确认，调用channel.basicAck()手动签收

通过设置`spring.rabbitmq.listener.direct.acknowledge-mode=MANUAL`开启手动确认，调用basicAck方法确认签收消息，调用basicNack或basicReject，拒绝消息

### 消费端限流
通过`channel.basicQos`设置消费的大小和数量，注意需要开启消费端手动确认（acknowledge-mode=MANUAL）

### TTL
通过参数`x-message-ttl`（整个队列）或`expiration`（单条消息）设置过期时间，到达过期时间没有被消费就会被清除

### 死信队列
Dead-Letter-Exchange(DLX)死信交换机，若消息绑定了死信交换机消息会在以下情况进入死信队列：
- 消息长度达到限制
- 消费者拒绝接受并且不重写入队
- 到达过期时间未被消费

### 延迟队列
RabbitMQ中可以通过 TTL+死信队列 方式实现延迟队列效果

![延迟队列](http://gxyan.gitee.io/file/img/Delay_Queue.png)

### 其它问题
- 消息幂等：多次消费相同消息能得到相同的结果
- 消息积压：增加消费者，或上线专门的消费服务将消息持久化再处理