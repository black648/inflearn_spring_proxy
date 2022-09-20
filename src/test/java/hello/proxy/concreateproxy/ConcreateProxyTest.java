package hello.proxy.concreateproxy;

import hello.proxy.concreateproxy.code.ConcreateClient;
import hello.proxy.concreateproxy.code.ConcreateLogic;
import hello.proxy.concreateproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreateProxyTest {

//    @Test
//    void noProxt() {
//        ConcreateLogic concreateLogic = new ConcreateLogic();
//
//    }

    @Test
    void addProxy() {
        ConcreateLogic concreateLogic = new ConcreateLogic();
        TimeProxy timeProxy = new TimeProxy(concreateLogic);
        ConcreateClient client = new ConcreateClient(timeProxy);
        client.execute();
    }

}
