package monthlycalendar.view.calendarpanel;

import monthlycalendar.view.Config;
import monthlycalendar.model.ImmutableDate;
import monthlycalendar.view.prop.DayViewProperty;
import monthlycalendar.view.prop.decider.DayViewPropertyDecider;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;


class DayCell extends JPanel {
    // 日付の色付けや、表示する情報を決定するオブジェクト
    private static final DayViewPropertyDecider viewPropertyDecider_;
    static {
        viewPropertyDecider_ = DayViewPropertyDecider.create();
    }

    //
    DayCell(ImmutableDate date) {
        this.viewProp = viewPropertyDecider_.getViewProperty(date);
        this.date_ = date;

        this.setLayout(new BorderLayout());
        this.add(
            new DayLabel(), BorderLayout.CENTER
        );
        this.add(
            new DayTagLabel(), BorderLayout.SOUTH
        );

        setBackground(viewProp.backGround);
    }

    private final DayViewProperty viewProp;
    private final ImmutableDate date_;


    //
    //
    private class DayLabel extends JLabel {
        {
            this.setFont(Config.getFont("DayLabel"));
        }

        DayLabel() {
            super(date_.dayStr(), JLabel.CENTER);

            setForeground(viewProp.foreGround);
        }
    }
    private class DayTagLabel extends JLabel {
        {
            this.setFont(Config.getFont("DayTagLabel"));
        }

        DayTagLabel() {
            super(viewProp.tag.length() == 0 ? " " : viewProp.tag, JLabel.CENTER);
            this.setForeground(viewProp.foreGround);
        }
    }
}
