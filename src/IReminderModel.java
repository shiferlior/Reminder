public interface IReminderModel {
    void submitReminder(ReminderDate rd,String text);
    String getReminder(ReminderDate rd);
}
