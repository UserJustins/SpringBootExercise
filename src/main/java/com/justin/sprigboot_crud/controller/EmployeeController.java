package com.justin.sprigboot_crud.controller;

import com.justin.sprigboot_crud.dao.DepartmentDao;
import com.justin.sprigboot_crud.dao.EmployeeDao;
import com.justin.sprigboot_crud.entities.Department;
import com.justin.sprigboot_crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    /*删除员工*/
    @DeleteMapping("emp/{id}")
    public  String delEmp(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
    /*修改员工页面*/
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        System.out.println("修改员工："+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /*
    来到员工列表页面
     */
    @GetMapping("/emps")
    public String emps(Model model){
        Collection<Employee> employeeAll = employeeDao.getAll();
        model.addAttribute("emps",employeeAll);
        return "employee/list";
    }

    /*
    来到添加员工的页面
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查询部门信息，用于部门下拉框的值
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "employee/add";
    }
    /*
    添加员工
     */
    @PostMapping("/emp")
    public String addEmployee(String _method,Employee employee){
        employeeDao.save(employee);
        System.out.println(_method);
        System.out.println("添加员工： "+employee);
        return "redirect:/emps";
    }
    /*
    修改页面，数据回显
     */
    @GetMapping("/emp/{id}")
    public String editEmp(@PathVariable("id") Integer id, Model model){
        //查询部门信息，用于部门下拉框的值
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //根据ID查询员工
        Employee emp = employeeDao.get(id);
        model.addAttribute("emp",emp);
        return "employee/add";
    }

}
