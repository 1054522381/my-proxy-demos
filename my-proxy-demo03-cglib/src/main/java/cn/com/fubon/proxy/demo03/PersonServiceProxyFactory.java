package cn.com.fubon.proxy.demo03;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import cn.com.fubon.proxy.demo03.service.impl.PersonServiceImpl;

public class PersonServiceProxyFactory {
	private Object object;
	public Object createPersonServiceProxy(final Object object){
		this.object = object;
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonServiceImpl.class);
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object proxy, Method method, Object[] args,
					MethodProxy methodProxy) throws Throwable {
				// 整个方法可以看做一个环绕通知
				System.out.println("before");
				// ... advice() --> AOP前置通知
				Object result = null;
				PersonServiceImpl target = (PersonServiceImpl)object;
				if(StringUtils.isNotBlank(target.getUsername())){
					
					try {
						// ... 通知,就是方法主要做的事情
						result = method.invoke(object, args);
					} catch (RuntimeException e) {
						// ... exceptionadvice() --> 例外通知
					} finally{
						// ... finallyadvice() --> 最终通知
					}
				}
				System.out.println("after");
				// ... afteradvice() --> 后置通知
				return result;
			}
		});
		return enhancer.create();
		
	}
	
}