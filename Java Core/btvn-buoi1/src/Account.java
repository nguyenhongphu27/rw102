public class Account {
    int id;
    String email;
    String userName;
    String fullName;
    String createdDate;
    Department department;
    Position position;

    void thongTinCuaAccount(){
        System.out.println("id " + id);
        System.out.println("email " + email);
        System.out.println("userName " + userName);
        System.out.println("fullName " + fullName);
        System.out.println("createdDate " + createdDate);
        System.out.println("Department " + department.name);
        System.out.println("Position " + position.name);
    }

}
