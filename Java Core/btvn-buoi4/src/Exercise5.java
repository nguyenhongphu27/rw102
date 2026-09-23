public class Exercise5 {
    public static void question5() {
        Account[] accounts = new Account[5];
        if (accounts[0].department == null || accounts[1].department == null) {
            System.out.println("Có phòng ban bị null");
        } else if (accounts[0].department.name.equals(accounts[1].department.name)) {
            System.out.println("2 phòng ban bằng nhau");
        } else {
            System.out.println("2 phòng ban không bằng nhau");
        }
    }

    public static void question6() {
        Department[] departments = new Department[5];

        departments[0] = new Department();
        departments[0].name = "Marketing";

        departments[1] = new Department();
        departments[1].name = "Accounting";

        departments[2] = new Department();
        departments[2].name = "Sale";

        departments[3] = new Department();
        departments[3].name = "Boss of director";

        departments[4] = new Department();
        departments[4].name = "Waiting room";

        // Sắp xếp
        for (int i = 0; i < 4; i++) {
            for (int a = i + 1; a < 5; a++) {

                if (departments[i].name.charAt(0) > departments[a].name.charAt(0)) {
                    Department temp = departments[i];
                    departments[i] = departments[a];
                    departments[a] = temp;
                }
            }
        }

        // In danh sách
        for (int i = 0; i < 5; i++) {
            System.out.println(departments[i].name);
        }
    }
    public static void question7(){
        Department[] departments = new Department[5];

        departments[0] = new Department();
        departments[0].name = "Sale";

        departments[1] = new Department();
        departments[1].name = "Marketing";

        departments[2] = new Department();
        departments[2].name = "Accounting";

        departments[3] = new Department();
        departments[3].name = "waiting room";

        departments[4] = new Department();
        departments[4].name = "Boss of director";
        // Sắp xếp
        for (int i = 0; i < 4; i++) {
            for (int a = i + 1; a < 5; a++) {

                if (departments[i].name.charAt(0) > departments[a].name.charAt(0)) {
                    Department temp = departments[i];
                    departments[i] = departments[a];
                    departments[a] = temp;
                }
            }
        }
        // In danh sách
        for (int i = 0; i < 5; i++) {
            System.out.println(departments[i].name);
        }
    }


}