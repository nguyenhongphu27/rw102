import java.time.LocalDate;

public class GroupAccount {
    Account account;
    Group group;
    LocalDate joinDate;

    void thongTinCuaGroupAcount(){
        System.out.println("Account " + account.userName);
        System.out.println("Group " + group.name);
        System.out.println("joinDate " + joinDate);

    }
}

