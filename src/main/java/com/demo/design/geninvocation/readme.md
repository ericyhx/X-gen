设计并实现核心框架中的具体调用模块
    1：具体调用模块的详细功能
        能够控制整个X-gen的调用过程，并能够灵活的扩展这个调用过程
        调用theme提供的action来具体实现每一个需要生成的功能
        能够在每个Action执行前后，动态组合添加一些功能
        能很灵活的通知多个输出实现，并能实现调用模块和输出模块的解耦
    2：具体调用模块的功能边界
        具体调用模块只是负责具体的generate调用过程
        具体调用模块不关心generate的数据从何而来
        具体调用模块不关心实际如何generate
        具体调用模块不关心按照什么流程顺序来generate
        具体调用模块也不关心每个不走都需要完成些什么功能，那都不是固定的，完全可以通过配置或者开发人员在外部theme中定义
    3：具体调用模块对外的接口
        接口已经存在
    4：具体调用模块的内部实现
        包括：状态模式，模板方法模式，工厂方法模式，装饰者模式，观察者模式的综合应用
    5：具体调用模块和其他模块的交互实现
    主要是使用中介者模式