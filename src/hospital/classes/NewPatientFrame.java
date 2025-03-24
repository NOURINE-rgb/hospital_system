package hospital.classes;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class NewPatientFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField patientNameField;
    private JTextField ageField;
    private JComboBox<String> blockLocationComboBox;
    private JRadioButton maleRadioButton;
    JLabel lblAdditionalFieldsrequired;
    private JRadioButton femaleRadioButton;
    private JRadioButton emergencyRadioButton;
    private JRadioButton inPatientRadioButton;
    private JRadioButton outPatientRadioButton;
    private JButton addButton;
    private List<hospitalBlock> blocks;
    // panels for each type patient
    private JPanel inPatientPanel;
    private JPanel outPatientPanel;
    private JPanel emergencyPatientPanel;

    // In-patient fields
    private JTextField roomNumberField;
    private JTextField admissionDateField;
    private JTextField dailyChargeField;
    private JTextField numberOfDaysAdmittedField;

    // Out-patient fields
    private JTextField appointmentDateField;
    private JTextField consultationFeeField;

    // Emergency Patient fields
    private JTextField severityLevelField;
    private JTextField emergencyTreatmentCostField;
    //methods 
    private void addAction(ActionEvent evt) {
    	    StringBuilder errorMessage = new StringBuilder();
    	    
    	    // Validate name
    	    String name = patientNameField.getText().trim();
    	    if(name.length() < 3) {
    	        errorMessage.append("Enter a valid name (minimum 3 characters).\n");
    	    }

    	    // Validate age with try-catch
    	    int age = 0;
    	    try {
    	        age = Integer.parseInt(ageField.getText().trim());
    	        if(age <= 0) {
    	            errorMessage.append("Enter a valid positive age.\n");
    	        }
    	    } catch (NumberFormatException e) {
    	        errorMessage.append("Age must be a whole number.\n");
    	    }

    	    // Validate gender
    	    String gender = "";
    	    if(maleRadioButton.isSelected() || femaleRadioButton.isSelected()) {
    	        gender = maleRadioButton.isSelected() ? "Male" : "Female";
    	    } else {
    	        errorMessage.append("Select a gender.\n");
    	    }

    	    // Validate hospital blocks
    	    hospitalBlock block = null;
    	    if(HospitalRecordManagerFrame.getHospital() != null) {
    	        if(blockLocationComboBox.getSelectedIndex() >= 0) {
    	            block = blocks.get(blockLocationComboBox.getSelectedIndex());
    	        } else {
    	            errorMessage.append("Select a block location.\n");
    	        }
    	    } else {
    	        errorMessage.append("No hospital blocks available. Add hospital and blocks first.\n");
    	    }

    	    // Validate patient type specific fields
    	    if (emergencyRadioButton.isSelected()) {
    	        String severityLevel = severityLevelField.getText().trim();
    	        if(severityLevel.isEmpty()) {
    	            errorMessage.append("Severity level is required.\n");
    	        }

    	        double emergencyTreatmentCost = 0;
    	        try {
    	            emergencyTreatmentCost = Double.parseDouble(emergencyTreatmentCostField.getText().trim());
    	            if(emergencyTreatmentCost <= 0) {
    	                errorMessage.append("Enter a valid positive treatment cost.\n");
    	            }
    	        } catch (NumberFormatException e) {
    	            errorMessage.append("Treatment cost must be a number.\n");
    	        }

    	    } else if (inPatientRadioButton.isSelected()) {
    	        String roomNumber = roomNumberField.getText().trim();
    	        if(roomNumber.isEmpty()) {
    	            errorMessage.append("Room number is required.\n");
    	        }

    	        String admissionDate = admissionDateField.getText().trim();
    	        if(admissionDate.isEmpty()) {
    	            errorMessage.append("Admission date is required.\n");
    	        }

    	        double dailyCharge = 0;
    	        try {
    	            dailyCharge = Double.parseDouble(dailyChargeField.getText().trim());
    	            if(dailyCharge <= 0) {
    	                errorMessage.append("Enter a valid positive daily charge.\n");
    	            }
    	        } catch (NumberFormatException e) {
    	            errorMessage.append("Daily charge must be a number.\n");
    	        }

    	        int numberOfDaysAdmitted = 0;
    	        try {
    	            numberOfDaysAdmitted = Integer.parseInt(numberOfDaysAdmittedField.getText().trim());
    	            if(numberOfDaysAdmitted <= 0) {
    	                errorMessage.append("Enter a valid positive number of days.\n");
    	            }
    	        } catch (NumberFormatException e) {
    	            errorMessage.append("Number of days must be a whole number.\n");
    	        }

    	    } else if (outPatientRadioButton.isSelected()) {
    	        String appointmentDate = appointmentDateField.getText().trim();
    	        if(appointmentDate.isEmpty()) {
    	            errorMessage.append("Appointment date is required.\n");
    	        }

    	        double consultationFee = 0;
    	        try {
    	            consultationFee = Double.parseDouble(consultationFeeField.getText().trim());
    	            if(consultationFee <= 0) {
    	                errorMessage.append("Enter a valid positive consultation fee.\n");
    	            }
    	        } catch (NumberFormatException e) {
    	            errorMessage.append("Consultation fee must be a number.\n");
    	        }

    	    } else {
    	        errorMessage.append("Select a patient type.\n");
    	    }

    	    // Show all errors if any
    	    if(errorMessage.length() > 0) {
    	        JOptionPane.showMessageDialog(this, errorMessage.toString(), 
    	            "Validation Error", JOptionPane.ERROR_MESSAGE);
    	        return;
    	    }

    	    // Create patient object based on type
    	    if (emergencyRadioButton.isSelected()) {
    	        EmergencyPatient patient = new EmergencyPatient(name, age, gender, block, 
    	            severityLevelField.getText().trim(), 
    	            Double.parseDouble(emergencyTreatmentCostField.getText().trim()));
    	        HospitalRecordManagerFrame.addPatient(patient);
    	    } else if (inPatientRadioButton.isSelected()) {
    	        Inpatient patient = new Inpatient(name, age, gender, block, 
    	            roomNumberField.getText().trim(),
    	            admissionDateField.getText().trim(),
    	            Double.parseDouble(dailyChargeField.getText().trim()),
    	            Integer.parseInt(numberOfDaysAdmittedField.getText().trim()));
    	        HospitalRecordManagerFrame.addPatient(patient);
    	    } else if (outPatientRadioButton.isSelected()) {
    	        OutPatient patient = new OutPatient(name, age, gender, block, 
    	            appointmentDateField.getText().trim(),
    	            Double.parseDouble(consultationFeeField.getText().trim()));
    	        HospitalRecordManagerFrame.addPatient(patient);
    	    }

    	    HospitalRecordManagerFrame.displayPatients();
    	    this.dispose();
    }
    private void showInPatientFields() {
    	lblAdditionalFieldsrequired.setVisible(true);
        inPatientPanel.setVisible(true);
        outPatientPanel.setVisible(false);
        emergencyPatientPanel.setVisible(false);
    }

    private void showOutPatientFields() {
    	outPatientPanel.setVisible(true);
    	lblAdditionalFieldsrequired.setVisible(true);
        inPatientPanel.setVisible(false);
        emergencyPatientPanel.setVisible(false);
    }

    private void showEmergencyPatientFields() {
    	lblAdditionalFieldsrequired.setVisible(true);
    	emergencyPatientPanel.setVisible(true);
        inPatientPanel.setVisible(false);
        outPatientPanel.setVisible(false);
    }
    public NewPatientFrame() {
    	Hospital hospital = HospitalRecordManagerFrame.getHospital();
    	List<String> locationModel = new ArrayList<String>();
    	blocks = new ArrayList<hospitalBlock>();
		if(hospital != null) {
			for(hospitalBlock block : hospital.getBlocks()) {
			    locationModel.add(block.getFullLocation());
			    blocks.add(block);
			}
		}
		
        setTitle("Add New Patient");
        setSize(430, 581);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(AppTheme.backgroundColor);
        // Initialise inPatient type panel
        inPatientPanel = new JPanel();
        inPatientPanel.setBounds(50, 392, 350, 100);
        inPatientPanel.setLayout(null);
        inPatientPanel.setBackground(AppTheme.backgroundColor);
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(0, 10, 150, 25);
        roomNumberField = new JTextField();
        roomNumberField.setBounds(120, 10, 200, 25);

        JLabel admissionDateLabel = new JLabel("Admission Date:");
        admissionDateLabel.setBounds(0, 40, 150, 25);
        admissionDateField = new JTextField();
        admissionDateField.setText("2025/01/01");
        admissionDateField.setBounds(120, 40, 200, 25);
        JLabel dailyChargeLabel = new JLabel("Daily Charge:");
        dailyChargeLabel.setBounds(0, 71, 150, 25);
        dailyChargeField = new JTextField();
        dailyChargeField.setBounds(120, 70, 200, 25);

        JLabel numberOfDaysAdmittedLabel = new JLabel("Days Admitted:");
        numberOfDaysAdmittedLabel.setBounds(0, 100, 150, 25);
        numberOfDaysAdmittedField = new JTextField();
        numberOfDaysAdmittedField.setBounds(120, 100, 200, 25);

        inPatientPanel.add(roomNumberLabel);
        inPatientPanel.add(roomNumberField);
        inPatientPanel.add(admissionDateLabel);
        inPatientPanel.add(admissionDateField);
        inPatientPanel.add(dailyChargeField);
        inPatientPanel.add(numberOfDaysAdmittedLabel);
        inPatientPanel.add(numberOfDaysAdmittedField);
        inPatientPanel.add(dailyChargeLabel);
        inPatientPanel.setVisible(false);
         // Initialise Out-patient fields
        outPatientPanel = new JPanel();
        outPatientPanel.setBounds(50, 392, 350, 100);
        outPatientPanel.setLayout(null);
        outPatientPanel.setBackground(AppTheme.backgroundColor);
        JLabel appointmentDateLabel = new JLabel("Appointment Date:");
        appointmentDateLabel.setBounds(0, 10, 150, 25);
        appointmentDateField = new JTextField();
        appointmentDateField.setText("2025/01/01");
        appointmentDateField.setBounds(120, 10, 200, 25);
                        
        JLabel consultationFeeLabel = new JLabel("Consultation Fee:");
        consultationFeeLabel.setBounds(0, 40, 150, 25);
        consultationFeeField = new JTextField();
        consultationFeeField.setBounds(120, 40, 200, 25);
        outPatientPanel.add(appointmentDateLabel);
        outPatientPanel.add(appointmentDateField);
        outPatientPanel.add(consultationFeeLabel);
        outPatientPanel.add(consultationFeeField);
        outPatientPanel.setVisible(false);
                                        
        // Initialise Emergency Patient fields
        emergencyPatientPanel = new JPanel();
        emergencyPatientPanel.setBounds(50, 392, 350, 100);
        emergencyPatientPanel.setLayout(null);
        emergencyPatientPanel.setBackground(AppTheme.backgroundColor);
        JLabel severityLevelLabel = new JLabel("Severity Level:");
        severityLevelLabel.setBounds(0, 10, 150, 25);
        severityLevelField = new JTextField();
        severityLevelField.setBounds(120, 10, 200, 25);
                                        
        JLabel emergencyTreatmentCostLabel = new JLabel("Treatment Cost:");
        emergencyTreatmentCostLabel.setBounds(0, 40, 150, 25);
        emergencyTreatmentCostField = new JTextField();
        emergencyTreatmentCostField.setBounds(120, 40, 200, 25);
        emergencyPatientPanel.add(severityLevelLabel);
        emergencyPatientPanel.add(severityLevelField);
        emergencyPatientPanel.add(emergencyTreatmentCostLabel);
        emergencyPatientPanel.add(emergencyTreatmentCostField);
        emergencyPatientPanel.setVisible(false);
        // Radio Buttons for Patient Type
        emergencyRadioButton = new JRadioButton("Emergency Patient");
        emergencyRadioButton.setBounds(170, 250, 171, 30);
        emergencyRadioButton.setFont(AppTheme.buttonFont);
        emergencyRadioButton.setBackground(AppTheme.backgroundColor);
        emergencyRadioButton.addActionListener(e -> showEmergencyPatientFields());
        
        inPatientRadioButton = new JRadioButton("In-patient");
        inPatientRadioButton.setBounds(170, 280, 150, 30);
        inPatientRadioButton.setFont(AppTheme.buttonFont);
        inPatientRadioButton.setBackground(AppTheme.backgroundColor);
        inPatientRadioButton.addActionListener(e -> showInPatientFields());
        
        outPatientRadioButton = new JRadioButton("Out-patient");
        outPatientRadioButton.setBounds(170, 310, 150, 30);
        outPatientRadioButton.setFont(AppTheme.buttonFont);
        outPatientRadioButton.setBackground(AppTheme.backgroundColor);
        outPatientRadioButton.addActionListener(e -> showOutPatientFields());
        // Labels
        JLabel patientNameLabel = new JLabel("Patient Name:");
        patientNameLabel.setBounds(50, 50, 150, 30);
        patientNameLabel.setFont(AppTheme.buttonFont);
        patientNameLabel.setForeground(AppTheme.primaryColor);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 100, 150, 30);
        ageLabel.setFont(AppTheme.buttonFont);
        ageLabel.setForeground(AppTheme.primaryColor);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 150, 150, 30);
        genderLabel.setFont(AppTheme.buttonFont);
        genderLabel.setForeground(AppTheme.primaryColor);

        JLabel blockLocationLabel = new JLabel("Block Location:");
        blockLocationLabel.setBounds(50, 200, 150, 30);
        blockLocationLabel.setFont(AppTheme.buttonFont);
        blockLocationLabel.setForeground(AppTheme.primaryColor);

        JLabel patientTypeLabel = new JLabel("Patient Type:");
        patientTypeLabel.setBounds(50, 250, 150, 30);
        patientTypeLabel.setFont(AppTheme.buttonFont);
        patientTypeLabel.setForeground(AppTheme.primaryColor);

        // Text Fields
        patientNameField = new JTextField();
        patientNameField.setBounds(170, 50, 200, 30);
        patientNameField.setFont(AppTheme.buttonFont);

        ageField = new JTextField();
        ageField.setBounds(170, 100, 200, 30);
        ageField.setFont(AppTheme.buttonFont);

        // Radio Buttons for Gender
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(170, 150, 100, 30);
        maleRadioButton.setFont(AppTheme.buttonFont);
        maleRadioButton.setBackground(AppTheme.backgroundColor);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(270, 150, 100, 30);
        femaleRadioButton.setFont(AppTheme.buttonFont);
        femaleRadioButton.setBackground(AppTheme.backgroundColor);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // ComboBox for Block Location
        blockLocationComboBox = new JComboBox<>(locationModel.toArray(new String[0]));
        blockLocationComboBox.setBounds(170, 200, 200, 30);
        blockLocationComboBox.setFont(AppTheme.buttonFont);
        blockLocationComboBox.setBackground(AppTheme.backgroundColor);
        blockLocationComboBox.setForeground(AppTheme.primaryColor);
        
        lblAdditionalFieldsrequired = new JLabel("Additional fields (required)");
        lblAdditionalFieldsrequired.setForeground(AppTheme.successColor);
        lblAdditionalFieldsrequired.setFont(AppTheme.buttonFont);
        lblAdditionalFieldsrequired.setBounds(50, 351, 209, 30);
        lblAdditionalFieldsrequired.setVisible(false);

        ButtonGroup patientTypeGroup = new ButtonGroup();
        patientTypeGroup.add(emergencyRadioButton);
        patientTypeGroup.add(inPatientRadioButton);
        patientTypeGroup.add(outPatientRadioButton);

        // Add Button
        addButton = new JButton("Add");
        addButton.setBounds(170, 503, 200, 30);
        addButton.addActionListener(e ->addAction(e));
        AppTheme.styleButton(addButton, AppTheme.successColor, AppTheme.buttonTextColor);

        // Adding components to the frame
        getContentPane().add(patientNameLabel);
        getContentPane().add(patientNameField);
        getContentPane().add(ageLabel);
        getContentPane().add(ageField);
        getContentPane().add(genderLabel);
        getContentPane().add(maleRadioButton);
        getContentPane().add(femaleRadioButton);
        getContentPane().add(blockLocationLabel);
        getContentPane().add(blockLocationComboBox);
        getContentPane().add(patientTypeLabel);
        getContentPane().add(emergencyRadioButton);
        getContentPane().add(inPatientRadioButton);
        getContentPane().add(outPatientRadioButton);
        getContentPane().add(addButton);
        getContentPane().add(lblAdditionalFieldsrequired);
        getContentPane().add(inPatientPanel);
        getContentPane().add(outPatientPanel);
        getContentPane().add(emergencyPatientPanel);
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPatientFrame frame = new NewPatientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
