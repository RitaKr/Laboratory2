import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.LEFT;

/**
 * Клас UI.java відповідає за графічний інтерфейс користувача
 */
class UI extends JFrame {
    Factory factory;
    JButton toMenu;
    JPanel mainPanel;
    JLabel mainLabel;
    GridBagConstraints gbc = new GridBagConstraints();
    static final Color col1 = new Color(69, 128, 206);
    static final  Color col2 = new Color(0, 0, 0);
    static final Color col3 = new Color(51, 48, 47);
    static final Color col4 = new Color(140, 73, 49);
    static final Color white = new Color(255, 255, 255);
    static final Color white1 = new Color(84, 79, 75, 255);
    static Font font1 = new Font("Trebuchet MS", Font.BOLD, 36);
    static Font font2 = new Font("Trebuchet MS", Font.PLAIN, 22);
    static Font font3 = new Font("Trebuchet MS", Font.PLAIN, 16);
    static Font font4 = new Font("Trebuchet MS", Font.PLAIN, 14);
    static int width = 800;
    static final int height = 800;
    static Point frameLocation;
    static int extendedState = NORMAL;
    static Dimension frameSize;
    static int gridCols = 3;
    static boolean mobile = false;
    UI(Factory factory){
        super("Склад");
        this.factory = factory;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(NORMAL);
        extendedState = this.getExtendedState();
        if (frameLocation==null) frameLocation = this.getLocation();
        if (frameSize == null) frameSize = this.getSize();
        this.setLocation(frameLocation);
        this.setExtendedState(extendedState);
        //this.setLocationRelativeTo(this);
        this.setSize(frameSize);


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

        mainPanel.setLayout(new GridBagLayout());

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        //add(mainPanel, BorderLayout.CENTER);
        JScrollPane scroller = new JScrollPane(mainPanel);
        scroller.setBorder(null);
        scroller.getVerticalScrollBar().setUnitIncrement(16);
        scroller.getHorizontalScrollBar().setUnitIncrement(16);
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
        this.addWindowStateListener(new WindowStateListener() {

            @Override
            public void windowStateChanged(WindowEvent e) {
                //System.out.println("window state changed");
                extendedState = UI.this.getExtendedState();
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                //System.out.println("Frame moved to (" + getX() + ", " + getY() + ")");
                frameLocation = UI.this.getLocation();
            }

            @Override
            public void componentResized(ComponentEvent e) {
                //System.out.println("size changed to ("+getWidth()+", "+getHeight()+")");
                frameSize = UI.this.getSize();
                if (frameSize.width<550) {
                    //System.out.println("new width: "+frameSize.width);
                    width=460;
                    font1 = new Font("Trebuchet MS", Font.BOLD, 30);
                    font2 = new Font("Trebuchet MS", Font.PLAIN, 18);
                    font3 = new Font("Trebuchet MS", Font.PLAIN, 14);
                    font4 = new Font("Trebuchet MS", Font.PLAIN, 12);
                    mobile = true;
                    gridCols =2;
                } else {
                    width = 800;
                    font1 = new Font("Trebuchet MS", Font.BOLD, 36);
                    font2 = new Font("Trebuchet MS", Font.PLAIN, 22);
                    font3 = new Font("Trebuchet MS", Font.PLAIN, 16);
                    font4 = new Font("Trebuchet MS", Font.PLAIN, 14);
                    mobile = false;
                    gridCols =3;
                }
                rootPane.updateUI();
                mainPanel.updateUI();
                mainLabel.updateUI();
                toMenu.updateUI();
                //UI.this.update(UI.this.getGraphics());
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
    public void styleSpinner(JSpinner spinner){
        spinner.setFont(font3);
        spinner.setBorder(BorderFactory.createLineBorder(white, 1));
        JFormattedTextField textField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
        textField.setHorizontalAlignment(LEFT);
        textField.setBackground(white1);
        textField.setForeground(white);
        //textField.setMargin(new Insets(10, 10, 10, 10));
        textField.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    public void styleComboBox(JComboBox comboBox){
        comboBox.setFont(font3);
        //comboBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        comboBox.setForeground(col3);
    }
    public void styleTextField(JTextField component) {
        component.setFont(font3);
        component.setForeground(white);
        component.setBackground(white1);
        component.setMargin(new Insets(10, 10, 10, 10));
    }
    public void styleTextField(JTextArea component) {
        component.setFont(font3);
        component.setForeground(white);
        component.setBackground(white1);
        component.setMargin(new Insets(10, 10, 10, 10));
        component.setLineWrap(true);
    }
    public JPanel createProductPanel(String productInfo) {
        JPanel productPanel = new JPanel();
        productPanel.setBackground(col4);

        JLabel label = new JLabel("<html><div style=\"width: "+(getWidth()*(mobile? 0.6 :0.6))+"px; padding: 5px;\">"+productInfo+"</div></html");
        styleLabel(label, font4);
        productPanel.add(label);
        return productPanel;
    }
    public JPanel createProductPanel(String productInfo, int w) {
        JPanel productPanel = new JPanel();
        productPanel.setBackground(col4);

        JLabel label = new JLabel("<html><div style=\"width: "+((mobile ? getWidth()*0.75 : w)*0.8)+"px; padding: 5px;\">"+productInfo+"</div></html");

        styleLabel(label, font4, white, LEFT);
        productPanel.add(label);

        return productPanel;
    }
    public void layoutButtonsGridPanel(JPanel panel, int numberOfButtons) {
        if (numberOfButtons>=3) panel.setLayout(new GridLayout(0, gridCols, 10, 10));
        else if (numberOfButtons==2) panel.setLayout(new GridLayout(0, 2, 10, 10));
        else panel.setLayout(new GridLayout(0, 1, 10, 10));
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

        //deleteProductButton.setForeground(Color.red);


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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchProductUI SearchProductUI = new SearchProductUI(factory);
                SearchProductUI.setVisible(true);
                dispose();
            }
        });

        factoryStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(factory.showAllProducts());
                String info = "<html><div style=\"text-align: center; width: "+(mobile ? getWidth()*0.8 : "initial")+"\">Всього товарів: " +factory.getNumberOfProducts()
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
                AddProductUI addProductUI = new AddProductUI(factory);
                addProductUI.setVisible(true);
                dispose();
            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChooseProductUI chooseProductUI = new ChooseProductUI("Редагування товару", factory, "edit");
                chooseProductUI.setVisible(true);
            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChooseProductUI chooseProductUI = new ChooseProductUI("Видалення товару", factory, "delete");
                chooseProductUI.setVisible(true);
            }
        });
        addMoreProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChooseProductUI chooseProductUI = new ChooseProductUI("Поставка товару", factory, "addMore");
                chooseProductUI.setVisible(true);
            }
        });
        sellProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ChooseProductUI chooseProductUI = new ChooseProductUI("Списання товару", factory, "sell");
                chooseProductUI.setVisible(true);
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
        int sideMargin = mobile? 20 :  40;
        mainPanel.setBorder(new EmptyBorder(10, sideMargin, 10, sideMargin));

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
        mainPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        generateButtons();

        //groupsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        layoutButtonsGridPanel(groupsPanel, factory.getNumberOfProductsGroups());


        //groupsPanel.setLayout(new GridLayout(0, 3, 10, 10));
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
                                //System.out.println(factory.showAllProducts());
                                String info = "<html><div style=\"text-align: center; width: "+(mobile ? getWidth()*0.8 : "initial")+"\">Назва групи: " + group.getName()
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

