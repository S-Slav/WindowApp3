package EmployeeManagement;

public class Employee implements Comparable<Employee> {
    private final String first_name;
    private final String last_name;
    private EmployeeCondition status;
    private final int birth_year;
    double salary;

    @Override public int compareTo(Employee employee)
    {
        return this.last_name.compareTo(employee.last_name);
    }
    public void printing() {
        System.out.println("EmployeeManagement.Employee information:");
        System.out.println("Name: " + first_name);
        System.out.println("Surname: " + last_name);
        System.out.println("Condition: " + status);
        System.out.println("Born in: " + birth_year);
        System.out.println("Salary: " + salary+"$");
    }
    public String getName(){return this.first_name;}
    public String getLastName(){
        return this.last_name;
    }
    public EmployeeCondition getStatus(){
        return this.status;
    }
    public Integer getBirthYear(){
        return this.birth_year;
    }
    public double getSalary(){
        return this.salary;
    }
    public String getFullName(){
        return this.first_name+' '+ this.last_name;
    }
    public void changeSalary(double amount){
        this.salary+=amount;
    }
    public void changeCondition(EmployeeCondition status){
        this.status=status;
    }
    //extra
    public Employee copy() {
        return new Employee(this.first_name, this.last_name, this.status, this.birth_year, this.salary);
    }
    public Employee(String first_name, String last_name, EmployeeCondition status, int birth_year, double salary) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
        this.birth_year = birth_year;
        this.salary = salary;
    }
}
