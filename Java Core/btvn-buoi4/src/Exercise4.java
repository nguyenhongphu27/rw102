import java.util.Locale;
import java.util.Scanner;
public class Exercise4 {
    public static void question1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập 1 xâu kí tự: ");
        String s = scanner.nextLine();
        String[] arr = s.trim().split("\\s+");
        System.out.println("Số từ của xâu vừa nhập là: " + arr.length);

    }
    public static void question2(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhập xâu 1");
        String s1 = scanner.nextLine();

        System.out.println("nhập xâu 2");
        String s2 = scanner.nextLine();

        String s3 = s1 + s2;

        System.out.println("xâu khi nối lại là " + s3);

    }
    public static void question3(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhập tên");
        String name = scanner.nextLine();
        // viết hoa chữ cái đầu
        name = name.substring(0,1).toUpperCase()
               + name.substring(1).toLowerCase();

        System.out.println("tên sau khi viết hoa" + name);
    }
    public static void question4(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhập tên");
        String name = scanner.nextLine();

        for (int i = 0; i < name.length(); i++){

        System.out.println("ký tự thứ " + (i + 1) + " là:" + name.charAt(i));
        }
    }
    public static void question5(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhập họ");
        String ho = scanner.nextLine();

        System.out.println("nhập tên");
        String ten = scanner.nextLine();

        String hoten = ho + " " + ten;

        System.out.println("họ tên sau khi nhập " + hoten);
    }
    public static void question6(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhập họ và tên");
        String hoTen = scanner.nextLine();

        String[] arr = hoTen.split(" ");

        System.out.println("họ là: " + arr[0]);
        System.out.println("tên đệm là: " + arr[1]);
        System.out.println("tên là: " + arr[2]);
    }
    public static void question7() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập họ và tên:");
        String hoTen = scanner.nextLine();
        // xóa khoảng trắng ở đầu và cuối
        hoTen = hoTen.trim();
        // xóa khoảng trắng thừa ở giữa
        hoTen = hoTen.replace("  ", " ");
        hoTen = hoTen.replace("  ", " ");
        // tách họ và tên
        String[] arr = hoTen.split(" ");
        // tạo chuỗi rỗng để chứa kết quả
        String result = "";
        //đuyệt qua từng phần tử trong arr
        for (int i = 0; i < arr.length; i++) {
            // chữ cái đầu viết hoa, còn lại viết thường
            arr[i] = arr[i].substring(0, 1).toUpperCase()
                    + arr[i].substring(1).toLowerCase();
            result = result + arr[i] + " ";
        }
        System.out.println("Họ và tên sau khi chuẩn hóa: " + result.trim());
    }
    public static void question8() {
        if (Program.groups != null) {
            for (Group group : Program.groups) {
                if (group != null && group.name != null && group.name.contains("Java")) {
                    System.out.println(group.name);
                }
            }
        }
    }
    public static void question9() {
        if (Program.groups != null) {
            for (Group group : Program.groups) {
                if (group != null && group.name != null && group.name.equals("Java")) {
                    System.out.println(group.name);
                }
            }
        }
    }
    public static void question10() {
        String a = "word";
        String b = "drow";

        String daoNguoc = new StringBuilder(a).reverse().toString();

        if (daoNguoc.equals(b)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
    }
    }