class SearchProductUI extends UI{
    JLabel resultsAreHere = new JLabel("Тут будуть результати пошуку...", SwingConstants.CENTER);
    JPanel searchPanel = new JPanel();
    JTextField searchField = new JTextField(20);
    JComboBox modeComboBox = new JComboBox();
    //JButton searchButton = new JButton("Пошук");
    JPanel mainMainPanel = new JPanel();
    ArrayList<Product> searchResult;


    public SearchProductUI (Factory factory){
        super(factory);
        setVisible(false);
        mainLabel.setText("Пошук товарів");


        searchField.setMargin(new Insets(10, 10, 10, 10));
        styleTextField(searchField);
        searchField.setPreferredSize(new Dimension(super.getWidth()-(width/4), 40));

        styleComboBox(modeComboBox);
        modeComboBox.addItem("Пошук за іменем");
        modeComboBox.addItem("Пошук за виробником");
        modeComboBox.addItem("Пошук за описом");
        modeComboBox.addItem("Пошук за групою");
        modeComboBox.addItem("Пошук за ціною");

        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchField,BorderLayout.CENTER);
        searchPanel.add(modeComboBox,BorderLayout.EAST);

        //styleItemButton(searchButton);

        gbc.insets = new Insets(10, 10, 10, 10);
        int sideMargin = mobile? 20 :  40;
        mainPanel.setBorder(new EmptyBorder(10, sideMargin, 10, sideMargin));
        styleLabel(resultsAreHere, font3, white1);
        mainPanel.add(resultsAreHere, gbc);


