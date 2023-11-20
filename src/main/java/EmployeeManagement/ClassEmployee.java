package EmployeeManagement;

import java.util.*;

public class ClassEmployee {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    String GroupName;
    List<Employee> EmployeeList;
    int MaxSize;

    public void addEmployee(Employee employee){
        if (!this.EmployeeList.contains(employee)) {
            if (this.EmployeeList.size() < this.MaxSize) {
                this.EmployeeList.add(employee);
                System.out.println("EmployeeManagement.Employee successfully added to the group");
            } else {
                System.out.println("This employee group is full");
            }
        } else {
            System.out.println("This employee is already in the group");
        }
    }
    public void removeEmployee(Employee employee){
        if (this.EmployeeList.contains(employee)){
            this.EmployeeList.remove(employee);
            System.out.println("EmployeeManagement.Employee successfully removed from the group");
        }else{
            System.out.println("EmployeeManagement.Employee not found in this group.");
        }
    }
    public void addSalary(Employee employee,double amount){
        if (!this.EmployeeList.contains(employee)) {
            System.out.println("EmployeeManagement.Employee not found in this group.");
            return;
        }
        if (amount < 0) {
            System.out.println("Negative amount declared, are you sure you want to reduce salary?");
            System.out.println("Type \"reduce\" to confirm, if you want to cancel, type in anything else.");
            Scanner in = new Scanner(System.in);
            if (!Objects.equals(in.next(), "reduce")) {
                System.out.println("Salary change cancelled.");
                return;
            }
            if (Math.abs(amount) > employee.salary) {
                amount = -employee.salary;
            }
            employee.changeSalary(amount);
            System.out.println("Salary change successful, new salary is: " + ANSI_GREEN + employee.salary + ANSI_RESET + "$");
            return;
        }
        employee.changeSalary(amount);
        System.out.println("Salary change successful, new salary is: " + ANSI_GREEN + employee.salary + ANSI_RESET + "$");
    }
    public void changeCondition(Employee employee,EmployeeCondition condition){
        if (!this.EmployeeList.contains(employee)){
            employee.changeCondition(condition);
            return;
        }
            System.out.println("EmployeeManagement.Employee not found in this group.");
    }

    public void search(String lastName) {
        boolean found = false;
        for (Employee employee : EmployeeList) {
            if (employee.compareTo(new Employee("", lastName, null,0,0)) == 0) {
                // EmployeeManagement.Employee with matching last name found
                employee.printing();
                System.out.println();
                found=true;
            }
        }
        if (found){
            System.out.println("EmployeeManagement.Employee with the specified last name not found");
        }
    }
    public void searchPartial(String partialphrase){
        boolean found = false;
        for (Employee employee : EmployeeList) {
            if (employee.getLastName().contains(partialphrase)) {
                employee.printing();
                System.out.println();
                found=true;
            }
        }
        if (found){
            System.out.println("EmployeeManagement.Employee with the specified last name not found");
        }
    }
    public void countByCondition(EmployeeCondition status){
        int count = 0;
        for (Employee employee : EmployeeList) {
            if (employee.getStatus() == status) {
                count++;
            }
        }
        System.out.println("Number of employees with "+ANSI_GREEN+status+ANSI_RESET+" condition: "+count);
    }
    public void summary() {
        System.out.println("EmployeeManagement.Employee Summary:");

        for (Employee employee : EmployeeList) {
            employee.printing();
            System.out.println();
        }
    }
    public void sortByName() {
        // Sort the employee list based on last names
        EmployeeList.sort(Comparator.comparing(Employee::getLastName));
        // Print the names and surnames of employees in sorted order
        System.out.println("Employees sorted by last names:");
        for (Employee employee : EmployeeList) {
            System.out.println(employee.getFullName());
        }
    }
    public void sortBySalary() {
        // Sort the employee list based on salary
        EmployeeList.sort(Comparator.comparingDouble(Employee::getSalary));

        // Print the names, surnames, and salaries of employees in sorted order
        System.out.println("Employees sorted by salary:");
        for (Employee employee : EmployeeList) {
            System.out.println(employee.getFullName() +
                    ", Salary: $" + employee.getSalary());
        }
    }
    public void largestSalary(){
        System.out.println("EmployeeManagement.Employee with the largest salary: "+Collections.max(EmployeeList,Comparator.comparingDouble(Employee::getSalary)).getFullName());
    }
    public ClassEmployee(String groupName, int maxSize) {
        this.GroupName = groupName;
        this.MaxSize = maxSize;
        EmployeeList = new ArrayList<>();
    }
    public boolean isEmpty() {
        return EmployeeList.isEmpty();
    }
    public String getGroupName(){
        return this.GroupName;
    }
    public int getMaxSize() {
        return MaxSize;
    }
    public int getEmployeeCount(){
        return EmployeeList.size();
    }
    public double getCapacity(){return (double) EmployeeList.size() /MaxSize;}

    public List<Employee> getEmployeeList() {
        return EmployeeList;
    }
    //extra
    public void clearGroup() {
        EmployeeList.clear();
        System.out.println("Removed all employees from group");
    }
    public ClassEmployee mergeGroups(ClassEmployee group,String name){
        ClassEmployee MergedGroup = new ClassEmployee(name,this.getMaxSize()+group.getMaxSize());
        for (Employee employee : this.EmployeeList) {
            MergedGroup.addEmployee(employee);
        }
        for (Employee employee : group.EmployeeList) {
            MergedGroup.addEmployee(employee);
        }
        return MergedGroup;
    }
}
