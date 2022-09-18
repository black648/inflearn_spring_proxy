package hello.proxy.app.v3_componentScan;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        //저장로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!!");
        }
        sleep(10);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