        JScrollPane scroller = new JScrollPane(mainPanel);
        scroller.setBorder(null);
        scroller.getVerticalScrollBar().setUnitIncrement(16);
        scroller.getHorizontalScrollBar().setUnitIncrement(16);
        add(scroller);
        //mainPanel.add(searchButton, gbc);
        mainMainPanel.setLayout(new BorderLayout());
        mainMainPanel.add(searchPanel, BorderLayout.NORTH);
        mainMainPanel.add(scroller, BorderLayout.CENTER);

        add(mainLabel, BorderLayout.NORTH);
        add(mainMainPanel, BorderLayout.CENTER);
        add(toMenu, BorderLayout.SOUTH);

        modeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchChanged();
            }
        });
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //searchChanged(e);
                searchChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchChanged();
            }
        });


    }
    private void searchChanged(){
        String search = searchField.getText();
        if (search.equals("")) {
            if (searchResult!=null) {
                searchResult.clear();
            }
            resultsAreHere.setText("Тут будуть результати пошуку...");
            mainPanel.updateUI();
            //JOptionPane.showMessageDialog(SearchProductUI.super.rootPane, "Введіть пошуковий запит!", "Помилка!", JOptionPane.ERROR_MESSAGE);
        } else {
            String mode =(String) modeComboBox.getSelectedItem();
            searchResult = factory.findProduct(search, mode);
            //System.out.println(searchResult);
        }
        generateProductLabels();
    }
    private void generateProductLabels() {
        mainPanel.removeAll();
        if (searchResult!=null && !searchResult.isEmpty()) {

            for (Product product : searchResult) {
                JPanel productPanel = createProductPanel(product.toStringUI());
                mainPanel.add(productPanel, gbc);
            }
        } else {
            resultsAreHere.setText("За вашим запитом нічого не знайдено :(");
            mainPanel.add(resultsAreHere, gbc);
        }
        mainPanel.updateUI();
    }
}

/**
 * Клас AddGroupUI.java відповідає за інтерфейс додавання групи товару
 */
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

        styleLabel(nameLabel, LEFT);
        styleLabel(descriptionLabel, LEFT);

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

/**
 * Клас EditGroupUI.java відповідає за інтерфейс редагування групи товарів
 */
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
        groupInfoPanel = createProductPanel(info,width/2);
        styleLabel(choseLabel);

        styleMenuButton(editName);
        styleMenuButton(editDescription);

        gbc.insets = new Insets(5, 10, 5, 10);

        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(col3);
        int sideMargin = mobile? 10 :  40;
        //mainPanel.setBorder(new EmptyBorder(10, sideMargin, 10, sideMargin));
        buttonsPanel.setBorder(new EmptyBorder(20, sideMargin, 20, sideMargin));
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
     * Метод, що показує діалогове вікно, в якому можна редагувати різні поля групи товарів
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

/**
 * Клас AddProductUI.java, що відповідає за інтерфейс додавання продукту до групи
 */
