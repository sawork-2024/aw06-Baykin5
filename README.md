[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/IMEm063v)
# Micro WebPoS 


请参考spring-petclinic-rest/spring-petclinic-microserivces 将webpos项目改为微服务架构，具体要求包括：
1. 至少包含独立的产品管理服务、订单管理服务以及discovery/gateway等微服务架构下需要的基础设施服务；
2. 请将系统内的不同微服务实现不同的计算复杂度，通过压力测试实验验证对单个微服务进行水平扩展（而无需整个系统所有服务都进行水平扩展）可以提升系统性能，请给出实验报告；
3. 请使用`RestTemplate`进行服务间访问，验证Client-side LB可行；
4. 请注意使用断路器等机制；
5. 如有兴趣可在kubernetes或者minikube上进行部署。

请编写readme对自己的系统和实验进行详细介绍。



### 项目架构

项目包含基础设施Eureka名服务，Api-GateWay网关服务。Api-Gateway可以将发往端口8080的请求路由到ProductService和OrderService上。

ProductService中使用了断路器机制，当数据库中products表为空时访问`/products`，返回值为只包含一个名称为"ERROR"的商品列表。

OrderService中使用了restTemplate机制，其存储订单使用的Item数据结构只保存了pid（商品id），在访问`/checkout`资源时，会调用restTemplate访问product-service下的`/products/*`资源以获取商品价格并计算。

![](C:\Files\研一下课程\软件体系结构\作业\aw06-Baykin5\pic\1.png)

#### 

![image-20240515110745186](C:\Files\研一下课程\软件体系结构\作业\aw06-Baykin5\pic\2.png)

### 压力测试

使用gatling，在压力测试脚本中依次访问`/products/`,`/items`,`/checkout`

`/products`和`/items`资源的获取只需要读取数据库，相当于/IO密集型任务，`/checkout`资源的获取还需要进行服务间访问和计算，相当于综合型任务。

使用gatling对api-gateway执行1000次脚本，分别在1，2，3个product-service实例下进行测试，实验结果如下：

#### 1个product-service实例

![image-20240515113649422](C:\Files\研一下课程\软件体系结构\作业\aw06-Baykin5\pic\3.png)

#### 2个product-service实例

![image-20240515113654647](C:\Files\研一下课程\软件体系结构\作业\aw06-Baykin5\pic\4.png)

#### 3个product-service实例

![image-20240515113659363](C:\Files\研一下课程\软件体系结构\作业\aw06-Baykin5\pic\5.png)

可以很明显看出整个服务的性能提升，可以得出微服务系统中，单个微服务的水平扩展可以提高整个系统的性能。
