package monthlycalendar.view.calendarpanel;

import monthlycalendar.view.Config;

import javax.swing.JLabel;
import java.util.Calendar;

class TitleLabel extends JLabel {
    TitleLabel(final Calendar calendar) {
        super(
            String.format(
                "%4d年%2d月",
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1
            )
        );

        setFont(Config.getFont("Title"));
    }
}
