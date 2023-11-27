package git.BookManager;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
 

class DBconnect{
    private String jdbc = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/bookmanager";
    private String user = "root";
    private String password = "324254";
    private Random random = new Random(); private Connection c;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
   
    public DBconnect() {
        try {
            Class.forName(this.jdbc);
            this.c = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    public boolean regist(String name, String pw, String phone, String sex) {
        boolean b = true;
        try {
            String exec_sel = "select username from user where username='" + name + "'";
            Statement s = this.c.createStatement();
            ResultSet r = s.executeQuery(exec_sel);
            if (r.next()) {
                b = false;
            } else {
                try {
                    String exec = "insert into user (id,username,password,role,sex,phone) values (?,?,?,0,?,?)";
                    PreparedStatement p = this.c.prepareStatement(exec);
                    p.setInt(1, this.random.nextInt(101));
                    p.setString(2, name);
                    p.setString(3, pw);
                    p.setString(4, sex);
                    p.setString(5, phone);
                    p.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return b;
    }
    public int login(String name, String pw, int role, boolean power) {
        int x = 0;
        String exec = "select password,id from user where username=? and (role=0 or role=1)";
        if (power) exec = "select password,id from user where username=? and role=1"; 
        try {
            PreparedStatement p = this.c.prepareStatement(exec);
            p.setString(1, name);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                String password = r.getString("password");
                if (password.equals(pw)) x = r.getInt("id"); 
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    public boolean addbook(String name, int type, String author, String publish, double price, int number, String remark) {
        String exec = "insert into book (id,book_name,type_id,author,publish,price,number,status,remark) values (?,?,?,?,?,?,?,1,?)";
        boolean b = true;
        try {
            PreparedStatement p = this.c.prepareStatement(exec);
            p.setInt(1, this.random.nextInt(101));
            p.setString(2, name);
            p.setInt(3, type);
            p.setString(4, author);
            p.setString(5, publish);
            p.setDouble(6, price);
            p.setInt(7, number);
            p.setString(8, remark);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        } 
        return b;
    }
    public String[][] table(int t, String book_name, String book_author, String username) {
        String[][] xtable = new String[0][];
        String exec = "";
        if (t == 0 || t == 1 || t == 2) {
            if (t == 0) { exec = "select * from book"; }
            else if (t == 1) { exec = "select * from book where book_name='" + book_name + "'"; }
            else if (t == 2) { exec = "select * from book where author='" + book_author + "'"; }
            try {
                Statement s = this.c.createStatement(1004, 1007);
                ResultSet r = s.executeQuery(exec);
                int xnum = 5;
                int ynum = 0;
                for (; r.next(); ynum++);
                xtable = new String[ynum][xnum];
                r.beforeFirst();
                int y = 0;
                while (r.next()) {
                    String id = r.getString("id");
                    String name = r.getString("book_name");
                    String type = r.getString("type_id");
                    if (type.equals("1")) { type = "小说"; }
                    else if (type.equals("2")) { type = "教材"; }
                    else if (type.equals("3")) { type = "科普"; }
                    else if (type.equals("4")) { type = "杂志"; }
                    String author = r.getString("author");
                    String remark = r.getString("remark");
                    String[] data = { id, name, type, author, remark };
                    for (int x = 0; x < xnum; x++) {
                        xtable[y][x] = data[x];
                    }
                    y++;
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else if (t == 3) {
            exec = "select * from borrowdetail where user_id='" + MyBookManager.Id + "'";
            try {
                Statement s = this.c.createStatement(1004, 1007);
                ResultSet r = s.executeQuery(exec);
                int xnum = 5;
                int ynum = 0;
                for (; r.next(); ynum++);
                xtable = new String[ynum][xnum];
                r.beforeFirst();
                int y = 0;
                while (r.next()) {
                    String id = r.getString("id");
                    String bookname = r.getString("book_name");
                    String status = r.getString("status");
                    if (status.equals("1")) { status = "在读"; }
                    else { status = "已还"; }
                    String borrow_time = r.getString("borrow_time");
                    String return_time = r.getString("return_time");
                    String[] data = { id, bookname, status, borrow_time, return_time };
                    for (int x = 0; x < xnum; x++) {
                        xtable[y][x] = data[x];
                    }
                    y++;
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (t == 4 || t == 5 || t == 6) {
            if (t == 4) { exec = "select * from book where book_name='" + book_name + "'"; }
            else if (t == 5) { exec = "select * from book where author='" + book_author + "'"; }
            else if (t == 6) { exec = "select * from book"; }
            try {
                Statement s = this.c.createStatement(1004, 1007);
                ResultSet r = s.executeQuery(exec);
                int xnum = 7;
                int ynum = 0;
                for (; r.next(); ynum++);
                xtable = new String[ynum][xnum];
                r.beforeFirst();
                int y = 0;
                while (r.next()) {
                    String book_id = r.getString("id");
                    String name = r.getString("book_name");
                    String type = r.getString("type_id");
                    if (type.equals("1")) { type = "小说"; }
                    else if (type.equals("2")) { type = "教材"; }
                    else if (type.equals("3")) { type = "科普"; }
                    else if (type.equals("4")) { type = "杂志"; }
                    String author = r.getString("author");
                    String price = r.getString("price");
                    String number = r.getString("number");
                    String status = r.getString("status");
                    if (status.equals("0")) { status = "下架"; }
                    else if (status.equals("1")) { status = "上架"; }
                    String[] data = { book_id, name, type, author, price, number, status };
                    for (int x = 0; x < xnum; x++) {
                        xtable[y][x] = data[x];
                    }
                    y++;
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (t == 7 || t == 8) {
            if (t == 7) { exec = "select * from user"; }
            else { exec = "select * from user where username='" + username + "'"; }
            try {
                Statement s = this.c.createStatement(1004, 1007);
                ResultSet r = s.executeQuery(exec);
                int xnum = 5;
                int ynum = 0;
                for (; r.next(); ynum++);
                xtable = new String[ynum][xnum];
                r.beforeFirst();
                int y = 0;
                while (r.next()) {
                    String id = r.getString("id");
                    String name = r.getString("username");
                    String password = r.getString("password");
                    String sex = r.getString("sex");
                    String phone = r.getString("phone");
                    String[] data = { id, name, password, sex, phone };
                    for (int x = 0; x < xnum; x++) {
                        xtable[y][x] = data[x];
                    }
                    y++;
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (t == 9) {
            exec = "select * from borrowdetail";
            try {
                Statement s = this.c.createStatement(1004, 1007);
                ResultSet r = s.executeQuery(exec);
                int xnum = 5;
                int ynum = 0;
                for (; r.next(); ynum++);
                xtable = new String[ynum][xnum];
                r.beforeFirst();
                int y = 0;
                while (r.next()) {
                    String user_name = r.getString("user_name");
                    String bookname = r.getString("book_name");
                    String status = r.getString("status");
                    if (status.equals("1")) { status = "在读"; }
                    else { status = "已还"; }
                    String borrow_time = r.getString("borrow_time");
                    String return_time = r.getString("return_time");
                    String[] data = { user_name, bookname, status, borrow_time, return_time };
                    for (int x = 0; x < xnum; x++) {
                        xtable[y][x] = data[x];
                    }
                    y++;
                } 
            } catch (Exception e) {
                e.printStackTrace();
            } 
        } 
        return xtable;
    }
    public boolean status(int user_id, int book_id, String book_name, int i) {
        boolean b = false;
        String exec = "";
        if (i == 0) {
            exec = "select status from borrowdetail where user_id=? and book_id=?";
            try {
                PreparedStatement p = this.c.prepareStatement(exec);
                p.setInt(1, user_id);
                p.setInt(2, book_id);
                ResultSet r = p.executeQuery();
            if (r.next() && r.getInt("status") == 1) b = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else if (i == 1 || i == 2 || i == 3) {
            if (i == 1) { exec = "select id from book where id=? and book_name='" + book_name + "'"; }
            else if (i == 2) { exec = "select id from book where id=?"; }
            else if (i == 3) { exec = "select id from borrowdetail where id=?"; }
            try {
                PreparedStatement p = this.c.prepareStatement(exec);
                p.setInt(1, book_id);
                ResultSet r = p.executeQuery();
                if (r.next()) {
                    b = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        else {
            exec = "select id from user where id='" + user_id + "'";
            try {
                Statement s = this.c.createStatement();
                ResultSet r = s.executeQuery(exec);
                for (; r.next(); b = true);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        } 
        return b;
    }
    public boolean borrowbook(int user_id, String username, int book_id, String book_name) {
        boolean b = true;
        String exec = "insert into borrowdetail (id,user_id,user_name,book_id,book_name,status,borrow_time,return_time) values (?,?,?,?,?,1,?,?)";
        try {
            LocalDateTime time_now = LocalDateTime.now();
            String time = time_now.format(this.formatter);
            PreparedStatement p = this.c.prepareStatement(exec);
            p.setInt(1, this.random.nextInt(101));
            p.setInt(2, user_id);
            p.setString(3, username);
            p.setInt(4, book_id);
            p.setString(5, book_name);
            p.setString(6, time);
            p.setString(7, "暂未归还");
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        } 
        return b;
    }
    public boolean returnbook(int id) {
        boolean b = true;
        try {
            String exec = "select status from borrowdetail where id='" + id + "'";
            Statement s = this.c.createStatement();
            ResultSet r = s.executeQuery(exec);
            if (r.next()) {
                if (r.getInt("status") == 1) {
                    exec = "update borrowdetail set status=0,return_time=? where id=?";
                    LocalDateTime time_now = LocalDateTime.now();
                    String time = time_now.format(this.formatter);
                    PreparedStatement p = this.c.prepareStatement(exec);
                    p.setString(1, time);
                    p.setInt(2, id);
                    p.executeUpdate();
                } else {
                    b = false;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        } 
        return b;
    }
    public boolean modifybook(int bookid, String bookname, int type, String author, String publish, double price, int number, int status, String remark) {
        boolean b = true;
        String exec = "update book set book_name=?,type_id=?,author=?,publish=?,price=?,number=?,status=?,remark=? where id='" + bookid + "'";
        try {
            PreparedStatement p = this.c.prepareStatement(exec);
            p.setString(1, bookname);
            p.setInt(2, type);
            p.setString(3, author);
            p.setString(4, publish);
            p.setDouble(5, price);
            p.setInt(6, number);
            p.setInt(7, status);
            p.setString(8, remark);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        } 
        return b;
    }
    public boolean modifyuser(String username, String password, String sex, String phone, int id) {
        boolean b = true;
        String exec = "update user set username=?,password=?,sex=?,phone=? where id='" + id + "'";
        try {
            PreparedStatement p = this.c.prepareStatement(exec);
            p.setString(1, username);
            p.setString(2, password);
            p.setString(3, sex);
            p.setString(4, phone);
            p.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        } 
        return b;
    }
}
