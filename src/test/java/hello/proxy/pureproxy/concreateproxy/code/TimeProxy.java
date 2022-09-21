package hello.proxy.concreateproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreateLogic{
    private ConcreateLogic concreateLogic;

    public TimeProxy(ConcreateLogic concreateLogic) {
        this.concreateLogic = concreateLogic;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();
        String result = concreateLogic.operation();
        long entTime = System.currentTimeMillis();

        log.info("TimeDecorator 종료 resultTime={}ms", entTime - startTime);

        return result;
    }
}

