public class Program {
    public static void main(String[] args) {
        //Tạo file Program.java có chứa main() method và khởi tạo ít nhất 3 đối tượng đối với mỗi table trong java
        // Department
        Department department1 = new Department();
        department1.id = 1;
        department1.name = "Sales";

        Department department2 = new Department();
        department2.id = 2;
        department2.name = "Marketing";

        Department department3 = new Department();
        department3.id = 3;
        department3.name = "IT";

        // =====================================
        //Position
        Position position1 = new Position();
        position1.id = 1;
        position1.name = "Dev";

        Position position2 = new Position();
        position2.id = 2;
        position2.name = "Test";

        Position position3 = new Position();
        position3.id = 3;
        position3.name = "PM";

        // ======================================
        // Account

        Account account1 = new Account();
        account1.id = 1;
        account1.email = "a@gmail.com";
        account1.userName = "nguyen van a";
        account1.fullName = "Nguyen Van A";
        account1.createdDate = "2026-08-21 19:59:59";
        account1.department = department1;
        account1.position = position1;

        Account account2 = new Account();
        account2.id = 2;
        account2.email = "b@gmail.com";
        account2.userName = "nguyen van b";
        account2.fullName = "Nguyen Van B";
        account2.createdDate = "2026-08-22 19:59:59";
        account2.department = department2;
        account2.position = position2;

        Account account3 = new Account();
        account3.id = 3;
        account3.email = "c@gmail.com";
        account3.userName = "nguyen van c";
        account3.fullName = "Nguyen Van C";
        account3.createdDate = "2026-08-23 19:59:59";
        account3.department = department3;
        account3.position = position3;

        //=========================================
        // Group
        Group group1 = new Group();
        group1.id = 1;
        group1.name = "Liên Quân Mobile";

        Group group2 = new Group();
        group2.id = 2;
        group2.name ="PUBG";

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "Free Fire";

        //=====================================
        //GroupAccount
        GroupAccount groupAccount1 = new GroupAccount();
        groupAccount1.account = account1;
        groupAccount1.group = group1;
        groupAccount1.joinDate = "2026-03-01 09:00:00";

        GroupAccount groupAccount2 = new GroupAccount();
        groupAccount2.account = account2;
        groupAccount2.group = group2;
        groupAccount2.joinDate = "2026-03-02 09:00:00";

        GroupAccount groupAccount3 = new GroupAccount();
        groupAccount3.account = account3;
        groupAccount3.group = group3;
        groupAccount3.joinDate = "2026-03-03 09:00:00";

        //=========================================
        //TypeQuestion
        TypeQuestion typeQuestion1 = new TypeQuestion();
        typeQuestion1.id = 1;
        typeQuestion1.name = "Essay";

        TypeQuestion typeQuestion2 = new TypeQuestion();
        typeQuestion2.id = 2;
        typeQuestion2.name ="Multiple Choice";

        TypeQuestion typeQuestion3 = new TypeQuestion();
        typeQuestion3.id = 3;
        typeQuestion3.name ="Coding";

        //==============================================
        //CategoryQuestion
        CategoryQuestion categoryQuestion1 = new CategoryQuestion();
        categoryQuestion1.id = 1;
        categoryQuestion1.name ="Java";

        CategoryQuestion categoryQuestion2 = new CategoryQuestion();
        categoryQuestion2.id = 2;
        categoryQuestion2.name ="Git";

        CategoryQuestion categoryQuestion3 = new CategoryQuestion();
        categoryQuestion3.id = 3;
        categoryQuestion3.name ="MySQL";

        //============================================
        // Question
        Question question1 = new Question();
        question1.id = 1;
        question1.content = "Java là gì";
        question1.createdDate ="2026-08-21 19:59:59";
        question1.typeQuestion = typeQuestion1;
        question1.categoryQuestion = categoryQuestion1;

        Question question2 = new Question();
        question2.id = 2;
        question2.content = "SQL là gì";
        question2.createdDate ="2026-08-22 19:59:59";
        question2.typeQuestion = typeQuestion2;
        question2.categoryQuestion = categoryQuestion2;

        Question question3 = new Question();
        question3.id = 3;
        question3.content = "Class trong Java là gì";
        question3.createdDate ="2026-08-23 19:59:59";
        question3.typeQuestion = typeQuestion3;
        question3.categoryQuestion = categoryQuestion3;

        //===============================================
        // Answer  id content question
        Answer answer1 = new Answer();
        answer1.id = 1;
        answer1.content ="1+1=2";
        answer1.isCorrect = "true";
        answer1.question = question1;

        Answer answer2 = new Answer();
        answer2.id = 2;
        answer2.content ="2x2=5";
        answer2.isCorrect = "false";
        answer2.question = question2;

        Answer answer3 = new Answer();
        answer3.id = 3;
        answer3.content ="9:3=3";
        answer3.isCorrect = "true";
        answer3.question = question3;

        //=======================================
        // Exam
        Exam exam1 = new Exam();
        exam1.id = 1;
        exam1.code = "JV101";
        exam1.title = "Java Quick Quiz";
        exam1.duration ="30";
        exam1.createdDate = "2026-08-21 19:59:59";
        exam1.creator = account1;
        exam1.categoryQuestion = categoryQuestion1;

        Exam exam2 = new Exam();
        exam2.id = 2;
        exam2.code = "SQ201";
        exam2.title = "SQL Starter Test";
        exam2.duration ="45";
        exam2.createdDate = "2026-08-21 19:59:59";
        exam2.creator = account2;
        exam2.categoryQuestion = categoryQuestion2;

        Exam exam3 = new Exam();
        exam3.id = 3;
        exam3.code = "SC401";
        exam3.title = "Scrum Mini Check";
        exam3.duration ="35";
        exam3.createdDate = "2026-08-21 19:59:59";
        exam3.creator = account3;
        exam3.categoryQuestion = categoryQuestion3;

        //=============================================
        // ExamQuestion
        ExamQuestion examQuestion1 = new ExamQuestion();
        examQuestion1.id = 1;
        examQuestion1.question = question1;
        examQuestion1.exam = exam1;

        ExamQuestion examQuestion2 = new ExamQuestion();
        examQuestion2.id = 2;
        examQuestion2.question = question2;
        examQuestion2.exam = exam2;

        ExamQuestion examQuestion3 = new ExamQuestion();
        examQuestion3.id = 3;
        examQuestion3.question = question3;
        examQuestion3.exam = exam3;

        //Trong file Program.java, hãy in ít nhất 1 giá trị của mỗi đối tượng ra
        System.out.println("=====Department=====");
        department1.thongTinCuaDepartment();
        department2.thongTinCuaDepartment();
        department3.thongTinCuaDepartment();
        //===================================
        System.out.println("=====Position=====");
        position1.thongTinCuaPosition();
        position2.thongTinCuaPosition();
        position3.thongTinCuaPosition();
        //==================================
        System.out.println("=====Account=====");
        account1.thongTinCuaAccount();
        account2.thongTinCuaAccount();
        account3.thongTinCuaAccount();
        //================================
        System.out.println("=====Group=====");
        group1.thongTinCuaGroup();
        group2.thongTinCuaGroup();
        group3.thongTinCuaGroup();
        //================================
        System.out.println("=====GroupAccount=====");
        groupAccount1.thongTinCuaGroupAcount();
        groupAccount2.thongTinCuaGroupAcount();
        groupAccount3.thongTinCuaGroupAcount();
        //==================================
        System.out.println("=====TypeQuestion=====");
        typeQuestion1.thongTinCuaTypeQuestion();
        typeQuestion2.thongTinCuaTypeQuestion();
        typeQuestion3.thongTinCuaTypeQuestion();
        //=====================================
        System.out.println("=====CategoryQuestion=====");
        categoryQuestion1.thongTinCuaCategoryQuestion();
        categoryQuestion2.thongTinCuaCategoryQuestion();
        categoryQuestion3.thongTinCuaCategoryQuestion();
        //====================================
        System.out.println("=====Question=====");
        question1.thongTinCuaQuestion();
        question2.thongTinCuaQuestion();
        question3.thongTinCuaQuestion();
        //==================================
        System.out.println("=====Answer=====");
        answer1.thongTinCuaAnswer();
        answer2.thongTinCuaAnswer();
        answer3.thongTinCuaAnswer();
        //=================================
        System.out.println("=====Exam=====");
        exam1.thongTinCuaExam();
        exam2.thongTinCuaExam();
        exam3.thongTinCuaExam();
        //==================================
        System.out.println("=====ExamQuestion=====");
        examQuestion1.thongTinCuaExamQuestion();
        examQuestion2.thongTinCuaExamQuestion();
        examQuestion3.thongTinCuaExamQuestion();







    }
    }

