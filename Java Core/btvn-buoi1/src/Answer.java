public class Answer {
    int id;
    String content;
    String isCorrect;
    Question question;

     void thongTinCuaAnswer(){
         System.out.println("id " + id);
         System.out.println("content " + content);
         System.out.println("isCorrect " + isCorrect);
         System.out.println("question " + question.id);
     }
}
