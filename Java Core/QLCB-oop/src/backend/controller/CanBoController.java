package backend.controller;

import backend.service.ICanBoService;
import backend.service.impl.CanBoServiceImpl;
import entity.CanBo;

import java.util.List;

public class CanBoController {
    private static ICanBoService canBoService;

    public CanBoController(){

        canBoService = new CanBoServiceImpl();
    }

    public List<CanBo> findAll(){
        // gọi đến service để lấy dữ liệu
        return canBoService.findAll();
    }
    public static List<CanBo> findByName(String ten) {
        return canBoService.findByName(ten);
    }
}
