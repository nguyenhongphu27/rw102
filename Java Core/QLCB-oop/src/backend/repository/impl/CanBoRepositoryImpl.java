package backend.repository.impl;

import backend.repository.ICanBoRepository;
import entity.*;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanBoRepositoryImpl implements ICanBoRepository {
    @Override
    public List<CanBo> findAll() {
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
        return canBos;
    }

    @Override
    public List<CanBo> findByName(String ten) {
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
        return canBos;
    }
}
