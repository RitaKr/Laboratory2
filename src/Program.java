import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class UI extends JFrame {
    Factory factory;
    JButton toMenu;
    JPanel mainPanel;
    JLabel mainLabel;
    GridBagConstraints gbc = new GridBagConstraints();
    static final Color col1 = new Color(69, 128, 206);
    static final  Color col2 = new Color(0, 0, 0);
    final Color col3 = new Color(51, 48, 47);
    final Color col4 = new Color(140, 73, 49);
    final Color white = new Color(255, 255, 255);
    final Color white1 = new Color(84, 79, 75, 255);
    final Font font1 = new Font("Trebuchet MS", Font.BOLD, 36);
    final Font font2 = new Font("Trebuchet MS", Font.PLAIN, 22);
    final Font font3 = new Font("Trebuchet MS", Font.PLAIN, 16);
    final Font font4 = new Font("Trebuchet MS", Font.PLAIN, 14);
    final int width = 800;
    final int height = 800;
    UI(Factory factory){
        super("Склад");
        this.factory = factory;


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        //this.setLocation(500, 100);
        setLocationRelativeTo(null);
        Container pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(col4);
        pane.setFont(font2);

        mainLabel = new JLabel();
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabel.setFont(font1);
        mainLabel.setPreferredSize(new Dimension(getWidth(), 70));
        mainLabel.setForeground(white);
        mainLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(mainLabel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setBackground(col3);
        mainPanel.setAlignmentY(CENTER_ALIGNMENT);
        mainPanel.setAlignmentX(CENTER_ALIGNMENT);

        //panel.setLayout(new FlowLayout());

        mainPanel.setLayout(new GridBagLayout());

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        //add(mainPanel, BorderLayout.CENTER);
        JScrollPane scroller = new JScrollPane(mainPanel);
        scroller.setBorder(null);
        add(scroller);

        //setting submit button
        toMenu = new JButton("До головного меню");
        toMenu.setSize(120, 80);
        toMenu.setBackground(col2);
        toMenu.setFont(font2);
        toMenu.setMargin(new Insets(10, 25, 10, 25));
        toMenu.setForeground(white);
        toMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toMenu.setBorderPainted(false);
        add(toMenu, BorderLayout.SOUTH);
        toMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuUI menuUI = new MenuUI(factory);
                menuUI.setVisible(true);

            }
        });


    }
    public void styleMenuButton(JButton button) {
        button.setBackground(col2);
        button.setForeground(col1);
        button.setFont(font3);
        button.setMargin(new Insets(10, 25, 10, 25));
        button.setPreferredSize(new Dimension(260, 40));
        button.setBorder(BorderFactory.createLineBorder(col2, 1, true));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //button.setBorderPainted(false);
    }
    public void styleItemButton(JButton button) {
        button.setBackground(col2);
        button.setFont(font2);
        button.setForeground(white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(20, 30, 20, 30));
        button.setBorderPainted(false);

        //button.setBorderPainted(false);
    }
    public void styleLabel(JLabel label) {
        label.setFont(font2);
        label.setForeground(white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public void styleLabel(JLabel label, int horizontalAlignment) {
        label.setFont(font2);
        label.setForeground(white);
        label.setHorizontalAlignment(horizontalAlignment);
    }
    public void styleLabel(JLabel label, Font font, Color color) {
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public void styleLabel(JLabel label, Font font, Color color, int horizontalAlignment) {
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(horizontalAlignment);
    }
    public void styleLabel(JLabel label, Font font,  int horizontalAlignment) {
        label.setFont(font);
        label.setForeground(white);
        label.setHorizontalAlignment(horizontalAlignment);
    }
    public void styleLabel(JLabel label, Font font) {
        label.setFont(font);
        label.setForeground(white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
    public JPanel createProductPanel(String productInfo) {
        JPanel productPanel = new JPanel();
        productPanel.setBackground(col4);
        //productPanel.setPreferredSize(new Dimension(400, 200));
        JLabel label = new JLabel("<html><div style=\"width: "+(width-300)+"px; padding: 5px;\">"+productInfo+"</div></html");
        styleLabel(label, font4);
        productPanel.add(label);
        return productPanel;
    }
    public JPanel createProductPanel(String productInfo, int width) {
        JPanel productPanel = new JPanel();
        productPanel.setBackground(col4);
        //productPanel.setPreferredSize(new Dimension(400, 200));
        JLabel label = new JLabel("<html><div style=\"width: "+(width-80)+"px; padding: 5px;\">"+productInfo+"</div></html");
        styleLabel(label, font4);
        productPanel.add(label);
        return productPanel;
    }
}
class MenuUI extends UI {

    JButton searchButton = new JButton("Пошук товарів");
    JPanel stats = new JPanel();
    JButton factoryStatsButton = new JButton("Інформація по складу");
    JButton groupStatsButton = new JButton("Інформація по групі товарів");
    JPanel groupManipulations = new JPanel();
    JButton addGroupButton = new JButton("Додати групу товарів");
    JButton editGroupButton = new JButton("Редагувати групу товарів");
    JButton deleteGroupButton = new JButton("Видалити групу товарів");
    JPanel productManipulations = new JPanel();
    JButton addProductButton = new JButton("Додати товар");
    JButton editProductButton = new JButton("Редагувати товар");
    JButton deleteProductButton = new JButton("Видалити товар");
    JButton addMoreProductButton = new JButton("Поставка товару");
    JButton sellProductButton = new JButton("Списання товару");
    JLabel label = new JLabel("Оберіть пункт меню:", JLabel.CENTER);

    MenuUI(Factory factory){
        super(factory);
        mainLabel.setText("Вітаємо на складі!");
        styleMenuButton(searchButton);
        styleMenuButton(factoryStatsButton);
        styleMenuButton(groupStatsButton);
        styleMenuButton(addGroupButton);
        styleMenuButton(editGroupButton);
        styleMenuButton(deleteGroupButton);
        styleMenuButton(addProductButton);
        styleMenuButton(editProductButton);
        styleMenuButton(deleteProductButton);
        styleMenuButton(addMoreProductButton);
        styleMenuButton(sellProductButton);


        styleLabel(label);

        stats.setBorder(new TitledBorder(BorderFactory.createLineBorder(white, 1), "Стан складу", TitledBorder.CENTER, TitledBorder.TOP, font3, white));
        stats.setPreferredSize(new Dimension(280, 120));
        stats.setBackground(col3);
        stats.setForeground(white);
        stats.add(factoryStatsButton, BorderLayout.CENTER);
        stats.add(groupStatsButton, BorderLayout.CENTER);


        groupManipulations.setBorder(new TitledBorder(BorderFactory.createLineBorder(white, 1), "Робота з групами товарів", TitledBorder.CENTER, TitledBorder.TOP, font3, white));
        groupManipulations.setPreferredSize(new Dimension(280, 165));
        groupManipulations.setBackground(col3);
        groupManipulations.setForeground(white);
        groupManipulations.add(addGroupButton, BorderLayout.CENTER);
        groupManipulations.add(editGroupButton, BorderLayout.CENTER);
        groupManipulations.add(deleteGroupButton, BorderLayout.CENTER);


        productManipulations.setBorder(new TitledBorder(BorderFactory.createLineBorder(white, 1), "Робота з товарами", TitledBorder.CENTER, TitledBorder.TOP, font3, white));
        productManipulations.setPreferredSize(new Dimension(280, 255));
        productManipulations.setBackground(col3);
        productManipulations.setForeground(white);
        productManipulations.add(addProductButton, BorderLayout.CENTER);
        productManipulations.add(editProductButton, BorderLayout.CENTER);
        productManipulations.add(deleteProductButton, BorderLayout.CENTER);
        productManipulations.add(addMoreProductButton, BorderLayout.CENTER);
        productManipulations.add(sellProductButton, BorderLayout.CENTER);

        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        gbc.insets = new Insets(5, 0, 5, 0);
        mainPanel.add(label, gbc);
        mainPanel.add(searchButton, gbc);
        mainPanel.add(stats, gbc);
        mainPanel.add(groupManipulations, gbc);
        mainPanel.add(productManipulations, gbc);

        toMenu.setVisible(false);

        factoryStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(factory.showAllProducts());
                String info = "<html><div style=\"text-align: center\">Всього товарів: " +factory.getNumberOfProducts()
                        +"<br>Всього груп товарів: "  +factory.getNumberOfProductsGroups()
                        +"<br>Загальна вартість товару: "  +factory.showAllProductsCost()+"</div></html>";
                StatsUI statisticsUI = new StatsUI("Інформація по складу", info, factory.showAllProducts(), factory);
                statisticsUI.setVisible(true);
                dispose();
            }
        });
        groupStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseGroupUI chooseGroupUI = new ChooseGroupUI("Інформація по групі товарів", factory, "stats");
                chooseGroupUI.setVisible(true);
                dispose();
            }
        });
        deleteGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChooseGroupUI chooseGroupUI = new ChooseGroupUI("Видалення групи товарів", factory, "delete");
                chooseGroupUI.setVisible(true);

            }
        });
        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddGroupUI addGroupUI = new AddGroupUI(factory);
                addGroupUI.setVisible(true);

            }
        });
        editGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChooseGroupUI chooseGroupUI = new ChooseGroupUI("Редагування групи товарів", factory, "edit");
                chooseGroupUI.setVisible(true);

            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChooseGroupUI chooseGroupUI = new ChooseGroupUI("Додавання товару", factory, "addProduct");
                chooseGroupUI.setVisible(true);
            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();
