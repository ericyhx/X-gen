桥接模式
定义：将抽象部分与他的实现部分分离，是他们都可以独立的变化
知识要点：
   1：桥接是在被分离了的抽象部分和实现部分之间来搭桥，桥接在程序上就体现成了在抽象部分拥有实现部分的接口对象，维护桥接
   就是维护这个关系
   2：桥接模式的意图：是的抽象和实现可以独立变化，都可以分别扩充
   3：桥接模式可以实现运行时动态组合具体的真实实现，从而达到动态变化功能的目的
   4：桥接模式适用于两个维度的变化，而继承使用与一个维度的变化
   5：使用桥接模式的时候，要注意谁来创建Implementor的对象，并把它设置到抽象部分的对象里面去
   6：从某个角度来讲，桥接模式就是对“面向抽象编程”这个设计原则的扩展
   7：桥接模式是可以连续组合使用的，一个桥接模式的实现部分，可以作为下一个桥接模式的抽象部分
本质：分离抽象和实现
何时选用桥接模式：
   1：如果你不希望在抽象和实现部分采用固定的榜单关系，可以采用桥接模式，来把抽象和实现部分分开，然后再程序运行期间来动态的
   设置抽象部分需要用到的具体的实现，还可以动态切换具体的实现
   2：入股哦出现抽象部分和实现部分都应该可以扩展的情况，可以采用桥接模式，让抽象部分和实现部分可以独立的变化，从而可以灵活
   的进行单独扩展，而不是搅在一起，扩展一边会影响到另一边
   3：如果希望实现部分的修改，不会对客户产生影响，可以采用桥接模式，客户是面向抽象的接口在运行，实现部分的修改，可以独立于抽象部分，
   也就不会对客户产生影响了，也可以说对客户是透明的
   4：如果采用继承的实现方案，会导致产生很多子类，对于这种情况，可以考虑采用桥接模式，分析功能变化的原因，看看是否能分离
   成不同的维度，然后通过桥接模式来分离它们，从而减少子类的数目
使用桥接模式来解决问题的思路：
        可以吧获取配置数据这边，设计成为桥接模式的实现部分，而数据模型这边设计成为桥接模式的抽象部分，而且数据模型这边确实需要
        使用具体实现部分来获取数据，兼职就是标准的桥接模式的应用


生成器模式
定义：将一个复杂对象的构建与他的表示分离，是的同样的构建过程可以创建不同的表示
知识要点：
    1：生成器模式重载解决一步一步构建复杂对象的问题，更为重要的是，这个构建的过程是统一的，固定不变的，变化的部分放到生成器部分了；
    2：生成器模式的重心在于分离构建发算法和具体的构造实现，从而使得构建算法可以重用，具体的构造实现可以很方便的扩展和切换，从而
    可以灵活的组合来构造出不同的产品对象；
    3：生成器模式一般存在两个部分，一个部分是不见构造和产品装配，另一个部分是整体构建的算法；
    4：生成器模式有一种退化的情况，就是让客户端和Director融合起来，让客户端直接去操作Builder，就好像是知道者自己想要给自己构建产品一样
    5：生成器模式里面，指导者承担的是整体构建算法部分，是相对不变的部分
    6：指导者分离出去的变化部分，就到了生成器那边，每个生成器对象都可以有两部分功能，一个是创建不见对象，一个是组装部件
    7：在标准的生成器模式里面，在Builder实现里面会提供一个返回装配好的产品的方法，在Builder接口上是没有的
    8：在标准的生成器模式里面，一般是不需要对产品定义抽象接口
本质：
    分离整体构建算法和部件构造


