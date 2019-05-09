import java.awt.event.ActionListener;

public interface IReminderView {
    void showText(String text);
    int showMessage(String title, String text);
    ReminderDate getDate();
    void addSubmitReminderListener(ActionListener listener);
    void addShowReminderListener(ActionListener listener);
    void errorMessage(String text);
    String getReminder();
}
