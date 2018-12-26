package monthlycalendar.view.calendarpanel;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;

public class CalendarPanel extends JPanel {
    public CalendarPanel(JFrame owner) {
        this.owner = owner;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        updated();

        setPreferredSize(
            new Dimension(700, 600)
        );
    }
    private void updated() {
        removeAll();

        addCenter(new TitleLabel(calendar));
        addCenter(new DayPanel(calendar));

        owner.pack();
        owner.repaint();
    }
    private void addCenter(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(component);
    }

    public void showPrevYear() {
        calendar.add(Calendar.YEAR, -1);
        updated();
    }
    public void showNextYear() {
        calendar.add(Calendar.YEAR, 1);
        updated();
    }
    public void showCurrentMonth() {
        calendar = Calendar.getInstance();
        updated();
    }
    public void showPrevMonth() {
        calendar.add(Calendar.MONTH, -1);
        updated();
    }
    public void showNextMonth() {
        calendar.add(Calendar.MONTH, 1);
        updated();
    }

    private Calendar calendar = Calendar.getInstance();
    private final JFrame owner;
}