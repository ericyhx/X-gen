
策略模式
问题：
    现在字符串也可以通过builder模式来拼接了，看起来一切很棒，又有一个新的问题出现了，那就是配置的数据里面有冬天的内容，需要动态
    的解析
    由于在配置ExtendConf的值时，会发现经常需要在某一个配置里面，需要使用已经配过的一个值，这个值可能直接来之本模块其他的ExtendConf的值，
    也可能来自GenConf中配置的数据，因而设计了表达动态引用的语法，大致如下：
    1：直接引用本模块配置的其他ExtendConf的数据，有两种方式，如果只是简单引用某一个配置的值，那么久直接写成${引用的ExtendConf的id}，
    2：直接引用本模块配置的其他ExtendConf的数据，还有两种方式，x-gen支持Beanshell的脚本，并自动传入gm代表GenConfModel，
    mapEcms代表当前模块配置的mapExtends，那么久可以写成${mapEcms.get("引用的ExtendConf的id")}；
定义：定义一些列的算法，把他们一个个封装起来，并且是他们可以相互替换，本模式使得算法可独立于使用它的客户而变化
知识要点：
    1：策略模式的功能是把具体的算法实现，从具体的业务出来里面独立出来，把他们实现成为单独的算法累，从而形成一些的算法，并让这些
    算法可以相互替换；
    2：策略模式的重新不是如何来实现算法，而是如何组织，调用这些算法，从而让程序结构更灵活、具有更好的维护下和扩展性
    3：策略模式一个很大的特点就是哥哥策略算法的平等性，所有逇策略算法在实现上也是相互独立的，相互之间是没有依赖的，所以可以这样描述
    这系列策略算法：策略算法是相同行为的不同实现
    4：使用策略模式的时候，要注意谁来选择策略，可以是客户端，也可以在上下文里面，而跟策略模式类似的状态模式，一般是不会让客户端来选择状态的
    ，状态是内部行为，这是一个很重要的区别
    5：策略模式在每一个时刻只能使用一个具体的策略实现对象，虽然可以动态的在不同的策略实现中切换，但是同时只能使用一个
    6：上下文策略模式里面有特殊的地位，上下文使用具体的策略实现对象，反过来，策略实现对象也可以从上下文获取所需要的数据，甚至在某些情况
    下，策略实现对还可以回调上下的方法来实现一定的功能，这种使用场景下，上下文变相充当了多个策略算法实现的公共接口，在上下文定义
    的方法可以当做是所有或者是部分策略算法使用的公共功能
本质：分离算法，选择实现
何时选用：
    1：出现有许多相关的类，仅仅是行为有差别的情况，可以使用策略模式来使用多个行为中的一个来配置一个雷的方法，实现算法动态切换；
    2：出现同一个算法，有很多不同的实现的情况，可以使用策略模式来把这些“不同的实现”实现成为一个算法的类层次
    3:：需要封装算法中，与算法相关的数据的情况，可以使用策略模式来避免暴露这些跟算法相关的数据结构
    4：出现抽象一个定义了很多行为的类，并且是通过多个if-else语句来选择这些行为的情况，可以使用策略模式来代替这些条件语句