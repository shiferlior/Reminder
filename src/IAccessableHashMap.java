import java.util.Map;

public interface IAccessableHashMap {
    void saveHashMap(Map<ReminderDate,String> reminders);
    Map<ReminderDate,String> reloadHashMap();
}
