# RabbitMQ
## 安装
```
docker run -d --name rabbitmq -p 5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15672:15672 rabbitmq:management
```
端口（https://www.rabbitmq.com/networking.html ）：
- 5671、5672：AMQP端口
- 15672：web管理后台端口
- 4369，25672：Erlang发现&集群端口

