import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame extends Core {
    private JFrame frame;
    private JPanel parkingBtnPanel;
    private JTable personListTable, vehicleListTable;
    private JLabel studentCountLbl, staffCountLbl, visitorCountLbl, carCountLbl, motorcycleCountLbl, otherCountLbl, personLbl, vehicleLbl;

    public MainFrame() {
        this.initialize();
        RefreshStatistic();

        if (Core.personList.size() > 0) {
            ShowFreshStartDialog();
        }
    }

    private void ShowFreshStartDialog() {
        Object[] options1 = { "Continue...", "Start fresh", "Quit" };

        JPanel panel = new JPanel();
        panel.add(new JLabel("It seems that you have data before. Do you want to continue or start fresh?"));

        int result = JOptionPane.showOptionDialog(null, panel, "Do you want to continue or start fresh ?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);

        if (result == JOptionPane.NO_OPTION) {
            Core.FreshStart();
            RefreshStatistic();
        } else if (result == JOptionPane.CANCEL_OPTION) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("FTSM Parking Lot");
        frame.setSize(600, 470);
        frame.setBounds(100, 100, 600, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        /** HEADER PANEL **/
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.CYAN);
        headerPanel.setBounds(0, 0, 594, 60);
        frame.getContentPane().add(headerPanel);
        FlowLayout fl_headerPanel = new FlowLayout(FlowLayout.CENTER, 5, 20);
        fl_headerPanel.setAlignOnBaseline(true);
        headerPanel.setLayout(fl_headerPanel);

        JLabel lblNewLabel = new JLabel("FTSM PARKING LOT");
        lblNewLabel.setFont(new Font("BankGothic Md BT", Font.BOLD, 16));
        headerPanel.add(lblNewLabel);

        /** MAIN CONTENT **/
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 103, 594, 338);
        frame.getContentPane().add(mainPanel);
        mainPanel.setLayout(new CardLayout(0, 0));

        CardLayout cl = (CardLayout) mainPanel.getLayout();

        /** HOME PANEL (1) **/
        JPanel homePanel = new JPanel();
        homePanel.setBackground(SystemColor.control);
        mainPanel.add(homePanel, "name_34839202250399");
        homePanel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("WELCOME TO FTSM PARKING LOT MANAGEMENT SYSTEM");
        lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 13));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(0, 11, 594, 54);
        homePanel.add(lblNewLabel_1);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Statistic", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBounds(10, 69, 295, 258);
        homePanel.add(panel);
        panel.setLayout(new GridLayout(2, 2, -60, 0));

        personLbl = new JLabel("Person");
        panel.add(personLbl);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.add(panel_2);
        panel_2.setLayout(new GridLayout(3, 2, 0, 0));

        JLabel lblNewLabel_4 = new JLabel("Student");
        panel_2.add(lblNewLabel_4);

        studentCountLbl = new JLabel("0");
        panel_2.add(studentCountLbl);

        JLabel lblNewLabel_6 = new JLabel("Staff");
        panel_2.add(lblNewLabel_6);

        staffCountLbl = new JLabel("0");
        panel_2.add(staffCountLbl);

        JLabel lblNewLabel_7 = new JLabel("Visitor");
        panel_2.add(lblNewLabel_7);

        visitorCountLbl = new JLabel("0");
        panel_2.add(visitorCountLbl);

        vehicleLbl = new JLabel("Vehicle");
        panel.add(vehicleLbl);

        JPanel panel_2_1 = new JPanel();
        panel_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.add(panel_2_1);
        panel_2_1.setLayout(new GridLayout(3, 2, 5, 0));

        JLabel lblNewLabel_4_1 = new JLabel("Car");
        panel_2_1.add(lblNewLabel_4_1);

        carCountLbl = new JLabel("0");
        panel_2_1.add(carCountLbl);

        JLabel lblNewLabel_6_1 = new JLabel("Motorcycle");
        panel_2_1.add(lblNewLabel_6_1);

        motorcycleCountLbl = new JLabel("0");
        panel_2_1.add(motorcycleCountLbl);

        JLabel lblNewLabel_7_1 = new JLabel("Others");
        panel_2_1.add(lblNewLabel_7_1);

        otherCountLbl = new JLabel("0");
        panel_2_1.add(otherCountLbl);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Quick Access", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(315, 69, 269, 258);
        homePanel.add(panel_1);

        /** PERSON PANEL (2) **/
        JPanel personPanel = new JPanel();
        personPanel.setBorder(new TitledBorder(null, "Person", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        personPanel.setBackground(SystemColor.control);
        mainPanel.add(personPanel, "name_34851867138600");
        personPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
        flowLayout_1.setVgap(10);
        personPanel.add(panel_5, BorderLayout.SOUTH);

        JButton addPersonBtn = new JButton("Add Person");
        addPersonBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    final Modal dialog = new Modal(frame, true);
                    dialog.setVisible(true);

                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent arg0) {
                            LoadPersonToTable();
                        }
                    });
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        panel_5.add(addPersonBtn);

        JButton appointManager = new JButton("Appoint as Management");
        appointManager.setName("manager");
        appointManager.setVisible(false);
        appointManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = personListTable.getSelectedRow();
                if (row != -1) {
                    String sName = personListTable.getModel().getValueAt(row, 1).toString();
                    String sMatric = personListTable.getModel().getValueAt(row, 2).toString();
                    String type = appointManager.getName();
                    Person xPerson = Core.GetPersonByMatric(sMatric);

                    if (type == "management") {
                        int ans = JOptionPane.showConfirmDialog(null, String.format("Are you sure want to appoint '%s' - %s as management staff?", sName, sMatric), "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION) {
                            xPerson.setCategory("management");
                        }
                    } else {
                        xPerson.setCategory("staff");
                    }

                    appointManager.setVisible(false);
                    Core.SavePerson();
                    LoadPersonToTable();
                }
            }
        });
        panel_5.add(appointManager);

        JButton btnNewButton_5 = new JButton("Remove Person");
        btnNewButton_5.setVisible(false);
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = personListTable.getSelectedRow();
                if (row != -1) {
                    String sName = personListTable.getModel().getValueAt(row, 1).toString();
                    String sMatric = personListTable.getModel().getValueAt(row, 2).toString();

                    int ans = JOptionPane.showConfirmDialog(null, String.format("Are you sure want to delete '%s' - %s\nYou can't revert this changes.", sName, sMatric), "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        boolean stmt = Core.RemovePersonByMatric(sMatric);
                        if (stmt)
                            LoadPersonToTable();
                        else
                            JOptionPane.showMessageDialog(null, "An error has been occurred.");
                    }
                }
            }
        });
        panel_5.add(btnNewButton_5);

        personListTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        personListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        personListTable.getSelectionModel().addListSelectionListener(e -> {
            if (personListTable.getSelectedRow() != -1) {
                String sMatric = personListTable.getModel().getValueAt(personListTable.getSelectedRow(), 2).toString();
                Person xPerson = Core.GetPersonByMatric(sMatric);

                if (xPerson != null) {
                    btnNewButton_5.setVisible(true);

                    if (xPerson.isStaff()) {
                        if (xPerson.getCategory().equals("staff")) {
                            appointManager.setText("Appoint as Management");
                            appointManager.setName("management");
                        } else {
                            appointManager.setText("Appoint as Ordinary");
                            appointManager.setName("staff");
                        }

                        appointManager.setEnabled(true);
                        appointManager.setVisible(true);
                    } else {
                        appointManager.setName("manager");
                        appointManager.setEnabled(false);
                        appointManager.setVisible(false);
                    }
                }
            } else {
                btnNewButton_5.setVisible(false);
            }
        });
        JScrollPane scrollPane = new JScrollPane(personListTable);
        personPanel.add(scrollPane, BorderLayout.CENTER);

        /** VEHICLE PANEL (3) **/
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setBorder(new TitledBorder(null, "Vehicle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        mainPanel.add(vehiclePanel, "name_39145096793400");
        vehiclePanel.setLayout(new BorderLayout(0, 0));

        JPanel vehicleActionPanel = new JPanel();
        vehicleActionPanel.setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        FlowLayout fl_vehicleActionPanel = (FlowLayout) vehicleActionPanel.getLayout();
        fl_vehicleActionPanel.setVgap(10);
        vehiclePanel.add(vehicleActionPanel, BorderLayout.SOUTH);

        JButton addVehicleBtn = new JButton("Add Vehicle");
        addVehicleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    final VehicleModal dialog = new VehicleModal(frame, true);
                    dialog.setVisible(true);

                    dialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent arg0) {
                            LoadVehicleToTable();
                        }
                    });
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        vehicleActionPanel.add(addVehicleBtn);

        JButton changeOwnerBtn = new JButton("Change ownership");
        changeOwnerBtn.setVisible(false);
        changeOwnerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = vehicleListTable.getSelectedRow();
                if (row != -1) {
                    String sPlate = vehicleListTable.getModel().getValueAt(row, 2).toString();
                    Vehicle xVeh = Core.GetVehicleByPlate(sPlate);
                    Person xPerson = Core.GetPersonByPlate(sPlate);

                    if (xVeh != null) {
                        String[] userOpt = new String[Core.personList.size()];
                        for (int i = 0; i < Core.personList.size(); i++) {
                            userOpt[i] = String.format("%s - %s", Core.personList.get(i).getMatricNo(), Core.personList.get(i).getName());
                        }

                        Object temp = JOptionPane.showInputDialog(frame,
                                "Please select the new owner", "Change vehicle ownership for " + sPlate,
                                JOptionPane.QUESTION_MESSAGE, null, userOpt, 0);

                        if (temp != null) {
                            String res = temp.toString();
                            String matric = res.substring(0, res.indexOf('-')).trim();

                            if (!matric.isEmpty()) {
                                Person nPerson = Core.GetPersonByMatric(matric);
                                Core.ChangeVehicleOwnership(sPlate, nPerson);
                                LoadVehicleToTable();
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "An error has been occurred. [Not found]");
                }

            }
        });
        vehicleActionPanel.add(changeOwnerBtn);

        JButton parkBtn = new JButton("Park vehicle");
        parkBtn.setVisible(false);
        parkBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = vehicleListTable.getSelectedRow();
                    String sPlate = vehicleListTable.getModel().getValueAt(row, 2).toString();
                    Person xPer = Core.GetPersonByPlate(sPlate);
                    Vehicle xVeh = Core.GetVehicleByPlate(sPlate);

                    if (xVeh != null) {
                        if (parkBtn.getName() == "park") {
                            ArrayList<ParkingLot> tParking = Core.GetAvailableParkingLot(xPer, xVeh.getType());
                            if (tParking.size() > 0) {
                                final ParkingModal dialog = new ParkingModal(frame, true, tParking, xVeh);
                                dialog.setVisible(true);

                                dialog.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosed(WindowEvent arg0) {
                                        LoadVehicleToTable();
                                    }
                                });
                            } else {
                                JOptionPane.showMessageDialog(null, "No parking space available for this car.");
                            }
                        } else {
                            for (ParkingLot pl : Core.parkingLotList) {
                                if (pl.UnparkVehicle(xVeh)) {
                                    JOptionPane.showMessageDialog(null, String.format("Successfully unpark %s", xVeh.getPlate()));
                                    LoadVehicleToTable();
                                    break;
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "An error has been occurred. [Not found]");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
        vehicleActionPanel.add(parkBtn);

        JButton removeVehicleBtn = new JButton("Remove Vehicle");
        removeVehicleBtn.setVisible(false);
        removeVehicleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = vehicleListTable.getSelectedRow();
                String sPlate = vehicleListTable.getModel().getValueAt(row, 2).toString();
                Vehicle xVeh = Core.GetVehicleByPlate(sPlate);
                Person xPerson = Core.GetPersonByPlate(sPlate);

                if (xVeh != null) {
                    int ans = JOptionPane.showConfirmDialog(null, String.format("Are you sure want to remove this %s (%s) owned by '%s'\nYou can't revert this changes.", xVeh.getType(), xVeh.getPlate(), xPerson.getMatricNo()), "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        boolean stmt = Core.RemoveVehicle(xVeh.getPlate(), xPerson.getMatricNo());
                        if (stmt)
                            LoadVehicleToTable();
                        else
                            JOptionPane.showMessageDialog(null, "An error has been occurred.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "An error has been occurred. [Not found]");
                }

            }
        });
        vehicleActionPanel.add(removeVehicleBtn);

        vehicleListTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        personListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vehicleListTable.getSelectionModel().addListSelectionListener(e -> {
            if (vehicleListTable.getSelectedRow() != -1) {
                String plate = vehicleListTable.getValueAt(vehicleListTable.getSelectedRow(), 2).toString();
                Vehicle xVeh = Core.GetVehicleByPlate(plate);

                changeOwnerBtn.setVisible(true);
                removeVehicleBtn.setVisible(true);
                parkBtn.setVisible(true);

                if (!xVeh.isPark()) {
                    parkBtn.setText("Park");
                    parkBtn.setName("park");
                } else {
                    parkBtn.setText("Remove from Parking");
                    parkBtn.setName("unpark");
                }
            } else {
                removeVehicleBtn.setVisible(false);
                changeOwnerBtn.setVisible(false);
                parkBtn.setVisible(false);
            }
        });
        JScrollPane vScrollPane = new JScrollPane(vehicleListTable);
        vehiclePanel.add(vScrollPane, BorderLayout.CENTER);

        /** PARKING PANEL (4) **/
        JPanel parkingPanel = new JPanel();
        parkingPanel.setBorder(new TitledBorder(null, "Parking", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        mainPanel.add(parkingPanel, "name_39392039976400");
        parkingPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setVgap(10);
        parkingPanel.add(panel_3, BorderLayout.SOUTH);

        JComboBox comboBox = new JComboBox();
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (comboBox.getSelectedIndex() != -1) {
                    LoadParkingBtn(comboBox.getSelectedItem().toString().charAt(0));
                }
            }
        });
        panel_3.add(comboBox);

        parkingBtnPanel = new JPanel();
        JScrollPane pScrollPane = new JScrollPane(parkingBtnPanel);
        pScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        parkingPanel.add(pScrollPane, BorderLayout.CENTER);

        /** ACTION PANEL **/
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(SystemColor.menu);
        FlowLayout fl_actionPanel = (FlowLayout) actionPanel.getLayout();
        fl_actionPanel.setVgap(9);
        fl_actionPanel.setAlignOnBaseline(true);
        actionPanel.setBounds(0, 60, 594, 40);
        frame.getContentPane().add(actionPanel);

        JButton homeBtn = new JButton("Home");
        homeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cl.show(mainPanel, "name_34839202250399");
            }
        });
        actionPanel.add(homeBtn);

        JButton personBtn = new JButton("Person");
        personBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                LoadPersonToTable();
                cl.show(mainPanel, "name_34851867138600");
            }
        });
        actionPanel.add(personBtn);

        JButton vehicleBtn = new JButton("Vehicle");
        vehicleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadVehicleToTable();
                cl.show(mainPanel, "name_39145096793400");
            }
        });
        actionPanel.add(vehicleBtn);

        JButton parkingBtn = new JButton("Parking");
        parkingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] pOpt = new String[Core.parkingLotList.size()];
                for (int i = 0; i < pOpt.length; i++)
                    pOpt[i] = String.format("%c - %s", Core.parkingLotList.get(i).getBlock(), Core.parkingLotList.get(i).getName());

                comboBox.setModel(new DefaultComboBoxModel(pOpt));
                LoadParkingBtn(pOpt[0].charAt(0));
                cl.show(mainPanel, "name_39392039976400");
            }
        });
        actionPanel.add(parkingBtn);
    }

    private void RefreshStatistic() {
        int[] count = new int[6];

        for (Person p : Core.personList) {
            switch (p.getCategory()) {
                case "student":
                    count[0]++;
                    break;

                case "staff":
                case "management":
                    count[1]++;
                    break;

                case "visitor":
                    count[2]++;
                    break;
            }

            for (Vehicle v : p.getVehicles()) {
                switch (v.getType()) {
                    case "car":
                        count[3]++;
                        break;

                    case "motorcycle":
                        count[4]++;
                        break;

                    default:
                        count[5]++;
                        break;
                }
            }
        }

        personLbl.setText("Person (" + Integer.toString(Core.personList.size()) + ")");
        vehicleLbl.setText("Vehicle (" + Integer.toString(count[3] + count[4] + count[5]) + ")");

        studentCountLbl.setText(Integer.toString(count[0]));
        staffCountLbl.setText(Integer.toString(count[1]));
        visitorCountLbl.setText(Integer.toString(count[2]));

        carCountLbl.setText(Integer.toString(count[3]));
        motorcycleCountLbl.setText(Integer.toString(count[4]));
        otherCountLbl.setText(Integer.toString(count[5]));
    }

    private void LoadPersonToTable() {
        try {
            String[][] data = new String[Core.personList.size()][];
            for (int i = 0; i < Core.personList.size(); i++) {
                Person xPerson = Core.personList.get(i);

                data[i] = new String[]{
                        Integer.toString((i + 1)),
                        xPerson.getName(),
                        xPerson.getMatricNo(),
                        xPerson.getCategory().toUpperCase(),
                        Integer.toString(xPerson.getVehicles().size())
                };
            }

            DefaultTableModel model = new DefaultTableModel(data, new String[]{
                    "#", "Name", "Matric Number", "Category", "Vehicle Count"
            });

            personListTable.setModel(model);
            RefreshStatistic();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void LoadVehicleToTable() {
        try {
            String[][] data = new String[Core.GetVehicles(false).size()][];
            for (int i = 0; i < Core.GetVehicles(false).size(); i++) {
                Vehicle xVeh = Core.GetVehicles(false).get(i);

                data[i] = new String[]{
                        Integer.toString((i + 1)),
                        Core.GetPersonByPlate(xVeh.getPlate()).getName(),
                        xVeh.getPlate(),
                        xVeh.getType(),
                        Boolean.toString(xVeh.isPark())
                };
            }

            DefaultTableModel model = new DefaultTableModel(data, new String[]{
                    "#", "MATRIC NUMBER", "PLATE", "TYPE", "PARKED"
            });

            vehicleListTable.setModel(model);
            RefreshStatistic();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void LoadParkingBtn(char block) {
        ArrayList<Space> xSpace = Core.GetParkingSpaceByBlock(block);
        JButton tButton;

        if (xSpace != null) {
            parkingBtnPanel.setLayout(new GridLayout(6, 6));
            parkingBtnPanel.removeAll();

            for (Space sp : xSpace) {
                tButton = new JButton(sp.getFull());
                tButton.setName(sp.getFull());
                tButton.setContentAreaFilled(false);
                tButton.setOpaque(true);
                tButton.setVisible(true);
                if (!sp.getPlate().equals("null")) {
                    tButton.setText(String.format("%s - (%s)", sp.getPlate(), sp.getFull()));
                    tButton.setBackground(Color.red);
                } else {
                    tButton.setBackground(Color.green);
                }

                JButton finalTButton = tButton;
                tButton.addActionListener(e -> {
                    char b = finalTButton.getName().charAt(0);
                    int id = Integer.parseInt(finalTButton.getName().substring(1));

                    ParkingLot tParking = Core.GetParkingLot(b);

                    if (tParking != null) {
                        // Unpark vehicle
                        if (!finalTButton.getText().equals(sp.getFull())) {
                            String plate = finalTButton.getText().split("-")[0].trim();

                            int ans = JOptionPane.showConfirmDialog(null, String.format("Are you sure want to remove '%s' from %s", plate, sp.getFull()), "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (ans == JOptionPane.YES_OPTION) {
                                tParking.UnparkVehicle((id - 1));
                            }
                        } else {
                            // Park Vehicle
                            ArrayList<Vehicle> vList = Core.GetVehicles(tParking, true);
                            String[] data = new String[vList.size()];

                            for (int i = 0; i < data.length; i++) {
                                Person xPerson = Core.GetPersonByPlate(vList.get(i).getPlate());
                                data[i] = String.format("%s - %s [%s]", vList.get(i).getPlate(), xPerson.getName(), xPerson.getMatricNo());
                            }

                            if (data.length > 0) {
                                Object temp = JOptionPane.showInputDialog(frame,
                                        "Please select a vehicle to park", "Park vehicle to " + finalTButton.getText(),
                                        JOptionPane.QUESTION_MESSAGE, null, data, 0);

                                if (temp != null) {
                                    String ans = temp.toString();
                                    String plate = ans.split("-")[0].trim();

                                    for (ParkingLot pl : Core.parkingLotList) {
                                        if (pl.getBlock() == b) {
                                            pl.ParkVehicle((id-1), Core.GetVehicleByPlate(plate));
                                            break;
                                        }
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "All vehicles has been parked.");
                            }
                        }
                    }
                    LoadParkingBtn(b);
                });
                parkingBtnPanel.add(tButton);
            }

            parkingBtnPanel.revalidate();
            parkingBtnPanel.repaint();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame().frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}