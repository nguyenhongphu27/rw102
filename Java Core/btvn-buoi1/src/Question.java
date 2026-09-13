public class Question {
    int id;
    String content;
    String createdDate;
    TypeQuestion typeQuestion;
    CategoryQuestion categoryQuestion;


     void thongTinCuaQuestion(){
         System.out.println("id " + id);
         System.out.println("content " + content);
         System.out.println("createdDate " + createdDate);
         System.out.println("typeQuestion " + typeQuestion.id);
         System.out.println("categoryQuestion " + categoryQuestion.id);
     }
}
