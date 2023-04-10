import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class intInputDialog {
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
     *
     * @param frame       JFrame of the main program
     * @param windowTitle window's title
     * @param label       text that will be displayed inside of window
     * @param method      "editPrice", "add", "sell" - parameters that determine which method will be performed on OK click
     * @param productName name of the product that method interacts with
     */
    intInputDialog(JFrame frame, String windowTitle, String label, String method, String productName) {
        spinnerModel = new SpinnerNumberModel(1, 1, 10000000, 1);
        cheateDialog(frame, windowTitle, label, method, productName);
    }

    intInputDialog(JFrame frame, String windowTitle, String label, int max, String method, String productName) {
        spinnerModel = new SpinnerNumberModel(1, 1, max, 1);
        cheateDialog(frame, windowTitle, label, method, productName);
    }

    private void cheateDialog(JFrame frame, String windowTitle, String label, String method, String productName) {
        JDialog dialog = new JDialog(frame, true);
        dialog.setLayout(new FlowLayout());
        JLabel displayText = new JLabel(label);
        displayText.setPreferredSize(new Dimension(width - 50, 30));
        JSpinner intInput = new JSpinner(spinnerModel);
        intInput.setPreferredSize(new Dimension(width - 50, 25));
        ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = (int) intInput.getValue();
                int confirm = JOptionPane.showConfirmDialog(frame, "Ви впевнені?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    String result = "";
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
                    JOptionPane.showMessageDialog(frame, result, "Операція пройшла успішно!", JOptionPane.PLAIN_MESSAGE);

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

        dialog.setSize(width, height);
        dialog.setLocation(frame.getX() + frame.getWidth() / 2 - width / 2, frame.getY() + frame.getHeight() / 2 - height / 2);
        dialog.setTitle(windowTitle);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
