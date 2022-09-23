package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeAdvice 실행");
        long startTime = System.currentTimeMillis();
        //동적 메서드 및 파라미터 적용
        Object result = invocation.proceed();

        long entTime = System.currentTimeMillis();
        log.info("TimeAdvice 종료 resultTime={}", entTime - startTime);
        return result;
    }
}
