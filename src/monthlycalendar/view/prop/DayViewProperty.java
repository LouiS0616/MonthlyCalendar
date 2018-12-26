package monthlycalendar.view.prop;

import java.awt.Color;


public class DayViewProperty {
    public final Color foreGround;
    public final Color backGround;
    public final String tag;

    DayViewProperty(Color foreGround, Color backGround, String tag) {
        this.foreGround = foreGround;
        this.backGround = backGround;
        this.tag        = tag;
    }
}
