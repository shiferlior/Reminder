public class Main {
   
    public static void main(String[] args) {

        IReminderView       rView       =   new ReminderView();
        IAccessableHashMap  stream      =   new AccessHashMapByFile(rView);
        IReminderModel      rModel      =   new ReminderModel(stream);
        IReminderController rController =   new ReminderController(rModel,rView);
    }
}
