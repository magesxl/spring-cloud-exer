模块包括：服务发现（Eureka），断路器（Hystrix），智能路有（Zuul），客户端负载均衡（Ribbon）

eureka-server  服务注册中心

compute-service  生产者--服务提供者

eureka-ribbon  消费者  负载均衡

feign-consumer  消费者

Feign是一个声明式的Web Service客户端，它使得编写Web Serivce客户端变得更加简单。
我们只需要使用Feign来创建一个接口并用注解来配置它既可完成。
它具备可插拔的注解支持，包括Feign注解和JAX-RS注解。Feign也支持可插拔的编码器和解码器。
Spring Cloud为Feign增加了对Spring MVC注解的支持，
还整合了Ribbon和Eureka来提供均衡负载的HTTP客户端实现。

hystrix默认是关闭状态，必须在application中配置feign.hystrix.enabled=true才能正常使用，否则断路器不生效

仓库中的配置文件会被转换成web接口，访问可以参照以下的规则：
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
以neo-config-dev.properties为例子，它的application是neo-config，profile是dev。client会根据填写的参数来选择读取对应的配置。

spring.application.name：对应{application}部分
spring.cloud.config.profile：对应{profile}部分
spring.cloud.config.label：对应git的分支。如果配置中心使用的是本地存储，则该参数无用
spring.cloud.config.uri：配置中心的具体地址
spring.cloud.config.discovery.service-id：指定配置中心的service-id，便于扩展为高可用配置集群。
特别注意：上面这些与spring-cloud相关的属性必须配置在bootstrap.properties中，config部分内容才能被正确加载。
因为config的相关配置会先于application.properties，
而bootstrap.properties的加载也是先于application.properties。

配置服务中心更新后
Spring Cloud Config分服务端和客户端，服务端负责将git（svn）中存储的配置文件发布成REST接口，客户端可以从服务端REST接口获取配置。
但客户端并不能主动感知到配置的变化，从而主动去获取新的配置。客户端如何去主动获取新的配置信息呢，
springcloud已经给我们提供了解决方案，每个客户端通过POST方法触发各自的/refresh  
手动执行命令刷新：curl -X POST http://localhost:8002/refresh 
1.每次手动刷新客户端也很麻烦，有没有什么办法只要提交代码就自动调用客户端来更新呢，github的webhook是一个好的办法
WebHook是当某个事件发生时，通过发送http post请求的方式来通知信息接收方。
Webhook来监测你在Github.com上的各种事件，最常见的莫过于push事件。
如果你设置了一个监测push事件的Webhook，那么每当你的这个项目有了任何提交，这个Webhook都会被触发，这时Github就会发送一个HTTP POST请求到你配置好的地址。
如此一来，你就可以通过这种方式去自动完成一些重复性工作，比如，你可以用Webhook来自动触发一些持续集成（CI）工具的运作，比如Travis CI；
又或者是通过 Webhook 去部署你的线上服务器。下图就是github上面的webhook配置。
2.客户端都是直接调用配置中心的server端来获取配置文件信息。
这样就存在了一个问题，客户端和服务端的耦合性太高，如果server端要做集群，客户端只能通过原始的方式来路由，
server端改变IP地址的时候，客户端也需要修改配置，不符合springcloud服务治理的理念。
springcloud提供了这样的解决方案，我们只需要将server端当做一个服务注册到eureka中，client端去eureka中去获取配置中心server端的服务既可
3.
Spring cloud bus通过轻量消息代理连接各个分布的节点。这会用在广播状态的变化（例如配置变化）或者其他的消息指令。
Spring bus的一个核心思想是通过分布式的启动器对spring boot应用进行扩展，也可以用来建立一个多个应用之间的通信频道。
目前唯一实现的方式是用AMQP消息代理作为通道，同样特性的设置（有些取决于通道的设置）在更多通道的文档中
配置中心客户端刷新就是典型的应用场景之一

Spring Cloud Bus做配置更新的步骤:

