package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        //로직1
        log.info("Start");
        String result1 = target.callA();
        log.info("result={}", result1);

        //로직2
        log.info("Start");
        String result2 = target.callB();
        log.info("result={}", result2);
    }

    /**
     * Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello") : 클래스 메타정보를 획득한다. 참고로 내부 클래스는 구분을 위해 $ 를 사용한다.
     * classHello.getMethod("call") : 해당 클래스의 call 메서드 메타정보를 획득한다.
     * methodCallA.invoke(target) : 획득한 메서드 메타정보로 실제 인스턴스의 메서드를 호출한다. 여기서
     * methodCallA 는 Hello 클래스의 callA() 이라는 메서드 메타정보이다.
     * methodCallA.invoke(인스턴스) 를 호출하면서 인스턴스를 넘겨주면 해당 인스턴스의 callA() 메서드를 찾아서 실행한다. 여기서는 target 의 callA() 메서드를 호출한다.
     * 그런데 target.callA() 나 target.callB() 메서드를 직접 호출하면 되지 이렇게 메서드 정보를
     * 획득해서 메서드를 호출하면 어떤 효과가 있을까? 여기서 중요한 핵심은 클래스나 메서드 정보를 동적으로
     * 변경할 수 있다는 점이다.
     * 기존의 callA() , callB() 메서드를 직접 호출하는 부분이 Method 로 대체되었다. 덕분에 이제 공통
     * 로직을 만들 수 있게 되었다.
     */
    @Test
    void reflection1() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        Method methodA = classHello.getMethod("callA");
        Object result1 = methodA.invoke(target);
        log.info("result={}", result1);

        Method methodB = classHello.getMethod("callB");
        Object result2 = methodB.invoke(target);
        log.info("result={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        Method methodA = classHello.getMethod("callA");
        dynamicCall(methodA, target);

        Method methodB = classHello.getMethod("callB");
        dynamicCall(methodB, target);

    }

    /**
     * dynamicCall(Method method, Object target)
     *      공통 로직1, 공통 로직2를 한번에 처리할 수 있는 통합된 공통 처리 로직이다.
     *      Method method : 첫 번째 파라미터는 호출할 메서드 정보가 넘어온다. 이것이 핵심이다. 기존에는
     *      메서드 이름을 직접 호출했지만, 이제는 Method 라는 메타정보를 통해서 호출할 메서드 정보가
     *      동적으로 제공된다.
     *      Object target : 실제 실행할 인스턴스 정보가 넘어온다. 타입이 Object 라는 것은 어떠한
     *      인스턴스도 받을 수 있다는 뜻이다. 물론 method.invoke(target) 를 사용할 때 호출할 클래스와
     *      메서드 정보가 서로 다르면 예외가 발생한다.
     */
    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("Start");
        log.info("result={}", method.invoke(target));
    }


    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
