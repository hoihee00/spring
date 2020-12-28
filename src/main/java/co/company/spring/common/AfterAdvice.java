package co.company.spring.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component	// bean 등록
@Aspect		// <aop:aspect>
public class AfterAdvice {
	@Pointcut("execution(* co.company.spring..*Impl.*(..))")
	public void getpointcut() {}
	
	@AfterReturning(pointcut =  "getpointcut()", returning = "returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		System.out.println("[공통로그] after " + method + "\n" + returnObj);
	}
}