1、提交代码触发post请求给bus/refresh
2、server端接收到请求并发送给Spring Cloud Bus
3、Spring Cloud bus接到消息并通知给其它客户端
4、其它客户端接收到通知，请求Server端获取最新配置
5、全部客户端均获取到最新的配置


Zuul路由是微服务架构的不可或缺的一部分，提供动态路由，监控，弹性，安全等的边缘服务

通过url映射的方式来实现zull的转发有局限性，比如每增加一个服务就需要配置一条内容，另外后端的服务如果是动态来提供，就不能采用这种方案来配置了。
实际上在实现微服务架构时，服务名与服务实例地址的关系在eureka server中已经存在了，
所以只需要将Zuul注册到eureka server上去发现其他服务，就可以实现对serviceId的映射

但是如果后端服务多达十几个的时候，每一个都这样配置也挺麻烦的，spring cloud zuul已经帮我们做了默认配置。
默认情况下，Zuul会代理所有注册到Eureka Server的微服务，
并且Zuul的路由规则如下：http://ZUUL_HOST:ZUUL_PORT/微服务在Eureka上的serviceId/**会被转发到serviceId对应的微服务

在Spring Cloud Zuul中实现的过滤器必须包含4个基本特征：过滤类型、执行顺序、执行条件、具体操作。这些元素看着似乎非常的熟悉，
实际上它就是ZuulFilter接口中定义的四个抽象方法：

String filterType();
    
int filterOrder();
    
boolean shouldFilter();
    
Object run();
它们各自的含义与功能总结如下：
filterType：该函数需要返回一个字符串来代表过滤器的类型，而这个类型就是在HTTP请求过程中定义的各个阶段。在Zuul中默认定义了四种不同生命周期的过滤器类型，
            具体如下：
        pre：可以在请求被路由之前调用。
        routing：在路由请求时候被调用。
        post：在routing和error过滤器之后被调用。
        error：处理请求时发生错误时被调用。
filterOrder：通过int值来定义过滤器的执行顺序，数值越小优先级越高。
shouldFilter：返回一个boolean类型来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围。
run：过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等。


第一个值：trace-1，它记录了应用的名称，也就是application.properties中spring.application.name参数配置的属性。
第二个值：f410ab57afd5c145，Spring Cloud Sleuth生成的一个ID，称为Trace ID，它用来标识一条请求链路。一条请求链路中包含一个Trace ID，多个Span ID。
第三个值：a9f2118fa2019684，Spring Cloud Sleuth生成的另外一个ID，称为Span ID，它表示一个基本的工作单元，比如：发送一个HTTP请求。
第四个值：false，表示是否要将该信息输出到Zipkin等服务中来收集和展示。
Spring Cloud Sleuth会在产生跟踪信息的时候调用它来为跟踪信息生成是否要被收集的标志。需要注意的是，即使isSampled返回了false，它仅代表该跟踪信息不被输出到后续对接的远程分析系统（比如：Zipkin），对于请求的跟踪活动依然会进行，所以我们在日志中还是能看到收集标识为false的记录。
默认情况下，Sleuth会使用PercentageBasedSampler实现的抽样策略，以请求百分比的方式配置和收集跟踪信息，我们可以通过在application.properties中配置下面的参数对其百分比值进行设置，它的默认值为0.1，代表收集10%的请求跟踪信息。
http://blog.didispace.com/spring-cloud-starter-dalston-8-6/


@EnableEurekaServer  注解启动一个服务注册中心提供给其他应用进行对话

@EnableDiscoveryClient注解，该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出。

@LoadBalanced注解开启均衡负载能力

@EnableFeignClients 在应用主类中通过注解开启Feign功能

@FeignClient("compute-service")注解来绑定该接口对应compute-service服务

@EnableCircuitBreaker注解开启断路器功能

@EnableConfigServer注解，开启Config Server激活对配置中心的支持

@RefreshScope  使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。

@EnableZuulProxy，支持网关路由

@EnableZipkinServer 支持收集跟踪

