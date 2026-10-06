package backend;

import entity.Account;
import entity.Department;
import entity.Position;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLAccount implements IQLAccount {
    @Override
    public void hienThiToanBo() {
        List<Account> accounts = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();
        try {
            String sql = "SELECT * FROM Account";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

               int id = resultSet.getInt( "account_id");
               String email = resultSet.getNString("email");
               String userName = resultSet.getNString("username");
               String fullName = resultSet.getNString("full_name");
               int DepartmentId = resultSet.getInt("department_id");
               int PositionId = resultSet.getInt("position_id");
                Account account = new Account(id, email, userName, fullName, new Department(), new Position());
                accounts.add(account);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection();
        }
        System.out.println("========HIỂN THỊ TOÀN BỘ ACCOUNT");
        System.out.println("+----------+--------------------+---------------+---------------+----------+----------+");
        System.out.printf("|%10s|%20s|%15s|%15s|%10s|%10s|%n", "account_id", "email", "username", "full_name", "department_id","position_id");
        System.out.println("+----------+--------------------+---------------+---------------+----------+----------+");
        for (Account acc : accounts){
            System.out.printf("|%10s|%20s|%15s|%15s|%10s|%10s|%n", acc.getId(), acc.getEmail(), acc.getUserName(),acc.getFullName(), acc.getDepartment().getId(), acc.getPosition().getId());
        }
        System.out.println("+----------+--------------------+---------------+---------------+----------+----------+");

    }

    @Override
    public void timKiemTheoUsername() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("nhập username cần tìm:");
            String username = sc.nextLine();
            List<Account> accounts = new ArrayList<>();
            Connection connection = JDBCUtils.getConnection();
            try {
                String sql = "SELECT * FROM Account WHERE username = ?";
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, username);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("account_id");
                    String email = resultSet.getNString("email");
                    String userName = resultSet.getNString("username");
                    String fullName = resultSet.getNString("full_name");
                    int DepartmentId = resultSet.getInt("department_id");
                    int PositionId = resultSet.getInt("position_id");
                    Account account = new Account(id, email, userName, fullName, new Department(), new Position());
                    accounts.add(account);

                    System.out.println("========HIỂN THỊ ACCOUNT");
                    System.out.println("+----------+--------------------+---------------+---------------+----------+----------+");
                    System.out.printf("|%10s|%20s|%15s|%15s|%10s|%10s|%n", "account_id", "email", "username", "full_name", "department_id", "position_id");
                    System.out.println("+----------+--------------------+---------------+---------------+----------+----------+");
                    for (Account acc : accounts) {
                        System.out.printf("|%10s|%20s|%15s|%15s|%10s|%10s|%n", acc.getId(), acc.getEmail(), acc.getUserName(), acc.getFullName(), acc.getDepartment().getId(), acc.getPosition().getId());
                    }
                    System.out.println("+----------+--------------------+---------------+---------------+----------+----------+");
                    break;
                }else {
                    System.out.println("username không tồn tại! vui lòng nhập lại:");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void themMoiAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("======THÊM ACCOUNT======");
        System.out.println("nhập account_id");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("nhập email");
        String email = sc.nextLine();

        System.out.println("nhập username");
        String username = sc.nextLine();

        System.out.println("nhập fullname");
        String fullname = sc.nextLine();

        System.out.println("nhập department_id");
        int dep_id = sc.nextInt();

        System.out.println("nhập position_id");
        int pos_id = sc.nextInt();
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "INSERT INTO Account(account_id, email, username, full_name, department_id, position_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, email);
            statement.setString(3, username);
            statement.setString(4, fullname);
            statement.setInt(5, dep_id);
            statement.setInt(6, pos_id);

            int c = statement.executeUpdate();

            if(c > 0){
                System.out.println("thêm account thành công");
            } else {
                System.out.println("thêm account không thành công");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection();
        }

    }

    @Override
    public void xoaAccountTheoUsername() {
        Scanner sc =new Scanner(System.in);
        System.out.println("nhập username cần xóa");
        String usename = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM Account WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, usename);

            int c = statement.executeUpdate();

            if (c > 0 ){
                System.out.println("xóa username thành công");

            } else {
                System.out.println("xóa username thành công");
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public void updateFullnameTheoUsername() {
        Scanner sc = new Scanner(System.in);
        System.out.println("nhập fullname cần update");
        String full_name = sc.nextLine();
        System.out.println("nhập username cần update");
        String user = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE Account SET full_name = ? WHERE username = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, full_name);
            statement.setString(2, user);

            int c = statement.executeUpdate();

            if(c > 0){
                System.out.println("update thành công");
            }else {
                System.out.println("update không thành công");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.closeConnection();
        }
    }
}
