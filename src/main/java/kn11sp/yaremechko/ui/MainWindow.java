package kn11sp.yaremechko.ui;
import kn11sp.yaremechko.derivative.Derivative;
import kn11sp.yaremechko.insurances.BaseInsurance;
import kn11sp.yaremechko.insurances.HealthInsurance;
import kn11sp.yaremechko.insurances.RealEstateInsurance;
import kn11sp.yaremechko.insurances.TransportInsurance;
import kn11sp.yaremechko.mysqlconection.MySQLUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showMessageDialog;
public class MainWindow extends JFrame  {

    private JPanel superPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    //search panel
    private JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    private JTextField searchLevelOfRiskField = new JTextField(10);
    private JTextField searchPriceField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JButton cancelSearchButton = new JButton("Cancel");
    //addEditPanel
    private JPanel addEditPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    private final String[] insuranceTypes = new String[]{"Health","Real estate", "Transport"};
    private JComboBox<String> insuranceTypeComboBox = new JComboBox<>(insuranceTypes);
    private JPanel insuranceTypeComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    private JPanel healthInsurancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    private JTextField healthTimeInYearsField = new JTextField(10);
    private JTextField healthLevelOfRiskField = new JTextField(10);
    private JTextField healthPersonNameField = new JTextField(10);
    private JPanel realEstateInsurancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    private JTextField realEstateTimeInYearsField = new JTextField(10);
    private JTextField realEstateLevelOfRiskField = new JTextField(10);
    private JTextField realEstateAreaField = new JTextField(10);
    private JTextField realEstateCityField = new JTextField(10);
    private JTextField realEstateAddressField = new JTextField(10);
    private JPanel transportInsurancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
    private JTextField transportTimeInYearsField = new JTextField(10);
    private JTextField transportLevelOfRiskField = new JTextField(10);
    private JTextField transportNameField = new JTextField(10);
    private String[] transportClasses = new String[]{"A", "B", "C", "D", "E", "F", "J", "M", "S"};
    private JComboBox<String> transportClassComboBox = new JComboBox<>(transportClasses);
    private JTextField transportNumberField = new JTextField(10);
    private JButton editButton = new JButton("Edit");
    private JButton addButton = new JButton("Save");
    private JButton cancelButton = new JButton("Cancel");
    //creating elements for derivative side of window
    private JLabel insuranceSum = new JLabel();
    private JPanel derivativeButtonPanel = new JPanel();
    private JButton addDerivativeButton = new JButton("Add derivative");
    private JButton deleteDerivativeButton = new JButton("Delete derivative");
    private JPanel derivativeBoxPanel = new JPanel();
    private JComboBox<String> derivativeComboBox = new JComboBox<>();
    //creating elements for insurance side of window
    private DefaultListModel listModel = new DefaultListModel<>();
    private JList<String> insurancesList = new JList<>(listModel);
    private JPanel insurancesScrollPane = new JPanel();
    private JPanel insuranceButtonPanel = new JPanel();
    private JButton searchInsuranceButton = new JButton("Search insurance");
    private JButton sortInsuranceButton = new JButton("Sort insurances");
    private JButton addInsuranceButton = new JButton("Add insurance");
    private JButton editInsuranceButton = new JButton("Edit insurance");
    private JButton deleteInsuranceButton = new JButton("Delete insurance");
    //creating main panel and its elements
    private JPanel allButtonPanel = new JPanel(new BorderLayout());
    private JPanel mainPanel = new JPanel();
    private ArrayList<Derivative> derivatives = new ArrayList();
    private ArrayList<ArrayList<BaseInsurance>> insurances = new ArrayList<>();
    private MySQLUtils sql = new MySQLUtils();

    public MainWindow(){
        sql.getData(derivatives, insurances);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1400,800);
        setTitle("Derivative manager");
        createAddEditPanel();

