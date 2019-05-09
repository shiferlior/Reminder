import java.util.Map;

public class ReminderModel implements IReminderModel {

    private IAccessableHashMap _dataAccess;
    private Map<ReminderDate,String> _reminders;

    public ReminderModel(IAccessableHashMap dataAccess) {
        _dataAccess = dataAccess;
    }

    public void submitReminder(ReminderDate rd,String text) {
        if(_reminders == null)
            _reminders = _dataAccess.reloadHashMap();
        _reminders.put(rd,text);
        _dataAccess.saveHashMap(_reminders);
    }


    public String getReminder(ReminderDate rd) {
        _reminders = _dataAccess.reloadHashMap();
        if(_reminders.get(rd) == null)
            return "";
        else 
            return _reminders.get(rd);
    }
}