//                ChooseProductUI chooseProductUI = new ChooseProductUI("Редагування товару", factory, "edit");
//                chooseProductUI.setVisible(true);
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();
//                ChooseProductUI chooseProductUI = new ChooseProductUI("Видалення товару", factory, "delete");
//                chooseProductUI.setVisible(true);
            }
        });
        addMoreProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();
//                ChooseProductUI chooseProductUI = new ChooseProductUI("Поставка товару", factory, "addMore");
//                chooseProductUI.setVisible(true);
            }
        });
        sellProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();
//                ChooseProductUI chooseProductUI = new ChooseProductUI("Списання товару", factory, "sell");
//                chooseProductUI.setVisible(true);
            }
        });
    }


}

class StatsUI extends UI{
    JLabel info = new JLabel();
    JPanel statsPanel = new JPanel();
    ArrayList<String> products;
    public StatsUI(String title, String information, ArrayList<String> products, Factory factory){
        super(factory);
        this.products = products;
        mainLabel.setText(title);

        info.setText(information);
        styleLabel(info);

        gbc.insets = new Insets(5, 0, 5, 0);
        statsPanel.setLayout(new GridBagLayout());
        statsPanel.setBackground(col3);
        generateProductLabels();

        mainPanel.setBorder(new EmptyBorder(10, 40, 10, 40));

        mainPanel.add(info, gbc);
        mainPanel.add(statsPanel, gbc);

    }
    private void generateProductLabels() {
        if (products!=null && !products.isEmpty()) {

            for (String product : products) {
                JPanel productPanel = createProductPanel(product);
                statsPanel.add(productPanel, gbc);
            }
        }
    }
}

