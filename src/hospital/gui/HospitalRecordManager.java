package hospital.gui;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class HospitalRecordManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton editHospitalInfoButton;
    private JButton addPatientButton;
    private JButton exitButton;
    private JTable table;
    private DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JLabel patientListText;
    private JButton applyFilterButton;
    private JLabel filterBlockText;
    private JLabel filterTypeText;
    JComboBox<Object> blockBox;
    JComboBox<Object> typeBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalRecordManager frame = new HospitalRecordManager();
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
	public HospitalRecordManager(){
		setTitle("Hospital Record Manager");
		setSize(1130, 627);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
        getContentPane().setBackground(AppTheme.backgroundColor);
		//JPanel panel = new JPanel();
		editHospitalInfoButton = new JButton("Edit Hospital information");
		addPatientButton = new JButton("Add Patient");
		exitButton = new JButton("Exit");
		patientListText = new JLabel("Patient List");
		String[] columns = {"Title 1", "Title 2", "Title 3", "Title 4"};
        tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);
	    scrollPane = new JScrollPane(table);
		applyFilterButton = new JButton("Apply Filter");
		filterBlockText = new JLabel("filter by block");
		filterTypeText = new JLabel("filter by type");
		blockBox = new JComboBox<Object>();
		blockBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"item1", "item2", "item3"}));
		blockBox.setSelectedIndex(1);
		typeBox = new JComboBox<Object>();
		typeBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"item1", "item2", "item3"}));
		typeBox.setSelectedIndex(0);

		// fonts
		AppTheme.styleButton(addPatientButton, AppTheme.successColor, AppTheme.buttonTextColor);
		AppTheme.styleButton(exitButton, AppTheme.errorColor, AppTheme.buttonTextColor);
		AppTheme.styleButton(editHospitalInfoButton, AppTheme.buttonColor, AppTheme.buttonTextColor);
		AppTheme.styleButton(applyFilterButton, AppTheme.successColor, AppTheme.buttonTextColor);
		blockBox.setBackground(AppTheme.backgroundColor); // Light Gray background
		blockBox.setForeground(AppTheme.primaryColor); // Blue text
		blockBox.setFont(AppTheme.buttonFont); // Consistent font

		typeBox.setBackground(AppTheme.backgroundColor); // Light Gray background
		typeBox.setForeground(AppTheme.primaryColor); // Blue text
		typeBox.setFont(AppTheme.buttonFont); 
		patientListText.setFont(AppTheme.titleFont);
		patientListText.setForeground(AppTheme.primaryColor);
		filterBlockText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		filterTypeText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setShowGrid(true);
        table.setGridColor(Color.GRAY);
        table.setRowHeight(25);
        table.setFont(AppTheme.tableFont);
        table.getTableHeader().setFont(AppTheme.tableHeader);
        table.getTableHeader().setBackground(AppTheme.primaryColor);
        table.getTableHeader().setForeground(Color.WHITE);
		// setting bounds
		addPatientButton.setBounds(32, 124, AppTheme.buttonWidth, AppTheme.buttonHeight);
		editHospitalInfoButton.setBounds(30, 60, AppTheme.buttonWidth, AppTheme.buttonHeight);
		exitButton.setBounds(32, 488, AppTheme.buttonWidth, AppTheme.buttonHeight);
		scrollPane.setBounds(266, 60, 630, 400);
		patientListText.setBounds(266, 11, 130, 38);
		applyFilterButton.setBounds(906, 252,  AppTheme.buttonWidth, AppTheme.buttonHeight);
		filterBlockText.setBounds(916, 78, 130, 38);
		filterTypeText.setBounds(916, 161, 130, 38);
		blockBox.setBounds(906, 112, 181, 30);
		typeBox.setBounds(906, 193, 181, 30);
		// adding the components
		//getContentPane().add(panel);
		getContentPane().add(exitButton);
		getContentPane().add(editHospitalInfoButton);
		getContentPane().add(addPatientButton);
		getContentPane().add(scrollPane);
		getContentPane().add(patientListText);
		getContentPane().add(applyFilterButton);
		getContentPane().add(filterBlockText);
		getContentPane().add(filterTypeText);
		getContentPane().add(blockBox);
		getContentPane().add(typeBox);
		
	}
}
