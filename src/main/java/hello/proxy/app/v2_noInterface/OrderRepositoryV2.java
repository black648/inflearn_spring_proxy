package hello.proxy.app.v2_noInterface;

public class OrderRepositoryV2 {
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