class ChooseGroupUI extends UI {
    JLabel label = new JLabel();
    JPanel groupsPanel = new JPanel();
    ArrayList<ProductsGroup> groupsArr;
    String action;

    public ChooseGroupUI(String title,  Factory factory, String action) {
        super(factory);
        this.groupsArr = factory.getProductsGroups();
        this.action = action;
        mainLabel.setText(title);

        label.setText("Оберіть групу товарів:");
        styleLabel(label);
        mainPanel.add(label, gbc);

        generateButtons();

        groupsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        groupsPanel.setBackground(col3);
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(groupsPanel, gbc);

    }
    private void generateButtons(){
        if (groupsArr.isEmpty()) {
            label.setText("На складі нема жодної групи товарів");
            gbc.insets = new Insets(10, 10, 10, 10);
            mainPanel.add(label, gbc);
            JButton addGroupButton = new JButton("Додати групу товарів");
            styleMenuButton(addGroupButton);

            addGroupButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddGroupUI addGroupUI = new AddGroupUI(factory);
                    addGroupUI.setVisible(true);
                    dispose();
                }
            });
            mainPanel.add(addGroupButton, gbc);
        } else {
            for (ProductsGroup group : groupsArr) {
                JButton button = new JButton(group.getName());
                styleItemButton(button);
                groupsPanel.add(button, gbc);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (action) {
//
//
//                        case "addProduct":
//                            addProductUI = new AddProductUI(groupsArr.get(i).getName(), factory);
//                            addProductUI.setVisible(true);
//                            dispose();
//                            break;
                            case "edit":
                            EditGroupUI editGroupUI = new EditGroupUI(group, factory);
                            editGroupUI.setVisible(true);
                            dispose();
                            break;
                            case "delete":
                                int confirm = JOptionPane.showConfirmDialog(ChooseGroupUI.super.rootPane, "Ви впевнені, що хочете видалити " + group.getName() + "?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
                                if (confirm == 0) {
                                    JOptionPane.showMessageDialog(ChooseGroupUI.super.rootPane, factory.deleteProductsGroup(group.getName()), "Результат видалення групи", JOptionPane.PLAIN_MESSAGE);
                                    dispose();
                                    ChooseGroupUI chooseGroupUI = new ChooseGroupUI("Видалення групи товарів", factory, "delete");
                                    chooseGroupUI.setVisible(true);
                                }
                                break;
                            case "stats":
                                System.out.println(factory.showAllProducts());
                                String info = "<html><div style=\"text-align: center\">Назва групи: " + group.getName()
                                        + "<br>Опис: " + group.getDescription()
                                        + "<br>Всього товарів: " + group.getNumberOfProducts()
                                        + "<br>Загальна вартість товару у групі: " + group.getAllProductsCostByGroup() + "</div></html>";
                                StatsUI statisticsUI = new StatsUI("Інформація по групі товарів", info, group.showAllProductsByGroup(), factory);
                                statisticsUI.setVisible(true);
                                dispose();
                                break;
                        }
                    }
                });


            }
        }
    }
}

