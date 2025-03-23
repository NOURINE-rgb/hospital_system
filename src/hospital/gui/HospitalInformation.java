package hospital.gui;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public class HospitalInformation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField hospitalNameField;
    private JTextField stateField;
    private JComboBox<String> maxPatientsComboBox;
    private JTextField blockNameField;
    private JTextField specialityField;
    private JComboBox<String> floorNumberComboBox;
    private JButton addBlockButton;
    private JButton saveHospitalButton;
    private JButton exitButton;
    private JTable table;
    private DefaultTableModel tableModel;
    JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalInformation frame = new HospitalInformation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HospitalInformation() {
		setTitle("Hospital Information");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(AppTheme.backgroundColor);

        // Labels
        JLabel hospitalNameLabel = new JLabel("Hospital Name:");
        hospitalNameLabel.setBounds(50, 50, 150, 30);
        hospitalNameLabel.setFont(AppTheme.buttonFont);
        hospitalNameLabel.setForeground(AppTheme.primaryColor);

        JLabel maxPatientsLabel = new JLabel("Max Patients:");
        maxPatientsLabel.setBounds(50, 100, 150, 30);
        maxPatientsLabel.setFont(AppTheme.buttonFont);
        maxPatientsLabel.setForeground(AppTheme.primaryColor);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(50, 150, 150, 30);
        stateLabel.setFont(AppTheme.buttonFont);
        stateLabel.setForeground(AppTheme.primaryColor);

        JLabel blockNameLabel = new JLabel("Block Name:");
        blockNameLabel.setBounds(410, 262, 150, 30);
        blockNameLabel.setFont(AppTheme.buttonFont);
        blockNameLabel.setForeground(AppTheme.primaryColor);

        JLabel floorNumberLabel = new JLabel("Floor Number:");
        floorNumberLabel.setBounds(410, 312, 150, 30);
        floorNumberLabel.setFont(AppTheme.buttonFont);
        floorNumberLabel.setForeground(AppTheme.primaryColor);

        JLabel specialityLabel = new JLabel("Speciality:");
        specialityLabel.setBounds(410, 362, 150, 30);
        specialityLabel.setFont(AppTheme.buttonFont);
        specialityLabel.setForeground(AppTheme.primaryColor);
        
        JLabel createNewBlockText = new JLabel("Create New block");
        createNewBlockText.setBounds(410, 213, 177, 38);
        createNewBlockText.setFont(AppTheme.titleFont);
		
		JLabel allBlockText = new JLabel("All Blocks");
		allBlockText.setFont(new Font("Arial", Font.BOLD, 20));
		allBlockText.setBounds(50, 213, 126, 38);
        // Text Fields
        hospitalNameField = new JTextField();
        hospitalNameField.setBounds(200, 50, 200, 30);
        hospitalNameField.setFont(AppTheme.buttonFont);

        stateField = new JTextField();
        stateField.setBounds(200, 151, 200, 30);
        stateField.setFont(AppTheme.buttonFont);

        blockNameField = new JTextField();
        blockNameField.setBounds(560, 262, 200, 30);
        blockNameField.setFont(AppTheme.buttonFont);

        specialityField = new JTextField();
        specialityField.setBounds(560, 363, 200, 30);
        specialityField.setFont(AppTheme.buttonFont);

        // ComboBoxes
        maxPatientsComboBox = new JComboBox<>(new String[]{"State1", "State2", "State3"});
        maxPatientsComboBox.setModel(new DefaultComboBoxModel(new String[] {"50", "100", "200", "300", "400", "500"}));
        maxPatientsComboBox.setBounds(200, 100, 200, 30);
        maxPatientsComboBox.setFont(AppTheme.buttonFont);
        maxPatientsComboBox.setBackground(AppTheme.backgroundColor);
        maxPatientsComboBox.setForeground(AppTheme.primaryColor);

        floorNumberComboBox = new JComboBox<>(new String[]{"Cardiology", "Neurology", "Orthopedics"});
        floorNumberComboBox.setModel(new DefaultComboBoxModel(new String[] {"first", "second ", "third", "fourth"}));
        floorNumberComboBox.setBounds(560, 312, 200, 30);
        floorNumberComboBox.setFont(AppTheme.buttonFont);
        floorNumberComboBox.setBackground(AppTheme.backgroundColor);
        floorNumberComboBox.setForeground(AppTheme.primaryColor);

        addBlockButton = new JButton("Add Block");
        addBlockButton.setBounds(450, 403, 200, 30);
        AppTheme.styleButton(addBlockButton, AppTheme.successColor, AppTheme.buttonTextColor);

        saveHospitalButton = new JButton("Save Hospital");
        saveHospitalButton.setBounds(29, 510, 200, 30);
        AppTheme.styleButton(saveHospitalButton, AppTheme.primaryColor, AppTheme.buttonTextColor);

        exitButton = new JButton("Exit");
        exitButton.setBounds(279, 510, 200, 30);
        AppTheme.styleButton(exitButton, AppTheme.errorColor, AppTheme.buttonTextColor);
        // table
        String[] columns = {"Title 1", "Title 2", "Title 3", "Title 4"};
        tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);
	    scrollPane = new JScrollPane(table);
	    table.setShowGrid(true);
        table.setGridColor(Color.GRAY);
        table.setRowHeight(25);
        table.setFont(AppTheme.tableFont);
        table.getTableHeader().setFont(AppTheme.tableHeader);
        table.getTableHeader().setBackground(AppTheme.primaryColor);
        table.getTableHeader().setForeground(Color.WHITE);
        scrollPane.setBounds(10, 251, 390, 242);
        // Adding components to the frame
        getContentPane().add(hospitalNameLabel);
        getContentPane().add(hospitalNameField);
        getContentPane().add(maxPatientsLabel);
        getContentPane().add(stateField);
        getContentPane().add(stateLabel);
        getContentPane().add(maxPatientsComboBox);
        getContentPane().add(blockNameLabel);
        getContentPane().add(blockNameField);
        getContentPane().add(floorNumberLabel);
        getContentPane().add(specialityField);
        getContentPane().add(specialityLabel);
        getContentPane().add(scrollPane);
        getContentPane().add(floorNumberComboBox);
        getContentPane().add(addBlockButton);
        getContentPane().add(saveHospitalButton);
        getContentPane().add(exitButton);
        getContentPane().add(createNewBlockText);
        getContentPane().add(allBlockText);
        
		
	}
}
