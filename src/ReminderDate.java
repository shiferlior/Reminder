import java.io.Serializable;

public class ReminderDate implements Serializable {
    private int _day;
    private int _month;
    private int _year;

    public ReminderDate(int year,int month,int day) {
        _day = day;
        _month = month;
        _year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ReminderDate) {
            ReminderDate rd = (ReminderDate) obj;
            return this.toString().equals(rd.toString());
        }
        else
            return super.equals(obj);
    }

    @Override
    public int hashCode(){
        return _day * 10000000 + _month * 10000 + _year;
    }

    @Override
    public String toString(){
        return _day + "/" + _month + "/" + _year;
    }
}
