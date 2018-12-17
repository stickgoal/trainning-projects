package maiz.me.toyapplication.common;

/**
 * 事件类
 */
public class SwitchToFragementEvent {
    private int index;

    public SwitchToFragementEvent() {
    }

    public SwitchToFragementEvent(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
