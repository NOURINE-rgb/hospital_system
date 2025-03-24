package hospital.classes;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class HospitalRecordManagerFrame extends JFrame {
    private static Hospital hospital;
    private static List<patient> patientList = new ArrayList<patient>();
	private static final long serialVersionUID = 1L;
	private JButton editHospitalInfoButton;
    private JButton addPatientButton;
    private JButton exitButton;
    private JTable table;
    private static DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JLabel patientListText;
    private JButton applyFilterButton;
    private JLabel filterBlockText;
    private JLabel filterTypeText;
    JComboBox<Object> blockBox;
    JComboBox<Object> typeBox;
    public static void setHospital(Hospital newHospital) {
        hospital = newHospital;
    }
    
    public static Hospital getHospital() {
        return hospital;
    }
    public static void addPatient(patient newPatient) {
    	patientList.add(newPatient);
    }
    public static patient getPatient(int index) {
    	return patientList.get(index);
    }
    public static void displayPatients() {
    	tableModel.setRowCount(0);
    		for(patient patient :  patientList) {
    			 Object[] rowData = {patient.getName(), patient.getAge(), patient.getGender(),patient.getLocation().getFullLocation()};
    	         tableModel.addRow(rowData);
    		}
    }
    private void applyFilterAction(ActionEvent evt) {

        /*StringBuilder errorMessage = new StringBuilder();
        if(hospital == null) {
        	errorMessage.append("add info of hospital and save it first.\n");
        }else {
        int numberOfBlocks =  hospital.getBlocks().size();
        if(numberOfBlocks < 2) {
        	errorMessage.append("at least add two blocks.\n");
        }
        }
        if(errorMessage.length()>0) {
        	JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }*/
    }
    private void editHospitalInfo(ActionEvent evt) {
    	this.dispose();
        new HospitalInformationFrame().setVisible(true);
    }
    private void addPatientAction(ActionEvent evt) {
    	Hospital hospital = HospitalRecordManagerFrame.getHospital();
    	if(hospital != null) {
        new NewPatientFrame().setVisible(true);
    	}else {
    		JOptionPane.showMessageDialog(this, "you don't have a hospital", "Validation Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    private void returnAction(ActionEvent evt) {
        this.dispose();
        new HospitalInformationFrame().setVisible(true);
    }
	public HospitalRecordManagerFrame(){
		String[] typeModel = {"All","Inpatient", "Outpatient", "Emergency patient"};
		List<String> blockModel = new ArrayList<String>();
		blockModel.add("All");
		if(hospital != null) {
			for(hospitalBlock block : hospital.getBlocks()) {
				blockModel.add(block.getBlockName());
			}
		}
		setTitle("Hospital Record Manager");
		setSize(1130, 627);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null); 
        getContentPane().setBackground(AppTheme.backgroundColor);
		//JPanel panel = new JPanel();
		editHospitalInfoButton = new JButton("Edit Hospital information");
		editHospitalInfoButton.addActionListener(e ->editHospitalInfo(e));
		addPatientButton = new JButton("Add Patient");
		addPatientButton.addActionListener(e -> addPatientAction(e));
		exitButton = new JButton("return");
		exitButton.addActionListener(e ->returnAction(e));
		applyFilterButton = new JButton("Apply Filter");
		applyFilterButton.addActionListener(e ->applyFilterAction(e));
		patientListText = new JLabel("Patient List");
		String[] columns = {"Name", "age", "gender", "location"};
        tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);
	    scrollPane = new JScrollPane(table);
		filterBlockText = new JLabel("filter by block");
		filterTypeText = new JLabel("filter by type");
		blockBox = new JComboBox<Object>();
		blockBox.setModel(new DefaultComboBoxModel<>(blockModel.toArray(new String[0])));
		blockBox.setSelectedItem("All");
		typeBox = new JComboBox<Object>();
		typeBox.setModel(new DefaultComboBoxModel<Object>(typeModel));
		typeBox.setSelectedIndex(0);
		JLabel hospitalInfoLabel = new JLabel();
		if (hospital != null) {
			hospitalInfoLabel.setText("Hospital: " + hospital.getName() + 
					", Max Patients: " + hospital.getMaxPatients() + 
					", State: " + hospital.getState());
		}

		// fonts
		hospitalInfoLabel.setFont(AppTheme.titleFont);
		hospitalInfoLabel.setForeground(AppTheme.primaryColor);
		AppTheme.styleButton(addPatientButton, AppTheme.successColor, AppTheme.buttonTextColor);
		AppTheme.styleButton(exitButton, AppTheme.errorColor, AppTheme.buttonTextColor);
		AppTheme.styleButton(editHospitalInfoButton, AppTheme.successColor, AppTheme.buttonTextColor);
		AppTheme.styleButton(applyFilterButton, AppTheme.successColor, AppTheme.buttonTextColor);
		blockBox.setBackground(AppTheme.backgroundColor); // Light Gray background
		blockBox.setForeground(AppTheme.primaryColor); // Blue text
		blockBox.setFont(AppTheme.buttonFont); // Consistent font

		typeBox.setBackground(AppTheme.backgroundColor); // Light Gray background
		typeBox.setForeground(AppTheme.primaryColor); // Blue text
		typeBox.setFont(AppTheme.buttonFont); 
		patientListText.setFont(AppTheme.titleFont);
		//patientListText.setForeground(AppTheme.primaryColor);
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
		addPatientButton.setBounds(32, 77, AppTheme.buttonWidth, AppTheme.buttonHeight);
		editHospitalInfoButton.setBounds(32, 19, AppTheme.buttonWidth, AppTheme.buttonHeight);
		exitButton.setBounds(32, 484, AppTheme.buttonWidth, AppTheme.buttonHeight);
		hospitalInfoLabel.setBounds(266, 29, 640, 30);
		scrollPane.setBounds(266, 124, 630, 400);
		patientListText.setBounds(266, 75, 130, 38);
		applyFilterButton.setBounds(906, 289,  AppTheme.buttonWidth, AppTheme.buttonHeight);
		filterBlockText.setBounds(916, 124, 130, 38);
		filterTypeText.setBounds(916, 207, 130, 38);
		blockBox.setBounds(906, 158, 181, 30);
		typeBox.setBounds(906, 239, 181, 30);
		// adding the components
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
		getContentPane().add(hospitalInfoLabel);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalRecordManagerFrame frame = new HospitalRecordManagerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
