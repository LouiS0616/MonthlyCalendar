package monthlycalendar;

import monthlycalendar.view.MainWindow;

import javax.swing.UIManager;
import java.util.TimeZone;


class Main {
    private Main() {
        assert TimeZone.getDefault().hasSameRules(
            TimeZone.getTimeZone("JST")
        );

        new MainWindow().setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        new Main();
    }
}
