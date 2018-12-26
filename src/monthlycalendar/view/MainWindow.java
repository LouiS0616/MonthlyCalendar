package monthlycalendar.view;

import monthlycalendar.view.calendarpanel.CalendarPanel;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Monthly Calendar");
        setLayout(new BorderLayout());

        CalendarPanel calendarPanel = new CalendarPanel(this);
            add(calendarPanel, BorderLayout.CENTER);
            add(new ControlPanel(calendarPanel), BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //
        // Move to center of screen.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize  = getSize();

        if(frameSize.height > screenSize.height) throw new RuntimeException("Frame is too Large");
        if(frameSize.width  > screenSize.width)  throw new RuntimeException("Frame is too Large");

        setLocation(
            (screenSize.width  - frameSize.width)  / 2,
            (screenSize.height - frameSize.height) / 2
        );
    }
}
