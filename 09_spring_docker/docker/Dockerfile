FROM java:8
# 暴露端口
EXPOSE 9001

# 挂载目录
VOLUME /tmp
# 复制文件到容器
ADD hello.jar /app.jar
# 通过bash更新app.jar时间属性
RUN bash -c "touch /app.jar"
# 运行命令 java -jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
