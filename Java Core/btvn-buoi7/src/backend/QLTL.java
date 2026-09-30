package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QLTL implements AiQLTL{
    private List<TaiLieu> taiLieuList;
    private Scanner sc = new Scanner(System.in);

    public QLTL(){
        taiLieuList = new ArrayList<>();
        taiLieuList.add(new Sach(1,"Kim Dong",100,"Nguyen Nhat Anh",250));
        taiLieuList.add(new Sach(2, "Tre", 80, "To Hoai", 300));
        taiLieuList.add(new TapChi(3, "Thanh Nien", 50, 10, 9));
        taiLieuList.add(new TapChi(4, "Tuoi Tre", 60, 15, 8));
        taiLieuList.add(new Bao(5, "Nhan Dan", 100, 29/9/2026));
        taiLieuList.add(new Bao(6, "Ha Noi Moi", 70, 28/9/2026));


    }
    @Override
    public void themMoiTaiLieu() {
        System.out.println("==========THÊM MỚI TÀI LIỆU==========");
        // Chọn loại tài liệu
        System.out.println("1. Sách");
        System.out.println("2. Tạp chí");
        System.out.println("3. Báo");
        System.out.print("Nhập loại tài liệu: ");

        int loai = sc.nextInt();
        sc.nextLine();

        switch (loai) {
            // ================= SÁCH =================
            case 1:
                System.out.print("Nhập mã tài liệu: ");
                int maTaiLieu = sc.nextInt();
                sc.nextLine();

                System.out.print("Nhập tên nhà xuất bản: ");
                String tenNhaXuatBan = sc.nextLine();

                System.out.print("Nhập số bản phát hành: ");
                int soBanPhatHanh = sc.nextInt();
                sc.nextLine();

                System.out.print("Nhập tên tác giả: ");
                String tenTacGia = sc.nextLine();

                System.out.print("Nhập số trang: ");
                int soTrang = sc.nextInt();

                taiLieuList.add(new Sach(maTaiLieu,tenNhaXuatBan,soBanPhatHanh,tenTacGia,soTrang));

                System.out.println("Đã thêm thành công!");
                break;
            // ================= TẠP CHÍ =================
            case 2:
                System.out.print("Nhập mã tài liệu: ");
                int maTaiLieuTC = sc.nextInt();
                sc.nextLine();

                System.out.print("Nhập tên nhà xuất bản: ");
                String tenNhaXuatBanTC = sc.nextLine();

                System.out.print("Nhập số bản phát hành: ");
                int soBanPhatHanhTC = sc.nextInt();

                System.out.print("Nhập số phát hành: ");
                int soPhatHanh = sc.nextInt();

                System.out.print("Nhập tháng phát hành: ");
                int thangPhatHanh = sc.nextInt();

                taiLieuList.add(new TapChi(maTaiLieuTC,tenNhaXuatBanTC,soBanPhatHanhTC,soPhatHanh,thangPhatHanh));

                System.out.println("Đã thêm thành công!");
                break;

            // ================= BÁO =================
            case 3:
                System.out.print("Nhập mã tài liệu: ");
                int maTaiLieuBao = sc.nextInt();
                sc.nextLine();

                System.out.print("Nhập tên nhà xuất bản: ");
                String tenNhaXuatBanBao = sc.nextLine();

                System.out.print("Nhập số bản phát hành: ");
                int soBanPhatHanhBao = sc.nextInt();
                sc.nextLine();

                System.out.print("Nhập ngày phát hành: ");
                int ngayPhatHanh = sc.nextInt();

                taiLieuList.add(new Bao(maTaiLieuBao,tenNhaXuatBanBao,soBanPhatHanhBao,ngayPhatHanh));
                System.out.println("Đã thêm thành công!");
                break;

            default:
                System.out.println("Tài liệu không hợp lệ!");

        }
    }
    @Override
    public void xoaTaiLieu() {
        System.out.println("==========XÓA TÀI LIỆU==========");
        System.out.print("Nhập mã tài liệu cần xóa: ");
        int maTaiLieu = sc.nextInt();

        // Tìm tài liệu có mã giống với mã cần xóa
        List<TaiLieu> removes = new ArrayList<>();

        for (TaiLieu tl : taiLieuList) {
            if (tl.getMaTaiLieu() == maTaiLieu) {
                removes.add(tl);
            }
        }

        // Xóa tài liệu
        if (removes.size() == 0) {
            System.out.println("Mã tài liệu này không có trong hệ thống");
        } else {
            taiLieuList.removeAll(removes);
            System.out.println("Xóa thành công!");
        }
    }
    @Override
    public void hienThiThongTinTaiLieu() {
        System.out.println("==========HIỂN THỊ THÔNG TIN TÀI LIỆU==========");
        System.out.println("+----------+--------------------+---------------+");
        System.out.printf("|%-10s|%-20s|%-10s|%n","Mã tài liệu", "Nhà xuất bản", "Số bản phát hành");
        System.out.println("+----------+--------------------+---------------+");
        for(TaiLieu tl : taiLieuList){
            System.out.printf("|%-10s|%-20s|%-10s|%n",tl.getMaTaiLieu(),tl.getTenXuatNhaXuatBan(),tl.getSoBanPhatHanh());
        }
        System.out.println("+----------+--------------------+---------------+");
    }
    @Override
    public void timKiemTaiLieu() {
        System.out.println("==========TÌM KIẾM TÀI LIỆU==========");
        System.out.println("Nhập tài liệu cần tìm");
        String ten = sc.nextLine();
        System.out.println("+----------+--------------------+---------------+");
        System.out.printf("|%-10s|%-20s|%-10s|%n","Mã tài liệu", "Nhà xuất bản", "Số bản phát hành");
        System.out.println("+----------+--------------------+---------------+");
        for(TaiLieu tl : taiLieuList){
            if(tl.getTenXuatNhaXuatBan().equals(ten));
            System.out.printf("|%-10s|%-20s|%-10s|%n",tl.getMaTaiLieu(),tl.getTenXuatNhaXuatBan(),tl.getSoBanPhatHanh());
        }
        System.out.println("+----------+--------------------+---------------+");
    }
}
