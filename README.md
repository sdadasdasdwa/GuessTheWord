# GuessTheWord (ViewModel and ViewModelFactory)
record studying

what you will learn
------------
- 如何使用推荐的 Android `应用架构`。
- 如何在您的应用程序中使用 `Lifecycle`、 `ViewModel`和 `ViewModelFactory`类。
- 如何通过设备配置更改保留 UI 数据。
- 什么是`工厂方法`设计模式以及如何使用它。
- 如何`ViewModel`使用接口创建对象`ViewModelProvider.Factory`。

what you will do
------------
- 将 a 添加 `ViewModel`到应用程序，以保存应用程序的数据，以便数据在配置更改后仍然存在。
- 使用 `ViewModelFactory`工厂方法设计模式来实例化`ViewModel`带有构造函数参数的对象。

Introduction
------------
第一个玩家查看应用程序中的单词并依次表演每个单词，确保不要将单词显示给第二个玩家。第二个玩家试图猜测这个词。

