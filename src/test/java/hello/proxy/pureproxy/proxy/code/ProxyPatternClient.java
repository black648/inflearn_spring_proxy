package hello.proxy.pureproxy.code;

public class ProxyPatternClient {
    Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        subject.operation();
    }
}
