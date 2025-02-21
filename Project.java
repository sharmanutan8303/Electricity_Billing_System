package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    String atype, meter;

    Project(String atype, String meter) {
        this.atype = atype;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        // Menu Bar
        JMenuBar mb = new JMenuBar();
        mb.setBackground(new Color(240, 240, 240));
        setJMenuBar(mb);

        // Master Menu
        JMenu master = new JMenu("Master");
        styleMenu(master, Color.BLUE, 16);

        // Add Menu Items for Master
        addMenuItem(master, "New Customer", "icon/icon1.png", 'D', KeyEvent.VK_D);
        addMenuItem(master, "Customer Details", "icon/icon2.png", 'M', KeyEvent.VK_M);
        addMenuItem(master, "Deposit Details", "icon/icon3.png", 'N', KeyEvent.VK_N);
        addMenuItem(master, "Calculate Bill", "icon/icon5.png", 'B', KeyEvent.VK_B);

        // Information Menu
        JMenu info = new JMenu("Information");
        styleMenu(info, Color.RED, 18); // Larger Font Size
        addMenuItem(info, "Update Information", "icon/icon4.png", 'P', KeyEvent.VK_P);
        addMenuItem(info, "View Information", "icon/icon6.png", 'L', KeyEvent.VK_L);

        // User Menu
        JMenu user = new JMenu("User");
        styleMenu(user, Color.BLUE, 18); // Larger Font Size
        addMenuItem(user, "Pay Bill", "icon/icon4.png", 'R', KeyEvent.VK_R);
        addMenuItem(user, "Bill Details", "icon/icon6.png", 'B', KeyEvent.VK_B);

        // Report Menu
        JMenu report = new JMenu("Report");
        styleMenu(report, Color.RED, 18); // Larger Font Size
        addMenuItem(report, "Generate Bill", "icon/icon7.png", 'G', KeyEvent.VK_G);

        // Utility Menu
        JMenu utility = new JMenu("Utility");
        styleMenu(utility, Color.BLUE, 18); // Larger Font Size
        addMenuItem(utility, "Notepad", "icon/icon12.png", 'N', KeyEvent.VK_N);
        addMenuItem(utility, "Calculator", "icon/icon9.png", 'C', KeyEvent.VK_C);

        // Exit Menu
        JMenu mexit = new JMenu("Exit");
        styleMenu(mexit, Color.RED, 16);
        addMenuItem(mexit, "Exit", "icon/icon11.png", 'W', KeyEvent.VK_W);

        // Add Menus
        if (atype.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }
        mb.add(utility);
        mb.add(mexit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    // Add Menu Item
    private void addMenuItem(JMenu menu, String name, String iconPath, char mnemonic, int keyEvent) {
        JMenuItem item = new JMenuItem(name);
        item.setFont(new Font("monospaced", Font.PLAIN, 14));
        item.setBackground(Color.WHITE);
        item.setForeground(Color.DARK_GRAY);
        item.addActionListener(this);

        // Set Icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        item.setIcon(new ImageIcon(image));

        // Add Keyboard Shortcut
        item.setMnemonic(mnemonic);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, ActionEvent.CTRL_MASK));
        menu.add(item);
    }

    // Style Menu Method
    private void styleMenu(JMenu menu, Color color, int fontSize) {
        menu.setForeground(color);
        menu.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
        menu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menu.setForeground(Color.ORANGE);
            }

            public void mouseExited(MouseEvent e) {
                menu.setForeground(color);
            }
        });
    }

    // Action Handling
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        try {
            if (msg.equals("New Customer")) new NewCustomer();
            else if (msg.equals("Customer Details")) new CustomerDetails();
            else if (msg.equals("Deposit Details")) new DepositDetails();
            else if (msg.equals("Calculate Bill")) new CalculateBill();
            else if (msg.equals("View Information")) new ViewInformation(meter);
            else if (msg.equals("Update Information")) new UpdateInformation(meter);
            else if (msg.equals("Bill Details")) new BillDetails(meter);
            else if (msg.equals("Pay Bill")) new PayBill(meter);
            else if (msg.equals("Generate Bill")) new GenerateBill(meter);
            else if (msg.equals("Notepad")) Runtime.getRuntime().exec("notepad.exe");
            else if (msg.equals("Calculator")) Runtime.getRuntime().exec("calc.exe");
            else if (msg.equals("Exit")) {
                setVisible(false);
                new Login();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Project("", "");
    }
}
