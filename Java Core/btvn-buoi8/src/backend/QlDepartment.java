package backend;

import entity.Department;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QlDepartment implements IQLDepartment{
    @Override
    public void hienThiToanBoDepartment() {
        List<Department> departments = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();
        try {
            String sql = "SELECT * FROM Department";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("department_id");
                String name = resultSet.getNString("department_name");
                Department department = new Department(id, name);
                departments.add(department);

                System.out.println("=======HIỂN THỊ TOÀN BỘ DEPARTMENT=======");
                System.out.println("+------+------------+");
                System.out.printf("|%6s|%12s|%n","department_id", "department_name");
                System.out.println("+------+------------+");
                for (Department dep : departments){
                    System.out.printf("|%6s|%12s|%n", dep.getId() , dep.getName());
                }
                System.out.println("+------+------------+");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection();
        }

    }

    @Override
    public void timKiemDepartmentTheoTen() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("nhập tên cần tìm:");
            String ten = sc.nextLine();
            List<Department> departments = new ArrayList<>();
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM Department WHERE department_name = ?";
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, ten);

                ResultSet resultSet = statement.executeQuery();

               if (resultSet.next()) {
                    int id = resultSet.getInt("department_id");
                    String name = resultSet.getNString("department_name");
                    Department department = new Department(id, name);
                    departments.add(department);

                        System.out.println("=======HIỂN THỊ DEPARTMENT=======");
                        System.out.println("+------+------------+");
                        System.out.printf("|%6s|%12s|%n", "department_id", "department_name");
                        System.out.println("+------+------------+");
                        for (Department dep : departments) {
                            System.out.printf("|%6s|%12s|%n", dep.getId(), dep.getName());
                        }
                        System.out.println("+------+------------+");
                        break;

                    }else {
                   System.out.println("Department không tồn tại! vui lòng nhập lại");
               }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.closeConnection();
            }

        }
    }

    @Override
    public void themMoiDepartment() {
        Scanner sc = new Scanner(System.in);
        System.out.println("======THÊM DEPARTMENT======");
        System.out.println("nhập department_id");
        int dep_id = sc.nextInt();

        System.out.println("nhập department_name");
        String dep_name = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO Department (department_id, department_name) VALUES (?,?);";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, dep_id);
            statement.setString(2, dep_name);

            int c = statement.executeUpdate();

            if(c > 0){
                System.out.println("thêm department thành công");
            } else {
                System.out.println("thêm department không thành công");
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            JDBCUtils.closeConnection();
        }

    }

    @Override
    public void xoaDepartmentTheoId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập id cần xóa");
        int id = sc.nextInt();
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM Department WHERE department_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            int c = statement.executeUpdate();

            if (c > 0){
                System.out.println("xóa department thành công");
            }else {
                System.out.println("xóa department không thành công");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeConnection();
        }
    }

    @Override
    public void updateTenPhongBanTheoId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập tên phòng ban cần update");
        String ten = sc.nextLine();
        System.out.println("nhập id cần update");
        int id = sc.nextInt();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE Department SET department_name = ? WHERE department_id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,ten);
            statement.setInt(2, id);

            int c = statement.executeUpdate();

            if(c > 0){
                System.out.println("update thành công");
            } else {
                System.out.println("update không thành công");
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
