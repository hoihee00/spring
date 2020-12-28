package co.company.spring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component	// bean 등록
@Aspect		// <aop:aspect>
public class LogAdvice {
	@Pointcut("execution(* co.company.spring..*Impl.*(..))")
	public void allpointcut() {}
	
	@Before("allpointcut()")
	public void printlog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] param = jp.getArgs();
		System.out.println("[공통로그] before " + method + "\n" + ((param != null && param.length>0)? param[0] : ""));
	}
}
