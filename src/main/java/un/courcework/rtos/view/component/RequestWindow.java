package un.courcework.rtos.view.component;

import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.view.RtosUI;

public abstract  class RequestWindow extends Window {

    private static Logger log = LoggerFactory.getLogger(RequestWindow.class);

    private static final long serialVersionUID = 929375638437463231L;

    public RequestWindow(String message) {
        this.setModal(false);
        this.center();
        this.setClosable(false);
        this.setResizable(false);
        this.setVisible(true);
        setCaption("Превышение времени ожидания");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.addComponent(new Label(message));

        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Button yesButton = new Button("Пропустить",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        skip();
                        RtosUI.getCurrent().removeWindow(RequestWindow.this);
                    }
                });

        Button noButton = new Button("Снять",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        remove();
                        RtosUI.getCurrent().removeWindow(RequestWindow.this);
                    }
                });
        buttonsLayout.addComponent(yesButton);
        buttonsLayout.addComponent(noButton);

        mainLayout.addComponent(buttonsLayout);
        this.setContent(mainLayout);
    }

    public abstract void skip();

    public abstract void remove();

}