class AddGroupUI extends UI {
    JLabel nameLabel = new JLabel("Введіть назву групи:");
    JTextField nameField = new JTextField(20);
    JLabel descriptionLabel = new JLabel("Введіть опис групи:");
    JTextArea descriptionField = new JTextArea(6, 20);
    JButton submit = new JButton("Створити групу");

    public void styleTextField(Component component){
        component.setFont(font3);
        component.setForeground(white);
        component.setBackground(white1);
    }
    public AddGroupUI(Factory factory){
        super(factory);
        setVisible(false);
        mainLabel.setText("Додавання групи товарів");

        styleLabel(nameLabel, SwingConstants.LEFT);
        styleLabel(descriptionLabel, SwingConstants.LEFT);

        styleTextField(nameField);
        nameField.setMargin(new Insets(10, 10, 10, 10));

        styleTextField(descriptionField);
        descriptionField.setMargin(new Insets(10, 10, 10, 10));
        descriptionField.setLineWrap(true);

        styleItemButton(submit);

        gbc.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(nameLabel, gbc);
        mainPanel.add(nameField, gbc);
        mainPanel.add(descriptionLabel, gbc);
        mainPanel.add(descriptionField, gbc);
        mainPanel.add(submit, gbc);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(AddGroupUI.super.rootPane, "Введіть назву групи!", "Заповніть всі поля!", JOptionPane.ERROR_MESSAGE);
                }
                if (description.equals("")) {
                    JOptionPane.showMessageDialog(AddGroupUI.super.rootPane, "Введіть опис групи!", "Заповніть всі поля!", JOptionPane.ERROR_MESSAGE);

                }
                if (!name.equals("") && !description.equals("")) {
                    int confirm = JOptionPane.showConfirmDialog(AddGroupUI.super.rootPane, "Ви впевнені, що хочете створити групу " + name + "?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
                    if (confirm == 0) {
                        JOptionPane.showMessageDialog(AddGroupUI.super.rootPane, factory.addProductsGroup(name, description), "Додавання групи", JOptionPane.PLAIN_MESSAGE);
                        nameField.setText("");
                        descriptionField.setText("");

                    }
                }
            }
        });


    }

}

