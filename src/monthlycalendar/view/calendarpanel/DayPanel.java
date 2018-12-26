package monthlycalendar.view.calendarpanel;

import monthlycalendar.model.ImmutableDate;
import monthlycalendar.view.Config;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.Calendar;

class DayPanel extends JPanel {
    DayPanel(final Calendar calendar) {
        setLayout(new GridLayout(rows, cols));

        Calendar work = (Calendar)calendar.clone();
        work.set(Calendar.DATE, 1);

        int numOfFilledCell = 0;
        // Fill weeks.
        for(String week: weeks) {
            JLabel label = new JLabel(week, JLabel.CENTER);
            label.setFont(Config.getFont("WeekLabel"));
            add(label);

            ++numOfFilledCell;
        }

        // Fill fore-end blank.
        for(int col = 1; col < work.get(Calendar.DAY_OF_WEEK); ++col) {
            this.add(new JPanel());

            ++numOfFilledCell;
        }

        // Fill days.
        do {
            ImmutableDate date = new ImmutableDate(work);
            this.add(new DayCell(date));
            work.add(Calendar.DATE, 1);

            ++numOfFilledCell;

        } while(work.get(Calendar.DATE) != 1);

        // Fill back-end blank.
        int numOfCell = rows * cols;
        for(; numOfFilledCell < numOfCell; ++numOfFilledCell) {
            this.add(new JPanel());
        }
    }

    //
    private static final int rows = 7;
    private static final int cols = 7;

    private static final String[] weeks = {
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };
}
