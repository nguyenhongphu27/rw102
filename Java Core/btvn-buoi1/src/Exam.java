public class Exam {
    int id;
    String code;
    String title;
    String duration;
    String createdDate;
    Account creator;
    CategoryQuestion categoryQuestion;

    void thongTinCuaExam(){
        System.out.println("id " + id);
        System.out.println("code " + code);
        System.out.println("title " + title);
        System.out.println("duration " + duration);
        System.out.println("createdDate " + createdDate);
        System.out.println("creator " + creator);
        System.out.println("categoryQuestion " + categoryQuestion.id);
    }
}
