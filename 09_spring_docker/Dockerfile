FROM java:8
# 暴露端口
EXPOSE 9001

# 挂载目录
VOLUME /tmp
# hello.jar重命名为app.jar
ADD target/hello.jar /app.jar
# 创建/更新app.jar
RUN bash -c "touch /app.jar"
# 运行命令 java -jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
