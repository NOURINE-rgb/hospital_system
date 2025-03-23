package hospital.gui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewPatient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField patientNameField;
    private JTextField ageField;
    private JComboBox<String> blockLocationComboBox;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton emergencyRadioButton;
    private JRadioButton inPatientRadioButton;
    private JRadioButton outPatientRadioButton;
    private JButton addButton;

    public NewPatient() {
        setTitle("Add New Patient");
        setSize(600, 439);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        getContentPane().setLayout(null);
        getContentPane().setBackground(AppTheme.backgroundColor);

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
        patientNameField.setBounds(200, 50, 200, 30);
        patientNameField.setFont(AppTheme.buttonFont);

        ageField = new JTextField();
        ageField.setBounds(200, 100, 200, 30);
        ageField.setFont(AppTheme.buttonFont);

        // Radio Buttons for Gender
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(200, 150, 100, 30);
        maleRadioButton.setFont(AppTheme.buttonFont);
        maleRadioButton.setBackground(AppTheme.backgroundColor);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(300, 150, 100, 30);
        femaleRadioButton.setFont(AppTheme.buttonFont);
        femaleRadioButton.setBackground(AppTheme.backgroundColor);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // ComboBox for Block Location
        blockLocationComboBox = new JComboBox<>(new String[]{"Item 1", "Item 2", "Item 3"});
        blockLocationComboBox.setBounds(200, 200, 200, 30);
        blockLocationComboBox.setFont(AppTheme.buttonFont);
        blockLocationComboBox.setBackground(AppTheme.backgroundColor);
        blockLocationComboBox.setForeground(AppTheme.primaryColor);

        // Radio Buttons for Patient Type
        emergencyRadioButton = new JRadioButton("Emergency Patient");
        emergencyRadioButton.setBounds(200, 250, 171, 30);
        emergencyRadioButton.setFont(AppTheme.buttonFont);
        emergencyRadioButton.setBackground(AppTheme.backgroundColor);

        inPatientRadioButton = new JRadioButton("In-patient");
        inPatientRadioButton.setBounds(200, 280, 150, 30);
        inPatientRadioButton.setFont(AppTheme.buttonFont);
        inPatientRadioButton.setBackground(AppTheme.backgroundColor);

        outPatientRadioButton = new JRadioButton("Out-patient");
        outPatientRadioButton.setBounds(200, 310, 150, 30);
        outPatientRadioButton.setFont(AppTheme.buttonFont);
        outPatientRadioButton.setBackground(AppTheme.backgroundColor);

        ButtonGroup patientTypeGroup = new ButtonGroup();
        patientTypeGroup.add(emergencyRadioButton);
        patientTypeGroup.add(inPatientRadioButton);
        patientTypeGroup.add(outPatientRadioButton);

        // Add Button
        addButton = new JButton("Add");
        addButton.setBounds(200, 350, 200, 30);
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
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPatient frame = new NewPatient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
