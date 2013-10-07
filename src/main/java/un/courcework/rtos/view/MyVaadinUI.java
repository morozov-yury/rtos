package un.courcework.rtos.view;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.view.component.layout.ContentLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
//@Theme("rtosTheme")
//@Push
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        GridLayout mainLayout = new GridLayout(3, 1);
        mainLayout.setSizeFull();

        mainLayout.addComponent(new VerticalLayout(), 0, 0);
        mainLayout.addComponent(new ContentLayout(), 1, 0);
        mainLayout.addComponent(new VerticalLayout(), 2, 0);

        mainLayout.setColumnExpandRatio(0, 1);
        mainLayout.setColumnExpandRatio(1, 0);
        mainLayout.setColumnExpandRatio(2, 1);


        setContent(mainLayout);

//        getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
//        setPollInterval(1000);
    }

}
