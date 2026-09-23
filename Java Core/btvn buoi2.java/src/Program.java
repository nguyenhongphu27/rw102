import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
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
        group2.name = "PUBG";

        Group group3 = new Group();
        group3.id = 3;
        group3.name = "Free Fire";

        //=====================================
        //GroupAccount
        GroupAccount groupAccount1 = new GroupAccount();
        groupAccount1.account = account1;
        groupAccount1.group = group1;
        groupAccount1.joinDate = LocalDate.of(2026,03,01);

        GroupAccount groupAccount2 = new GroupAccount();
        groupAccount2.account = account2;
        groupAccount2.group = group2;
        groupAccount2.joinDate = LocalDate.of(2026,03,02);

        GroupAccount groupAccount3 = new GroupAccount();
        groupAccount3.account = account3;
        groupAccount3.group = group3;
        groupAccount3.joinDate = LocalDate.of(2026,03,03);

        //=========================================
        //TypeQuestion
        TypeQuestion typeQuestion1 = new TypeQuestion();
        typeQuestion1.id = 1;
        typeQuestion1.name = "Essay";

        TypeQuestion typeQuestion2 = new TypeQuestion();
        typeQuestion2.id = 2;
        typeQuestion2.name = "Multiple Choice";

        TypeQuestion typeQuestion3 = new TypeQuestion();
        typeQuestion3.id = 3;
        typeQuestion3.name = "Coding";

        //==============================================
        //CategoryQuestion
        CategoryQuestion categoryQuestion1 = new CategoryQuestion();
        categoryQuestion1.id = 1;
        categoryQuestion1.name = "Java";

        CategoryQuestion categoryQuestion2 = new CategoryQuestion();
        categoryQuestion2.id = 2;
        categoryQuestion2.name = "Git";

        CategoryQuestion categoryQuestion3 = new CategoryQuestion();
        categoryQuestion3.id = 3;
        categoryQuestion3.name = "MySQL";

        //============================================
        // Question
        Question question1 = new Question();
        question1.id = 1;
        question1.content = "Java là gì";
        question1.createdDate = "2026-08-21 19:59:59";
        question1.typeQuestion = typeQuestion1;
        question1.categoryQuestion = categoryQuestion1;

        Question question2 = new Question();
        question2.id = 2;
        question2.content = "SQL là gì";
        question2.createdDate = "2026-08-22 19:59:59";
        question2.typeQuestion = typeQuestion2;
        question2.categoryQuestion = categoryQuestion2;

        Question question3 = new Question();
        question3.id = 3;
        question3.content = "Class trong Java là gì";
        question3.createdDate = "2026-08-23 19:59:59";
        question3.typeQuestion = typeQuestion3;
        question3.categoryQuestion = categoryQuestion3;

        //===============================================
        // Answer  id content question
        Answer answer1 = new Answer();
        answer1.id = 1;
        answer1.content = "1+1=2";
        answer1.isCorrect = "true";
        answer1.question = question1;

        Answer answer2 = new Answer();
        answer2.id = 2;
        answer2.content = "2x2=5";
        answer2.isCorrect = "false";
        answer2.question = question2;

        Answer answer3 = new Answer();
        answer3.id = 3;
        answer3.content = "9:3=3";
        answer3.isCorrect = "true";
        answer3.question = question3;

        //=======================================
        // Exam
        Exam exam1 = new Exam();
        exam1.id = 1;
        exam1.code = "JV101";
        exam1.title = "Java Quick Quiz";
        exam1.duration = "30";
        exam1.createdDate = LocalDate.of(2026,10,20);
        exam1.creator = account1;
        exam1.categoryQuestion = categoryQuestion1;

        Exam exam2 = new Exam();
        exam2.id = 2;
        exam2.code = "SQ201";
        exam2.title = "SQL Starter Test";
        exam2.duration = "45";
        exam2.createdDate = LocalDate.of(2026,10,21);
        exam2.creator = account2;
        exam2.categoryQuestion = categoryQuestion2;

        Exam exam3 = new Exam();
        exam3.id = 3;
        exam3.code = "SC401";
        exam3.title = "Scrum Mini Check";
        exam3.duration = "35";
        exam3.createdDate = LocalDate.of(2026,10,22);
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

        // Question 1:
        //  Kiểm tra account thứ 2
        //  Nếu không có phòng ban (tức là department == null) thì sẽ in ra text
        // "Nhân viên này chưa có phòng ban"
        //  Nếu không thì sẽ in ra text "Phòng ban của nhân viên này là …"

        Account[] accounts = {account1, account2, account3};
        Account account = accounts[1];
        if (account.department == null) {
            System.out.println("nhân viên này có chứa phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là ");
        }

        //Question 2:
        // Kiểm tra account thứ 2
        // Nếu không có group thì sẽ in ra text "Nhân viên này chưa có group"
        // Nếu có mặt trong 1 hoặc 2 group thì sẽ in ra text "Group của nhân viên này là Java Fresher, C# Fresher"
        // Nếu có mặt trong 3 Group thì sẽ in ra text "Nhân viên này là người quan trọng, tham gia nhiều group"
        // Nếu có mặt trong 4 group trở lên thì sẽ in ra text "Nhân viên này là người hóng chuyện, tham gia tất cả các group"

        int soGroup = 3;
        if (soGroup == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (soGroup <= 2) {
            System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
        } else if (soGroup == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");

            //Question 3:
            //Sử dụng toán tử ternary để làm Question 1

            System.out.println(account.department == null
                    ? "Nhân viên này chưa có phòng ban" : "Phòng ban của nhân viên này là ");

            //Question 4:
            //Sử dụng toán tử ternary để làm yêu cầu sau:
            //Kiểm tra Position của account thứ 1
            //Nếu Position = Dev thì in ra text "Đây là Developer"
            //Nếu không phải thì in ra text "Người này không phải là Developer"

            String name = "Dev";

            if (name == "Dev") {
                System.out.println("Đây là Developer");
            } else {
                System.out.println("Người này không phải là Developer");

                //Question 5:
                //Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format sau: Nếu số lượng account = 1 thì in ra "Nhóm có một thành viên"
                //Nếu số lượng account = 2 thì in ra "Nhóm có hai thành viên"
                //Nếu số lượng account = 3 thì in ra "Nhóm có ba thành viên"
                //Còn lại in ra "Nhóm có nhiều thành viên"

                int soAccount = 3;

                if (soAccount == 1) {
                    System.out.println("Nhóm có một thành viên");
                } else if (soAccount == 2) {
                    System.out.println("Nhóm có hai thành viên");
                } else if (soAccount == 3) {
                    System.out.println("Nhóm có ba thành viên");
                } else {
                    System.out.println("Nhóm có nhiều thành viên");
                }

                //Question 6:
                //Sử dụng switch case để làm lại Question 2

                switch (soGroup) {
                    case 1:
                        System.out.println("Nhân viên này chưa có group");
                        break;
                    case 2:
                        System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
                        break;
                    case 3:
                        System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                        break;
                    default:
                        System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
                }

                //Question 7:
                //Sử dụng switch case để làm lại Question 4

                switch (name) {
                    case "Dev":
                        System.out.println("Đây là Developer");
                        break;
                    default:
                        System.out.println("Người này không phải là Developer");
                }

                //Question 8:
                //In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ

                System.out.println("Email: " + account.email);
                System.out.println("FullName: " + account.fullName);
                System.out.println("Phòng ban: " + account.department.name);

                //Question 9:
                //In ra thông tin các phòng ban bao gồm: id và name

                Department[] departments = new Department[3];
                for (int i = 0; i < departments.length; i++) {
                    System.out.println("id: " + departments[i].id);
                    System.out.println("name: " + departments[i].name);
                }

                //Question 10:
                //In ra thông tin các account bao gồm: Email, FullName và tên phòng ban của họ theo định dạng như sau:
                //Thông tin account thứ 1 là:
                //Email: NguyenVanA@gmail.com
                //Full name: Nguyễn Văn A
                //Phòng ban: Sale

                //Thông tin account thứ 2 là:
                //Email: NguyenVanB@gmail.com
                //Full name: Nguyễn Văn B
                //Phòng ban: Marketting

                for (int i = 0; i < accounts.length; i++) {
                    System.out.println("Thông tin account thứ " + (i + 1) + " là:");
                    System.out.println("Email: " + accounts[i].email);
                    System.out.println("Full name: " + accounts[i].fullName);
                    System.out.println("Phòng ban: " + accounts[i].department.name);
                }

                //Question 11:
                //In ra thông tin các phòng ban bao gồm: id và name theo định dạng sau:
                //Thông tin department thứ 1 là:
                //Id: 1
                //Name: Sale
                //Thông tin department thứ 2 là:
                //Id: 2
                //Name: Marketing

                for (int i = 0; i < departments.length; i++) {
                    System.out.println("Thông tin department thứ " + (i + 1) + " là:");
                    System.out.println("Id: " + departments[i].id);
                    System.out.println("Name: " + departments[i].name);
                }

                //Question 13:
                //In ra thông tin tất cả các account ngoại trừ account thứ 2

                for (int i = 0; i < accounts.length; i = i + 1) {
                    if (i != 1) {
                        System.out.println("Email: " + accounts[i].email);
                        System.out.println("Full name: " + accounts[i].fullName);
                        System.out.println("Phòng ban: " + accounts[i].department.name);
                    }
                }

                //Question 14:
                //In ra thông tin tất cả các account có id < 4

                for (Account acc : accounts) {
                    if (acc.id < 4) {
                        System.out.println("Email: " + acc.email);
                        System.out.println("Full name: " + acc.fullName);
                    }
                }
                //Question 15:
                //In ra các số chẵn nhỏ hơn hoặc bằng 20

                for (int i = 0; i <= 20; i = i + 2) {
                    System.out.println(i);
                }


            }
        }


    }


}

