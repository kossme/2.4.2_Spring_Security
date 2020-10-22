package web.Config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    //пустой класс, использующийся для резистрации модуля в спринг-контейнере

}