import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReminderController implements IReminderController{

        private IReminderModel _reminderModel;
        private IReminderView _reminderView;

        public ReminderController(IReminderModel reminderModel, IReminderView reminderView){
            _reminderModel = reminderModel;
            _reminderView = reminderView;

            _reminderView.addShowReminderListener(new showReminderListener());
            _reminderView.addSubmitReminderListener(new submitListener());
        }

        private class submitListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                _reminderModel.submitReminder(_reminderView.getDate(), _reminderView.getReminder());

            }
        }

        private class showReminderListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                _reminderView.showText(_reminderModel.getReminder(_reminderView.getDate()));
            }
        }

}
