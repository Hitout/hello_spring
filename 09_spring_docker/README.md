#Docker
## Dockerfile
Dockerfile 是一个用来构建镜像的文本文件，文本内容包含了一条条构建镜像所需的指令和说明。

### Dockerfile常用指令
| 命令 | 用途 |
| ---- | ---- |
|FROM|基础镜像文件|
|RUN|构建镜像阶段执行命令|
|ADD \<src> \<dest>|添加文件，从src目录复制文件到容器的dest，其中src可以是Dockerfile所在目录的相对路径，也可以是一个URL，还可以是一个压缩包|
|COPY|拷贝文件，和ADD命令类似，但不支持URL和压缩包|
|CMD|容器启动后执行命令|
|EXPOSE|声明容器在运行时对外提供的服务端口|
|WORKDIR|指定容器工作路径|
|ENV|指定环境变量|
|ENTRYPOINT|容器入口，CMD指令的目的一样，都是指定Docker容器启动时执行的命令，可多次设置，但只有最后一个有效|
|USER|用于设置启动镜像时的用户或者UID,写在该指令后的 RUN、CMD以及ENTRYPOINT指令都将使用该用户执行命令|
|VOLUME|指定挂载点，该指令使容器中的一个目录具有持久化存储的功能，该 目录可被容器本身使用，也可共享给其他容器。当容器中的应用有持 久化数据的需求时可以在 Dockerfile中使用该指令。格式为: VOLUME["/data"]|

RUN命令在image文件的构建阶段执行，执行结果都会打包进入image文件；CMD命令则是在容器启动后执行。另外，一个Dockerfile可以包含多个RUN命令，但是只能有一个CMD命令。
指定了CMD命令以后，docker container run命令就不能附加命令了(比如前面的/bin/bash)，否则它会覆盖CMD命令。

### 构建镜像
在 Dockerfile 文件的存放目录下，执行构建动作
```bash
docker build -t 镜像名:TAG .
# 最后一个.表示上下文路径，该路径下的文件会被一起打包
```

## Docker Compose
Docker Compose可以轻松、高效地管理容器，通过docker-compose.yml定义和运行多容器的Docker应用。

### docker-compose.yml常用指令
 - image：指定镜像名或者镜像id
 - build：指定Dockerfile文件的路径
 - external_links：链接到docker-compose.yml外部的容器
 - ports：暴露端口信息
 - expose：暴露端口
 - volumes：卷挂载路径
 - environment：环境变量
 - net：设置网络模式

### 使用Docker Compose
 - `docker-compose up`：以依赖性顺序启动服务
 - `docker-compose stop`：按依赖关系顺序停止服务