class AddProductUI extends UI {
    JLabel groupName = new JLabel("Введіть назву групи:");
    JComboBox groupComboBox = new JComboBox();
    JLabel nameLabel = new JLabel("Введіть назву товару:");
    JTextField nameField = new JTextField(20);
    JLabel descriptionLabel = new JLabel("Введіть опис товару:");
    JTextArea descriptionField = new JTextArea(6, 20);
    JLabel productionName = new JLabel("Введіть країну-виробника:");
    JTextField productionField = new JTextField(20);

    JLabel quantityLabel = new JLabel("Кількість товару:");
    JLabel priceLabel = new JLabel("Ціна товару:");

    int val=0;
    int val2=1000;

    SpinnerModel spinnerModel = new SpinnerNumberModel(val, 0, 100000, 1);
    JSpinner quantitySpinner = new JSpinner(spinnerModel);

    SpinnerModel model = new SpinnerNumberModel(val2, 1000, 100000, 500);
    JSpinner priceSpinner = new JSpinner(model);

    JButton submit = new JButton("Створити товар");


    public void fillComboBox(){
        for (ProductsGroup group:factory.getProductsGroups()){
            groupComboBox.addItem(group.getName());
        }
    }
    public AddProductUI(Factory factory) {
        super(factory);
        setVisible(false);
        mainLabel.setText("Додавання товару");

        if (factory.getProductsGroups().isEmpty()) {
            JLabel label = new JLabel("На складі нема жодної групи товарів");
            styleLabel(label);
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
            styleLabel(groupName, LEFT);
            styleLabel(nameLabel, LEFT);
            styleLabel(descriptionLabel, LEFT);
            styleLabel(productionName, LEFT);
            styleLabel(quantityLabel, LEFT);
            styleLabel(priceLabel, LEFT);

            styleSpinner(quantitySpinner);
            styleSpinner(priceSpinner);

            styleComboBox(groupComboBox);

            fillComboBox();

            styleTextField(nameField);

            styleTextField(descriptionField);

            styleTextField(productionField);

            styleItemButton(submit);

            gbc.insets = new Insets(10, 10, 10, 10);

            mainPanel.add(groupName, gbc);
            mainPanel.add(groupComboBox, gbc);
            mainPanel.add(nameLabel, gbc);
            mainPanel.add(nameField, gbc);
            mainPanel.add(descriptionLabel, gbc);
            mainPanel.add(descriptionField, gbc);
            mainPanel.add(productionName, gbc);
            mainPanel.add(productionField, gbc);
            mainPanel.add(quantityLabel, gbc);
            mainPanel.add(quantitySpinner, gbc);
            mainPanel.add(priceLabel,gbc);
            mainPanel.add(priceSpinner, gbc);
            mainPanel.add(submit, gbc);
        }


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String group=(String) groupComboBox.getSelectedItem();
                //System.out.println(group);
                String name = nameField.getText();
                String description = descriptionField.getText();
                String producer = productionField.getText();
                int quantity = (int)quantitySpinner.getValue();
                int price = (int)priceSpinner.getValue();
//                if(group.equals("")){
//                    JOptionPane.showMessageDialog(AddProductUI.super.rootPane, "Введіть назву групи!", "Заповніть всі поля!", JOptionPane.ERROR_MESSAGE);
//                }
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(AddProductUI.super.rootPane, "Введіть назву товару!", "Заповніть всі поля!", JOptionPane.ERROR_MESSAGE);
                }
                if (description.equals("")) {
                    JOptionPane.showMessageDialog(AddProductUI.super.rootPane, "Введіть опис товару!", "Заповніть всі поля!", JOptionPane.ERROR_MESSAGE);
                }
                if(producer.equals("")){
                    JOptionPane.showMessageDialog(AddProductUI.super.rootPane, "Введіть країну-виробника!", "Заповніть всі поля!", JOptionPane.ERROR_MESSAGE);
                }
                //if (!group.equals("")&&!name.equals("") && !description.equals("")&&!producer.equals("")) {
                if (!name.equals("") && !description.equals("")&&!producer.equals("")) {
                    int confirm = JOptionPane.showConfirmDialog(AddProductUI.super.rootPane, "Ви впевнені, що хочете створити товар " + name + "?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
                    if (confirm == 0) {
                        JOptionPane.showMessageDialog(AddProductUI.super.rootPane, factory.addProduct(group, name, description, producer, quantity, price), "Додавання товару", JOptionPane.PLAIN_MESSAGE);
                        //groupField.setText("");
                        nameField.setText("");
                        descriptionField.setText("");
                        productionField.setText("");
                        quantitySpinner.setValue(val);
                        priceSpinner.setValue(val2);

                    }
                }
            }
        });
    }
}

