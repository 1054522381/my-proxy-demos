@Configuration
@EnableAspectJAutoProxy 开启AOP功能
@Aspect 定义切面类

#疑问:
@Around定义的环绕通知退出时机是何时?
测试结果:
1.业务方法抛异常
环绕通知进入
前置通知:zhangsan
saved!
最终通知
例外通知:/ by zero
exception catched!

2.业务方法正常执行
环绕通知进入
前置通知:zhangsan
saved!
环绕通知退出
最终通知
后置通知:saved!


#AOP概念:

横切性关注点:需要被控制的方法,方法主要做什么事情等思考步骤都可以称为"横切性关注点".

Aspect(切面):指横切性关注点的抽象,它与类相似,只是两者的关注点不一样,类是对物体特征的抽象,而切面是横切性关注点的抽象.

joinpoint(连接点):指那些被拦截到的点.在spring中,这些点指的是方法,因为spring只支持方法类型的连接点,实际上joinpoint还可以是field或类构造器.

Pointcut(切入点):指我们要对那些joinpoint进行拦截的定义.

Advice(通知):指拦截到joinpoint之后所要做的事情.分为前置通知,后置通知,异常通知,最终通知,环绕通知.

Target(目标对象):代理的目标对象.

Weave(织入):指将aspects应用到target对象并导致proxy对象创建的过程.

Introduction(引入):在不修改类代码的前提下,Introduction可以在运行期为类动态地添加一些方法或Field.


