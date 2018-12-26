package monthlycalendar.view;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;


public final class Config {
    //
    // Fonts
    private static final Map<String, Font> font = new HashMap<>();
    static {
        font.put("Title",       new Font("Meiryo",  Font.BOLD,  40));

        font.put("WeekLabel",   new Font("Courier", Font.PLAIN, 30));
        font.put("DayLabel",    new Font("Courier", Font.BOLD,  30));
        font.put("DayTagLabel", new Font("Meiryo",  Font.PLAIN, 15));
    }

    public static Font getFont(String name) {
        if(font.containsKey(name)) {
            return font.get(name);
        }

        throw new FontNotFoundException(name);
    }
    private static class FontNotFoundException extends RuntimeException {
        FontNotFoundException(String fontName) {
            super(
                String.format("Font tagged '%s' is not found.", fontName)
            );
        }
    }
}
