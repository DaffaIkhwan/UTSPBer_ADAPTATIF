package com.example.utspweb;

import java.util.ArrayList;

public class Constants {
    // ArrayList and return the ArrayList
    public static ArrayList<Employee> getEmployeeData() {
        // create an ArrayList of type Employee class
        ArrayList<Employee> employeeList
                = new ArrayList<Employee>();
        Employee emp1 = new Employee(R.drawable.iwan, "Iwan Iskandar, S.T., M.T.",
                "5865846", "Laki-laki", "Ketua Jurusan");
        employeeList.add(emp1);
        Employee emp2
                = new Employee(R.drawable.reski, "Reski Mai Candra, S.T., M.Sc.", "6786464", "Laki-laki", "Sekretaris Jurusan");
        employeeList.add(emp2);
        Employee emp3 = new Employee(R.drawable.affandes, "Muhammad Affandes, S.T., M.T.",
                "5865846", "Laki-laki", "Kepala Laboratorium");
        employeeList.add(emp3);
        Employee emp4
                = new Employee(R.drawable.fadhilah, "Fadhilah Syafria, S.T., M.Kom.", "6786464", "Perempuan", "Koord. Tugas Akhir");
        employeeList.add(emp4);


        return employeeList;
    }
}
