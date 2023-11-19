package com.example.okienka3;

import EmployeeManagement.ClassEmployee;
import EmployeeManagement.Employee;
import EmployeeManagement.EmployeeCondition;
import javafx.collections.FXCollections;
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

public class GroupMenu implements Initializable {
    ObservableList<ClassEmployee> employeeGroups;
    @FXML
    public TableView<ClassEmployee> groupData;
    @FXML
    public TableColumn<ClassEmployee,String> groupNameColumn;
    @FXML
    public TableColumn<ClassEmployee,Double> groupCapacityColumn;
    @FXML
    public TableColumn<ClassEmployee,Integer> groupMaxSizeColumn;
    @FXML
    public TextField groupNameField;
    @FXML
    public TextField maxSizeField;
    @FXML
    public Text rightGroupPaneText;
    @FXML
    public Text midGroupPaneText;
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
        int selectedID = groupData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1) {
            if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || stateField.getValue() == null || birthYearField.getText().isEmpty() || salaryField.getText().isEmpty()) {
                rightPaneText.setVisible(true);
                rightPaneText.setText("Provide all values");
            } else {
                try {
                    Employee employee = new Employee(nameField.getText(), surnameField.getText(), stateField.getValue(), Integer.parseInt(birthYearField.getText()), Double.parseDouble(salaryField.getText()));
                    for (Employee e:employeeData.getItems()) {
                        employeeGroups.get(selectedID).addEmployee(e);
                    }
                    employeeGroups.get(selectedID).addEmployee(employee);
                    employeeData.setItems(FXCollections.observableArrayList(employeeGroups.get(selectedID).getEmployeeList()));
                    rightPaneText.setVisible(false);
                } catch (NumberFormatException nfe) {
                    rightPaneText.setVisible(true);
                    rightPaneText.setText("Birth Year and Salary must be numerical values");
                }
            }
        }else{midGroupPaneText.setVisible(true);}
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
        int selectedGroupID = groupData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1){
            if(selectedGroupID!=-1) {
                employeeData.getItems().remove(selectedID);
                employeeGroups.get(selectedGroupID).getEmployeeList().remove(selectedID);
            }else{midGroupPaneText.setVisible(true);}
        }else{midPaneText.setVisible(true);}
    }
    @FXML
    public void hideButtonWarning(){
        midPaneText.setVisible(false);
        rightPaneText.setVisible(false);
    }
    @FXML
    public void filterTableBySurname(){
        int selectedID = groupData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1) {
            String filterString = filterField.getText();
            FilteredList<Employee> filteredList = new FilteredList<>(FXCollections.observableArrayList(employeeGroups.get(selectedID).getEmployeeList()), s -> Objects.equals(s.getLastName(), filterString));
            employeeData.setItems(filteredList);
        }else{midGroupPaneText.setVisible(true);}
    }
    @FXML
    public void disableFilterTableBySurname(){
        int selectedID = groupData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1) {
        employeeData.setItems(FXCollections.observableArrayList(employeeGroups.get(selectedID).getEmployeeList()));
        }else{midGroupPaneText.setVisible(true);}
    }
    @FXML
    public void removeGroup(){
        int selectedID = groupData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1){groupData.getItems().remove(selectedID);}else{midPaneText.setVisible(true);}
    }
    @FXML
    public void loadGroupIntoFields(){
        int selectedID = groupData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1){
            groupNameField.setText(nameColumn.getCellData(selectedID));
            groupMaxSizeColumn.setText(salaryColumn.getCellData(selectedID).toString());
        }else{
            rightGroupPaneText.setVisible(true);
            rightGroupPaneText.setText("Select a group in the table first");
        }
    }
    @FXML
    public void addGroupFromFields(){
        if(groupNameField.getText().isEmpty()||maxSizeField.getText().isEmpty()) {
        rightGroupPaneText.setVisible(true);
        rightGroupPaneText.setText("Provide all values");
        }else{
            try {
                ClassEmployee employeeGroup = new ClassEmployee(groupNameField.getText(),Integer.parseInt(maxSizeField.getText()));
                employeeGroups = groupData.getItems();
                employeeGroups.add(employeeGroup);
                groupData.setItems(employeeGroups);
                rightGroupPaneText.setVisible(false);
            }catch (NumberFormatException nfe){
                rightGroupPaneText.setVisible(true);
                rightGroupPaneText.setText("Max Size must be a numerical value");
            }
        }
    }
    @FXML
    public void hideGroupButtonWarning(){
        midGroupPaneText.setVisible(false);
        rightGroupPaneText.setVisible(false);
        int selectedID = groupData.getSelectionModel().getSelectedIndex();
        if(selectedID!=-1) {
        employeeData.setItems(FXCollections.observableArrayList(employeeGroups.get(selectedID).getEmployeeList()));}
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateField.getItems().setAll(EmployeeCondition.present,EmployeeCondition.absent,EmployeeCondition.sick,EmployeeCondition.business_trip);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("Name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("LastName"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<Employee,EmployeeCondition>("Status"));
        birthYearColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("BirthYear"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Employee,Double>("Salary"));
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<ClassEmployee,String>("GroupName"));
        groupMaxSizeColumn.setCellValueFactory(new PropertyValueFactory<ClassEmployee,Integer>("MaxSize"));
        groupCapacityColumn.setCellValueFactory(new PropertyValueFactory<ClassEmployee,Double>("Capacity"));
    }
}
