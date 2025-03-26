package hospital.classes;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class HospitalRecordManagerFrame extends JFrame {
    private static Hospital hospital;
    private static List<patient> patientList = new ArrayList<patient>();
    private static List<patient> filteredList = new ArrayList<patient>();
	private static final long serialVersionUID = 1L;
	private JButton editHospitalInfoButton;
    private JButton addPatientButton;
    private JButton exitButton;
    private JTable table;
    private static DefaultTableModel tableModel;
    static JLabel lengthTableText;
    JScrollPane scrollPane;
    JLabel patientListText;
  //  private JButton applyFilterButton;
    private JLabel filterBlockText;
    private JLabel filterTypeText;
    static JComboBox<Object> blockBox;
    static JComboBox<Object> typeBox;
    private static JTextField searchField;
    private JLabel searchLabel;
    public static void setHospital(Hospital newHospital) {
        hospital = newHospital;
    }
    
    public static Hospital getHospital() {
        return hospital;
    }
    
    public static void addPatient(patient newPatient) {
        Hospital hospital = getHospital();
        if(hospital.getMaxPatients() <= patientList.size()) {
            JOptionPane.showMessageDialog(null, "the hospital is full", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        patientList.add(newPatient);
        lengthTableText.setText(patientList.size() + "/" + hospital.getMaxPatients());
        lengthTableText.repaint(); 
        applyFilters();
    }
    public static patient getPatient(int index) {
    	return patientList.get(index);
    }
    public static boolean checkTheTypeOfPatient(patient patient) {
        String selectedItem = typeBox.getSelectedItem().toString();
        if("All Types".equals(selectedItem)) return true;
        if("Emergency".equals(selectedItem)) {
            return patient instanceof EmergencyPatient;
        }
        else if("Outpatient".equals(selectedItem)) {
            return patient instanceof OutPatient;
        }
        else {
            return patient instanceof Inpatient;
        }
    }
    public static void displayPatients() {
    	tableModel.setRowCount(0);
    		for(patient patient :  filteredList) {
    			 Object[] rowData = {patient.getName(), patient.getAge(), patient.getGender(),patient.getLocation().getFullLocation()};
    	         tableModel.addRow(rowData);
    		}
    }
    private static  void applyFilters() {
        String selectedBlock = blockBox.getSelectedItem().toString();
        String selectedType = typeBox.getSelectedItem().toString();
        String searchText = searchField.getText().toLowerCase();
        
        filteredList = new ArrayList<>(patientList);

        if(!"All Blocks".equals(selectedBlock)) {
            filteredList.removeIf(t -> !t.getLocation().getBlockName().equals(selectedBlock));
        }

        if(!"All Types".equals(selectedType)) {
            filteredList.removeIf(t -> {
                if("Emergency".equals(selectedType)) {
                    return !(t instanceof EmergencyPatient);
                } else if("Outpatient".equals(selectedType)) {
                    return !(t instanceof OutPatient);
                } else {
                    return !(t instanceof Inpatient);
                }
            });
        }

        if(!searchText.isEmpty()) {
            filteredList.removeIf(t -> !t.getName().toLowerCase().contains(searchText));
        }
        
        displayPatients();
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
		String[] typeModel = {"All Types","Inpatient", "Outpatient", "Emergency"};
		List<String> blockModel = new ArrayList<String>();
		blockModel.add("All Blocks");
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
		//applyFilterButton = new JButton("Apply Filter");
		//applyFilterButton.addActionListener(e ->applyFilterAction(e));
		patientListText = new JLabel("Patient List");
		String[] columns = {"Name", "age", "gender", "location"};
        tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);
		;
	    scrollPane = new JScrollPane(table);
		filterBlockText = new JLabel("filter by block");
		filterTypeText = new JLabel("filter by type");
		blockBox = new JComboBox<Object>();
		blockBox.setModel(new DefaultComboBoxModel<>(blockModel.toArray(new String[0])));
		blockBox.setSelectedItem("All");
		typeBox = new JComboBox<Object>();
		typeBox.setModel(new DefaultComboBoxModel<Object>(typeModel));
		typeBox.setSelectedIndex(0);
		blockBox.addActionListener(e -> applyFilters());
		typeBox.addActionListener(e -> applyFilters());
		JLabel hospitalInfoLabel = new JLabel();
		searchLabel = new JLabel("Search by Name:");
		searchField = new JTextField();
		searchField.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        applyFilters();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        applyFilters();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        applyFilters();
		    }
		});
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
		//AppTheme.styleButton(applyFilterButton, AppTheme.successColor, AppTheme.buttonTextColor);
		blockBox.setBackground(AppTheme.backgroundColor); 
		blockBox.setForeground(AppTheme.primaryColor); 
		blockBox.setFont(AppTheme.buttonFont); 
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchField.setFont(AppTheme.tableFont);

		typeBox.setBackground(AppTheme.backgroundColor); 
		typeBox.setForeground(AppTheme.primaryColor); 
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
		//applyFilterButton.setBounds(906, 289,  AppTheme.buttonWidth, AppTheme.buttonHeight);
		filterBlockText.setBounds(919, 213, 130, 38);
		filterTypeText.setBounds(919, 296, 130, 38);
		blockBox.setBounds(909, 247, 181, 30);
		typeBox.setBounds(909, 328, 181, 30);
		searchLabel.setBounds(921, 141, 130, 20);
		searchField.setBounds(908, 172, 182, 30);
		// adding the components
		getContentPane().add(exitButton);
		getContentPane().add(editHospitalInfoButton);
		getContentPane().add(addPatientButton);
		getContentPane().add(scrollPane);
		getContentPane().add(patientListText);
		//getContentPane().add(applyFilterButton);
		getContentPane().add(filterBlockText);
		getContentPane().add(filterTypeText);
		getContentPane().add(blockBox);
		getContentPane().add(typeBox);
		getContentPane().add(hospitalInfoLabel);
		getContentPane().add(searchLabel);
		getContentPane().add(searchField);
		if(hospital != null) {
		lengthTableText = new JLabel(patientList.size() +"/"+ hospital.getMaxPatients());
		lengthTableText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lengthTableText.setBounds(849, 520, 130, 38);
		getContentPane().add(lengthTableText);
		}
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