/**
 * Клас ChooseProductUI.java, що відповідає за інтерфейс постачання та списання товару
 */
class ChooseProductUI extends UI{
    JLabel label = new JLabel();
    JPanel productsPanel = new JPanel();
    ArrayList<Product> productsArr;
    String action;

    public ChooseProductUI(String title,  Factory factory, String action) {
        super(factory);
        this.productsArr = factory.getAllProducts();
        this.action = action;
        mainLabel.setText(title);

        label.setText("Оберіть товар:");
        styleLabel(label);
        mainPanel.add(label, gbc);
        mainPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        generateButtons();
        gbc.insets = new Insets(20, 10, 20, 10);
        productsPanel.setBackground(col3);
        layoutButtonsGridPanel(productsPanel, factory.getNumberOfProducts());

        mainPanel.add(productsPanel, gbc);
    }
    private void generateButtons(){
        if (productsArr.isEmpty()) {
            label.setText("На складі нема жодного товару");
            gbc.insets = new Insets(10, 10, 10, 10);
            mainPanel.add(label, gbc);
            JButton addProductButton = new JButton("Додати товар");
            styleMenuButton(addProductButton);

            addProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AddProductUI addProductUI = new AddProductUI(factory);
                    addProductUI.setVisible(true);
                    dispose();
                }
            });
            mainPanel.add(addProductButton, gbc);
        } else {
            for (Product product : productsArr) {
                JButton button = new JButton(product.getName());
                styleItemButton(button);
                productsPanel.add(button, gbc);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        switch (action) {
                            case "addMore":
                                new intInputDialog(ChooseProductUI.this, "Поставка товару", "Скільки шт. "+product.getName()+" прибуло на склад?", "addMore", product.getName());

                                break;
                            case "sell":
                                new intInputDialog(ChooseProductUI.this, "Списання товару", "Скільки шт. "+product.getName()+" продали?",  product.getQuantity(), "sell", product.getName());

                                break;
                            case "edit":
                                EditProductUI editProductUI = new EditProductUI(product, factory);
                                editProductUI.setVisible(true);
                                dispose();
                                break;
                            case "delete":
                                int confirm = JOptionPane.showConfirmDialog(ChooseProductUI.super.rootPane, "Ви впевнені, що хочете видалити " + product.getName() + "?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
                                if (confirm == 0) {
                                    JOptionPane.showMessageDialog(ChooseProductUI.super.rootPane, factory.deleteProduct(product.getName()), "Результат видалення товару", JOptionPane.PLAIN_MESSAGE);
                                    dispose();
                                    ChooseProductUI chooseProductUI = new ChooseProductUI("Видалення товару", factory, "delete");
                                    chooseProductUI.setVisible(true);
                                }
                                break;

                        }
                    }
                });
            }
        }
    }
}

/**
 * Клас EditProductUI.java, що відповідає за редагування товару за різними параметрами (назва, опис, ціна, приналежність до групи)
 */
class EditProductUI extends UI{

    JLabel label = new JLabel("Обраний товар:");
    JPanel productInfoPanel;
    JPanel buttonsPanel = new JPanel();
    JLabel choseLabel = new JLabel("Оберіть дію:");

    JButton editGroup = new JButton("Змінити групу");
    JButton editName = new JButton("Змінити назву");
    JButton editDescription = new JButton("Змінити опис");
    JButton editProducer = new JButton ("Змінити країну-виробника");
    JButton editPrice = new JButton("Змінити ціну");
    Product product;

