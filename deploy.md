--生成脚手架
--根目录
mvn clean archetype:create-from-project

--进入到target目录的generated-sources的 archetype 目录
mvn clean archetype:create-from-project -Darchetype.properties
mvn clean install

上传
\target\generated-sources\archetype>mvn deploy


archetype-metadata.xml  修改 ${rootArtifactId}-facade下  yml  filtered="true"
Application 修改 scanBasePackages   ${package}
bootstrap.yml 修改 spring:application:name: ${artifactId}
main.xml 修改依赖模块 version ${project.version}
RedisKeyConstant 修改 String redisSys = "${rootArtifactId}"+colon;