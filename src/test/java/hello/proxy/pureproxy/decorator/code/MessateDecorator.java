package hello.proxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessateDecorator implements Component{

    private Component component;

    public MessateDecorator(Component component) {
        this.component = component;
    }


    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        String result = component.operation();
        String decoResult = "******" + result + "******";
        log.info("MessageDecorator 꾸미기 적용전 ={}, 적용 후={}", result, decoResult);
        return decoResult;
    }
}
