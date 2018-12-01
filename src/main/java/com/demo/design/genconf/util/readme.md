解释器模式（ReadXmlExpression）
定义：
    给定一个语言，定义它的文法的一种表示，病定义一个解释器，这个解释器使用该表示来解释语言中的句子
知识要点：
    1：解释器模式使用解释器对象来表示和处理相应的语法规则，一般一个解释器处理一条语法规则
    2：解释器模式没有定义谁来构建抽象语法树，把这个工作交个了客户端处理
    3：使用解释器模式的时候，应该先定义好相应的语法规则，并根据规则制定解释器的语法树，然后客户端在调用解释器进行解释
    操作的时候，需要自行构建符合语法规要求的语法树，而解释器模式只是负责解释并执行
    4：从实质上看，解释器模式的思路仍然是分离、封装、简化，跟很多模式是一样的
解决问题的思路：
    要解决通用解析xml的问题，第一步，需要先设计一个简单的表达式语言，在客户端条用解析程序的时候，传入用这个表达式语言
    描述的一个表达式，然后把这个表达式通过解析器的解析，形成一个抽象的语法树；第二步，解析完成后，自动调用解释器来解释
    抽象语法树，并执行每个节点所对应的功能，从而完成通用的xml解析；
约定简单的语法规则如下：
    1：获取单个元素的值，从根元素开始，一直到想要获取值得元素，元素中间用“/”分隔，根元素前不加“/”，比如表达式“root/a/b/c”
    就表示获取根元素下、a元素下、b元素下的c元素的值；
    2：获取单个元素的属性的值，要获取值得属性一定是表达式的最后一个元素的属性，在最后一个元素后面添加“.”然后再加上属性的名称，
    比如表达式“root/a/b/c.name”就表示获取根元素下、a元素下、b元素下、c元素的name属性的值
    3：获取相同元素名称的值，当然是多个，要获取值得元素一定是表达式的最后一个元素，在最后一个元素后面添加“$”，比如表达式
    “root/a/b/d$”就表示获取根元素下、a元素下、b元素下的多个d元素的值得集合
    4：获取相同元素名称的属性的值，当然也是多个，要获取属性值得元素一定是表达式的最后一个元素，在最后一个元素后面添加“$”，
    然后再后面添加“.”然后再加上属性的名称，再属性名称后面也添加“$”。比如表达式“root/a/b/d$.id$”就表示获取根元素下、
    a元素下、b元素下的多个d元素的id属性的值的集合
    5：如果要获取某个需要区分的元素下面的值，那么对于判断使用这样的语法：[属性名=值]，属性名和值都不需要加引号，比如：要想获取
    id=“e1”的e元素下面的f元素的值，就是用这样的表达式：“root/a/e$[id=e1]/f”


组合模式（Parser）
定义：
    将对象组合成树形结构一表示“部分-整体”的层次结构，组合模式使得用户对单个对象和组合对象的使用具有一致性
知识要点：
    1：组合模式的目的是：让客户端不在区分操作的是组合对象还是叶子对象，而是以一个统一的方式来操作
    2：组合模式设计的关键之处，是设计一个抽象的组件类，让他可以代表组合对象和叶子对象
    3：组合模式通常会构成对象树，理论上可以包含无数个层次
    4：通常需要以对象递归的方式来访问对象树中的对象
    5：使用组合模式的时候，应该更关注于透明性而非安全性
    6：需要的时候，要老驴父子组件的引用关系，并避免环状引用，当然有意这样设计的除外


备忘录模式
定义：
    在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样以后就可将该对象回复到原先保存的状态
知识要点：
    1：备忘录模式的功能，首先是在不破坏封装的前提下，捕获一个对象的内部状态，但是要记住，备忘的目的是为了在后面需要的时候进行
    恢复，把对象的状态恢复到备忘录所保存的状态，这才是备忘录正真的目的；
    2：备忘录对象通常都会实现窄接口，一般不考虑宽接口，而且通常会实现成为原发器对象的一个私有静态内部类
    3：管理者对象可要可不要，管理者对象只是存放备忘录对象，并不是操作备忘录对象
    4：使用备忘录模式，潜在的代价就是耗费内存
    5：备忘录模式通常会结合原型模式来使用
    6：备忘录的存储方式可以多样，除了内存，还可以离线存储
本质：保存和恢复内部状态
何时选用：
    1：如果必须保存一个对象在某一个时刻的全部或者部分状态，这样在以后需要的时候，可以把该对象恢复到先前的状态，可以使用备忘录模式
    使用备忘录对象来封装和保存需要保存的内部状态，然后把备忘录对象保存到管理者对象里面，在需要的时候，再从管理者对象里面获取
    备忘录对象，来恢复对象的状态
    2：如果需要保存一个对象的内部状态，但是如果用接口来让其他对象直接得到这些需要保存的状态，将会暴露对象的实现细节并破坏对象
    的封装性，可以使用备忘录模式，把备忘录对象实现成为原发器对象的内部类，而且还是私有的，从而保证只有原发器对象才能访问
    备忘录对象，这样既保持了需要保存的状态，又不会暴露原发器对象的内部实现细节
解决问题的思路：
    每次在拿到一个字符串之后，先跟已有的记录进行比较，找出最大的已经解析过的字符串，那么这个长度的字符串是不需要在解析的
    只需要把后面没有解析的字符串拿出来进行解析，然后把解析的对象添加到已经解析好的这个对象后头就可以了
    这就需要每次在解析的时候，每次解析完成，就向备忘录里面添加一条记录，而每次进入的时候，根据最长能匹配的字符串，从备忘录
    里面获取到相应的对象，这就不用解析了
    当然，备忘录对象还是需要实现一个窄接口，也需要实现一个备忘录管理者，这里跟标准的备忘录模式有一个小小的区别，这里是在解析器
    对象里面直接去备忘录管理者里面获取备忘录对象，而不是从客户端传递，这是为了让客户端操作更方便，其实由客户端传递也是可以的


原型模式
定义：
    用原型实例来制定创建对象的种类，并通过拷贝这些原型创建对象
知识要点
    1：原型模式的主要功能就是，通过克隆来创建对象的实例，一般来讲，新创建出来的实例的数据是和原型实例一样的
    2：原型模式的克隆方法和new操作最明显的不同就在于：new一个对象实例，一般属性是没有值得，或者是室友默认值得，如果是克隆
    得到的一个实例，通常属性是有值的，属性的值就是原型对象实例在克隆的时候，原型对象实例的属性的值
    3：java中已经有了clone方法的基本实现，可以直接使用，但是只是实现了浅度克隆，如果是深度克隆，还是需要额外的代码实现
    4：要考虑浅度克隆和深度克隆的问题，要想深度克隆成功，必须要整个克隆所涉及的对象都要正确实现克隆方法，如果其中一个没有
    正确实现克隆，那么就会导致克隆失败
    5：原型模式的重心在创建新的对象实例，克隆只是手段
本质：克隆生成对象