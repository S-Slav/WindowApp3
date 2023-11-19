package EmployeeManagement;

import java.util.HashMap;
import java.util.Map;public class ClassContainer {
    private Map<String, ClassEmployee> groupMap;
    public ClassContainer() {
        this.groupMap = new HashMap<>();
    }
    public void addClass(ClassEmployee classEmployee) {
        groupMap.put(classEmployee.getGroupName(), classEmployee);
    }
    public void removeClass(ClassEmployee classEmployee) {
        groupMap.remove(classEmployee.getGroupName(), classEmployee);
    }
    public void printEmpty() {
        System.out.println("Empty Groups:");

        for (Map.Entry<String, ClassEmployee> entry : groupMap.entrySet()) {
            String groupName = entry.getKey();
            ClassEmployee classEmployee = entry.getValue();

            if (classEmployee == null || classEmployee.isEmpty()) {
                System.out.println(groupName);
            }
        }
    }
    public void summary() {
        System.out.println("Group Sizes:");

        for (Map.Entry<String, ClassEmployee> entry : groupMap.entrySet()) {
            String groupName = entry.getKey();
            ClassEmployee classEmployee = entry.getValue();

            if (classEmployee != null) {
                int currentSize = classEmployee.EmployeeList.size();
                int maxSize = classEmployee.MaxSize;
                double percentageFullness = ((double) currentSize / maxSize) * 100;
                System.out.println(groupName + ": " + currentSize + " / " + maxSize+" ("+String.format("%.2f", percentageFullness) + "% full)");
            }
        }
    }
    public Map<String,ClassEmployee> getGroupMap(){
        return groupMap;
    }
    //extra
    public void renameGroup(String oldName, String newName) {
        if (groupMap.containsKey(oldName)) {
            ClassEmployee classEmployee = groupMap.remove(oldName);
            groupMap.put(newName, classEmployee);
        }
    }
    public void getTotalEmployeeCount() {
        System.out.println("Number of employees from all groups: "+groupMap.values().stream().mapToInt(ClassEmployee::getEmployeeCount).sum());
    }
    public void removeEmployeeFromGroup(String groupName, Employee employee) {
        ClassEmployee classEmployee = groupMap.get(groupName);
        if (classEmployee != null) {
            classEmployee.removeEmployee(employee);
        }
    }
}
