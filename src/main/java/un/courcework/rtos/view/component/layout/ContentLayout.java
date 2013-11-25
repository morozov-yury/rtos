package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.GridLayout;

public class  ContentLayout extends GridLayout {

    private LeftPanel leftPanel;

    private RightPanel rightPanel;

    public ContentLayout() {
        super(2, 1);

        addStyleName("content-layout");
        setMargin(true);
        setWidth(Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
        setSpacing(true);

        this.leftPanel =  new LeftPanel();
        this.rightPanel = new RightPanel(this.leftPanel);
        addComponent(leftPanel, 0, 0);
        addComponent(rightPanel, 1, 0);

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                setWidth(Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
            }
        });
    }

}

