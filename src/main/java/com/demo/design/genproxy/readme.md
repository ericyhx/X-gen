代理模块
生成代理模块的功能边界
    1:生成代理模块不关心谁要求真正执行，也不关心究竟谁来真正的执行，它只是根据配置和客户端的选择，来代理找到具体执行功能的对象
    ，并启动具体执行generate功能的对象
生成代理模块的对外接口
    事实上，代理模块对外是没有自己的接口的，它实现的是被代理的对象的接口，也就是生成调用的接口
面临的问题：
    由于要在分发调度模块和具体生成模块之间引入一个间接性，以备在需要的时候，可以让相同的分发调度模块调用不同的具体生成模块，
    而且要考虑到可能需要访问远程的功能
    简单点说，就是需要一种访问代理，就用代理模式来解决
定义：
    为其他对象提供一种代理以控制对这个对象的访问
知识要点：
    1：代理模式是通过创建一个代理对象，用这个代理对象去代表真实的对象，客户端得到这个代理对象以后，直接当真实对象去操作
    2：代理对象夹在客户端和被代理的真实对象中间，相当于一个中转，那么在中转你的时候就可以做很多工作，代理模式的功能也主要通过现在中转
    的时候进行实现，比如在中转前后附件很多操作等
    3：代理分成很多种种类，开发中最常见的是虚代理和保护代理
    4：虚代理，刚开始创建一个“虚”代理对象返回给客户端，知道客户端需要真正使用这个 对象的时候，代理才真正去创建这个对象访问
    的代理，从而变相实现一个延迟装载，节省资源
    5：保护代理是一种控制对原始对象访问的代理，保护代理会检查调用者是否有请求所必需的访问权限，如果没有相应的权限，那么就
    不会调用目标对象，从而实现对目标对象的保护
    6：Java中的静态和动态代理，不过java的动态代理目前只能代理接口，基本的实现是依靠java的反射机制和动态生成class的技术，来动态
    生成被代理的接口的实现对象
本质：控制对对象的访问
何时选用：
    1：为一个对象在不同的地址空间提供局部代表的时候，可以使用远程代理
    2：需要安装需要创建开销很大的对象的时候，可以使用虚代理
    3：需要控制对原始对象的访问的时候，可以使用保护代理
    4：需要在访问对象的时候执行一些附加操作的时候，可以使用智能指引代理



设计并实现核心框架中的具体调用模块
    1：具体调用模块的详细功能
        能够控制整个X-gen的调用过程，并能够灵活的扩展这个调用过程
        调用theme提供的action来具体实现每一个需要生成的功能
        能够在每个Action执行前后，动态组合添加一些功能
        能很灵活的通知多个输出实现，并能实现调用模块和输出模块的解耦
    2：具体diaoyo9ng模块的功能边界
    3：具体嗲用模块对外的接口
    4：具体调用模块的内部实现
        包括：状态模式，模板方法模式，工厂方法模式，装饰者模式，观察者模式的综合应用
    5：具体调用模块和其他模块的交互实现
    主要是使用中介者模式