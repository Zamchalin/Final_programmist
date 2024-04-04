package View;
import Control.BasicControl;
import Control.Control;

public interface BasicView {
    public void start(BasicControl control, String text);

    public BasicControl getControl();
}