class EditGroupUI extends UI {
    JLabel label = new JLabel("Обрана група товарів:");
    JPanel groupInfoPanel;

    JPanel buttonsPanel = new JPanel();
    JLabel choseLabel = new JLabel("Оберіть дію:");

    JButton editName = new JButton("Змінити назву");
    JButton editDescription = new JButton("Змінити опис");
    ProductsGroup group;
    private String listProductNames(ArrayList<Product> products) {
        String names = "";
        if (!products.isEmpty()) {
            for (Product product : products) {
                names += product.getName() + (products.indexOf(product)<products.size()-1 ? ", ": "");
            }
        } else {
            names = "<em>Поки немає товарів</em>";
        }
        return names;
    }
    public EditGroupUI(ProductsGroup group, Factory factory){
        super(factory);
        this.group = group;
        setVisible(false);
        mainLabel.setText("Редагування групи товарів");

        styleLabel(label);
        String info = "<b>Назва:</b> "+group.getName()+"<br><b>Опис:</b> "+group.getDescription()+"<br><b>Товари:</b> "+listProductNames(group.getProducts());
        groupInfoPanel = createProductPanel(info,400);
        styleLabel(choseLabel);

        styleMenuButton(editName);
        styleMenuButton(editDescription);

        gbc.insets = new Insets(5, 10, 5, 10);

        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(col3);
        buttonsPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        buttonsPanel.add(choseLabel, gbc);
        buttonsPanel.add(editName, gbc);
        buttonsPanel.add(editDescription, gbc);

        mainPanel.add(label, gbc);
        mainPanel.add(groupInfoPanel, gbc);
        mainPanel.add(buttonsPanel, gbc);

        editName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editGroup("", "Введіть нову назву для групи товарів "+group.getName(), "Ви впевнені, що хочете змінити назву \""+group.getName()+"\"", "name");
            }
        });
        editDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editGroup("", "Введіть новий опис для групи товарів "+group.getName(),"Ви впевнені, що хочете змінити опис \""+group.getDescription()+"\"", "description");
            }

        });

    }

    /**
     * Method that shows dialog windows to change fields of products group
     * @param value starting value of input
     * @param message1 message that will be displayed in the dialog window (asking for new value)
     * @param message2 message that will be displayed in the confirmation window (are you sure?)
     * @param mode "name", "description" - determines which value will be changed
     */
    private void editGroup(String value, String message1, String message2, String mode){
        String newValue = JOptionPane.showInputDialog(EditGroupUI.super.rootPane, message1,value);
        if (newValue!=null && newValue.equals("")) {
            JOptionPane.showMessageDialog(EditGroupUI.super.rootPane, "Ви не ввели нічого!", "Поле не заповнене!", JOptionPane.ERROR_MESSAGE);
            editGroup("", message1, message2, mode);
        } else if (newValue!=null) {
            int confirm = JOptionPane.showConfirmDialog(EditGroupUI.super.rootPane, message2+" на \""+newValue+"\"?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                String result = factory.editProductsGroup(group.getName(), newValue, mode);
                JOptionPane.showMessageDialog(EditGroupUI.super.rootPane, result, "Операція пройшла успішно!", JOptionPane.PLAIN_MESSAGE);
                dispose();
                EditGroupUI editGroupUI = new EditGroupUI(group, factory);
                editGroupUI.setVisible(true);

            } else {
                editGroup(newValue, message1, message2, mode);
            }
        }
    }

}
public class Program {
    static Factory factory;
    static MenuUI menuUI;

    public static void main(String[] args) {
        factory = new Factory();
        System.out.println(factory);
        init();
    }
    private static void init(){

        menuUI = new MenuUI(factory);

        menuUI.setVisible(true);
//        new intInputDialog(menuUI, "Зміна ціни товару", "Введіть нову ціну товару pollen:", "editPrice", "pollen");
//        new intInputDialog(menu, "Списання товару", "Скільки шт. pollen продали?",  Factory.findProduct("pollen").getQuantity(), "sell", "pollen");
//        new intInputDialog(menu, "Додавання товару", "Скільки шт. pollen прибуло на склад?", "addMore", "pollen");
//        System.out.println(factory);
    }
}
