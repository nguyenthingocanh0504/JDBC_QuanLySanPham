import dao.BrandDAO;
import dao.ProductDAO;
import model.Brand;
import model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static ProductDAO productDAO = new ProductDAO();
    private static BrandDAO brandDAO = new BrandDAO();

    private static void mainMenu() {
        System.out.println("--- QUẢN LÝ SẢN PHẨM ---");
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Xóa sản phẩm theo mã");
        System.out.println("4. Câp nhật thông tin sản phẩm");
        System.out.println("5. Lấy thông tin hãng sx");
        System.out.println("6. Top 5 sản phẩm có giá trị cao nhất");
        System.out.println("7. Danh sách hãng sản xuất");
        System.out.println("8. Thêm hãng sản xuất");
        System.out.println("9. Xóa hãng sản xuất theo mã");
    }

    private static void option1() {
        List<Product> productList = productDAO.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s", "STT", "Tên sản phẩm", "Giá sản phẩm", "Màu sắc");
        System.out.println();
        for (int i = 0; i < productList.size(); i++) {
            Product p = productList.get(i);
            System.out.printf("%-20d %-20s %-20d %-20s\n", (i+1), p.getName(), p.getPrice(), p.getColor());
        };
    }
    private static void option2(Scanner in){
        Product p = new Product();
        System.out.print("\tNhập tên: ");
        p.setName(in.nextLine());
        System.out.print("\tNhập giá: ");
        p.setPrice(Long.parseLong(in.nextLine()));
        System.out.print("\tNhập size: ");
        p.setSize(in.nextLine());
        System.out.print("\tNhập màu sắc: ");
        p.setColor(in.nextLine());
        System.out.println("\tChọn hãng: ");
        List<Brand> brandList = brandDAO.getAll();
        for (int i = 0; i < brandList.size(); i++) {
            System.out.printf("\t\t%-5d %-20s \n", i+1, brandList.get(i).getName());
        }
        // Tam thoi nhap chinh xac
        long brand_id =  brandList.get(Integer.parseInt(in.nextLine()) - 1).getId();

        p.setBrandId(brand_id);

        productDAO.insert(p);

    }

    private static void option3(Scanner in){
        ProductDAO productDAO= new ProductDAO();
        System.out.println("Nhap ma san pham muon xoa: ");
        long maSP = Long.parseLong(in.nextLine());

        productDAO.delete(maSP);
    }

    private static void option4(Scanner in){
        ProductDAO productDAO = new ProductDAO();
        Product p=new Product();
        System.out.println("Cap nhat ma san pham: ");
        long maSP=Long.parseLong(in.nextLine());
        System.out.println("Cap nhat ten san pham: ");
        String tenSP=in.nextLine();
        System.out.println("Cap nhat gia san pham: ");
        Long giaSP=Long.parseLong(in.nextLine());
        System.out.println("Cap nhat size sp: ");
        String size=in.nextLine();
        System.out.println("Nhap mau sac san pham: ");
        String mau=in.nextLine();
        System.out.println("Nhap ma hang: ");
        Long maHang=Long.parseLong(in.nextLine());

        p.setId(maSP);
        p.setName(tenSP);
        p.setPrice(giaSP);
        p.setSize(size);
        p.setColor(mau);
        p.setBrandId(maHang);

        productDAO.update(p,maSP);
    }
    private static void option6() throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAll();
        productList.stream()
                .sorted((o1, o2) -> { //Giảm dần
                    if (o1.getPrice() < o2.getPrice()) {
                        return 1;
                    } else if (o1.getPrice() > o2.getPrice()) {
                        return -1;
                    }
                    return 0;
                })
                .limit(5)
                .forEach(p -> System.out.println(p));
    }

    public static void main(String[] args) throws SQLException {

        Scanner in = new Scanner(System.in);
        int option = -1;

        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            option = Integer.parseInt(in.nextLine());
            // Làm thêm phàn try-catch khi người dùng nhập lỗi
            if (option < 1 || option > 9) {
                System.out.println("Vui lòng nhập lại!");
                continue;
            }
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }

        }
        while (option != 0);
        in.close();

    }
}
