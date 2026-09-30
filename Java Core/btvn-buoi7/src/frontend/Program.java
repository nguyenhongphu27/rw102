package frontend;

import backend.AiQLTL;
import backend.QLTL;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Scanner sc = new Scanner(System.in);
        AiQLTL aiQLTL = new QLTL();
        while (true){
            System.out.println("=====Mời Bạn Chọn Chức Năng=====");
            System.out.println("1. Thêm mới tài liệu");
            System.out.println("2. Xóa tài liệu");
            System.out.println("3. Hiển thị thông tin tài liệu");
            System.out.println("4. Tìm kiếm tài liệu");
            System.out.println("5. Thoát khỏi chương trình");
            String choice = sc.nextLine();
            switch (choice){
                case "1":
                    aiQLTL.themMoiTaiLieu();
                    break;
                case "2":
                    aiQLTL.xoaTaiLieu();
                    break;
                case "3":
                    aiQLTL.hienThiThongTinTaiLieu();
                    break;
                case "4":
                    aiQLTL.timKiemTaiLieu();
                    break;
                case "5":
                    System.out.println("Thoát.");
                    System.exit(0);
                default:
                    System.out.println("Chọn sai, chọn lại");



            }
        }

    }
}