    public EditProductUI(Product product, Factory factory){
        super(factory);
        this.product = product;
        setVisible(false);
        mainLabel.setText("Редагування товару");

        styleLabel(label);
        String info = product.toStringUI();
        productInfoPanel = createProductPanel(info,width/2);
        styleLabel(choseLabel);

        styleMenuButton(editGroup);
        styleMenuButton(editName);
        styleMenuButton(editDescription);
        styleMenuButton(editProducer);
        styleMenuButton(editPrice);

        gbc.insets = new Insets(5, 10, 5, 10);

        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(col3);
        int sideMargin = mobile? 10 :  40;
        //mainPanel.setBorder(new EmptyBorder(10, sideMargin, 10, sideMargin));
        buttonsPanel.setBorder(new EmptyBorder(20, sideMargin, 20, sideMargin));
        buttonsPanel.add(choseLabel, gbc);
        buttonsPanel.add(editGroup, gbc);
        buttonsPanel.add(editName, gbc);
        buttonsPanel.add(editDescription, gbc);
        buttonsPanel.add(editProducer, gbc);
        buttonsPanel.add(editPrice, gbc);

        mainPanel.add(label, gbc);
        mainPanel.add(productInfoPanel, gbc);
        mainPanel.add(buttonsPanel, gbc);

        editGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct("", "Введіть назву групи, до якої хочете перемістити товар "+product.getName(), "Ви впевнені, що хочете змінити групу \""+product.getGroup()+"\"", "group");
            }
        });
        editName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct("", "Введіть нову назву товару "+product.getName(), "Ви впевнені, що хочете змінити назву \""+product.getName()+"\"", "name");
            }
        });
        editDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct("", "Введіть новий опис для товару "+product.getName(),"Ви впевнені, що хочете змінити опис \""+product.getDescription()+"\"", "description");
            }

        });

        editProducer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProduct("", "Введіть нову назву країни-виробника "+product.getName(),"Ви впевнені, що хочете змінити країну-виробника \""+product.getProducer()+"\"", "producer");
            }
        });
        editPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new intInputDialog(EditProductUI.this, "Зміна ціни товару", "Введіть нову ціну товару "+product.getName()+":", product.getPrice(), 3000, 10000000, 500, "editPrice", product.getName());
                dispose();
                EditProductUI editProductUI = new EditProductUI(product, factory);
                editProductUI.setVisible(true);
                //editProduct("", "Введіть нову ціну товару "+product.getName(),"Ви впевнені, що хочете змінити ціну \""+product.getPrice()+"\"", "price");
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
    private void editProduct(String value, String message1, String message2, String mode){
        String newValue = JOptionPane.showInputDialog(EditProductUI.super.rootPane, message1,value);
        if (newValue!=null && newValue.equals("")) {
            JOptionPane.showMessageDialog(EditProductUI.super.rootPane, "Ви не ввели нічого!", "Поле не заповнене!", JOptionPane.ERROR_MESSAGE);
            editProduct("", message1, message2, mode);
        } else if (newValue!=null) {
            int confirm = JOptionPane.showConfirmDialog(EditProductUI.super.rootPane, message2+" на \""+newValue+"\"?", "Підтвердження операції", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                String result = factory.editProduct(product.getName(), newValue, mode);
                JOptionPane.showMessageDialog(EditProductUI.super.rootPane, result, "Операція пройшла успішно!", JOptionPane.PLAIN_MESSAGE);
                dispose();
                EditProductUI editProductUI = new EditProductUI(product, factory);
                editProductUI.setVisible(true);

            } else {
                editProduct(newValue, message1, message2, mode);
            }
        }
    }
}


/**
 * Клас Program.java слугує для запуску програми
 */
public class Program {
    static Factory factory;
    static MenuUI menuUI;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        factory = new Factory();
        //System.out.println(factory);
        init();

    }
    private static void init(){
        menuUI = new MenuUI(factory);
        menuUI.setSize(800, 800);
        menuUI.setLocationRelativeTo(null);
        menuUI.setVisible(true);
    }
}
