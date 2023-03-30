import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class intInputDialog {
    private int value;
    private SpinnerModel spinnerModel;
    private JButton ok;
    private final int width = 300;
    private final int height = 150;
    public int getValue() {
        return value;
    }

    public SpinnerModel getSpinnerModel() {
        return spinnerModel;
    }

    public JButton getOk() {
        return ok;
    }

    /**
     * Dialog window with JSpinner instead of JTextField
     * @param frame JFrame of the main program
     * @param windowTitle window's title
     * @param label text that will be displayed inside of window
     * @param method "editPrice", "add", "sell" - parameters that determine which method will be performed on OK click
     * @param productName name of the product that method interacts with
     */
    intInputDialog(JFrame frame, String windowTitle, String label, String method, String productName){
        spinnerModel = new SpinnerNumberModel(1, 1, 10000000, 1);
        cheateDialog(frame, windowTitle, label, method, productName);
    }
    intInputDialog(JFrame frame, String windowTitle, String label, int max, String method, String productName){
        spinnerModel = new SpinnerNumberModel(1, 1, max, 1);
        cheateDialog(frame, windowTitle, label, method, productName);
    }

    private void cheateDialog(JFrame frame, String windowTitle, String label, String method, String productName){
        JDialog dialog = new JDialog(frame, true);
        dialog.setLayout(new FlowLayout());
        JLabel displayText = new JLabel(label);
        displayText.setPreferredSize(new Dimension(width-50, 30));
        JSpinner intInput = new JSpinner(spinnerModel);
        intInput.setPreferredSize(new Dimension(width-50, 25));
        ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = (int) intInput.getValue();
                int confirm = JOptionPane.showConfirmDialog(frame, "Ви впевнені?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
                if (confirm==0) {
                    String result="";
                    switch (method) {
                        case "editPrice" -> {
                            result = Program.factory.editProduct(productName, value);
                        }
                        case "add" -> {
                            result = Program.factory.addMoreOfProduct(productName, value);
                        }
                        case "sell" -> {
                            result = Program.factory.soldProduct(productName, value);
                        }
                    }
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, result, "Операція пройшла успішно!",JOptionPane.PLAIN_MESSAGE);

                }

            }
        });
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        dialog.add(displayText);
        dialog.add(intInput);
        dialog.add(ok);
        dialog.add(cancel);

        dialog.setSize(width,height);
        dialog.setLocation(frame.getX()+frame.getWidth()/2-width/2, frame.getY()+frame.getHeight()/2-height/2);
        dialog.setTitle(windowTitle);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
class UI extends JFrame {
    JButton toMenu;
    JPanel mainPanel;
    JLabel mainLabel;
    GridBagConstraints gbc = new GridBagConstraints();
    final Color col1 = new Color(69, 150, 206);
    final  Color col2 = new Color(0, 0, 0);
    final Color col3 = new Color(51, 48, 47);
    final Color col4 = new Color(105, 61, 39);
    final Color white = new Color(255, 255, 255);
    final Color white1 = new Color(72, 67, 64, 255);
    final Font font1 = new Font("Trebuchet MS", Font.BOLD, 36);
    final Font font2 = new Font("Trebuchet MS", Font.PLAIN, 24);
    final Font font3 = new Font("Trebuchet MS", Font.PLAIN, 16);
    UI(){
        super("Склад");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocation(500, 100);
        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(col3);
        pane.setFont(font2);
    }
}
class MenuUI extends UI {
    MenuUI(){
        super();
    }

}
public class Program {
    static Factory factory;
    public static void main(String[] args) {
        factory = new Factory();
        System.out.println(factory);
        init();
    }
    private static void init(){

        UI menu = new MenuUI();
        menu.setVisible(true);
        new intInputDialog(menu, "Зміна ціни товару", "Введіть нову ціну товару pollen:", "editPrice", "pollen");
        new intInputDialog(menu, "Списання товару", "Скільки шт. pollen продали?",  Factory.findProduct("pollen").getQuantity(), "sell", "pollen");
        new intInputDialog(menu, "Додавання товару", "Скільки шт. pollen прибуло на склад?", "add", "pollen");
        System.out.println(factory);
    }
}
