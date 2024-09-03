import java.util.*;

public class Ecommerce {
    public static void main(String[] args) throws ProductNotFoundException {
        Product product = new Product(1,"product1",10,"description",1);
        product.addProduct(product);

    }
    static class Product {
        private int id;
        private String name;
        private int price;
        private String description;
        private int stock;
        private int quantity;
        public Product(int id, String name, int price, String description, int stock,int quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
            this.stock = stock;
            this.quantity = quantity;
        }
        private List<Product> products = new ArrayList<>();
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public int getPrice() {
            return price;
        }
        public String getDescription() {
            return description;
        }
        public int getStock() {
            return stock;
        }
        public void setPrice(int price) {
            this.price = price;
        }
        public void setName(String name) {
            this.name =name;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public void setStock(int stock) {
            this.stock = stock;
        }
        public List<Product> getAllProducts() {
            System.out.println("all products: "+products);
            return products;
        }
        public Product getProductById(int id) throws ProductNotFoundException{
            for(Product p : products){
                if(p.getId() == id){
                    return p;
                }
            }
            throw new ProductNotFoundException("No product with id:" +id);
        }
        public void addProduct(Product product) {
            products.add(product);
            System.out.println(product.getName()+"isimli product eklendi");
        }
        public void updateProduct(int id,Product product) throws ProductNotFoundException {
            boolean productFound = false;
            for(Product p : products) {
                if (p.getId() == id) {
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setStock(product.getStock());
                    productFound = true;
                }
            }

            if(!productFound){
                throw new ProductNotFoundException("Product Not Found");
            }
            else{
                System.out.println(product.getName()+"isimli product güncellendi");
            }
        }
        public void manageStock(int id,int quantity) throws ProductNotFoundException {
            Product product = getProductById(id);
            product.setStock(product.getStock() + quantity);
        }

    }
    static class Customer {
        private int id;
        private String name;
        private String email;
        public Customer(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public void setName(String name) {
            this.name = name;
        }


    }
    static class Order {
        private int id;
        private int customerId;
        private Customer customer;
        private Product product;
        private int totalPrice;
        public Order(int id, int customerId, Customer customer,Product product, int totalPrice) {
            this.id = id;
            this.customerId = customerId;
            this.customer = customer;
            this.product = product;
            this.totalPrice = totalPrice;
        }
        private List<Order> orders = new ArrayList<>();
        public int getId() {
            return id;
        }
        public int getCustomerId() {
            return customerId;
        }
        public Customer getCustomer() {
            return customer;
        }
        public int getTotalPrice() {
            return totalPrice;
        }
        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }
        public void createOrder(Order order) {
            orders.add(order);
            for(Product product : order.product.getAllProducts()){
                try {
                    product.manageStock(product.getId(), -1);
                } catch (Exception e) {
                    System.err.println("Error updating stock: " + e.getMessage());
                }
            }

        }
    }




}