import java.time.LocalDate;

public class Acount {
    int id;
    String username;
    String fullname;
    String email;
    Department department;
    Position position;
    LocalDate createDate;

    public void in(){
        System.out.println("id " + id);
        System.out.println("name " + fullname);
    }
}
