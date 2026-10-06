package frontend;

import backend.controller.CanBoController;
import entity.CanBo;

import java.util.List;
import java.util.Scanner;

public class Function {
    private CanBoController canBoController;
    private Scanner sc;
    public Function(){
        this.canBoController = new CanBoController();
        this.sc = new Scanner(System.in);
    }

    // thêm mới

    // tìm kiếm
    public void timkiemTheoHoTen(){
        System.out.println("==== TÌM KIẾM CÁN BỘ ====");
        System.out.println("Nhập họ tên cần tìm: ");
        String ten = sc.nextLine();
        List<CanBo> canBos = CanBoController.findByName(ten);
        if(canBos.isEmpty()){
            System.out.println("không có kết quả tương ứng");
        }else {
            System.out.println("+-------------------------+-----+----------+--------------------+");
            System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
            System.out.println("+-------------------------+-----+----------+--------------------+");
            for (CanBo cb : canBos) {
                System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
            }

            System.out.println("+-------------------------+-----+----------+--------------------+");
        }

    }

    // hiển thị
    public void hienThiToanBo(){
        // yêu cầu controller trả ra một danh sách cán bộ
        List<CanBo> canBos = canBoController.findAll();
        System.out.println("==== HIỂN THỊ TOÀN BỘ CÁN BỘ ====");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBos) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");

    }

    // delete

    // update

    public void menu() {
        Scanner sc = new Scanner(System.in);
        // IQLCB iqlcb = new QLCB();
        while (true) {
            System.out.println("==== Mời bạn chọn chức năng ====");
            System.out.println("1. Thêm mới cán bộ.");
            System.out.println("2. Tìm kiếm theo họ tên.");
            System.out.println("3. Hiển thị toàn bộ các cán bộ.");
            System.out.println("4. Nhập vào tên của cán bộ và delete cán bộ đó.");
            System.out.println("5. Update địa chỉ theo tên.");
            System.out.println("6. Thoát khỏi chương trình.");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":

                    break;
                case "2":
                    this.timkiemTheoHoTen();
                    break;
                case "3":
                    this.hienThiToanBo();
                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "6":
                    System.out.println("Thoát.");
                    System.exit(0);
                default:
                    System.out.println("Chọn sai, Chọn lại!");
            }
        }
    }
}

