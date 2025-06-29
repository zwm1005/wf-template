### 项目结构
```markdown
xxx-client：调用第三方服务统一封装，处理特殊异常或返回码，隔离service层的影响。
xxx-common：微服务内部通用特征集合，如微服务业务异常码定义，常量定义，异常定义等。
xxx-dao：存储层交互逻辑层，如DB增删改查。
xxx-facade：web实现层，含biz/service引用, 通常是业务的逻辑入口。
xxx-facade-stub：微服务能力接口定义，原则上该模块最简依赖model。
xxx-service：业务逻辑单元封装，具备复用性。
xxx-biz：单元服务聚合层，含复杂业务逻辑，是对service的组装。
xxx-model：领域模型定义，可对外开放。

注意：facade为应用核心层，微服务deploy时默认只有xxx-facade-stub和xxx-model 被上传到私服。
```
![](structure.jpg)

### 基础架构
```markdown
wf-core-base: 微服务通讯协议体定义，如result对象等。
wf-core-boot: springframework启动组件自定义相关配置,如日志相关组件加载。
wf-core-util: 常用工具类，自定义log注解等。
wf-starter-cache: 统一缓存封装。
wf-starter-database: 数据库组件及配置封装。
wf-starter-feign: 默认微服务集成feign客户端，定义熔断等RPC策略。
wf-starter-lock: 分布式锁封装。
wf-starter-logger: slf4j日志实现封装，当前使用log4j2。
wf-starter-mq: 消息队列rocketmq客户端。
wf-starter-nacos: 默认集成微服务配置中心。
wf-starter-process: 提供流程编排相关抽象，可用于复杂业务场景。
wf-starter-sentinel: 限流相关组件。
wf-starter-sequence: 分布式自增ID生成。
wf-starter-timer: 分布式延迟队列实现。
wf-starter-transaction: 基于本地事务表的分布式事务解决方案。
```
