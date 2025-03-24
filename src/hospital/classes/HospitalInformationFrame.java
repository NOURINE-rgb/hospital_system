package hospital.classes;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

public class HospitalInformationFrame extends JFrame {

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
    String[] columns = {"Name", "Floor N°", "Speciality"};
    private DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
    JScrollPane scrollPane;
    //methods
    private void saveHospitalAction(ActionEvent evt) {
    	String hospitalName = hospitalNameField.getText().trim();
        int maxPatients = Integer.parseInt(maxPatientsComboBox.getSelectedItem().toString());
        String state = stateField.getText().trim();
        StringBuilder errorMessage = new StringBuilder();
        if(hospitalName.isEmpty()) {
        	errorMessage.append("Hospital Name can't be empty.\n");
        }else if(hospitalName.length() < 3) {
        	errorMessage.append("Hospital Name must be at least 3 characters long.\n");
        }
        if(state.isEmpty()) {
        	errorMessage.append("State can't be empty.\n");
        }
        if(errorMessage.length()>0) {
        	JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Hospital hospital = new Hospital(hospitalName,maxPatients,state);
        HospitalRecordManagerFrame.setHospital(hospital);
        JOptionPane.showMessageDialog(this, "Hospital saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    private void seeListPatientAction(ActionEvent evt) {
    	Hospital hospital = HospitalRecordManagerFrame.getHospital();
        StringBuilder errorMessage = new StringBuilder();
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
        }
        this.dispose();
        new HospitalRecordManagerFrame().setVisible(true);
    }
    private void addBlockAction(ActionEvent evt) {
    	String blockName = blockNameField.getText().trim();
        int floorNumber = floorNumberComboBox.getSelectedIndex() + 1;
        String speciality = specialityField.getText().trim();
        StringBuilder errorMessage = new StringBuilder();
        if(blockName.isEmpty()) {
        	errorMessage.append("Block Name can't be empty.\n");
        }else if(blockName.length() < 3) {
        	errorMessage.append("Block Name must be at least 3 characters long.\n");
        }
        if(speciality.isEmpty()) {
        	errorMessage.append("Speciality can't be empty.\n");
        }
        if(errorMessage.length()>0) {
        	JOptionPane.showMessageDialog(this, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Hospital hospital = HospitalRecordManagerFrame.getHospital();
        hospitalBlock hospitalBlock = new hospitalBlock(blockName,floorNumber,speciality);
        if(hospital != null) {
        	hospital.addBlock(hospitalBlock);
        }else {
        	JOptionPane.showMessageDialog(this, "Hospital information is not saved yet. Please save the hospital first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // for refresh the table
       displayBlocks(); 
       JOptionPane.showMessageDialog(this, "Block added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
       blockNameField.setText("");
       specialityField.setText("");
       floorNumberComboBox.setSelectedIndex(0);
    }
    private void exitAction(ActionEvent evt) {    	
    	int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose(); // Close the HospitalInformation window
        }
    }
    private void displayBlocks() {
    	tableModel.setRowCount(0);
    	Hospital hospital = HospitalRecordManagerFrame.getHospital();
    	if(hospital != null) {
    		for(hospitalBlock block :  hospital.getBlocks()) {
    			 Object[] rowData = {block.getBlockName(), block.getFloorNumber(), block.getSpecialty()};
    	         tableModel.addRow(rowData);
    		}
    	}
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalInformationFrame frame = new HospitalInformationFrame();
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
	public HospitalInformationFrame() {
		setTitle("Hospital Information");
        setSize(962, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(AppTheme.backgroundColor);

        // Labels
        JLabel hospitalNameLabel = new JLabel("Hospital Name:");
        hospitalNameLabel.setBounds(10, 48, 150, 30);
        hospitalNameLabel.setFont(AppTheme.buttonFont);
        hospitalNameLabel.setForeground(AppTheme.primaryColor);

        JLabel maxPatientsLabel = new JLabel("Max Patients:");
        maxPatientsLabel.setBounds(10, 98, 150, 30);
        maxPatientsLabel.setFont(AppTheme.buttonFont);
        maxPatientsLabel.setForeground(AppTheme.primaryColor);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(10, 148, 150, 30);
        stateLabel.setFont(AppTheme.buttonFont);
        stateLabel.setForeground(AppTheme.primaryColor);

        JLabel blockNameLabel = new JLabel("Block Name:");
        blockNameLabel.setBounds(474, 60, 150, 30);
        blockNameLabel.setFont(AppTheme.buttonFont);
        blockNameLabel.setForeground(AppTheme.primaryColor);

        JLabel floorNumberLabel = new JLabel("Floor Number:");
        floorNumberLabel.setBounds(474, 110, 150, 30);
        floorNumberLabel.setFont(AppTheme.buttonFont);
        floorNumberLabel.setForeground(AppTheme.primaryColor);

        JLabel specialityLabel = new JLabel("Speciality:");
        specialityLabel.setBounds(474, 160, 150, 30);
        specialityLabel.setFont(AppTheme.buttonFont);
        specialityLabel.setForeground(AppTheme.primaryColor);
        
        JLabel createNewBlockText = new JLabel("Create New block");
        createNewBlockText.setBounds(474, 11, 177, 38);
        createNewBlockText.setFont(AppTheme.titleFont);
		
		JLabel allBlockText = new JLabel("All Blocks");
		allBlockText.setFont(new Font("Arial", Font.BOLD, 20));
		allBlockText.setBounds(10, 213, 126, 38);
        // Text Fields
        hospitalNameField = new JTextField();
        hospitalNameField.setBounds(160, 48, 200, 30);
        hospitalNameField.setFont(AppTheme.buttonFont);

        stateField = new JTextField();
        stateField.setBounds(160, 149, 200, 30);
        stateField.setFont(AppTheme.buttonFont);

        blockNameField = new JTextField();
        blockNameField.setBounds(624, 60, 200, 30);
        blockNameField.setFont(AppTheme.buttonFont);

        specialityField = new JTextField();
        specialityField.setBounds(624, 161, 200, 30);
        specialityField.setFont(AppTheme.buttonFont);

        // ComboBoxes
        maxPatientsComboBox = new JComboBox<>();
        maxPatientsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"50", "100", "200", "300", "400", "500"}));
        maxPatientsComboBox.setBounds(160, 98, 200, 30);
        maxPatientsComboBox.setFont(AppTheme.buttonFont);
        maxPatientsComboBox.setBackground(AppTheme.backgroundColor);
        maxPatientsComboBox.setForeground(AppTheme.primaryColor);

        floorNumberComboBox = new JComboBox<>();
        floorNumberComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"first", "second ", "third", "fourth"}));
        floorNumberComboBox.setBounds(624, 110, 200, 30);
        floorNumberComboBox.setFont(AppTheme.buttonFont);
        floorNumberComboBox.setBackground(AppTheme.backgroundColor);
        floorNumberComboBox.setForeground(AppTheme.primaryColor);
        
        // buttons
        addBlockButton = new JButton("Add Block");
        addBlockButton.setBounds(514, 201, AppTheme.buttonWidth, AppTheme.buttonHeight);
        addBlockButton.addActionListener(e -> addBlockAction(e));
        AppTheme.styleButton(addBlockButton, AppTheme.successColor, AppTheme.buttonTextColor);

        saveHospitalButton = new JButton("Save Hospital");
        saveHospitalButton.setBounds(10, 512,AppTheme.buttonWidth, AppTheme.buttonHeight);
        saveHospitalButton.addActionListener(e -> saveHospitalAction(e));
        AppTheme.styleButton(saveHospitalButton, AppTheme.primaryColor, AppTheme.buttonTextColor);

        JButton seeListPatientsButton = new JButton("See List Patients");
        seeListPatientsButton.setBounds(738, 512,AppTheme.buttonWidth,AppTheme.buttonHeight);
        seeListPatientsButton.addActionListener(e ->seeListPatientAction(e));
        AppTheme.styleButton(seeListPatientsButton, AppTheme.buttonColor, AppTheme.buttonTextColor);
        
        exitButton = new JButton("Exit");
        exitButton.setBounds(230, 512,AppTheme.buttonWidth, AppTheme.buttonHeight);
        exitButton.addActionListener(e ->exitAction(e) );
        AppTheme.styleButton(exitButton, AppTheme.errorColor, AppTheme.buttonTextColor);
        // table
		table = new JTable(tableModel);
	    scrollPane = new JScrollPane(table);
	    table.setShowGrid(true);
        table.setGridColor(Color.GRAY);
        table.setRowHeight(25);
        table.setFont(AppTheme.tableFont);
        table.getTableHeader().setFont(AppTheme.tableHeader);
        table.getTableHeader().setBackground(AppTheme.primaryColor);
        table.getTableHeader().setForeground(Color.WHITE);
        scrollPane.setBounds(10, 251, 445, 242);
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
        getContentPane().add(seeListPatientsButton);
        


		
	}
}
