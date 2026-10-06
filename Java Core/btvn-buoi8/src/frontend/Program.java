package frontend;

import backend.IQLAccount;
import backend.IQLDepartment;
import backend.QLAccount;
import backend.QlDepartment;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========== MENU ==========");
            System.out.println("1. Quản lý Account");
            System.out.println("2. Quản lý Department");
            System.out.println("0. Thoát");
            System.out.println("==========================");

            System.out.print("Mời bạn chọn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    menuAccount();
                    break;

                case 2:
                    menuDepartment();
                    break;
                case 0:
                    System.out.println("Thoát chương trình");
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static void menuAccount() {
        Scanner scanner = new Scanner(System.in);
        IQLAccount iqlAccount = new QLAccount();
        while (true) {
            System.out.println("========== QUẢN LÝ ACCOUNT ==========");
            System.out.println("1. Hiển thị toàn bộ Account");
            System.out.println("2. Tìm kiếm Account theo username");
            System.out.println("3. Thêm mới Account");
            System.out.println("4. Xóa Account theo username");
            System.out.println("5. Update fullname theo username");
            System.out.println("0. Quay lại");
            System.out.println("=====================================");

            System.out.print("Mời bạn chọn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    iqlAccount.hienThiToanBo();
                    break;

                case 2:
                   iqlAccount.timKiemTheoUsername();
                    break;

                case 3:
                    iqlAccount.themMoiAccount();
                    break;

                case 4:
                   iqlAccount.xoaAccountTheoUsername();
                    break;

                case 5:
                   iqlAccount.updateFullnameTheoUsername();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static void menuDepartment() {
        Scanner scanner = new Scanner(System.in);
        IQLDepartment iqlDepartment = new QlDepartment();

        while (true) {
            System.out.println("========== QUẢN LÝ DEPARTMENT ==========");
            System.out.println("1. Hiển thị Department");
            System.out.println("2. Tìm kiếm Department theo tên");
            System.out.println("3. Thêm mới Department");
            System.out.println("4. Xóa Department theo ID");
            System.out.println("5. Update tên phòng ban theo ID");
            System.out.println("0. Quay lại");
            System.out.println("=========================================");

            System.out.print("Mời bạn chọn: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    iqlDepartment.hienThiToanBoDepartment();
                    break;

                case 2:
                    iqlDepartment.timKiemDepartmentTheoTen();
                    break;

                case 3:
                    iqlDepartment.themMoiDepartment();
                    break;

                case 4:
                   iqlDepartment.xoaDepartmentTheoId();
                    break;

                case 5:
                   iqlDepartment.updateTenPhongBanTheoId();
                    break;

                case 0:
                   System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }
}