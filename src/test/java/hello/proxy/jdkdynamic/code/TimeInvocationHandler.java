package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    //주입 대상
    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();
        //동적 메서드 및 파라미터 적용
        Object result = method.invoke(target, args);

        long entTime = System.currentTimeMillis();
        log.info("TimeProxt 종료 resultTime={}", entTime - startTime);
        return result;
    }
}
