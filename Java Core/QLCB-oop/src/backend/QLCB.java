package backend;
import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class QLCB implements IQLCB {
    //    private CanBo[] canBos = new CanBo[1000];// lưu trữ dữ lieu, fix cứng số lượng lưu tru
    private List<CanBo> canBoList;
    private Scanner sc = new Scanner(System.in);
    @Override
    public void themMoi() {

        Connection conn = null;
        String sql = "";
        PreparedStatement statement = null;

        System.out.println("==== THÊM MỚI CÁN BỘ ====");
        System.out.print("Nhập họ tên: ");
        String hoTen = sc.nextLine();

        System.out.print("Nhập tuổi: ");
        int tuoi = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhập giới tính: 1. NAM    2. NU    3. KHAC: ");
        String gt = sc.nextLine();

        GioiTinh gioiTinh;

        switch (gt) {
            case "1":
                gioiTinh = GioiTinh.NAM;
                break;

            case "2":
                gioiTinh = GioiTinh.NU;
                break;

            default:
                gioiTinh = GioiTinh.KHAC;
                break;
        }

        System.out.print("Nhập địa chỉ: ");
        String diaChi = sc.nextLine();

        System.out.println("Chọn loại cán bộ:");
        System.out.println("1. Công nhân");
        System.out.println("2. Kỹ sư");
        System.out.println("3. Nhân viên");

        System.out.print("Nhập lựa chọn: ");
        String choice = sc.nextLine();

        switch (choice) {

            case "1":
                System.out.print("Nhập bậc: ");
                int bac = sc.nextInt();
                sc.nextLine();
                try {
                    conn = JDBCUtils.getConnection();
                    sql =  "INSERT INTO can_bo (hoten, tuoi, gioi_tinh, dia_chi, loai, bac) VALUES (?, ?, ?, ?, 'CN', ?)";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, hoTen);
                    statement.setInt(2,tuoi);
                    statement.setString(3, gioiTinh.name());//gioiTinh.name() chuyển từ enum sang string
                    statement.setString(4, diaChi);
                    statement.setInt(5, bac);

                    int c = statement.executeUpdate();
                    if(c > 0){
                        System.out.println("thêm công nhân thành công");
                    } else {
                        System.out.println("thêm công nhân thất bại");
                    }

                } catch (Exception e){
                    System.out.println("Lỗi: " + e.getMessage());
                }
                break;

            case "2":
                System.out.print("Nhập ngành đào tạo: ");
                String nganhDaoTao = sc.nextLine();
                try {
                    conn = JDBCUtils.getConnection();
                    sql =  "INSERT INTO can_bo (hoten, tuoi, gioi_tinh, dia_chi, loai, nganh) VALUES (?, ?, ?, ?, 'KS', ?)";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, hoTen);
                    statement.setInt(2,tuoi);
                    statement.setString(3, gioiTinh.name());//gioiTinh.name() chuyển từ enum sang string
                    statement.setString(4, diaChi);
                    statement.setString(5, nganhDaoTao);

                    int c = statement.executeUpdate();
                    if(c > 0){
                        System.out.println("thêm kỹ sư thành công");
                    } else {
                        System.out.println("thêm kỹ sư thất bại");
                    }

                } catch (Exception e){
                    System.out.println("Lỗi: " + e.getMessage());
                }
                break;
            case "3":
                System.out.print("Nhập công việc: ");
                String congViec = sc.nextLine();
                try {
                    conn = JDBCUtils.getConnection();
                    sql =  "INSERT INTO can_bo (hoten, tuoi, gioi_tinh, dia_chi, loai, cong_viec) VALUES (?, ?, ?, ?, 'NV', ?)";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, hoTen);
                    statement.setInt(2,tuoi);
                    statement.setString(3, gioiTinh.name());//gioiTinh.name() chuyển từ enum sang string
                    statement.setString(4, diaChi);
                    statement.setString(5, congViec);

                    int c = statement.executeUpdate();
                    if(c > 0){
                        System.out.println("thêm nhân viên thành công");
                    } else {
                        System.out.println("thêm nhân viên thất bại");
                    }

                } catch (Exception e){
                    System.out.println("Lỗi: " + e.getMessage());
                }finally {
                    JDBCUtils.closeConnection();
                }
        }
    }
    @Override
    public void timKiemTheoTen() {
        System.out.println("==== TÌM KIẾM CÁN BỘ ====");
        System.out.println("Nhập họ tên cần tìm: ");
        String ten = sc.nextLine();
        List<CanBo> canBos = new ArrayList<>();
        try {
            // bước 1 lấy dữ liệu từ database
            //  this.getConnection(); tạo một method riêng
            // tạo kết nối tới database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from can_bo where hoten like ?";// là tham số
            // statement hỗ trợ câu sql tĩnh(là câu sql kh có tham số)

            PreparedStatement statement = connection.prepareStatement(sql);// hỗ trợ câu sql động(sql có tham số)
            statement.setString(1, "%"+ ten +"%");// truyền giá trị cho tham số
            
            ResultSet resultSet = statement.executeQuery(sql);// thực thi câu Query(select) sau
            while (resultSet.next()){
                String hoten = resultSet.getString("hoten"); // lây dữ liệu theo tên cột hoặc vitri
                int tuoi = resultSet.getInt("tuoi");
                String gt = resultSet.getString("gioi_tinh");
                GioiTinh gioiTinh = GioiTinh.valueOf(gt);// chuyển từ String sang Enum
                String diaChi = resultSet.getString("dia_chi");
                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString);// chuyển từ String sang Enum
                if(loai == Loai.CN){
                    int bac = resultSet.getInt("bac");
                    CanBo cn = new CongNhan(hoten, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                    canBos.add(cn);
                } else if(loai == Loai.KS){
                    String nganh = resultSet.getString("nganh");
                    CanBo ks = new KySu(hoten, tuoi, gioiTinh, diaChi,Loai.KS, nganh);
                    canBos.add(ks);
                } else if(loai == Loai.NV){
                    String congViec =resultSet.getString("cong_viec");
                    CanBo ks = new KySu(hoten, tuoi, gioiTinh, diaChi,Loai.NV, congViec);
                    canBos.add(ks);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        // bước 2 hiện thị
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBos) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }

        System.out.println("+-------------------------+-----+----------+--------------------+");
    }
    @Override
    public void hienThiToanBo() {
        List<CanBo> canBos = new ArrayList<>();
        try {
            // bước 1 lấy dữ liệu từ database
            //  this.getConnection(); tạo một method riêng
            // tạo kết nối tới database
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from can_bo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);// thực thi câu Query(select) sau
            while (resultSet.next()){
                String hoten = resultSet.getString("hoten"); // lây dữ liệu theo tên cột hoặc vitri
                int tuoi = resultSet.getInt("tuoi");
                String gt = resultSet.getString("gioi_tinh");
                GioiTinh gioiTinh = GioiTinh.valueOf(gt);// chuyển từ String sang Enum
                String diaChi = resultSet.getString("dia_chi");
                String loaiString = resultSet.getString("loai");
                Loai loai = Loai.valueOf(loaiString);// chuyển từ String sang Enum
                if(loai == Loai.CN){
                    int bac = resultSet.getInt("bac");
                    CanBo cn = new CongNhan(hoten, tuoi, gioiTinh, diaChi, Loai.CN, bac);
                    canBos.add(cn);
                } else if(loai == Loai.KS){
                String nganh = resultSet.getString("nganh");
                CanBo ks = new KySu(hoten, tuoi, gioiTinh, diaChi,Loai.KS, nganh);
                canBos.add(ks);
                } else if(loai == Loai.NV){
                    String congViec =resultSet.getString("cong_viec");
                    CanBo ks = new KySu(hoten, tuoi, gioiTinh, diaChi,Loai.NV, congViec);
                    canBos.add(ks);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeConnection();
        }
        System.out.println("==== HIỂN THỊ TOÀN BỘ CÁN BỘ ====");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        System.out.printf("|%25s|%5s|%10s|%20s|\n", "Họ tên", "Tuổi", "Giới tính", "Địa chỉ");
        System.out.println("+-------------------------+-----+----------+--------------------+");
        for (CanBo cb : canBos) {
            System.out.printf("|%25s|%5s|%10s|%20s|\n", cb.getHoTen(), cb.getTuoi(), cb.getGioiTinh(), cb.getDiaChi());
        }
        System.out.println("+-------------------------+-----+----------+--------------------+");
    }
    @Override
    public void xoaTheoTen() {
        System.out.println("==== XÓA CÁN BỘ ====");
        System.out.print("Nhập tên cán bộ cần xóa: ");
        String hoTen = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "DELETE FROM can_bo WHERE hoten like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hoTen);

            int c = preparedStatement.executeUpdate();
            if (c > 0){
                System.out.println("xóa thành công");
            } else {
                System.out.println("tên không tồn tại");
            }

        } catch (Exception e){

        } finally {
            JDBCUtils.closeConnection();
        }
    }
    @Override
    public void UpdateDiaChiTheoTen() {
        System.out.println("==== UPDATE ĐỊA CHỈ THEO TỄN ====");
        System.out.print("Nhập tên cân update: ");
        String hoTen = sc.nextLine();
        System.out.println("nhập địa chỉ cần update");
        String diaChi = sc.nextLine();

        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "UPDATE can_bo SET dia_chi = ? WHERE hoten = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, diaChi);
            preparedStatement.setString(2, hoTen);

            int c = preparedStatement.executeUpdate();
            if (c > 0){
                System.out.println("Update thông tin thành công");
            } else {
                System.out.println("Update thông tin không thành công");
            }

        } catch (Exception e){
            e.printStackTrace();

        } finally {
            JDBCUtils.closeConnection();
        }
    }

    }

