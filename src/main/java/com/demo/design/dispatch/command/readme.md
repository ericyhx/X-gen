命令模式
问题：
   按照上面的实现，现在在外观类里面，需要去调用真正的实现，因为分发调度模块本身是不负责具体实现的，那么现在的问题是，到底外观
   类要如何去调用具体的实现呢
   有朋友会说，直接嗲用具体是的对象不就可以了，好像听起来是这么一回事情，但仔细想想，如果调用的顺序比较复杂，或者是调用的过程
    本身并不是固定的呢，那么直接调用就会有问题了，另外，如果调用的程序不仅仅在本地，也有可能在远程呢？
用命令模式来解决
定义：
    将一个请求封装成为一个对象，从而使你可用不同的请求对客户进行参数化，对请求排队或记录请求日志，以及支持可撤销的操作
知识要点;
    1:命令模式的关键之处就是把请求封装成为对象，也就是命令对象，并定义了统一的执行操作的接口，这个命令对象可以被存储，转发
    ，记录，处理，撤销等，整个命令模式都是是围绕这个对象进行
    2：在命令模式中经常会有一个命令的组装中，用它来维护命令的“虚”实现和真是实现之间的关系
    3：命令模式的接受者可以是任意类，只要完成命令要求的功能即可
    4：可以有智能的命令，也就是不需要接受者，命令的实现类就正真实现了命令要求的功能
    5：命令模式实现了命令的发起方和真正实现对象之间的解耦
    6：命令的参数化配置的意思是：可以用不同的命令对象，去参数化配置客户的请求
    7：命令模式实现可撤销操作的常用方式，一种是补偿式，另一种是存储恢复式（备忘录模式）
    8：多个命令还可以组合在一起，形成宏命令，宏命令从本质上来说仍然是命令，运行宏命令，其实就是循环运行一个宏命令类的每一个命令
    9：命令模式可以退化成为类似于java回调的实现形式
本质：封装请求
何时选用：
    1：如果需要抽象出需要执行的动作，并参数化这些对象，可以选用命令模式，把这些需要执行的动作抽象成为命令，然后实现命令的参数化配置
    2：如果需要在不同的时刻指定、排列和执行请求，可以选用命令模式，把这些请求封装成为命令对象，然后实现把请求队列化
    3：如果需要支持取消操作，可以选用命令模式，铜鼓哦管理命令对象，能很容易的实现命令的恢复和重做的功能；
    4：如果选用偶支持当系统崩溃时，能把度子系统的操作功能重新执行一遍，可以选用命令模式，把这些操作功能的请求封装成为命令
    对象，然后实现日志命令，就可以在系统恢复回来后，通过日志获取命令列表，从而重新执行一遍功能
    5：在需要事物的系统中，可以选用命令模式，命令模式提供了对事物进行建模的方法，命令模式有一个别名就Transaction