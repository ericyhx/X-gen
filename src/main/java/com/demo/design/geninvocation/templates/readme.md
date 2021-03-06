模板方法模式
定义：
    定义一个操作中的算法的骨架，而将一些步骤延迟到子类中，模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些
    特定的步骤
知识要点：
    1：模板方法模式的功能在于固定算法骨架，而让具体算法实现可扩展
    2：模板方法模式通常实现成为抽象类，而不是接口
    3：程序设计的一个很重要的思考点就是“变与不变”，模板类实现的就是不变的方法和算法的骨架，而需要变化的地方，都通过抽象方法，
    把具体实现延迟到子类去了，而且还通过父类的定义来约束了子类的行为，从而使系统能有更好的服用性和扩展性
    4：模板方法模式体现了好莱坞法则，是一种父类来找子类的方向的控制结构
    5：可以使用java回调来实现模板方法，回调机制是通过委托的方式来组合功能，他的耦合强度要比继承低一些，这回给我们更多的灵活性
    6：要重点区分模板方法模式中的一些常见的类型
        1）模板方法：就是定义算法骨架的方法
        2）具体的操作：在模板中直接实现某些步骤的方法，通常这些步骤的实现算法是固定的，而且是不怎么变化的，因此就可以当做
        公共功能实现在模板里面
        3）具体的AbstractClass操作：在模板中实现某些公共功能，可以提供给子类使用，一般不是具体的算法步骤的实现，只是一些
        辅助的公共功能
        4）原语操作：就是在模板中定义的抽象操作，通常是模板方法需要调用的操作，是必须的操作
        5）钩子操作，在模板中定义，并提供默认实现的操作，这些方法通常被视为可扩展的点，但不是必须的，子类可以有选择的覆盖这些方法
        6）Factory Method：在模板方法中，入股哦需要得到某些对象实例的话，可以考虑通过工厂方法模式来获取，把具体的构建对象的实现
        延迟到子类中去
本质：固定算法骨架
何时选用；
    1：需要固定定义算法骨架，实现一个算法的不变部分，并把可变的行为留给子类来实现的情况
    2：各个子类中具有公共行为，应该抽取出来，集中在一个公共类中去实现，从而避免代码重复
    3：需要控制子类扩展的情况，模板方法模式会在特定的点来调用子类的方法，这样只允许在这些点进行扩展



工厂方法模式
定义：
    定义一个用于创建对象的接口，让子类决定实例化哪一个类，Factory Method使一个类的实例化延迟到其子类
知识要点：
    1：工厂方法的主要功能是让父类不知道具体实现的情况下，完成自身的功能调用，而具体的实验延迟到子类来实现
    2：工厂方法的实现中，通常父类胡是一个抽象基类，里面包含创建所需对象的抽象方法，这些抽象方法就是工厂方法
    3：子类在实现抽象方法的时候，通常并不是真的由子类来实现具体功能，而是在子类的方法里面做选择，选择具体产品实现对象，有些类似
    于简单工厂的功能，也就是“选择实现”
    4：可以在抽象方法里面传递参数，以便在子类实现的时候根据参数进行选择，创建需要的对象实例
    5：还有一个重要的问题就是，谁在使用工厂方法创建的对象？通常情况下工厂方法创建的对象应该是在父类里面的其他方法来使用的
    一般不直接把这个对象返回给客户端
    6：工厂方法模式和IOC、DI从思想层面上是相似的，都是“主动变被动”，进行了“主从换位”，从而获得了更灵活的程序结构
    7：所谓参数化工厂方法指的就是：通过给工厂方法传递参数，让工厂方法根据参数的不同来创建不同的对象
    8：要理解平行的类层次结构，工厂方法模式可以用于连接平行的类层次
本质：延迟到子类来选择实现
