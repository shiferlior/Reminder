import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionListener;
import java.time.Year;



public class ReminderView extends JFrame implements IReminderView{

    private int WIDTH = 300;
    private int HEIGHT = 200;

    private JComboBox _dayCb;
    private JComboBox _monthCb;
    private JComboBox _yearCb;

    private JButton _submitReminderBtn;
    private JButton _showReminderBtn;

    private JTextArea _reminderContentTf;


    public ReminderView(){
        super("Reminder");
        init();
        orderFrame();

        setSize(WIDTH,HEIGHT);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init(){
        initDays();
        initMonths();
        initYears();

        _submitReminderBtn = new JButton("Submit");
        _showReminderBtn = new JButton("Show reminder");
        _reminderContentTf = new JTextArea("",3,1);
    }

    private void initDays(){
        String[] days = new String[31];
        for(int i = 0; i < days.length; i++)
            days[i] = Integer.toString(i+1);
        _dayCb = new JComboBox(days);
    }

    private void initMonths(){
        String[] months = new String[12];
        for(int i = 0; i < months.length; i++)
            months[i] = Integer.toString(i+1);
        _monthCb = new JComboBox(months);
    }

    private void initYears(){
        int yearNumber = Year.now().getValue();
        String[] years = new String[yearNumber];
        for (int i =0; i<years.length; i++)
            years[i] = Integer.toString(yearNumber - i);
        _yearCb = new JComboBox(years);
    }

    private void orderFrame() {
        JPanel datePnl = buildDatePanel();
        JPanel buttonsPnl = buildButtonPanel();

        add(datePnl, BorderLayout.NORTH);
        add(buttonsPnl,BorderLayout.CENTER);
        add(_reminderContentTf, BorderLayout.SOUTH);
    }

    private JPanel buildDatePanel(){
        JPanel datePnl = new JPanel();
        datePnl.add(_dayCb);
        datePnl.add(_monthCb);
        datePnl.add(_yearCb);
        return datePnl;
    }

    private JPanel buildButtonPanel(){
        JPanel buttonsPnl = new JPanel();
        buttonsPnl.add(_submitReminderBtn);
        buttonsPnl.add(_showReminderBtn);
        return buttonsPnl;
    }

    public void showText(String text) {
        _reminderContentTf.setText(text);
        repaint();
    }

    public int showMessage(String title,String text) {
        return JOptionPane.showConfirmDialog(null, text, title, JOptionPane.YES_NO_OPTION);
    }

    public void errorMessage(String text){
        JOptionPane.showConfirmDialog(null, text, "Error!", JOptionPane.CLOSED_OPTION);
        System.exit(0);
    }

    public ReminderDate getDate(){
        return new ReminderDate(parseDatePart(_yearCb),parseDatePart(_monthCb), parseDatePart(_dayCb));
    }

    private int parseDatePart(JComboBox cb) {
        return Integer.parseInt(cb.getSelectedItem().toString());
    }


    public void addSubmitReminderListener(ActionListener listener) {
        _submitReminderBtn.addActionListener(listener);
    }

    public void addShowReminderListener(ActionListener listener) {
        _showReminderBtn.addActionListener(listener);
    }

    @Override
    public String getReminder() {
        return _reminderContentTf.getText();
    }
}
