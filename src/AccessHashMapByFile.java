import javax.swing.*;
import java.io.*;
import java.util.*;

public class AccessHashMapByFile implements IAccessableHashMap {
    private String _filePath;
    private IReminderView _reminderView;


    public AccessHashMapByFile(IReminderView reminderView){
        _reminderView = reminderView;
        getPath();
    }

    public void saveHashMap(Map<ReminderDate,String> reminders) {
        try {
            FileOutputStream fos = new FileOutputStream(_filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(reminders);
            oos.close();
            fos.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public Map<ReminderDate,String> reloadHashMap() {
        HashMap<ReminderDate, String> map = null;
        try {
            FileInputStream fis = new FileInputStream(_filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
            return map;
        }
        catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return map;
        }
        return map;
    }

    private void getPath(){
        if(_reminderView.showMessage("Choose","Do you want to load a file?") == 0)
            fileChooser();
        else {
            createFile();
        }
    }

    private void createFile() {
        String fileName = JOptionPane.showInputDialog(null, "Write the file name: \nThe path is: "
                + System.getProperty("user.home") + "/", "Init", JOptionPane.PLAIN_MESSAGE);
        _filePath = System.getProperty("user.home") + "/" + fileName + ".reminderData";
        saveHashMap(new HashMap<ReminderDate,String>());
    }

    private void fileChooser() {
        SwingUtilities.invokeLater(() -> {
            JFileChooser getFile = new JFileChooser();
            getFile.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = getFile.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                _filePath = getFile.getSelectedFile().getAbsolutePath();
                if (_filePath.endsWith(".reminderData"))
                    reloadHashMap();
                else
                    _reminderView.errorMessage("You must choose the file who ends with: reminderData");
            } else
                _reminderView.errorMessage("You must choose the file who ends with: reminderData");
        });
    }
}