        //search panel
        createSearchPanel();
        //placing elements of derivative side of window
        derivativeButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        derivativeButtonPanel.add(addDerivativeButton);
        derivativeButtonPanel.add(deleteDerivativeButton);
        derivativeBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        updateDerivativeComboBox();
        derivativeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = derivativeComboBox.getSelectedIndex();
                updateInsuranceListBox(index);
                insuranceSum.setText(String.format("%,.2f $",derivatives.get(index).count()));
            }
        });
        derivativeBoxPanel.add(derivativeComboBox);
        derivativeBoxPanel.add(new JLabel("Total Price: "));
        derivativeBoxPanel.add(insuranceSum);

        //placing elements of insurance side of window
        insuranceButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        insuranceButtonPanel.add(searchInsuranceButton);
        insuranceButtonPanel.add(sortInsuranceButton);
        insuranceButtonPanel.add(addInsuranceButton);
        insuranceButtonPanel.add(editInsuranceButton);
        insuranceButtonPanel.add(deleteInsuranceButton);
        allButtonPanel.add(derivativeButtonPanel, BorderLayout.WEST);
        allButtonPanel.add(insuranceButtonPanel, BorderLayout.EAST);
        insurancesList.setPreferredSize(new Dimension(1000,600));
        insurancesScrollPane.setSize(400,300);
        insurancesList.setSize(400,300);
        insurancesScrollPane.add(insurancesList);

        //main panel elements placing
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(allButtonPanel, BorderLayout.PAGE_END);
        mainPanel.add(derivativeBoxPanel, BorderLayout.PAGE_START);
        mainPanel.add(insurancesScrollPane, BorderLayout.CENTER);
        superPanel.add(addEditPanel);
        superPanel.add(mainPanel);
        superPanel.add(searchPanel);
        add(superPanel);
        setVisible(true);
        //listeners
        addInsuranceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAddLayout();
            }
        });
        editInsuranceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseInsurance in = insurances.get(derivativeComboBox.getSelectedIndex()).get(insurancesList.getSelectedIndex());
                setEditLayout(in);
            }
        });
        addDerivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insurances.add(new ArrayList<BaseInsurance>());
                derivatives.add(new Derivative(derivativeComboBox.getItemCount()+1, insurances.get(insurances.size()-1)));
                updateDerivativeComboBox();

            }
        });
        sortInsuranceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = derivativeComboBox.getSelectedIndex();
                derivatives.get(index).sort();
                updateInsuranceListBox(index);
            }
        });
        deleteDerivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = derivativeComboBox.getSelectedIndex();
                derivatives.remove(index);
                sql.deleteDerivative(index+1);
                updateDerivativeComboBox();
            }
        });
        deleteInsuranceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int inIndex = insurancesList.getSelectedIndex();
               int derIndex = derivativeComboBox.getSelectedIndex();
               int dbId = insurances.get(derIndex).get(inIndex).getId();
               insurances.get(derIndex).remove(inIndex);
               sql.deleteInurance(dbId);
            }
        });
        searchInsuranceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSearchLayout();
            }
        });
        cancelSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
    }
    private void createSearchPanel () {
        searchPanel.add(new JLabel("he insurance shoud have an "));
        searchPanel.add(searchLevelOfRiskField);
        searchPanel.add(new JLabel("level of risk and less than a"));
        searchPanel.add(searchPriceField);
        searchPanel.add(new JLabel("price"));
        searchPanel.add(searchButton);
        searchPanel.add(cancelSearchButton);
        searchPanel.setVisible(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int derIndex = derivativeComboBox.getSelectedIndex();
                int searchableLevelOfRisk = Integer.parseInt(searchLevelOfRiskField.getText());
                double searchablePrice = Double.parseDouble(searchPriceField.getText());
                showMessageDialog(null,derivatives.get(derIndex).search(searchableLevelOfRisk,searchablePrice));
                mainPanel.setVisible(true);
                searchPanel.setVisible(false);
            }
        });
    }
    public void createAddEditPanel(){
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        comboBoxPanelCreate();
        setHealthInsurancePanel();
        setRealEstateInsurancePanel();
        setTransportInsurancePanel();
        setAddEditPanel();

        healthInsurancePanel.setVisible(false);
        realEstateInsurancePanel.setVisible(false);
        transportInsurancePanel.setVisible(false);
        addButton.setVisible(false);
        editButton.setVisible(false);
        addEditPanel.setVisible(false);
        insuranceTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) insuranceTypeComboBox.getSelectedItem();
                switch (item){
                    case "Health":
                        realEstateInsurancePanel.setVisible(false);
                        transportInsurancePanel.setVisible(false);
                        healthInsurancePanel.setVisible(true);
                        break;
                    case "Real estate":
                        healthInsurancePanel.setVisible(false);
                        transportInsurancePanel.setVisible(false);
                        realEstateInsurancePanel.setVisible(true);
                        break;
                    case "Transport":
                        healthInsurancePanel.setVisible(false);
                        realEstateInsurancePanel.setVisible(false);
                        transportInsurancePanel.setVisible(true);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = (String) insuranceTypeComboBox.getSelectedItem();
                switch (type){
                    case "Health":
                        insurances.get(derivativeComboBox.getSelectedIndex()).add(new HealthInsurance(0, Integer.parseInt(healthLevelOfRiskField.getText()),
                                Integer.parseInt(healthTimeInYearsField.getText()), healthPersonNameField.getText()));
                        break;
                    case "Real estate":
                        insurances.get(derivativeComboBox.getSelectedIndex()).add(new RealEstateInsurance(0,Integer.parseInt(realEstateTimeInYearsField.getText()),
                                Integer.parseInt(realEstateTimeInYearsField.getText()),
                                realEstateCityField.getText(),realEstateAddressField.getText(),
                                Double.parseDouble(realEstateAreaField.getText())));

                        break;
                    case "Transport":
                        insurances.get(derivativeComboBox.getSelectedIndex()).add(new TransportInsurance(0,Integer.parseInt(transportLevelOfRiskField.getText()),
                                Integer.parseInt(transportTimeInYearsField.getText()),
                                transportNameField.getText(), (String) transportClassComboBox.getSelectedItem(),
                                transportNumberField.getText()));
                        break;
                }

                    sql.insertData(insurances.get(derivativeComboBox.getSelectedIndex()).get(insurances.get(derivativeComboBox.getSelectedIndex()).size()-1),
                            derivativeComboBox.getSelectedIndex()+1);
                sql.getData(derivatives,insurances);
                emptyFields();
                addEditPanel.setVisible(false);
                mainPanel.setVisible(true);

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int inIndex = insurancesList.getSelectedIndex();
                int derIndex = derivativeComboBox.getSelectedIndex();
                BaseInsurance in = insurances.get(derIndex).get(inIndex);
                String inClass = in.getClass().toString();

                switch (inClass){
                    case "class kn11sp.yaremechko.insurances.HealthInsurance":
                        in.setPersonName(healthPersonNameField.getText());
                        in.setLevelOfRisk(Integer.parseInt(healthLevelOfRiskField.getText()));
                        in.setTimeInYears(Integer.parseInt(healthTimeInYearsField.getText()));
                        break;
                    case "class kn11sp.yaremechko.insurances.RealEstateInsurance":
                        in.setTimeInYears(Integer.parseInt(realEstateTimeInYearsField.getText()));
                        in.setLevelOfRisk(Integer.parseInt(realEstateLevelOfRiskField.getText()));
                        in.setArea(Double.parseDouble(realEstateAreaField.getText()));
                        in.setAddress(realEstateAddressField.getText());
                        in.setCity(realEstateCityField.getText());

                        break;
                    case "class kn11sp.yaremechko.insurances.TransportInsurance":
                        in.setTimeInYears(Integer.parseInt(transportTimeInYearsField.getText()));
                        in.setLevelOfRisk(Integer.parseInt(transportTimeInYearsField.getText()));
                        in.setTransportName(transportNameField.getText());
                        in.setTransportClass(transportClassComboBox.getSelectedItem().toString());
                        in.setTransportNumber(transportNumberField.getText());
                        break;
                }
                sql.updateData(in);
                emptyFields();
                addEditPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyFields();
                addEditPanel.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
    }
    private void comboBoxPanelCreate(){
        insuranceTypeComboBoxPanel.add(insuranceTypeComboBox);
    }
    private void setHealthInsurancePanel(){
        healthInsurancePanel.add(new JLabel("time in years:"));
        healthInsurancePanel.add(healthTimeInYearsField);
        healthInsurancePanel.add(new JLabel("level of risk:"));
        healthInsurancePanel.add(healthLevelOfRiskField);
        healthInsurancePanel.add(new JLabel("Person name:"));
        healthInsurancePanel.add(healthPersonNameField);
    }
    private void setRealEstateInsurancePanel(){
        realEstateInsurancePanel.add(new JLabel("time in years:"));
        realEstateInsurancePanel.add(realEstateTimeInYearsField);
        realEstateInsurancePanel.add(new JLabel("level of risk:"));
        realEstateInsurancePanel.add(realEstateLevelOfRiskField);
        realEstateInsurancePanel.add(new JLabel("area:"));
        realEstateInsurancePanel.add(realEstateAreaField);
        realEstateInsurancePanel.add(new JLabel("city:"));
        realEstateInsurancePanel.add(realEstateCityField);
        realEstateInsurancePanel.add(new JLabel("address:"));
        realEstateInsurancePanel.add(realEstateAddressField);
    }
    private void setTransportInsurancePanel(){
        transportInsurancePanel.add(new JLabel("time in years:"));
        transportInsurancePanel.add(transportTimeInYearsField);
        transportInsurancePanel.add(new JLabel("level of risk:"));
        transportInsurancePanel.add(transportLevelOfRiskField);
        transportInsurancePanel.add(new JLabel("transport name:"));
        transportInsurancePanel.add(transportNameField);
        transportInsurancePanel.add(new JLabel("transport class"));
        transportInsurancePanel.add(transportClassComboBox);
        transportInsurancePanel.add(new JLabel("transport number:"));
        transportInsurancePanel.add(transportNumberField);
    }
    private void setAddEditPanel(){
        addEditPanel.add(insuranceTypeComboBoxPanel);
        addEditPanel.add(healthInsurancePanel);
        addEditPanel.add(realEstateInsurancePanel);
        addEditPanel.add(transportInsurancePanel);
        addEditPanel.add(addButton);
        addEditPanel.add(editButton);
        addEditPanel.add(cancelButton);
    }
    private void setSearchLayout (){
        mainPanel.setVisible(false);
        addEditPanel.setVisible(false);
        searchPanel.setVisible(true);
    }
    private void setEditLayout(BaseInsurance insurance){
        String inClass = insurance.getClass().toString();
        insuranceTypeComboBox.setVisible(false);
        switch (inClass){
            case "class kn11sp.yaremechko.insurances.HealthInsurance":
                healthInsurancePanel.setVisible(true);
                realEstateInsurancePanel.setVisible(false);
                transportInsurancePanel.setVisible(false);
                healthLevelOfRiskField.setText(String.valueOf(insurance.getLevelOfRisk()));
                healthTimeInYearsField.setText(String.valueOf(insurance.getTimeInYears()));
                healthPersonNameField.setText(insurance.getPersonName());
                break;
            case "class kn11sp.yaremechko.insurances.RealEstateInsurance":
                realEstateInsurancePanel.setVisible(true);
                healthInsurancePanel.setVisible(false);
                transportInsurancePanel.setVisible(false);
                realEstateLevelOfRiskField.setText(String.valueOf(insurance.getLevelOfRisk()));
                realEstateTimeInYearsField.setText(String.valueOf(insurance.getTimeInYears()));
                realEstateAreaField.setText(String.valueOf(insurance.getArea()));
                realEstateAddressField.setText(insurance.getAddress());
                realEstateCityField.setText(insurance.getCity());
                break;
            case "class kn11sp.yaremechko.insurances.TransportInsurance":
                transportInsurancePanel.setVisible(true);
                healthInsurancePanel.setVisible(false);
                realEstateInsurancePanel.setVisible(false);
                transportLevelOfRiskField.setText(String.valueOf(insurance.getLevelOfRisk()));
                transportTimeInYearsField.setText(String.valueOf(insurance.getTimeInYears()));
                transportClassComboBox.setSelectedItem(insurance.getTransportClass());
                transportNameField.setText(insurance.getTransportName());
                transportNumberField.setText(insurance.getTransportNumber());
                break;}
        addButton.setVisible(false);
        editButton.setVisible(true);
        mainPanel.setVisible(false);
        addEditPanel.setVisible(true);
        }
        private void setAddLayout(){
            insuranceTypeComboBox.setVisible(true);
            addButton.setVisible(true);
            mainPanel.setVisible(false);
            addEditPanel.setVisible(true);

        }

        private void emptyFields(){
            healthTimeInYearsField.setText("");
            healthLevelOfRiskField.setText("");
            healthPersonNameField.setText("");
            realEstateTimeInYearsField.setText("");
            realEstateLevelOfRiskField.setText("");
            realEstateCityField.setText("");
            realEstateAddressField.setText("");
            realEstateAreaField.setText("");
            transportTimeInYearsField.setText("");
            transportLevelOfRiskField.setText("");
            transportClassComboBox.setSelectedItem("A");
            transportNameField.setText("");
            transportNumberField.setText("");
            searchLevelOfRiskField.setText("");
            searchPriceField.setText("");
        }
    public void updateDerivativeComboBox(){
        derivativeComboBox.removeAllItems();
        for(int i = 0; i < derivatives.size();i++){
            derivativeComboBox.addItem(derivatives.get(i).toString());
        }
    }

    public void updateInsuranceListBox(int derivativeId){
        listModel.removeAllElements();
        for(int i = 0;i<insurances.get(derivativeId).size();i++){
            System.out.println(insurances.get(derivativeId).get(i));
            listModel.addElement(insurances.get(derivativeId).get(i));
        }
    }

}
