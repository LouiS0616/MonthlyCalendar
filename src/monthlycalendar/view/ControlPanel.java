package monthlycalendar.view;

import monthlycalendar.view.calendarpanel.CalendarPanel;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ControlPanel extends JPanel {
    ControlPanel(final CalendarPanel calendarPanel) {
        setLayout(new FlowLayout());

        {
            JButton button = new JButton("<<");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.showPrevYear();
                }
            });
            add(button);
        }
        {
            JButton button = new JButton("<");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.showPrevMonth();
                }
            });
            add(button);
        }
        {
            JButton button = new JButton("□");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.showCurrentMonth();
                }
            });
            add(button);
        }
        {
            JButton button = new JButton(">");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.showNextMonth();
                }
            });
            add(button);
        }
        {
            JButton button = new JButton(">>");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.showNextYear();
                }
            });
            add(button);
        }
    }
}