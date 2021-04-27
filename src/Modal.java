import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Modal extends JDialog {
    private JTextField matricBox, nameBox;

    public Modal(Frame owner, boolean modal) {
        super(owner, modal);
        initialize();
    }

    private void initialize() {
        this.setTitle("Create person");
        this.setResizable(false);
        this.setSize(280, 200);
        this.setBounds(100, 100, 280, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(3, 2, 0, 0));

        JLabel lblNewLabel = new JLabel("Name");
        mainPanel.add(lblNewLabel);

        nameBox = new JTextField();
        mainPanel.add(nameBox);
        nameBox.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Matric Number");
        mainPanel.add(lblNewLabel_2);

        matricBox = new JTextField();
        mainPanel.add(matricBox);
        matricBox.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Category");
        mainPanel.add(lblNewLabel_1);

        JComboBox categoryBox = new JComboBox();
        categoryBox.setModel(new DefaultComboBoxModel(new String[]{"Student", "Staff", "Visitor"}));
        mainPanel.add(categoryBox);

        JPanel actionPanel = new JPanel();
        this.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        JButton createBtn = new JButton("CREATE");
        createBtn.addActionListener(e -> {
            try {
                String name = nameBox.getText();
                String matric = matricBox.getText().toUpperCase();
                String category = categoryBox.getSelectedItem().toString();

                if (name.isEmpty() || matric.isEmpty() || category.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in the blank!");
                } else {
                    if (Core.AddPerson(new Person(name, matric, category.toLowerCase())))
                        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    else
                        JOptionPane.showMessageDialog(null, "A person with the matric number already exists!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        actionPanel.add(createBtn);
    }
}

class VehicleModal extends JDialog {
    private JTextField nameBox;
    private JComboBox userBox;

    public VehicleModal(Frame owner, boolean modal) {
        super(owner, modal);
        initialize();
        LoadPersonToSelection();
    }

    private void LoadPersonToSelection() {
        String[] userOpt = new String[Core.personList.size()];
        for (int i = 0; i < userOpt.length; i++)
            userOpt[i] = String.format("%s - %s", Core.personList.get(i).getMatricNo(), Core.personList.get(i).getName());

        userBox.setModel(new DefaultComboBoxModel(userOpt));
    }

    private void initialize() {
        this.setTitle("Create vehicle");
        this.setResizable(false);
        this.setSize(280, 200);
        this.setBounds(100, 100, 280, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(3, 2, 0, 0));

        JLabel lblNewLabel_1 = new JLabel("User");
        mainPanel.add(lblNewLabel_1);

        userBox = new JComboBox();
        mainPanel.add(userBox);

        JLabel lblNewLabel_2 = new JLabel("Type");
        mainPanel.add(lblNewLabel_2);

        JComboBox typeBox = new JComboBox();
        typeBox.setModel(new DefaultComboBoxModel(new String[]{"Car", "Motorcycle", "Others"}));
        mainPanel.add(typeBox);

        JLabel lblNewLabel = new JLabel("Plate");
        mainPanel.add(lblNewLabel);

        nameBox = new JTextField();
        lblNewLabel.setLabelFor(nameBox);
        mainPanel.add(nameBox);
        nameBox.setColumns(10);

        JPanel actionPanel = new JPanel();
        this.getContentPane().add(actionPanel, BorderLayout.SOUTH);
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

        JButton createBtn = new JButton("CREATE");
        createBtn.addActionListener(e -> {
            if (userBox.getSelectedIndex() != -1 || !nameBox.getText().isEmpty() || typeBox.getSelectedIndex() != -1) {
                Person xPerson = Core.personList.get(userBox.getSelectedIndex());

                Vehicle v = Core.GetVehicleByPlate(nameBox.getText().trim());

                if (v == null) {
                    xPerson.addVehicle(nameBox.getText(), (typeBox.getSelectedIndex() + 1));
                    Core.SavePerson();
                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                } else {
                    JOptionPane.showMessageDialog(null, "There're already a vehicle with that plate.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in the blank!");
            }
        });
        actionPanel.add(createBtn);
    }
}

class ParkingModal extends JDialog {
    private JComboBox comboBox;
    private JPanel parkingBtnPanel;
    private ArrayList<ParkingLot> lots;
    private Vehicle v;

    public ParkingModal(Frame owner, boolean modal, ArrayList<ParkingLot> lots, Vehicle v) {
        super(owner, modal);
        this.v = v;
        this.lots = lots;
        initialize();
        LoadAvailableParking();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setTitle("Parking");
        this.setBounds(100, 100, 432, 343);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        this.getContentPane().add(panel, BorderLayout.SOUTH);

        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(removeBtn);

        comboBox = new JComboBox();
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (comboBox.getSelectedIndex() != -1) {
                    LoadParkingBtn(comboBox.getSelectedItem().toString().charAt(0));
                }
            }
        });
        panel.add(comboBox);

        parkingBtnPanel = new JPanel();
        parkingBtnPanel.setBorder(new TitledBorder(null, "Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JScrollPane pScrollPane = new JScrollPane(parkingBtnPanel);
        pScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.getContentPane().add(pScrollPane, BorderLayout.CENTER);
    }

    private void LoadAvailableParking() {
        try {
            String[] pOpt = new String[this.lots.size()];
            for (int i = 0; i < pOpt.length; i++) {
                pOpt[i] = String.format("%c - %s", this.lots.get(i).getBlock(), Core.parkingLotList.get(i).getName());
            }

            comboBox.setModel(new DefaultComboBoxModel(pOpt));
            LoadParkingBtn(comboBox.getSelectedItem().toString().charAt(0));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }

    private void LoadParkingBtn(char block) {
        ParkingLot tParking = Core.GetParkingLot(block);
        JButton tButton;

        parkingBtnPanel.setLayout(new GridLayout(6, 6));
        parkingBtnPanel.removeAll();

        for (Space sp : tParking.getSpace()) {
            tButton = new JButton(sp.getFull());
            tButton.setName(Integer.toString(sp.getId()));
            tButton.setContentAreaFilled(false);
            tButton.setOpaque(true);
            tButton.setVisible(true);
            if (!sp.getPlate().equals("null")) {
                tButton.setEnabled(false);
                tButton.setText(String.format("%s - (%s)", sp.getPlate(), sp.getFull()));
                tButton.setBackground(Color.red);
            } else {
                tButton.setEnabled(true);
                tButton.setBackground(Color.green);
            }

            JButton finalTButton = tButton;
            tButton.addActionListener(e -> {
                int id = Integer.parseInt(finalTButton.getName());

                int ans = JOptionPane.showConfirmDialog(null, String.format("Are you sure want to park '%s' to %s", v.getPlate(), sp.getFull()), "Confirmation", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    tParking.ParkVehicle((id - 1), v);
                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
            });
            parkingBtnPanel.add(tButton);
        }

        parkingBtnPanel.revalidate();
        parkingBtnPanel.repaint();
    }
}