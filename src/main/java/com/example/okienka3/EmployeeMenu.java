package com.example.okienka3;

import EmployeeManagement.Employee;
import EmployeeManagement.EmployeeCondition;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmployeeMenu implements Initializable {
    ObservableList<Employee> employees;
    @FXML
    public TableView<Employee> employeeData;
    @FXML
    public TableColumn<Employee,String> nameColumn;
    @FXML
    public TableColumn<Employee,String> surnameColumn;
    @FXML
    public TableColumn<Employee, EmployeeCondition> stateColumn;
    @FXML
    public TableColumn<Employee, Integer> birthYearColumn;
    @FXML
    public TableColumn<Employee,Double> salaryColumn;
    @FXML
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public ChoiceBox<EmployeeCondition> stateField;
    @FXML
    public TextField birthYearField;
    @FXML
    public TextField salaryField;
    @FXML
    public Text midPaneText;
    @FXML
    public Text rightPaneText;
    @FXML
    public TextField filterField;
    @FXML
    protected void onReturnButtonClick()throws IOException {
        EMMain m = new EMMain();
        m.changeScene("main-menu.fxml");
    }
    @FXML
    public void addEmployeeFromFields(){
        if(nameField.getText().isEmpty()||surnameField.getText().isEmpty()||stateField.getValue()==null||birthYearField.getText().isEmpty()||salaryField.getText().isEmpty()) {
            rightPaneText.setVisible(true);
            rightPaneText.setText("Provide all values");
        }else{
            try {
                Employee employee = new Employee(nameField.getText(), surnameField.getText(), stateField.getValue(), Integer.parseInt(birthYearField.getText()), Double.parseDouble(salaryField.getText()));
                employees = employeeData.getItems();
                employees.add(employee);
                employeeData.setItems(employees);
                rightPaneText.setVisible(false);
            }catch (NumberFormatException nfe){
                rightPaneText.setVisible(true);
                rightPaneText.setText("Birth Year and Salary must be numerical values");
            }
        }
    }
    @FXML
    public void loadEmployeeIntoFields(){
        int selectedID = employeeData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1){
            nameField.setText(nameColumn.getCellData(selectedID));
            surnameField.setText(surnameColumn.getCellData(selectedID));
            stateField.setValue(stateColumn.getCellData(selectedID));
            birthYearField.setText(birthYearColumn.getCellData(selectedID).toString());
            salaryField.setText(salaryColumn.getCellData(selectedID).toString());
            employeeData.getItems().remove(selectedID);
        }else{
            rightPaneText.setVisible(true);
            rightPaneText.setText("Select an employee in the table first");
        }
    }
    @FXML
    public void removeEmployee(){
        int selectedID = employeeData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1){
            employees.remove(selectedID);
            // employeeData.getItems().remove(selectedID);
        }else{midPaneText.setVisible(true);}
    }
    @FXML
    public void hideButtonWarning(){
        midPaneText.setVisible(false);
        rightPaneText.setVisible(false);
    }
    @FXML
    public void filterTableBySurname(){
        String filterString=filterField.getText();
        FilteredList<Employee> filteredList = new FilteredList<>(employees,s-> Objects.equals(s.getLastName(), filterString));
        employeeData.setItems(filteredList);
    }
    @FXML
    public void disableFilterTableBySurname(){
        employeeData.setItems(employees);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateField.getItems().setAll(EmployeeCondition.present,EmployeeCondition.absent,EmployeeCondition.sick,EmployeeCondition.business_trip);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("Name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("LastName"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<Employee,EmployeeCondition>("Status"));
        birthYearColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("BirthYear"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee,Double>("Salary"));
    }
}
